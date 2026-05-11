package it.unicam.cs.mpgc.rpg125668.persistence;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.cs.mpgc.rpg125668.model.characters.Student;

import java.lang.reflect.Type;

public class StudentSerialize implements JsonSerializer<Student> {

    @Override
    public JsonElement serialize(Student student, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        //Student serializes
        jsonObject.addProperty("maxLife", student.getMaxLife());
        jsonObject.addProperty("name", student.getName());
        jsonObject.addProperty("life", student.getLife());
        jsonObject.addProperty("level", student.getLevel());
        jsonObject.addProperty("preparation", student.getPreparation());
        jsonObject.addProperty("concentration", student.getConcentration());

        //Inventory serialize
        jsonObject.add("inventory", jsonSerializationContext.serialize(student.getInventory()));

        //Skill serialize
        jsonObject.add("skills", jsonSerializationContext.serialize(student.getSkills()));

        return jsonObject;
    }
}
