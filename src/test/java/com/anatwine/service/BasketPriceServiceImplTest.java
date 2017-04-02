package com.anatwine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.SpecialOffer;
import model.TotalWithDiscountInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasketPriceServiceImplTest
{
	private static final Map<String, BigDecimal> CATALOGUE = new HashMap<String, BigDecimal>();
	private static final List<SpecialOffer> SPECIAL_OFFERS = new ArrayList<SpecialOffer>();

	static {
		CATALOGUE.put( "Jacket", new BigDecimal( "49.90" ) );
		CATALOGUE.put( "Trousers", new BigDecimal( "35.50" ) );
		CATALOGUE.put( "Shirt", new BigDecimal( "12.50" ) );
		CATALOGUE.put( "Tie", new BigDecimal( "9.50" ) );

		SPECIAL_OFFERS.add( new SpecialOffer( "Trousers", 1, "Trousers", 10,
				"Trousers have a 10% discount off their normal price this week" ) );
		SPECIAL_OFFERS.add( new SpecialOffer( "Shirt", 2, "Tie", 50, "Buy 2 shirts and get a tie for half price" ) );
	}

	private BasketPriceService basketPriceService;

	@Before
	public void setUp() {
		basketPriceService = new BasketPriceServiceImpl();
	}

	@Test
	public void testGetSubTotal() {
		Map<String, Integer> lineItems = new HashMap<String, Integer>();
		lineItems.put( "Jacket", 1 );
		lineItems.put( "Shirt", 2 );
		lineItems.put( "Tie", 1 );

		BigDecimal subTotal = basketPriceService.getSubTotal( lineItems, CATALOGUE );

		Assert.assertEquals( new BigDecimal( "84.40" ), subTotal );
	}

	@Test
	public void testGetSubTotal_NoLineItem() {
		Map<String, Integer> lineItems = new HashMap<String, Integer>();

		BigDecimal subTotal = basketPriceService.getSubTotal( lineItems, CATALOGUE );

		Assert.assertEquals( new BigDecimal( "0.00" ), subTotal );
	}

	@Test
	public void testGetTotalWithDiscountInfo_DisCountOnOwnLine() {
		Map<String, Integer> lineItems = new HashMap<String, Integer>();
		lineItems.put( "Trousers", 2 );
		lineItems.put( "Tie", 1 );

		TotalWithDiscountInfo totalWithDiscountInfo = basketPriceService.getTotalWithDiscountInfo( lineItems, CATALOGUE,
				SPECIAL_OFFERS );

		Assert.assertEquals( new BigDecimal( "73.40" ), totalWithDiscountInfo.getAmount() );
		Assert.assertTrue( totalWithDiscountInfo.getDiscountDescription().contains(
				"Trousers have a 10% discount off their normal price this week" ) );
	}

	@Test
	public void testGetTotalWithDiscountInfo_DisCountOnOtherLine() {
		Map<String, Integer> lineItems = new HashMap<String, Integer>();
		lineItems.put( "Shirt", 2 );
		lineItems.put( "Tie", 1 );

		TotalWithDiscountInfo totalWithDiscountInfo = basketPriceService.getTotalWithDiscountInfo( lineItems, CATALOGUE,
				SPECIAL_OFFERS );

		Assert.assertEquals( new BigDecimal( "29.75" ), totalWithDiscountInfo.getAmount() );
		Assert.assertTrue( totalWithDiscountInfo.getDiscountDescription().contains( "Buy 2 shirts and get a tie for half price" ) );
	}

	@Test
	public void testGetTotalWithDiscountInfo_MultipleDiscounts() {
		Map<String, Integer> lineItems = new HashMap<String, Integer>();
		lineItems.put( "Trousers", 2 );
		lineItems.put( "Shirt", 2 );
		lineItems.put( "Tie", 1 );

		TotalWithDiscountInfo totalWithDiscountInfo = basketPriceService.getTotalWithDiscountInfo( lineItems, CATALOGUE,
				SPECIAL_OFFERS );

		Assert.assertEquals( new BigDecimal( "93.65" ), totalWithDiscountInfo.getAmount() );
		Assert.assertTrue( totalWithDiscountInfo.getDiscountDescription().contains(
				"Trousers have a 10% discount off their normal price this week" ) );
		Assert.assertTrue( totalWithDiscountInfo.getDiscountDescription().contains( "Buy 2 shirts and get a tie for half price" ) );
	}
}
