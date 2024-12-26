# Spring Logistics Backend 🚀

![Java](https://img.shields.io/badge/Java-98.4%25-blue)
![Dockerfile](https://img.shields.io/badge/Dockerfile-1.6%25-blue)

Spring Boot application designed to manage orders and centers. It includes functionalities for creating orders, allocating them to centers, and managing center capacities. This project was build for the INDITEX Java Backend challenge, UI maded in Vue with Nuxt and Mapbox for fun just to have a more friendly way to test the api.

![Captura de pantalla 2024-12-26 a las 15 05 19](https://github.com/user-attachments/assets/0c971a93-e61a-4192-8934-87db7bfe0fad)
UI LINK: https://github.com/CoderAnchel/Logistics-UI 

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- Create and manage orders
- Allocate orders to the nearest available center
- Manage center capacities and statuses
- RESTful API with endpoints for orders and centers

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/CoderAnchel/magic-eagle-learning.git
    ```
2. Navigate to the project directory:
    ```sh
    cd magic-eagle-learning
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```
4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access the API at `http://localhost:8080`.

## API Endpoints

### Orders

- **Create Order**
    ```http
    POST /orders/create
    ```
    **Request Body:**
    ```json
    {
        "customerId": 1,
        "size": "large",
        "coordinates": {
            "latitude": 42.3601,
            "longitude": -71.0589
        }
    }
    ```

- **Allocate Orders**
    ```http
    GET /orders/allocation
    ```

- **Get All Orders**
    ```http
    GET /orders/all
    ```

### Centers

- **Get All Centers**
    ```http
    GET /centers
    ```

- **Create Center**
    ```http
    POST /centers
    ```
    **Request Body:**
    ```json
    {
        "name": "Center 1",
        "status": "Available",
        "capacity": "large",
        "maxCapacity": 100,
        "currentLoad": 0,
        "coordinates": {
            "latitude": 42.3601,
            "longitude": -71.0589
        }
    }
    ```

- **Get Center Distance**
    ```http
    POST /centers/distance/all
    ```
    **Request Body:**
    ```json
    {
        "latitude": 42.3601,
        "longitude": -71.0589
    }
    ```

- **Get Nearest Center**
    ```http
    POST /centers/distance/nearest
    ```
    **Request Body:**
    ```json
    {
        "latitude": 42.3601,
        "longitude": -71.0589
    }
    ```

- **Update Center**
    ```http
    PATCH /centers/update/{id}
    ```
    **Request Body:**
    ```json
    {
        "name": "Updated Center",
        "status": "Full",
        "capacity": "medium",
        "maxCapacity": 150,
        "currentLoad": 75,
        "coordinates": {
            "latitude": 42.3601,
            "longitude": -71.0589
        }
    }
    ```
