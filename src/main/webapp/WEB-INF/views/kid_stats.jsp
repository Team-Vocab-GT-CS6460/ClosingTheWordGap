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
				<img src="resources/themes/images/cwg_logo_name.png" class="favicon" onclick="goHome()">
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">
				<div id="good_stats">
					<h2 class="title">Good Performance:</h2>
					<div id="good" class="stats"></div>
				</div>
				<div id="bad_stats">
					<h2 class="title">Room for Improvement:</h2>
					<div id="bad" class="stats"></div>
				</div>
				<h1 id="not_found" style="visibility:hidden">No Activity Found</h1>
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToStats()">Back to Stats</a>
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
						var efficiency = Math.round(100 * stat.correct / stat.total);
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
					if(htmlgood || htmlbad) {
						if(htmlgood) {
							$('#good').html(htmlgood);
						} else {
							$('#good_stats').css("visibility", "hidden");
							$('#good_stats').css("height", "0");
						}
						if(htmlbad) {
							$('#bad').html(htmlbad);
						} else {
							$('#bad_stats').css("visibility", "hidden");
							$('#bad_stats').css("height", "0");
						}
					    $("[class='kidButton']").button();
					} else {
						$('#good_stats').css("visibility", "hidden");
						$('#good_stats').css("height", "0");
						$('#bad_stats').css("visibility", "hidden");
						$('#bad_stats').css("height", "0");
						$('#not_found').css("visibility", "visible");
					}
				}
			});
		});
		function goToStats() {
			window.location = "stats";
		}
		function goHome() {
			window.location="profile";
		};

		</script>

	</body>
</html>
