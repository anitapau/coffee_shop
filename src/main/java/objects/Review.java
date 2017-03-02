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
    private int reviewid;
    /**
     * A reviews string representation of the review.
     */
    private String review;

    /**
     * Shop id associated with this review.
     */
    private Integer shopid;
    /**
     * rating associated with this review.
     */
    private Integer rating;

    public Review() {
    }

    // GETTERS AND SETTERS
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

}
