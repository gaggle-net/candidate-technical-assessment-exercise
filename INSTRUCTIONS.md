

##Instructions for Running the Person Search Service

---

## Summary of Functionality
Upon clicking submit, the Search Service will perform a person search using the criteria that you entered in this field.

If a number is entered, the service will search for any records with that number as it's Primary Key.
If text is entered, the service will search for any name that matches or partially matches that text.


## Setting up the Service Demo
instructions for setting up...

more instructions for setting up...

still yet even more instructions for setting up, should we need such detail...

Include links to any downloads that may be necessary like this: [veri forma
modo](http://secum.com/dentes) and include brief instructions if necessary.



## Using the Interface
You may enter either a number, a name or a portion of a name, and then click the **submit** button.



---

Default Demo Data:

    // here's what's included in the embedded table by default:

    *person_id*   *first_name*  *last_name*    *full_name*
        200        Tommy         Schaeffer      Tommy Schaeffer
        400        Thomas        Schaeffer      Thomas Schaeffer, Sr.
        700        Bruce         Wayne          Bruce Wayne
        800        Thomas        Newman         Thomas Newman
        900        Isambard      Brunel         Isambard Brunel
        *1000       Leonardo      D'Vinci        Leonardo D'Vinci
        1100       Jòsé          Cuervo         Jòsé Cuervo

    Note: as of release version 1.0, record at id 1000 is not included.
    -------------------------------------------------------------------


    statement.executeUpdate("create table person (person_id integer, first_name string, last_name string, full_name string)");
    statement.executeUpdate("insert into person values(200, 'Tommy', 'Schaeffer', 'Tommy Schaeffer')");
    statement.executeUpdate("insert into person values(400, 'Thomas', 'Schaeffer', 'Thomas Schaeffer, Sr.')");
    statement.executeUpdate("insert into person values(700, 'Bruce', 'Wayne', 'Bruce Wayne')");
    statement.executeUpdate("insert into person values(800, 'Thomas', 'Newman', 'Thomas Newman')");
    statement.executeUpdate("insert into person values(900, 'Isambard', 'Brunel', 'Isambard Brunel')");
    //statement.executeUpdate("insert into person values(1000, 'Leonardo', 'D'Vinci', 'Leonardo D'Vinci')");
    statement.executeUpdate("insert into person values(1100, 'Jòsé', 'Cuervo', 'Jòsé Cuervo')");




---


## Assumptions, Concerns, Boundary Conditions, etc.
Corripiunt fuit mortis, solum **stagnum**, tot nepos atria arbor: eras inermia
poposcerat *indoluit*, cetera! Inter resolvo et zephyri quae animo quaerensque
et regem stellantibus metuit Alcimedon superabat ferae praequestus quondam?
Latura Terror, accensae, forte orbem armiferos mearum, nomina spargit lydia


---

