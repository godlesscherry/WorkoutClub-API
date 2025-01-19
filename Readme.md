# Workout Club API

The Workout Club API is a RESTful service designed for managing workout club operations. It enables club owners to create classes, book spots in classes, and search functionalities. This project is implemented using Java and Spring Boot, and it uses in-memory data storage for simplicity.

## Features

### Classes
- **Create workout classes** with the following details:
    - Name
    - Start Date
    - End Date
    - Start Time
    - Duration (in minutes)
    - Capacity (maximum participants)
- **Enforce constraints** such as:
    - Capacity must be at least 1.
    - End date must be in the future.

### Bookings
- **Book a spot** in a class with:
    - Member name
    - Class name
    - Participation date
- **Validate bookings**:
    - Participation date must be in the future.
    - Bookings cannot exceed class capacity.

### Search
- **Search bookings** by:
    - Member name
    - Date range
    - Combine filters for detailed results.

## Technologies
- **Java 17**: Programming language used for the project.
- **Spring Boot**: Framework for building Java applications.
- **Maven**: Build automation tool for managing dependencies.
- **JUnit 5**: Testing framework for unit tests.
- **Mockito**: Mocking framework for unit tests.

## API Design

### Architecture
The API follows a layered architecture with the following layers:

- Controller Layer: Handles HTTP requests and responses.
- Service Layer: Contains business logic.
- Model Layer: Represents the data structures.
- Utility Layer: Provides helper functions.

### Usability
- RESTful Endpoints: The API uses standard HTTP methods (GET, POST) and follows REST principles.
- JSON Responses: All responses are in JSON format for easy consumption by clients.

### Data Models
The API uses the following data models:

- **ClassDetails**: Represents a workout class with details such as name, date, time, duration, and capacity.
- **Booking**: Represents a booking with member name, class name, and participation date.
- **ResponseFormatter**: Utility class for formatting API responses.
- **ErrorResponse**: Represents an error response with status and message.

### Error Handling
The API provides error responses with appropriate status codes and messages for invalid requests.


## Setup Instructions

### Prerequisites
- Java 17 or later
- Maven

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/godlesscherry/WorkoutClub-API.git
   ```
2. Navigate to the project directory:
   ```bash
   cd workout-club-api
   ```
3. Install dependencies using Maven:
   ```bash
   mvn install
   ```
4. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
5. The application will be available at `http://localhost:8080`.

### API Endpoints

#### Classes
- **Create Class**
    - **Endpoint**: `POST /api/classes`
    - **Request Body**:
      ```json
      {
        "name": "Pilates",
        "startDate": "2023-12-01",
        "endDate": "2023-12-20",
        "startTime": "14:00:00",
        "duration": 60,
        "capacity": 10
      }
      ```
    - **Response**:
      ```json
      {
        "status": "success",
        "message": "Class added successfully.",
        "data": {
          "name": "Pilates",
          "startDate": "2023-12-01",
          "endDate": "2023-12-20",
          "startTime": "14:00:00",
          "duration": 60,
          "capacity": 10
        }
      }
      ```

#### Bookings
- **Book Class**
    - **Endpoint**: `POST /api/bookings`
    - **Request Body**:
      ```json
      {
        "memberName": "John Doe",
        "className": "Pilates",
        "participationDate": "2023-12-01"
      }
      ```
    - **Response**:
      ```json
      {
        "status": "success",
        "message": "Booking created successfully.",
        "data": {
          "memberName": "John Doe",
          "className": "Pilates",
          "participationDate": "2023-12-01"
        }
      }
      ```

- **Search Bookings**
    - **Endpoint**: `GET /api/bookings`
    - **Query Parameters**:
        - `memberName` (optional)
        - `startDate` (optional)
        - `endDate` (optional)
    - **Response**:
      ```json
      {
        "status": "success",
        "message": "Bookings retrieved successfully.",
        "data": [
          {
            "memberName": "John Doe",
            "className": "Pilates",
            "participationDate": "2023-12-01"
          }
        ]
      }
      ```
- **Search Classes**
    - **Endpoint**: `GET /api/classes`
    - **Query Parameters**:
        - `name` (optional)
        - `startDate` (optional)
        - `endDate` (optional)
    - **Response**:
      ```json
      {
        "status": "success",
        "message": "Classes retrieved successfully.",
        "data": [
          {
            "name": "Pilates",
            "startDate": "2023-12-01",
            "endDate": "2023-12-20",
            "startTime": "14:00:00",
            "duration": 60,
            "capacity": 10
          }
        ]
      }
      ```
- **Error Response**
    - **Response**:
      ```json
      {
        "status": "error",
        "message": "Invalid input.",
        "data": null
      }
      ```

## Testing

### Unit Tests
The project includes unit tests to validate the functionality of the API.

#### Running Tests
To execute the tests, use the following command:
```bash
mvn test
```

## Project Structure
```
src
├── main
│   ├── java
│   │   └── com.example.workoutclub
│   │       ├── controllers
│   │       │   ├── BookingController.java
│   │       │   └── ClassController.java
│   │       ├── models
│   │       │   ├── Booking.java
│   │       │   └── ClassDetails.java
│   │       ├── services
│   │       │   ├── BookingService.java
│   │       │   └── ClassService.java
│   │       ├── utils
│   │       │   └── ResponseFormatter.java
│   │       └── WorkoutClubApplication.java
│   └── resources
│       └── application.properties
├── test
│   └── java
│       └── com.example.workoutclub
│           ├── controllers
│           │   ├── BookingControllerTest.java
│           │   └── ClassControllerTest.java
│           ├── models
│           │   └── BookingTest.java
│           ├── services
│           │   ├── BookingServiceTest.java
│           │   └── ClassServiceTest.java
│           └── utils
│               └── ResponseFormatterTest.java
```

## License
This project is provided for educational purposes and does not include a specific license.

