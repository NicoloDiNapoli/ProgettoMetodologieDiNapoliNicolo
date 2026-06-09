package it.unicam.cs.mpgc.rpg125668.persistence.mapper;

import it.unicam.cs.mpgc.rpg125668.model.skill.BossSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IBossSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.BossSkillEntity;

public class BossSkillMapper {

    public static IBossSkill toModel(BossSkillEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        return new BossSkill(
                entity.getName(),
                entity.getRarity(),
                entity.getDamage(),
                entity.getDescription(),
                entity.getSkillType()
        );
    }

    public static BossSkillEntity toEntity(IBossSkill model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new BossSkillEntity(
                model.getName(),
                model.getRarity(),
                model.getDamage(),
                model.getDescription(),
                model.getSkillType()
        );
    }
}