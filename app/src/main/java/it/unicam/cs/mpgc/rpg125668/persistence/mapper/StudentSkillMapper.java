package it.unicam.cs.mpgc.rpg125668.persistence.mapper;

import it.unicam.cs.mpgc.rpg125668.model.skill.StudentSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.StudentSkillEntity;

public class StudentSkillMapper {

    public static IStudentSkill toModel(StudentSkillEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        return new StudentSkill(
                entity.getPreparationRequired(),
                entity.getName(),
                entity.getRarity(),
                entity.getDamage(),
                entity.getDescription(),
                entity.getSkillType()
        );
    }

    public static StudentSkillEntity toEntity(IStudentSkill model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new StudentSkillEntity(
                model.getName(),
                model.getRarity(),
                model.getDamage(),
                model.getDescription(),
                model.getSkillType(),
                model.getPreparationRequired()
        );
    }
}