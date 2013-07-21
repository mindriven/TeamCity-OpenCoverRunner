package PathsManipulations;

import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.PathsHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.security.InvalidAlgorithmParameterException;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 21.07.13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class When_getting_folder_name_for_discovery extends PathManipulationDataTestsBase {

    public When_getting_folder_name_for_discovery(String path, String subPathToScanForDiscovery, String immediateFolderNameBeginning) {
        super(path, subPathToScanForDiscovery, immediateFolderNameBeginning);
    }

    @Test
    public void proper_string_is_returned() throws Exception
    {
        String result = this.pathsHelper.getFolderNameBeginningForDiscovery(this.fullPath);

        Assert.assertEquals(this.immediateFolderNameBeginning, result);
    }

    @Test(expected = InvalidAlgorithmParameterException.class)
    public void and_path_doesnt_contain_discovery_placeholder__exception_gets_thrown() throws Exception
    {
        String expectedSubpath = "someDir\\otherDir";
        String path = expectedSubpath+"\\pattern\\app.exe";

        String subpath = pathsHelper.getFolderNameBeginningForDiscovery(expectedSubpath);
    }
}
