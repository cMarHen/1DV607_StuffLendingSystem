# A2

Project for Assignment 2

A project template based on gradle and a gitlab pipeline. You should always build and run the application using gradle regularely.

[design.md](design.md) contains the prescribed architectural design of the application.

## Building
The build must pass by running console command:  
`./gradlew build`  
Note that you should get a report over the quality like:
```
CodeQualityTests > codeQuality() STANDARD_OUT
    0 CheckStyle Issues in controller/App.java
    0 CheckStyle Issues in controller/Simple.java
    0 CheckStyle Issues in model/Simple.java
    0 CheckStyle Issues in view/Simple.java
    0 FindBugs Issues in controller/App.java
    0 FindBugs Issues in model/Simple.java
    0 FindBugs Issues in view/Simple.java
    0 FindBugs Issues in controller/Simple.java
```

Removing or manipulating the code quality checks results in an immediate assignment **Fail**. 

## Running
The application should start by running console command:  
`./gradlew run -q --console=plain`

## Usage

1. Main menu 
    > `1 - Member Menu` => Go to member menu
    >
    > `2 - Item Menu` => Go to item menu
    >
    > `3 - Forward day` => Forward day counter
    >
    > `0 - Quit => Exit` application

2. Member menu 
    > `1 - Add member` => Add a new member
    >
    > `2 - List members simple` => List all members in a simple way
    >
    > `3 - List members verbose` => List all members in a verbose way
    > 
    > `4 - Show detailed member` => (req id) Show one member detailed 
    >
    > `5 - Edit a member` => (req id) Edit a member via EditMemberMenu
    > 
    > `6 - Delete member` => (req id) Delete a member
    >
    > `0 - Back` => (goto MainMenu) => Go back


## Adding Your Own Code
The `Simple` classes project should likely be removed do not forget to also remove the test case associated to `model.Simple`.  

Add your own code to the packages respectively and feel free to add automatic test cases for your own code. A good process is to design a little - code a little - test a little one feature at a time and then iterate.

## Versioning

Adhere to the git versioning instructions according to the assignment.

## System test
Adhere to the instructions according to the assigment.

## Handing In
Adhere to the instructions according to the assigment.