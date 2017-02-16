/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;
/**
 *
 * @author ubuntu
 */
public class CoffeeShop {

    String name;
    String address; 
    String description;
    int id;
    public CoffeeShop(){}

    public CoffeeShop(String name, String address, String description, int id) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
