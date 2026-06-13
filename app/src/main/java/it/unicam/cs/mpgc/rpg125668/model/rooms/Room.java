package it.unicam.cs.mpgc.rpg125668.model.rooms;

import it.unicam.cs.mpgc.rpg125668.model.rooms.interfaces.IRoom;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable, IRoom {
    private  final String name;
    private  final List<IRoom> exits;
    private  final String description;

    public Room(String name, List<IRoom> exits, String description){
        if(name == null || exits == null || description == null) throw new IllegalArgumentException("Illegal arguments: name is null or exits is null or description is null");
        this.name = name;
        this.exits = exits;
        this.description = description;
    }

    public void addExit(IRoom room) {
        if(room == null) throw new IllegalArgumentException("Illegal arguments: room is null");
        exits.add(room);
    }

    @Override
    public String getName() {return name;}
    @Override
    public List<IRoom> getExits() {return exits;}
    @Override
    public String getDescription() {return description;}
}
