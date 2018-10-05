<%@ attribute name="js" required="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="o" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="push"></div>
</div>
<!-- end #wrap -->
<div id="cookies">
    <div id="cookies-wrapper">
        <p>
            <spring:message code="openconext.footer.cookies_text"/>
            <a href="<spring:message code="openconext.footer.cookies_link_url"/>" target="_blank">
            <spring:message code="openconext.footer.cookies_link_text"/>
            </a>
        </p>
        <a id="js-accept-cookies" class="cookies-ok" href="#">
            <spring:message code="openconext.footer.cookies_accept_btn_text"/>
        </a>

    </div>
</div>
<div id="footer">
        <div class="container">
		<div class="row">
			<div class="col-sm-3 ssp-footer__item"></div>
			<div class="col-sm-6 ssp-footer__item">
                        	<o:copyright />
			</div>
			<div class="col-sm-3 ssp-footer__item powered">
				<div class="ssp-footer__item__powered">
				 	Powered by <a href="<spring:message code="openconext.footer.git_link"/>" target="_blank"><spring:message code="openconext.footer.git_project"/></a>
				</div>
			</div>
		</div>
        </div>
</div>
<!-- javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="resources/bootstrap2/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/lib/underscore.js"></script>
<script type="text/javascript" src="resources/js/lib/backbone.js"></script>
<script type="text/javascript" src="resources/js/lib/purl.js"></script>
<script type="text/javascript" src="resources/js/lib/bootstrapx-clickover.js"></script>
<script type="text/javascript" src="resources/js/lib/bootstrap-sheet.js"></script>
<script type="text/javascript" src="resources/js/lib/bootpag.js"></script>
<script type="text/javascript" src="resources/js/lib/cookie.js"></script>
<script type="text/javascript" src="resources/js/lib/theme.js"></script>
<c:if test="${js != null && js != ''}">
        <script type="text/javascript" src="resources/js/client.js"></script>
        <script type="text/javascript" src="resources/js/scope.js"></script>
        <script type="text/javascript" src="resources/js/admin.js"></script>
	<script type="text/javascript" src="resources/js/service.js"></script>
</c:if>
<script type="text/javascript" src="resources/js/lib/retina.js"></script>
</body>
</html>
