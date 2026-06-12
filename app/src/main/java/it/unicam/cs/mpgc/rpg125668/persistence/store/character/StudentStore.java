package it.unicam.cs.mpgc.rpg125668.persistence.store.character;

import it.unicam.cs.mpgc.rpg125668.persistence.entity.character.StudentEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.inventory.InventoryItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.item.ItemEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.entity.skill.StudentSkillEntity;
import it.unicam.cs.mpgc.rpg125668.persistence.store.BaseStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.character.interfaces.IStudentStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.item.ItemStore;
import it.unicam.cs.mpgc.rpg125668.persistence.store.skill.SkillStore;

import java.util.ArrayList;
import java.util.List;

public class StudentStore extends BaseStore<StudentEntity> implements IStudentStore {

    public StudentStore() {
        super(StudentEntity.class);
    }

    /**
     * Override of save method to map the skills and items of the student
     * as existing entities from DB and not as new ones.
     * @param entity StudentEntity to save
     */
    @Override
    public void save(StudentEntity entity) {
        linkExistingEntities(entity);
        super.save(entity);
    }

    /**
     * Override of update method to map the skills and items of the student
     * as existing entities from DB and not as new ones.
     * @param entity StudentEntity to save
     */
    @Override
    public void update(StudentEntity entity) {
        linkExistingEntities(entity);
        super.update(entity);
    }

    public StudentEntity findByName(String name) {
        return entityManager.createQuery(
                        "SELECT s FROM StudentEntity s WHERE s.name = :name",
                        StudentEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    /**
     * Link the skills and items of the student to the student entity.
     * @param entity StudentEntity to link the skills and items to
     */
    private void linkExistingEntities(StudentEntity entity) {
        linkSkills(entity);
        linkInventory(entity);
    }

    /**
     * Link the items of the inventory to the student entity.
     * If the item is already in the DB, it is linked to the student entity.
     * @param entity StudentEntity to link the items to
     */
    private void linkInventory(StudentEntity entity) {
        if(entity.getInventory() == null || entity.getInventory().getItems()==null) return;
        boolean hasTransientItems = entity.getInventory().getItems().stream()
                .anyMatch(item -> item.getId() == null || (item.getItem() != null && item.getItem().getId() == null));

        if (hasTransientItems) {
            ItemStore itemStore = new ItemStore();
            List<InventoryItemEntity> itemsDB = new ArrayList<>();

            entity.getInventory().getItems().stream().forEach(item -> {
                ItemEntity itemDB = itemStore.findByName(item.getItem().getName());
                if (itemDB != null) item.setItem(itemDB);
                else {
                    itemStore.save(item.getItem());
                    item.setItem(item.getItem());
                }
                item.setInventory(entity.getInventory());
                itemsDB.add(item);
            });
            entity.getInventory().getItems().clear();
            entity.getInventory().getItems().addAll(itemsDB);
        }
    }

    /**
     * Link the skills of the student to the student entity.
     * If the skill is already in the DB, it is linked to the student entity.
     * @param entity StudentEntity to link the skills to
     */
    private void linkSkills(StudentEntity entity) {
        if (entity.getSkills() == null) return;
        boolean hasTransientSkills = entity.getSkills().stream().anyMatch(s -> s.getId() == null);

        if (hasTransientSkills) {
            SkillStore<StudentSkillEntity> skillStore = new SkillStore<>(StudentSkillEntity.class);
            List<StudentSkillEntity> skillDB = new ArrayList<>();
            entity.getSkills().stream().forEach(skill -> {
                StudentSkillEntity skillDB1 = skillStore.findByName(skill.getName());
                skillDB.add(skillDB1);
            });
            entity.setSkills(skillDB);
        }
    }

}