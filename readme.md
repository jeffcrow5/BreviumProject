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

## Notes
- I was unfortunately not able to completely finish this project before hitting 3 hours. I was able to get the initial schedule and the requests for appointments, but I was not able to finish the logic for scheduling appointments. I believe I was close to getting it done, but I was not able to finish it in time. I also did not have time to write tests for the project, which I would have liked to do. I would have used JUnit for the tests. I would have also finished the JavaDocs for the project, as I was not able to finish those either.
- A note about timing... You'll see that my initial commit was well before my other commits. Something came up at home that I had to take care of and which ended up taking a few hours to resolve. My total time remains under 3 hours, but I wanted to make sure you knew why there was a gap in my commits.
- There are several changes that would like to make to this project if given more time, after getting the logic to actually work, of course. If given the chance, I can talk about that with you if you end up liking my work and would like to talk more.