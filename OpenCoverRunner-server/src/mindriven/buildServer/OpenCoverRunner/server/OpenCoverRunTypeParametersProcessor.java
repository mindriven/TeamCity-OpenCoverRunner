package mindriven.buildServer.OpenCoverRunner.server;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.util.PropertiesUtil;
import mindriven.buildServer.OpenCoverRunner.common.DefaultValuesMap;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 07.08.13
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunTypeParametersProcessor implements PropertiesProcessor {

    private Map<String, String> settings;
    private List<InvalidProperty> result = new Vector<InvalidProperty>();

    @Override
    public Collection<InvalidProperty> process(Map<String, String> settings) {
        this.settings = settings;
        this.checkProperty(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS, null);
        this.checkProperty(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH, null);
        if(PropertiesUtil.getBoolean(settings.get(OpenCoverRunnerConsts.SETTINGS_PASS_ASSEMBLIES_AS_SWITCH)))
        {
            checkProperty(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_COMMAND_LINE_SWITCH, null);
        }

        return this.result;
    }

    private void checkProperty(String key,
                               String messageToAdd)
    {
        if(messageToAdd==null || messageToAdd.isEmpty())
        {
            messageToAdd = "Required, without this tests cannot be run";
        }

        if(!settings.containsKey(key))
        {
            result.add(new InvalidProperty(key, messageToAdd));
            return;
        }
        else
        {
            String value = settings.get(key);
            if(value.isEmpty())
                result.add(new InvalidProperty(key, messageToAdd));
        }
    }

}
