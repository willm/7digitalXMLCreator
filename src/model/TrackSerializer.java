package model;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class TrackSerializer {

	private Track track;
	
	public TrackSerializer(Track track){
		this.track = track;
	}
	
	public TagCollection Serialize() {
		TagCollection theTrack = new TagCollection("track");

		theTrack.addAttribute("isrc", track.Isrc);
	    theTrack.addAttribute("hidden", track.isHidden ? "true" : "false");
	    theTrack.addTag(new Tag("track_identifier", track.Id));
		
	    theTrack.addTag(serializeArtists());
	    theTrack.addTag(serializeParticipants());
	    
	    theTrack.addTag(new Tag("explicit_content", track.IsExplicit ? "true" : "false"));	    
	    theTrack.addTag(new Tag("track_volume", track.Volume));	    
	    theTrack.addTag(new Tag("track_number", Integer.toString(track.TrackNo)));	    
	    theTrack.addTag(new Tag("track_type", track.Type));	    
	    theTrack.addTag(new Tag("track_title", track.Title));	    
	    theTrack.addTag(new Tag("track_version_title", track.VersionTitle));	    
	    theTrack.addTag(new Tag("track_length", track.Length));	    
	    theTrack.addTag(new Tag("track_label", track.Label));	    
	    theTrack.addTag(new Tag("track_p_line", track.PublisherLine));
	    
	    theTrack.addTag(serializeGenres());
		
		return theTrack;
	}	
	
	private TagCollection serializeGenres() {
		TagCollection trackGenres = new TagCollection("genres");
		for(Genre genre : track.Genres){
			trackGenres.addTag(genre.serialize());
		}
	    return trackGenres;
	}

	private TagCollection serializeParticipants() {
		TagCollection trackParticipants = new TagCollection("participants");
		for(Participant participant : track.Participants){
			trackParticipants.addTag(participant.serialize());
		}
		return trackParticipants;
	}

	private TagCollection serializeArtists() {
		TagCollection trackArtists = new TagCollection("track_artists");
		for(Artist artist : track.Artists){
			trackArtists.addTag(artist.serialize("track"));
		}
		return trackArtists;
	}
}
