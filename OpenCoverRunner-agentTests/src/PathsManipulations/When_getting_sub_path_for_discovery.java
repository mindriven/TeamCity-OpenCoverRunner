package PathsManipulations;

import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.PathsHelper;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 21.07.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class When_getting_sub_path_for_discovery extends PathManipulationDataTestsBase {

    public When_getting_sub_path_for_discovery(String path, String subPathToScanForDiscovery, String immediateFolderNameBeginning) {
        super(path, subPathToScanForDiscovery, immediateFolderNameBeginning);
    }

    @Test
    public void and_path_contains_discovery_pattern__proper_subpath_gets_returned() throws Exception
    {
        String subpath = pathsHelper.extractSubPathForDiscovery(this.fullPath);

        Assert.assertEquals(this.subPathToScanForDiscovery, subpath);
    }

    @Test(expected = InvalidAlgorithmParameterException.class)
    public void and_path_doesnt_contain_discovery_placeholder__exception_gets_thrown() throws Exception
    {
        String expectedSubpath = "someDir\\otherDir";
        String path = expectedSubpath+"\\pattern\\app.exe";

        String subpath = pathsHelper.extractSubPathForDiscovery(expectedSubpath);
    }
}
