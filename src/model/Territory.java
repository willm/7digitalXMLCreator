package model;

public class Territory {
	
	public Boolean isRestrictedTo;
	public String code;
	public String releaseDate;
	public String priceCode;
	public String currencyCode;
	public String price;
		
	public Territory(
		Boolean isRestrictedTo, 
	    String code, 
	    String releaseDate, 
	    String priceCode, 
	    String price, 
	    String currencyCode)
		{
			this.isRestrictedTo = isRestrictedTo;
			this.code = code;
			this.releaseDate = releaseDate;
			this.priceCode = priceCode;
			this.currencyCode = currencyCode;
			this.price = price;
		}
}