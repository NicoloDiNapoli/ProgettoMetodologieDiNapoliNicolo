package it.unicam.cs.mpgc.rpg125668.persistence.store.skill;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.SkillEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.skill.interfaces.ISKillStore;

import java.util.List;

public class SkillStore<T extends SkillEntity> extends BaseStore<T> implements ISKillStore<T> {

    private final Class<T> skillClass;

    public SkillStore(Class<T> skillClass) {
        super(skillClass);
        this.skillClass = skillClass;
    }

    public List<T> selectAll() {
        return entityManager.createQuery("select s from " + skillClass.getSimpleName() + " s",
                skillClass)
                .getResultList();
    }

    public T findByName(String name){
        return entityManager.createQuery("select s from " + skillClass.getSimpleName() + " s where s.name = :name",
                skillClass)
                .setParameter("name", name)
                .getSingleResult();
    }
}