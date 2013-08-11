package mindriven.buildServer.OpenCoverRunner.agent;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 11.08.13
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    public static String trimAndGlue(String glue, String[] strArray)
    {
        String result = "";
        int lenght = strArray.length;
        for(int i=0;i<lenght;i++)
        {
            result += strArray[i].trim();
            if(i!=lenght-1)
                result += glue;
        }
        return result.trim();
    }
}
