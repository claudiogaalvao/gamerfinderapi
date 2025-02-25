# Game Rooms API

This is a RESTful API built with Spring Boot and Kotlin. It allows users to view available games, create rooms, update them, and delete rooms. The primary focus of this API is to provide a platform for players to create game rooms and invite others to join them.

## Endpoints

The API exposes the following endpoints:

- **GET /games**: Retrieve a list of available games.
- **GET /rooms**: Retrieve a list of rooms.
- **POST /rooms**: Create a new room.
- **PUT /rooms/{id}**: Update a room's information.
- **DELETE /rooms/{id}**: Remove a room.

## API Documentation

You can find the collection of the API endpoints mapped out in Postman at the following link:  
[Game Rooms API Collection - Postman](https://grey-shuttle-689501.postman.co/workspace/My-Workspace~145a0d82-6526-4d94-862c-03dcca93ea43/collection/7263376-0f4ff2e1-1796-4a53-ad76-05c0dc49c875?action=share&creator=7263376)

## Features

- **Game & Room Management**: Players can create and manage game rooms.
- **MySQL Integration**: Persistent data storage with MySQL, configured with JPA.
- **Data Validation & Error Handling**: Requests are validated, and appropriate error responses are returned (e.g., 404 Not Found, 400 Bad Request).
- **Separation of Concerns**: The API follows best practices for separation of concerns, including distinct models for requests, responses, and domain logic.

## Data Models

- **Request Models**: Used to validate and accept input from the client.
- **Response Models**: Used to structure the data returned to the client.
- **Domain Models**: Represent the core business logic, like the game and room entities.

## Technologies

- **Spring Boot**: Framework used for building the REST API.
- **Kotlin**: Programming language used for implementing the API.
- **JPA (Java Persistence API)**: ORM framework for database operations.
- **MySQL**: Relational database for persistent data storage.
- **Docker**: Used to run the MySQL database in a container.

## Installation & Setup

To run this project locally:

1. Clone the repository:

   ```bash
   git clone https://github.com/claudiogaalvao/gamerfinderapi.git
   cd gamerfinderapi

2. Build the project:

   ```bash
   ./gradlew bootRun
   
3. Run the application:

   ```bash
   ./gradlew bootRun
   
4. The API will be available at `http://localhost:8080`.

## Future Enhancements

- Integration with a persistent database (e.g., PostgreSQL, MySQL). [DONE]
- Authentication and Authorization mechanisms to restrict access to certain endpoints.
- Additional game and room features (e.g., request to join a room, private rooms, etc.).


## Contributing

Feel free to open issues or submit pull requests. Contributions are welcome! When contributing, please ensure that your code adheres to the project's coding style and includes appropriate tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.