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
    private Integer reviewId;
    /**
     * A reviews string representation of the review.
     */
    private String review;

    /**
     * Shop id associated with this review.
     */
    private Integer shopId;
    /**
     * rating associated with this review.
     */
    private Integer rating;

    public Review(int reviewId, int shopid) {
        this.review = review;
        this.shopId = shopid;
        this.reviewId = reviewId;
        this.rating = rating;
    }

    public Review() {
        
    }
     public Review(String review) {
        
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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewid) {
        this.reviewId = reviewid;
    }

    public Integer getShopid() {
        return shopId;
    }

    public void setShopid(Integer shopid) {
        this.shopId = shopid;
    }

}
