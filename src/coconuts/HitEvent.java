package coconuts;

import javafx.scene.image.Image;

import java.util.Collection;
import java.util.LinkedList;

// An abstraction of all objects that can be hit by another object
// This captures the Subject side of the Observer pattern; observers of the hit event will take action
//to process that event
// This is a domain class; do not introduce JavaFX or other GUI components here
public class HitEvent {
    // TODO this needs to translate the happenings of the hit events to the scoreboard
    // probably needs to include the two objects that interact?
    // or could the scoreboard just know the types and adjust the scores accordingly
    // because the game manager handles the removal. like if a laser hits a coconut,
    // we don't need to know what coconut instance and what laser instance, just what
    // field to change given the types
}
