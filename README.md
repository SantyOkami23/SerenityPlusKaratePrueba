# Automation Exercise API Test Suite

Este repositorio contiene la automatización de la API de **AutomationExercise** (https://automationexercise.com/api_testing) utilizando un enfoque multi-módulo que integra dos potentes frameworks:

1. **Serenity BDD + REST Assured + Cucumber** (Arquitectura Screenplay)
2. **Karate DSL**

El proyecto ha sido diseñado siguiendo estándares estrictos de Clean Code, principios SOLID, y prácticas de mantenibilidad. Se ha implementado también configuración para Inteligencia Artificial mediante directivas en `.agents`.

## 🏗 Arquitectura del Proyecto

El proyecto está estructurado con Maven Multi-Módulo:

```text
├── serenity-rest-module/   # Módulo Serenity + Cucumber (Screenplay)
├── karate-module/          # Módulo Karate DSL
├── .github/workflows/      # Pipelines de GitHub Actions
├── Jenkinsfile             # Pipeline de Jenkins
└── .agents/                # Reglas y Skills para asistentes de IA
```

## 🚀 Requisitos Previos

- Java JDK 17 o superior.
- Apache Maven 3.8 o superior.
- Git.

## ⚙️ Configuración e Instalación

1. Clona este repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```

2. Descarga las dependencias y compila:
   ```bash
   mvn clean install -DskipTests
   ```

## ▶️ Ejecución de las Pruebas

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

## 🤖 Directivas de Inteligencia Artificial (Agents)

Este proyecto cuenta con directivas en la carpeta `.agents/` para guiar a los asistentes de IA durante la extensión del código:
- **`AGENTS.md`**: Reglas de negocio, estándares de nombrado, commits y Clean Code.
- **`skills/api-automation/SKILL.md`**: Contexto del framework, patrones de arquitectura de Screenplay, y uso de Karate DSL para que la IA actúe como Automation Engineer respetando el estilo del equipo.
