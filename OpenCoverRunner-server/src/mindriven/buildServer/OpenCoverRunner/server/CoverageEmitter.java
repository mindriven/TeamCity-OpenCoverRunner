package mindriven.buildServer.OpenCoverRunner.server;
import jetbrains.buildServer.serverSide.BuildMetric;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactHolder;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactsViewMode;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.xml.sax.SAXException;
import sun.misc.IOUtils;

import javax.print.DocFlavor;
import java.io.InputStream;
import java.lang.Override;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* Created with IntelliJ IDEA.
* User: Kamil
* Date: 17.08.13
* Time: 09:12
* To change this template use File | Settings | File Templates.
*/
public class CoverageEmitter extends BuildMetric {
    private Logger Log = Logger.getLogger(CoverageEmitter.class.getName());
    private ReportCoverageReader reportCoverageReader;

    public CoverageEmitter()
    {
        super(OpenCoverRunnerConsts.OPEN_COVER_RUNNER_COVERAGE_METRIC_KEY,
              OpenCoverRunnerConsts.OPEN_COVER_RUNNER_COVERAGE_METRIC_DESCRIPTION);
        this.reportCoverageReader = new ReportCoverageReader();
    }

    public void setReportCoverageReader(ReportCoverageReader reportCoverageReader) {
        this.reportCoverageReader = reportCoverageReader;
    }

    @Override
    public BigDecimal calculateMetric(SBuild sBuild) {

        try {
            BuildArtifactHolder artifactHolder = sBuild.getArtifacts(BuildArtifactsViewMode.VIEW_ALL)
                    .findArtifact(OpenCoverRunnerConsts.SETTINGS_HTM_REPORTS_ARTIFACT_PATH+"/"+OpenCoverRunnerConsts.CONST_REPORT_GENERATOR_HTM_RESULT_FILE_NAME);
            if(artifactHolder.isAvailable() && artifactHolder.isAccessible())
            {
                BigDecimal result = this.reportCoverageReader.readCoverage(artifactHolder.getArtifact());
                Log.log(Level.FINER, "OpenCoverRunner emitting coverage metric: "+result+" % of lines covered");
                return result;
            }
        }
        catch (Exception e) {
            Log.log(Level.WARNING, "Unable to emit coverage, exception of type "+e.getClass().getName()+"occurred. Message is: "+e.getMessage()+" Emitting 0.0 coverage.");
        }
        return new BigDecimal(0);
    }
}
