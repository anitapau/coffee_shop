/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uw.coffeeshop;

import data.Model;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import objects.CoffeeShop;
import objects.Review;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ubuntu
 */
@Path("coffeeShop")
public class CoffeeShopService {

    static final Logger logger = Logger.getLogger(CoffeeShopService.class.getName());

    @Context
    private UriInfo context;

    @GET
    @Path("/api/shops")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCoffeeShop() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>MESSAGE LIST:</b><br><br><table cellpadding=10 border=1><tr><td>`name`</td><td>id</td><td>city</td><td>state</td><td>zip</td><td>phone</td><td>opentime</td><td>closetime</td><td>description</td><td>reviews</td></tr>");
        try {
            Model db = Model.singleton();
            CoffeeShop[] shop = db.getCoffeeShop();
            for (int i = 0; i < shop.length; i++) {
                StringBuilder reviews = new StringBuilder();
                reviews.append("[");
                boolean isFirst = true;
                for(Review review: shop[i].getReviews()){
                    if (isFirst){
                        isFirst = false;
                    }else{
                        reviews.append(", ");
                    }
                    reviews.append("review:"+review.getReview());
                }
                reviews.append("]");
                sb.append("<tr><td>" + shop[i].getName() + "</td><td>" + shop[i].getShopid() + "</td><td>" + shop[i].getCity() + "</td><td>" + shop[i].getState() + "</td><td>" + shop[i].getZip() + "</td><td>" + shop[i].getPhone() + "</td><td>" + shop[i].getOpentime() + "</td><td>" + shop[i].getClosetime() + "</td><td>" + shop[i].getDescription()+ "</td><td>" + reviews.toString()+ "</td></tr>");
            }
        } catch (Exception e) {
            sb.append("</table><br>Error getting coffeeShop: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
        return sb.toString();

    }

    @GET
    @Path("/{shopId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCoffeeShop(@PathParam("shopId") int shopId) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>CoffeeShop LIST:</b><br><br><table cellpadding=10 border=1><tr><td>`name`</td><td>id</td><td>city</td><td>state</td><td>zip</td><td>phone</td><td>opentime</td><td>closetime</td><td>description</td></tr>");
        try {
            Model db = Model.singleton();
            CoffeeShop[] shop = db.getCoffeeShop(shopId);
            for (int i = 0; i < shop.length; i++) {
                sb.append("<tr><td>" + shop[i].getName() + "</td><td>" + shop[i].getShopid() + "</td><td>" + shop[i].getCity() + "</td><td>" + shop[i].getState() + "</td><td>" + shop[i].getZip() + "</td><td>" + shop[i].getPhone() + "</td><td>" + shop[i].getOpentime() + "</td><td>" + shop[i].getClosetime() + "</td><td>" + shop[i].getDescription()+ "</td></tr>");
            }
        } catch (Exception e) {
            sb.append("</table><br>Error getting coffeeShop: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateCoffeeShop(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            int shopId = shop.getShopid();
            db.updateCoffeeShop(shop);
            logger.log(Level.INFO, "update contents with shop id=" + shopId);
            text.append("shop contents updated with shop id=" + shopId + "\n");
        } catch (SQLException sqle) {
            String errText = "Error updating shop after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            text.append(errText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
            text.append("Error connecting to db.");
        }
        return text.toString();

    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCoffeeShop(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);

        StringBuilder text = new StringBuilder();
        text.append("The JSON obj:" + jobj.toString() + "\n");
        text.append("CoffeeShop name is  " + shop.getName()+ "\n");
        text.append("CoffeeShop city is " + shop.getCity()+ "\n");
        text.append("CoffeeShop state is " + shop.getState()+ "\n");
        text.append("CoffeeShop zip is " + shop.getZip()+ "\n");
        text.append("CoffeeShop phone is " + shop.getPhone()+ "\n");
        text.append("CoffeeShop opentime is " + shop.getOpentime()+ "\n");
        text.append("CoffeeShop closetime is " + shop.getClosetime()+ "\n");
        text.append("CoffeeShop description is " + shop.getDescription()+ "\n");
        try {
            Model db = Model.singleton();
            int shopid = db.createCoffeeShop(shop);
            logger.log(Level.INFO, "Coffeeshop persisted to db as id=" + shopid);
            text.append("CoffeeShop id persisted with id=" + shopid);
        } catch (SQLException sqle) {
            String errText = "Error persisting shop after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            text.append(errText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
        }

        return text.toString();

    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String deleteCoffeeShop(@PathParam("id") int id, String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       // CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            db.deleteCoffeeShop(id);
            logger.log(Level.INFO, "Shop deleted from db=" + id);
            text.append("coffeeShop id deleted with id=" + id);
        } catch (SQLException sqle) {
            String errText = "Error deleteing shop after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            text.append(errText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
            text.append("Error connecting to db.");
        }
        return text.toString();
    }

}
