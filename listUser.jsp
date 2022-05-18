<%@include file="includes/header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List"%>
<%@page import="org.phoenix.entity.User"%>
<%-- <c:import url="includes/header.jsp"><c:param name="title" value="listUser"/></c:import> --%>
<%-- <jsp:include page="includes/header.jsp"></jsp:include> --%>
<<style>
td, th {
	padding: 10px;
}
</style>
 <div class="container mtb">
    <div class="row">
      <div class="col-lg-6">
<!--         <img class="img-responsive" src="img/agency.jpg" alt=""> -->
      </div>

      <div class="col-lg-6">
        <h2>Users Page...</h2>
        <strong>Listing Users</strong>
        <hr>
        <table border="1">
        	<thead>
        		<th>User ID</th>
        		<th>Username</th>
        		<th>Email</th>
        		<th>Operation</th>
        	</thead>
        	<c:forEach items="${listUsers}" var="user">
        		<c:url var="updateUrl" value="operation">
        			<c:param name="page" value="updateUser"></c:param>
        			<c:param name="usersId" value="${user.userId}"></c:param>
        			<c:param name="username" value="${user.username}"></c:param>
        			<c:param name="email" value="${user.email}"></c:param>
        		</c:url>
        		<c:url var="deleteUser" value="operation">
        			<c:param name="page" value="deleteUser"></c:param>
        			<c:param name="usersId" value="${user.userId}"></c:param>
        		</c:url>
        		<tr>
        			<td>${user.userId}</td>
        			<td>${user.username}</td>
        			<td>${user.email}</td>
        			<td>
        				<a href="${updateUrl}">Update</a> |
        				<a href="${deleteUser}" onclick="if(!confirm('Are you sure to delete this user?')) return false">Delete</a></td>
        			</td>
        		</tr>
        	</c:forEach>
        	
        	<%! //String deleteUrl; %>
        	<% /**
        	List<User>listUsers = (List)request.getAttribute("listUsers");
        	String updateUrl;
        	for(int i=0;i<listUsers.size(); i++){
        		out.print("<tr>");
	        		out.print("<td>"+listUsers.get(i).getUserId()+"</td>");
	        		out.print("<td>"+listUsers.get(i).getUsername()+"</td>");
	        		out.print("<td>"+listUsers.get(i).getEmail()+"</td>");
	        		updateUrl = request.getContextPath()+"/operation?page=updateUser"+
	        				"&usersId="+listUsers.get(i).getUserId()+
	        				"&username="+listUsers.get(i).getUsername()+
	        				"&email="+listUsers.get(i).getEmail();
	        		//
	        		deleteUrl = request.getContextPath()+"/operation?page=deleteUser"+
	        				"&usersId="+listUsers.get(i).getUserId();
	        		out.print("<td><a href="+updateUrl+">Update</a> | "); **/
        		
        	
        	%>
        	</tr>
        	<% // }// %>
        </table>
        
      </div>
    </div>
  </div>
  <%@include file="includes/footer.jsp" %>
<%-- <c:import url="includes/footer.jsp"></c:import> --%>
<%-- <jsp:include page="/includes/footer.jsp"></jsp:include> --%>