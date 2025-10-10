# Coconut Observer Lab

[Link to lab #6:](https://faculty-web.msoe.edu/hasker/swe2410/labs/6/)

## Observer & Subject Details
We need to, "Introduce the Observer Pattern to handle one object hitting another. 
In particular, create a subject class that responds to hit events and multiple observer 
classes that capture the effects of various hit events."
I think the subject should be the **game manager**, because that's the class that manages hit events.
This feels wierd because I thought we were gonna apply the pattern to the domain objects, but
I can't think of a way to do that, that doesnt massively decrease cohesion?  

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
  * We just need to link text boxes to the scoreboard via stamp? coupling
* Implement the HitEvent class to handle the proper hit events
* Have the OhCoconutsGameManager implement the subject interface and its methods
* Implement an observer that servers to remove Island objects 
(for example when a laser hits a coconut the coconut needs to be removed)

## Questions
* Does it make sense for the OhCoconutsGameManager to implement the subject interface?
* Would the HitEvent be better to implement the subject interface? But then we would have to attach
and detach subjects a bunch as the objects are created & deleted

My main concern here is that the OhCoconutsGameManager is the only object that has full knowledge of
the HitEvents because its the one that creates it. If we just send the HitEvent out to the observers via 
the OhCoconutsGameManager? The only hitch in this is that the OhCoconutsGameManager seems to be the one creating 
and deleting the objects?

