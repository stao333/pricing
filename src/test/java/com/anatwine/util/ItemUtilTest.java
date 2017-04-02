package com.anatwine.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemUtilTest
{
	private static final Map<String, BigDecimal> CATALOGUE = new HashMap<String, BigDecimal>();

	static {
		CATALOGUE.put( "Jacket", new BigDecimal( "49.90" ) );
		CATALOGUE.put( "Trousers", new BigDecimal( "35.50" ) );
	}

	private ItemUtil itemUtil;

	@Before
	public void setUp() {
		itemUtil = new ItemUtil();
	}

	@Test
	public void testBuildLineItems_Single() {
		Map<String, Integer> lineItemsMap = itemUtil.buildLineItems( new String[] { "Jacket", "Trousers" } );

		Integer qtyJacket = lineItemsMap.get( "Jacket" );
		Integer qtyCoat = lineItemsMap.get( "Coat" );

		Assert.assertEquals( new Integer( 1 ), qtyJacket );
		Assert.assertEquals( null, qtyCoat );
	}

	@Test
	public void testBuildLineItems_Multipe() {
		Map<String, Integer> lineItemsMap = itemUtil.buildLineItems( new String[] { "Jacket", "Trousers", "Jacket", "Jacket" } );

		Integer qtyJacket = lineItemsMap.get( "Jacket" );

		Assert.assertEquals( new Integer( 3 ), qtyJacket );
	}

	@Test
	public void testGetInvalidItems_Null() {
		String[] allItems = new String[] { "Jacket", "Trousers", "Jacket", "Jacket" };

		String retStr = itemUtil.getInvalidItems( CATALOGUE, allItems );

		Assert.assertNull( retStr );
	}

	@Test
	public void testGetInvalidItems_NotNull() {
		String[] allItems = new String[] { "Jacket", "Trousers", "Jacket", "Coat", "Jacket" };

		String retStr = itemUtil.getInvalidItems( CATALOGUE, allItems );

		Assert.assertNotNull( retStr );
		Assert.assertTrue( retStr.contains( "Coat" ) );
	}

}
