# BookingAPITest

BookingAPITest is a sample test framework for the Booking API, written in Java using Maven, Rest-Assured, Cucumber, and JUnit. It includes a set of API requests and corresponding test cases to validate the responses.

### Prerequisites
To run the test suite, you will need to have the following software installed on your machine:

* Java JDK (version 8 or later)
* Maven (version 3.0 or later)
Usage
To run the test suite, execute the following command in the project root directory:

#### Run Test - CukesRunner class
* Test Run class  `src/test/java/booking/runner/CukesRunner`

#### Run Test - Terminal
```
mvn clean test
```

With specific tag
```
mvn clean test -Dcucumber.options="--tags @TagName"
```

### Framework
This test framework is built using the following technologies:

* Java
* Maven
* Rest-Assured
* Cucumber
* JUnit

TestCases Reports store on HTML file.
`/target/cucumber-html-reports` folder.