package mindriven.buildServer.OpenCoverRunner.common;

/**
 * Created with IntelliJ IDEA.
 * User: kposiadala
 * Date: 06.07.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public interface OpenCoverRunnerConsts {
    String RUNNER_TYPE = "OpenCoverRunner";
    String RUNNER_DISPLAY_NAME = "OpenCover runner";
    String RUNNER_DESCRIPTION = "Runs OpenCover and publishes coverage to TC";

    String SETTINGS_OPEN_COVER_PATH = "openCoverRunner.openCoverPath";
    String SETTINGS_DEFAULT_OPEN_COVER_PATH = "packages/OpenCover.4.5.1604/OpenCover.Console.exe";
    String SETTINGS_OPEN_COVER_FILTERS = "openCoverRunner.openCoverFilters";
    String SETTINGS_DEFAULT_OPEN_COVER_FILTERS = "+[*]*";
    String SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS = "openCoverRunner.openCoverAdditionalOptions";
}
