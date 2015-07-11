package com.online.groceries.util;

public interface HibernateQueryConstants {

	public static final String DELETE_GROCERY_DATA_QUERY = "delete from grocery";
	public static final String GROCERY_LIST_BY_NAME_QUERY = "from Grocery g where g.groceryName like :groceryName order by g.groceryUpdatedOn asc";
	public static final String GROCERY_LIST_QUERY = "from Grocery g order by g.groceryUpdatedOn asc"; 			
	
}
