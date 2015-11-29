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
		<title>CWG-Management</title>
	</head>
	<%
	String theme="d";
	%>
	<body oncontextmenu="return false;">

		<div data-role="page" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>">${kid.name} Management
				<img src="resources/themes/images/cwg_logo.png" class="favicon">
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">
				<div data-role="strategies">
				   <label for="language" class="language">Language:</label>
				   <select name="language" id="language">
				      <option value="english">English</option>
				      <option value="spanish">Spanish</option>
				   </select>

				   <label for="strategy" class="strategy">Learning Strategy:</label>
				   <select name="strategy" id="strategy">
				      <option value="smart">Smart Tutoring</option>
				      <option value="synonyms">Focus on Synonyms</option>
				      <option value="antonyms">Focus on Antonyms</option>
				   </select>

				   <label for="activity" class="activity">Activity Type:</label>
				   <select name="activity" id="activity">
				      <option value="fill">Fill in the blanks</option>
				      <option value="drag">Drag and Drop</option>
				   </select>
				</div>
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToMgt()">Done</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			$('#language').val("${kid.language}").selectmenu('refresh', true);
			$('#strategy').val("${kid.strategy}").selectmenu('refresh', true);
			$('#activity').val("${kid.activity}").selectmenu('refresh', true);
		});
		function goToMgt() {
			var kid = ${kid.id};
			var language = $('#language').val();
			var strategy = $('#strategy').val();
			var activity = $('#activity').val();
			$.ajax({
				url : 'save/settings',
				data: {kid: kid, language: language, strategy: strategy, activity: activity},
				success : function(kids) {
					window.location = "management";
				}
			});
		}

		</script>

	</body>
</html>
