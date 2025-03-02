Для поддержки TomCat русских символов рекомендуется переписать секцию (дополнить) `server.xml` следующим образом:

Запуск Приложения:   mvn spring-boot:run

---

```xml
    URIEncoding="UTF-8"
    useBodyEncodingForURI="true"
```
> * `URIEncoding="UTF-8"` — этот параметр гарантирует, что запросы, содержащие символы, закодированные в UTF-8 (например, русские буквы в URL), будут корректно декодированы.
> * `useBodyEncodingForURI="true"` — этот параметр разрешает использовать ту же кодировку, которая применяется для тела запроса (например, данные формы), и для URI. Это полезно для корректной обработки данных в UTF-8.

> HTTP Коннектор: Коннектор настроен для обработки HTTP-запросов на порту 8080, что стандартно для Tomcat:

```xml
<Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443"
            maxParameterCount="1000"
            URIEncoding="UTF-8"
            useBodyEncodingForURI="true"/>
```
> Этот блок отвечает за обработку входящих HTTP-запросов. Параметры URIEncoding и useBodyEncodingForURI обеспечат правильную работу с кодировкой UTF-8.

---

### По хорошему нужно руководствоваться таким подходом к типам запросов:

* `GET` — **получение** _entity_ или списка **без изменения состояния на сервере**. Подходит для страниц отображения данных.;
* `POST` — **создание новых** _entity_ или выполнения действий, которые **изменяют** состояние на сервере _(например, создание или удаление записи)_.;
* `PUT` — **изменение** _entity_ применяется для полной замены ресурса (например, для обновления сущности).;
* `PATCH` — **изменение** _entity_ (ЧАСТИЧНОЕ ИЗМЕНЕНИЕ кусочка _entity_);
* `DELETE` — **удаление** _entity_ применяется для полного удаления ресурса.;
* `HEAD` — **получение** заголовков _entity_ возвращает только заголовки HTTP-ответа, без тела.;
* `OPTIONS` — возвращает информацию о **поддерживаемых методах HTTP** для конкретного ресурса.;
* `TRACE` — устаревший метод, который не используется _(есть уязвимости)_.

---

---

> `SHOW DATABASES;` - показать все базы;
> 
> `SHOW SCHEMAS;` синоним;
> 
> `USE 'name_db';` выбрать / ипользовать БД 'name_db'
> 
> `SHOW TABLES;` - показать список всех имеющихся таблиц;
> 
> 



---

---

Запись (**Record**) - введен в Java 16 для упрощения создания неизменяемых объектов
```java
public record Point(int x, int y) {}
```   
Особенности record
 * Неизменяемость: Поля являются (final), и после создания объекта поля не могут быть изменены.

 * Автоматическая генерация методов: При объявлении record автоматически создаются:

 * Конструктор с параметрами для всех полей;

 * Геттеры (они называются так же, как и поля);

 * Методы equals(), hashCode() и toString(), которые реализованы на основе полей записи.

---

---

---

Ниже - несколько скринов правильной настройки **TomCat**

---

Конфигурация запуска / вкладка "**Server**"

![Конфигурация запуска / вкладка "Server"](/imgs/rem_/StartupConfiguration_Server.png)

---

Конфигурация запуска / вкладка "**Deployment**"

![Конфигурация запуска / вкладка "Deployment"](/imgs/rem_/StartupConfiguration_Deployment.png)

---

Страница, открывающаяся при этом **в браузере**

![Страница, открывающаяся при этом в браузере"](/imgs/rem_/Page_in_browser.png)

---


[**Spring** - базовые понятия в вопросах и ответах](https://habr.com/ru/articles/470305/)

[Spring для ленивых. // _javarush.com_](https://javarush.com/groups/posts/477-spring-dlja-lenivihkh-osnovih-bazovihe-koncepcii-i-primerih-s-kodom-chastjh-2)

[maven 'овский репозиторий](https://mvnrepository.com/)

