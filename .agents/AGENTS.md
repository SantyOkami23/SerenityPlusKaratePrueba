# 🤖 AI-Assisted Development Guidelines

> Este proyecto utiliza asistencia de agentes de IA para el desarrollo, la generación de código
> y la automatización de pruebas. Todos los artefactos generados con IA siguen los lineamientos
> de calidad, arquitectura y buenas prácticas definidos en este documento.

---

## 📐 Principios de Diseño y Arquitectura

### SOLID Principles

Todo el código del proyecto —ya sea escrito manualmente o generado con asistencia de IA— debe adherirse
estrictamente a los principios SOLID:

| Principio | Aplicación en este proyecto |
|---|---|
| **S** — Single Responsibility | Cada clase tiene una única responsabilidad. Los API Clients solo manejan comunicación HTTP; los modelos solo representan datos; los step definitions solo orquestan pasos de prueba. |
| **O** — Open/Closed | Las clases base (ej. `BaseApiClient`) están abiertas a extensión pero cerradas a modificación. Nuevos endpoints se agregan creando nuevos clients que heredan del base. |
| **L** — Liskov Substitution | Las subclases de API Clients pueden sustituir a su clase base sin alterar el comportamiento esperado. |
| **I** — Interface Segregation | Las interfaces son específicas y cohesivas. No se fuerza a implementar métodos que no se usan. |
| **D** — Dependency Inversion | Las capas de alto nivel (tests, step definitions) dependen de abstracciones (interfaces de clients), no de implementaciones concretas. |

### Clean Code

