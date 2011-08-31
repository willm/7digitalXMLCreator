package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import xmlSerialization.*;

public class Xml
{
    private String fileName =null;
    private Tag[] initinfo = new Tag[9];
    private Tag productArtist;
    private TagCollection participants = new TagCollection("paricipants");
    private Tag explictC;
    private ArrayList<Tag> genres;
    private TagCollection territories = new TagCollection("territory_restrictions");
    private TagCollection tracks = new TagCollection("tracks");
    
    private List<Track> newTracks = new ArrayList<Track>();
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
        boolean exists = file.exists();
        //checks if XML directory exists
        if (!exists) {
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
            System.out.println("the file or directory you are searching does not exist : " + exists);    
        }
        
        else{
          // It returns true if File or directory exists
          System.out.println("the file o directory you are searching does exist : " + exists); 
        }
        
        try {
        	new XmlFileWriter().writeToXmlFile(product, distributor, fileName);
        }
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    public int numOfTracks(){
        return(product.Tracks.size());
    }
    
    public void editTag(String title, String newVal){
        for(int i =0; i<initinfo.length; i++){
            if(initinfo[i].returnName().equals(title)){
                initinfo[i].editValue(newVal);
                }                
            }
            if(title.equals("product upc")){
                fileName=newVal;
            }
        }
    
    public TagCollection gettrck(int i){
        TagCollection theTrack = (TagCollection)tracks.accessInside(i);
        theTrack.print();
        return(theTrack);
    }
    
    public void editTrackTagValue(int tNo, String title, String newVal){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        track.replace(title,newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        track.print();
    }
    

    
    public void editTrackTagAttribute(int tNo, String attribute, String newVal){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        track.editAttribute(attribute, newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        track.print();
    }
    
    public String getTrackTagValue(int tNo, String title){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        return(track.returnVal(title));
    }
    
    public String getTrackAtrbtValue(int tNo, String title){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        System.out.println("ATTRIBUTE = " + track.returnAtrbtVal(title));
        return(track.returnAtrbtVal(title));
    }
    
    public String getTrackArtistValue(int tNo){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection artists = (TagCollection)track.accessInside(1);
        //gets the artists collection from the track
        System.out.println("Track ARTIST = " + artists.returnVal("track_artist_name"));
        return(artists.returnVal("track_artist_name"));
    }
    
    public String getTrackGenreValue(int tNo){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection genres = (TagCollection)track.accessInside(12);
        //gets the artists collection from the track
        System.out.println("Track GENRE = " + genres.returnVal("genre"));
        return(genres.returnVal("genre"));
    }
      
    public void addTrackParticipent(int tNo, String pRole, String pName){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection participents = (TagCollection)track.accessInside(2);
        //gets the participent BCollection from the track
        TagCollection participent = new TagCollection("participent");
        //adds a participent collection to the participents collection
        participent.addTag(new Tag("participant_role",pRole));
        participent.addTag(new Tag("participant_name",pName));
        //adds participent info to participent collection
        participents.addTag(participent);
    }
    
    public String getTrackParticipentTagValue(int tNo, int pNo, String title){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection participents = (TagCollection)track.accessInside(2);
        //gets the participent BCollection from the track
        TagCollection participent = (TagCollection)participents.accessInside(pNo);
        return(participent.returnVal(title));
    }
    
    public void editTrackParTagValue(int tNo, int parNo, String title, String newVal){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection participents = (TagCollection)track.accessInside(2);
        //gets the participent BCollection from the track
        TagCollection participent = (TagCollection)participents.accessInside(parNo);
        participent.print();
        participent.replace(title, newVal);
        System.out.println("!1111111111111111111111111111111111111111111111111111");
        participent.print();
    }
    
    public void editTrackArtistValue(int tNo, String newArt){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection artists = (TagCollection)track.accessInside(1);
        //gets the artists collection from the track
        
        artists.replace("track_artist_name",newArt);
        System.out.println("New Track ARTIST = " + artists.returnVal("track_artist_name"));
        }                
    
    public String[] getTrackParticipents(int tNo){
        String[] result;
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        TagCollection participents = (TagCollection)track.accessInside(2);
        int size = participents.insideSize();
        result = new String[size];
        TagCollection curParticipent;
        for(int i =0; i<size; i++){
            curParticipent = (TagCollection)participents.accessInside(i);
            String[] element = new String[2];
            element[0] = curParticipent.accessInside(0).returnVal();
            element[1] = curParticipent.accessInside(1).returnVal();
            result[i] = (element[0] + " - " + element[1]);
        }
        for(int i =0; i<size; i++){
            System.out.println(result[i]);
        }
        return result;
        
    }
    
    public void removeTrackParticipent(int tNo, int pNo){
        TagCollection track;
        track = (TagCollection)tracks.accessInside(tNo);
        //gets the track
        TagCollection participents = (TagCollection)track.accessInside(2);
        participents.print();
        participents.removeTag(pNo);
        participents.print();
    }
    
    public void removeTrk(int tNo){
        tracks.print();
        tracks.removeTag(tNo);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        tracks.print();
    }
    
    public void getTer(int terNo){
        TagCollection ter;
        ter = (TagCollection)territories.accessInside(terNo);
        ter.print();
    }
    
    public String getTerTagValue(int terNo, String name){
        TagCollection ter;
        ter = (TagCollection)territories.accessInside(terNo);
        return ter.returnVal(name);
    }
    
    public void editTerTagValue(int terNo, String title, String newVal){
        TagCollection ter;
        ter = (TagCollection)territories.accessInside(terNo);
        ter.replace(title,newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        ter.print();
    }
    
    public String getTerAtrbtValue(int terNo, String title){
        TagCollection ter;
        ter = (TagCollection)territories.accessInside(terNo);
        System.out.println("ATTRIBUTE = " + ter.returnAtrbtVal(title));
        return(ter.returnAtrbtVal(title));
    }
    
    public void editTerAtrbtValue(int terNo, String title, String newVal){
        TagCollection ter;
        ter = (TagCollection)territories.accessInside(terNo);
        ter.print();
        ter.editAttribute(title, newVal);
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        ter.print();
    }
    
    public void removeTer(int terNo){
        territories.print();
        territories.removeTag(terNo);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        territories.print();
    }
    
    public void getPar(int parNo){
        TagCollection par;
        par = (TagCollection)participants.accessInside(parNo);
        par.print();
    }
    
    public String getParTagValue(int parNo, String name){
        TagCollection par;
        par = (TagCollection)participants.accessInside(parNo);
        return par.returnVal(name);
    }
        
    public void editParTagValue(int parNo, String title, String newVal){
        TagCollection par;
        par = (TagCollection)participants.accessInside(parNo);
        par.replace(title,newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        par.print();
    }
    public void removePar(int parNo){
        participants.print();
        participants.removeTag(parNo);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        participants.print();
    }
    
    public String getTagVal(int pos){
        return initinfo[pos].returnVal();
    }    
}
