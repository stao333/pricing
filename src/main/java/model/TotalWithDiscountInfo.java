package model;

import java.math.BigDecimal;

/**
 * TotalWithDiscountInfo class represents the total amount of the transaction and reasons for the discounts if any.
 * 
 * @author Sha
 * 
 */
public class TotalWithDiscountInfo
{
	private BigDecimal amount;
	private String discountDescription;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount( BigDecimal amount ) {
		this.amount = amount;
	}

	public String getDiscountDescription() {
		return discountDescription;
	}

	public void setDiscountDescription( String discountDescription ) {
		this.discountDescription = discountDescription;
	}

	@Override
	public String toString() {
		return "TotalWithDiscountInfo [amount=" + amount + ", discountDescription=" + discountDescription + "]";
	}

}
