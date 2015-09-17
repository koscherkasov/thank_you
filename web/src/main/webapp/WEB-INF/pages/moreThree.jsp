<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true" %>
<html>
<head>
  <spring:url value="/resources/pic/1.jpg" var="pic1"/>
  <spring:url value="/resources/pic/2.jpg" var="pic2"/>
  <spring:url value="/resources/pic/3.jpg" var="pic3"/>
  <spring:url value="/resources/pic/4.jpg" var="pic4"/>
  <spring:url value="/resources/pic/5.jpg" var="pic5"/>
  <spring:url value="/resources/pic/6.jpg" var="pic6"/>
  <spring:url value="/resources/pic/del.jpg" var="picDel"/>
  <spring:url value="/resources/css/style.css" var="mainCss"/>
  <link href="${mainCss}" rel="stylesheet">
</head>
<body>

<div id="page-wrap">
  <div id="head">
    <a href=${pageContext.request.contextPath}/user><h1> ThankYou </h1></a>
  </div>
  <div id="information">
    <div id="profile">
      <c:url value="/j_spring_security_logout" var="logoutUrl" />
      <script>
        function formSubmit() {
          document.getElementById("logoutForm").submit();
        }
      </script>
      <a href=${pageContext.request.contextPath}/user>${myUser.name} ${myUser.surName} </a> &nbsp &nbsp | &nbsp
      &nbsp <a href="javascript:formSubmit()">Logout</a>

      <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
      </form>
    </div>
    <div id="users">
      <a href=${pageContext.request.contextPath}/users>All users</a>
    </div>
  </div>
  <div id="base">
    <div id="listUser">
      <h2>You can not send more three like today.</h2>
    </div>

  </div>
</div>

</body>
</html>