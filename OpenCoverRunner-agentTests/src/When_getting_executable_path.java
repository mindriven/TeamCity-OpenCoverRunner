import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.BuildAgentSystemInfo;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import junit.extensions.TestSetup;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.*;
import mindriven.buildServer.OpenCoverRunner.server.OpenCoverRunType;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
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
    public void and_user_provided_absolute_path__raw_path_is_this_provided_path() throws Exception {
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

    @Test
    public void and_discovery_required__file_system_helper_gets_used_for_scanning_directory() throws Exception {
        String expectedSubPath = "subpath";
        String expectedBeginningOfName = "beginningOfName";
        Map<String, String> params = new HashMap<String, String>();
        PathsHelper pathsHelper = mock(PathsHelper.class);
        when(pathsHelper.doesPathUseDiscoveryPattern(anyString())).thenReturn(true);
        when(pathsHelper.extractSubPathForDiscovery(anyString())).thenReturn(expectedSubPath);
        when(pathsHelper.getFolderNameBeginningForDiscovery(anyString())).thenReturn(expectedBeginningOfName);
        FileSystemHelper FSHelper = mock(FileSystemHelper.class);
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);
        provider.setPathsHelper(pathsHelper);
        provider.setFileSystemHelper(FSHelper);

        provider.getExecutablePath();

        verify(FSHelper).getSubdirectoriesBeginningWith(expectedSubPath, expectedBeginningOfName);
    }

    @Test
    public void and_discovery_not_required__file_system_helper_is_not_used() throws Exception {
        String expectedSubPath = "subpath";
        String expectedBeginningOfName = "beginningOfName";
        Map<String, String> params = new HashMap<String, String>();
        PathsHelper pathsHelper = mock(PathsHelper.class);
        when(pathsHelper.doesPathUseDiscoveryPattern(anyString())).thenReturn(false);
        when(pathsHelper.extractSubPathForDiscovery(anyString())).thenReturn(expectedSubPath);
        when(pathsHelper.getFolderNameBeginningForDiscovery(anyString())).thenReturn(expectedBeginningOfName);
        FileSystemHelper FSHelper = mock(FileSystemHelper.class);
        ExecutablePathProvider provider = new ExecutablePathProvider(params, null);
        provider.setPathsHelper(pathsHelper);
        provider.setFileSystemHelper(FSHelper);

        provider.getExecutablePath();

        verifyZeroInteractions(FSHelper);
    }
}
