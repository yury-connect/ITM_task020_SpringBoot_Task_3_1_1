## Важно:
#### Приложение необходимо запускать на порту **8088**, т.к. используются статические ресурсы _(css -файл)_.

---

# Задача: **PP 3.1.1.**

---

#### Тема: _10 Spring_
#### № **ITMPJ-2764**
#### Наименование: _Микросервисная система_
#### Дедлайн: _25/11/2024_
#### Код модуля: _PP3_ //  _PreProject3_

---
# Микросервисная система

## Задание:

Необходимо перенести ваше CRUD-приложение на Spring Boot.

## В этой теме Вы изучите
* Spring Boot
* Spring Security
* Rest
* Микросервисы

---

```
ITM_task020_SpringBoot_Task_3_1_1
└───main
    ├───java
    │   └───web
    │       │   SpringBootMvcApplication.java
    │       │
    │       ├───config
    │       │       AppConfig.java
    │       │       AppInit.java
    │       │       WebConfig.java
    │       │       WebConfig.java~
    │       │
    │       ├───controller
    │       │       HelloController.java
    │       │       UserController.java
    │       │
    │       ├───dao
    │       │       UserDao.java
    │       │       UserDaoImpDB.java
    │       │       UserDaoImplList.java
    │       │
    │       ├───model
    │       │       Car.java~
    │       │       User.java
    │       │
    │       ├───service
    │       │       UserService.java
    │       │       UserServiceImpl.java
    │       │
    │       └───util
    │               UserUtils.java
    │
    └───resources
        │   application.properties
        │   dbMySQL.properties
        │   dbPostgreSQL.properties
        │   sql_scrypts.sql
        │
        ├───static
        │   └───css
        │           styles.css
        │
        └───templates
            └───users_pages
                    all_users.html
                    create_user_page.html
                    delete_user_page.html
                    index.html
                    update_user_page.html
                    view_user_page.html

```

> 1. Установим **tree**
> 
> `choco install tree` - Скачать и установить _tree_ из _official site_ или через _Chocolatey_ // Windows
> 
> `sudo apt-get install tree` - то же, но для _Linux (Debian/Ubuntu)_
> 
> 2. Выведем дерево каталогов с именами файлов
> 
> `tree /F /A` - Это выведет полную структуру каталогов с именами файлов.
> 
> `tree /F /A > project-structure.txt` - Чтобы сохранить вывод в файл, vможно воспользоваться командой `>`