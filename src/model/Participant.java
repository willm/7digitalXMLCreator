package model;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class Participant{
	private String Name;
	private String Role;
	public Participant(String role, String name){
		Name = name;
		Role = role;
	}
	public TagCollection serialize() {
		TagCollection serializedParticipant = new TagCollection("participant");
		serializedParticipant.addTag(new Tag("paticipant_role", Role));
		serializedParticipant.addTag(new Tag("paticipant_name", Name));
		return serializedParticipant;
	}
}