/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class HittableIslandObject
 * Name: higaredajra
 */
package coconuts;

import javafx.scene.image.Image;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class HittableIslandObject
 * Responsibility: Represents island objects which can be hit
 * Modified by Anthony 10/16/2025
 */
public abstract class HittableIslandObject extends IslandObject {
    public HittableIslandObject(OhCoconutsGameManager game, int x, int y, int width, Image image, HitEvents hitType) {
        super(game, x, y, width, image, hitType);
    }

    @Override
    public boolean isHittable() {
        return true;
    }
}
