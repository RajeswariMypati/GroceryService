/**
 * 
 */
package com.online.groceries.service;

import java.util.List;

import com.online.groceries.util.GroceryServiceException;
import com.online.groceries.web.vo.GroceryVO;

/**
 * Grocery Service Interface
 * @author  Rajeswari Mypati
 *
 */
public interface GroceryService {
	
	public void addGrocery(GroceryVO groceryVO) throws GroceryServiceException;
	
	public GroceryVO getGroceryById(int groceryId) throws GroceryServiceException;
	
	public void updateGrocery(GroceryVO groceryVO) throws GroceryServiceException; 
	
	public List<GroceryVO> getGroceriesList() throws GroceryServiceException;
	
	public List<GroceryVO>  filterGroceryByName(String groceryName) throws GroceryServiceException;
	
	public void batchGroceryInsert(List<GroceryVO> groceryVOList) throws GroceryServiceException;
	
}
