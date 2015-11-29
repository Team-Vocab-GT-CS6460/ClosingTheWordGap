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
		<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.js"></script>
		<title>Profiles</title>
	</head>
	<%
	String theme="d";
	%>
	<body oncontextmenu="return false;">

		<div data-role="page" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>">Closing the Word Gap
				<img src="resources/themes/images/cwg_logo.png" class="favicon">
			</div><!-- /header -->
	
			<div data-role="content" data-theme="<%= theme%>">	
				<div id="profiles" class="profiles"></div>

			    <form:form action="profile" commandName="newKid" method="post" onsubmit="window.location.reload()" style="margin: 0;">
				    <div data-role="collapsible">

						<h4>Add a new kid</h4>

						Name:
						<form:input type="text" path="name" class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset" />

						Icon:
						<div class="profiles-radio">
							<input type="radio" name="icon" value="dog" checked> <img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png />
							<input type="radio" name="icon" value="cat"> <img src=http://cat-pictures.clipartonline.net/_/rsrc/1359117040773/Cute-Kittens-Images-Page-1/cat-image%2032.png />
						</div>

						<input type="submit" value=" Add " class="ui-link ui-btn ui-shadow ui-corner-all" />
					</div>
			    </form:form><!-- Add Kid -->

				<a data-role="button" onclick="goToStats()" data-icon="grid" style="text-align: left;"> View Stats </a>

				<a data-role="button" onclick="goToManagement()" data-icon="gear" style="text-align: left;"> Manage Profiles </a>

			</div><!-- /content -->

			<div data-role="footer" data-theme="<%= theme%>"></div><!-- /footer -->
		</div>

		<div data-role="popup" id="deletePopup" data-theme="<%= theme%>">
			<div data-role="header" data-theme="<%= theme%>" class="ui-corner-top ui-header ui-bar-d">
				<h1 class="ui-title">Delete Profile?</h1>
			</div>
			<div class="ui-corner-bottom ui-content">
				<h3>Are you sure you want to delete this profile?</h3>
				<a data-role="button" onclick="closePopup()" data-theme="<%= theme%>" class="ui-link ui-btn ui-btn-inline ui-shadow ui-corner-all">Cancel</a>
				<a data-role="button" onclick="tapholdHandler()" data-theme="<%= theme%>" class="ui-link ui-btn ui-btn-inline ui-shadow ui-corner-all">Delete</a>
			</div>
		</div>

		<script type="text/javascript">
	
		$(document).ready(function() {
			$( "#deletePopup" ).popup();
			$.ajax({
				url : 'get/profiles',
				success : function(kids) {
					var html = '';
					for(var i = 0; i < kids.length; i++) {
						var kid = kids[i];
						html += '<form:form class="activities" action="activities" method="post">';
						html += '<a class=' + kid['icon'] + ' href="javascript:;" onclick="parentNode.parentNode.submit();">' + kid['name'] + '</a>';
						html += '<input type="hidden" name="kid" value="' + kid['id'] + '" />';
						html += '</form:form>';
					}
					$('#profiles').html(html);
				    $("[class='dog']")
				        .append("<img src=http://images.clipartpanda.com/cute-dog-clipart-13289830681766660254dog.svg.hi.png />")
				        .button();
				    $("[class='cat']")
				        .append("<img src=http://cat-pictures.clipartonline.net/_/rsrc/1359117040773/Cute-Kittens-Images-Page-1/cat-image%2032.png />")
				        .button();
				    $('.activities').mousedown(function(event) {
				    	if (event.which === 3) { // right mouse click
				    		deleteKid = event.currentTarget.childNodes[1].value;
				    		$( "#deletePopup" ).popup( "open" );
				        }
				    });
				    $('.activities').on("taphold",function(event){
			    		deleteKid = event.currentTarget.childNodes[1].value;
			    		$( "#deletePopup" ).popup( "open" );
				    });
				}
			});
		});
		var deleteKid = 0;
		function closePopup() {
			$( "#deletePopup" ).popup( "close" );
		}
	    function tapholdHandler() {
	    	closePopup();
			$.ajax({
				url : 'delete/profile',
				data: {kid: deleteKid},
				success : function(kids) {
					location.reload();
				}
			});
		}
		function goToStats() {
			window.location = "stats";
		}
		function goToManagement() {
			window.location = "management";
		}

		</script>

	</body>
</html>
