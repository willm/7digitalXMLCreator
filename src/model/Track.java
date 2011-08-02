package model;

import java.util.ArrayList;
import java.util.List;
import model.Artist;
import xmlSerialization.*;

public class Track {

	String Isrc; 
    String isHidden; //could be changed to boolean?
    String TrackId;
    Artist TrackArtist;
    String isExplicit; //could be changed to boolean?
    String Volume;
    String TrackNo; //could be changed to int?
    String Type; //is this necessary?
    String Ttitle;
    String TversionT;
    String Tlength;
    String Tlabel;
    String TPline;
    private List<String> genres;
	
	public Track(String Isrc, 
            String isHidden, //could be changed to boolean?
            String TrackId,
            Artist TrackArtist,
            String isExplicit, //could be changed to boolean?
            String Volume,
            String TrackNo, //could be changed to int?
            String Type, //is this necessary?
            String Ttitle,
            String TversionT,
            String Tlength,
            String Tlabel,
            String TPline,
            String Genre
                        )
	{
		this.Isrc = Isrc; 
        this.isHidden = isHidden; //could be changed to boolean?
        this.TrackId = TrackId;
        this.TrackArtist = TrackArtist;
        this.isExplicit = isExplicit; //could be changed to boolean?
        this.Volume = Volume;
        this.TrackNo = TrackNo; //could be changed to int?
        this.Type = Type; //is this necessary?
        this.Ttitle = Ttitle;
        this.TversionT = TversionT;
        this.Tlength = Tlength;
        this.Tlabel = Tlabel;
        this.TPline = TPline;

        genres = new ArrayList<String>();
        genres.add(Genre);
	}

	public TagCollection Serialize() {
		return new TagCollection("track");
	}
}
