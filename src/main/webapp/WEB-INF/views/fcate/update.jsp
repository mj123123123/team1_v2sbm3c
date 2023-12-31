<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dev.mvc.fcate.FcateVO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9091/fcate/update.do?cateno=2</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>카테고리 수정</div>

<%
FcateVO fcateVO = (FcateVO)request.getAttribute("fcateVO");
int cateno = fcateVO.getFcateno();
%>
  
<form name='frm' method='post' action='/fcate/update.do'>
  <input type='hidden' name='cateno' value='<%=cateno %>'>
  <div>
    <label>카테고리 이름</label>
    <input type="text" name="name" value="<%=fcateVO.getName() %>" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>

  <div style="margin-top: 20px;">
    <label>글수</label>
    <input type="text" name="cnt" value="<%=fcateVO.getCnt() %>" required="required" autofocus="autofocus" 
               class="form-control form-control-sm" style="width: 50%">
  </div>
  
  <div class="content_body_bottom">
    <button type="submit" class="btn btn-dark btn-sm">저장</button>
    <button type="button" onclick="history.back();" class="btn btn-dark btn-sm">취소</button> 
  </div>
</form>

<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>

