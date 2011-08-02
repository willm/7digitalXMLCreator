package xmlSerialization;
import java.util.ArrayList;

public class TagCollection extends Tag
{
    private ArrayList<Tag> tags = new ArrayList<Tag>();

    public TagCollection(String theName)
    {
        super(theName, null);
        super.value=null;
    }

   public void addTag(Tag theTag){
       tags.add(theTag);
    }
    
    public void replaceTag(int pos, Tag theTag){
        tags.set(pos, theTag);
    }
    
    public void print()
    {
        System.out.print(printXml());        
    }
    
    public String printXml()
    {
        if(attributes.size()>0){
            opener =  "<"+name+" ";
            for(String key : attributes.keySet()){
                opener += key + "=\"" + attributes.get(key) + "\" ";
            }
            opener += ">";
        }
        
        String output = (opener);
        for(Tag tag : tags){
                output += tag.printXml();
        }
        output += closer;
        return output;
    }
    
    public Tag accessInside(int pos){
        return tags.get(pos);
    }
    
    public void removeTag(int pos){
        tags.remove(pos);
    }
    
    public int insideSize(){
        return tags.size();
    }
    
    public void replace(String theName, String theNewVal){
        for(int i=0; i<tags.size(); i++){
            Tag currentTag = tags.get(i);
                if (currentTag.name == theName){
                    currentTag.editValue(theNewVal);
                }
            }
        }
        
    public String returnVal(String theName){
        for(int i=0; i<tags.size(); i++){
            Tag currentTag = tags.get(i);
                if (currentTag.name == theName){
                    return(currentTag.value);
                }
        }
        return(null);
    }
}
