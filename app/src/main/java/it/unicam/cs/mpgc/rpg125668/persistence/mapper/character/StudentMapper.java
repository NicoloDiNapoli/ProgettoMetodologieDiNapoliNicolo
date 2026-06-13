package it.unicam.cs.mpgc.rpg125668.persistence.mapper.character;

import it.unicam.cs.mpgc.rpg125668.model.characters.Level;
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.characters.interfaces.IStudent;
import it.unicam.cs.mpgc.rpg125668.model.inventory.interfaces.IInventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.interfaces.IStudentSkill;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.StudentEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.inventory.InventoryMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.item.ItemMapper;
import it.unicam.cs.mpgc.rpg125668.persistence.mapper.skills.StudentSkillMapper;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    /**
     * Mapper used to convert the entity to the model
     * @param entity StudentEntity to convert
     * @return Student converted
     */
    public static IStudent<IStudentSkill> toModel(StudentEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null");
        Level level = LevelMapper.toModel(entity.getLevel());
        IInventory inventory = InventoryMapper.toModel(entity.getInventory());
        List<IStudentSkill> skills = new ArrayList<>(entity.getSkills().stream()
                .map(StudentSkillMapper::toModel)
                .toList());
        return new Student(
                entity.getName(),
                entity.getLife(),
                level,
                entity.getPreparation(),
                entity.getConcentration(),
                entity.getMaxLife(),
                skills,
                inventory
        );
    }

    /**
     * Mapper used to convert the model to the entity
     * @param model IStudent<IStudentSkill> to convert
     * @return StudentEntity converted
     */
    public static StudentEntity toEntity(IStudent<IStudentSkill> model) {
        if (model == null) throw new IllegalArgumentException("Model cannot be null");
        return new StudentEntity(
                model.getName(),
                model.getLife(),
                model.getMaxLife(),
                model.getPreparation(),
                model.getMaxPreparation(),
                model.getConcentration(),
                model.getMaxConcentration(),
                LevelMapper.toEntity(model.getLevel()),
                InventoryMapper.toEntity(model.getInventory()),
                new ArrayList<>(model.getSkills().stream()
                        .map(StudentSkillMapper::toEntity)
                        .toList())
        );
    }

    /**
     * private method that updates the entityToUpdate with the values of the model.
     * It avoids creating new entities and generates conflicts with the DB.
     * @param model entity to copy the values from
     * @param entityToUpdate entity to copy the values to
     */
    public static void updateEntity(IStudent<IStudentSkill> model, StudentEntity entityToUpdate) {
        if (model == null || entityToUpdate == null) return;

        entityToUpdate.setLife(model.getLife());
        entityToUpdate.setMaxLife(model.getMaxLife());
        entityToUpdate.setPreparation(model.getPreparation());
        entityToUpdate.setMaxPreparation(model.getMaxPreparation());
        entityToUpdate.setConcentration(model.getConcentration());
        entityToUpdate.setMaxConcentration(model.getMaxConcentration());


        if (model.getInventory() != null && entityToUpdate.getInventory() != null) {
            copyInventory(model,entityToUpdate);
        }

        if (model.getSkills() != null && entityToUpdate.getSkills() != null) {
            copySkill(model,entityToUpdate);
        }
    }

    /**
     * private method that copies the skills of the model to the entityToUpdate
     * to avoid creating new entities and generate conflicts with the DB.
     * @param model to copy the skills from
     * @param entityToUpdate to copy the skills to
     */
    private static void copySkill(IStudent<IStudentSkill> model, StudentEntity entityToUpdate) {
        entityToUpdate.getSkills().clear();

        model.getSkills().stream().forEach(skill -> {
            entityToUpdate.getSkills().add(StudentSkillMapper.toEntity(skill));
        });
    }

    /**
     * private method that copies the inventory of the model to the entityToUpdate
     * to avoid creating new entities and generate conflicts with the DB.
     * @param model to copy the skills from
     * @param entityToUpdate to copy the skills to
     */
    private static void copyInventory(IStudent<IStudentSkill> model, StudentEntity entityToUpdate) {
        InventoryEntity existingInventory = entityToUpdate.getInventory();
        existingInventory.setCoins(model.getInventory().seeCoins());
        existingInventory.getItems().clear();

        model.getInventory().getItems().keySet().forEach(
                item -> {
                    ItemEntity baseItemEntity = ItemMapper.toEntity(item);
                    InventoryItemEntity joinEntity = new InventoryItemEntity(existingInventory, baseItemEntity, model.getInventory().getItems().get(item));
                    existingInventory.addItem(joinEntity);
                });
    }
}