- **Nomenclatura descriptiva**: nombres de clases, métodos y variables que expresen claramente su intención.
- **Métodos cortos y enfocados**: cada método realiza una sola tarea bien definida.
- **Sin comentarios innecesarios**: el código se auto-documenta; los comentarios se reservan para el *porqué*, no el *qué*.
- **Sin código muerto**: no se deja código comentado, imports no utilizados ni métodos sin uso.
- **DRY (Don't Repeat Yourself)**: la lógica reutilizable se extrae en métodos o clases utilitarias.

### Escalabilidad y Mantenibilidad

- **Arquitectura multi-módulo Maven**: cada framework de pruebas vive en su propio módulo con independencia de compilación y ejecución.
- **Configuración externalizada**: URLs, credenciales y parámetros de entorno se gestionan en archivos de configuración (`serenity.conf`, `karate-config.js`), nunca hardcodeados.
- **Datos de prueba dinámicos**: se usa DataFaker para generación de datos aleatorios, evitando dependencia de datos estáticos.
- **Independencia de tests**: cada prueba es autónoma — crea sus precondiciones, ejecuta y limpia su estado.

---

## 🏗️ Estructura del Código

### Capas de Arquitectura (Serenity REST Module)

```
src/main/java/
├── api/
│   ├── clients/       → Comunicación HTTP (un client por dominio funcional)
│   ├── models/        → POJOs/Records para request/response (inmutables)
│   └── builders/      → Builders para requests complejos
├── config/            → Configuración centralizada
└── utils/
    └── data/          → Generadores de datos de prueba (DataFaker)

src/test/java/
├── runners/           → Cucumber/JUnit Runners
└── stepdefinitions/
    └── api/           → Step definitions organizados por feature
```

### Capas de Arquitectura (Karate Module)

```
src/test/
├── java/              → JUnit5 Runners por dominio funcional
└── resources/
    ├── karate-config.js   → Configuración global
    └── features/          → Feature files organizados por dominio
```

---

## 🧪 Estándares de Testing

### Convenciones de Feature Files (Gherkin)

- Cada feature file cubre un dominio funcional (Products, Brands, Search, Auth, Users).
- Los escenarios incluyen tags para filtrado: `@api`, `@positive`, `@negative`, `@smoke`.
- Los nombres de escenarios son descriptivos y siguen el patrón: `Verbo + recurso + condición`.

### Estructura de Test Cases

- **Precondiciones claras** (Given): preparar datos y estado.
- **Acción única** (When): ejecutar la operación bajo prueba.
- **Verificaciones específicas** (Then): validar status code, response body, mensajes de error.

### Aserciones

- Validar siempre: response code, estructura del body, mensajes esperados.
- En pruebas negativas: verificar mensajes de error y códigos de estado incorrectos.
- Usar matchers expresivos (Hamcrest/AssertJ en Serenity, `match` nativo en Karate).

---

## 🚫 Anti-Patrones Prohibidos

| ❌ No hacer | ✅ Hacer en su lugar |
|---|---|
| `System.out.println()` | SLF4J + Logback |
| URLs hardcodeadas en el código | Archivos de configuración (`serenity.conf`, `karate-config.js`) |
| Datos de prueba estáticos/fijos | DataFaker para datos dinámicos |
| Tests dependientes entre sí | Cada test crea y limpia sus propios datos |
| `application/json` body para esta API | `application/x-www-form-urlencoded` con form params |
| Clases "God Object" con múltiples responsabilidades | Una clase = una responsabilidad |
| Lógica de negocio en step definitions | Delegar a API Clients y helpers |
| Catch genérico de excepciones (`catch (Exception e)`) | Manejo específico de excepciones |
| Ignorar tests fallidos con `@Ignore` | Corregir o documentar como `@Pending` con justificación |

---

## 📦 Convenciones de Commits

Se usa [Conventional Commits](https://www.conventionalcommits.org/) para mantener un historial limpio y trazable:

| Prefijo | Uso |
|---|---|
| `docs:` | Documentación (plan de pruebas, README) |
| `feat(módulo):` | Nueva funcionalidad o componente |
| `fix(módulo):` | Corrección de bugs o ajustes |
| `test(módulo):` | Nuevos tests o ajustes de tests |
| `chore:` | Configuración de proyecto, dependencias |
| `ci:` | CI/CD (GitHub Actions, Jenkinsfile) |
| `refactor(módulo):` | Mejora de código sin cambio de funcionalidad |

---

## 🔧 Stack Tecnológico

| Componente | Tecnología | Versión |
|---|---|---|
| Lenguaje | Java | 17+ |
| Build | Maven | Multi-módulo |
| Framework 1 | Serenity BDD + REST Assured + Cucumber | 4.x |
| Framework 2 | Karate DSL | 1.x |
| Test Runner | JUnit 5 | Platform Suite |
| Datos de Prueba | DataFaker | 2.x |
| Logging | SLF4J + Logback | — |
| CI/CD | GitHub Actions + Jenkins | — |
| Reportes | Serenity Reports + Karate Reports | — |

---

## 📋 Flujo de Trabajo con IA

El desarrollo asistido por IA en este proyecto sigue un flujo disciplinado:

1. **Plan primero**: se genera el plan de pruebas antes de cualquier línea de código.
2. **Diseño por capas**: cada componente se diseña siguiendo la arquitectura definida.
3. **Implementación incremental**: se trabaja por fases con commits atómicos y descriptivos.
4. **Verificación continua**: cada fase se verifica antes de avanzar a la siguiente.
5. **Revisión humana**: todo código generado por IA es revisado y aprobado por el equipo.

> [!IMPORTANT]
> El uso de IA no reemplaza el criterio del ingeniero. Todo artefacto generado es validado
> contra los estándares de calidad del equipo antes de ser integrado al codebase.

---

## 📚 Referencias

- [Serenity BDD Documentation](https://serenity-bdd.info/)
- [Karate DSL Documentation](https://karatelabs.github.io/karate/)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [Clean Code — Robert C. Martin](https://www.oreilly.com/library/view/clean-code/9780136083238/)
- [SOLID Principles](https://en.wikipedia.org/wiki/SOLID)
