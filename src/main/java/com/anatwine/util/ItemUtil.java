package com.anatwine.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to work with items.
 * 
 * @author Sha
 * 
 */
public class ItemUtil
{
	/**
	 * This method identifies all the input items that are not on the product catalogue.
	 * 
	 * @param catalogue
	 *            product catalogue
	 * @param allItems
	 *            all input items
	 * @return space separated invalid input items
	 */
	public String getInvalidItems( Map<String, BigDecimal> catalogue, String[] allItems ) {
		StringBuilder invalidItemsSb = new StringBuilder();
		for( String item : allItems ) {
			if( catalogue.get( item ) == null ) {
				invalidItemsSb.append( item ).append( " " );
			}
		}
		if( invalidItemsSb.length() == 0 ) {
			return null;
		} else {
			return invalidItemsSb.toString();
		}
	}

	/**
	 * This method builds line items, i.e. if there are multiple identical items they will become a single line item with a quantity
	 * of more than one.
	 * 
	 * @param allItems
	 *            all input items
	 * @return the line item map
	 */
	public Map<String, Integer> buildLineItems( String[] allItems ) {
		Map<String, Integer> lineItemMap = new HashMap<String, Integer>();

		for( String item : allItems ) {
			Integer qty = lineItemMap.get( item );
			if( qty == null ) {
				lineItemMap.put( item, new Integer( 1 ) );
			} else {
				lineItemMap.put( item, qty.intValue() + 1 );
			}
		}
		return lineItemMap;
	}

}
