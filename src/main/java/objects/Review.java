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
    private String review;

    /**
     * Shop id associated with this review.
     */
    private Integer shopid;

    public Review(String review, int shopid) {
        this.review = review;
        this.shopid = shopid;
    }

    // GETTERS AND SETTERS
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getReviewId() {
        return reviewid;
    }

    public void setReviewId(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

}
