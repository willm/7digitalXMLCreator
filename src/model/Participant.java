package model;

import xmlSerialization.Tag;
import xmlSerialization.TagCollection;

public class Participant{
	public String Name;
	public String Role;
	public Participant(String role, String name){
		Name = name;
		Role = role;
	}
	public TagCollection serialize() {
		TagCollection serializedParticipant = new TagCollection("participant");
		serializedParticipant.addTag(new Tag("participant_role", Role));
		serializedParticipant.addTag(new Tag("participant_name", Name));
		return serializedParticipant;
	}
}