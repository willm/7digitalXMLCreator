package model;

import xmlSerialization.Tag;

public class Artist{
	
	public String Name;
	public Boolean IsMain;
	public Artist(String name, Boolean isMain){
		this.Name = name;
		this.IsMain = isMain;
	}
	public Tag serialize(String type) {
		Tag artist = new Tag(type+"_artist_name", Name);
		artist.addAttribute("main", IsMain ? "yes" : "no");
		return artist;
	}
}