import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.SpecialOffer;
import model.TotalWithDiscountInfo;

import com.anatwine.service.BasketPriceService;
import com.anatwine.service.BasketPriceServiceImpl;
import com.anatwine.util.ItemUtil;

public class AnatwineBasket
{
	private static final Map<String, BigDecimal> CATALOGUE = new HashMap<String, BigDecimal>();
	private static final List<SpecialOffer> SPECIAL_OFFERS = new ArrayList<SpecialOffer>();
	private static final String CURRENCY_SYMBAL = "£";

	static {
		CATALOGUE.put( "Jacket", new BigDecimal( "49.90" ) );
		CATALOGUE.put( "Trousers", new BigDecimal( "35.50" ) );
		CATALOGUE.put( "Shirt", new BigDecimal( "12.50" ) );
		CATALOGUE.put( "Tie", new BigDecimal( "9.50" ) );

		SPECIAL_OFFERS.add( new SpecialOffer( "Trousers", 1, "Trousers", 10,
				"Trousers have a 10% discount off their normal price this week" ) );
		SPECIAL_OFFERS.add( new SpecialOffer( "Shirt", 2, "Tie", 50, "Buy 2 shirts and get a tie for half price" ) );
	}

	public static void main( String[] args ) {

		ItemUtil itemUtil = new ItemUtil();
		BasketPriceService basketPriceService = new BasketPriceServiceImpl();

		if( args.length == 0 ) {
			System.out.println( "You need to specify at least one item to buy." );
			System.out.println( "Usage:" );
			System.out.println( "AnatwineBasket item1 item2 item3 ..." );
			return;
		}

		String invalidItems = itemUtil.getInvalidItems( CATALOGUE, args );
		if( invalidItems != null ) {
			System.out.println( "There are invalid items in the input as follows: " + invalidItems );
			return;
		}

		Map<String, Integer> lineItems = itemUtil.buildLineItems( args );

		BigDecimal subTotal = basketPriceService.getSubTotal( lineItems, CATALOGUE );
		TotalWithDiscountInfo totalWithDiscountInfo = basketPriceService.getTotalWithDiscountInfo( lineItems, CATALOGUE,
				SPECIAL_OFFERS );

		System.out.println( "Sub total: " + CURRENCY_SYMBAL + subTotal );
		if( totalWithDiscountInfo.getDiscountDescription().length() == 0 ) {
			System.out.println( "(No offers available)" );
		} else {
			System.out.println( totalWithDiscountInfo.getDiscountDescription() );
		}
		System.out.println( "Total: " + CURRENCY_SYMBAL + totalWithDiscountInfo.getAmount() );

	}

}
