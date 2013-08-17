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
