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
public class Review {

    /**
     * The id associated with this review.
     */
    private Integer reviewid;
    /**
     * A reviews string representation of the review.
     */
    private String reviews;

    /**
     * Shop id associated with this review.
     */
    private Integer shopid;
    /**
     * rating associated with this review.
     */
    private Integer rating;

    public Review(int reviewid, int shopid) {
        this.review = review;
        this.shopid = shopid;
        this.reviewid = reviewid;
        this.rating = rating;
    }

    public Review() {
        
    }
     public Review(String review) {
         this.reviews = review;
        
    }

    // GETTERS AND SETTERS
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getShopid() {
        return shopId;
    }

    public void setShopid(Integer shopid) {
        this.shopId = shopid;
    }

}
