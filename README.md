# A2

Project for Assignment 2


[design.md](design.md) contains the prescribed architectural design of the application.


## Running
The application should start by running console command:  
`./gradlew run -q --console=plain`

## Menu navigation

### 1. Main menu 
    1 - Member Menu => Go to member menu
    2 - Item Menu => Go to item menu
    3 - Forward day => Forward day counter
    0 - Quit => Exit application

### 2. Member menu 
     1 - Add member => Add a new member
     2 - List members simple => List all members in a simple way
     3 - List members verbose => List all members in a verbose way
     4 - Show detailed member => (req id) Show one member detailed 
     5 - Edit a member => (req id) Edit a member via EditMemberMenu
     6 - Delete member => (req id) Delete a member
     0 - Back => (goto MainMenu) => Go back

### 3. Item menu
     1 - Add new item => Add a new item
     2 - List items => List all items
     3 - Show detailed item => (req id) Show one item detailed
     4 - Edit item => (req id) Edit an item via EditItemMenu
     5 - Lend item => (req item id, req lender id) Lend an item
     6 - Delete item => (req id) Delete an item
     0 - Back => Go back

### 4. EditMemberMenu
     1 - Edit first name => Edit first name
     2 - Edit last name => Edit last name
     3 - Edit email => Edit email
     4 - Edit phone number => Edit phone number
     0 - Back => Go back

### 5. EditMemberMenu
     1 - Edit name => Edit name
     2 - Edit description => Edit description
     3 - Edit email => Edit email
     4 - Edit cost per day => Edit cost per day
     0 - Back => Go back


## Versioning

Adhere to the git versioning instructions according to the assignment.

## System test
[testreport.md](testreport.md) contains test report of the system.
