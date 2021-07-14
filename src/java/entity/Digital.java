/*
 *Copyright(C) 2021,  FPTU.
 * J3.L.P0004 :
 *  Digital News
 *
 * Record of change:
 * DATE                       Version             AUTHOR            DESCRIPTION
 * 2021-05-10                  1.0                PhatNT         Start implement
 * 2021-05-11                  1.0                PhatNT         Test, Comment,Fix Bug
 */
package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * The class define attributes of digital, constructor, getter,setter, convert date
 * and date publisher method.
 *
 * @author PhatNT
 */
public class Digital {

    private int id;
    private String title;
    private String description;
    private String image;
    private String author;
    private Date timePost;
    private String shortDes;

    /**
     * Format date to correct format. Date must be in the correct format 'MMM dd
     * yyyy - hh:mm' <code>java.text.SimpleDateFormat</code> object
     *
     * @return a string date after format. It is a String
     */
    public String getDateConvert() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - hh:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("a");
        String date = dateFormat1.format(this.timePost) + dateFormat2.format(this.timePost).toLowerCase();
        return date;
    }

    /**
     * Format text display to correct format. Text must be in the correct format
     * "By {author} | 'MMM dd yyyy - hh:mm' ". It's a String
     *
     * @return a string contain author and create date. It is a String
     */
    public String getDatePublisher() {
        return "By " + author + " | " + getDateConvert();
    }

    /**
     * Constructor none pram
     *
     */
    public Digital() {
    }

    /**
     * Constructor with attributes: id, title, description, image, author,
     * timePost, shortDes
     *
     * @param id the id of digital. It is a <code>java.lang.Integer</code>
     * object
     * @param title the title of digital. It is a <code>java.lang.String</code>
     * object
     * @param description the description of digital. It is a
     * <code>java.lang.String</code> object
     * @param image the image of digital. It is a <code>java.lang.String</code>
     * object
     * @param author the author of digital. It is a
     * <code>java.lang.String</code> object
     * @param timePost the id of digital. It is a <code>java.sql.Date</code>
     * object
     * @param shortDes the author of digital. It is a
     * <code>java.lang.String</code> object
     */
    public Digital(int id, String title, String description, String image, 
            String author, Date timePost, String shortDes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.timePost = timePost;
        this.shortDes = shortDes;
    }

    /**
     * Constructor with attributes: id, title, description, image, author,
     * timePost
     *
     * @param id the id of digital. It is a int <code>java.lang.Integer</code>
     * object
     * @param title the title of digital. It is a string <code>java.lang.String</code>
     * object
     * @param description the description of digital. It is a string
     * <code>java.lang.String</code> object
     * @param image the image of digital. It is a string <code>java.lang.String</code>
     * object
     * @param author the author of digital. It is a string
     * <code>java.lang.String</code> object
     * @param timePost the id of digital. It is a date <code>java.sql.Date</code>
     * object
     */
    public Digital(int id, String title, String description, String image, String author, Date timePost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.timePost = timePost;
    }

    /**
     *
     * @return the id of digital. It is an int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id of digital. It is an int.
     * object
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the title of digital. It is a String
     * object
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title of digital. It is a String 
     * object
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the description of digital. It is a String
     * object
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the title of digital. It is a String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the image of digital. It is a String
     * object
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image the title of digital. It is a String
     * object
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return the author of digital. It is a String
     * object
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author the title of digital. It is a String
     * object
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return the time post of digital. It is a date
     * <code>java.sql.Date</code> object
     */
    public Date getTimePost() {
        return timePost;
    }

    /**
     *
     * @param timePost the time post of digital. It is a date <code>java.sql.Date</code>
     * object
     */
    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    /**
     *
     * @return the short description of digital. It is a String
     */
    public String getShortDes() {
        return shortDes;
    }

    /**
     *
     * @param shortDes description the title of digital. It is a String
     */
    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

}
