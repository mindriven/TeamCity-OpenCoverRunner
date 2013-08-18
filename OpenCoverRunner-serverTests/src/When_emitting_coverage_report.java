import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifact;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactHolder;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifacts;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactsViewMode;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import mindriven.buildServer.OpenCoverRunner.server.CoverageEmitter;
import mindriven.buildServer.OpenCoverRunner.server.ReportCoverageReader;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 17.08.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class When_emitting_coverage_report {

    @Test
    public void and_report_exists__value_returned_by_ReportCoverageReader_is_returned() throws Exception
    {
        CoverageEmitter emitter = new CoverageEmitter();
        SBuild build = mock(SBuild.class);
        BuildArtifacts artifacts = mock(BuildArtifacts.class);
        BuildArtifactHolder artifactHolder = mock(BuildArtifactHolder.class);
        when(artifactHolder.isAvailable())
            .thenReturn(true);
        when(artifactHolder.isAccessible())
                .thenReturn(true);
        when(artifacts.findArtifact(eq(OpenCoverRunnerConsts.SETTINGS_HTM_REPORTS_ARTIFACT_PATH
                                    +"/"+OpenCoverRunnerConsts.CONST_REPORT_GENERATOR_HTM_RESULT_FILE_NAME)))
                      .thenReturn(artifactHolder);
        when(build.getArtifacts(any(BuildArtifactsViewMode.class)))
                      .thenReturn(artifacts);
        ReportCoverageReader reader = mock(ReportCoverageReader.class);
        when(reader.readCoverage(any(BuildArtifact.class))).thenReturn(new BigDecimal("8.56"));
        emitter.setReportCoverageReader(reader);

        BigDecimal result = emitter.calculateMetric(build);

        Assert.assertEquals(8.56, result.doubleValue());
    }

    @Test
    public void and_report_does_not_exists__value_returned_is__0() throws Exception
    {
        CoverageEmitter emitter = new CoverageEmitter();
        SBuild build = mock(SBuild.class);
        BuildArtifacts artifacts = mock(BuildArtifacts.class);
        BuildArtifactHolder artifactHolder = mock(BuildArtifactHolder.class);
        when(artifactHolder.isAvailable())
                .thenReturn(false);
        when(artifactHolder.isAccessible())
                .thenReturn(false);
        when(artifacts.findArtifact(eq(OpenCoverRunnerConsts.SETTINGS_HTM_REPORTS_ARTIFACT_PATH
                +"/"+OpenCoverRunnerConsts.CONST_REPORT_GENERATOR_HTM_RESULT_FILE_NAME)))
                .thenReturn(artifactHolder);
        when(build.getArtifacts(any(BuildArtifactsViewMode.class)))
                .thenReturn(artifacts);
        ReportCoverageReader reader = mock(ReportCoverageReader.class);
        when(reader.readCoverage(any(BuildArtifact.class))).thenReturn(new BigDecimal("8.56"));
        emitter.setReportCoverageReader(reader);

        BigDecimal result = emitter.calculateMetric(build);

        Assert.assertEquals(0.0, result.doubleValue());
    }
}
