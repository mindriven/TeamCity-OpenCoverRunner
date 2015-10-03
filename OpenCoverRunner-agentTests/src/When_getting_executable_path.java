import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.*;
import mindriven.buildServer.OpenCoverRunner.agent.ExecutablePathProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import org.junit.Before;
import org.junit.Test;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.07.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class When_getting_executable_path {

    private ExecutablePathProvider pathProvider = null;
    @Before
    public void Initialize()
    {
        this.pathProvider = new ExecutablePathProvider(null);
    }

    @Test
    public void directories_are_scanned_for_executable_based_on_user_provided_path() throws Exception {

        String userProvidedPath = "somePath";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH)).thenReturn(userProvidedPath);
        ExecutablePathProvider provider = new ExecutablePathProvider(configProvider);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        provider.setDirectoryScanner(scanner);

        provider.getExecutablePath(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argument2 = ArgumentCaptor.forClass(String.class);
        verify(scanner).scanForSinglePath(argument2.capture(), argument.capture());
        Assert.assertEquals(userProvidedPath, argument.getValue());
    }

    @Test
    // inspired by https://github.com/mindriven/TeamCity-OpenCoverRunner/issues/8
    public void and_user_specifies_absolute_path__it_gets_returned_as_executable_path() throws Exception {

        String userProvidedPath = "C:\\Program Files\\Microsoft Visual Studio 12.0\\Common7\\IDE\\CommonExtensions\\Microsoft\\TestWindow\\vstest.console.exe";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH)).thenReturn(userProvidedPath);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR)).thenReturn("X:\\BuildAgent\\work\\9385ca14d240412f");
        ExecutablePathProvider provider = new ExecutablePathProvider(configProvider);
        OpenCoverRunnerDirectoryScanner scanner = new OpenCoverRunnerDirectoryScanner();
        provider.setDirectoryScanner(scanner);

        String result = provider.getExecutablePath(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH);

        Assert.assertEquals(userProvidedPath, result);
    }

    @Test
    public void base_path_for_search_is_checkout_dir() throws Exception {
        String basePath = "basePath";
        ConfigValuesProvider configProvider = mock(ConfigValuesProvider.class);
        when(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR))
                            .thenReturn(basePath);
        ExecutablePathProvider provider = new ExecutablePathProvider(configProvider);
        OpenCoverRunnerDirectoryScanner scanner = mock(OpenCoverRunnerDirectoryScanner.class);
        provider.setDirectoryScanner(scanner);

        provider.getExecutablePath(null);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argument2 = ArgumentCaptor.forClass(String.class);
        verify(scanner).scanForSinglePath(argument.capture(), argument2.capture());
        Assert.assertEquals(basePath, argument.getValue());
    }
}
