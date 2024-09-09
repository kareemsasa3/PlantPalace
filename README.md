# PlantPalace
Welcome to PlantPalace, a cutting-edge full-stack e-commerce platform designed for herbal and cannabis product retailers. Powered by a React-based frontend and a Spring Boot backend, the application offers a seamless, scalable, and user-friendly shopping experience. Built with Docker and Docker Compose for streamlined deployment, the platform leverages npm and Maven for efficient package management, and integrates an SQL database for secure and reliable data storage.

## Installation
1. Clone the repository:
     ```bash
      git clone https://github.com/kareemsasa3/PlantPalace.git
      cd PlantPalace
     ```
2. Set up environment variables:
   - Create a .env file in the root of the project directory.
   - Create a .env file in the root of the /frontend directory.
   - Create a .env file in the resources directory in the /backend directory.
   Each .env file should contain the necessary environment variables, such as API keys, database credentials, and configuration options,
   Example content might look like:
     ```bash
       # Root .env file
       DATABASE_URL=your_database_url
     ```
     ```bash
       # /frontend/.env
       REACT_APP_API_URL=your_backend_api_url
     ```
     ```bash
       # /backend/src/main/resources/.env
       SPRING_DATASOURCE_URL=jdbc:your_database_url
     ```
3. Install dependencies:
   - For the frontend:
       ```bash
         cd frontend
         npm install
       ```
   - For the backend:
       ```bash
         cd backend
         mvn install
       ```
4. Run the application using Docker Compose:
     ```bash
         docker compose build
         docker compose up -d
         docker compose down (to end the process)
     ```
5. Access the application:
   - Frontend: http://localhost:3000
   - Bakcend API: http://localhost:8090
 
   
