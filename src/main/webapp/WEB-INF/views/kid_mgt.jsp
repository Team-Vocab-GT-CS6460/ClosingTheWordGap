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
		<title>CWG Management</title>
	</head>
	<%
	String theme="d";
	%>
	<body oncontextmenu="return false;">

		<div data-role="page" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>">${kid.name} Settings
				<img src="resources/themes/images/cwg_logo_name.png" class="favicon" onclick="goHome()">
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">
				<div data-role="print_language" class="combo-box">
				   <label for="print_language" class="print_language">Print Language:</label>
				   <select name="print_language" id="print_language">
				      <option value="english">English</option>
				      <option value="spanish">Spanish</option>
				   </select>
			   </div>

				<div data-role="speech_language" class="combo-box">
				   <label for="speech_language" class="speech_language">Speech Language:</label>
				   <select name="speech_language" id="speech_language">
				      <option value="english">English</option>
				      <option value="spanish">Spanish</option>
				   </select>
			   </div>

				<div data-role="activity" class="combo-box">
				   <label for="activity" class="activity">Activity:</label>
				   <select name="activity" id="activity">
				      <option value="smart">Smart Activity</option>
				      <option value="sentences">Sentences</option>
				      <option value="definitions">Definitions</option>
				      <option value="analogies">Analogies</option>
				   </select>
			   </div>

				<div data-role="word_relationship" class="combo-box">
				   <label for="word_relationship" class="word_relationship">Word Relationship:</label>
				   <select name="word_relationship" id="word_relationship">
				      <option value="smart">Smart Relationship</option>
				      <option value="synonyms">Synonyms</option>
				      <option value="antonyms">Antonyms</option>
				   </select>
			   </div>

				<div data-role="word_types" class="combo-box">
				   <label for="word_types" class="word_types">Word Type:</label>
				   <select name="word_types" id="word_types">
				      <option value="smart">Smart Word</option>
				      <option value="actions">Actions</option>
				      <option value="directional">Directional</option>
				      <option value="adjectives">Adjectives</option>
				      <option value="positional">Positional</option>
				   </select>
			   </div>

				<div data-role="sentence_types" class="combo-box">
				   <label for="sentence_types" class="sentence_types">Sentence Type:</label>
				   <select name="sentence_types" id="sentence_types">
				      <option value="smart">Smart Sentence</option>
				      <option value="definitions">Definitions</option>
				      <option value="comparisons">Comparisons</option>
				      <option value="causes">Causes</option>
				      <option value="sequencing">Sequencing</option>
				   </select>
				</div>
			</div>

			<div data-role="footer" data-theme="<%= theme%>">
				<a data-role="button" onclick="goToMgt()">Done</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			$('#print_language').val("${kid.print_language}").selectmenu('refresh', true);
			$('#speech_language').val("${kid.speech_language}").selectmenu('refresh', true);
			$('#activity').val("${kid.activity}").selectmenu('refresh', true);
			$('#word_relationship').val("${kid.word_relationship}").selectmenu('refresh', true);
			$('#word_types').val("${kid.word_types}").selectmenu('refresh', true);
			$('#sentence_types').val("${kid.sentence_types}").selectmenu('refresh', true);
		});
		function goToMgt() {
			var kid = ${kid.id};
			var print_language = $('#print_language').val();
			var speech_language = $('#speech_language').val();
			var activity = $('#activity').val();
			var word_relationship = $('#word_relationship').val();
			var word_types = $('#word_types').val();
			var sentence_types = $('#sentence_types').val();
			$.ajax({
				url : 'save/settings',
				data: {
					kid: kid, 
					print_language: print_language, 
					speech_language: speech_language, 
					activity: activity,
					word_relationship: word_relationship, 
					word_types: word_types, 
					sentence_types: sentence_types
				},
				success : function(kids) {
					window.location = "management";
				}
			});
		}
		function goHome() {
			window.location="profile";
		};

		</script>

	</body>
</html>
