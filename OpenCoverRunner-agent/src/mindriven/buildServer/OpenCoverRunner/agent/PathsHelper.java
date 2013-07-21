package mindriven.buildServer.OpenCoverRunner.agent;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.io.File;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 21.07.13
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class PathsHelper {
    public boolean doesPathUseDiscoveryPattern(String path)
    {
        return path.contains(OpenCoverRunnerConsts.PATH_DISCOVERY_PLACEHOLDER);
    }

    public String extractSubPathForDiscovery(String fullPath) throws InvalidAlgorithmParameterException {
        Path path = this.getPartOfPathBeforePlaceholder(fullPath);
        return path.getParent().toString();
    }

    public String getFolderNameBeginningForDiscovery(String fullPath) throws InvalidAlgorithmParameterException
    {
        Path path = this.getPartOfPathBeforePlaceholder(fullPath);
        return path.getFileName().toString();
    }

    private Path getPartOfPathBeforePlaceholder(String fullPath) throws InvalidAlgorithmParameterException
    {
        String splitBy = OpenCoverRunnerConsts.PATH_DISCOVERY_PLACEHOLDER;
        if(!fullPath.contains(splitBy))
            throw new InvalidAlgorithmParameterException(this.getPathHasNoPlaceholderExceptionMessage(fullPath));
        String[] splitByPlaceholder = fullPath.split("\\"+splitBy);
        return new File(splitByPlaceholder[0]).toPath();
    }

    private String getPathHasNoPlaceholderExceptionMessage(String path)
    {
        return "path \""+path+"\" does not contain placeholder \""+OpenCoverRunnerConsts.PATH_DISCOVERY_PLACEHOLDER+"\" and therefore discovery can not be done";
    }
}
