/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class GameObserver
 * Name: higaredajra
 * Created 10/9/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Interface GameObserver Purpose: Observer interface to track hits of coconuts, the crab, and the beach
 *
 * @author higaredajra
 * @version created on 10/9/2025 11:23 AM
 */
public interface GameObserver {

    public void update(IslandObject thisObj, IslandObject hittableObj, HitEvents hitType, OhCoconutsGameManager theGame);

}