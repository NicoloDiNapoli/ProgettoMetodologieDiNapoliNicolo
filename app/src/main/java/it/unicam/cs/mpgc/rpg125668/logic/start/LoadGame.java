package it.unicam.cs.mpgc.rpg125668.logic.start;

import it.unicam.cs.mpgc.rpg125668.logic.dto.GameSaveData;

public class LoadGame extends Game {


    public LoadGame(String saveName) {
        super();
        if(saveName == null) throw new IllegalArgumentException("Save name cannot be null");
        GameSaveData save = this.loadedData.getSaves().stream()
                .filter(s -> s.getSaveName().equalsIgnoreCase(saveName))
                .findFirst()
                .orElseThrow();
        this.player = save.getPlayer();
        this.enemies = this.loadedData.getEnemies();
        this.studentSkillsToUnlock = this.loadedData.getStudentSkills();
        this.gameMap = createGameMap(save.getCurrentRoom().getName());
        this.start();
    }
}