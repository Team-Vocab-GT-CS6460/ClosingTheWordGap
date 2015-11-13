<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
		<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
		<title>Profiles</title>
	</head>
	<body>
		<h1>Current Profiles!</h1>
		<br>
		<div id="profiles"></div>
		<br>

	    <form:form action="addProfile" commandName="newKid" method="post">
	        <p>Kid Name: <form:input type="text" path="name" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset" /></p>
	        <p><input type="submit" value=" Add " /></p>
	    </form:form>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>

		<script type="text/javascript">
	
		$(document).ready(function() {
			$.ajax({
				url : 'get/profiles',
				success : function(kids) {
					var html = '';
					for(var i = 0; i < kids.length; i++) {
						var kid = kids[i];
						html += '<button class="ui-btn">' + kid['name'] + '</button><br>';
					}
					$('#profiles').html(html);
				}
			});
		});
		
		</script>

	</body>
</html>