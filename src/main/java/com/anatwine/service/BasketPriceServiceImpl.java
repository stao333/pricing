package com.anatwine.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import model.SpecialOffer;
import model.TotalWithDiscountInfo;

public class BasketPriceServiceImpl implements BasketPriceService
{
	private static final String CURRENCY_SYMBAL = "£";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.anatwine.service.BasketPriceService#getTotalWithDiscountInfo(java.util.Map, java.util.Map, java.util.List)
	 */
	@Override
	public TotalWithDiscountInfo getTotalWithDiscountInfo( Map<String, Integer> lineItems, Map<String, BigDecimal> catalogue,
			List<SpecialOffer> specialOffers ) {

		BigDecimal totalAmount = new BigDecimal( "0.00" );
		StringBuilder discountInfoSb = new StringBuilder();

		for( Map.Entry<String, Integer> lineItem : lineItems.entrySet() ) {
			BigDecimal lineTotal = new BigDecimal( "0.00" );
			// discount caused by this line if any
			BigDecimal discountedAmountValue = new BigDecimal( "0.00" );

			String itemName = lineItem.getKey();
			Integer itemQty = lineItem.getValue();
			lineTotal = catalogue.get( itemName ).multiply( new BigDecimal( itemQty.intValue() ) );

			for( SpecialOffer offer : specialOffers ) {
				if( itemName.equals( offer.getSourceItem() ) && itemQty.intValue() >= offer.getSourceItemQty() ) {
					// look for the item that qualify for this discount if there is one
					for( Map.Entry<String, Integer> discountItem : lineItems.entrySet() ) {
						String discountItemName = discountItem.getKey();
						Integer discountItemQty = discountItem.getValue();

						if( offer.getTargetItem().equals( discountItemName ) ) {
							BigDecimal lineDiscountValue = (catalogue.get( discountItemName ))
									.multiply( new BigDecimal( discountItemQty.intValue() ) ) // assuming you get the same discount
																							  // if you buy one, two or more ties
									.multiply( new BigDecimal( offer.getPercentageDiscount() ) ).divide( new BigDecimal( 100 ) );
							discountInfoSb.append( offer.getDiscountDescription() ).append( ":" ).append( "  -" )
									.append( CURRENCY_SYMBAL ).append( lineDiscountValue ).append( "\n" );
							discountedAmountValue = discountedAmountValue.add( lineDiscountValue );
						}
					}

				}
			}

			// the discount caused by this line may well happen on a different line but we subtract it here nevertheless
			lineTotal = lineTotal.subtract( discountedAmountValue );
			totalAmount = totalAmount.add( lineTotal );
		}

		TotalWithDiscountInfo totalWithDiscountInfo = new TotalWithDiscountInfo();
		totalWithDiscountInfo.setAmount( totalAmount );
		totalWithDiscountInfo.setDiscountDescription( discountInfoSb.toString() );

		return totalWithDiscountInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.anatwine.service.BasketPriceService#getSubTotal(java.util.Map, java.util.Map)
	 */
	@Override
	public BigDecimal getSubTotal( Map<String, Integer> lineItems, Map<String, BigDecimal> catalogue ) {

		BigDecimal total = new BigDecimal( "0.00" );

		for( Map.Entry<String, Integer> lineItem : lineItems.entrySet() ) {
			String itemName = lineItem.getKey();
			Integer qty = lineItem.getValue();
			BigDecimal itemPrice = catalogue.get( itemName );
			BigDecimal itemLineValue = itemPrice.multiply( new BigDecimal( qty.intValue() ) );
			total = total.add( itemLineValue );
		}
		return total;
	}

}
