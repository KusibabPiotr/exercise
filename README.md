# recruitment task
##### Simple REST API as exercise.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)

## General info
This is REST API created as exercise for CDQ.
	
## Technologies
Project is created with:
* Java JDK 15.2
* Spring Boot
* PostgreSQL

## Features
* you can create nawe task, you will get task ID in return,
* you can check progress and status of any task if you have task ID,
* you can fetch statuses and progress of all tasks stored in db

## Setup
* first of all you have to download code
* go to project root and tap "docker compose up -d"
* you need DB go to >>> http://localhost:5050/browser/ and create server with:
  - General: tap any name
  - Connection: Host Name / Address >>> postgres
                username >>> amigoscode
                password >>> password
* build project with "gradlew build"
* go to /build/libs and run application with "java -jar exercise-0.0.1-SNAPSHOT.jar
