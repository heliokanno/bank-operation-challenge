## Example project with:
- Spring Boot 3
- Java 17
- Gradle 8.x
- PostgreSQL
- Pessimistic Lock
- Flyway
- Rest API
- Docker


## Features
* Create Account
* Send Transfer

## Setup

### Swagger-ui
**http://localhost:8080/swagger-ui/index.html**

### Local
Run:
```
./gradlew build
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### Docker
Build the docker image
```
./gradlew build
docker build -t hmk/challenge-bank-api .
```

Run the application
```
cd docker
docker-compose -f docker-compose.yml -p challenge-stack up
```
