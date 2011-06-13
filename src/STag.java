
/**
 * This is a one line xml tag with no closing backslash
 * 
 * @author (Will Munn) 
 * @version (24/1/2010)
 */
public class STag extends Tag
{    

    public STag(String theName, String theValue)
    {
        super(theName, theValue);
        
        opener="<"+name+"=";
        value="\""+theValue+"\"";
        closer=">";
    }
    
    public void editValue(String newVal){
        value = "\""+newVal+"\"";;
    }
    
    public void print()
    {
        System.out.println(opener+value+closer);
    }
}
