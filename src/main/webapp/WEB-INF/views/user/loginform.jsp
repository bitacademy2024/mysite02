<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
	String email = (String)request.getAttribute("email");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite02/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="/mysite02/user?a=login">
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="<%=(email != null ? email : "")%>">
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">					
					<%
						if("fail".equals(result)) {
					%>
						<p>
							로그인이 실패 했습니다.
						</p>
					<%
						}
					%>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="">안대혁</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018</p>
		</div>
	</div>
</body>
</html>