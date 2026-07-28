# 📋 Plan de Pruebas — AutomationExercise API Testing

> **Proyecto**: Automatización de APIs de AutomationExercise.com  
> **Fecha de creación**: Julio 2026  
> **Frameworks**: Serenity BDD + REST Assured | Karate DSL  
> **Versión**: 1.0

---

## 1. Objetivo del Plan

Validar el correcto funcionamiento de las **14 APIs** de [AutomationExercise.com](https://automationexercise.com/api_list)
mediante pruebas automatizadas implementadas con dos frameworks complementarios:

- **Serenity BDD 4.x** + REST Assured + Cucumber + JUnit 5
- **Karate DSL 1.x** + JUnit 5

El plan busca garantizar cobertura completa de endpoints, validación de respuestas esperadas
(positivas y negativas), y trazabilidad total entre APIs y casos de prueba.

---

## 2. Alcance

### 2.1 Incluido

| Elemento | Detalle |
|---|---|
| APIs cubiertas | Las 14 APIs documentadas en `/api_list` |
| Métodos HTTP | GET, POST, PUT, DELETE |
| Tipos de prueba | Positivas y negativas |
| Validaciones | Status code (en body), response body, response message, estructura JSON |
| Entornos | Producción (https://automationexercise.com) |
| Datos de prueba | Dinámicos (DataFaker) + Estáticos (configuración) |
| CI/CD | GitHub Actions + Jenkins |
| Reportes | Serenity Reports + Karate Reports |

### 2.2 Excluido

- UI Testing / Browser Testing
- Performance Testing / Load Testing
- Security Testing profundo (penetration testing, OWASP)
- APIs no documentadas en `/api_list`

---

## 3. APIs Bajo Prueba

| # | API | Método | URL | Parámetros | Response Code | Response Esperado |
|---|-----|--------|-----|------------|:---:|---|
| 1 | Get All Products List | GET | `/api/productsList` | ninguno | 200 | Lista JSON de productos |
| 2 | POST To All Products List | POST | `/api/productsList` | ninguno | 405 | "This request method is not supported." |
| 3 | Get All Brands List | GET | `/api/brandsList` | ninguno | 200 | Lista JSON de marcas |
| 4 | PUT To All Brands List | PUT | `/api/brandsList` | ninguno | 405 | "This request method is not supported." |
| 5 | POST To Search Product | POST | `/api/searchProduct` | `search_product` | 200 | Lista de productos filtrados |
| 6 | POST To Search Product (sin param) | POST | `/api/searchProduct` | ninguno | 400 | "Bad request, search_product parameter is missing in POST request." |
| 7 | POST Verify Login (válido) | POST | `/api/verifyLogin` | `email`, `password` | 200 | "User exists!" |
| 8 | POST Verify Login (sin email) | POST | `/api/verifyLogin` | `password` | 400 | "Bad request, email or password parameter is missing in POST request." |
| 9 | DELETE To Verify Login | DELETE | `/api/verifyLogin` | ninguno | 405 | "This request method is not supported." |
| 10 | POST Verify Login (inválido) | POST | `/api/verifyLogin` | `email`, `password` (inválidos) | 404 | "User not found!" |
| 11 | POST Create/Register User | POST | `/api/createAccount` | 17 campos de usuario | 201 | "User created!" |
| 12 | DELETE User Account | DELETE | `/api/deleteAccount` | `email`, `password` | 200 | "Account deleted!" |
| 13 | PUT Update User Account | PUT | `/api/updateAccount` | 17 campos de usuario | 200 | "User updated!" |
| 14 | GET User Detail by Email | GET | `/api/getUserDetailByEmail` | `email` | 200 | Detalle de usuario (JSON) |

> **Nota técnica**: El servidor siempre responde con HTTP 200. El `responseCode` real se encuentra
> dentro del body JSON. Los parámetros se envían como `application/x-www-form-urlencoded`.

---

## 4. Casos de Prueba por API

### 4.1 Products API

| ID | Descripción | Precondiciones | Datos de Entrada | Resultado Esperado | Prioridad | Tipo |
|---|---|---|---|---|:---:|:---:|
| TC-API-001 | Obtener lista completa de productos | Ninguna | GET `/api/productsList` | responseCode=200, products es un array no vacío con id, name, price, brand, category | Alta | Positivo |
| TC-API-002 | POST a lista de productos retorna 405 | Ninguna | POST `/api/productsList` | responseCode=405, message="This request method is not supported." | Media | Negativo |

### 4.2 Brands API

| ID | Descripción | Precondiciones | Datos de Entrada | Resultado Esperado | Prioridad | Tipo |
|---|---|---|---|---|:---:|:---:|
| TC-API-003 | Obtener lista completa de marcas | Ninguna | GET `/api/brandsList` | responseCode=200, brands es un array no vacío con id y brand | Alta | Positivo |
| TC-API-004 | PUT a lista de marcas retorna 405 | Ninguna | PUT `/api/brandsList` | responseCode=405, message="This request method is not supported." | Media | Negativo |

### 4.3 Search API

| ID | Descripción | Precondiciones | Datos de Entrada | Resultado Esperado | Prioridad | Tipo |
|---|---|---|---|---|:---:|:---:|
| TC-API-005 | Buscar productos con parámetro válido | Ninguna | POST `/api/searchProduct` con search_product="top" | responseCode=200, products es un array con resultados relevantes | Alta | Positivo |
| TC-API-006 | Buscar productos sin parámetro | Ninguna | POST `/api/searchProduct` sin parámetros | responseCode=400, message contiene "search_product parameter is missing" | Alta | Negativo |

### 4.4 Auth API

| ID | Descripción | Precondiciones | Datos de Entrada | Resultado Esperado | Prioridad | Tipo |
|---|---|---|---|---|:---:|:---:|
| TC-API-007 | Verificar login con credenciales válidas | Usuario existente | POST `/api/verifyLogin` con email y password válidos | responseCode=200, message="User exists!" | Alta | Positivo |
| TC-API-008 | Verificar login sin parámetro email | Ninguna | POST `/api/verifyLogin` solo con password | responseCode=400, message contiene "email or password parameter is missing" | Alta | Negativo |
| TC-API-009 | DELETE a verify login retorna 405 | Ninguna | DELETE `/api/verifyLogin` | responseCode=405, message="This request method is not supported." | Media | Negativo |
| TC-API-010 | Verificar login con credenciales inválidas | Ninguna | POST `/api/verifyLogin` con datos falsos | responseCode=404, message="User not found!" | Alta | Negativo |

### 4.5 User Management API

| ID | Descripción | Precondiciones | Datos de Entrada | Resultado Esperado | Prioridad | Tipo |
|---|---|---|---|---|:---:|:---:|
| TC-API-011 | Crear cuenta de usuario | Email no registrado | POST `/api/createAccount` con 17 campos (DataFaker) | responseCode=201, message="User created!" | Alta | Positivo |
| TC-API-012 | Eliminar cuenta de usuario | Usuario previamente creado | DELETE `/api/deleteAccount` con email y password | responseCode=200, message="Account deleted!" | Alta | Positivo |
| TC-API-013 | Actualizar cuenta de usuario | Usuario previamente creado | PUT `/api/updateAccount` con 17 campos actualizados | responseCode=200, message="User updated!" | Alta | Positivo |
| TC-API-014 | Obtener detalle de usuario por email | Usuario previamente creado | GET `/api/getUserDetailByEmail?email=...` | responseCode=200, user contiene id, name, email, campos de perfil | Alta | Positivo |

---

## 5. Estrategia de Datos de Prueba

| Tipo de Dato | Estrategia | Herramienta |
|---|---|---|
| Datos de usuario (creación) | Generación aleatoria en cada ejecución | DataFaker 2.x |
| Credenciales de login (válidas) | Datos estáticos en archivos de configuración | serenity.conf / karate-config.js |
| Credenciales de login (inválidas) | Datos hardcodeados en tests (valores falsos conocidos) | Constantes de test |
| Términos de búsqueda | Valores estáticos conocidos: "top", "tshirt", "jean" | Feature files |

### Patrón de Limpieza de Datos

Para los tests de User Management (APIs 11-14), se sigue el patrón:

1. **Setup**: Crear usuario con datos aleatorios (API 11)
2. **Test**: Ejecutar la operación bajo prueba (API 13, 14)
3. **Teardown**: Eliminar usuario creado (API 12)

Esto garantiza la independencia de cada test y evita contaminación de datos.

---

## 6. Matriz de Trazabilidad

| API | Test Case IDs | Feature File (Serenity) | Feature File (Karate) |
|---|---|---|---|
| API 1 — Get Products | TC-API-001 | products.feature | products.feature |
| API 2 — POST Products | TC-API-002 | products.feature | products.feature |
| API 3 — Get Brands | TC-API-003 | brands.feature | brands.feature |
| API 4 — PUT Brands | TC-API-004 | brands.feature | brands.feature |
| API 5 — Search Product | TC-API-005 | search.feature | search.feature |
| API 6 — Search sin param | TC-API-006 | search.feature | search.feature |
| API 7 — Login válido | TC-API-007 | auth.feature | auth.feature |
| API 8 — Login sin email | TC-API-008 | auth.feature | auth.feature |
| API 9 — DELETE Login | TC-API-009 | auth.feature | auth.feature |
| API 10 — Login inválido | TC-API-010 | auth.feature | auth.feature |
| API 11 — Create User | TC-API-011 | user_management.feature | users.feature |
| API 12 — Delete User | TC-API-012 | user_management.feature | users.feature |
| API 13 — Update User | TC-API-013 | user_management.feature | users.feature |
| API 14 — Get User by Email | TC-API-014 | user_management.feature | users.feature |

---

## 7. Criterios de Aceptación

- [ ] 100% de las 14 APIs cubiertas con al menos un test positivo o negativo
- [ ] Todas las pruebas pasan en verde (ambos módulos)
- [ ] Reportes de Serenity generados correctamente en `target/site/serenity/`
- [ ] Reportes de Karate generados correctamente en `target/karate-reports/`
- [ ] CI/CD configurado y funcional (GitHub Actions + Jenkins)
- [ ] Código sigue principios SOLID y Clean Code
- [ ] Sin datos hardcodeados (URLs, credenciales en configuración)
- [ ] Tests independientes entre sí

---

## 8. Herramientas y Frameworks

| Componente | Tecnología | Versión |
|---|---|---|
| Lenguaje | Java | 17+ |
| Build Tool | Maven | Multi-módulo |
| Framework 1 | Serenity BDD + REST Assured + Cucumber | 4.x |
| Framework 2 | Karate DSL | 1.x |
| Test Runner | JUnit 5 Platform Suite | 1.11.x |
| Datos de Prueba | DataFaker | 2.x |
| Logging | SLF4J + Logback | — |
| CI/CD | GitHub Actions + Jenkins | — |
| Reportes | Serenity Reports + Karate Reports | — |
| Control de Versiones | Git + GitHub | — |

---

## 9. Riesgos y Mitigaciones

| Riesgo | Probabilidad | Impacto | Mitigación |
|---|---|---|---|
| API de AutomationExercise no disponible | Baja | Alto | Retry automático, timeouts configurables |
| Cambio en estructura de respuestas | Baja | Medio | Modelos desacoplados, fácil actualización |
| Rate limiting del servidor | Media | Medio | Esperas entre requests, ejecución secuencial |
| Datos de usuario residuales | Media | Bajo | Patrón create-test-delete, emails únicos con DataFaker |
