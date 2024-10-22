## Аннотации для передачи параметров в контроллер. 
### На стороне клиента - Thumeleaf. На стороне сервера - Spring.


* @RequestParam используется для query-параметров (например, ?param=value).
* @PathVariable — для параметров пути (например, /user/{id}).
* @RequestBody — для получения тела запроса в виде объекта.
* @ModelAttribute — для связывания данных формы с объектами.
* @RequestHeader и @CookieValue — для работы с заголовками и куками.

---

### @RequestParam

Используется для извлечения **параметров из запроса**. Чаще всего применяется для обработки данных из строки запроса (например, query parameters).

##### Клиентская часть (Thymeleaf):
```
<form th:action="@{/search}" method="get">
    <label for="query">Search Query:</label>
    <input type="text" id="query" name="query">
    <button type="submit">Search</button>
</form>
```

##### Контроллер:
```
@GetMapping("/search")
public String search(@RequestParam("query") String query, Model model) {
    model.addAttribute("result", "Result for query: " + query);
    return "searchResult";
}
```

##### Пример запроса: `/search?query=spring`

* В этом примере параметр `query` будет извлечен из строки запроса, например: `/search?query=spring`.

---

### @PathVariable

Используется для извлечения переменных из `URI` _(маршрута)_.

```
@GetMapping("/user/{id}")
public String getUserById(@PathVariable("id") int userId) {
// Используем userId для поиска пользователя
return "user";
}
```

* Переменная id будет извлечена из пути `/user/1`, где `1` — это значение переменной `id`.

---

### @RequestBody

Используется для получения тела HTTP-запроса (`JSON`, `XML` и т.д.) и преобразования его **в объект**.

```
@PostMapping("/users")
public String createUser(@RequestBody User user) {
    // Создаем пользователя на основе данных из JSON
    return "userCreated";
}
```

* Spring автоматически преобразует JSON в объект User.

---

### @ModelAttribute

Используется для привязки полей HTML-форм к объектам. Обычно применяется для работы с формами.

```
@PostMapping("/user/save")
public String saveUser(@ModelAttribute User user) {
    // Данные формы будут маппированы на объект user
    return "userSaved";
}

```
* Поля формы, такие как `name`, `email`, будут автоматически привязаны к полям объекта `User`.

---

### @RequestHeader

Используется для извлечения данных из HTTP-заголовков запроса.

```
@GetMapping("/header")
public String getHeader(@RequestHeader("User-Agent") String userAgent) {
// Используем информацию из заголовка User-Agent
return "header";
}
```

* Аннотация извлекает значение заголовка `User-Agent`.

---

### @CookieValue

Используется для получения данных из куки.

```
@GetMapping("/cookie")
public String getCookie(@CookieValue(value = "sessionId", defaultValue = "defaultSession") String sessionId) {
    // Работа с кукой sessionId
    return "cookie";
}
```

* Извлекает значение куки `sessionId`.

---

### @RequestPart

Используется для обработки части `multipart`-запроса, например, для загрузки файлов.

```
@PostMapping("/upload")
public String handleFileUpload(@RequestPart("file") MultipartFile file) {
    // Работа с файлом
    return "uploadSuccess";
}
```

* Используется для получения файла из multipart-запроса.

---

### @SessionAttribute

Используется для извлечения данных из сессии пользователя.

```
@GetMapping("/profile")
public String getProfile(@SessionAttribute("user") User user) {
    // Извлекаем объект пользователя из сессии
    return "profile";
}
```

* Извлекает значение атрибута `user` из сессии.

---

### @MatrixVariable

Используется для извлечения переменных матричных параметров, которые могут быть использованы в `URI`.

```
@GetMapping("/cars/{id}")
public String getCarDetails(@PathVariable String id, @MatrixVariable Map<String, String> filters) {
    // Пример URI: /cars/1;color=red;brand=bmw
    return "carDetails";
}

```

---

### 

---
