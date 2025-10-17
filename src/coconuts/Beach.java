package coconuts;

// the beach catches (hits) coconuts and increases the coconut score
// This is a domain class; do not introduce JavaFX or other GUI components here
public class Beach extends IslandObject {

    private final int islandDifference;

    public Beach(OhCoconutsGameManager game, int skyHeight, int islandWidth) {
        super(game, 0, skyHeight, islandWidth, null, HitEvents.BEACH);
        System.out.println("Beach at y = " + super.y);
        System.out.println("Beach Sky Height "+ skyHeight);
        System.out.println("Beach Island Width " + islandWidth);
        this.islandDifference = islandWidth-skyHeight;
    }

    @Override
    public void step() { /* do nothing */ }

    @Override
    protected int hittableHeight() {
        return super.y - islandDifference;
    }
}

