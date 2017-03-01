/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.RewriteException;
import objects.CoffeeShop;
import objects.Review;

/**
 *
 * @author ubuntu
 */
public class Model {

    static final Logger logger = Logger.getLogger(Model.class.getName());
    private static Model instance;
    private Connection conn;
    List<Review> reviewList;

    public static Model singleton() throws Exception {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    Model() throws Exception {
        Class.forName("org.postgresql.Driver");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if ((dbUrl == null) || (dbUrl.length() < 1)) {
            dbUrl = System.getProperties().getProperty("DBCONN");
        }
        logger.log(Level.INFO, "dbUrl=" + dbUrl);
        logger.log(Level.INFO, "attempting db connection");
        conn = DriverManager.getConnection(dbUrl);
        logger.log(Level.INFO, "db connection ok.");
        reviewList = new ArrayList<>();
    }

    private Connection getConnection() {
        return conn;
    }

    private Statement createStatement() throws SQLException {
        Connection conn = getConnection();
        if ((conn != null) && (!conn.isClosed())) {
            logger.log(Level.INFO, "attempting statement create");
            Statement st = conn.createStatement();
            logger.log(Level.INFO, "statement created");
            return st;
        } else {
            // Handle connection failure
        }
        return null;
    }

    private PreparedStatement createPreparedStatement(String sql) throws SQLException {
        Connection conn = getConnection();
        if ((conn != null) && (!conn.isClosed())) {
            logger.log(Level.INFO, "attempting statement create");
            PreparedStatement pst = conn.prepareStatement(sql);
            logger.log(Level.INFO, "prepared statement created");
            return pst;
        } else {
            // Handle connection failure
        }
        return null;
    }

//    public int createReview(Review review) throws SQLException {
//        String sqlInsert = "insert into review (review, rating) values ("
//                + "'" + review.getReview() + "',"
//                + "'" + review.getRating();
//        logger.log(Level.INFO, "SQL STATMENT= " + sqlInsert);
//        Statement s = createStatement();
//        logger.log(Level.INFO, "attempting statement execute");
//        s.execute(sqlInsert, Statement.RETURN_GENERATED_KEYS);
//        logger.log(Level.INFO, "statement executed.  atempting get generated keys");
//        ResultSet rs = s.getGeneratedKeys();
//        logger.log(Level.INFO, "retrieved keys from statement");
//        int reviewId = -1;
//        while (rs.next()) {
//            reviewId = rs.getInt(1);   // assuming 2nd column is shopid
//        }
//        logger.log(Level.INFO, "The new review id=" + reviewId);
//        return reviewId;
//
//    }

//    public Review[] getReview(int reviewId, int shopId) throws SQLException {
//        //To change body of generated methods, choose Tools | Templates.
//        LinkedList<Review> ll = new LinkedList<Review>();
//        //CoffeeShop shop = new CoffeeShop();
//        String sqlQuery = "select * from coffeeShop where id = " + shopId + ";";
//        String sqlQuery = "select * from review list where id = " + reviewId + ";";
//        Statement st = createStatement();
//        ResultSet rows = st.executeQuery(sqlQuery);
//        while (rows.next()) {
//            logger.log(Level.INFO, "Reading row...");
//            Review review = new Review(reviewId, shopId);
//            review.setReview(rows.getString("`name`"));
//            review.setRating(rows.getInt("rate"));
//
//            logger.log(Level.INFO, "Adding review to list with id=" + review.getReviewId());
//            logger.log(Level.INFO, "Adding review to a coffeeShop with id=" + review.getShopid());
//            ll.add(review);
//        }
//        return ll.toArray(new Review[ll.size()]);
//
//    }

/** public Review[] getReview() throws SQLException {
        LinkedList<Review> ll = new LinkedList<Review>();
        String sqlQuery = "select * from coffeeShop;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            Review review = new Review();
            review.setReview(rows.getString("`name`"));
            review.setRating(rows.getInt("rating"));
            review.setReviewId(rows.getInt("reviewId"));
            review.setShopid(rows.getInt("shopId"));
            
            logger.log(Level.INFO, "Adding review to list with id=" + review.getReviewId());
            logger.log(Level.INFO, "Adding review to a ID coffeeShop =" + review.getShopid());

            ll.add(review);
        }
        return ll.toArray(new Review[ll.size()]);
    }
**/

    public void deleteReview(int reviewID) throws SQLException {

        String sqlDelete = "delete from coffeeShop where reviewId=?";
        PreparedStatement pst = createPreparedStatement(sqlDelete);
        pst.setInt(1, reviewID);
        pst.execute();

    }

//    public boolean updateReview(Review review) throws SQLException {
//        StringBuilder sqlQuery = new StringBuilder();
//        sqlQuery.append("update coffeeShop ");
//        sqlQuery.append("set `review`=" + review.getReview() + ",");
//        sqlQuery.append("rating='" + review.getRating() + "', ");
//        sqlQuery.append("where coffeeshop id=" + review.getShopid() + ";");
//        sqlQuery.append("where review id=" + review.getReviewId() + ";");
//        Statement st = createStatement();
//        logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
//        return st.execute(sqlQuery.toString());
//
//    }

    public int createCoffeeShop(CoffeeShop coffeeShop) throws SQLException {
        String sqlInsert = "insert into shops (name, city, state, zip, phone, description, opentime, closetime) values ("
                + "'" + coffeeShop.getName() + "',"
                + "'" + coffeeShop.getCity() + "',"
                + "'" + coffeeShop.getState() + "',"
                + "'" + coffeeShop.getZip() + "',"
                + "'" + coffeeShop.getPhone() + "',"
                + "'" + coffeeShop.getDescription() + "',"
                + "'" + coffeeShop.getOpentime() + "',"
                + "'" + coffeeShop.getClosetime() + "')";
        logger.log(Level.INFO, "SQL STATMENT= " + sqlInsert);
        Statement s = createStatement();
        logger.log(Level.INFO, "attempting statement execute");
        s.execute(sqlInsert, Statement.RETURN_GENERATED_KEYS);
        logger.log(Level.INFO, "statement executed.  atempting get generated keys");
        ResultSet rs = s.getGeneratedKeys();
        logger.log(Level.INFO, "retrieved keys from statement");
        int shopid = -1;
        while (rs.next()) {
            shopid = rs.getInt("shop_id");   // assuming 2nd column is shopid
        }
        logger.log(Level.INFO, "The new shop id=" + shopid);
        return shopid;

    }

    public void deleteCoffeeShop(int id) throws SQLException {

        String sqlDelete = "delete from coffeeShop where shopid=?";
        PreparedStatement pst = createPreparedStatement(sqlDelete);
        pst.setInt(1, id);
        pst.execute();

    }

    public CoffeeShop[] getCoffeeShop(int shopid) throws SQLException {
        //To change body of generated methods, choose Tools | Templates.
        LinkedList<CoffeeShop> ll = new LinkedList<CoffeeShop>();
        String sqlQuery = "select * from coffeeShop where shop_id = " + shopid + ";";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            CoffeeShop shop = new CoffeeShop();
            shop.setName(rows.getString("`name`"));
            shop.setShopid(rows.getInt("shop_id"));
            shop.setCity(rows.getString("city"));
            shop.setState(rows.getString("state"));
            shop.setZip(rows.getInt("zip"));
            shop.setOpentime(rows.getInt("opentime"));
            shop.setClosetime(rows.getInt("closetime"));
            shop.setDescription(rows.getString("description"));
            logger.log(Level.INFO, "Adding coffeeShop to list with id=" + shop.getShopid());
            ll.add(shop);
        }
        return ll.toArray(new CoffeeShop[ll.size()]);

    }

