# Online Store

A full-stack online store built with **Spring Boot, Java, MySQL, React, and Vite**. The application provides a REST API for store operations and a React interface for customers to browse products, manage a cart, register or log in, and place orders.

## Features

### Store and backend
- Product and category management
- Paginated product listing
- Shopping cart and cart-item quantity management
- Order placement and order history
- Product reviews
- RESTful API built with Spring Boot
- Layered backend structure using controllers, services, and repositories
- Spring Data JPA and Hibernate for database persistence
- DTOs and MapStruct for request/response mapping
- Input/error handling and centralized exception handling
- Caching for category data
- Transactional order processing and stock checks

### Authentication and authorization
- User registration and login
- Spring Security with HTTP Basic authentication
- BCrypt password hashing
- Role-based authorization for `ADMIN` and `CUSTOMER`
- Public access to product and category browsing, with protected operations for authenticated users and administrative product management

### Frontend
- React with JavaScript and Vite
- React Router for page navigation
- Axios for API requests
- Product browsing, login and registration, cart, checkout, and order pages
- Session-based handling of authentication details in the frontend

## Tech Stack

| Area | Technologies |
|---|---|
| Backend | Java, Spring Boot, Spring MVC |
| Data access | Spring Data JPA, Hibernate |
| Database | MySQL |
| Security | Spring Security, HTTP Basic, BCrypt |
| API mapping | DTOs, MapStruct |
| Caching | Caffeine |
| Frontend | React, JavaScript, Vite, React Router |
| HTTP client | Axios |
| Styling | CSS |
| Build tools | Maven, npm |

## Project Structure

```text
Online-Store/
├── OnlineStoreBackend/     # Spring Boot REST API
└── OnlineStoreFrontend/    # React + Vite application
```

## Getting Started

### Prerequisites

Install the following:
- Java version compatible with the backend project
- Node.js and npm
- MySQL
- Git

### 1. Set up the database

1. Start MySQL.
2. Create a database for the application, for example:

   ```sql
   CREATE DATABASE online_store;
   ```

3. Configure the database URL, username, and password in the backend's `application.properties` (or the configuration file used by your local setup).

   Example configuration:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/online_store
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

   Use your own local database credentials.

### 2. Run the backend

Open a terminal in `OnlineStoreBackend`:

```powershell
cd OnlineStoreBackend
.\mvnw spring-boot:run
```

The backend runs at `http://localhost:8080` by default, unless your configuration specifies another port.

### 3. Run the frontend

Open a second terminal in `OnlineStoreFrontend`:

```powershell
cd OnlineStoreFrontend
npm install
npm run dev
```

Open the local URL printed by Vite, usually `http://localhost:5173`.

Make sure the backend and MySQL are running before using the frontend.

## API Overview

The backend exposes REST endpoints under `/api`. The following are examples of the main resources:

| Resource | Example endpoint | Purpose |
|---|---|---|
| Authentication | `/api/auth/**` | Registration and authentication-related operations |
| Products | `/api/products?page=0&size=12` | Retrieve a page of products |
| Categories | `/api/categories` | Retrieve categories |
| Cart | `/api/cart` | Retrieve the authenticated user's cart |
| Cart items | `/api/cart/items/{productId}/{quantity}` | Update a product's quantity in the cart |
| Orders | `/api/orders` | Order operations, including checkout and order retrieval |
| Reviews | `/api/reviews/products/{productId}` | Product review operations |

The exact HTTP method and access requirements depend on the endpoint. Protected operations require authentication, and administrative product-management operations require the appropriate role.

## Authentication Notes

The application uses **HTTP Basic authentication**, not JWT. The frontend sends authentication information with API requests, and the backend verifies credentials through Spring Security. Passwords are stored as BCrypt hashes.

For local development, ensure the frontend's Axios base URL points to the backend address (by default, `http://localhost:8080`) and that the backend's CORS configuration allows the frontend origin.

## Future Improvements

Possible extensions include automated backend and frontend tests, and additional store features.

## Author

**Malak Jamal Milhem**  
Computer Engineering student at Birzeit University
