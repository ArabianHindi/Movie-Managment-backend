# Movie-Managment-backend
This is a web-based application that provides a platform for both admin and regular users to manage and view movies. It integrates with the OMDB API to fetch movie details and allows admins to curate a custom movie list for users.

## Features

### Admin Features
- Secure login with admin credentials
- Search and add movies from OMDB API
- Remove movies from the database
- Batch add/remove movies

### User Features
- User registration and login
- Browse all movies in the database
- Search for specific movies
- View detailed movie information
- Rate and review movies

## Tech Stack

### Backend
- Java 17
- Spring Boot 3.4.4
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL
- OMDB API Integration
- Maven

### Frontend(not finished)
- Angular 17
- Angular Material
- TypeScript
- SCSS
- RxJS

## Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- PostgreSQL
- Maven
- Angular CLI

## Setup Instructions

### Backend Setup

1. Clone the repository:
```bash
git clone
cd movie-app-backend
```

2. Create a PostgreSQL database named `movie_db`

3. Update the `application.properties` file with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/movie_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Get an OMDB API key from https://www.omdbapi.com/ and update it in `application.properties`:
```properties
omdb.api.key=your_api_key
```

5. Build and run the backend:
```bash
mvn clean install
mvn spring-boot:run
```

The application will be available at:
- Frontend: http://localhost:4200
- Backend: http://localhost:8080

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token

### Movies
- `GET /api/movies` - Get all movies (paginated)
- `GET /api/movies/search` - Search movies by title
- `GET /api/movies/{imdbId}` - Get movie details
- `POST /api/admin/movies/{imdbId}` - Add a movie (admin only)
- `DELETE /api/admin/movies/{imdbId}` - Remove a movie (admin only)
- `POST /api/admin/movies/batch` - Add multiple movies (admin only)
- `DELETE /api/admin/movies/batch` - Remove multiple movies (admin only)

### Ratings
- `POST /api/movies/{imdbId}/ratings` - Add a rating
- `GET /api/movies/{imdbId}/ratings` - Get movie ratings

## Security

- JWT-based authentication
- Role-based access control (ADMIN/USER)
- Password encryption using BCrypt
- CORS configuration
- CSRF protection

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
