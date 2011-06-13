import java.io.*;
import java.util.ArrayList;

public class Xml
{
    private String testanswer =null;

    private String filename =null;
    private Tag[] initinfo = new Tag[9];
    private Tag productArtist;
    private Tag album;
    private Tag year;
    //private ArrayList tracks;
    private BCollection participants = new BCollection("paricipants");
    private Tag explictC;
    private ArrayList genres;
    private BCollection territories = new BCollection("territory_restrictions");
    private BCollection tracks = new BCollection("tracks");

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
                String isExplicit
                )
    {
        filename = theUpc;
        initinfo[0] = new Tag("distributor",theDist);
        initinfo[1] = new STag("product upc",theUpc);
        //this is actually a cheat maybe upc should be added via add attribute method
        initinfo[2] = new Tag("product_type",thePtype);
        initinfo[3] = new Tag("product_label",theLabel);
        initinfo[4] = new Tag("product_image",theImage);
        initinfo[5] = new Tag("product_title",thePtitle);
        initinfo[6] = new Tag("product_release_date",theRdate);
        initinfo[7] = new Tag("product_p_line",thePublisherLine);
        initinfo[8] = new Tag("product_c_line",theCopywLine);
        
        
        productArtist = new Tag("product_artist_name",theArtist);
        productArtist.addAttribute("main","yes");
        //this hard codes the main attribute (may have to be changed)
        
        explictC = new Tag("explicit_content", isExplicit);
        
        //participants = new ArrayList();
        //tracks = new ArrayList();
        genres = new ArrayList();
    }
   
    public void addTrack(String theIsrc, 
                        String isHidden, //could be changed to boolean?
                        String theTrackId,
                        String theTrackArtist,
                        String isExplicit, //could be changed to boolean?
                        String theVolume,
                        String theTrackNo, //could be changed to int?
                        String theType, //is this necessary?
                        String theTtitle,
                        String theTversionT,
                        String theTlength,
                        String theTlabel,
                        String theTPline,
                        String theGenre
                        ){
        BCollection theTrack=new BCollection("track");
        theTrack.addAttribute("isrc", theIsrc);
        theTrack.addAttribute("hidden", isHidden);
        
        theTrack.addTag(new Tag("track_identifier", theTrackId));
        
        BCollection trackArtists = new BCollection("track_artists");
        Tag tkAtstNm = new Tag("track_artist_name",theTrackArtist);
        tkAtstNm.addAttribute("main","yes");
        //maybe this shouldn't be hardcoded
        trackArtists.addTag(tkAtstNm);
        theTrack.addTag(trackArtists);
        
        BCollection trackParticipents = new BCollection("participants");
        
        theTrack.addTag(trackParticipents);
        
        theTrack.addTag(new Tag("explicit_content", isExplicit));
        
        theTrack.addTag(new Tag("track_volume", theVolume));
        
        theTrack.addTag(new Tag("track_number", theTrackNo));
        
        theTrack.addTag(new Tag("track_type", theType));
        
        theTrack.addTag(new Tag("track_title", theTtitle));
        
        theTrack.addTag(new Tag("track_version_title", theTversionT));
        
        theTrack.addTag(new Tag("track_length", theTlength));
        
        theTrack.addTag(new Tag("track_label", theTlabel));
        
        theTrack.addTag(new Tag("track_p_line", theTPline));
        
        BCollection trackGenres = new BCollection("genres");
        trackGenres.addTag(new Tag("genre",theGenre));
        theTrack.addTag(trackGenres);
        
        tracks.addTag(theTrack);
        //creates a new track collection tag and adds it to the Tracks arraylist
    }
    
     public void addParticipant(String theProle, String thePname){
        BCollection theParticipant=new BCollection("participant");
        theParticipant.addTag(new Tag("participant_role", theProle));
        theParticipant.addTag(new Tag("participant_name", thePname));
        participants.addTag(theParticipant);
        //creates a new participant collection tag and adds it to the participants arraylist
    }
    
    public void addGenre(String theGenre){
        Tag genre=new Tag("genre",theGenre);
        genres.add(genre);
    }
    
    public void removeGenres(){
        genres = new ArrayList();
    }
    
    
    public void addTerritory(String restrictedTo, 
                            String tCode, 
                            String tReleaseDate, 
                            String thePriceCode, 
                            String thePrice, 
                            String theCurrencyCode)
    {
        BCollection theTerritory=new BCollection("territory");
        theTerritory.addAttribute("restricted_to",restrictedTo);
        theTerritory.addTag(new Tag("territory_code",tCode));
        theTerritory.addTag(new Tag("territory_release_date",tReleaseDate));
        theTerritory.addTag(new Tag("price_code",thePriceCode));
        
        BCollection theWSP = new BCollection("wholesale_price");
        theWSP.addTag(new Tag("price",thePrice));
        theWSP.addTag(new Tag("currency_code", theCurrencyCode));
                
        theTerritory.addTag(theWSP);
        
        territories.addTag(theTerritory);
    }
        
    public void print()
    {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        System.out.println("<products>");
        
        productArtist.print();
        album.print();
        year.print();
        
        System.out.println("<tracks>");
        
        /**for(int i=0; i<tracks.size(); i++){
            Collection temp =(Collection) tracks.get(i);
            temp.print();
        }
        */
        System.out.println("</tracks>");
        
        System.out.println("</products>");
    }
    
    public void printXml()
    {
        File file=new File("xml");
        boolean exists = file.exists();
        //checks if xml directory exists
        if (!exists) {
            try{
                boolean success = (new File("xml")).mkdir();
                if (success) {
                  System.out.println("Directory: " + "xml" + " created");
                }
                //creates xml directory if it does not
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
            PrintStream out = new PrintStream(new FileOutputStream("xml/"+filename+".xml"));
            
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            out.println("<products xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"7dig.xsd\">");
            out.println("<!--CREATED USING THE 7digital XML CREATOR-->");
            
            for(int i =0; i<9; i++){
                out.println(initinfo[i].printXml());
            }
            //prints the initinfo array
            
            out.println("<product_artists>");
            
                out.println(productArtist.printXml());
                //prints product artists
            
            out.println("</product_artists>");
                       
            out.print(participants.printXml());
            //participants list 
            
            out.println(explictC.printXml());
            //prints wheteher explicit content is present
            
            out.println("<genres>");
            //prints genres list
            
                for(int i=0; i<genres.size(); i++){
                    Tag temp = (Tag)genres.get(i);
                    out.println(temp.printXml());
                }
            
            out.println("</genres>");
            
            out.print(territories.printXml());
            //territory_restrictions list
            
            
            
            out.print(tracks.printXml());
            //tracks list
            
                /**for(int i=0; i<tracks.size(); i++){
                Collection temp =(Collection) tracks.get(i);
                //gets each track collection out of the tracks ArrayList
                String[] currentTag = temp.printToXml();
                //gets the string Array output by the printToXml method
                    for(int j=0; j<currentTag.length; j++){
                        out.println(currentTag[j]);
                        //iterates over all tags in the current track and prints them to a new line
                    }
                }*/
            
            
            out.println("</product>");
            out.println("</products>");
            
            out.close();
        }
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    public int numOfTracks(){
        return(tracks.insideSize());
    }
    
    public void editTag(String title, String newVal){
        for(int i =0; i<initinfo.length; i++){
            if(initinfo[i].returnName().equals(title)){
                initinfo[i].editValue(newVal);
                }                
            }
            if(title.equals("product upc")){
                filename=newVal;
            }
        }
    
    public BCollection gettrck(int i){
        BCollection theTrack = (BCollection)tracks.accessInside(i);
        theTrack.print();
        return(theTrack);
    }
    
    public void editTrackTagValue(int tNo, String title, String newVal){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        track.replace(title,newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        track.print();
    }
    

    
    public void editTrackTagAttribute(int tNo, String attribute, String newVal){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        track.editAttribute(attribute, newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        track.print();
    }
    
    public String getTrackTagValue(int tNo, String title){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        return(track.returnVal(title));
    }
    
    public String getTrackAtrbtValue(int tNo, String title){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        System.out.println("ATTRIBUTE = " + track.returnAtrbtVal(title));
        return(track.returnAtrbtVal(title));
    }
    
    public String getTrackArtistValue(int tNo){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection artists = (BCollection)track.accessInside(1);
        //gets the artists collection from the track
        System.out.println("Track ARTIST = " + artists.returnVal("track_artist_name"));
        return(artists.returnVal("track_artist_name"));
    }
    
    public String getTrackGenreValue(int tNo){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection genres = (BCollection)track.accessInside(12);
        //gets the artists collection from the track
        System.out.println("Track GENRE = " + genres.returnVal("genre"));
        return(genres.returnVal("genre"));
    }
      
    public void addTrackParticipent(int tNo, String pRole, String pName){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection participents = (BCollection)track.accessInside(2);
        //gets the participent BCollection from the track
        BCollection participent = new BCollection("participent");
        //adds a participent collection to the participents collection
        participent.addTag(new Tag("participant_role",pRole));
        participent.addTag(new Tag("participant_name",pName));
        //adds participent info to participent collection
        participents.addTag(participent);
    }
    
    public String getTrackParticipentTagValue(int tNo, int pNo, String title){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection participents = (BCollection)track.accessInside(2);
        //gets the participent BCollection from the track
        BCollection participent = (BCollection)participents.accessInside(pNo);
        return(participent.returnVal(title));
    }
    
    public void editTrackParTagValue(int tNo, int parNo, String title, String newVal){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection participents = (BCollection)track.accessInside(2);
        //gets the participent BCollection from the track
        BCollection participent = (BCollection)participents.accessInside(parNo);
        participent.print();
        participent.replace(title, newVal);
        System.out.println("!1111111111111111111111111111111111111111111111111111");
        participent.print();
    }
    
    public void editTrackArtistValue(int tNo, String newArt){
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection artists = (BCollection)track.accessInside(1);
        //gets the artists collection from the track
        
        artists.replace("track_artist_name",newArt);
        System.out.println("New Track ARTIST = " + artists.returnVal("track_artist_name"));
        }                
    
    public String[] getTrackParticipents(int tNo){
        String[] result;
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        BCollection participents = (BCollection)track.accessInside(2);
        int size = participents.insideSize();
        result = new String[size];
        BCollection curParticipent;
        for(int i =0; i<size; i++){
            curParticipent = (BCollection)participents.accessInside(i);
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
        BCollection track;
        track = (BCollection)tracks.accessInside(tNo);
        //gets the track
        BCollection participents = (BCollection)track.accessInside(2);
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
        BCollection ter;
        ter = (BCollection)territories.accessInside(terNo);
        ter.print();
    }
    
    public String getTerTagValue(int terNo, String name){
        BCollection ter;
        ter = (BCollection)territories.accessInside(terNo);
        return ter.returnVal(name);
    }
    
    /**public String getTerCurValue(int terNo){
        BCollection ter, wsp;
        ter = (BCollection)territories.accessInside(terNo);
        wsp = (BCollection)ter.accessInside(3);
        //gets whole sale price collection from territory
        return wsp.returnVal("currency_code");
    }*/
    
    public void editTerTagValue(int terNo, String title, String newVal){
        BCollection ter;
        ter = (BCollection)territories.accessInside(terNo);
        ter.replace(title,newVal);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        ter.print();
    }
    
    /**public void editTerCurValue(int terNo, String newVal){
        BCollection ter, wsp;
        ter = (BCollection)territories.accessInside(terNo);
        wsp = (BCollection)ter.accessInside(3);
        //gets whole sale price collection from territory
        wsp.print();
        wsp.replace("currency_code",newVal);
        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        wsp.print();
    }*/
    
    public String getTerAtrbtValue(int terNo, String title){
        BCollection ter;
        ter = (BCollection)territories.accessInside(terNo);
        System.out.println("ATTRIBUTE = " + ter.returnAtrbtVal(title));
        return(ter.returnAtrbtVal(title));
    }
    
    public void editTerAtrbtValue(int terNo, String title, String newVal){
        BCollection ter;
        ter = (BCollection)territories.accessInside(terNo);
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
        BCollection par;
        par = (BCollection)participants.accessInside(parNo);
        par.print();
    }
    
    public String getParTagValue(int parNo, String name){
        BCollection par;
        par = (BCollection)participants.accessInside(parNo);
        return par.returnVal(name);
    }
        
    public void editParTagValue(int parNo, String title, String newVal){
        BCollection par;
        par = (BCollection)participants.accessInside(parNo);
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
