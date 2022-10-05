# Stuff Lending Test Report

Version #ea913d68 (git commit hash/link)
Date: 2022.10.04
Environment: Windows, Java version 18.

| **Case** |  **Result**   | **Note**        |
| -------- | :------------ | :-------------- |
|   1.1    | :white-check: |                 |
|   1.2    | :white-check: |                 |
|   1.3    | :white-check: |                 |
|   1.4    | :white-check: |                 |
|   1.5    | :white-check: |                 |
|   1.6    | :white-check: |                 |
|   2.1    | :white-check: |                 |
|   2.2    | :white-check: |                 |
|   2.3    | :white-check: |                 |
|   2.4    | :white-check: |                 |
|   2.5    | :white-check: |                 |
|   3.1    | :white-check: |                 |
|   3.2    | :white-check: |                 |
|   3.3    | :white-check: |                 |
|   3.4    | :white-check: |                 |
|   3.5    | :white-check: |                 |
|   3.6    | :white-check: |                 |
|   4.1    | :white-check: |                 |
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

Requirement: 1.3, 1.4, 1.5

1. Change a Members firstname to Allan.
2. Check that the members firstname is Allan in the members-list.

### 1.5 Edit Member - Conflicting email

Requirement: 1.3, 1.4, 1.6

1. List all members in verbose way.
2. Change a Members email to the exact match of another members email.
2. Check that the email was NOT changed.

### 1.6 Edit Member - Conflicting phonenumber

Requirement: 1.3, 1.4, 1.6

1. List all members in verbose way.
2. Change a Members phonenumber to the exact match of another members phonenumber.
2. Check that the phonenumber was NOT changed.

### 2.1 Create Item

Requirement: 2.1, 2.1.1, 2.4

1. Create an item for a Member
2. Check that the item is created and part of the Members items by inspecting the verbose memberlist.
3. Check that the owner member has increased it's credits with 100

### 2.2 Delete Item - Items without future/active contracts

Requirement: 2.2, 2.4

1. Select a member with one or several items
2. Delete one of the member's items that is not involved in any contract
3. Check that the item was deleted from the members owned items in the verbose member-list

### 2.3 Delete Item - Items with future contract

Requirement: 2.2, 2.4

1. Select a member with one or several items
2. Delete one of the member's items that is booked (i.e. a future contract)
3. Check that the item was NOT from the members owned items
4. Check that the contract was NOT cancelled.

### 2.4 Edit Item - Without future contract

Requirement: 2.3, 2.4

1. Change the (I1) Item cost/day to 40
2. Check that the cost/day was updated for the item

### 2.5 Edit Item - With future contract

Requirement: 2.3, 2.4

1. Change the (I2) Item cost/day to 100
2. Check that the cost/day was updated for the item
3. Check that the contract-fee for the future contract did NOT change (still 30 (10/day))

### 3.1 Create Contract

Requirement: 2.4, 3.1, 3.1.1

1. Create a contract for I2 lending to M2, 3 days of lending, from day 1 to and including day 3
2. Check that the contract was created

### 3.2 Create Contract - Not enough credits

Requirement: 2.4, 3.1, 3.1.1

1. Create a contract for I1 lending to M2, 3 days of lending (e.g. day 1 to and including day 3)
2. Check that the contract was not created due to lack of funds

### 3.3 Create Contract - Conflicting time (stretching into existing contract)

Requirement: 2.4, 3.1, 3.1.2, 3.1.3

1. Create a contract for I2 lending to M2, 3 days of lending, day 4 to and including day 6
2. Check that the contract was NOT created due to conflicting time

### 3.4 Create Contract - Conflicting time (starting during existing contract)

Requirement: 2.4, 3.1, 3.1.2, 3.1.3

1. Create a contract for I2 lending to M2, 3 days of lending, day 6 to and including day 9
2. Check that the contract was NOT created due to conflicting time

### 3.5 Create Contract - Conflicting time (stretching from before to after)

Requirement: 2.4, 3.1, 3.1.2, 3.1.3

1. Create a contract for I2 lending to M2, 6 days of lending, day 4 to and including day 9
2. Check that the contract was NOT created due to conflicting time

### 3.6 Create Contract - Conflicting time (for only one day during existing contract)

Requirement: 2.4, 3.1, 3.1.2, 3.1.3

1. Create a contract for I2 lending to M2, 3 days of lending, day 6 to and including day 6
2. Check that the contract was NOT created due to conflicting time

### 4.1 Advance Time

Requirement: 2.4, 4.1, 4.2

1. Advance the time 8 days.
2. Check that the contract has been fullfilled and that the contract now is in expired contracts-list
3. Check that I2 now is available for loan

### 5.1 Member Data

Requirement: 5

1. Check that there are at least 3 Members.
2. Check that one member (M1) with 500 credits. M1 has two items for lending, I1 with cost 50 one cheap I2 cost 10
3. Check that one member (M2) with 100 credits. M2 has no items for lending.
4. Check that one member (M3) with 100 credits has an active lending contract for I2 that starts on day 5 and ends on day 7 (3 days) for