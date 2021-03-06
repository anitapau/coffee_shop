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

    public int createReview(Review review) throws SQLException {
        String sqlInsert = "insert into review (shopid, reviews, rating) values ("
                + "'" + review.getShopid()+ "', "
                + "'" + review.getReview() + "',"
                + "'" + review.getRating()+"');";
        logger.log(Level.INFO, "SQL STATMENT= " + sqlInsert);
        Statement s = createStatement();
        logger.log(Level.INFO, "attempting statement execute");
        s.execute(sqlInsert, Statement.RETURN_GENERATED_KEYS);
        logger.log(Level.INFO, "statement executed.  atempting get generated keys");
        ResultSet rs = s.getGeneratedKeys();
        logger.log(Level.INFO, "retrieved keys from statement");
        int reviewId = -1;
        while (rs.next()) {
            reviewId = rs.getInt("reviewid");   // assuming 2nd column is shopid
        }
        logger.log(Level.INFO, "The new review id=" + reviewId);
        return reviewId;
    }

    public Review[] getReview(int reviewId) throws SQLException {
        //To change body of generated methods, choose Tools | Templates.
        LinkedList<Review> ll = new LinkedList<Review>();
        //CoffeeShop shop = new CoffeeShop();
        String sqlQuery = "select * from review where reviewid = " + reviewId + ";";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
           while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            Review review = new Review();
            review.setReview(rows.getString("reviews"));
            review.setRating(rows.getInt("rating"));
            review.setShopid(rows.getInt("shopid"));
            review.setReviewid(rows.getInt("reviewid"));
            logger.log(Level.INFO, "Adding coffeeShop to list with id=" + review.getShopid());
            ll.add(review);
        }
           return ll.toArray(new Review[ll.size()]);
    }

    public void deleteReview(int reviewID) throws SQLException {

        Statement sqlStatement = createStatement();
        sqlStatement.execute("delete from review where reviewid=" + reviewID);

    }

    public boolean updateReview(Review review) throws SQLException {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("update review ");
        sqlQuery.append("set reviews='" + review.getReview() + "', ");
        sqlQuery.append("rating='" + review.getRating() + "', ");
        sqlQuery.append("shopid='" + review.getShopid() + "' ");
        sqlQuery.append("where reviewid=" + review.getReviewid() + ";");
        Statement st = createStatement();
        logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
        return st.execute(sqlQuery.toString());

    }

    public int createCoffeeShop(CoffeeShop coffeeShop) throws SQLException {
        String sqlInsert = "insert into coffeeshop (name, street, city, state, zip, phone, description, opentime, closetime) values ("
                + "'" + coffeeShop.getName() + "',"
                + "'" + coffeeShop.getAddress() + "',"
                + "'" + coffeeShop.getCity() + "',"
                + "'" + coffeeShop.getState() + "',"
                + "'" + coffeeShop.getZip() + "',"
                + "'" + coffeeShop.getPhone() + "',"
                + "'" + coffeeShop.getDescription() + "',"
                + "'" + coffeeShop.getOpentime() + "',"
                + "'" + coffeeShop.getClosetime() + "');";
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

        Statement sqlStatement = createStatement();
        sqlStatement.execute("delete from coffeeshop where shop_id=" + id);
    }

    public CoffeeShop[] getCoffeeShop(int shopid) throws SQLException {
        //To change body of generated methods, choose Tools | Templates.
        LinkedList<CoffeeShop> ll = new LinkedList<CoffeeShop>();
        String sqlQuery = "select * from coffeeshop where shop_id = " + shopid + ";";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            CoffeeShop shop = new CoffeeShop();
            shop.setName(rows.getString("name"));
            shop.setShopid(rows.getInt("shop_id"));
            shop.setAddress(rows.getString("street"));
            shop.setCity(rows.getString("city"));
            shop.setState(rows.getString("state"));
            shop.setZip(rows.getInt("zip"));
            shop.setPhone(rows.getString("phone"));
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
        String sqlQuery = "select * from coffeeshop;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);

        while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            CoffeeShop shop = new CoffeeShop();
            shop.setName(rows.getString("name"));
            shop.setShopid(rows.getInt("shop_id"));
             shop.setAddress(rows.getString("street"));
            shop.setCity(rows.getString("city"));
            shop.setState(rows.getString("state"));
            shop.setZip(rows.getInt("zip"));
            shop.setPhone(rows.getString("phone"));
            shop.setOpentime(rows.getInt("opentime"));
            shop.setClosetime(rows.getInt("closetime"));
            shop.setDescription(rows.getString("description"));
          // shop.setReviews(this.getReviewsByShopId(shop.getShopid()));
            logger.log(Level.INFO, "Adding coffeeShop to list with id=" + shop.getShopid());
            ll.add(shop);
            
        }
        return ll.toArray(new CoffeeShop[ll.size()]);
    }
    
    private List<Review> getReviewsByShopId(int shopId) throws SQLException {
        
         //To change body of generated methods, choose Tools | Templates.
        List<Review> ll = new ArrayList<Review>();
        //CoffeeShop shop = new CoffeeShop();
        String sqlQuery = "select * from review where shopid = " + shopId + ";";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
           while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            Review review = new Review();
            review.setReview(rows.getString("reviews"));
            review.setRating(rows.getInt("rating"));
            review.setShopid(rows.getInt("shopid"));
            review.setReviewid(rows.getInt("reviewid"));
            logger.log(Level.INFO, "Adding coffeeShop to list with id=" + review.getShopid());
            ll.add(review);
        }
           return ll;
        
    }

    public boolean updateCoffeeShop(CoffeeShop coffeeShop) throws SQLException {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("update coffeeshop ");
        sqlQuery.append("set name='" + coffeeShop.getName() + "', ");
        sqlQuery.append("street='" + coffeeShop.getAddress() + "', ");
        sqlQuery.append("city='" + coffeeShop.getCity() + "', ");
        sqlQuery.append("state='" + coffeeShop.getState() + "', ");
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

    public Review[] getReview() throws SQLException { //To change body of generated methods, choose Tools | Templates.
           //To change body of generated methods, choose Tools | Templates.
        LinkedList<Review> ll = new LinkedList<Review>();
        //CoffeeShop shop = new CoffeeShop();
        String sqlQuery = "select * from review ;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
           while (rows.next()) {
            logger.log(Level.INFO, "Reading row...");
            Review review = new Review();
            review.setReview(rows.getString("reviews"));
            review.setRating(rows.getInt("rating"));
            review.setShopid(rows.getInt("shopid"));
            review.setReviewid(rows.getInt("reviewid"));
            logger.log(Level.INFO, "Adding review to list with id=" + review.getReviewid());
            ll.add(review);
        }
           return ll.toArray(new Review[ll.size()]);
        
    }
}
