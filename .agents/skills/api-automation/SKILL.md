---
name: api-automation-automationexercise
description: >
  Skill for automating API tests against AutomationExercise.com using Serenity BDD + REST Assured
  and Karate DSL in a Maven multi-module project. Provides architectural context, API domain knowledge,
  and implementation patterns specific to this test suite.
---

# API Automation — AutomationExercise.com

## Contexto del Proyecto

Este proyecto automatiza las 14 APIs del sitio [AutomationExercise.com](https://automationexercise.com/api_list)
utilizando dos frameworks complementarios en un proyecto Maven multi-módulo:

- **Módulo Serenity**: Serenity BDD 4.x + REST Assured + Cucumber + JUnit 5 + Screenplay Pattern
- **Módulo Karate**: Karate DSL 1.x + JUnit 5

## Comportamiento Específico del API

> [!IMPORTANT]
> Las APIs de AutomationExercise tienen comportamientos particulares que deben tenerse en cuenta:

1. **HTTP Status siempre 200**: El servidor responde siempre con HTTP 200. El código de respuesta real
   se encuentra dentro del body JSON en el campo `responseCode`.

2. **Form-Encoded, no JSON**: Los endpoints que reciben parámetros usan
   `application/x-www-form-urlencoded`, no `application/json`.
   - En Serenity/REST Assured: usar `.formParam("key", "value")`
   - En Karate: usar `And form field key = 'value'`

3. **Response como HTML con JSON embebido**: Algunas respuestas pueden venir como HTML que contiene
   JSON. Se debe parsear el body correctamente.

## APIs Bajo Prueba

| # | API | Método | Endpoint | Parámetros |
|---|-----|--------|----------|------------|
| 1 | Get All Products List | GET | `/api/productsList` | ninguno |
| 2 | POST To All Products List | POST | `/api/productsList` | ninguno |
| 3 | Get All Brands List | GET | `/api/brandsList` | ninguno |
| 4 | PUT To All Brands List | PUT | `/api/brandsList` | ninguno |
| 5 | POST To Search Product | POST | `/api/searchProduct` | `search_product` |
| 6 | POST To Search Product sin param | POST | `/api/searchProduct` | ninguno |
| 7 | POST Verify Login (válido) | POST | `/api/verifyLogin` | `email`, `password` |
| 8 | POST Verify Login sin email | POST | `/api/verifyLogin` | `password` |
| 9 | DELETE To Verify Login | DELETE | `/api/verifyLogin` | ninguno |
| 10 | POST Verify Login (inválido) | POST | `/api/verifyLogin` | `email`, `password` (inválidos) |
| 11 | POST Create/Register User | POST | `/api/createAccount` | 17 campos de usuario |
| 12 | DELETE User Account | DELETE | `/api/deleteAccount` | `email`, `password` |
| 13 | PUT Update User Account | PUT | `/api/updateAccount` | 17 campos de usuario |
| 14 | GET User Detail by Email | GET | `/api/getUserDetailByEmail` | `email` |

## Agrupación por Dominio Funcional

| Feature File | APIs | Dominio |
|---|---|---|
| `products.feature` | 1, 2 | Catálogo de productos |
| `brands.feature` | 3, 4 | Catálogo de marcas |
| `search.feature` | 5, 6 | Búsqueda de productos |
| `auth.feature` | 7, 8, 9, 10 | Autenticación y login |
| `user_management.feature` / `users.feature` | 11, 12, 13, 14 | Gestión de cuentas de usuario |

## Patrones de Implementación

### API Clients (Serenity Module)

- `BaseApiClient`: clase base con configuración de request, logging, base URL desde `serenity.conf`.
- Un client por dominio: `ProductClient`, `BrandClient`, `SearchClient`, `AuthClient`, `UserClient`.
- Soportar verbos: GET, POST, PUT, DELETE.
- Content-Type: `application/x-www-form-urlencoded` para parámetros.

### Modelos (Serenity Module)

- Usar Java Records (inmutables) donde sea posible.
- Modelos de request: `SearchRequest`, `LoginRequest`, `CreateAccountRequest`, `UpdateAccountRequest`, `DeleteAccountRequest`.
- Modelos de response: `ProductResponse`, `BrandResponse`, `ApiResponse` (genérico con `responseCode` y `message`).
- `CreateAccountRequest` y `UpdateAccountRequest` incluyen los 17 campos del API.

### Datos de Prueba

- Usar DataFaker 2.x para generación de datos aleatorios de usuarios.
- Credenciales de login estáticas en archivos de configuración.
- Patrón de limpieza: crear usuario → probar → eliminar usuario.

### Feature Files

- Tags obligatorios: `@api`, `@positive`/`@negative`, `@smoke` para pruebas críticas.
- Nombres de escenarios descriptivos.
- En Karate: requests y validaciones inline en el feature file.
- En Cucumber/Serenity: steps genéricos reutilizables.

## Configuración

### Serenity (`serenity.conf`)
```
webdriver.autodownload = false
serenity.project.name = "AutomationExercise API Tests — Serenity REST"
restapi.baseurl = "https://automationexercise.com"
```

### Karate (`karate-config.js`)
```javascript
function fn() {
  var config = {
    baseUrl: 'https://automationexercise.com'
  };
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);
  return config;
}
```

## CI/CD

- **GitHub Actions**: workflow en `.github/workflows/api-tests-ci.yml` con jobs separados para cada módulo.
- **Jenkins**: `Jenkinsfile` con stages para Serenity y Karate, publicación de reportes HTML.
- JDK 17 + Maven con cache.
- Upload de reportes como artifacts.

## Flujo de Trabajo por Fases

1. **Plan de Pruebas** → `docs/PLAN_DE_PRUEBAS.md` documentando todos los casos de prueba.
2. **Estructura del Proyecto** → Scaffold Maven multi-módulo.
3. **Módulo Serenity** → Configuración, modelos, clients, features, step definitions, runners.
4. **Módulo Karate** → Configuración, features, runners.
5. **CI/CD** → GitHub Actions + Jenkinsfile.
6. **Documentación** → README profesional.
7. **Verificación** → Ejecución completa de ambos módulos.

## Verificación de Calidad

Antes de considerar cualquier fase completa:

- [ ] El código compila sin errores (`mvn clean compile`).
- [ ] Los tests pasan en verde (`mvn clean verify` / `mvn clean test`).
- [ ] No hay warnings de compilación ignorados.
- [ ] Los reportes se generan correctamente.
- [ ] Se respetan las convenciones de nomenclatura y estructura.
- [ ] El commit message sigue Conventional Commits.
