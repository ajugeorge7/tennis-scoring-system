## Tennis Scoring System

A tennis scoring system to calculate the number of points won, games won and sets won by each player.
 
This is based on the input supplied to the `Match` class using `pointsWonBy()` method.

The final score can be obtained using `score()` method in the `Match` class.

#### Constraints
* Only taken into consideration about 1 set winner.
* No validations done under the assumption that the client passes in correct data.

#### Prerequisites
* Java 8
* Maven 3+

#### How to Build
```bash
mvn clean install
```

It creates a shaded jar, which can be tested by running:
```bash
java -jar target/tennis-scoring-system-1.0-SNAPSHOT.jar
```

#### Tools Used
Have used the `jacoco` library to ensure the code coverage of application.

#### How to Test
Apart from the unit tests for each class, use the `TennisScoringSystem` class to test the actual match.

There is already a match been played across Roger Federer and Novak Djokovic there.
