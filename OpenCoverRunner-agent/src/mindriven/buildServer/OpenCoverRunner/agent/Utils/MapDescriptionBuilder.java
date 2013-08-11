package mindriven.buildServer.OpenCoverRunner.agent.Utils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 04.08.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class MapDescriptionBuilder {

    public String getDescription(Map<String, String> map, String title)
    {
        String newLine = "\n\r";
        String result = title+":"+newLine;
        if(map!=null && !map.isEmpty())
        {
            Iterator iterator = map.entrySet().iterator();
            Map.Entry<String, String> entry = null;
            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                result += entry.getKey()+" : "+entry.getValue()+newLine;
            }
        }
        else
        {
            result+= "null or empty"+newLine;
        }
        return result;
    }
}
