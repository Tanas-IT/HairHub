# Stage 1: build
# Start with a Maven image that includes JDK 24
FROM maven:3.9.9-amazoncorretto-24 AS build

# Copy source code and pom.xml file to /app folder
# Tạo thư mục app và chuyển thư mục hiện tại về thư mục /app
WORKDIR /app

# Copy file pom.xml vào thư mục app
COPY pom.xml .

# Copy thư mục src vào thư mục app/src
COPY src ./src

# Build source code with maven
RUN mvn package -DskipTests

# Stage 2: create image
# Start with Amazon Correcto JDK 24
FROM amazoncorretto:24

# Set working folder to App and copy compiled file from above step
# (Copy file jar từ image build ở trên và đổi tên file jar đó thành app.jar)
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
