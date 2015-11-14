<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<link rel="stylesheet" href="resources/themes/wordgap.css" />
		<link rel="stylesheet" href="resources/themes/closing-the-word-gap.min.css" />
		<link rel="stylesheet" href="resources/themes/jquery.mobile.icons.min.css" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />
		<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.js"></script>
		<title>Profiles</title>
	</head>
	<%
	String theme="d";
	%>
	<body>

		<!-- Start of second page: #two -->
		<div data-role="page" id="profile" data-theme="<%= theme%>">
	
			<div data-role="header" data-theme="<%= theme%>">
				<h1>Closing the Word Gap</h1>
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">	
				<div id="profiles" class="profiles"></div>

			    <form:form action="profile" commandName="newKid" method="post">
				    <div data-role="collapsible">

						<h4>Add a new kid</h4>

						Name:
						<form:input type="text" path="name" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset" />

						Icon:
						<div class="profiles-radio">
							<input type="radio" name="icon" value="dog" checked> <img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png />
							<input type="radio" name="icon" value="cat"> <img src=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnYslakVXRM31dpE3NCKM8ek1Idgt8NGdNQN_-WR10lhOa4TAq />
						</div>

						<input type="submit" value=" Add " class="ui-link ui-btn ui-shadow ui-corner-all" />
					</div>
			    </form:form>

				<a href="#" data-role="button" data-icon="grid" style="text-align: left;"> View Stats </a>
			</div><!-- /content -->

			<div data-role="footer" data-theme="<%= theme%>">
				<h4></h4>
			</div><!-- /footer -->
		</div><!-- /page two -->
	
		<script type="text/javascript">
	
		$(document).ready(function() {
			$.ajax({
				url : 'get/profiles',
				success : function(kids) {
					var html = '';
					for(var i = 0; i < kids.length; i++) {
						var kid = kids[i];
						html += '<form:form action="activities" method="post">';
						html += '<a class=' + kid['icon'] + ' href="javascript:;" onclick="parentNode.parentNode.submit();">' + kid['name'] + '</a>';
						html += '<input type="hidden" name="kid" value="' + kid['id'] + '" />';
						html += '</form:form>';
					}
					$('#profiles').html(html);
				    $("[class='dog']")
				        .append("<img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png />")
				        .button();
				    $("[class='cat']")
				        .append("<img src=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnYslakVXRM31dpE3NCKM8ek1Idgt8NGdNQN_-WR10lhOa4TAq />")
				        .button();
				}
			});
		});

		</script>

	</body>
</html>