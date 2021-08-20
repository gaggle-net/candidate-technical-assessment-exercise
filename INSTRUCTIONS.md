#Instructions for the Person Search Service


---


## Summary of Functionality
This demo web service presents you with an index page containing a single input field.
Upon clicking submit, the Search Service will perform a person search using the criteria that you entered.

If a number is entered, the service will search for and return a JSON formatted string for any records that have that number as its Primary Key.
There should only be one, but the interface is built to return any and all matches, leaving the data integrity to be handled where it should be.

If text is entered, the service will search for and return a JSON formatted string for any name (first or last) that matches--or partially matches--that text.


## Setting up the Service Demo
The demo was created with love using the IntelliJ IDE and a Maven project build structure (see **pom.xml** for specific dependencies, of course).
Development was done using **Java SDK version 16.0.2**.


Out of the box, it uses an embedded MSQLite database and Jetty server.
The demo data is created and inserted into the demo table when the concrete implementation of the database is established.
As of version 1.0, there is not yet a way to add your own test data (other than modifying the code).


Additionally, the design submitted could support a different data source, if you wish, but a new concrete implementation of this data source would be up to the developer adding it.
Once added, however, you would simply modify the dependency injections done in the SearchContainer at the top of the signal chain and everything should fall into place...



<br>

###To set up the Search Service:


---
Fork this repository or download the .zip and extract it into your default IntelliJ workspace.
This document assumes you have IntelliJ Idea and Maven installed.
If you do not have Maven installed, you can [get started here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).
If you do not have an IDE, I recommend [starting with IntelliJ](https://www.jetbrains.com/help/idea/getting-started.html).
Then, once you've got your environment and project all set up, simply run:

    mvn clean install

This will build your dependency tree, run all the unit tests, package up and deploy the project to the Jetty server. 
If you want to run the tests alone, you can also do so with the command:

    mvn clean test


Once you run the `mvn clean install` and see a BUILD SUCCESS, then you are ready to start the Jetty server with:

    mvn jetty:run

Finally, you should see **"[INFO] Started Jetty Server"** in the console.  Now just open your favorite browser and head to:

    http://localhost:8080/

Enter your search criteria into the form field provided and click submit.
(Or just hit < enter >).  Of course if you want to skip the form push some parameters via your own 
URL directly, the format we're looking to conform to here is:

    http://localhost:8080/SearchService/search/searchForPerson?searchCriteria=<param>

You are ready to search!!


<br><br>

## Using the Interface
You may enter either a number, a name or a portion of a name, and then click the **submit** button.

For best results, your entry should appear in the demo data below :).
However, the service is built to return **"Invalid Input Received"** for most invalid inputs as well 
as **"No Results Found"** for any case where no records were found for a valid (validly formed) input. 



---

Default Demo Data:

    // here's what's included in the embedded table by default:

    *person_id*   *first_name*  *last_name*    *full_name*
        200        Tommy         Schaeffer      Tommy Schaeffer
        400        Thomas        Schaeffer      Thomas Schaeffer, Sr.
        700        Bruce         Wayne          Bruce Wayne
        800        Thomas        Newman         Thomas Newman
        900        Isambard      Brunel         Isambard Brunel
        1000       Leonardo      D'Vinci        Leonardo D'Vinci
        1100       Jòsé          Cuervo         Jòsé Cuervo
    -------------------------------------------------------------------





---


## Assumptions, Boundary Conditions, & Considerations
- Developer chose to use `org.json.simple` but would consider refactoring to use `javax.json` if the data processing requirements were more complex, or if it needed to be scalable as such.
- The `regex` in PersonSearch can still be made even more stringent, but with the sacrifice of readability; consider other more maintainable options here.
- _Assumption:_  &nbsp; when a search results in multiple records, the expected behavior is to return all of them, not just the best fit or first match
---

