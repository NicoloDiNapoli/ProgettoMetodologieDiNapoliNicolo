package it.unicam.cs.mpgc.rpg125668.persistence.mapper.character;

import it.unicam.cs.mpgc.rpg125668.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IEnemy;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.EnemyEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.skills.BossSkillMapper;

import java.util.ArrayList;
import java.util.List;

public class EnemyMapper {

    public static Enemy toModel(EnemyEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        List<IBossSkill> skills = new ArrayList<>(entity.getSkills().stream()
                .map(BossSkillMapper::toModel)
                .toList());
        return new Enemy(
                entity.getName(),
                entity.getLife(),
                entity.getMaxLife(),
                entity.getDifficult(),
                skills
        );
    }

    public static EnemyEntity toEntity(IEnemy<IBossSkill> model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new EnemyEntity(
                model.getName(),
                model.getLife(),
                model.getMaxLife(),
                model.getDifficult(),
                new ArrayList<>(model.getSkills().stream()
                        .map(BossSkillMapper::toEntity)
                        .toList())
        );
    }
}