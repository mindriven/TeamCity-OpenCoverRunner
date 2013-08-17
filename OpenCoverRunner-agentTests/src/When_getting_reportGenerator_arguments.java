import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.ReportsGenerator.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.TestsAssembliesPathsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Test;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 16.08.13
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
public class When_getting_reportGenerator_arguments {

    @Test
    public void report_file_is_taken_from_open_cover() throws Exception
    {
        String userInput = "userInput";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH))
                .thenReturn(userInput);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("-reports:\"userInput\""));
    }

    @Test
    public void output_dir_is_taken_user_input_cover() throws Exception
    {
        String userInput = "userInput";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_OUTPUT_DIR))
                .thenReturn(userInput);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("-targetdir:\"userInput\""));
    }
}
