SOFT2201 Assignment 1 README

This project consists of the construction of a platformer video game.
A hero is created that is able to move left, right and jump on some floor,
and clouds blow in the sky.

In order to run the game, ensure Java 11 and Gradle is installed and functioning.
For a Linux operating system, in the CLI (command-line interface) type in: 

	$gradle build

Afterwards run the game by typing:

	$gradle run

The configuration file is a JSON file and can be found in src/main/resources.
In the configuration file, changes can be made to stickman size, stickman position, 
cloud velocity, level number, number of platforms, platform positions, number of 
enemies, enemy positions, and finish line position. The default configuration file is
"src/main/resources/medium.json".

To test the game, type:

	$gradle test

If Java 11 and/or Gradle is not installed, it is accessable through 
https://sdkman.io/. The following steps can be followed to install it in a Linux 
environment:
	
	$sdk install java
	$sdk intall gradle

Coding style:
https://google.github.io/styleguide/javaguide.html
