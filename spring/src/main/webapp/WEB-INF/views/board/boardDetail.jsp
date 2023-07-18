<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html> -->
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시글 상세조회"/>
</jsp:include>
<section>
	 <div id="board-container">
        <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle"  
        	value="${b.boardTitle }" required>
        <input type="text" class="form-control" name="boardWriter" value="${b.boardWriter.userId}" readonly required>
		<c:if test="${not empty b.file }">
			<c:forEach var="f" items="${b.file }">
            	<button type="button" 
                    class="btn btn-outline-success btn-block"
                    onclick="fn_fileDownload('${f.originalFilename}','${f.renamedFilename }')">${f.originalFilename }
            	</button>
        	</c:forEach>
        </c:if>
        <textarea class="form-control" name="boardContent" placeholder="내용" required>${b.boardContent }</textarea>
    	<button type="button" class="form-control btn-outline-danger" onclick="location.assign('${path}/board/deleteBoard.do?no=${b.boardNo }')">
    	삭제</button>
    </div>
	<script>
		function fn_fileDownload(oriName,reName){
			location.assign("${path}/board/fileDownload?oriname="+oriName+"&rename="+reName);
		}
	</script>
    <style>
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input,div#board-container button{margin-bottom:15px;}
    div#board-container label.custom-file-label{text-align:left;}
    </style>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>