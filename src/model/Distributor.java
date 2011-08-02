package model;

import xmlSerialization.Tag;;

public class Distributor {

	private String Name;
	
	public Distributor(String name){
		Name = name;
	}
	
	public void Serialize(){
		Tag serialisedDistributor = new Tag("distributor", Name);
		serialisedDistributor.print();
	}
}
