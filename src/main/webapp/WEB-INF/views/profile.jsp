<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<link rel="stylesheet" href="resources/themes/closing-the-word-gap.min.css" />
		<link rel="stylesheet" href="resources/themes/jquery.mobile.icons.min.css" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />
		<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.js"></script>
		<title>Profiles</title>
		<style>
			.dog {
			    background: green;
			}
			.cat {
			    background: white;
			}
		</style>
	</head>
	<%
	String theme="d";
	%>
	<body>

		<!-- Start of second page: #two -->
		<div data-role="page" id="profile" data-theme="<%= theme%>">
	
			<div data-role="header" data-theme="<%= theme%>">
				<h1>Current Profiles!</h1>
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">	
				<div id="profiles" style="display: flex;"></div>

			    <form:form action="profile" commandName="newKid" method="post">
				    <div style="border: solid; display: flex; padding-left: 100px;">
				    	<div>
							Kid Name: <form:input type="text" path="name" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset" />
							<br>
							<input type="submit" value=" Add " class="ui-link ui-btn ui-shadow ui-corner-all" />
						</div>
						<div>
							<input type="radio" name="icon" value="dog" checked> <img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png width=100 height=100 /><br>
							<input type="radio" name="icon" value="cat"> <img src=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnYslakVXRM31dpE3NCKM8ek1Idgt8NGdNQN_-WR10lhOa4TAq width=100 height=100 />
						</div>
					</div>
			    </form:form>
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
						html += '<br><button class=' + kid['icon'] + ' onclick="alert(' + kid['id'] + ')">' + kid['name'] + '</button><br>';
					}
					$('#profiles').html(html);
				    $("[class='dog']")
				        .append("<img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png width=100 height=100 style='display: block; cursor: pointer;'/>")
				        .button();
				    $("[class='cat']")
				        .append("<img src=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnYslakVXRM31dpE3NCKM8ek1Idgt8NGdNQN_-WR10lhOa4TAq width=100 height=100 style='display: block; cursor: pointer;'/>")
				        .button();
				}
			});
		});

		</script>

	</body>
</html>