### Git Code Challenge
Requires Java 1.8. 
Clone this repo and run the api from source or by executing the jar. 
By default, this app returns  0:5 followers at 0:3 levels deep. You can change this behavior
in the `Application`. Configurations can range from 0:N followers at 0:N levels deep.

#### From Source

Just run the `Application` file.

#### From Maven
Run `mvn package`. Go to the target directory and run `java -jar git-challenge-1.0-jar-with-dependencies.jar`

#### Accessing the API
When the application is running, you can GET
`http://localhost:8080/followers/{gitUsername}`
 to retrieve the followers for the given user.
 
 Ex: <http://localhost:8080/followers/kevinstl>