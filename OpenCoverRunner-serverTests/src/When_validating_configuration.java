import jetbrains.buildServer.serverSide.InvalidProperty;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.common.DefaultValuesMap;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import mindriven.buildServer.OpenCoverRunner.server.OpenCoverRunTypeParametersProcessor;
import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 07.08.13
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class When_validating_configuration {

    @Test
    public void and_tests_runner_is_not_specified__it_is_invalid() throws Exception
    {
        HashMap<String, String> properties = new HashMap<String, String>();
        OpenCoverRunTypeParametersProcessor validator = new OpenCoverRunTypeParametersProcessor();

        Collection<InvalidProperty> result = validator.process(properties);

        Assert.assertTrue(containsPropertyWithName(result, OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH));
    }

    @Test
    public void and_tests_runner_is_specified__it_is_valid() throws Exception
    {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH, "someString");
        OpenCoverRunTypeParametersProcessor validator = new OpenCoverRunTypeParametersProcessor();

        Collection<InvalidProperty> result = validator.process(properties);

        Assert.assertFalse(containsPropertyWithName(result, OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH));
    }

    @Test
    public void and_tests_assemblies_paths_are_not_specified__it_is_invalid() throws Exception
    {
        HashMap<String, String> properties = new HashMap<String, String>();
        OpenCoverRunTypeParametersProcessor validator = new OpenCoverRunTypeParametersProcessor();

        Collection<InvalidProperty> result = validator.process(properties);

        Assert.assertTrue(containsPropertyWithName(result, OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS));
    }

    @Test
    public void and_tests_assemblies_paths_are_specified__it_is_valid() throws Exception
    {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS, "someString");
        OpenCoverRunTypeParametersProcessor validator = new OpenCoverRunTypeParametersProcessor();

        Collection<InvalidProperty> result = validator.process(properties);

        Assert.assertFalse(containsPropertyWithName(result, OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS));
    }

    private Boolean containsPropertyWithName(Collection<InvalidProperty> collection, String name)
    {
        Iterator<InvalidProperty> iterator = collection.iterator();
        while (iterator.hasNext())
        {
            InvalidProperty prop = iterator.next();
            if(prop.getPropertyName()==name)
                return true;
        }
        return false;
    }
}
