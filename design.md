# Boatclub OO-Design
This document describes the design according to the requirements presented in assignment 2.

## Architectural Overview
The application uses the model-view-controller (MVC) architectural pattern. The view is passive and gets called from the controller. The view may only read information from the model, not directly change it.

![class diagram](img/package_diagram.jpg)

## Detailed Design
### Class Diagram
Please provide at least one class diagram according to the assignment requirments.

### Sequence Diagram
The selected `Add member` from the `Member menu` in the system and the sequence that follows is illustrated below.

![sequence diagram](img/sequence-diagram.jpeg)

### Object Diagram
The diagram corresponds to the scenario above just **after** the illustrated sequence. <br>
(The `red link-lines` indicates that the lifetime of the object is limited to the sequence.)

![sequence diagram](img/object-diagram.jpeg)


