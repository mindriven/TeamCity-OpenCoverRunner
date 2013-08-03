package mindriven.buildServer.OpenCoverRunner.agent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class ConfigValuesContainer {

    private Map<String, String> definedConfigValues = new HashMap<String, String>();
    private Map<String, String> defaultValues = new HashMap<String, String>() ;

    public void setDefaultValuesMapping(Map<String, String> defaultValues)
    {
        this.defaultValues = new HashMap<String, String>(defaultValues) ;
    }

    public Map<String, String> getDefaultValuesMapping()
    {
        return new HashMap<String, String>(this.defaultValues);
    }

    public void setDefinedConfigValues(Map<String, String> defaultValues)
    {
        this.definedConfigValues = new HashMap<String, String>(defaultValues) ;
    }

    public Map<String, String> getDefinedConfigValues()
    {
        return new HashMap<String, String>(this.definedConfigValues);
    }
}
