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
        <div id="likePage">
            <form name='baseForm' methos='GET' action="${pageContext.request.contextPath}/sendlike">
                <table>
                    <tr>
                        <td> Like for:</td>
                        <td>
                            <select class="sendSelect" name="idRec">
                                <c:forEach var="user" items="${listUser}">
                                    <option <c:if test="${idRec eq user.id}">selected</c:if> value="${user.id}">${user.name} ${user.surName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Comment:</td>
                        <td>
                            <textarea maxlength="200" name="comment" cols="60" rows="6"></textarea>
                        </td>

                    </tr>


                </table>
                <table>
                    <tr>
                        <td><label><input checked type="radio" name="typeBage" value="1"><img src="${pic1}"/></label></td>
                        <td><label><input type="radio" name="typeBage" value="2"><img src="${pic2}"/></label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="typeBage" value="3"><img src="${pic3}"/></label></td>
                        <td><label><input type="radio" name="typeBage" value="4"><img src="${pic4}"/></label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="typeBage" value="5"><img src="${pic5}"/></label></td>
                        <td><label><input type="radio" name="typeBage" value="6"><img src="${pic6}"/></label></td>
                    </tr>
                </table>
                <input class="sendButton" type="submit" value="Send">
            </form>
        </div>

    </div>
</div>

</body>
</html>