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
		<title>CWG Stats</title>
	</head>
	<%
	String theme="d";
	%>
	<body oncontextmenu="return false;">

		<div data-role="page" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>">${kid.name} Stats
				<img src="resources/themes/images/cwg_logo_name.png" class="favicon">
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">
				<h2>Good Performance:</h2>
				<div id="good" class="stats"></div>
				<h2>Room for Improvement:</h2>
				<div id="bad" class="stats"></div>
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToStats()">Done</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			var kid = ${kid.id};
			$.ajax({
				url : 'get/kid_stats',
				data: { kid: kid },
				success : function(stats) {
					var htmlgood = '';
					var htmlbad = '';
					var html = '';
					for(var i = 0; i < stats.length; i++) {
						var stat = stats[i];
						html = '<a data-role="button" data-theme="d" class="kidButton">';
						html += '<h3>' + stat.name + '</h3>';
						var efficiency = Math.round(10000 * stat.correct / stat.total) / 100;
						if(efficiency > 50) {
							html += '<h1 style="color: green;">' + efficiency + '%</h1>';
						} else {
							html += '<h1 style="color: orange;">' + efficiency + '%</h1>';
						}
						html += '<p style="margin:0;">correct: ' + stat.correct + '</p>';
						html += '<p style="margin:0;">total: ' + stat.total + '</p>';
						html += '</a>';
						if(efficiency > 50) {
							htmlgood += html;
						} else {
							htmlbad += html;
						}
					}
					$('#good').html(htmlgood);
					$('#bad').html(htmlbad);
				    $("[class='kidButton']").button();
				}
			});
		});
		function goToStats() {
			window.location = "stats";
		}

		</script>

	</body>
</html>
