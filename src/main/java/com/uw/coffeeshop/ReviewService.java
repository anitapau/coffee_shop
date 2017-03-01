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
@Path("review")
public class ReviewService {

    static final Logger logger = Logger.getLogger(ReviewService.class.getName());

    @Context
    private UriInfo context;

    @GET
    @Path("/{reviewid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getReview(@PathParam("reviewid") int reviewId) throws IOException {
       StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>REVIEW LIST:</b><br><br><table cellpadding=10 border=1><tr><td>reviews</td><td>reviewId</td><td>shopId</td><td>rating</td></tr>");
        try {
            Model db = Model.singleton();
            Review[] review = db.getReview(reviewId);
            for (int i = 0; i < review.length; i++) {
                sb.append("<tr><td>" + review[i].getReview() + "</td><td>" + review[i].getReviewid()+ "</td><td>" + review[i].getShopid() + "</td><td>" + review[i].getRating() + "</td></tr>");
            }
        } catch (Exception e) {
            sb.append("</table><br>Error getting review: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
       return sb.toString();

    }
    
    
       @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReview() throws IOException {
       StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>REVIEW LIST:</b><br><br><table cellpadding=10 border=1><tr><td>reviews</td><td>reviewId</td><td>shopId</td><td>rating</td></tr>");
        try {
            Model db = Model.singleton();
            Review[] review = db.getReview();
            for (int i = 0; i < review.length; i++) {
                sb.append("<tr><td>" + review[i].getReview() + "</td><td>" + review[i].getReviewid()+ "</td><td>" + review[i].getShopid() + "</td><td>" + review[i].getRating() + "</td></tr>");
            }
        } catch (Exception e) {
            sb.append("</table><br>Error getting review: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
       return sb.toString();

    }
    

    @PUT
   @Produces(MediaType.TEXT_PLAIN)
   @Consumes(MediaType.APPLICATION_JSON)
   public String updateReview(String jobj) throws IOException {
       ObjectMapper mapper = new ObjectMapper();
       Review review = mapper.readValue(jobj.toString(), Review.class);
        StringBuilder text = new StringBuilder();
        try {
           Model db = Model.singleton();
           db.updateReview(review);
          logger.log(Level.INFO, "update contents with review id=" + review.getReviewid());
          text.append("review contents updated with review id=" + review.getReviewid() + "\n");
        } catch (SQLException sqle) {
            String errText = "Error updating review after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
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
   public String createReview(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);

        StringBuilder text = new StringBuilder();
        text.append("The JSON obj:" + jobj.toString() + "\n");
        text.append("Review reviews is  " + review.getReview()+ "\n");
        text.append("Review shopid is " + review.getShopid() + "\n");
        text.append("Review reviewid is" + review.getReviewid() + "\n");
        text.append("Review rating is " + review.getRating() + "\n");
        
        try {
            Model db = Model.singleton();
            int reviewId = db.createReview(review);
            logger.log(Level.INFO, "Review persisted to db as id=" + reviewId);
            text.append("Review id persisted with id=" + reviewId);
        } catch (SQLException sqle) {
           String errText = "Error persisting review after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
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
    public String deleteReview(@PathParam("id") int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder text = new StringBuilder();
       try {
            Model db = Model.singleton();
           db.deleteReview(id);
            logger.log(Level.INFO, "review deleted from db=" + id);
           text.append("review id deleted with id=" + id);
        } catch (SQLException sqle) {
            String errText = "Error deleteing review after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
           logger.log(Level.SEVERE, errText);
            text.append(errText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to db.");
            text.append("Error connecting to db.");
       }
        return text.toString();
    }

}
