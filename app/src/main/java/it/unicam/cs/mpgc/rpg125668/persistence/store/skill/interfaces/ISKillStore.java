package it.unicam.cs.mpgc.rpg125668.persistence.store.skill.interfaces;

import java.util.List;

/**
 * Interface for Skill Query
 * @param <T> type of the skill
 */
public interface ISKillStore<T> {
    /**
     * Select all skills and return them as a list
     * @return List<T> of skills
     */
    List<T> selectAll();

    /**
     * Find a skill by its name
     * @param name String name of the skill
     * @return T skill
     */
    T findByName(String name);
}
