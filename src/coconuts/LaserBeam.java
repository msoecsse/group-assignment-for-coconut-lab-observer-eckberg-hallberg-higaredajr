/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class LaserBeam
 * Name: John Eckberg
 */
package coconuts;

import javafx.scene.image.Image;


/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class Laser
 * Responsibility: Represents the beam of light moving from the crab to a coconut; can hit only falling objects
 * Modified by John, Anthony 10/17/2025
 */
public class LaserBeam extends IslandObject {
    private static final int WIDTH = 12; // TODO must be updated with image
    private static final Image laserImage = new Image("file:images/laser-1.png");

    public LaserBeam(OhCoconutsGameManager game, int eyeHeight, int crabCenterX) {
        super(game, crabCenterX, eyeHeight, WIDTH, laserImage, HitEvents.LASER);
    }

    public int hittableHeight() {
        return y + WIDTH;
    }

    @Override
    public void step() {
        y -= 3;
    }

    @Override
    public boolean isRising() {
        return true;
    }
}
