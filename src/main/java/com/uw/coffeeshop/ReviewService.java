/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uw.coffeeshop;

import static com.uw.coffeeshop.CoffeeShopService.logger;
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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author ubuntu
 */
@Path("review")
public class ReviewService {

    static final Logger logger = Logger.getLogger(ReviewService.class.getName());

    @Context
    private UriInfo context;

    @GET
    @Path("/{reviewid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReview(@PathParam("reviewid") int reviewId) throws IOException {
       ObjectMapper mapper = new ObjectMapper();
        String response = null;
        Review [] review =null;
        try {
            Model db = Model.singleton();
            review = db.getReview(reviewId);
            response = mapper.writeValueAsString(review);
        } catch (Exception e) {
            response = "{ErroMessage: Error geting review}";
        }
        return response;

    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReview() throws IOException {
            ObjectMapper mapper = new ObjectMapper();
        String response = null;
        Review [] review =null;
        try {
            Model db = Model.singleton();
            review = db.getReview();
            response = mapper.writeValueAsString(review);
        } catch (Exception e) {
            response = "{ErroMessage: Error geting review}";
        }
        return response;
    }
    

    @PUT
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public String updateReview(String jobj) throws IOException, JSONException {
     ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);
        
        JSONObject response = new JSONObject();
        try {
            Model db = Model.singleton();
            int reviewId = review.getReviewid();
            db.updateReview(review);
            response.put("reviewid", reviewId);
            logger.log(Level.INFO, "update contents with review id=" + reviewId);
        } catch (SQLException sqle) {
           response.put("Error", sqle.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
            response.put("Error", e.getMessage());
        }
        return response.toString();

    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
   public String createReview(String jobj) throws IOException, JSONException {
       ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);
        
        JSONObject response = new JSONObject();
        try {
            Model db = Model.singleton();
            int reviewid = db.createReview(review);
            logger.log(Level.INFO, "Review persisted to db as id=" + reviewid);
            response.put("shopId", reviewid);
        } catch (SQLException sqle) {
            String errText = "Error persisting review after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            response.put("Error", errText);
        } catch (Exception e) {
            response.put("Error", "Error connecting to db.");
            logger.log(Level.SEVERE, "Error connecting to db.");
        }

        return response.toString();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deleteReview(@PathParam("id") int id) throws IOException, JSONException {
          JSONObject response = new JSONObject();
        try {
            Model db = Model.singleton();
            db.deleteReview(id);
            logger.log(Level.INFO, "Review id deleted from db=" + id);
            response.put("reviewid", id);
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

}
