package com.anatwine.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import model.SpecialOffer;
import model.TotalWithDiscountInfo;

/**
 * Interface for basket price service.
 * 
 * @author Sha
 * 
 */
public interface BasketPriceService
{
	/**
	 * This method returns a {@link TotalWithDiscountInfo} object, giving the transaction total and any discount information.
	 * 
	 * @param lineItems
	 *            line items in the transaction with quantity information
	 * @param catalogue
	 *            product catalogue to be used for pricing the line items
	 * @param specialOffers
	 *            special offer rules to be applied
	 * @return a {@link TotalWithDiscountInfo} object
	 */
	public TotalWithDiscountInfo getTotalWithDiscountInfo( Map<String, Integer> lineItems, Map<String, BigDecimal> catalogue,
			List<SpecialOffer> specialOffers );

	/**
	 * This method returns the sub total of the transaction excluding any discount.
	 * 
	 * @param lineItems
	 *            line items in the transaction with quantity information
	 * @param catalogue
	 *            product catalogue to be used for pricing the line items
	 * @return the sub total amount excluding any discount
	 */
	public BigDecimal getSubTotal( Map<String, Integer> lineItems, Map<String, BigDecimal> catalogue );
}
