# 📝 ToDo Microservice

Microservicio de gestión de tareas (**ToDo**) implementado con **Spring Boot 3**, arquitectura **hexagonal**, y soporte para **Swagger** (documentación de API) y **Prometheus** (métricas).  

## 🚀 Características

- ✅ Arquitectura hexagonal (puertos y adaptadores).  
- ✅ API REST con Spring Boot.  
- ✅ Persistencia con JPA:  
  - **H2 en memoria** para tests y desarrollo.  
  - **MySQL** en producción.  
- ✅ Documentación de la API con Swagger / OpenAPI.  
- ✅ Observabilidad con Spring Actuator + Micrometer + Prometheus.  
- ✅ Tests unitarios e integración con `SpringBootTest`.  

---

## 📂 Estructura del proyecto

src/
├── main/
│ ├── java/com/example/demo/
│ │ ├── application/ # Casos de uso (servicios de dominio)
│ │ ├── domain/ # Entidades y lógica de negocio
| | ├── port # Repositorios genericos
│ │ ├── adapter/
│ │ │ ├── in/ # Controladores REST
│ │ │ └── out/ # Repositorios JPA
│ │ └── ControllerApplication.java # Clase principal
│ └── resources/
│   └── application.yml
│
└── test/
    ├── resources/
    |   └── application-test.yml
    └── java/com/example/demo/ # Tests unitarios e integración


---

## ⚙️ Requisitos previos

- **Java 17** o superior  
- **Maven Wrapper (`./mvnw`)** incluido  


---

## ▶️ Ejecución en local (perfil `dev` con H2)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

Aplicación disponible en:

    API: http://localhost:8080/tasks

Swagger UI: http://localhost:8080/swagger-ui.html

Actuator health: http://localhost:8080/actuator/health

Prometheus: http://localhost:8080/actuator/prometheus
🐬 Ejecución con MySQL (perfil prod)
1. Levantar MySQL con Docker

docker run --name todo-mysql -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=todo_db -p 3306:3306 -d mysql:8.0

2. Ejecutar la aplicación

./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

🧪 Ejecutar tests

Los tests usan H2 en memoria automáticamente.

./mvnw clean test

Puedes ver los reportes en:

target/surefire-reports/

🔍 Endpoints principales
Tareas

    POST /tasks → Crear tarea

    GET /tasks/{id} → Obtener tarea por ID

    GET /tasks → Listar todas las tareas

    PUT /tasks/{id}/complete → Marcar tarea como completada

    GET /tasks/pending → Listar tareas pendientes

Observabilidad

    GET /swagger-ui.html → Swagger UI

    GET /actuator/health → Estado de la aplicación

    GET /actuator/prometheus → Métricas para Prometheus

📊 Métricas disponibles

Gracias a Spring Actuator + Micrometer, se exponen métricas como:

    jvm_memory_used_bytes

    http_server_requests_seconds

    process_uptime_seconds

    application_ready_time_seconds

Estas métricas pueden ser recolectadas por Prometheus y visualizadas en Grafana.