package mindriven.buildServer.OpenCoverRunner.server;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;
/**
 * Created with IntelliJ IDEA.
 * User: kposiadala
 * Date: 07.07.13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerConstantsBean {
    @NotNull
    public String getOpenCoverPath()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH;
    }

    @NotNull
    public String getOpenCoverDefaultPath()
    {
        return OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_PATH;
    }

    @NotNull
    public String getOpenCoverFilters()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS;
    }

    @NotNull
    public String getOpenCoverDefaultFilters()
    {
        return OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_FILTERS;
    }

    @NotNull
    public String getOpenCoverAdditionalOptions()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS;
    }
}
