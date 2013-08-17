<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="constants" class="mindriven.buildServer.OpenCoverRunner.server.OpenCoverRunnerConstantsBean"/>

<l:settingsGroup title="Open Cover">
<tr>
    <th><label>Path to OpenCover:</label></th>
    <td>
        <props:textProperty name="${constants.openCoverPath}" className="longField" />
        <bs:vcsTree fieldId="${constants.openCoverPath}" treeId="${constants.openCoverPath}"/>
        <span class="smallNote">OpenCover executable relative to checkout root. Use * to enable dynamic discovery. Default is ${constants.openCoverDefaultPath}</span>
        <span class="error" id="error_${constants.openCoverPath}"></span></td>
    </td>
</tr>
<tr>
    <th><label>Filters:</label></th>
    <td>
        <props:multilineProperty name="${constants.openCoverFilters}"
                                       className="longField"
                                       linkTitle="Open cover format filters"
                                       cols="55" rows="5"
                                       expanded="true"/>
        <span class="smallNote">OpenCover filters, line or space separated. Default is ${constants.openCoverDefaultFilters}</span>
        <span class="error" id="error_${constants.openCoverFilters}"></span></td>
    </td>
</tr>
<tr>
    <th><label>Additional options:</label></th>
    <td>
        <props:textProperty name="${constants.openCoverAdditionalOptions}" className="longField" />
        <span class="smallNote">Additional options to pass to OpenCover. Default is no options.</span>
        <span class="error" id="error_${constants.openCoverAdditionalOptions}"></span></td>
    </td>
</tr>
<tr>
    <th><label>Working directory</label></th>
    <td>
        <props:textProperty name="${constants.openCoverWorkingDirectory}" className="longField" />
        <bs:vcsTree fieldId="${constants.openCoverWorkingDirectory}" treeId="${constants.openCoverWorkingDirectory}"/>
        <span class="smallNote">Open directory. Default is checkout dir.</span>
        <span class="error" id="error_${constants.openCoverWorkingDirectory}"></span></td>
    </td>
</tr>
</l:settingsGroup>

<l:settingsGroup title="Tests assemblies">
<tr>
    <th><label>Test assemblies:</label></th>
    <td>
        <props:multilineProperty name="${constants.testsAssembliesPaths}"
                                       className="longField"
                                       linkTitle="tests assemblies paths"
                                       cols="55" rows="5"
                                       expanded="true"/>
        <span class="smallNote">New line separated list of test assemblies. Ant-like wildcards are supported (each line analysed independently)</span>
        <span class="error" id="error_${constants.testsAssembliesPaths}"></span></td>
    </td>
</tr>
</l:settingsGroup>

<l:settingsGroup title="Tests runner">
<tr>
    <th><label>Runner executable:</label></th>
    <td>
        <props:textProperty name="${constants.testsRunnerPath}" className="longField" />
        <bs:vcsTree fieldId="${constants.testsRunnerPath}" treeId="${constants.testsRunnerPath}"/>
        <span class="smallNote">Path to tests runner executable, ant-like wildcard in paths are supported as long as resolution will return only one result.</span>
        <span class="error" id="error_${constants.testsRunnerPath}"></span></td>
    </td>
</tr>
<tr>
    <th><label>Additional arguments for test runner:</label></th>
    <td>
        <props:textProperty name="${constants.testsRunnerArguments}" className="longField" />
        <bs:vcsTree fieldId="${constants.testsRunnerArguments}" treeId="${constants.testsRunnerArguments}"/>
        <span class="smallNote">Path to tests runner, ant-like paths are supported</span>
        <span class="error" id="error_${constants.testsRunnerArguments}"></span></td>
    </td>
</tr>
<tr>
    <th><label>Switch:</label></th>
    <td>
        <props:checkboxProperty name="${constants.passAssembliesAsSwitch}"/> <br />
        <props:textProperty name="${constants.testsAssembliesCommandLineSwitch}" className="longField" />
        <span class="smallNote">If test assemblies should be passed to runner as value of command-line switch instead as arguments (like MSTest) specify command line switch to prefix each assembly path with.</span>
        <span class="error" id="error_${constants.testsAssembliesCommandLineSwitch}"></span></td>
    </td>
</tr>
</l:settingsGroup>

<l:settingsGroup title="Reports Generator">
<tr>
    <th><label>Path to ReportGenerator:</label></th>
    <td>
        <props:textProperty name="${constants.getReportsGeneratorExecutablePath}" className="longField" />
        <bs:vcsTree fieldId="${constants.getReportsGeneratorExecutablePath}" treeId="${constants.getReportsGeneratorExecutablePath}"/>
        <span class="smallNote">ReportsGenerator executable relative to checkout root. Use * to enable dynamic discovery. Default is ${constants.getDefaultReportsGeneratorExecutablePath}</span>
        <span class="error" id="error_${constants.getReportsGeneratorExecutablePath}"></span></td>
    </td>
</tr>
<tr>
    <th><label>ReportGenerator output directory:</label></th>
    <td>
        <props:textProperty name="${constants.getReportsGeneratorOutputDirectory}" className="longField" />
        <bs:vcsTree fieldId="${constants.getReportsGeneratorOutputDirectory}" treeId="${constants.getReportsGeneratorOutputDirectory}"/>
        <span class="smallNote">ReportsGenerator output directory relative to checkout root. Default is ${constants.getDefaultReportsGeneratorOutputDirectory}</span>
        <span class="error" id="error_${constants.getReportsGeneratorOutputDirectory}"></span></td>
    </td>
</tr>
</l:settingsGroup>

<l:settingsGroup title="Coverage">
</l:settingsGroup>