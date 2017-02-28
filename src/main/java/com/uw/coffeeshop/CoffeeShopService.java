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
     @Path("/{api/shops}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoffeeShop() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>MESSAGE LIST:</b><br><br><table cellpadding=10 border=1><tr><td>`name`</td><td>id</td><td>city</td><td>state</td><td>zip</td><td>phone</td><td>opentime</td><td>closetime</td><td>description</td></tr>");
        try {
            Model db = Model.singleton();
            CoffeeShop[] shop = db.getCoffeeShop();
            for (int i = 0; i < shop.length; i++) {
                sb.append("<tr><td>" + shop[i].getName()+ "</td><td>" + shop[i].getShopid()+ "</td><td>" + shop[i].getCity()+"</td><td>" + shop[i].getState()+ "</td><td>" + shop[i].getZip()+ "</td><td>" + shop[i].getPhone()+ "</td><td>" + shop[i].getOpentime()+ "</td><td>" + shop[i].getClosetime()+ "</td></tr>");
            }
        } catch (Exception e) {
            sb.append("</table><br>Error getting users: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
      
    }
    
    @GET
    @Path("/{shopId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoffeeShopById(@PathParam("shopId") int shopId) throws IOException {
        CoffeeShop coffeeShop = null;
        ObjectMapper mapper = new ObjectMapper();
        Model db = null;
        try {
            db = Model.singleton();
            coffeeShop = db.getCoffeeShop(shopId);

        } catch (Exception ex) {
            Logger.getLogger(CoffeeShopService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapper.writeValueAsString(coffeeShop);
      
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
            Logger.getLogger(CoffeeShopService.class.getName()).log(Level.SEVERE, null, ex);
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
