# A2

### Contributors:

Martin Henriksson - mh225wd

Anders Jonsson - aj224rj

---

Project for Assignment 2

### Contributors:

Martin Henriksson - mh225wd

Anders Jonsson - aj224rj

---

[design.md](design.md) contains the prescribed architectural design of the application.


## Running
The application should start by running console command:  
`./gradlew run -q --console=plain`

## Menu navigation

---
### NOT LOGGED IN
---

### 1. Main menu 
    1 - Member Menu => Go to member menu
    2 - Item Menu => Go to item menu
    3 - Log in => Enter credentials
    4 - Register => Add new member
    0 - Quit => Exit application

### 2. Member menu 
     1 - List members simple => List all members in a simple way
     2 - List members verbose => List all members in a verbose way
     3 - Show detailed member => (req id) Show one member detailed 
     0 - Back => (goto MainMenu) => Go back

### 3. Item menu
     1 - List items => List all items
     2 - Search items => Search on name and type
     3 - Show detailed item => (req id) Show one item detailed
     0 - Back => Go back

---
### LOGGED IN
---

### 1. Main menu 
    1 - Member Menu => Go to member menu
    2 - Item Menu => Go to item menu
    3 - Forward day => Forward day counter
    4 - Log out => Log out
    0 - Quit => Exit application

### 2. Member menu 
     1 - List members simple => List all members in a simple way
     2 - List members verbose => List all members in a verbose way
     3 - Show detailed member => (req id) Show one member detailed 
     4 - Edit profile => Edit logged in member via EditMemberMenu
     5 - Delete Your Account => Delete logged in member
     0 - Back => (goto MainMenu) => Go back

### 3. Item menu
     1 - Add new item => Add a new item to logged in member
     2 - List items => List all items
     3 - Search items => Search on name and type
     4 - Show detailed item => (req id) Show one item detailed
     5 - Edit item => (req id) Edit logged in members item via EditItemMenu
     6 - Lend item => (req item id) Lend an item as logged in member
     7 - Delete item => (req id) Delete the logged in members item
     0 - Back => Go back

### 4. EditMemberMenu
     1 - Edit first name => Edit first name
     2 - Edit last name => Edit last name
     3 - Edit email => Edit email
     4 - Edit phone number => Edit phone number
     0 - Back => Go back

### 5. EditItemMenu
     1 - Edit name => Edit name
     2 - Edit description => Edit description
     3 - Edit cost per day => Edit cost per day
     0 - Back => Go back



## Versioning

Adhere to the git versioning instructions according to the assignment.

## System test
[testreport.md](testreport.md) contains test report of the system.
