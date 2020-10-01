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

- Maven's exec plugin can be used to run any of the main class generated in the target folder. Here the main class being  `com.jobsity.challenge.App` , and passing the path to the input file as a parameter:

	```bash
	#execute the project
	mvn exec:java -Dexec.mainClass=com.jobsity.challenge.App 
	-Dexec.args="path-to-the-input-file"
	```
### Running Unit Tests

- If you want to run tests, run the  `test`  goal. It will run all the tests.

    ```bash
    mvn test
    ```

- You can also run a single test file or a particular method inside a test file as follows.

    ```bash
    mvn test -Dtest=com.mycompany.AppTest#testMethodName

- To run integration tests, use the failsafe plugin and run this command:

    ```bash
       mvn failsafe:integration-test
    ```