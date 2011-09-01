package main;

import java.io.*;
import java.util.ArrayList;
import model.*;
import xmlSerialization.*;

public class Xml
{
    private String fileName =null;
    private Tag[] initinfo = new Tag[9];
    private Product product;
	private Distributor distributor;
	

    public Xml(String theDist, 
                String theUpc, 
                String thePtype, 
                String theLabel,
                String theImage,
                String thePtitle,
                String theRdate,
                String thePublisherLine,
                String theCopywLine,
                String theArtist,
                Boolean isExplicit
                )
    { 
    	distributor = new Distributor(theDist);
    	product = new Product(
    				theUpc,
    				thePtype,
    				theLabel,
    				theImage,
    				thePtitle,
    				theRdate,
    				thePublisherLine,
    				theCopywLine,
    				new Artist(theArtist, true),
    				isExplicit);
    	
        fileName = theUpc;
    }
   
    public void addTrack(String theIsrc, 
                        Boolean isHidden, //could be changed to boolean?
                        String theTrackId,
                        String theTrackArtist,
                        Boolean isExplicit, //could be changed to boolean?
                        String theVolume,
                        int theTrackNo, //could be changed to int?
                        String theType, //is this necessary?
                        String theTtitle,
                        String theTversionT,
                        String theTlength,
                        String theTlabel,
                        String theTPline,
                        String theGenre
                        ){
    	
    	Artist trackArtist = new Artist(theTrackArtist, true);
		Track track = new Track(
								theIsrc, 
								isHidden, 
								theTrackId, 
								trackArtist, 
								isExplicit, 
								theVolume, 
								theTrackNo, 
								theType, 
								theTtitle, 
								theTversionT, 
								theTlength, 
								theTlabel, 
								theTPline, 
								new Genre(theGenre));
    	product.Tracks.add(track);

    }
    
    public void replaceDistributor(String name){
    	distributor = new Distributor(name);
    }
    
    public Track getTrack(int trackNumber){
    	return product.Tracks.get(trackNumber);
    }
    
    public void addParticipant(String theProle, String thePname){    	
    	product.Participants.add(new Participant(theProle, thePname));
    }
    
    public void addGenre(String theGenre){
        product.Genres.add(new Genre(theGenre));
    }
    
    public void removeGenres(){
    	product.Genres = new ArrayList<Genre>();
    }
    
    
    public void addTerritory(Boolean isRestrictedTo, 
                            String tCode, 
                            String tReleaseDate, 
                            String thePriceCode, 
                            String thePrice, 
                            String theCurrencyCode)
    {
    	Territory territory = new Territory(isRestrictedTo,
    			tCode,tReleaseDate,thePriceCode,thePrice,theCurrencyCode);
    	product.territories.add(territory);
    }
        
    public void printXml()
    {
        File file=new File("xml");
        createDirectory(file);
        
        try {
        	new XmlFileWriter().writeToXmlFile(product, distributor, fileName);
        }
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

	private void createDirectory(File file) {
		boolean exists = file.exists();
        //checks if XML directory exists
        if (!exists) {
        	System.out.println("the file or directory you are searching does not exist : " + exists);  
            try{
                boolean success = (new File("xml")).mkdir();
                if (success) {
                  System.out.println("Directory: " + "xml" + " created");
                }
                //creates XML directory if it does not
            }            
            catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage());
            }
                  // It returns false if File or directory does not exist
              return;
        }
        
        System.out.println("the file o directory you are searching does exist : " + exists); 
	}
    
    public int numberOfTracks(){
        return(product.Tracks.size());
    }
              
    public void addTrackParticipant(int tNo, String role, String name){
        Track track = getTrack(tNo);
        track.Participants.add(new Participant(role, name));
    }
    
    public Participant getTrackParticipant(int trackNumber, int participantNumber){
    	Track track = product.Tracks.get(trackNumber);
    	return track.Participants.get(participantNumber); 
    }
    
    public void replaceTrackParticipant(int tNo, int parNo, Participant newParticipant){
    	product.Tracks.get(tNo).Participants.set(parNo, newParticipant);
    }
    
    public void removeTrackParticipent(int tNo, int pNo){
    	product.Tracks.get(tNo).Participants.remove(pNo);
    }
    
    public void removeTrack(int tNo){
    	product.Tracks.remove(tNo);
    }
    
    public void removeTerritory(int terNo){
    	product.territories.remove(terNo);
    }
    
    public Participant getParticipant(int parNo){
        return product.Participants.get(parNo);
    }    
    
    public Participant getProductParticipant(int participantNumber){
    	return product.Participants.get(participantNumber);
    }
        
    public void replaceParticipant(int parNo,Participant newParticipant){
    	product.Participants.set(parNo, newParticipant);
    }
    public void removeParticipant(int parNo){
    	product.Participants.remove(parNo);
    }
    
    public String getTagVal(int pos){
        return initinfo[pos].returnVal();
    }

	public Territory getTerritory(int territoryNumber) {
		return product.territories.get(territoryNumber);		
	}

	public Product getProduct() {
		return product;
	}    
}
