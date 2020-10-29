Instructions
====================
The building process assumes that you have [Maven](https://maven.apache.org/) and all its requirements (Java, etc).

### Build your project
- From the command line, at the root of the project run:
	```
	mvn compile
	```

- If you wish to clean the  `target/`  folder. Run the following maven build command

	```bash
	mvn clean install
	```

- The following command builds the maven project and installs it into local maven repository.

	```bash
	mvn install
	```

### Execute the project

- Maven's exec plugin can be used to run any of the main class generated in the target folder. Here the main class being  `com.jobsity.challenge.App` , and passing the absolute path to the input file as a parameter:

	```bash
	mvn exec:java -Dexec.mainClass=com.jobsity.challenge.App -Dexec.args="path-to-the-input-file"
	```
### Running Unit Tests

- Run the  `test`  goal. It will run all the unit tests.

    ```bash
    mvn test
    ```

- You can also run a single test file or a particular method inside a test file as follows.

    ```bash
    mvn test -Dtest=com.jobsity.challenge.unit.BowlingGameScoreBoardTest#testMethodName

### Running Integration Tests
- To run integration tests, use the failsafe plugin and run this command:

    ```bash
    mvn failsafe:integration-test
    ```
  
  
### Included Files

In the directory `bowling-score/src/main/java/jobsity/challenge/files` you can find test
files already created and they are:

- `jobsity-example.txt`: A file describing the example given in the instructions for the test.
- `youtube-example.txt`: A file describing the scenario on the youtube video given in the instructions for the test.
- `all-foul.txt`: A file describing a scenario where all the throws are fouls `F`.
- `no-score.txt`: A file describing a scenario where all throws are zero.
- `perfect-score.txt`: A file describing a perfect match, where all throws are Strikes.

To test the application using these files, run the program as previously explained, and pass the path to any of these files as a parameter.

- For example using one of these files pass this parameter:

    `{your-project-directory}/src/main/java/com/jobsity/challenge/files/jobsity-example.txt` 

- Using a file that you created pass this parameter:

    `/Users/user/Desktop/custom-file.txt`