package model;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class TerritorySerializer{
	Territory territory;
	
	public TerritorySerializer(Territory territory){
		this.territory = territory;
	}
	public TagCollection Serialize(){
		TagCollection theTerritory=new TagCollection("territory");
		theTerritory.addAttribute("restricted_to",territory.isRestrictedTo ? "true" : "false");
		theTerritory.addTag(new Tag("territory_code",territory.code));
		theTerritory.addTag(new Tag("territory_release_date",territory.releaseDate));
		theTerritory.addTag(new Tag("price_code",territory.priceCode));
		
		theTerritory.addTag(serializePrice());
		
		return theTerritory;
}
	private TagCollection serializePrice() {
		TagCollection wholeSalePrice = new TagCollection("wholesale_price");
		wholeSalePrice.addTag(new Tag("price",territory.price));
		wholeSalePrice.addTag(new Tag("currency_code", territory.currencyCode));
		return wholeSalePrice;
	}
}
