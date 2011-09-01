package excel;

import junit.framework.TestCase;

public class excelConverterTests extends TestCase {

	private ExcelReader excelReader = new ExcelReader();
	
	public void test() throws Exception{
		try {
			excelReader.exceltoxml("c:\\Stuff\\Will\\XMLCreator\\Excel Template\\7digital_Metadata_Template_SAVE_AS_AN_XLS_ONLY.xls");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
