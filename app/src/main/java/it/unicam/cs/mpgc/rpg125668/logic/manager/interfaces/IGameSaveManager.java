package it.unicam.cs.mpgc.rpg125668.logic.manager.interfaces;

import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.ICharacter;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IGameMap;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public interface IGameSaveManager {
    /**
     * Save the state of the game.
     * If the game is already saved, it updates the existing save.
     * Otherwise, it creates a new save.
     * @param player player to save
     * @param gameMap gameMap to save
     * @param saveGameName name of the save game
     */
    void saveGame(IStudent<IStudentSkill> player, IGameMap gameMap, String saveGameName);
}
