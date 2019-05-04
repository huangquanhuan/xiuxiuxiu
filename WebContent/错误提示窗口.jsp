

<%
	if (request.getAttribute("err") != null) {
		String errMessage = (String) request.getAttribute("err");
%>
<script type="text/javascript">
	alert(errMessage);
</script>
<%
	}
%>