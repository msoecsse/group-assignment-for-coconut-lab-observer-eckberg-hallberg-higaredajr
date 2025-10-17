package coconuts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// an object in the game, either something coming from the island or falling on it
// Each island object has a location and can determine if it hits another island object
// This is a domain class; do not introduce JavaFX or other GUI components here
public abstract class IslandObject {
    protected final int width;
    protected final OhCoconutsGameManager containingGame;
    protected int x, y;
    ImageView imageView = null;
    protected final HitEvents hitType;

    // TODO why is it x,y here and height and width here in the game manager?
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
        // TODO hit boxes are still kinda messed up (too big?)
        // TODO maybe we need a hittable width as well?

        // check if overlap on x axis
        boolean xOverlap = (this.x < other.x + other.width) &&
                (this.x + this.width > other.x);

       // check if overlap on y axis (assuming width & height are the same)
        boolean yOverlap = (this.y < other.hittableHeight()) &&
                (this.hittableHeight() > other.y);

        // collision occurs only if there is overlap on BOTH axes.
        return xOverlap && yOverlap;
//        return (other.hittableHeight() == hittableHeight() &&
//                other.x + (other.width / 2) >= x && other.x + (other.width * 2) <= x + width);
    }

    public abstract void step();

    public HitEvents getHitType() {
        return hitType;
    }
}
