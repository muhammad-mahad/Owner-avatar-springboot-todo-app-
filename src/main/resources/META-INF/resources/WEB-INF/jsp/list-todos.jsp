<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<title>List Todos Page</title>		

<div class="container">
	<h1>Your Todos</h1>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>	
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.getId()}</td>
					<td>${todo.getDescription()}</td>
					<td>${todo.getLocalDate()}</td>
					<td>${todo.isDone()}</td>
					<td> <a href="delete-todo?id=${todo.getId()}" class="btn btn-warning">Delete</a>   </td>
					<td> <a href="update-todo?id=${todo.getId()}" class="btn btn-success">Update</a>   </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf" %>