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
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToStats()">Done</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			var leco = ${allStats};
		});
		function goToStats() {
			window.location = "stats";
		}

		</script>

	</body>
</html>
