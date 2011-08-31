package model;

import xmlSerialization.Tag;

public class Genre {
	
	public String Name;
	
	public Genre(String name){
		Name = name;
	}

	public Tag serialize() {
		return new Tag("genre", Name);
	}

}
