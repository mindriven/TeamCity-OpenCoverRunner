package mindriven.buildServer.OpenCoverRunner.agent;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.security.InvalidAlgorithmParameterException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 20.07.13
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class ExecutablePathProvider {

    private Map<String, String> parameters = null;
    private FileSystemHelper FSHelper = new FileSystemHelper();
    private PathsHelper pathsHelper = new PathsHelper();
    private String checkoutDir = null;
    public ExecutablePathProvider(Map<String, String> parameters, String checkoutDir)
    {
        this.parameters = parameters;
        this.checkoutDir = checkoutDir;
    }

    public void setFileSystemHelper(FileSystemHelper helper)
    {
        this.FSHelper = helper;
    }

    public void setPathsHelper(PathsHelper helper)
    {
        this.pathsHelper = helper;
    }

//    public String GetDiscoveredPath(String rawPath)
//    {
//
//    }

    public String getExecutablePath() throws InvalidAlgorithmParameterException {
        String rawPath = this.getRawExecutablePath();
        if(pathsHelper.doesPathUseDiscoveryPattern(rawPath))
        {
            String subPathToScan = this.pathsHelper.extractSubPathForDiscovery(rawPath);
            String filterFoldersBy = this.pathsHelper.getFolderNameBeginningForDiscovery(rawPath);
            this.FSHelper.getSubdirectoriesBeginningWith(subPathToScan, filterFoldersBy);
        }
        return null;
    }

    public String getRawExecutablePath()
    {
        String key = OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH;
        String userProvidedPath = OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_PATH;
        if(this.parameters.containsKey(key))
            userProvidedPath = this.parameters.get(key);

        return userProvidedPath;
    }
}
