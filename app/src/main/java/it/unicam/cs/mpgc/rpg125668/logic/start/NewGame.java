package it.unicam.cs.mpgc.rpg125668.logic.start;

import it.unicam.cs.mpgc.rpg125668.model.characters.Level;
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.utils.LootGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NewGame extends Game {

    private static final String START_CURRENT_ROOM = "Atrio";


    public NewGame(String namePlayer, String skillName, int preparation, int startLife) {
        super();
        if(namePlayer == null || skillName == null ) throw new IllegalArgumentException("Name, skillName or inventory cannot be null");
        this.startLoadEntity(skillName,namePlayer,startLife,new Inventory(0,new HashMap<>()),preparation);
        this.start();
    }

    /**
     * Method that loads the entity of the game
     * @param skillName the name of the skill to be loaded
     * @param namePlayer the name of the player to create
     * @param startLife the starting life of the player
     * @param inventory the inventory of the player to create
     */
    protected void startLoadEntity(String skillName, String namePlayer, int startLife, IInventory inventory, int preparation) {
        //get the skill from the list of skills loaded from db
        IStudentSkill skill = this.loadedData.getStudentSkills().stream()
                .filter(s -> s.getName().equals(skillName))
                .findFirst()
                .orElseThrow();

        //create the player with base stats and a starter skill added to new ArrayList (mutable list)
        this.player = new Student(namePlayer, startLife, new Level(), preparation, 100, 0, new ArrayList<>(List.of(skill)), inventory);

        //add enemies to the list of enemies of the game
        this.enemies = this.loadedData.getEnemies();

        //add skills to unlock/unlocked to the list of skills to unlock of the game
        this.studentSkillsToUnlock = this.loadedData.getStudentSkills();

        //create the map of the game
        this.gameMap = createGameMap(START_CURRENT_ROOM);
        LootGenerator.generateLoot(this.gameMap);
    }

}
