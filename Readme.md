# Workout Club API

The Workout Club API is a RESTful service designed for managing workout club operations. It enables club owners to create and manage classes, members to book spots in classes, and owners to review bookings using search functionalities. This project is implemented using Java and Spring Boot, and it uses in-memory data storage for simplicity.

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

## Setup Instructions

### Prerequisites
- Java 17 or later
- Maven

### Running the Application
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```
2. Navigate to the project directory:
   ```bash
   cd workout-club-api
   ```
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. The application will be available at `http://localhost:8080`.

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
│   │       ├── models
│   │       ├── services
│   │       ├── utils
│   │       └── WorkoutClubApplication.java
│   └── resources
├── test
│   └── java
│       └── com.example.workoutclub
│           ├── ClassServiceTest.java
│           ├── BookingServiceTest.java
│           └── ControllerTests.java
```

## License
This project is provided for educational purposes and does not include a specific license.

