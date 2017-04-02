package model;

/**
 * SpecialOffer class represents a special offer. It identifies the cause of the offer (sourceItem and quantity you have to buy) and
 * effect (targetItem and the percentage of discount); it also has a wordy description of the offer.
 * 
 * @author Sha
 * 
 */
public class SpecialOffer
{
	private String sourceItem;
	private int sourceItemQty;
	private String targetItem;
	private int percentageDiscount;
	private String discountDescription;

	public SpecialOffer( String sourceItem, int sourceItemQty, String targetItem, int percentageDiscount, String discountDescription ) {
		super();
		this.sourceItem = sourceItem;
		this.sourceItemQty = sourceItemQty;
		this.targetItem = targetItem;
		this.percentageDiscount = percentageDiscount;
		this.discountDescription = discountDescription;
	}

	public String getSourceItem() {
		return sourceItem;
	}

	public void setSourceItem( String sourceItem ) {
		this.sourceItem = sourceItem;
	}

	public int getSourceItemQty() {
		return sourceItemQty;
	}

	public void setSourceItemQty( int sourceItemQty ) {
		this.sourceItemQty = sourceItemQty;
	}

	public String getTargetItem() {
		return targetItem;
	}

	public void setTargetItem( String targetItem ) {
		this.targetItem = targetItem;
	}

	public int getPercentageDiscount() {
		return percentageDiscount;
	}

	public void setPercentageDiscount( int percentageDiscount ) {
		this.percentageDiscount = percentageDiscount;
	}

	public String getDiscountDescription() {
		return discountDescription;
	}

	public void setDiscountDescription( String discountDescription ) {
		this.discountDescription = discountDescription;
	}

	@Override
	public String toString() {
		return "SpecialOffer [sourceItem=" + sourceItem + ", sourceItemQty=" + sourceItemQty + ", targetItem=" + targetItem
				+ ", percentageDiscount=" + percentageDiscount + ", discountDescription=" + discountDescription + "]";
	}

}
