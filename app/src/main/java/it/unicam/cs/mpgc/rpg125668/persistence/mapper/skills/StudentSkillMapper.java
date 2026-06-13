package it.unicam.cs.mpgc.rpg125668.persistence.mapper.skills;

import it.unicam.cs.mpgc.rpg125668.model.skill.StudentSkill;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.StudentSkillEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.skill.SkillStore;

public class StudentSkillMapper {

    /**
     * Convert a StudentSkillEntity to a StudentSkill
     * @param entity StudentSkillEntity
     * @return StudentSkill
     */
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

    /**
     * Convert a IStudentSkill to a StudentSkillEntity
     * @param model IStudentSkill
     * @return StudentSkillEntity
     */
    public static StudentSkillEntity toEntity(IStudentSkill model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new SkillStore<>(StudentSkillEntity.class).findByName(model.getName());
    }
}