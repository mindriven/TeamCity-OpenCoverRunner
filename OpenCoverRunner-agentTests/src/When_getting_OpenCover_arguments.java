import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
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
    public void filters_are_properly_constructed() throws Exception {
        String filters = "+[*]* \r\n -[someAssembly].namespace.*    +[otherAssembly]*   ";
        String expected = "filter: \"+[*]* -[someAssembly].namespace.* +[otherAssembly]*\"";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS))
                           .thenReturn(filters);

        ArgumentsProvider provider = new ArgumentsProvider(configProvider);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    public void and_user_provided_additional_parameters__user_input_is_included_in_results() throws Exception {
        String userInput = "userInput";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS))
                .thenReturn("filters");
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(userInput));
    }

}
