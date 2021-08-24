# Modified Pacman Clone Written in Java

## Dependencies

- Gradle 6.x
	
- Java 8.x


## Playing the game

1. Clone the repository
2. Move into the project directory, for example if it was cloned into your home folder use `cd ~/wakka`
3. From within the project folder use `gradle run` to run the game. Note that the first time the game is run, project dependencies will need to be downloaded so it may take a minute.

### Configuration

The game offers several configuration options which can be set in the config.json

- "map": give and absolute path to a map configuration text file.
- "lives": the number of lives
- "speed": Speed of the player character and the antagonists
- "frightenedLength": The length of time where the ghosts are frightened after eating a super fruit and can be killed by the player character 
- "modeLengths": An array of integers representing the time the ghosts spend in scatter mode (target the corners of the map) or chase mode (targets the player although each type of ghost behaves slightly differently). The game simply alternates between each mode starting from scatter mode. When the end of the array is reached it simply continues from the start again.

Maps can be configured in text files using ascii characters. A valid map is 36 characters tall and 28 wide. No validation is done.

The map configuration files use the following syntax:

- 'p': Player starting location
- 'a': The starting cell of an ambusher ghost. Ambusher ghosts targets four grid spaces ahead of waka during CHASE mode
- 'c': The starting cell of a chaser ghosts. Targets waka during CHASE mode
- 'i': The starting cell for ignorant ghosts. Targets waka if further than 8 units away (euclidean distance) otherwise it targets bottom left corner
- 'w': Targets the cell located at double the vector from a chaser ghost to two grid spaces ahead of waka
- 's': Soda can: When a soda can is stepped on by the player the ghosts turn invisible for a short period
- '8': Super Fruits are twice the size of normal fruits and frighten the ghosts. while in frightened mode theymove randomly and if the player hits them they will die.
- '7': Fruit cell. When all fruit cells have been eaten by the player the player wins
- '6': Top left corner wall 
- '5': Top right corner wall
- '4': Bottom right corner wall
- '3': Bottom left corner wall
- '2': Vertical wall
- '1': Horizontal Wall

## Debugging and Testing

To show the ghosts targets during the running game for debugging, simply press spacebar to toggle.

To run the tests, use `gradle test`. A basic report viewable in the browser will be created. Its path will be shown by gradles output.

For more in detail test reporting, use `gradle jacocTestReport`. The report can be found in the `build/reports/jacoco/test/html` folder and is again viewable in the browser.
