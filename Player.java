
import java.util.HashMap;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package worldofzuul;

/**
 *
 * @author hal
 */
public class Player implements Cloneable {
    private Room currentRoom;
    private String name;
    private int maxWeight;
    private HashMap<String, Item> items = new HashMap<String, Item>();
    
    /**
     * Constructor x defecto
     */
    public Player() {
        name = "";
        maxWeight = Integer.MAX_VALUE;
    }
    
    /**
     * Constructor de copia
     * @param player el Player a copiar
     */
    public Player(Player player) {
        this(player.currentRoom, player.name, player.maxWeight, player.items);
    }
    
    /**
     * 
     * @param currentRoom La habitación donde comienza la partida el jugador
     * @param name El nombre del jugador
     * @param maxWeight El peso máximo que puede acarrear el jugador
     * @param items
     */
    public Player(Room currentRoom, 
            String name, 
            int maxWeight, 
            HashMap<String, Item> items) {
        
        this.currentRoom = currentRoom;
        this.name = name;
        this.maxWeight = maxWeight;
        this.items = items;
    }
    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @return the currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom the currentRoom to set
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
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

    /**
     * @return the maxWeight
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * @param maxWeight the maxWeight to set
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * @return the item
     */
    public String getItems() {
        String itemsString = "Items: ";
        
        Set<String> keys =  items.keySet();
        for(String item : keys) {
            itemsString += item + " ";
        }
        
        return itemsString;
    }

    /**
     * @param item the item to set
     */
    public void addItem(String itemName, Item item) {
        Item ia = item;
        items.put(itemName, item);
    }
 
    /**
     *
     * @param item
     * @return
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }
}
