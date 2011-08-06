package model;

import java.util.ArrayList;
import java.util.List;
import model.Artist;
import xmlSerialization.*;

public class Track {

	public String Isrc; 
	public Boolean isHidden; //could be changed to boolean?
	public String TrackId;
    private Artist TrackArtist;
    public Boolean isExplicit; //could be changed to boolean?
    public String Volume;
    public int TrackNo; //could be changed to int?
    public String Type; //is this necessary?
    public String Ttitle;
    public String TversionT;
    public String Tlength;
    public String Tlabel;
    public String TPline;
    public ArrayList<Artist> Artists;
    public ArrayList<Participant> Participants;
    public List<Genre> Genres;
	
	public Track(String isrc, 
            Boolean isHidden,
            String TrackId,
            Artist TrackArtist,
            Boolean isExplicit,
            String Volume,
            int TrackNo,
            String Type, //is this necessary?
            String Ttitle,
            String TversionT,
            String Tlength,
            String Tlabel,
            String TPline,
            Genre Genre
                        )
	{
		this.Isrc = isrc; 
        this.isHidden = isHidden;
        this.TrackId = TrackId;
        this.TrackArtist = TrackArtist;
        this.isExplicit = isExplicit;
        this.Volume = Volume;
        this.TrackNo = TrackNo;
        this.Type = Type;
        this.Ttitle = Ttitle;
        this.TversionT = TversionT;
        this.Tlength = Tlength;
        this.Tlabel = Tlabel;
        this.TPline = TPline;

        Artists = new ArrayList<Artist>();
        Participants = new ArrayList<Participant>();
        
        Artists.add(TrackArtist);
        
        Genres = new ArrayList<Genre>();
        Genres.add(Genre);
	}	
}
