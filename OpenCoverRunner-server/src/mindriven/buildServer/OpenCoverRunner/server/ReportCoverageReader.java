package mindriven.buildServer.OpenCoverRunner.server;

import jetbrains.buildServer.serverSide.artifacts.BuildArtifact;
import sun.security.util.BigInt;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 17.08.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class ReportCoverageReader {
    public BigDecimal readCoverage(BuildArtifact reportArtifact) {
        return new BigDecimal(7);
    }
}
