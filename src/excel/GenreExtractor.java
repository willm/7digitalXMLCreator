package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import main.Xml;

public class GenreExtractor extends DataExtractor{
	
	private static final int ProductGenreColumn = 10;
	
    public void addProductGenre(Xml theXml, HSSFRow currentRow){
    	HSSFRow row = currentRow;
        
		HSSFCell cell = row.getCell(ProductGenreColumn);
        String rawGenres = antiNullString(cell);
        String[] genres = rawGenres.split("-");
        if(!rawGenres.equals("")){
            for(int i=0; i<genres.length; i++){
                genres[i] = genres[i].trim();
                theXml.addGenre(genres[i]);
            }
        }
    }
}
