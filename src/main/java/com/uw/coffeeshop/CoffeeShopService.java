/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uw.coffeeshop;

import data.Model;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import objects.CoffeeShop;
import objects.Review;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ubuntu
 */
@Path("coffeeShop")
public class CoffeeShopService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoffeeShop() throws IOException {
//TODO return proper representation object
        List<CoffeeShop> coffeeshop = null;
        ObjectMapper mapper = new ObjectMapper();
        Model db = null;
        try {
            db = Model.singleton();
            coffeeshop = db.getCoffeeShop();

        } catch (Exception ex) {
            Logger.getLogger(CoffeeShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapper.writeValueAsString(coffeeshop);
      
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateCoffeeShop( String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop coffee = mapper.readValue(jobj, CoffeeShop.class);
        StringBuilder text = new StringBuilder();
        Model db = null;
        try {
          db = Model.singleton();
          boolean updated = db.updateCoffeeShop(coffee);
           if(!updated){
                text.append("coffee shop not found");
            }else{
                text.append("CofeeShop is updated with name=" + coffee.getName());
            }
        } catch (Exception ex) { 
            Logger.getLogger(CoffeeShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text.toString();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCoffeeShop(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop coffeeShop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        Model db = null;
        try {
            db = Model.singleton();
            db.createCoffeeShop(coffeeShop);
        } catch (Exception ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String deleteCoffeeShop(@PathParam("id") int id) throws IOException {
        StringBuilder text = new StringBuilder();
        Model db = null;
        try {
            db = Model.singleton();
            CoffeeShop shop = db.deleteCoffeeShop(id);
            // logger.log(Level.INFO, "user deleted from db=" + userid);
            if(shop == null){
                text.append("coffee shop not found");
            }else{
                text.append("CofeeShop is deleted with name=" + shop.getName());
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CoffeeShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text.toString();
    }
}
