<%@attribute name="pageName" required="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="o" tagdir="/WEB-INF/tags"%>
<c:choose>
	<c:when test="${ not empty userInfo.preferredUsername }">
		<c:set var="shortName" value="${ userInfo.preferredUsername }" />
	</c:when>
	<c:otherwise>
		<c:set var="shortName" value="${ userInfo.sub }" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${ not empty userInfo.name }">
		<c:set var="longName" value="${ userInfo.name }" />
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${ not empty userInfo.givenName || not empty userInfo.familyName }">
				<c:set var="longName" value="${ userInfo.givenName } ${ userInfo.familyName }" />
			</c:when>
			<c:otherwise>
				<c:set var="longName" value="${ shortName }" />
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
<div class="corner-ribbon red">Develop</div>
<div class="header">
  <div class="text-center ssp-logo">
    <a href="https://simplesamlphp.org/">
      <img src="resources/images/logo.jpg" alt="simplesamlphp">
    </a>
  </div>
  <h1 class="text-center">
    ${config.topbarTitle}
  </h1>
</div>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<c:if test="${ not empty pageName }">
				<div class="nav-collapse collapse">
						<security:authorize access="hasRole('ROLE_USER')">
		
							<ul class="nav hidden-desktop">
							<o:actionmenu />
							</ul>
	
						</security:authorize>
	
					<!-- use a full user menu and button when not collapsed -->
					<ul class="nav pull-right visible-desktop">
	                    <security:authorize access="hasRole('ROLE_USER')">
						<li class="dropdown">
							<a id="userButton" class="dropdown-toggle" data-toggle="dropdown" href="">${ longName } <i class="icon-user"></i> <span class="caret"></span></a>
							<ul class="dropdown-menu pull-right">
								<li><a href="manage/#user/profile" data-toggle="collapse" data-target=".nav-collapse">${ shortName }</a></li>
								<li><a href="/oidc/saml/logout" data-toggle="collapse" data-target=".nav-collapse" class="logoutLink"><spring:message code="topbar.logout"/><i class="icon-arrow-right"></i> </a></li>
							</ul>
						</li>
	                    </security:authorize>
	                    <security:authorize access="!hasRole('ROLE_USER')">
	                    <li>
	                    	<a id="loginButton" href="/oidc/saml/login" data-toggle="collapse" data-target=".nav-collapse"><i class="icon-lock"></i> <spring:message code="topbar.login"/></a>
	                    </li>
	                    </security:authorize>
	                </ul>
	                
	                <!--  use a simplified user button system when collapsed -->
	                <ul class="nav hidden-desktop">
	                    <security:authorize access="hasRole('ROLE_USER')">
						<li><a href="manage/#user/profile">${ longName }</a></li>
						<li><a href="/oidc/saml/logout" class="logoutLink"><i class="icon-arrow-right"></i> <spring:message code="topbar.logout"/></a></li>
	                    </security:authorize>
	                    <security:authorize access="!hasRole('ROLE_USER')">
	                    <li>
	                    	<a href="/oidc/saml/login" data-toggle="collapse" data-target=".nav-collapse"><i class="icon-lock"></i> <spring:message code="topbar.login"/></a>
	                    </li>
	                    </security:authorize>
	                </ul>
	                <form action="${ config.issuer }${ config.issuer.endsWith('/') ? '' : '/' }saml/logout" method="POST" class="hidden" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
	                
	            </div><!--/.nav-collapse -->
			</c:if>
        </div>
    </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('.logoutLink').on('click', function(e) {
			e.preventDefault();
			$('#logoutForm').submit();
		});
	});
</script>
