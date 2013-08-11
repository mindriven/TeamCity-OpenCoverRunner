import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.agent.TestsAssembliesPathsProvider;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 11.08.13
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class When_getting_tests_assemblies_paths {

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
        TestsAssembliesPathsProvider provider = new TestsAssembliesPathsProvider(configProvider, scanner);

        String result = provider.getTestsAssembliesPaths();

        Assert.assertEquals(result, "\"p1r1\" \"p1r2\" \"p2r1\" \"p3r1\" \"p3r2\" \"p3r3\"");
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
        TestsAssembliesPathsProvider provider = new TestsAssembliesPathsProvider(configProvider, scanner);

        String result = provider.getTestsAssembliesPaths();

        Assert.assertEquals(result, "\"switch p1r1\" \"switch p1r2\" \"switch p2r1\" \"switch p3r1\" \"switch p3r2\" \"switch p3r3\"");
    }

    @Test(expected = FileNotFoundException.class)
    public void and_no_matching_assemblies_found__exception_is_thrown() throws Exception {
        String userInput = "patt\\  \\*ern1";
        String[] matchedAssemblies = new String[]{};
        String checkoutDir = "checkoutDir";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(anyString()))
                .thenReturn("otherConfigValueInput");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_PASS_ASSEMBLIES_AS_SWITCH))
                .thenReturn("false");
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS))
                .thenReturn(userInput);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                .thenReturn(checkoutDir);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        when(scanner.scanForMultiplePaths(eq(checkoutDir), eq(userInput)))
                .thenReturn(matchedAssemblies);
        TestsAssembliesPathsProvider provider = new TestsAssembliesPathsProvider(configProvider, scanner);

        provider.getTestsAssembliesPaths();
    }
}
