# Spring Projections

Проект демонстрирует использование Spring Data JPA проекций, маппера MapStruct и управление миграциями через Liquibase.

## Технологии

- Java 21
- Spring Boot 4.0.6
- Spring Data JPA
- Liquibase
- PostgreSQL 17
- MapStruct 1.6.3
- Docker / Docker Compose
- Testcontainers (интеграционные тесты)

## Быстрый старт

### Требования

- JDK 21
- Maven 3.9+
- Docker / Docker Compose

### Сборка проекта
### Собрать проект с тестами
```bash
mvn clean package
````
### Собрать проект без тестов
```bash
mvn clean package -DskipTests
```

### Собрать Docker образ
```bash
docker build -t myapp . 
```

### Запустить PostgreSQL + приложение
```bash
docker-compose --env-file .env.local up -d
```
### Остановить контейнеры
```bash
docker-compose down
```
### Полная очистка (удалить данные БД)
```bash
docker-compose down -v
```
