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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import objects.Review;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ubuntu
 */
@Path("review")
public class ReviewService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReviews() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Review> reviews = null;
        try {

            Model db = Model.singleton();
            reviews = db.getReviews();

        } catch (Exception ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapper.writeValueAsString(reviews);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createReview(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Review user = mapper.readValue(jobj.toString(), Review.class);
        Model db = null;
        try {
            db = Model.singleton();
            db.createReview(user);
        } catch (Exception ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteReview(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);
        StringBuilder text = new StringBuilder();
        Model db = null;
        try {
            db = Model.singleton();
            //String userid = review.getName();
            db.deleteReview(jobj);
            // logger.log(Level.INFO, "user deleted from db=" + userid);
           // text.append("User id deleted with name=" + userid);
        } catch (Exception ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text.toString();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateReview(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Review user = mapper.readValue(jobj.toString(), Review.class);
        StringBuilder text = new StringBuilder();
        Model db = null;
        try {
            db = Model.singleton();
            //int userid = user.getRating();
          //  String description = user.getDescription();
            db.updateReview(user);
            //logger.log(Level.INFO, "update user with userid=" + userid);
          //  text.append("User id updated with user name=" + user.getName() + "\n");
        } catch (Exception ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return text.toString();
    }
}
