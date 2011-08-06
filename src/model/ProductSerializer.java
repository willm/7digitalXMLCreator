package model;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class ProductSerializer{
	
	private Product product;
	
	public ProductSerializer(Product theProduct){
		product = theProduct;
	}

	public TagCollection Serialize(){
		TagCollection serializedProduct = new TagCollection("product");
		serializedProduct.addAttribute("upc", product.Upc);
		
		serializedProduct.addTag(new Tag("product_type",product.Type));
		serializedProduct.addTag(new Tag("product_label",product.Label));
		serializedProduct.addTag(new Tag("product_image",product.Image));
		serializedProduct.addTag(new Tag("product_title",product.Title));
		serializedProduct.addTag(new Tag("product_release_date",product.ReleaseDate));
        serializedProduct.addTag(new Tag("product_p_line",product.PublisherLine));
        serializedProduct.addTag(new Tag("product_c_line",product.CopyrightLine));
        
        serializedProduct.addTag(serializeArtists());
        serializedProduct.addTag(serializeParticipants());
        
        serializedProduct.addTag(new Tag("explicit_content", product.IsExplicit ? "true" : "false"));
        
        serializedProduct.addTag(serializeGenres());
        
        serializedProduct.addTag(serializeTerritories());
        
        serializedProduct.addTag(serializeTracks());
        
		return serializedProduct;
	}
	
	private Tag serializeTerritories() {
		TagCollection serializedTerritories = new TagCollection("territory_restrictions");
		for(Territory territory : product.territories){
			TerritorySerializer territorySerializer = new TerritorySerializer(territory);
			serializedTerritories.addTag(territorySerializer.Serialize());
		}
		return serializedTerritories;
	}

	private Tag serializeGenres() {
		TagCollection serializedGenres = new TagCollection("genres"); 
		for(Genre genre : product.Genres){
			serializedGenres.addTag(genre.serialize());
		}
		return serializedGenres;
	}

	private TagCollection serializeParticipants() {
		TagCollection productParticipants = new TagCollection("participants");
		for(Participant participant : product.Participants){
			productParticipants.addTag(participant.serialize());
		}
		return productParticipants;
	}

	private TagCollection serializeArtists() {
		TagCollection productArtists = new TagCollection("product_artists");
		for(Artist artist : product.Artists){
			productArtists.addTag(artist.serialize("product"));
		}
		return productArtists;
	}

	private TagCollection serializeTracks(){
		
		TagCollection tracks = new TagCollection("tracks");
		for(Track track : product.Tracks){
			TrackSerializer trackSerializer = new TrackSerializer(track);
			tracks.addTag(trackSerializer.Serialize());
		}
		return tracks;
	}
	
}
