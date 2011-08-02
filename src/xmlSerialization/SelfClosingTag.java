package xmlSerialization;
public class SelfClosingTag extends Tag
{    

    public SelfClosingTag(String theName, String theValue)
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
