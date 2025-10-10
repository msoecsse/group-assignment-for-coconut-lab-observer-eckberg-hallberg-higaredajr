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
 * Class ScoreBoard Purpose: implements the scoreboard; implements the observer interface
 *
 * @author eckbergj
 * @version created on 10/9/2025 11:29 AM
 */
public class ScoreBoard implements GameObserver {

    private Label landCount;
    private Label shotCount;
    private int CoconutsOnGround;
    private int CoconutsShot;
    // hold the game state (score, lives, etc.)
    public ScoreBoard(Label landCountLabel, Label shotCountLabel) {
        this.landCount = landCountLabel;
        this.shotCount = shotCountLabel;
    }

    // This function needs to take in HitEvents and translate them to changes in the scoreboard
    @Override
    public void update(HitEvent hit) {

    }
}
    
