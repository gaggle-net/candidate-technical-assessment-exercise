# Overview
This exercise is meant to showcase your technical abilities.
* Include documentation about your development environment and instructions on how to run your program.
   * The assignment need only execute from a local machine and there are no requirements to deploy the implementation anywhere else.
* Include tests and instructions on how to run the tests.

# Requirements
Please complete the assignment using the following criteria. It is expected that you wil fork this repository in GitHub and share the link with us when complete.

Assume there is a database of your choice (can be relational or non-relational and the use of an embedded database is acceptable) with records that have the following fields:
* Unique identifier
* Name

Write the code for a Web Service that provides two possible operations for a client to call:
* Get a contact by ID
* Search for contacts by name
  * **_Example_**: If the database contains the full name of "Bruce Wayne", then it should be reasonable for the function to return this result given any of the following search strings: "bru", "Bruce", "Wayne", "Bruce Wayne", etc.
* Both the input and output of the operations should be formatted in JSON.
* Unit tests for the provided code is written
* **Bonus:** Leverage dependency injection
