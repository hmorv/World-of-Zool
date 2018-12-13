/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author hal
 */
public class Item {
    private String name;
    private String description;
    private int weight;
    
    public Item(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public String getInfo() {
        return "Item: " + getName() + ", "
                + ".\n Nombre: " + getName() + ", "
                + "Descripción: " + getDescription() + ".\n"
                +"Peso: " + getWeight() + ".\n"; 
    }
    
    public String toString() {
        return "";
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
