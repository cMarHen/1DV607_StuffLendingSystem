# Stuff Lending Test Report

Version #ea913d68 (git commit hash/link)
Date: 2022.10.04
Environment: Windows, Java version 18.

| **Case** |  **Result**   | **Note**        |
| -------- | :------------ | :-------------- |
|   1.1    | :white-check: |                 |
|   1.2    | :white-check: |                 |
|   1.3    | :white-check: |                 |
|   1.4    |               |                 |
|   2.1    |               |                 |
|   2.2    |               |                 |
|   2.3    |               |                 |
|   3.1    |               |                 |
|   3.2    |               |                 |
|   3.3    |               |                 |
|   4.1    |               |                 |
|   5.1    | :white-check: |                 |


## Test Cases

<br>


### 1.1 Create Member

Requirement: 1.1, 1.1.1, 1.4

1. Create a member with firstname: "Allan", lastname: "Turing", email: "allan@enigma.com", phone: "123456"
2. Check that the member is created correctly with an id according to the requirement by checking the members full information.
3. Quit the application


### 1.2 Create Member - Duplicate Email and Phone

Requirement: 1.1, 1.1.1, 1.1.2, 1.4

1. Create a member with firstname: "Allan", lastname: "Turing", email: "allan@enigma.com", phone: "123456"
2. Check that the member is created correctly with an id according to the requirement by checking the members full information.
3. Create a member with firstname: "Allan", lastname: "Gritun", email: "allan@enigma.com", phone: "123"
4. Check that the member is not created (duplicate email)
5. Create a member with firstname: "Llana", lastname: "Turing", email: "turing@enigma.com", phone: "123456"
6. Check that the member is not created (duplicate phone)
7. Create a member with firstname: "Llana", lastname: "Turing", email: "turing@enigma.com", phone: "123"
8. Check that the member is created correctly with an id according to the requirement by checking the members full information.
9. Quit the application

### 1.3 Delete Member

Requirement: 1.1, 1.1.1, 1.1.2, 1.2, 1.4

1. Create a member with firstname: "Allan", lastname: "Turing", email: "allan@enigma.com", phone: "123456"
2. Check that the member is created correctly with an id according to the requirement by checking the members full information.
3. Delete the member
4. Check that the member is deleted by listing all members (simple)
5. Create a member with firstname: "Allan", lastname: "Turing", email: "allan@enigma.com", phone: "123456"
6. Check that the member is created correctly with an id according to the requirement by checking the members full information.
8. Quit the application.

### 1.4 Edit Member

Requirement: 1.3, 1.4, 1.5, 1.6

1. ...

### 2.1 Add Item

Requirement: 2.1, 2.1.1, 2.4

1. ...

### 2.2 Delete Item

Requirement: 2.2, 2.4

1. ...

### 2.3 Edit Item

Requirement: 2.3, 2.4

1. ...

### 3.1 Lend Item - Lend Available Item From Current Day

Requirement: 2.4, 3.1, 3.1.1

1. ...

### 3.2 Lend Item - Lend Available Item From Future Day

Requirement: 2.4, 3.1, 3.1.1, 4.1, 4.2

1. ... Forward time to see item reserved ...

### 3.3 Lend Item - Lend Reserved and Too Expensive Item

Requirement: 2.4, 3.1, 3.1.2, 3.1.3

1. ...

### 4.1 Forward Time

Requirement: 2.4, 4.1, 4.2

1. ... Forward time to see preloaded item reserved ...

### 5.1 Member Data

Requirement: 5

1. Check that there are at least 3 Members.
2. Check that one member has two items for lending.
3. Check that one member has no items for lending.
4. Check that one member has an active lending contract that starts on day 5 and ends on day 7
