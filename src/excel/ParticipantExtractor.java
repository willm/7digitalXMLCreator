package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import main.Xml;

public class ParticipantExtractor extends DataExtractor {
	
	private HSSFRow row;
	private boolean successful = true;
	private int participantNameColumn = 27;
	private int participantRoleColumn = 28;

	public void addTrackParticipants(Xml xml, HSSFRow currentRow) throws Exception{
		row = currentRow;
    	
    	String[] names = getParticipantNames(participantNameColumn);
    	String[] roles = getParticipantRoles(participantRoleColumn);
    	if(names.length != roles.length){
		    successful = false;
		}
    	for(int i=0; i<roles.length; i++){
    		xml.addTrackParticipant(xml.numberOfTracks()-1,roles[i], names[i]);
    	} 
    	if(!successful){
        	throw new Exception("Track participants error UPC : " +xml.getProduct().Upc);
        }
    }
    
    public void addProductParticipants(Xml xml,  HSSFRow currentRow) throws Exception{
    	row = currentRow;
    	int participantNameColumn = 11;
    	int participantRoleColumn = 12;
    	String[] names = getParticipantNames(participantNameColumn);
    	String[] roles = getParticipantRoles(participantRoleColumn);
    	if(names.length != roles.length){
		    successful = false;
		}
    	for(int i=0; i<roles.length; i++){
    		xml.addParticipant(roles[i], names[i]);
    	}
    	if(!successful){
            throw new Exception("Participants error UPC : " + xml.getProduct().Upc);
        }
    }

	private String[] getParticipantRoles(int participantRoleColumn) {
		HSSFCell cell  = row.getCell(participantRoleColumn);
		String rawRoles = antiNullString(cell);
		String[] roles = rawRoles.split("-");
		if(!rawRoles.equals("")){
            for(int i=0; i<roles.length; i++){
            	roles[i] = roles[i].trim();
            }
		}
		return roles;
	}

	private String[] getParticipantNames(int parCellStart) {
		HSSFCell cell  = row.getCell(parCellStart);
		String rawNames = antiNullString(cell);
		String[] names = rawNames.split("-");
		if(!rawNames.equals("")){
			
		    for(int i=0; i<names.length; i++){
		    	names[i] = names[i].trim();
		    }
		}
		return names;
	}	
}
