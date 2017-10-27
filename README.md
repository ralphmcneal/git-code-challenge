### Git Code Challenge
Requires Java 1.8. 
Clone this repo and run the api from source or by executing the jar.

#### From Source

Just run the `Application` file.

#### From Maven
Run `mvn package`. Go to the target directory and run `java -jar git-challenge-1.0-jar-with-dependencies.jar`

#### Accessing the API
When the application is running, you can GET
`http://localhost:8080/followers/{gitUsername}`
 to retrieve the followers for the given user.
 
 Ex: <http://localhost:8080/followers/kevinstl>