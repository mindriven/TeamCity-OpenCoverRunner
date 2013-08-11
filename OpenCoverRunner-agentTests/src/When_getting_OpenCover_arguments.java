import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.TestsAssembliesPathsProvider;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Matchers.eq;
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
        String expected = "-filter: +[*]* -[someAssembly].namespace.* +[otherAssembly]*";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS))
                           .thenReturn(filters);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(anyString(), anyString())).thenReturn(new String[]{""});
        provider.setDirectoryScanner(scanner);
        provider.setTestAssembliesPathsProvider(mock(TestsAssembliesPathsProvider.class));

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    public void and_user_provided_additional_parameters__user_input_is_included_in_results() throws Exception {
        String userInput = "userInput";
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(anyString(), anyString())).thenReturn(new String[]{""});
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS))
                .thenReturn("filters");
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);
        provider.setTestAssembliesPathsProvider(mock(TestsAssembliesPathsProvider.class));

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(userInput));
    }

    @Test
    public void tests_runner_gets_discovered_based_on_tests_runner_config_entry_and_set_as_target() throws Exception {
        String userInput = "userInput";
        String scanResult = "pathToRunnerExecutable";
        String checkoutDir = "checkoutDir";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                .thenReturn(checkoutDir);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(anyString(), anyString())).thenReturn(new String[]{""});
        when(scanner.scanForSinglePath(eq(checkoutDir), eq(userInput))).thenReturn(scanResult);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);
        provider.setTestAssembliesPathsProvider(mock(TestsAssembliesPathsProvider.class));

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("-target: \"pathToRunnerExecutable\""));
    }

    @Test
    public void TestsAssembliesPathsProvider_is_used_to_get_paths_passed_to_tests_runner() throws Exception
    {
        String matchedAssemblies = "abc.dll";
        String checkoutDir = "checkoutDir";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                .thenReturn(checkoutDir);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_ARGUMENTS))
                .thenReturn("");
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(anyString(), anyString())).thenReturn(new String[]{matchedAssemblies});
        when(scanner.scanForSinglePath(anyString(), anyString())).thenReturn("");
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);
        TestsAssembliesPathsProvider testsAssembliesProvider = mock(TestsAssembliesPathsProvider.class);
        when(testsAssembliesProvider.getTestsAssembliesPaths()).thenReturn(matchedAssemblies);
        provider.setTestAssembliesPathsProvider(testsAssembliesProvider);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("-targetargs: "+matchedAssemblies));
    }

    @Test
    public void additional_target_args_are_passed_to_target() throws Exception
    {
        String matchedAssemblies = "abc.dll";
        String additionalOptions = "options";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_ARGUMENTS))
                .thenReturn(additionalOptions);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(anyString(), anyString())).thenReturn(new String[]{});
        when(scanner.scanForSinglePath(anyString(), anyString())).thenReturn("");
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);
        TestsAssembliesPathsProvider testsAssembliesProvider = mock(TestsAssembliesPathsProvider.class);
        when(testsAssembliesProvider.getTestsAssembliesPaths()).thenReturn(matchedAssemblies);
        provider.setTestAssembliesPathsProvider(testsAssembliesProvider);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("-targetargs: "+matchedAssemblies+" "+additionalOptions));
    }

    @Test
    public void and_user_specified_absolute_file_path_for_output__it_gets_passed_as_parameter() throws Exception {
        String userInput = "c:\\someFile.xml";
        String checkoutDir = "checkoutDir";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                .thenReturn(checkoutDir);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(anyString(), anyString())).thenReturn(new String[]{""});
        when(scanner.scanForSinglePath(eq(checkoutDir), eq(userInput))).thenReturn("");
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);
        provider.setTestAssembliesPathsProvider(mock(TestsAssembliesPathsProvider.class));

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("-output: \"c:\\someFile.xml\""));
    }
}
