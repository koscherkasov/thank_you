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
    <div id="sendLike">
        <c:if test="${myUser.id eq someUser.id}">
            <a href=${pageContext.request.contextPath}/like>&lt;SEND LIKE&gt;</a>
        </c:if>
        <c:if test="${myUser.id ne someUser.id}">
            <a href=${pageContext.request.contextPath}/like/?idRec=${someUser.id}>&lt;SEND LIKE&gt;</a>
        </c:if>

    </div>

    <div id="base">

        <c:if test="${showNotif}">
            <div id="notif">
                You have new like!
            </div>
        </c:if>

        <div id="nameAndInfo">
            <div id="fullName">
                ${someUser.name}<br>
                ${someUser.surName}
            </div>
            <div id="fullInfo">
                ${someUser.dep.name}<br>
                ${someUser.role.name}<br>
                ${someUser.email}
            </div>
        </div>

        <c:if test="${empty listLike}">
           No likes for the selected period
        </c:if>

        <div id="selectTime">
            <form name='timeForm' methos='GET' action="${pageContext.request.contextPath}/user/${someUser.id}">
                <select name="time">
                    <option value="1">all</option>
                    <option <c:if test="${time eq 5}">selected</c:if> value="5">day</option>
                    <option <c:if test="${time eq 4}">selected</c:if> value="4">week</option>
                    <option <c:if test="${time eq 2}">selected</c:if> value="2">month</option>

                </select>
                <input type="submit" value="Refresh">
            </form>
        </div>

        <c:if test="${not empty listLike}">

        <div id="comments">


            <c:forEach var="like" items="${listLike}">
                <div class="comment">

                    <c:if test="${myUser.id eq someUser.id}">
                        <c:if test="${not like.isShow}">
                            <div class="new">
                                NEW
                            </div>
                        </c:if>
                    </c:if>

                    <div class="picCom">
                        <div class="pic">
                            <c:set var="numPic" value="${like.typeBage}"/>
                            <c:choose>
                                <c:when test="${numPic == 1}">
                                    <img src="${pic1}"/>
                                </c:when>
                                <c:when test="${numPic == 2}">
                                    <img src="${pic2}"/>
                                </c:when>
                                <c:when test="${numPic == 3}">
                                    <img src="${pic3}"/>
                                </c:when>
                                <c:when test="${numPic == 4}">
                                    <img src="${pic4}"/>
                                </c:when>
                                <c:when test="${numPic == 5}">
                                    <img src="${pic5}"/>
                                </c:when>
                                <c:when test="${numPic == 6}">
                                    <img src="${pic6}"/>
                                </c:when>
                            </c:choose>

                        </div>
                        <div class="com">
                            <div class="commiter">
                                <a href=${pageContext.request.contextPath}/user/${like.sender.id}> ${like.sender.name} ${like.sender.surName}</a>

                                <c:if test="${myUser.id eq like.sender.id}">
                                    <div class="del">
                                        <a href=${pageContext.request.contextPath}/delLike?idLike=${like.id}> <img
                                                src="${picDel}"> </a>
                                    </div>
                                </c:if>
                            </div>
                            <div class="text">
                                    ${like.comment}
                            </div>
                            <div class="time">
                                    ${like.date}
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            </c:if>

        </div>
    </div>
</div>

</body>
</html>