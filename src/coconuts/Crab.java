package coconuts;

import javafx.scene.image.Image;

// Represents the object that shoots down coconuts but can be hit by coconuts. Killing the
//   crab ends the game
// This is a domain class; other than Image, do not introduce JavaFX or other GUI components here
public class Crab extends IslandObject {
    private static final int WIDTH = 50; // assumption: height and width are the same
    private static final Image crabImage = new Image("file:images/crab-1.png");
    private int islandWidth;

    public Crab(OhCoconutsGameManager game, int skyHeight, int islandWidth) {
        super(game, islandWidth / 2, skyHeight-10, WIDTH, crabImage, HitEvents.CRAB);
        // TODO Why do I need at add 10?
        this.islandWidth = islandWidth;
    }

    @Override
    protected int hittableHeight() {
        return y;
    }

    @Override
    public boolean isGroundObject() {
        return true;
    }

    @Override
    public void step() {
        // do nothing
    }

    // Captures the crab crawling sideways
    public void crawl(int offset) {
        // prevents crab crawling off screen
        if (x+ offset >= 0 && x + offset <= islandWidth- width) {
            x += offset;
            display();
        }
    }
}
