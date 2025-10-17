package coconuts;

// https://stackoverflow.com/questions/42443148/how-to-correctly-separate-view-from-model-in-javafx

import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;

// This class manages the game, including tracking all island objects and detecting when they hit
public class OhCoconutsGameManager implements Subject {
    private final Collection<IslandObject> nonHittableObjects = new LinkedList<>();
    private final Collection<HittableIslandObject> hittableIslandSubjects = new LinkedList<>();
    private final Collection<IslandObject> scheduledForRemoval = new LinkedList<>();
    private final Collection<GameObserver> observers = new LinkedList<>();
    private final int height, width;
    private final int DROP_INTERVAL = 10;
    private final int MAX_TIME = 100;
    private Pane gamePane;
    private Crab theCrab;
    private Beach theBeach;
    /* game play */
    private int coconutsInFlight = 0;
    private int gameTick = 0;

    public OhCoconutsGameManager(int height, int width, Pane gamePane) {
        this.height = height;
        this.width = width;
        this.gamePane = gamePane;

        // TODO why do we swap height and width? height and width here and x,y in the islandobject??
        this.theCrab = new Crab(this, height, width);
        registerObject(theCrab);
        gamePane.getChildren().add(theCrab.getImageView());

        this.theBeach = new Beach(this, height, width);
        registerObject(theBeach);
        if (theBeach.getImageView() != null)
            System.out.println("Unexpected image view for beach");
    }

    private void registerObject(IslandObject object) {
        // TODO it might make sense to have one list of hittable
        // and one list of non hittable, instead of one list of all objects
        // and one list of hittable objects
        if (object.isHittable()) {
            HittableIslandObject asHittable = (HittableIslandObject) object;
            hittableIslandSubjects.add(asHittable);
        } else {
            nonHittableObjects.add(object);
        }
    }

    public void shootLaser() {
        LaserBeam laser = new LaserBeam(this, getCrab().hittable_height(),
                getCrab().x + (getCrab().width / 2));
        registerObject(laser);
        gamePane.getChildren().add(laser.getImageView());
        laser.display();
        //System.out.println(AssertNotNull(laser));
        System.out.println("laser");
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void coconutDestroyed() {
        coconutsInFlight -= 1;
    }

    public void tryDropCoconut() {
        if (gameTick % DROP_INTERVAL == 0 && theCrab != null) {
            coconutsInFlight += 1;
            Coconut c = new Coconut(this, (int) (Math.random() * width));
            registerObject(c);
            gamePane.getChildren().add(c.getImageView());
        }
        gameTick++;
    }

    public Crab getCrab() {
        return theCrab;
    }

    public void killCrab() {
        theCrab = null;
    }

    public void advanceOneTick() {
        for (IslandObject o : hittableIslandSubjects) {
            o.step();
            o.display();
        }
        for (IslandObject o : nonHittableObjects) {
            o.step();
            o.display();
        }
        // you can't change the lists while processing them, so collect
        //   items to be removed in the first pass and remove them later
        scheduledForRemoval.clear();
        for (IslandObject thisObj : nonHittableObjects) {
            for (HittableIslandObject hittableObject : hittableIslandSubjects) {
                if (thisObj.canHit(hittableObject) && thisObj.isTouching(hittableObject)) {
                    // TODO: add code here to process the hit
                    // the goal here is that only the coconut is a hittable object
                    // This involves using the notify method to call the observers update method
                    // and pass the hit event to the observer(s) (only the scoreboard)
                    // we do this by getting the hit type of the non hittable
                    // add the right objects to the scheduledForRemoval
                    // The only hittable objects are the coconut instances
                    // The crab, laser and beach can hit the coconut, but cannot be hit themselves
                    // We also need to ensure based in the hit type that we remove/keep the non
                    // hittable object hitting the hittable object
                    // so when the crab hits the coconut, remove both
                    // when the laser hits the coconut, remove both
                    // when the beach hits the coconut, remove only the coconut

                    scheduledForRemoval.add(hittableObject); // this should use the method below
                    gamePane.getChildren().remove(hittableObject.getImageView());
                }
            }

        }
        // actually remove the objects as needed
        // we would need to update this if we switched list setup
        // This would need to remove from non-hittable list and hittable list
        for (IslandObject thisObj : scheduledForRemoval) {
            // TODO fix instance of?
            if (!thisObj.isHittable()) {
                nonHittableObjects.remove(thisObj);
            }
            if (thisObj instanceof HittableIslandObject) {
                hittableIslandSubjects.remove((HittableIslandObject) thisObj);
            }
        }
        scheduledForRemoval.clear();
    }

    public void scheduleForDeletion(IslandObject islandObject) {
        scheduledForRemoval.add(islandObject);
    }

    public boolean done() {
        return coconutsInFlight == 0 && gameTick >= MAX_TIME;
    }

    @Override
    public void attach(GameObserver ob) {
        observers.add(ob);
    }

    @Override
    public void detach(GameObserver ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObservers(HitEvents hitType) {
        for (GameObserver ob : observers) {
            ob.update(hitType, this);
        }
    }
}
