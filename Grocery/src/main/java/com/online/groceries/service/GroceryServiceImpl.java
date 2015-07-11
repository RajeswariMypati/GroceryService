/**
 * 
 */
package com.online.groceries.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.groceries.orm.Grocery;
import com.online.groceries.orm.dao.GroceryDAO;
import com.online.groceries.util.GroceryServiceException;
import com.online.groceries.web.vo.GroceryVO;

/**
 * Grocery Service base class provides CRUD functionality
 * @author Rajeswari Mypati
 * 
 */

@Service
public class GroceryServiceImpl implements GroceryService {
		
	@Autowired
	GroceryDAO groceryDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(GroceryServiceImpl.class);

	private String loggerPrString = "GroceryServiceImpl.class ";
	
	/**
	 * Add Grocery Service
	 * 
	 * @param groceryVO
	 */
	@Transactional
	public void addGrocery(GroceryVO groceryVO) throws GroceryServiceException {		
		try {
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggerPrString+" addGrocery: groceryVO =  "+groceryVO);
			
			Grocery grocery = new Grocery(groceryVO);
			
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggerPrString+" addGrocery: grocery =  "+grocery);
			
			groceryDAO.addGrocery(grocery);			
		} catch(Exception ex) {
			LOGGER.error(loggerPrString+" addGrocery, Exception is: " + ex);
			throw new GroceryServiceException("Error from DAO during addGrocery", ex);
		}
	}
	
	/**
	 * Add Grocery Bacth Service
	 * 
	 * @param groceryVOList
	 */
	@Transactional
	public void batchGroceryInsert(List<GroceryVO> groceryVOList) throws GroceryServiceException {
		
		List<Grocery> groceryList = new ArrayList<Grocery>();
		
		try {
			for (GroceryVO groceryVO : groceryVOList) {
				if(LOGGER.isDebugEnabled())
					LOGGER.debug(loggerPrString+" addGrocery: groceryVO =  "+groceryVO);
				
				Grocery grocery = new Grocery(groceryVO);
				if(LOGGER.isDebugEnabled())
					LOGGER.debug(loggerPrString+" addGrocery: grocery =  "+grocery);
				//add each grocery object to grocery list
				groceryList.add(grocery);
			}
			groceryDAO.batchGroceryInsert(groceryList);			
		} catch(Exception ex) {
			LOGGER.error(loggerPrString+" batchGroceryInsert, Exception is: " + ex);
			throw new GroceryServiceException("Error from DAO during batchGroceryInsert" , ex);
		}
	}
	
	/**
	 * Retreive Grocery Service
	 * 
	 * @param groceryId
	 * @return GroceryVO
	 */
	@Transactional
	public GroceryVO getGroceryById(int groceryId) throws GroceryServiceException {
		GroceryVO groceryVO = null;
		
		try{
			Grocery grocery = groceryDAO.getGroceryById(groceryId);
			groceryVO = new GroceryVO(grocery);
		}catch(Exception ex) {
			LOGGER.error(loggerPrString+" getGroceryById, Exception is: " + ex);
			throw new GroceryServiceException("Error from DAO during getGroceryById" , ex);
		}
		
		return groceryVO;
	}

	/**
	 * Update Grocery Service
	 * 
	 * @param groceryVO
	 */
	@Transactional
	public void updateGrocery(GroceryVO groceryVO)  throws GroceryServiceException {
		try {
			/*
			 * As we are separating VO and entity objects by restricting to the layer that they belongs to (to achieve loose coupling)
			 * we need to load the entity object and update manually to save it back to data base.
			 * In case of Entity objects is share across layers, we can load it and can call merge directly. 
			 */
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggerPrString+",  updateGrocery for = "+groceryVO.getGroceryId());  
			Grocery grocery = groceryDAO.getGroceryById(groceryVO.getGroceryId());
			//populate grocery data
			grocery.setGroceryName(groceryVO.getGroceryName());
			grocery.setGroceryPrice(groceryVO.getGroceryPrice());
			grocery.setGroceryStock(groceryVO.getGroceryStock());
			
			groceryDAO.updateGrocery(grocery);			
		} catch(Exception ex) {
			LOGGER.error(loggerPrString+",  updateGrocery :  Exception = "+ex.getMessage());
			throw new GroceryServiceException("Error from DAO during updateGrocery",ex);
		}
	}

	/**
	 * Filter Grocery Service
	 * 
	 * @param groceryName
	 * @return List
	 */
	@Transactional
	public List<GroceryVO>  filterGroceryByName(String groceryName)  throws GroceryServiceException {
		
		List<GroceryVO> groceryVOList = new ArrayList<GroceryVO>();
		
		try {
			List<Grocery> groceryList = groceryDAO.filterGroceryByName(groceryName);
			for (Grocery grocery : groceryList) {
				GroceryVO vo = new GroceryVO(grocery);
				groceryVOList.add(vo);
			}
		} catch(Exception ex) {
			LOGGER.error("getGroceriesList Exception is: " + ex);			
			throw new GroceryServiceException("Error from DAO during filterGroceryByName" , ex);
		}
		return groceryVOList;
	}

	/**
	 * List All Grocery Service
	 * 
	 * @return List
	 */
	@Transactional
	public List<GroceryVO> getGroceriesList() throws GroceryServiceException {
		List<GroceryVO> groceryVOList = new ArrayList<GroceryVO>();
		try {
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggerPrString+" getGroceriesList:");
			
			List<Grocery> groceryList = groceryDAO.getGroceryList();
			
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggerPrString+" getGroceriesList, Lst:   size = "+groceryList.size());
			
			for (Grocery grocery : groceryList) {
				GroceryVO vo = new GroceryVO(grocery);
				groceryVOList.add(vo);
			}
			if(LOGGER.isDebugEnabled())
				LOGGER.debug(loggerPrString+" getGroceriesList:  "+groceryVOList.size());
//			groceryDAO.getGroceryList();			
		} catch(Exception ex) {
			LOGGER.error(loggerPrString+" getGroceriesList Exception is: " + ex);
			throw new GroceryServiceException("Error from DAO during getGroceriesList" , ex);
		}
		return groceryVOList;
	}

}
