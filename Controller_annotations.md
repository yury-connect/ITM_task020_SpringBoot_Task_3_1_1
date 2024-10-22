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

##### Клиентская часть (_Thymeleaf_):
```html
<form th:action="@{/search}" method="get">
    <label for="query">Search Query:</label>
    <input type="text" id="query" name="query">
    <button type="submit">Search</button>
</form>
```

##### Контроллер:
```java
@GetMapping("/search")
public String search(@RequestParam("query") String query, Model model) {
    model.addAttribute("result", "Result for query: " + query);
    return "searchResult";
}
```

##### Пример запроса: `/search?query=spring`

---

### @PathVariable

Используется для извлечения переменных из `URI` _(маршрута)_ // через **URL-путь**.


##### Клиентская часть (Thymeleaf):
```html
<a th:href="@{/user/1}">View User</a>
```

##### Контроллер:
```java
@GetMapping("/user/{id}")
public String getUserById(@PathVariable("id") int userId, Model model) {
    model.addAttribute("user", "User ID: " + userId);
    return "userDetails";
}
```

##### Пример запроса: `/user/1`

---

### @RequestBody

Используется для передачи `JSON` или других данных в теле запроса. Для отправки `JSON` с помощью `HTML`-форм потребуется `JavaScript`.

##### Клиентская часть (JavaScript для отправки JSON):
```html
<form id="userForm">
    <input type="text" id="name" name="name" placeholder="Name">
    <input type="email" id="email" name="email" placeholder="Email">
    <button type="button" onclick="submitForm()">Submit</button>
</form>

<script>
function submitForm() {
    const user = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    fetch('/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => response.json()).then(data => console.log(data));
}
</script>
```

##### Контроллер:
```java
@PostMapping("/users")
public ResponseEntity<String> createUser(@RequestBody User user) {
    // Сохранить пользователя
    return ResponseEntity.ok("User created: " + user.getName());
}
```

---

### @ModelAttribute

Используется для привязки полей `HTML`-форм к объектам. Обычно применяется для работы с формами.


##### Клиентская часть (Thymeleaf):
```html
<form th:action="@{/user/save}" th:object="${user}" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" th:field="*{name}">
    
    <label for="email">Email:</label>
    <input type="email" id="email" th:field="*{email}">
    
    <button type="submit">Save</button>
</form>
```

##### Контроллер:
```java
@PostMapping("/user/save")
public String saveUser(@ModelAttribute User user, Model model) {
    model.addAttribute("message", "User saved: " + user.getName());
    return "userSaved";
}
```

---

### @RequestHeader

Используется для передачи данных через заголовки HTTP-запроса.

##### Клиентская часть (JavaScript для отправки заголовка):
```html
<button onclick="sendRequest()">Send Request</button>

<script>
function sendRequest() {
    fetch('/header', {
        method: 'GET',
        headers: {
            'User-Agent': 'MyCustomUserAgent'
        }
    }).then(response => response.text()).then(data => console.log(data));
}
</script>
```

##### Контроллер:
```java
@GetMapping("/header")
public String getHeader(@RequestHeader("User-Agent") String userAgent, Model model) {
    model.addAttribute("header", "User-Agent: " + userAgent);
    return "headerView";
}
```

---

### @CookieValue

Используется для получения данных **из куки**.


##### Клиентская часть (JavaScript для установки куки):
```html
<button onclick="setCookie()">Set Cookie</button>

<script>
function setCookie() {
    document.cookie = "sessionId=abc123; path=/";
    window.location.href = "/cookie";
}
</script>
```

##### Контроллер:
```java
@GetMapping("/cookie")
public String getCookie(@CookieValue(value = "sessionId", defaultValue = "none") String sessionId, Model model) {
    model.addAttribute("cookie", "Session ID: " + sessionId);
    return "cookieView";
}
```

---

### @RequestPart

Используется для обработки части `multipart`-запроса, например, для загрузки файлов.


##### Клиентская часть (Thymeleaf):
```html
<form th:action="@{/upload}" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <button type="submit">Upload</button>
</form>
```

##### Контроллер:
```java
@PostMapping("/upload")
public String handleFileUpload(@RequestPart("file") MultipartFile file, Model model) {
    model.addAttribute("message", "File uploaded: " + file.getOriginalFilename());
    return "uploadResult";
}
```

---

### @SessionAttribute

Используется для извлечения данных **из сессии** пользователя.

##### Клиентская часть (Thymeleaf):
```html
<a th:href="@{/profile}">View Profile</a>
```

##### Контроллер:
```java
@GetMapping("/profile")
public String getProfile(@SessionAttribute("user") User user, Model model) {
    model.addAttribute("user", user);
    return "profile";
}
```
* Для корректной работы сессии, в контроллере должен быть ранее сохранён объект сессии **user**.

---
### @MatrixVariable
Используется для передачи данных через матричные параметры в URL.

##### Клиентская часть (Thymeleaf):
```html
<a th:href="@{/cars/1;color=red;brand=bmw}">View Car</a>
```

##### Контроллер:
```java
@GetMapping("/cars/{id}")
public String getCarDetails(@PathVariable String id, @MatrixVariable Map<String, String> filters, Model model) {
    model.addAttribute("filters", filters);
    return "carDetails";
}
```
##### Пример URL запроса: `/cars/1;color=red;brand=bmw`

---

