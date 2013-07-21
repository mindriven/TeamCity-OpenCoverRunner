package PathsManipulations;

import mindriven.buildServer.OpenCoverRunner.agent.PathsHelper;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 21.07.13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public abstract class PathManipulationDataTestsBase {

    public PathManipulationDataTestsBase(
            String path,
            String subPathToScanForDiscovery,
            String immediateFolderNameBeginning)
    {
        this.fullPath = path;
        this.subPathToScanForDiscovery = subPathToScanForDiscovery;
        this.immediateFolderNameBeginning = immediateFolderNameBeginning;
    }

    protected String fullPath;
    protected String subPathToScanForDiscovery;
    protected String immediateFolderNameBeginning;

    protected PathsHelper pathsHelper = null;

    @Before
    public void Initialize()
    {
        this.pathsHelper = new PathsHelper();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String placeholder = OpenCoverRunnerConsts.PATH_DISCOVERY_PLACEHOLDER;
        return Arrays.asList(new Object[][]{
                {"someDir\\otherDir\\pattern1" + placeholder + "\\app.exe", "someDir\\otherDir", "pattern1"},
                {"C:\\otherDir\\pattern2" + placeholder + "\\app.exe", "C:\\otherDir", "pattern2"}
        });
    }
}
