pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Clean Workspace') {
            steps {
                bat 'C:\\Users\\PC\\Downloads\\PruebaTecnicaOpcionUno\\maven\\bin\\mvn clean'
            }
        }

        stage('Execute Serenity API Tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'C:\\Users\\PC\\Downloads\\PruebaTecnicaOpcionUno\\maven\\bin\\mvn verify -pl serenity-rest-module'
                }
            }
            post {
                always {
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'serenity-rest-module/target/site/serenity',
                        reportFiles: 'index.html',
                        reportName: 'Serenity BDD Report'
                    ])
                }
            }
        }

        stage('Execute Karate DSL Tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'C:\\Users\\PC\\Downloads\\PruebaTecnicaOpcionUno\\maven\\bin\\mvn test -pl karate-module'
                }
            }
            post {
                always {
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'karate-module/target/karate-reports',
                        reportFiles: 'karate-summary.html',
                        reportName: 'Karate Test Report'
                    ])
                }
            }
        }
    }
}
