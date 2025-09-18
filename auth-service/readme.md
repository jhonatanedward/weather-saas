### Authentication Service API

This document provides a comprehensive overview of the authentication endpoints for user registration and login.

#### Base URL

`http://localhost:8080/auth`

#### Endpoints

-----

##### 1\. Register a New User

Registers a new user account with a unique username and password.

* **Endpoint:** `/signup`

* **Method:** `POST`

* **Request Body:**

    * `username` (string): The user's desired username.
    * `password` (string): The user's password.

* **Example Request:**

  ```json
  {
    "username": "johndoe",
    "password": "strongpassword123"
  }
  ```

* **Success Response (HTTP 200 OK):**

    * **Description:** User registered successfully.

    * **Body:**

        * `userId` (integer): The unique ID of the newly created user.
        * `username` (string): The username of the new user.
        * `token` (string): A JSON Web Token (JWT) for authentication.

    * **Example Response:**

      ```json
      {
        "userId": 1,
        "username": "johndoe",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9..."
      }
      ```

* **Error Responses:**

    * **HTTP 400 Bad Request:** Occurs if the username is already taken or the request is invalid.

-----

##### 2\. Authenticate and Log In

Authenticates an existing user and returns an authentication token for subsequent requests.

* **Endpoint:** `/signin`

* **Method:** `POST`

* **Request Body:**

    * `username` (string): The user's username.
    * `password` (string): The user's password.

* **Example Request:**

  ```json
  {
    "username": "johndoe",
    "password": "strongpassword123"
  }
  ```

* **Success Response (HTTP 200 OK):**

    * **Description:** User authenticated successfully.

    * **Body:**

        * `userId` (integer): The user's ID.
        * `username` (string): The user's username.
        * `token` (string): A JSON Web Token (JWT) for authentication.

    * **Example Response:**

      ```json
      {
        "userId": 1,
        "username": "johndoe",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjF9..."
      }
      ```

* **Error Responses:**

    * **HTTP 401 Unauthorized:** Occurs for invalid credentials (incorrect username or password).

Com o conteúdo fornecido, aqui está a documentação simples e direta para o seu serviço de autenticação, usando Markdown.

-----

### Run Application Locally 🏃

This guide explains how to build your Spring Boot application and run it as a Docker container, making it easy to test and deploy locally.

-----

### 1\. Build the Application

First, build your application into a runnable JAR file using Gradle's `bootJar` task. This command compiles your code and packages it with all its dependencies.

```bash
./gradlew bootJar
```

-----

### 2\. Create the Docker Image

Next, create a Docker image from your application's JAR. An image is a lightweight, standalone, executable package that contains everything needed to run your software.

```bash
docker build -t auth-service:latest .
```

The `-t` flag tags the image with a name (`auth-service`) and a version (`latest`), while the dot (`.`) tells Docker to look for the `Dockerfile` in the current directory.

-----

### 3\. Run the Container

Finally, run the Docker container. This will start your application and map the container's internal port to your local machine, allowing you to access the service.

```bash
docker run -p 8080:8080 --name auth-service auth-service:latest
```

* **`-p 8080:8080`**: Maps port `8080` from the container to port `8080` on your host.
* **`--name auth-service`**: Assigns a readable name to the container for easy management.