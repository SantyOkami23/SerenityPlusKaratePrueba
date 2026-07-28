# Automation Exercise API Test Suite

Este repositorio contiene la automatización de la API de **AutomationExercise** (https://automationexercise.com/api_testing) utilizando un enfoque multi-módulo que integra dos potentes frameworks:

1. **Serenity BDD + REST Assured + Cucumber** (Arquitectura Screenplay)
2. **Karate DSL**

El proyecto ha sido diseñado siguiendo estándares estrictos de Clean Code, principios SOLID, y prácticas de mantenibilidad. Se ha implementado también configuración para Inteligencia Artificial mediante directivas en `.agents`.

## Arquitectura del Proyecto

El proyecto está estructurado con Maven Multi-Módulo:

```text
├── serenity-rest-module/   # Módulo Serenity + Cucumber (Screenplay)
├── karate-module/          # Módulo Karate DSL
├── .github/workflows/      # Pipelines de GitHub Actions
├── Jenkinsfile             # Pipeline de Jenkins
└── .agents/                # Reglas y Skills para asistentes de IA
```

## Requisitos Previos

- Java JDK 17 o superior.
- Apache Maven 3.8 o superior.
- Git.

## Configuración e Instalación

1. Clona este repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```

2. Descarga las dependencias y compila:
   ```bash
   mvn clean install -DskipTests
   ```

## Ejecución de las Pruebas

Puedes ejecutar todo el proyecto (ambos módulos) o ejecutarlos independientemente.

### Ejecutar Todas las Suites
```bash
mvn clean verify
```

### Ejecutar Solo Serenity BDD
```bash
mvn clean verify -pl serenity-rest-module
```
Los reportes HTML detallados se generarán en: `serenity-rest-module/target/site/serenity/index.html`

### Ejecutar Solo Karate DSL
```bash
mvn clean test -pl karate-module
```
Los reportes de Karate se generarán en: `karate-module/target/karate-reports/karate-summary.html`

## Reportes de Pruebas (Evidencias)

A continuación se muestran evidencias de la correcta ejecución de todos los escenarios de prueba en ambos frameworks:

### Reporte de Serenity BDD
![Reporte de Serenity BDD](assets/serenity-report.png)

### Reporte de Karate DSL
![Reporte de Karate DSL](assets/karate-report.png)

## Directivas de Inteligencia Artificial (Agents)

Este proyecto cuenta con directivas en la carpeta `.agents/` para guiar a los asistentes de IA durante la extensión del código:
- **`AGENTS.md`**: Reglas de negocio, estándares de nombrado, commits y Clean Code.
- **`skills/api-automation/SKILL.md`**: Contexto del framework, patrones de arquitectura de Screenplay, y uso de Karate DSL para que la IA actúe como Automation Engineer respetando el estilo del equipo.

## Integración Continua (CI/CD)

El proyecto está configurado para ejecutarse automáticamente en el flujo de CI/CD.

Cada vez que se hace un *push* a la rama `master`, **GitHub Actions** dispara automáticamente la ejecución de todas las pruebas (tanto de Serenity como de Karate). Una vez finalizadas, los reportes en formato HTML se empaquetan y se generan como artefactos descargables (Artifacts) directamente en la plataforma.

A continuación se muestran evidencias de las ejecuciones automáticas en GitHub Actions:

![Ejecución Exitosa en GitHub Actions](assets/github-actions-run.png)
<br/>
![Reportes generados como Artifacts](assets/github-actions-artifacts.png)

### Ejecución Local en Jenkins

Además de GitHub Actions, el repositorio incluye un `Jenkinsfile` completo que permite la ejecución de todas las pruebas en una instancia local de Jenkins. Al finalizar el pipeline de manera exitosa, los reportes HTML de Serenity y Karate son comprimidos y guardados usando la funcionalidad nativa de *Artifacts* de Jenkins.

![Ejecución Exitosa en Jenkins](assets/jenkins-run.png)
