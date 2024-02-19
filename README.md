# TO BUILD

## With Nix (preferred):
Run `nix build .#P1` to build project 1\
All dependencies are automatically locked\
and the compilation tested to be 100% reproducible\
\
The result will be linked to the `result` folder

## With maven:
Enter the project folder where `pom.xml` is located\
\
Run `mvn test` to run the tests\
Run `mvn compile` to build the project\
The result .jar will be found in the `target` folder

## Manually:
Enter the `src/main` folder in the project folder\
Run `javac` or `java` to build or run the project

### Dependencies
All dependencies are declared in the `pom.xml` file\
The most notable dependency is `org.junit.jupiter:junit-jupiter:5.10.2` currently
