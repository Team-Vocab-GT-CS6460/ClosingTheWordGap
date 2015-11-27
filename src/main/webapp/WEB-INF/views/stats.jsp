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
		<link rel="stylesheet" href="resources/themes/wordgap.css" />
		<link rel="icon" type="image/png" href="resources/themes/images/cwg_ico.png">
		<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
		<title>Stats</title>
	</head>
	<%
	String theme="d";
	%>
	<body oncontextmenu="return false;">

		<div data-role="page" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>">Overall Stats
				<img src="resources/themes/images/cwg_logo.png" class="favicon">
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">	
				<div id="stats" class="stats"></div>

				<hr>

				<div class="indicators">
					<a data-role="button" data-theme="<%= theme%>">
						<p>Best Category</p>
						<h3>${bestCategory}</h3>
						<h1 style="color: green;">${bestCategoryEfficiency}</h1>
					</a>
					<a data-role="button" data-theme="<%= theme%>">
						<p>Room for Improvement</p>
						<h3>${worstCategory}</h3>
						<h1 style="color: orange;">${worstCategoryEfficiency}</h1>
					</a>
				</div>
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToProfile()">Back to Profiles</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			$.ajax({
				url : 'get/profiles',
				success : function(kids) {
					var html = '';
					for(var i = 0; i < kids.length; i++) {
						var kid = kids[i];
						html += '<form:form class="kid_stats" action="kid_stats" method="post">';
						html += '<a class=' + kid['icon'] + ' href="javascript:;" onclick="parentNode.parentNode.submit();">' + kid['name'] + '</a>';
						html += '<input type="hidden" name="kid" value="' + kid['id'] + '" />';
						html += '</form:form>';
					}
					$('#stats').html(html);
				    $("[class='dog']")
				        .append("<img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png />")
				        .button();
				    $("[class='cat']")
				        .append("<img src=http://cat-pictures.clipartonline.net/_/rsrc/1359117040773/Cute-Kittens-Images-Page-1/cat-image%2032.png />")
				        .button();
				}
			});
		});
		function goToProfile() {
			//update last activity
			window.location="profile";
		}

		</script>

	</body>
</html>
