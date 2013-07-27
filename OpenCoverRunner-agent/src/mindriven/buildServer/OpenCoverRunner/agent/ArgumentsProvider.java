package mindriven.buildServer.OpenCoverRunner.agent;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.apache.tools.ant.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 27.07.13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentsProvider {
    private Map<String, String> parameters = null;

    public ArgumentsProvider(Map<String, String> parameters)
    {
        this.parameters = parameters;
    }

    public List<String> getArguments()
    {
        List<String> result = new ArrayList<String>();
        String rawFilters = this.parameters.containsKey(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS)?
                                this.parameters.get(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS)
                                :OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_FILTERS;
        String[] parts = rawFilters.split("\\s+");
        result.add("filter: \""+this.trimAndGlue(" ",parts)+"\"");
        return result;
    }

    private String trimAndGlue(String glue, String[] strArray)
    {
        String result = "";
        for(int i=0;i<strArray.length;i++)
        {
            result += strArray[i].trim() + glue;
        }
        return result.trim();
    }
}
