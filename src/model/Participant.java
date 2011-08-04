package model;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class Participant{
	private String Name;
	private String Role;
	public Participant(String name, String role){
		Name = name;
		Role = role;
	}
	public Tag serialize() {
		TagCollection serializedParticipant = new TagCollection("participant");
		serializedParticipant.addTag(new Tag("paticipant_role", Role));
		serializedParticipant.addTag(new Tag("paticipant_name", Name));
		return serializedParticipant;
	}
}