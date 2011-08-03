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
    private ArrayList<Artist> Artists;
    private ArrayList<Participant> Participants;
    private List<Genre> Genres;
	
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

        Artists = new ArrayList<Artist>();
        Participants = new ArrayList<Participant>();
        
        Genres = new ArrayList<Genre>();
        Genres.add(Genre);
	}

	public TagCollection Serialize() {
		TagCollection theTrack = new TagCollection("track");

		theTrack.addAttribute("isrc", Isrc);
	    theTrack.addAttribute("hidden", isHidden ? "true" : "false");
	    theTrack.addTag(new Tag("track_identifier", TrackId));
		
	    theTrack.addTag(addArtists(theTrack));
	    theTrack.addTag(addParticipants(theTrack));
	    
	    theTrack.addTag(new Tag("explicit_content", isExplicit ? "true" : "false"));	    
	    theTrack.addTag(new Tag("track_volume", Volume));	    
	    theTrack.addTag(new Tag("track_number", Integer.toString(TrackNo)));	    
	    theTrack.addTag(new Tag("track_type", Type));	    
	    theTrack.addTag(new Tag("track_title", Ttitle));	    
	    theTrack.addTag(new Tag("track_version_title", TversionT));	    
	    theTrack.addTag(new Tag("track_length", Tlength));	    
	    theTrack.addTag(new Tag("track_label", Tlabel));	    
	    theTrack.addTag(new Tag("track_p_line", TPline));
	    
	    theTrack.addTag(addGenres());
		
		return theTrack;
	}	
	
	private TagCollection addGenres() {
		TagCollection trackGenres = new TagCollection("genres");
		for(Genre genre : Genres){
			trackGenres.addTag(genre.serialize());
		}
	    return trackGenres;
	}

	private TagCollection addParticipants(TagCollection theTrack) {
		TagCollection trackParticipants = new TagCollection("participants");
		for(Participant participant : Participants){
			trackParticipants.addTag(participant.serialize());
		}
		return trackParticipants;
	}

	private TagCollection addArtists(TagCollection theTrack) {
		TagCollection trackArtists = new TagCollection("track_artists");
		for(Artist artist : Artists){
			trackArtists.addTag(artist.serialize());
		}
		return trackArtists;
	}
}
