/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class IslandObjectRemover
 * Name: eckbergj
 * Created 10/21/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class IslandObjectRemover Purpose: Observer that removes Island Objects from the Game Manager
 *
 * @author eckbergj
 * @version created on 10/21/2025 8:07 PM
 */
public class IslandObjectRemover implements GameObserver{

    @Override
    public void update(IslandObject thisObj, IslandObject hittableObject, HitEvents hitType, OhCoconutsGameManager theGame) {

        // automatically schedule coconut for deletion & decrement coconut count
        theGame.scheduleForDeletion(hittableObject);
        theGame.gamePane.getChildren().remove(hittableObject.getImageView());
        theGame.coconutDestroyed();

        // handle the non-hittable object (thisObj) removal
        // beach doesn't need to be handled here because the coconut already got removed
        // that could be moved back into the if statement if people think it improves readability
        if (hitType == HitEvents.LASER) {
            // for laser, remove both
            theGame.scheduleForDeletion(thisObj);
            theGame.gamePane.getChildren().remove(thisObj.getImageView());
        } else if (hitType == HitEvents.CRAB) {
            // for crab hits the coconut, remove both & call kill crab
            theGame.killCrab();
            theGame.scheduleForDeletion(thisObj);
            theGame.gamePane.getChildren().remove(thisObj.getImageView());
        }
    }
}
