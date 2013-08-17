package mindriven.buildServer.OpenCoverRunner.agent;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 17.08.13
 * Time: 08:22
 * To change this template use File | Settings | File Templates.
 */
public interface IArgumentsProvider {
    public List<String> getArguments() throws FileNotFoundException;
}
