<%@ include file="common/header.jspf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/navigation.jspf" %>

<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css"  rel="stylesheet">
<title>${title} Todo Page</title>		

<div class="container">
	<h1>Enter Todo Details</h1>
	<form:form method="post" modelAttribute="todo">
		<fieldset class="mb-3">				
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required"/>
			<form:errors path="description" cssClass="text-warning"/>
		</fieldset>
		
		<fieldset class="mb-3">				
			<form:label path="localDate">Target Date</form:label>
			<form:input type="text" path="localDate" required="required"/>
			<form:errors path="localDate" cssClass="text-warning"/>
		</fieldset>
		<c:if test="${ title == \"Update\" }">
			<fieldset class="mb-3">				
				<form:label path="done">is Done?</form:label>
				<form:checkbox path="done"/>
				<form:errors path="done" cssClass="text-warning"/>
			</fieldset>
		</c:if>
		<input type="submit" class="btn btn-success"/>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>
<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

<script type="text/javascript">
$('#localDate').datepicker({
    format: 'yyyy-mm-dd'
});
</script>