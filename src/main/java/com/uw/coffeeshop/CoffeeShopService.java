/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uw.coffeeshop;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoffeeShop() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        CoffeeShop [] shop =null;
        try {
            Model db = Model.singleton();
            shop = db.getCoffeeShop();
            response = mapper.writeValueAsString(shop);
        } catch (Exception e) {
            response = "{ErroMessage: Error geting coffeeshop}";
        }
        return response;

    }

    @GET
    @Path("/{shopId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCoffeeShop(@PathParam("shopId") int shopId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        CoffeeShop [] shop =null;
        try {
            Model db = Model.singleton();
            shop = db.getCoffeeShop(shopId);
            response = mapper.writeValueAsString(shop);
        } catch (Exception e) {
            response = "{ErroMessage: Error geting coffeeshop}";
        }
        return response;   
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateCoffeeShop(String jobj) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        
        JSONObject response = new JSONObject();
        try {
            Model db = Model.singleton();
            int shopId = shop.getShopid();
            db.updateCoffeeShop(shop);
            response.put("shopId", shopId);
            logger.log(Level.INFO, "update contents with shop id=" + shopId);
        } catch (SQLException sqle) {
           response.put("Error", sqle.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
            response.put("Error", e.getMessage());
        }
        return response.toString();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createCoffeeShop(String jobj) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        
        JSONObject response = new JSONObject();
        try {
            Model db = Model.singleton();
            int shopid = db.createCoffeeShop(shop);
            logger.log(Level.INFO, "Coffeeshop persisted to db as id=" + shopid);
            response.put("shopId", shopid);
        } catch (SQLException sqle) {
            String errText = "Error persisting shop after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            response.put("Error", errText);
        } catch (Exception e) {
            response.put("Error", "Error connecting to db.");
            logger.log(Level.SEVERE, "Error connecting to db.");
        }

        return response.toString();

    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String deleteCoffeeShop(@PathParam("id") int id, String jobj) throws IOException, JSONException {
        
        JSONObject response = new JSONObject();
        try {
            Model db = Model.singleton();
            db.deleteCoffeeShop(id);
            logger.log(Level.INFO, "Shop deleted from db=" + id);
            response.put("shopId", id);
        } catch (SQLException sqle) {
            String errText = "Error deleteing shop after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            response.put("Error", errText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
            response.put("Error", "Error connecting to db");
        }
        return response.toString();
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/common")
    public String createCoffeeShopFromCommonUrl(String jObj) throws UnirestException, JSONException, IOException{
        JSONObject input = new JSONObject(jObj);
        String url = input.getString("url");
        
        HttpResponse<JsonNode> getResponse = Unirest.get(url).asJson();
       
        Model db = null;
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop[] shops = (CoffeeShop[])mapper.readValue(getResponse.getBody().toString(), CoffeeShop[].class);
        
        JSONArray response = new JSONArray();
        for(CoffeeShop shop: shops){
            try {
                db = Model.singleton();
                int shopid = db.createCoffeeShop(shop);
                response.put(shopid);
            } catch (Exception ex) {
                Logger.getLogger(CoffeeShopService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        return response.toString();
    }
    

}
