package coconuts;

// https://stackoverflow.com/questions/42443148/how-to-correctly-separate-view-from-model-in-javafx

import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

/** This class manages the game, including tracking all island objects and detecting when they hit
 * Modified by Anthony, John
 */
public class OhCoconutsGameManager implements Subject {
    private final Collection<IslandObject> nonHittableObjects = new LinkedList<>();
    private final Collection<HittableIslandObject> coconuts = new LinkedList<>();
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
        // one list of hittable, one list of non hittable
        if (object.isHittable()) {
            HittableIslandObject asHittable = (HittableIslandObject) object;
            coconuts.add(asHittable);
        } else {
            nonHittableObjects.add(object);
        }
    }

    public void shootLaser() {
        LaserBeam laser = new LaserBeam(this, getCrab().hittableHeight(),
                getCrab().x + (getCrab().width / 2));
        registerObject(laser);
        gamePane.getChildren().add(laser.getImageView());
        laser.display();
        //System.out.println(AssertNotNull(laser));
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
        System.out.println("Killing crab");
        theCrab = null;
    }

    public void advanceOneTick() {
        for (IslandObject o : coconuts) {
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

            // check to see if laser is in skybox
            if(thisObj.isRising()){
                if(thisObj.y <= 0){
                    scheduledForRemoval.add(thisObj);
                    gamePane.getChildren().remove(thisObj.getImageView());
                    break;
                }
            }

            for (HittableIslandObject hittableObject : coconuts) {
                if (thisObj.canHit(hittableObject) && thisObj.isTouching(hittableObject)) {
                    System.out.println("hit registered");

                    // the goal is that only the coconut is a hittable object
                    // the non-hittable object (thisObj) determines the type of hit
                    HitEvents hitType = thisObj.getHitType();
                    System.out.println(hitType);

                    notifyObservers(hitType);

                    // automatically schedule coconut for deletion & decrement coconut count
                    scheduleForDeletion(hittableObject);
                    gamePane.getChildren().remove(hittableObject.getImageView());
                    coconutDestroyed();

                    // handle the non-hittable object (thisObj) removal
                    // beach doesn't need to be handled here because the coconut already got removed
                    // that could be moved back into the if statement if people think it improves readability
                    if (hitType == HitEvents.LASER) {
                        // for laser, remove both
                        scheduleForDeletion(thisObj);
                        gamePane.getChildren().remove(thisObj.getImageView());
                    } else if (hitType == HitEvents.CRAB) {
                        // for crab hits the coconut, remove both & call kill crab
                        killCrab();
                        scheduleForDeletion(thisObj);
                        gamePane.getChildren().remove(thisObj.getImageView());
                    }
                }
            }

        }
        for (IslandObject thisObj : scheduledForRemoval) {
            if (!thisObj.isHittable()) {
                nonHittableObjects.remove(thisObj);
            }else{
                coconuts.remove((HittableIslandObject) thisObj);
            }
        }
        scheduledForRemoval.clear();
    }

    public void scheduleForDeletion(IslandObject islandObject) {
        scheduledForRemoval.add(islandObject);
    }


    public boolean done() {
        return coconutsInFlight == 0 && gameTick >= MAX_TIME || theCrab == null;
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
