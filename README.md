Weather Service Application

A Java console application that retrieves live weather data from the OpenWeather API and compares temperature ranges between two cities. The project demonstrates REST API integration, JSON parsing, object modeling, and clean modular program design.

This project was built to practice consuming third-party web services and converting raw JSON into structured Java objects using the Gson library.

--------------------------------------------------

Features

- Live weather data from OpenWeather API
- JSON → Java object mapping using Gson
- User input via console
- Temperature comparison between cities
- High / low / current temperature reporting
- Modular object-oriented architecture
- Error handling for API failures

--------------------------------------------------

How It Works

1. User enters two city names
2. Program calls OpenWeather API for each city
3. Raw JSON response is retrieved
4. Gson converts JSON into Java object hierarchy
5. Application compares temperature ranges
6. Results are printed to console

The application models the API response using structured Java classes:

WeatherServiceManager → Weather → Weather_main

This mirrors the JSON structure and allows clean access to parsed weather data.

--------------------------------------------------

Tech Stack

- Java
- REST API (OpenWeather)
- Gson JSON library
- HTTP URL connection
- Console I/O
- Object-oriented design

--------------------------------------------------

Example Output

Please enter the first city: San Diego
Please enter second city: Denver

The temperature in Denver is 38°F
Denver has a greater temperature range than San Diego
Denver: high of ~ 45°F low of ~ 22°F

--------------------------------------------------

How to Run

1. Clone repository:

git clone https://github.com/mattgallenberger/WeatherService.git

2. Open project in IntelliJ

3. Add Gson library dependency

4. Run:

WeatherServiceManager.java

5. Enter two city names when prompted

--------------------------------------------------

API Key Notice

This project uses the OpenWeather API.

For production use, the API key should be stored securely using environment variables instead of hardcoding it in the source code.

--------------------------------------------------

Project Structure

src/com/grossmont/ws/
  WeatherServiceManager.java
  Weather.java
  Weather_main.java

- WeatherServiceManager → handles API calls and program logic
- Weather → represents parsed weather response
- Weather_main → maps temperature fields from JSON

--------------------------------------------------

Learning Objectives

This project demonstrates:

- Consuming REST APIs in Java
- JSON parsing with Gson
- Mapping nested JSON structures to Java objects
- Clean separation of data and logic
- Console application workflow
- Practical error handling

--------------------------------------------------

Author

Matt Gallenberger
Computer Science Student
San Diego State University
