<%@include file="includes/header.jsp" %>
<%-- <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> --%>
<%@page import="java.util.List"%>
<%@page import="org.phoenix.entity.User"%>

<div class="container mtb">
	<div class="row">
		<div class="col-lg-6">
			<form action="${pageContext.request.contextPath}/operation" method="post">
				<label for="usernme">Username:</label>
				<input type="text" name="username" id="username" required="required"><br>
				<!--  -->
				<label for="usernme">Email:</label>
				<input type="email" name="email" id="email" required="required"><br>
				<input type="hidden" name="form" value="addUserOperation">
				<input type="submit" value="Add User">
			</form>
		</div>
	</div>
</div>
<%@include file="includes/footer.jsp" %>