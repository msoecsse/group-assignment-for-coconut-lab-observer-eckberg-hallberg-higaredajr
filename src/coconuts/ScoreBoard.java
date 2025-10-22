/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class ScoreBoard
 * Name: eckbergj
 * Created 10/9/2025
 */
package coconuts;

import javafx.scene.control.Label;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class ScoreBoard
 * Responsibility: implements the scoreboard; implements the observer interface
 *
 * @author John Eckberg
 * @version created on 10/9/2025 11:29 AM
 * Modified by Anthony 10/17/2025
 */
public class ScoreBoard implements GameObserver {

    private Label shotLabel;
    private Label groundLabel;
    private int coconutsOnGround = 0;
    private int coconutsShot = 0;
    // hold the game state (score, lives, etc.)

    public ScoreBoard(Label groundLabel, Label shotLabel) {
        this.groundLabel = groundLabel;
        this.shotLabel = shotLabel;
    }

    // This function needs to take in HitEvents and translate them to changes in the scoreboard
    @Override
    public void update(IslandObject thisObj, IslandObject hittableObj, HitEvents hitType, OhCoconutsGameManager theGame) {
        switch (hitType){
            case LASER:
                coconutsShot++;
                break;
            case BEACH:
                coconutsOnGround++;
                break;
            case CRAB:
                //theGame.killCrab();
                // so we don't actually have to do anything in here
                // because the game loop handles the object deletion
                // and if we kill the crab here it throws a null pointer?
                // TODO maybe call a function to make a pop-up?
                break;
            case NULL_EVENT:
                break;
        }
        updateLabels();
    }

    // TODO is it better for coupling to pass in the labels or pass in the whole
    //  controller and do setters?
    private void updateLabels(){
        groundLabel.setText(Integer.toString(coconutsOnGround));
        shotLabel.setText(Integer.toString(coconutsShot));
    }
}
    
