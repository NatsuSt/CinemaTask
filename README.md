# 🎬 Cinema REST API
Роботу виконав Стешенко Сергій (Serhii Steshenko)

> **Live demo:** [https://cinematask.onrender.com](https://cinematask.onrender.com)

---

## 🔐 Аутентифікація

### `POST /api/auth/register`
Реєстрація нового користувача.

- **Body**:
  ```json
  {
    "username": "john_doe",
    "password": "secure_password",
    "authority": "READ_PRIVILEGE",
    "role": "ROLE_USER"
  }
  ```
- **Response**: `200 OK`  
  `"User registered successfully"`

---

### `POST /api/auth/login`
Логін користувача, повертає JWT токен.

- **Body**:
  ```json
  {
    "username": "john_doe",
    "password": "secure_password"
  }
  ```
- **Response**: `200 OK`  
  `"eyJhbGciOiJIUzI1NiIsInR..."` (JWT токен)

---

## 🎥 Фільми (`/movies`)

### `GET /movies`
Отримати список всіх фільмів.

- **Response**:
  ```json
  [
    {
      "id": 1,
      "title": "Inception",
      "description": "A mind-bending thriller"
    }
  ]
  ```

---

### `GET /movies/{id}`
Отримати фільм за ID.

- **Response**:
  ```json
  {
    "id": 1,
    "title": "Inception",
    "description": "A mind-bending thriller"
  }
  ```

---

### `POST /movies`
Створити новий фільм.

- **Body**:
  ```json
  {
    "title": "New Movie",
    "genre": "Action",
    "duration": 130,
    "ageLimit": 16
  }
  ```

- **Response**: `201 Created`

---

## 🎟️ Сеанси (`/screenings`)

### `GET /screenings`
Отримати всі сеанси.

### `GET /screenings/{id}`
Отримати сеанс за ID.

### `POST /screenings`
Створити новий сеанс.

- **Body**:
  ```json
  {
    "movieId": {
    "id": 1
  },
    "dateTime": "2025-06-10T20:00:00",
    "hall": "A1"
  }
  ```

---

## 🧾 Квитки (`/tickets`)

### `POST /tickets`
Створити новий квиток.

- **Body**:
  ```json
  {
    "customerName": "Alice",
    "seatNumber": 15,
    "screeningId": {
    "id": 5
  }
  }
  ```

---

### `GET /tickets/by-customer?customerName=Alice`
Отримати всі квитки для конкретного клієнта.

---

## 🖥️ Web-інтерфейс (Thymeleaf)

- `GET /register` — форма реєстрації
- `POST /register` — обробка реєстрації
- `GET /login` — форма логіну
- `GET /home` — захищена домашня сторінка (вимагає автентифікації)

---

## 📂 Структура проєкту

- `controller/` — REST контролери
- `service/` — бізнес-логіка
- `model/` — сутності
- `repository/` — доступ до бази даних
- `security/` — JWT та конфігурація безпеки

---
