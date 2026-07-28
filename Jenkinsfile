pipeline {
    agent any

    tools {
        maven 'maven-3' 
        jdk 'jdk-17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Clean Workspace') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Execute Serenity API Tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn verify -pl serenity-rest-module'
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'serenity-rest-module/target/site/serenity/**/*', allowEmptyArchive: true
                }
            }
        }

        stage('Execute Karate DSL Tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn test -pl karate-module'
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'karate-module/target/karate-reports/**/*', allowEmptyArchive: true
                }
            }
        }
    }
}
