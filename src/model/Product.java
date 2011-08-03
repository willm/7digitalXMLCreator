package model;

import java.util.ArrayList;

public class Product {
	public String Upc; 
	public String Type; 
	public String Label;
	public String Image;
	public String Title;
	public String ReleaseDate;
	public String PublisherLine;
	public String CopyrightLine;
	public Artist Artist;
	public boolean IsExplicit;
	public ArrayList<Track> Tracks;
	public ArrayList<Participant> Participants;
	public ArrayList<Artist> Artists;
	
	public Product(String theUpc, 
                String thePtype, 
                String theLabel,
                String theImage,
                String thePtitle,
                String theRdate,
                String thePublisherLine,
                String theCopywLine,
                Artist artist,
                boolean isExplicit){
		
		Upc = theUpc;
		Type = thePtype;
		Label= theLabel;
		Image = theImage;
		Title = thePtitle;
		ReleaseDate = theRdate;
		CopyrightLine = theCopywLine;
		Artist = artist;
		IsExplicit = isExplicit;
		Tracks = new ArrayList<Track>();
		Participants = new ArrayList<Participant>();
		Artists = new ArrayList<Artist>();
		Artists.add(Artist);
	}
}
