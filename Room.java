/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.HashMap;
import java.util.Set;

public class Room 
{
    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
    }
    
    public Room getExit(String direction)
    {
        
        return exits.get(direction);
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
    }
    
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    public void setItem(Item item) {
        
        items.put(item.getName(), new Item(item.getName(), item.getDescription(), item.getWeight()));
    }
    public Item getItem(String itemName) {
        return items.remove(itemName);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getFullDescription()
    {
        return "Estas " + description + ".\n" 
                + getExitString()  + ".\n"
                + showItemsInfo();
    }
    
    public String getExitString()
    {
        String stringExits = "Salidas: ";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys)
            stringExits +=  " " + exit;
            
        return stringExits;
        
    }

    private String showItemsInfo() {
        String itemsInfo = "Items: ";
        
        for(String key : items.keySet()) {
            itemsInfo += "nombre: " + items.get(key).getName() + ", "
                    + "descripcion: " + items.get(key).getDescription() + ", "
                    + "peso: " + items.get(key).getWeight() + ".\n";
        }
        
        return itemsInfo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
        
            

}
