<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demo 데이터 전체보기"/>
</jsp:include>
<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정</th>
		</tr>
		데이터출력하기
		<c:if test="${empty devs }">
			<tr>
				<td colspan="7">데이터가 존재하지 않습니다</td>
			</tr>
		</c:if>
		<c:if test="${not empty devs }">
			<c:forEach var="d" items="${devs }">
				<tr>
					<td>${d.devNo }</td>
					<td>${d.devName }</td>
					<td>${d.devAge }</td>
					<td>${d.devEmail }</td>
					<td>${d.devGender }</td>
					<td>
						${Arrays.toString(d.devLang) }
					</td>
					<td><input type="button" value="수정"></td>
				</tr>
			</c:forEach>
		</c:if>		
	</table>	
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>