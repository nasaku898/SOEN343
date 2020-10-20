SOEN-343 Project: Smart Home Simulation System

1. Smart Home Simulator

	The Smart Home Simulation System is a suite of interactive modules designed to reproduce the systems that operate
inside a smart home. The system allows a user to control every aspect of a smart home that would exist inside a
physical smart home, such as door locks, lights, heating and air conditioning. The simulator is comprised of a number
of modules that each have their own dedicated purpose and reproduce inputs from sensors as well as the various 
commands that can be sent to the integrated systems under their purview.

	Simulations can be run from the system to model the behaviour and interactions of the different modules under
a specific set of initial conditions, and data is gathered over the course of the simulation and collected in a
database for further analysis. Users can influence the simulation by manually opening doors and windows, turning
lights on and off, etc.

2. Current Objective

	The overall mission for this part of the project is to build the backbone of the simulator as well as its basic
user interface. A user login feature will be created to allow users to create profiles and set permissions, which
will be collected in the simulator database. This database will be setup to gather all data regarding the home, such
as its list of rooms, doors, windows and lights, as well as certain conditions, such as the temperature. A Smart Home
Core module will be developed to let users have basic controls over the home's environment, such as opening and
closing doors and windows, turning lights on and off, moving users from one room to another, blocking windows,
changing the outside temperature, and changing the date and time.

3. Team Members

	The developers for this project are:

	- Simon Lim 
	- Neerujah Ledchumanan
	- Matthew Frances 
	- Alexandre Careau
	- Josh Fried

4. List of Features

	The following features will be included in the current deliverable: 
	
	- User Login and Profile: Users can login and create a profile with a set of permissions that will allow them to 
  acces a pre-determined set of control features. The user profiles are saved in the database.
  
	- Smart Home Core module: This module allows the user to control the basic subset of the smart home's systems.
  This module oversees all of a house's doors, windows, and lights, and gives other modules permission to send
  commands to these objects.
  
  - Smart Home Simulator dashboard: This is the user interface of the system. It includes a set of tabs to navigate
  between the different smart home modules, a house layout view which displays the different rooms that exist in
  the currently loaded house as well as their properties, a user profile section, an output console, and a central
  form for the user to control the current module's settings.
  
  - Simulation preferences: The user will be able to adjust settings before starting a simulation, such as moving 
  users to different rooms of the home, blocking windows, setting the outside temperature and setting the date and
  time of the simulation. These settings will also be modifiable once a simulation has begun.
  
5. Technology Used

  The entire server-side of the project was written in Java, using the Spring Boot framework for the architecture, 
as well as the Lombok library of tools. The database is created in MySQL and is accessed through Spring Data JPA.
The server is built and executed using Maven.
  
  The web user interface is written in Javascript using the React framework for the architecture, and npm for
library management.

6. Installation
