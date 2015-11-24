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
		<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
		<title>Kid Stats</title>
	</head>
	<%
	String theme="d";
	%>
	<body oncontextmenu="return false;">

		<div data-role="page" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>">${kid.name} Stats</div>
	
			<div data-role="content" data-theme="<%= theme%>">	
				<div class="indicators">
					<a data-role="button" data-theme="<%= theme%>">
						<h3>${bestCategory}</h3>
						<h1 style="color: green;">${bestCategoryEfficiency}</h1>
					</a>
					<a data-role="button" data-theme="<%= theme%>">
						<h3>${worstCategory}</h3>
						<h1 style="color: orange;">${worstCategoryEfficiency}</h1>
					</a>
				</div>

				<div data-role="strategies">
				   <label for="strategy" class="strategy">Learning Strategy:</label>
				   <select name="strategy" id="strategy">
				      <option value="smart">Smart Tutoring</option>
				      <option value="synonyms">Focus on Synonyms</option>
				      <option value="antonyms">Focus on Antonyms</option>
				   </select>
				</div>
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToStats()">Done</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			$('#strategy').val("${kid.learningStrategy}").selectmenu('refresh', true);
		});
		function goToStats() {
			var kid = ${kid.id};
			var strategy = $('#strategy').val();
			$.ajax({
				url : 'save/strategy',
				data: {kid: kid, strategy: strategy},
				success : function(kids) {
					window.location = "stats";
				}
			});
		}

		</script>

	</body>
</html>
