/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class Subject
 * Name: higaredajra
 * Created 10/9/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Interface Subject Purpose: Subject interface for a game object being observed
 *
 * @author higaredajra
 * @version created on 10/9/2025 11:25 AM
 */
public interface Subject {

    public void attach(GameObserver ob);
    public void detach(GameObserver ob);
    public void notifyObservers();

}