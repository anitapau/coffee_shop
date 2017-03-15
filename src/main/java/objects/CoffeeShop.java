/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.List;

/**
 *
 * @author ubuntu
 */
public class CoffeeShop {

    /**
     * The id of the coffee shop.
     */
    public int shop_id;
    /**
     * Name of the coffee shop.
     */
    public String name;
    /**
     * Street address
     */
    public String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * City of the coffee shop.
     */
    public String city;
    /**
     * State of the coffee shop.
     */
    public String state;
    /**
     * Zip of the coffee shop.
     */
    public long zip;
    /**
     * Phone number of the coffee shop.
     */
    public String description;
    /**
     * Phone number of the coffee shop.
     */
    public String phone;
    /**
     * Opening time of the coffee shop
     */
    public int opentime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phonenumber) {
        this.phone = phonenumber;
    }

    public int getOpentime() {
        return opentime;
    }

    public void setOpentime(int opentime) {
        this.opentime = opentime;
    }

    public int getClosetime() {
        return closetime;
    }

    public void setClosetime(int closetime) {
        this.closetime = closetime;
    }
    /**
     * Close time of the coffee shop..../api/shops
     */
    public int closetime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public int getShopid() {
        return shop_id;
    }

    public void setShopid(int shopid) {
        this.shop_id = shopid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
