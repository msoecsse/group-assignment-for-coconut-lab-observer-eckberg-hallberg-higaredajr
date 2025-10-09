# Coconut Observer Lab

## Observer & Subject Details
We need to, "Introduce the Observer Pattern to handle one object hitting another. 
In particular, create a subject class that responds to hit events and multiple observer 
classes that capture the effects of various hit events."
I think the subject be the **game manager**, because that's the class that manages hit events.
This feels wierd because I thought we were gonna apply the pattern to the domain objects, but
I can't think of a way to do that that doesnt massively decrease cohesion?  

We have the scoreboard object as an observer, 
and another observer that removes objects

Scoreboard Game Observer: The scoreboard needs to encapsulate scoreboard state
* Needs to take in the GUI components in the constructor so it can update them
* It will be instantiated within the controller within the init() method &

### Capturing Interactions:
The canHit() and isTouching() methods in the IslandObject are called to check if things have
interacted in the OhCoconutsGameManager hit detection loop (line 88).

We can use the HitEvent class within the if() block which we can implement to process the following hits:
**This is where we can get the subjects to update the observer?**
* Coconut interacting with the laser that hits it
* Coconut hitting the ground
* Coconut hitting crab & ending game 
The HitEvent receives the OhCoconutsGameManager instance in its constructor.
It will then call OhCoconutsGameManager methods that update the observers

## TODO:
* Build out the ScoreBoard, implement update() and add the JavaFX elements to the Controller
* Implement the HitEvent class to handle the proper hit events
* Have the OhCoconutsGameManager implement the subject interface and its methods
* Implement an observer that servers to remove Island objects 
(for example when a laser hits a coconut the coconut needs to be removed)

## Questions
* Does it make sense for the OhCoconutsGameManager to implement the subject interface?