    public CoffeeShop[] getCoffeeShop() throws SQLException {
        LinkedList<CoffeeShop> ll = new LinkedList<CoffeeShop>();
        String sqlQuery = "select * from coffeeShop;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            CoffeeShop shop = new CoffeeShop();
            shop.setName(rows.getString("`name`"));
            shop.setShopid(rows.getInt("shop_id"));
            shop.setCity(rows.getString("city"));
            shop.setState(rows.getString("state"));
            shop.setZip(rows.getInt("zip"));
            shop.setOpentime(rows.getInt("opentime"));
            shop.setClosetime(rows.getInt("closetime"));
            shop.setDescription(rows.getString("description"));
            logger.log(Level.INFO, "Adding coffeeShop to list with id=" + shop.getShopid());
            ll.add(shop);
        }
        return ll.toArray(new CoffeeShop[ll.size()]);
    }

    public boolean updateCoffeeShop(CoffeeShop coffeeShop) throws SQLException {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("update coffeeShop ");
        sqlQuery.append("set `name`=" + coffeeShop.getName() + ",");
        sqlQuery.append("city='" + coffeeShop.getCity() + "', ");
        sqlQuery.append("`state`='" + coffeeShop.getState() + "', ");
        sqlQuery.append("zip='" + coffeeShop.getZip() + "', ");
        sqlQuery.append("phone='" + coffeeShop.getPhone() + "', ");
        sqlQuery.append("opentime='" + coffeeShop.getOpentime() + "', ");
        sqlQuery.append("closetime='" + coffeeShop.getClosetime() + "', ");
        sqlQuery.append("description='" + coffeeShop.getDescription() + "' ");
        sqlQuery.append("where shop_id=" + coffeeShop.getShopid() + ";");
        Statement st = createStatement();
        logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
        return st.execute(sqlQuery.toString());

    }
}
