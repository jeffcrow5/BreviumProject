# Jeffrey Crowley's Brevium Project

## Description
This project is a simple service that schedules doctor's appointments based on the initial schedule and the requests for appointments that exist. The project is written in Java, using Gradle as the build tool.

## Setup
To run the project, you will need to have Java 8 installed on your machine. You can download it from the [Oracle website](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html). You will also need to have Gradle installed. You can download it from the [Gradle website](https://gradle.org/install/). I used [IntelliJ IDEA](https://www.jetbrains.com/idea/) as my IDE, as it installed Gradle for me and made it easy to run the project.

## Running the Project
The following steps will allow you to run the project:
1. Clone the repository to your local machine.
2. Open the project in your IDE.
3. Add a new configuration to run the project. You will need to add two environment variables: `API_URL` and `API_TOKEN`. 
    - `API_URL` should be the URL of the API you want to use (up to and including the `api/Scheduling`)
    - `API_TOKEN` should be a valid API token as specified in the project specs.
4. Run the main (SchedulingApp) class.

The project will then run and output the results to the console.