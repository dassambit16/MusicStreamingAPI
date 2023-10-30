# Music Streaming API

## Framework Used

- Spring Boot

## Dependencies

The following dependencies are required to run the project:

- Spring Web
- Spring Data JPA
- MySQL Driver
- Lombok
- Validation
- Swagger

## Language Used

- Java (Version: 17)

## Data Model

### User Model

- Id: integer
- Password: string
- Email: string
- Role: Role

### Playlist Model

- PlaylistId: Long
- PlayListName: String
- Songs: Songs
- User: User

### Song Model

- SongId: Long
- SongTitle: String
- Artist: String
- User: User

### Authentication Token

- TokenId: Long
- TokenValue: string
- TokenCreationDateTime: LocalDate
- User: User (One-to-One relationship)

### Role

- Enum: ADMIN, NORMAL

## Data Flow

1. The user at the client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in the service class.
4. The method in the service class builds logic and retrieves or modifies data from the database, which is in turn given to the controller class.
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

## Database Used

- SQL database

We have used a persistent database to implement CRUD Operations.

## Documentation

This project implements a Music Streaming application using Spring Boot. The code includes several controller classes (PlayListController, UserController, SongController) and other supporting classes (DTOs, models, repositories, services).

Here is a breakdown of the code provided:

- **PlayListController class**: This class is a Spring RestController that handles HTTP requests related to playlists.

- **SongController class**: This class is a Spring RestController that handles HTTP requests related to songs. It has methods for getting all songs.

- **UserController class**: This class is a Spring RestController that handles HTTP requests related to users. It has methods for making CRUD Operations for songs, playlists. Users can Sign In, Sign Up, and Sign Out successfully.

- **DTOs (Data Transfer Objects)**: These classes (SignInInput, SignInOutput, SignUpInput, SignUpOutput) represent the data transferred between the client and server. They contain fields and annotations for data validation.

- **Models**: These classes (AuthenticationToken, PlayList, Role, Song, User) represent the entities in your application. They are annotated with JPA annotations to define the database schema.

- **Repositories**: These interfaces (IPlayListRepo, ISongRepo, IAuthTokenRepo, IUserRepo) extend the Spring Data JPA JpaRepository interface and provide methods for interacting with the database.

- **Services**: These classes (PlayListService, UserService, SongService, AuthenticationService, UserService) contain the business logic of your application. They use the repositories to perform CRUD operations on the entities.

Overall, the code structure follows the MVC (Model-View-Controller) pattern commonly used in Spring Boot applications. The controllers handle HTTP requests, the services handle the business logic, and the repositories handle the database operations.

Feel free to customize this README to include specific instructions on how to build and run your project, as well as any other relevant information for potential contributors or users.
