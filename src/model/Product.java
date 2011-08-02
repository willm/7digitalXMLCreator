package model;

import java.util.ArrayList;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class Product {
	private String Upc; 
	private String Type; 
	private String Label;
	private String Image;
	private String Title;
	private String ReleaseDate;
	private String PublisherLine;
	private String CopyrightLine;
	private String Artist;
	private String IsExplicit;
	private ArrayList<Track> Tracks;
	private ArrayList<Participant> Participants;
	private ArrayList<Artist> Artists;
	
	public Product(String theUpc, 
                String thePtype, 
                String theLabel,
                String theImage,
                String thePtitle,
                String theRdate,
                String thePublisherLine,
                String theCopywLine,
                String theArtist,
                String isExplicit){
		
		Upc = theUpc;
		Type = thePtype;
		Label= theLabel;
		Image = theImage;
		Title = thePtitle;
		ReleaseDate = theRdate;
		CopyrightLine = theCopywLine;
		Artist = theArtist;
		IsExplicit = isExplicit;
		Tracks = new ArrayList<Track>();
		Participants = new ArrayList<Participant>();
		Artists = new ArrayList<Artist>();
	}
	
	public void addTrack(Track track){
		Tracks.add(track);
	}
	
	public TagCollection Serialize(){
		TagCollection product = new TagCollection("product");
		product.addAttribute("upc", Upc);
		
		product.addTag(new Tag("product_type",Type));
		product.addTag(new Tag("product_label",Label));
		product.addTag(new Tag("product_image",Image));
		product.addTag(new Tag("product_title",Title));
		product.addTag(new Tag("product_release_date",ReleaseDate));
        product.addTag(new Tag("product_p_line",PublisherLine));
        product.addTag(new Tag("product_c_line",CopyrightLine));
        
        product.addTag(AddTracks());
		
		return product;
	}
	
	private TagCollection AddTracks(){
		TagCollection tracks = new TagCollection("tracks");
		for(Track track : Tracks){
			tracks.addTag(track.Serialize());
		}
		return tracks;
	}
	
}
