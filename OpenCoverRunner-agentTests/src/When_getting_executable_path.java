import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.BuildAgentSystemInfo;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import junit.extensions.TestSetup;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.*;
import mindriven.buildServer.OpenCoverRunner.server.OpenCoverRunType;
import org.apache.tools.ant.DirectoryScanner;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.mockito.ArgumentCaptor;
import org.mockito.MockSettings;
import org.mockito.listeners.InvocationListener;
import org.mockito.stubbing.Answer;

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
        this.pathProvider = new ExecutablePathProvider(null, null);
    }

    @Test
    public void directories_are_scanned_for_executable_based_on_user_provided_path() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String userProvidedPath = "somePath";
        params.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH, userProvidedPath);
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        provider.setDirectoryScanner(scanner);
        when(scanner.getIncludedFilesCount()).thenReturn(1);
        when(scanner.getIncludedFiles()).thenReturn(new String[]{""});

        provider.getExecutablePath();

        ArgumentCaptor<String[]> argument = ArgumentCaptor.forClass(String[].class);
        verify(scanner).setIncludes(argument.capture());
        Assert.assertEquals(userProvidedPath, argument.getValue()[0]);
        verify(scanner).scan();
    }

    @Test
    public void casing_does_not_matter() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        provider.setDirectoryScanner(scanner);
        when(scanner.getIncludedFilesCount()).thenReturn(1);
        when(scanner.getIncludedFiles()).thenReturn(new String[]{""});

        provider.getExecutablePath();

        ArgumentCaptor<Boolean> argument = ArgumentCaptor.forClass(Boolean.class);
        verify(scanner).setCaseSensitive(argument.capture());
        Assert.assertFalse(argument.getValue());
    }

    @Test
    public void base_path_for_search_is_checkout_dir() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String basePath = "basePath";
        ExecutablePathProvider provider = new ExecutablePathProvider(params, basePath);
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        provider.setDirectoryScanner(scanner);
        when(scanner.getIncludedFilesCount()).thenReturn(1);
        when(scanner.getIncludedFiles()).thenReturn(new String[]{""});

        provider.getExecutablePath();

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(scanner).setBasedir(argument.capture());
        Assert.assertEquals(basePath, argument.getValue());
    }

    @Test(expected = FileNotFoundException.class)
    public void and_found_not_1_matching_file__exception_is_thrown() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        provider.setDirectoryScanner(scanner);
        when(scanner.getIncludedFilesCount()).thenReturn(3);
        when(scanner.getIncludedFiles()).thenReturn(new String[]{""});

        provider.getExecutablePath();
    }

    @Test
    public void and_found_1_matching_file__it_gets_returned() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        when(scanner.getIncludedFilesCount()).thenReturn(1);
        String matchedPath = "matchedPath";
        when(scanner.getIncludedFiles()).thenReturn(new String[]{matchedPath});
        provider.setDirectoryScanner(scanner);

        String result = provider.getExecutablePath();

        Assert.assertEquals(matchedPath, result);
    }

    @Test
    public void and_user_provided_path__raw_path_is_this_provided_path() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String userProvidedPath = "somePath";
        params.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH, userProvidedPath);
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);

        String result = provider.getRawExecutablePath();

        Assert.assertEquals(result, userProvidedPath);
    }

    @Test
    public void and_user_didnt_provided_path__raw_path_is_default_path() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);

        String result = provider.getRawExecutablePath();

        Assert.assertEquals(result, OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_PATH);
    }
}
