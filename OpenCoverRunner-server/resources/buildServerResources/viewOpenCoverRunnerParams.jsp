<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="constants" class="mindriven.buildServer.OpenCoverRunner.server.OpenCoverRunnerConstantsBean"/>

<div class="parameter">
OpenCover executable: <strong><props:displayValue name="${constants.openCoverPath}" emptyValue="${constants.openCoverDefaultPath}"/></strong>
</div>
<div class="parameter">
OpenCover filters: <strong><props:displayValue name="${constants.openCoverFilters}" emptyValue="${constants.openCoverDefaultFilters}"/></strong>
</div>
<div class="parameter">
OpenCover additional options: <strong><props:displayValue name="${constants.openCoverAdditionalOptions}" emptyValue="none"/></strong>
</div>
<div class="parameter">
OpenCover working directory: <strong><props:displayValue name="${constants.openCoverWorkingDirectory}" emptyValue="same as checkout directory"/></strong>
</div>
<div class="parameter">
OpenCover output file path: <strong><props:displayValue name="${constants.openCoverOutputFilePath}" emptyValue="${constants.openCoverOutputDefaultFilePath}"/></strong>
</div>
<div class="parameter">
Tests assemblies paths: <strong><props:displayValue name="${constants.testsAssembliesPaths}" emptyValue="missing!"/></strong>
</div>
<div class="parameter">
Tests runner path: <strong><props:displayValue name="${constants.testsRunnerPath}" emptyValue="missing!"/></strong>
</div>
<div class="parameter">
Tests runner arguments: <strong><props:displayValue name="${constants.testsRunnerArguments}" emptyValue="none"/></strong>
</div>
<div class="parameter">
Pass test assemblies as command line switch: <strong><props:displayValue name="${constants.passAssembliesAsSwitch}" emptyValue="false"/></strong>
    <c:choose>
      <c:when test="${propertiesBean.properties[constants.passAssembliesAsSwitch]}">
         <strong>(<props:displayValue name="${constants.testsAssembliesCommandLineSwitch}" emptyValue="missing!"/>)</strong>
      </c:when>
    </c:choose>
</div>
<div class="parameter">
Reports Generator executable path: <strong><props:displayValue name="${constants.reportsGeneratorExecutablePath}" emptyValue="${constants.defaultReportsGeneratorExecutablePath}"/></strong>
</div>
<div class="parameter">
Reports Generator output directory: <strong><props:displayValue name="${constants.reportsGeneratorOutputDirectory}" emptyValue="${constants.defaultReportsGeneratorOutputDirectory}"/></strong>
</div>