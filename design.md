# Boatclub OO-Design
This document describes the design according to the requirements presented in assignment 2.

## Architectural Overview
The application uses the model-view-controller (MVC) architectural pattern. The view is passive and gets called from the controller. The view may only read information from the model, not directly change it.

![class diagram](img/package_diagram.jpg)

## Detailed Design
### Class Diagram
Please provide at least one class diagram according to the assignment requirments.

### Sequence Diagram
TODO: Fix sequence diagram.
title controller::MainController.addMember

"mm:controller::MainController"->+"view:view::Console":promptForNewMember()
"view:view::Console"->*"newMember:model::domain::Member": newMember := create(firstName, lastName, email, phoneNumber)
"view:view::Console"-->-"mm:controller::MainController": newMember

"mm:controller::MainController"->+"sls:model::domain::StuffLendingSystem":addNewMember(newMember)
"sls:model::domain::StuffLendingSystem"->+"newMember:model::domain::Member":getEmail()
"newMember:model::domain::Member"->-"sls:model::domain::StuffLendingSystem":email

"sls:model::domain::StuffLendingSystem"->+"newMember:model::domain::Member":getPhoneNumber()
"newMember:model::domain::Member"->-"sls:model::domain::StuffLendingSystem":phoneNumber

"sls:model::domain::StuffLendingSystem"->+"members:model::domain::MemberCollection":isUniqueEmailAndPhoneNumber(email, phoneNumber)
"members:model::domain::MemberCollection"->-"sls:model::domain::StuffLendingSystem":isUniqueEmailAndPhoneNumber

alt isUniqueEmailAndPhoneNumber
    "sls:model::domain::StuffLendingSystem"->"sls:model::domain::StuffLendingSystem":getNewUniqueMemberId
    
    loop while !isUniqueMemberId
    "sls:model::domain::StuffLendingSystem"->+"randomStringGenerator:model::domain::RandomString":getAlphanumeric(6)
    "randomStringGenerator:model::domain::RandomString"->-"sls:model::domain::StuffLendingSystem":alphanumericId
    "sls:model::domain::StuffLendingSystem"->+"members:model::domain::MemberCollection":isUniqueMemberId(alphanumericId)
    "members:model::domain::MemberCollection"->-"sls:model::domain::StuffLendingSystem":isUniqueMemberId
    end
    
    "sls:model::domain::StuffLendingSystem"->+"newMember:model::domain::Member":getFirstName()
    "newMember:model::domain::Member"->-"sls:model::domain::StuffLendingSystem":firstName
    "sls:model::domain::StuffLendingSystem"->+"newMember:model::domain::Member":getLastName()
    "newMember:model::domain::Member"->-"sls:model::domain::StuffLendingSystem":lastName
    "sls:model::domain::StuffLendingSystem"->+"newMember:model::domain::Member":getEmail()
    "newMember:model::domain::Member"->-"sls:model::domain::StuffLendingSystem":email
    "sls:model::domain::StuffLendingSystem"->+"newMember:model::domain::Member":getPhoneNumber()
    "newMember:model::domain::Member"->-"sls:model::domain::StuffLendingSystem":phoneNumber
    
    "sls:model::domain::StuffLendingSystem"->*"mutableMember:model::domain::Member.Mutable": mutableMember := create(firstName, lastName, email, phoneNumber, alphanumericId, currentDay)
    "sls:model::domain::StuffLendingSystem"-->-"members:model::domain::MemberCollection": addMember(mutableMember)

else !isUniqueEmailAndPhoneNumber
    "mm:controller::MainController"->+"view:view::Console":actionResponder(ActionEvent.ERR_CREATE_MEMBER)
end

