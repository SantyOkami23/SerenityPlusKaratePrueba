$jobName = "API-Automation-Tests"
$jenkinsUrl = "http://localhost:8080"
$authUser = "admin"
$authPass = "password"

$authString = [System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes("$($authUser):$($authPass)"))
$headers = @{
    Authorization = "Basic $authString"
}

# Obtener Crumb (CSRF Token)
try {
    $crumbResponse = Invoke-RestMethod -Uri "$jenkinsUrl/crumbIssuer/api/json" -Headers $headers -Method Get
    $headers.Add($crumbResponse.crumbRequestField, $crumbResponse.crumb)
} catch {
    Write-Host "No se pudo obtener CSRF Crumb. Asumiendo que está deshabilitado."
}

$configXml = @"
<?xml version='1.1' encoding='UTF-8'?>
<flow-definition plugin="workflow-job">
  <description>Pipeline de Pruebas Automatizadas (Serenity BDD y Karate DSL)</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <definition class="org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition" plugin="workflow-cps">
    <scm class="hudson.plugins.git.GitSCM" plugin="git">
      <configVersion>2</configVersion>
      <userRemoteConfigs>
        <hudson.plugins.git.UserRemoteConfig>
          <!-- Se usa la URL publica o requerirá configurar las credenciales nativamente en Jenkins -->
          <url>https://github.com/SantyOkami23/SerenityPlusKaratePrueba.git</url>
        </hudson.plugins.git.UserRemoteConfig>
      </userRemoteConfigs>
      <branches>
        <hudson.plugins.git.BranchSpec>
          <name>*/master</name>
        </hudson.plugins.git.BranchSpec>
      </branches>
      <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
      <submoduleCfg class="empty-list"/>
      <extensions/>
    </scm>
    <scriptPath>Jenkinsfile</scriptPath>
    <lightweight>true</lightweight>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>
"@

$apiUrl = "$jenkinsUrl/createItem?name=$jobName"
$contentType = "application/xml"

Write-Host "Creando el job $jobName..."
try {
    $response = Invoke-RestMethod -Uri $apiUrl -Method Post -Headers $headers -Body $configXml -ContentType $contentType
    Write-Host "Pipeline creado exitosamente en Jenkins!"
    
    # Trigger build
    Write-Host "Disparando primera ejecución..."
    Invoke-RestMethod -Uri "$jenkinsUrl/job/$jobName/build" -Method Post -Headers $headers
    Write-Host "Ejecución disparada. Revisa Jenkins en tu navegador."
} catch {
    Write-Host "Error al crear el job:"
    Write-Host $_.Exception.Message
}
