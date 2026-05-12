package it.unicam.cs.mpgc.rpg125668.persistence;

import com.google.gson.*;
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Book;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Drink;
import it.unicam.cs.mpgc.rpg125668.model.consumable.Snack;
import it.unicam.cs.mpgc.rpg125668.model.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125668.model.skill.Skill;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentDeserialize implements JsonDeserializer<Student> {

    @Override
    public Student deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<Skill> skills = new ArrayList<>();
        if (hasSkill(jsonObject)) {
            getSkill(jsonObject, skills, jsonDeserializationContext);
        }

        Inventory inventory = new Inventory(0, new ArrayList<>());
        if(hasInventory(jsonObject)) {
            if(hasCoins(jsonObject)) getCoins(jsonObject, inventory, jsonDeserializationContext);
            if(hasItems(jsonObject)) getInventory(jsonObject, inventory, jsonDeserializationContext);
        }

        return new Student(
                jsonObject.get("name").getAsString(),
                jsonObject.get("life").getAsInt(),
                jsonObject.get("level").getAsInt(),
                jsonObject.get("preparation").getAsInt(),
                jsonObject.get("concentration").getAsInt(),
                jsonObject.get("maxLife").getAsInt(),
                skills,
                inventory
        );

    }

    private void getSkill(JsonObject jsonObject, List<Skill> skills, JsonDeserializationContext jsonDeserializationContext) {
        for(JsonElement skill : jsonObject.get("skills").getAsJsonArray()){
            skills.add(jsonDeserializationContext.deserialize(skill.getAsJsonObject(), Skill.class));
        }
    }

    private void getCoins(JsonObject jsonObject, Inventory inventory, JsonDeserializationContext jsonDeserializationContext) {
        inventory.addCoins(jsonObject.get("inventory").getAsJsonObject().get("coins").getAsInt());
    }

    private boolean hasSkill(JsonObject jsonObject) {
        return jsonObject.has("skills") && !jsonObject.get("skills").isJsonNull();
    }

    private boolean hasItems(JsonObject jsonObject) {
        return jsonObject.get("inventory").getAsJsonObject().has("items") && !jsonObject.get("inventory").getAsJsonObject().get("items").isJsonNull();
    }

    private boolean hasCoins(JsonObject jsonObject) {
        return jsonObject.get("inventory").getAsJsonObject().has("coins") && !jsonObject.get("inventory").getAsJsonObject().get("coins").isJsonNull();
    }

    private boolean hasInventory(JsonObject jsonObject) {
        return jsonObject.has("inventory") && !jsonObject.get("inventory").isJsonNull();
    }

    private void getInventory(JsonObject jsonObject, Inventory inventory, JsonDeserializationContext jsonDeserializationContext) {
        for(JsonElement item : jsonObject.get("inventory").getAsJsonObject().getAsJsonArray("items").getAsJsonArray()){
            switch (item.getAsJsonObject().get("item").getAsJsonObject().get("type").getAsString()){
                case "book":
                    inventory.addItem(jsonDeserializationContext.deserialize(item.getAsJsonObject().get("item"), Book.class), item.getAsJsonObject().get("quantity").getAsInt());
                    break;
                case "drink":
                    inventory.addItem(jsonDeserializationContext.deserialize(item.getAsJsonObject().get("item"), Drink.class), item.getAsJsonObject().get("quantity").getAsInt());
                    break;
                case "snack":
                    inventory.addItem(jsonDeserializationContext.deserialize(item.getAsJsonObject().get("item"), Snack.class), item.getAsJsonObject().get("quantity").getAsInt());
            }
        }
    }
}
