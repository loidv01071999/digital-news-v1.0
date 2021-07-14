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
package dao;

import entity.Digital;
import java.util.List;

/**
 * The interface class of Digital model. Contains method get one,get top digital, 
 * search by title. The method will
 * throw an object of <code>java.lang.Exception</code> class if there is any
 * error occurring when finding data or connect
 * 
 * Bugs: None
 *
 * @author PhatNT
 */
public interface DigitalDAO{

    /**
     * Find the top latest news. The news has the latest creation date will be
     * returned The result contain a list of Digital objects with ID, title,
     * description,image, author, timePost and shortDes attributes
     *
     * @param numberOfResult is number of result you hope. It is an integer.
     * @return a list of Digital object.
     * @throws java.lang.Exception
     */
    public List<Digital> getTopDigital(int numberOfResult) throws Exception;

    /**
     * Find the news by txtSearch. All the news have title contained txtSearch
     * and in rage of page will be returned The result contain a list of
     * <code>entity.Digital</code> objects with ID, title, description,image,
     * author, timePost and shortDes attributes
     *
     * @param pageIndex the index page which you want to display. It is an
     * integer.
     * @param pageSize the size of a page. It is an integer.
     * @param txtSearch the text to search. It is a string.
     * @return a list of digital object.
     * @throws java.lang.Exception
     */
    public List<Digital> searchByTitleAndPagging(int pageIndex,
            int pageSize, String txtSearch) throws Exception;

    /**
     * Find the digital by id. Digital have id matched will be returned The
     * result contain a digital object with ID, title, description,image,
     * author, timePost and shortDes attributes
     *
     * @param id the id of digital need search. It is an integer.
     * @return a Digital object.
     * @throws java.lang.Exception
     */
    public Digital getOne(int id) throws Exception;

    /**
     * Find number of results returned when searching Digital with txtSearch.
     * The result contain integer number.
     *
     * @param txtSearch the text to search. It is a string.
     * @return number of result. It's an integer.
     * @throws java.lang.Exception
     */
    public int getNumberOfSearchResult(String txtSearch) throws Exception;
}
