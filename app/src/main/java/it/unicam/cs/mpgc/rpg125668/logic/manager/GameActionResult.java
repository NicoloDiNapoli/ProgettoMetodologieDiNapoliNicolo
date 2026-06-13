package it.unicam.cs.mpgc.rpg125668.logic.manager;
import it.unicam.cs.mpgc.rpg125668.logic.enumeration.CombatResult;

/**
 * Immutable class that carries the result of a game action
 * from the GameManager to the GUI Controller.
 */
public final class GameActionResult {
    private final CombatResult combatResult;
    private final String message;
    private final boolean success;

    public GameActionResult(CombatResult combatResult, String message, boolean success) {
        this.combatResult = combatResult;
        this.message = message;
        this.success = success;
    }

    // success result
    public static GameActionResult info(String message) {
        return new GameActionResult(CombatResult.FIGHT, message, true);
    }

    // failure result
    public static GameActionResult failure(String message) {
        return new GameActionResult(CombatResult.FIGHT, message, false);
    }

    public CombatResult getCombatResult(){return this.combatResult;}
    public String getMessage(){return this.message;}
    public boolean isSuccess(){return this.success;}
}