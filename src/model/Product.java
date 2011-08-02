package model;

import java.util.ArrayList;

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
}
