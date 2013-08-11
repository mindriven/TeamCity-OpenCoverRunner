import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
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
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS))
                           .thenReturn(filters);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        provider.setDirectoryScanner(scanner);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    public void and_user_provided_additional_parameters__user_input_is_included_in_results() throws Exception {
        String userInput = "userInput";
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS))
                .thenReturn("filters");
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);

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
        when(scanner.scanForSinglePath(eq(checkoutDir), eq(userInput))).thenReturn(scanResult);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("target: \"pathToRunnerExecutable\""));
    }

    @Test
         public void and_assemblies_are_not_passed_with_command_line_switch__scan_result_of_each_assembly_pattern_is_included_in_results() throws Exception {
        String userInput = "patt\\  \\*ern1 " + "\n\r pattern\\**2 " + "   \n\r \"patt\\e r\\n3\"";
        String[] resultForPatter1 = new String[]{"p1r1", "p1r2"};
        String[] resultForPatter2 = new String[]{"p2r1"};
        String[] resultForPatter3 = new String[]{"p3r1", "p3r2", "p3r3"};
        String checkoutDir = "checkoutDir";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                .thenReturn(checkoutDir);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq("patt\\  \\*ern1")))
                .thenReturn(resultForPatter1);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq("pattern\\**2")))
                .thenReturn(resultForPatter2);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq("patt\\e r\\n3")))
                .thenReturn(resultForPatter3);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("p1r1"));
        Assert.assertTrue(result.contains("p1r2"));
        Assert.assertTrue(result.contains("p2r1"));
        Assert.assertTrue(result.contains("p3r1"));
        Assert.assertTrue(result.contains("p3r2"));
        Assert.assertTrue(result.contains("p3r3"));
    }

    @Test
    public void and_assemblies_are_passed_with_command_line_switch__scan_result_of_each_assembly_pattern_is_included_in_results_prefixed_with_switch() throws Exception {
        String userInput = "patt\\  \\*ern1 " + "\n\r pattern\\**2 " + "   \n\r \"patt\\e r\\n3\"";
        String[] resultForPatter1 = new String[]{"p1r1", "p1r2"};
        String[] resultForPatter2 = new String[]{"p2r1"};
        String[] resultForPatter3 = new String[]{"p3r1", "p3r2", "p3r3"};
        String checkoutDir = "checkoutDir";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_COMMAND_LINE_SWITCH))
                .thenReturn("switch");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_PASS_ASSEMBLIES_AS_SWITCH))
                .thenReturn("true");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                .thenReturn(checkoutDir);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq("patt\\  \\*ern1")))
                .thenReturn(resultForPatter1);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq("pattern\\**2")))
                .thenReturn(resultForPatter2);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq("patt\\e r\\n3")))
                .thenReturn(resultForPatter3);
        ArgumentsProvider provider = new ArgumentsProvider(configProvider);
        provider.setDirectoryScanner(scanner);

        List<String> result = provider.getArguments();

        Assert.assertTrue(result.contains("switch p1r1"));
        Assert.assertTrue(result.contains("switch p1r2"));
        Assert.assertTrue(result.contains("switch p2r1"));
        Assert.assertTrue(result.contains("switch p3r1"));
        Assert.assertTrue(result.contains("switch p3r2"));
        Assert.assertTrue(result.contains("switch p3r3"));
    }
}
