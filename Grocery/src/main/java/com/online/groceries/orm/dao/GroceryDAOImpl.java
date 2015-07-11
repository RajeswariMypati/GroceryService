package com.online.groceries.orm.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.online.groceries.orm.Grocery;
import com.online.groceries.util.HibernateQueryConstants;

@Repository
public class GroceryDAOImpl implements GroceryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GroceryDAOImpl.class);

	private String loggPreString = "GroceryDAOImpl.class ";
	
	/**
	 * Adds new grocery to db
	 * 
	 * @param grocery
	 */
	public void addGrocery(Grocery grocery) {
		try{
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggPreString+" addGrocery: grocery =  " + grocery);
			
			sessionFactory.getCurrentSession().save(grocery);
		}catch(HibernateException he){
			LOGGER.error(loggPreString + "Error occured while adding grocery : " + he);
		}
		
	}
	
	/**
	 * batch grocery update to db
	 * 
	 * @param groceryList
	 */
	public void batchGroceryInsert(List<Grocery> groceryList) {		
		try{
			Query deleteQuery = sessionFactory.getCurrentSession().createSQLQuery(HibernateQueryConstants.DELETE_GROCERY_DATA_QUERY);
			//remove existing data
			deleteQuery.executeUpdate();
			
			for (Grocery grocery : groceryList) {
				if(LOGGER.isDebugEnabled())
					LOGGER.debug(loggPreString+" batchGroceryInsert grocery =  "+grocery);
				
				sessionFactory.getCurrentSession().save(grocery);				
			}
			
			sessionFactory.getCurrentSession().flush();
		}catch(HibernateException he){
			LOGGER.error(loggPreString + "Error occured while adding batch grocery : " + he);
		}
		
	}
	
	/**
	 * Update exisitng grocery
	 * 
	 * @param grocery
	 */
	public void updateGrocery(Grocery grocery) {
		
		try{
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggPreString+" update grocery =  "+grocery);
			
			sessionFactory.getCurrentSession().update(grocery);
		}catch(HibernateException he){
			LOGGER.error(loggPreString + "Error occured while updating grocery : " + he);
		}	
	}
	
	/**
	 * Retreives the grocery based on grocery id
	 * 
	 * @param groceryId
	 */
	public Grocery getGroceryById(int groceryId) {
		try{
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggPreString+" getGroceryById, Lst:  "+groceryId);
			
			return (Grocery)sessionFactory.getCurrentSession().get(Grocery.class, groceryId);
		}catch(HibernateException he){
			LOGGER.error(loggPreString + "Error occured while updating grocery : " + he);
		}	
		
		return null;
	}

	/**
	 * Filters the grocery based on grocery name
	 * 
	 * @param groceryName
	 */
	@SuppressWarnings("unchecked")
	public List<Grocery> filterGroceryByName(String groceryName) {
		try{
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggPreString+" filterGroceryByName, Lst:  "+groceryName);
			
			Query hQuery = sessionFactory.getCurrentSession().createQuery(HibernateQueryConstants.GROCERY_LIST_BY_NAME_QUERY);
			hQuery.setParameter("groceryName", '%'+groceryName+'%');
			
			List<Grocery> filterList = hQuery.list();
			return filterList;
		}catch(HibernateException he){
			LOGGER.error(loggPreString + "Error occured while updating grocery : " + he);
		}	
		
		return null;
	}

	/**
	 * Feteches the all gracery list
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<Grocery> getGroceryList() {
		try{
			
			List<Grocery> groceryList = sessionFactory.getCurrentSession().createQuery(HibernateQueryConstants.GROCERY_LIST_QUERY).list();
			
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggPreString+" getGroceryList, Lst:  "+groceryList);
			
			return groceryList;
		}catch(HibernateException he){
			LOGGER.error(loggPreString + "Error occured while updating grocery : " + he);
		}	
		
		return null;
	}

}
