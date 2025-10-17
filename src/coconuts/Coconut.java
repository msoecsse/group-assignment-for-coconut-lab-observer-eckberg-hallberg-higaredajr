package coconuts;

import javafx.scene.image.Image;

// Represents the falling object that can kill crabs. If hit by a laser, the coconut disappears
// This is a domain class; other than Image, do not introduce JavaFX or other GUI components here
public class Coconut extends HittableIslandObject {
    private static final int WIDTH = 50;
    private static final Image coconutImage = new Image("file:images/coco-1.png");

    public Coconut(OhCoconutsGameManager game, int x) {
        super(game, x, 0, WIDTH, coconutImage, HitEvents.NULL_EVENT);
    }

    @Override
    public void step() {
        y += 5;
    }

    @Override
    protected int hittableHeight() {
        return y + width;
    }

    // we dont use this because when the coconut hits the beach its a hitevent
    @Override
    public boolean isFalling() {
        return true;
    }
}
