package model;

import java.util.ArrayList;
import java.util.List;
import model.Artist;
import xmlSerialization.*;

public class Track {

	public String Isrc; 
	public Boolean isHidden; //could be changed to boolean?
	public String Id;
    private Artist TrackArtist;
    public Boolean IsExplicit; //could be changed to boolean?
    public String Volume;
    public int TrackNo; //could be changed to int?
    public String Type; //is this necessary?
    public String Title;
    public String VersionTitle;
    public String Length;
    public String Label;
    public String PublisherLine;
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
        this.Id = TrackId;
        this.TrackArtist = TrackArtist;
        this.IsExplicit = isExplicit;
        this.Volume = Volume;
        this.TrackNo = TrackNo;
        this.Type = Type;
        this.Title = Ttitle;
        this.VersionTitle = TversionT;
        this.Length = Tlength;
        this.Label = Tlabel;
        this.PublisherLine = TPline;

        Artists = new ArrayList<Artist>();
        Participants = new ArrayList<Participant>();
        
        Artists.add(TrackArtist);
        
        Genres = new ArrayList<Genre>();
        Genres.add(Genre);
	}	
}
