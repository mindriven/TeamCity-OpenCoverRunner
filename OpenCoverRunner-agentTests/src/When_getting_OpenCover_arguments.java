import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.apache.tools.ant.DirectoryScanner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.07.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class When_getting_OpenCover_arguments {

    private ArgumentsProvider provider = null;
    @Before
    public void Initialize()
    {
        this.provider = new ArgumentsProvider(null);
    }

    @Test
    public void and_user_provided_filters__filters_are_properly_constructed() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String filters = "+[*]* \r\n -[someAssembly].namespace.*    +[otherAssembly]*   ";
        String expected = "filter: \"+[*]* -[someAssembly].namespace.* +[otherAssembly]*\"";
        params.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS, filters);
        ArgumentsProvider provider = new ArgumentsProvider(params);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    public void and_filters_are_not_provided__default_filters_are_used() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String expected = "filter: \""+OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_FILTERS+"\"";
        ArgumentsProvider provider = new ArgumentsProvider(params);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    public void and_user_provided_additional_parameters__user_input_is_included_in_results() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String userInput = "userInput";
        params.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS, userInput);
        ArgumentsProvider provider = new ArgumentsProvider(params);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(userInput));
    }

}
