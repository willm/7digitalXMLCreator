package model;

import xmlSerialization.Tag;;

public class Distributor {

	private String Name;
	
	public Distributor(String name){
		Name = name;
	}
	
	public Tag Serialize(){
		Tag serialisedDistributor = new Tag("distributor", Name);
		return serialisedDistributor;
	}
}
