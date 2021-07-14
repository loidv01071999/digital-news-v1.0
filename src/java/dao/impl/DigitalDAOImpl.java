/*
 *Copyright(C) 2021,  FPTU.
 * J3.L.P0004 :
 *  Digital News
 *
 * Record of change:
 * DATE                       Version             AUTHOR            DESCRIPTION
 * 2021-05-10                  1.0                PhatNT         Start implement
 * 2021-05-12                  1.0                PhatNT         Test, Comment
 * 2021-05-24                  1.0                PhatNT         Review
 * 2021-05-31                  1.0                PhatNT         Fixbug, Fixcomment
 */
package dao.impl;

import context.DBContext;
import dao.DigitalDAO;
import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 * The class contains method to query data from Digital table.
 * The method will
 * throw an object of <code>java.lang.Exception</code> class if there is any
 * error occurring when finding data or connect
 *
 * Bugs: None
 *
 * @author PhatNT
 */
public class DigitalDAOImpl extends DBContext implements DigitalDAO {

    /**
     * Default constructor to throws NamingException when extends DBContext
     *
     * @throws javax.naming.NamingException
     */
    public DigitalDAOImpl() throws NamingException {
        super();
    }

    /**
     * Find the top latest news. The news has the latest creation date will be
     * returned The result contain a list of Digital objects with ID, title,
     * description,image, author, timePost and shortDes attributes
     *
     * @param numberOfResult is number of result you hope. It is an int.
     * @return a list of Digital object.
     * @throws java.lang.Exception
     */
    @Override
    public List<Digital> getTopDigital(int numberOfResult) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //the list containing the results is the top digitals
        ArrayList<Digital> listTopDigital = new ArrayList<>();
        //get image path from context
        String imagePath = getImagePath();
        //query to get data from db
        String sql = "select *from(select ROW_NUMBER() over \n"
                + "(order by timepost DESC) as rn, *from digital) as x \n"
                + "where rn between 1 and ?";

        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setObject(1, numberOfResult);
            rs = ps.executeQuery();
            
            //put query results in declared list
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                digital.setImage(imagePath + digital.getImage());
                listTopDigital.add(digital);
            }
            return listTopDigital;
        } catch (Exception e) {
            
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    /**
     * Find the news by txtSearch. All the news have title contained txtSearch
     * and in rage of page will be returned The result contain a list of
     * <code>entity.Digital</code> objects with ID, title, description,image,
     * author, timePost and shortDes attributes
     *
     * @param pageIndex the index page which you want to display. It is an
     * int.
     * @param pageSize the size of a page. It is an int.
     * @param txtSearch the text to search. It is a String.
     * @return a list of digital object.
     * @throws java.lang.Exception
     */
    @Override
    public List<Digital> searchByTitleAndPagging(int pageIndex, int pageSize, String txtSearch) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //the list containing results when doing search by title
        ArrayList<Digital> listResult = new ArrayList<>();
        //get image path from context
        String imagePath = getImagePath();
        //get value between start to end
        int start = (pageIndex - 1) *pageSize + 1;
        int end = pageIndex * pageSize;
        //query to get data from db
        String sql = "select *from(\n"
                + "                select ROW_NUMBER() over (order by ID ASC) as No, *\n"
                + "                from digital where title like ?\n"
                + "                )as x\n"
                + "                where No between ?\n"
                + "                and ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setObject(2, start);
            ps.setObject(3, end);
            rs = ps.executeQuery();
            //put query results in declared list
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                digital.setImage(imagePath + digital.getImage());
                listResult.add(digital);
            }
            return listResult;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(connection);
        }
    }

    /**
     * Find the digital by id. Digital have id matched will be returned The
     * result contain a digital object with ID, title, description,image,
     * author, timePost and shortDes attributes
     *
     * @param id the id of digital need search. It is an int.
     * @return a Digital object.
     * @throws java.lang.Exception
     */
    @Override
    public Digital getOne(int id) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //get image path from context
        String imagePath = getImagePath();
        //query to get data from db
        String sql = "Select * from digital where ID = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            //read the results returned when searching by id of digital
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                digital.setImage(imagePath + digital.getImage());
                return digital;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(connection);
        }
        return null;
    }

    /**
     * Find number of results returned when searching Digital with txtSearch.
     * The result contain int number.
     *
     * @param txtSearch the text to search. It is a string.
     * @return number of result. It's an int.
     * @throws java.lang.Exception
     */
    @Override
    public int getNumberOfSearchResult(String txtSearch) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //query to get data from db
        String sql = "Select count(ID) from digital where title like ?";
        //number of result when searching by title
        int count = 0;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            //set value for count
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(connection);
        }
        return count;
    }

}
