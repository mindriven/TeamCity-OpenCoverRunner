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
        <span class="smallNote">OpenCover executable relative to checkout root. Default is ${constants.openCoverDefaultPath}</span>
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
</l:settingsGroup>

<l:settingsGroup title="Tests runner">
</l:settingsGroup>

<l:settingsGroup title="Reports Generator">
</l:settingsGroup>

<l:settingsGroup title="Coverage">
</l:settingsGroup>