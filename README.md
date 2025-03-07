# pharmacy-api
# Pharmacy Management API - Project Overview

This project is a **Pharmacy Management System API** built using **Spring Boot**. It includes core functionalities for user authentication (login and registration), sales management, inventory tracking, customer management, reporting, and dashboard views. JWT tokens are used for secure user sessions.

## Features

### 1. **Authentication & Authorization**
- **User Registration**: Allows new users to register with necessary details (email, username, password).
- **User Login**: Authenticates users and generates a JWT token for secure communication.
- **JWT Token Management**: Ensures only authenticated users can access protected endpoints.

### 2. **Sales Management**
- Create and manage sales records.
- Track sales by date, customer, and product.

### 3. **Inventory Management**
- Add, update, and remove products from inventory.
- Track stock levels and reorder thresholds.

### 4. **Customer Management**
- Add and manage customer details.
- View customer purchase history.

### 5. **Reporting**
- Generate sales and inventory reports.
- Filter reports by date range, product, or customer.

### 6. **Dashboard**
- Overview of key metrics such as total sales, inventory status, and customer insights.

## Project Structure

The project follows a **layered architecture** for better scalability and maintainability:

- `auth/`: Authentication and user management.
- `sales/`: Sales-related logic and controllers.
- `inventory/`: Inventory management features.
- `customer/`: Customer-related features.
- `reports/`: Report generation and management.
- `dashboard/`: Aggregated data for display on the dashboard.

## Technologies Used

- **Spring Boot**: Java framework for building RESTful APIs.
- **JWT (JSON Web Tokens)**: For secure session management.
- **MongoDB**: NoSQL database for flexible data storage.
- **Maven**: Dependency management and build automation.

## API Endpoints

### Authentication

- `POST /api/register`: Register a new user.
- `POST /api/login`: Authenticate user and generate a JWT token.

### Sales

- `GET /api/sales`: Retrieve all sales records.
- `POST /api/sales`: Create a new sales record.

### Inventory

- `GET /api/inventory`: Retrieve inventory items.
- `POST /api/inventory`: Add a new product.
- `PUT /api/inventory/{id}`: Update product details.
- `DELETE /api/inventory/{id}`: Delete a product.

### Customers

- `GET /api/customers`: Retrieve all customers.
- `POST /api/customers`: Add a new customer.
- `GET /api/customers/{id}`: View customer details and purchase history.

### Reports

- `GET /api/reports/sales`: Generate sales reports.
- `GET /api/reports/inventory`: Generate inventory reports.

### Dashboard

- `GET /api/dashboard`: Retrieve summary metrics for the dashboard.

## Installation & Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/nicolas/pharmacy-api.git
   ```

2. **Navigate to Project Directory**:
   ```bash
   cd pharmacy-api
   ```

3. **Configure Database**:
   - Update the `application.properties` file with your MongoDB database credentials.

   Example:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/pharmacy_db
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
   The API will be available at `http://localhost:8080/api`.

## Security

- **Password Encryption**: User passwords are encrypted before storing in the database.
- **JWT Tokens**: Used for session management, ensuring secure access to protected endpoints.

## Error Handling

- Custom exceptions are used for better error messages (e.g., `UserNotFoundException`, `InvalidPasswordException`).
- Global exception handlers ensure consistent error responses.

## Future Enhancements

- Add role-based access control (e.g., admin vs. regular user).
- Implement product categorization and supplier management.
- Add export options for reports (e.g., CSV, PDF).

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch.
3. Make your changes and test thoroughly.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

---

Thank you for using the Pharmacy Management API!

