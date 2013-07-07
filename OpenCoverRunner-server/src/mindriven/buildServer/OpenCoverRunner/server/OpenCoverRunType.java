package mindriven.buildServer.OpenCoverRunner.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jetbrains.buildServer.requirements.Requirement;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.util.StringUtil;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: kposiadala
 * Date: 06.07.13
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunType extends jetbrains.buildServer.serverSide.RunType {
    private final PluginDescriptor myPluginDescriptor;

    public OpenCoverRunType(final RunTypeRegistry runTypeRegistry, final PluginDescriptor pluginDescriptor) {
        myPluginDescriptor = pluginDescriptor;
        runTypeRegistry.registerRunType(this);
    }

    @NotNull
    @Override
    public String getType() {
        return OpenCoverRunnerConsts.RUNNER_TYPE;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return OpenCoverRunnerConsts.RUNNER_DISPLAY_NAME;
    }

    @NotNull
    @Override
    public String getDescription() {
        return OpenCoverRunnerConsts.RUNNER_DESCRIPTION;
    }

    @Nullable
    @Override
    public PropertiesProcessor getRunnerPropertiesProcessor() {
        return new OpenCoverPropertiesProcessor();
    }

    @Nullable
    @Override
    public String getEditRunnerParamsJspFilePath() {
        return myPluginDescriptor.getPluginResourcesPath("editOpenCoverRunnerParams.jsp");
    }

    @Nullable
    @Override
    public String getViewRunnerParamsJspFilePath() {
        return myPluginDescriptor.getPluginResourcesPath("viewOpenCoverRunnerParams.jsp");
    }

    @Nullable
    @Override
    public Map<String, String> getDefaultRunnerProperties() {
        Map<String, String> parameters = new HashMap<String, String>();
        return parameters;
    }
}
