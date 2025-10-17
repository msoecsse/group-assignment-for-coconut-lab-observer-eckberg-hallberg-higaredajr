# Coconut Observer Lab

[Link to lab #6:](https://faculty-web.msoe.edu/hasker/swe2410/labs/6/)

## Observer & Subject Details
We need to, "Introduce the Observer Pattern to handle one object hitting another. 
In particular, create a subject class that responds to hit events and multiple observer 
classes that capture the effects of various hit events."
I think the subject should be the **game manager**, because that's the class that manages hit events.
This feels wierd because I thought we were gonna apply the pattern to the domain objects, but
I can't think of a way to do that, that doesn't massively decrease cohesion?

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
It will then call OhCoconutsGameManager methods that update the observers?

## TODO:
1. Build out the ScoreBoard, implement observer interface DONE
  * We just need to link text boxes to the scoreboard via stamp? coupling, or add getters and setters to controller
  * add the JavaFX elements to the Controller Done?
  * Implement update method
  * Add scoreboard to game manager

2. Implement the HitEvent class to handle the proper hit events DONE (Enum)

3. Have the OhCoconutsGameManager implement the subject interface DONE
  * Implement attach method
  * Implement detatch method
  * Implement notifyAll method

4. Manage item deletion inside game manager when interacted with DONE
  * This just involves adding the correct items to the scheduledForRemoval list
  * Includes when the coconuts get hit by the laser

5. Remove game objects that have left the screen DONE

* I think that the UML is done? (Nathan Halberg)
* I think the Sequence Diagram is done? (Anthony Higareda Jr.)
* Report incomplete (John Eckberg)


## TODOS/Bug fixes
* Add a pop up for game end?
* Had to add some magic numbers related to dimension I would like removed
* Why are the coconuts disappearing before they actually hit the ground? DONE 
* Theres a wierd dimension swap going on between island object and the game manager
* Any improvements to reduce coupling and improve cohesion
* Come up with test Scenarios 
* Game manager is 100% just a do it class
* Hit Boxes are still too big
* Headers/javadocs meet lab specs (modified by, etc)
* The crab can run off the edge of the map (DONE?)


## Notes
* So a hit event will be generated if a object that can hit (crab and laser and ground) hits a hittable object (coconut)
  * This hit event will be sent to the observer
  * So the notify method for the subject takes in a hit event and then calls the update method of the
  scoreboard & passes a boolean into the update. The update method then uses that boolean to determine 
  which of the two fields in the scoreboard it needs to change (coconut hit ground or coconut hit laser)
* Game end observer 

**only thing that's hittable is the coconut** **the only things that can hit would be the ground, crab, and laser**


