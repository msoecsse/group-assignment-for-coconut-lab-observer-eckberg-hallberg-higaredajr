/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class IslandObject
 * Name: eckbergj
 */
package coconuts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// an object in the game, either something coming from the island or falling on it
// Each island object has a location and can determine if it hits another island object
// This is a domain class; do not introduce JavaFX or other GUI components here

/**
 * Responsibility: An object in the game, either something coming from the island or falling on it
 * Modified by John Eckberg 10/17/2025
 */
public abstract class IslandObject {
    protected final int width;
    protected final OhCoconutsGameManager containingGame;
    protected int x, y;
    ImageView imageView = null;
    protected final HitEvents hitType;

    // why is it x,y here and height and width here in the game manager?
    //  when we actually instantiate the sub classes, we swap the x & y before it goes into the super
    public IslandObject(OhCoconutsGameManager game, int x, int y, int width, Image image, HitEvents hitType) {
        containingGame = game;
        if (image != null) {
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(width);
        }
        this.x = x;
        this.y = y;
        this.width = width;
        display();
        this.hitType = hitType;
        //System.out.println(this + " left " + left() + " right " + right());
    }

    protected ImageView getImageView() {
        return imageView;
    }

    public void display() {
        if (imageView != null) {
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
        }
    }

    public boolean isHittable() {
        return false;
    }

    protected int hittableHeight() {
        System.out.println("Warning: Hittable Height is not overridden");
        System.out.println(hitType);
        return 0;
    }

    public boolean isGroundObject() {
        return false;
    }

    public boolean isFalling() {
        return false;
    }

    public boolean isRising() {
        return false;
    }

    public boolean canHit(IslandObject other) {
        return other.isHittable();
    }

    public boolean isTouching(IslandObject other) {


        int thisHittableWidth = this.x + this.width;
        int otherHittableWidth = other.x + other.width;

        // the left edge of this object must be to the left of the right edge of the other object
        boolean xOverlap = (this.x < otherHittableWidth)
        // and, the right edge of this object must be to the right of the left edge of the other object
                && (thisHittableWidth > other.x);

        // the top edge of this object is above the bottom edge of the other object
        boolean yOverlap = (this.y < other.hittableHeight())
        // and the bottom edge of this object is below the top edge of the other object
                && (this.hittableHeight() > other.y);

        // collision occurs only if there is overlap on both axises.
        return xOverlap && yOverlap;
//        return (other.hittableHeight() == hittableHeight() &&
//                other.x + (other.width / 2) >= x && other.x + (other.width * 2) <= x + width);
    }

    public abstract void step();

    public HitEvents getHitType() {
        return hitType;
    }
}
