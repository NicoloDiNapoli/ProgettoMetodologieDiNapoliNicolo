package it.unicam.cs.mpgc.rpg125668.logic.manager.interfaces;

import it.unicam.cs.mpgc.rpg125668.logic.enumeration.CombatResult;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;

public interface ICombatManager {
    /**
     * Manage the fight between student and enemy using the skill passed as parameter by the student
     * @param skill skill to use in the fight
     * @return CombatResult result of the fight (WIN, LOSE, FIGHT)
     */
    CombatResult fight(IStudentSkill skill);
}
