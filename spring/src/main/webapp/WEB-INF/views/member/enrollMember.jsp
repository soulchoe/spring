<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<style>
	div#enroll-container{width:400px; margin:0 auto; text-align:center;}
	div#enroll-container input, div#enroll-container select {margin-bottom:10px;}
	.error{
		color:red;
		font-weight:bold;
	}
</style>
<div id="enroll-container">
	<%-- <form name="memberEnrollFrm" method="post" action="${path }/member/insertMember.do" >
		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="userId" id="userId_" required>
		<input type="password" class="form-control" placeholder="비밀번호" name="password" id="password_" required>
		<input type="password" class="form-control" placeholder="비밀번호확인" id="password2" required>
		<input type="text" class="form-control" placeholder="이름" name="userName" id="userName" required>
		<input type="number" class="form-control" placeholder="나이" name="age" id="age">
		<input type="email" class="form-control" placeholder="이메일" name="email" id="email" required>
		<input type="tel" class="form-control" placeholder="전화번호 (예:01012345678)" name="phone" id="phone" maxlength="11" required>
		<input type="text" class="form-control" placeholder="주소" name="address" id="address">
		<select class="form-control" name="gender" required>
			<option value="" disabled selected>성별</option>
			<option value="M">남</option>
			<option value="F">여</option>
		</select>
		<div class="form-check-inline form-check">
					취미 : &nbsp; 
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby0" value="운동"><label for="hobby0" class="form-check-label">운동</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby1" value="등산"><label for="hobby1" class="form-check-label">등산</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby2" value="독서"><label for="hobby2" class="form-check-label">독서</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby3" value="게임"><label for="hobby3" class="form-check-label">게임</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby4" value="여행"><label for="hobby4" class="form-check-label">여행</label>&nbsp;
		</div>
		<br/>
		<input type="submit" class="btn btn-outline-success" value="가입" >&nbsp;
		<input type="reset" class="btn btn-outline-success" value="취소">
	</form> --%>
	<springform:form modelAttribute="member" name="memberEnrollFrm" method="post" action="${path }/member/insertMember.do" >
		<springform:input path="userId" type="text" class="form-control" placeholder="아이디 (4글자이상)" name="userId" id="userId_"/>
		<springform:errors path="userId" cssClass="error"/>
		<springform:input path="password" type="password" class="form-control" placeholder="비밀번호" name="password" id="password_"/>
		<springform:errors path="password" cssClass="error"/>
		<input type="password" class="form-control" placeholder="비밀번호확인" id="password2">
		<springform:input path="userName" type="text" class="form-control" placeholder="이름" name="userName" id="userName"/>
		<springform:errors path="userName" cssClass="error"/>
		<springform:input path="age" type="number" class="form-control" placeholder="나이" name="age" id="age"/>
		<springform:errors path="age" cssClass="error"/>
		<springform:input path="email" type="email" class="form-control" placeholder="이메일" name="email" id="email"/>
		<springform:errors path="email" cssClass="error"/>
		<springform:input path="phone" type="tel" class="form-control" placeholder="전화번호 (예:01012345678)" name="phone" id="phone" maxlength="11"/>
		<springform:errors path="phone" cssClass="error"/>
		<springform:input path="address" type="text" class="form-control" placeholder="주소" name="address" id="address"/>
		<springform:errors path="address" cssClass="error"/>
		<springform:select path="gender" class="form-control" name="gender">
			<springform:option value="" label="성별"/>
			<springform:option value="M" label="남"/>
			<springform:option value="F" label="여"/>
		</springform:select>
		<div class="form-check-inline form-check">
					취미 : &nbsp; 
			<springform:checkbox path="hobby" class="form-check-input" name="hobby" id="hobby0" value="운동" label="운동"/>&nbsp;
			<springform:checkbox path="hobby" class="form-check-input" name="hobby" id="hobby1" value="등산" label="등산"/>&nbsp;
			<springform:checkbox path="hobby" class="form-check-input" name="hobby" id="hobby2" value="독서" label="독서"/>&nbsp;
			<springform:checkbox path="hobby" class="form-check-input" name="hobby" id="hobby3" value="게임" label="게임"/>&nbsp;
			<springform:checkbox path="hobby" class="form-check-input" name="hobby" id="hobby4" value="여행" label="여행"/>&nbsp;
		</div>
		<br/>
		<input type="submit" class="btn btn-outline-success" value="가입" >&nbsp;
		<input type="reset" class="btn btn-outline-success" value="취소">
	</springform:form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
