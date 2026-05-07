# Computer Hardware Store API

RESTful backend-приложение для управления товарами магазина компьютерной техники и комплектующих.

Проект реализован в рамках тестового задания на позицию Java-разработчика.

## Описание проекта

Приложение предоставляет REST API для управления складом компьютерного магазина.

Поддерживаемые типы товаров:

- Настольные компьютеры
- Ноутбуки
- Мониторы
- Жесткие диски

Каждый товар содержит общие характеристики:

- идентификатор
- серийный номер
- производитель
- цена
- количество единиц товара на складе

Также каждый тип товара имеет специфичные характеристики:

| Тип товара | Дополнительное поле |
|----------|----------------------|
| Настольный компьютер | Форм-фактор: `DESKTOP`, `NETTOP`, `MONOBLOCK` |
| Ноутбук | Размер экрана: `INCH_13`, `INCH_14`, `INCH_15`, `INCH_17` |
| Монитор | Диагональ |
| Жесткий диск | Объем в GB |

## Основной функционал

Приложение поддерживает следующие операции:

- добавление товара
- редактирование товара
- получение всех товаров определенного типа
- получение товара по идентификатору

## Технологический стек

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- MapStruct
- Bean Validation
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Docker
- Docker Compose

## Архитектура проекта

Проект разделен на слои:

```text
controller   — REST-контроллеры
service      — бизнес-логика приложения
repository   — доступ к базе данных
entity       — JPA-сущности
dto          — DTO для request/response
mapper       — преобразование Entity <-> DTO
exception    — кастомные исключения и обработка ошибок
config       — конфигурационные классы
```

## API Documentation

Swagger UI доступен после запуска приложения по адресу:
```text
http://localhost:8080/swagger-ui/index.html
```
OpenAPI доступна по адресу:
```text
http://localhost:8080/v3/api-docs
```

## Коды ответов API

| Код | Описание |
|----|----------|
| `200 OK` | Успешное получение или обновление ресурса |
| `201 Created` | Ресурс успешно создан |
| `400 Bad Request` | Ошибка валидации входных данных |
| `404 Not Found` | Ресурс не найден |
| `409 Conflict` | Нарушение уникальности или конфликт данных |
| `500 Internal Server Error` | Непредвиденная ошибка сервера |

## Требования для запуска

Для локального запуска без Docker необходимо установить:

- Java 17
- Maven
- PostgreSQL

Для запуска через Docker необходимо установить:

- Docker
- Docker Compose

## Настройка базы данных

По умолчанию приложение использует PostgreSQL.

База данных может быть развернута локально с помощью создания БД с названием: TZLyapanovDB
Для локального запуска без Docker необходимо использовать application.properties, где использовать:
```text
spring.datasource.url=jdbc:postgresql://localhost:5432/TZLyapanovDB
```

При использовании Docker для запуска БД необходимо использовать application.properties, где использовать:
```text
spring.datasource.url=jdbc:postgresql://localhost:54322/TZLyapanovDB
```

## Инициализация базы данных

SQL-скрипт инициализации находится по пути:

```text
src/main/db_init/init_db.sql
```

При запуске PostgreSQL через Docker Compose этот скрипт автоматически выполняется и создает необходимые таблицы.

## Локальный запуск приложения

### 1. Клонировать репозиторий

```bash
git clone https://github.com/SashaLyapanov/ComputerHardwareStore.git
cd _your_path_to_project_
```

### 2. Запустить PostgreSQL через Docker Compose

Если нужно запустить только базу данных:

```bash
docker compose up -d db
```

Проверить статус контейнера:

```bash
docker compose ps
```

### 3. Собрать проект

```bash
mvn clean package
```

### 4. Запустить приложение

```bash
mvn spring-boot:run
```

Или через jar-файл:

```bash
java -jar target/TZ_Lyapanov_AA-0.0.1-SNAPSHOT.jar
```

Или с помощью Run в IntelliJ IDEA.

После запуска приложение будет доступно по адресу:

```text
http://localhost:8080
```

## Запуск через Docker Compose

Для запуска приложения вместе с базой данных выполните:

```bash
docker compose up --build -d
```

После запуска API будет доступно по адресу:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## Тестирование

В проекте реализованы unit-тесты сервисного слоя с использованием JUnit 5 и Mockito.

Запуск всех тестов:

```bash
mvn test
```

## Статус проекта

Реализовано:

- REST API для всех типов товаров
- CRUD-операции согласно техническому заданию
- DTO-слой
- MapStruct-мапперы
- JPA-сущности
- PostgreSQL-интеграция
- SQL-инициализация базы данных
- централизованная обработка ошибок
- валидация входящих данных
- Swagger/OpenAPI-документация
- unit-тесты сервисного слоя
- Dockerfile
- Docker Compose

## Автор

Разработчик: Ляпанов Александр