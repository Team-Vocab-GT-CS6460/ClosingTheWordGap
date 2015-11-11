<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>Multi-page template</title> 
		<link rel="stylesheet" href="resources/themes/closing-the-word-gap.min.css" />
		<link rel="stylesheet" href="resources/themes/jquery.mobile.icons.min.css" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
		<style>
			.ui-page .ui-header {
				font-size: 35px;
			}
			body {
				font-family: "Comic Sans MS", "Comic Sans";
			}
		</style>
	</head> 
	<%
	String theme="d";
	%>
	<body> 
	<!-- Start of first page: #one -->
	<div data-role="page" id="one" data-theme="<%= theme%>">
		<div data-role="header" data-theme="<%= theme%>">
			<h1>Closing the Word Gap</h1>
		</div><!-- /header -->

		<div data-role="content" data-theme="<%= theme%>">	
			<h2>Closing the Word Gap</h2>
			
			<h3>Show internal pages:</h3>
			<p><a href="#fill_in_the_blank" data-role="button">Fill in the blank</a></p>
			<p><a href="#activity1" data-role="button">Activity 1</a></p>
			<p><a href="#activity2" data-role="button">Activity 2</a></p>
			<p><a href="#two" data-role="button">Show page "two"</a></p>
			<p><a href="#popup" data-role="button" data-rel="dialog" data-transition="pop">Show page "popup" (as a dialog)</a></p>
		</div><!-- /content -->
		
		<div data-role="footer" data-theme="<%= theme%>">
			<h4></h4>
		</div><!-- /footer -->
	</div><!-- /page one -->

	<div data-role="page" id="fill_in_the_blank" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			<h1>Fill in the blank!</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="<%= theme%>">
			<h1 onclick="document.getElementById('audio').play();">We can swim in the lake when the water is ______.</h1>
			<audio src="resources/sounds/calm.fill_blank.question.mp3" id='audio'></audio>
			<div class="ui-grid-c">
				<div class="ui-block-a">
					<div style="height:200px;text-align:center;">
						<img src="http://52.10.68.119:8080/images/calm.png" style="height:200px;" />
					</div>
				</div>
				<div class="ui-block-b">
					<div style="height:200px;text-align:center;">
						<img src="http://52.10.68.119:8080/images/blow.png" style="height:200px;" />
					</div>
				</div>
				<div class="ui-block-c">
					<div style="height:200px;text-align:center;">
						<img src="http://52.10.68.119:8080/images/autumn.png" style="height:200px;" />
					</div>
				</div>
				<div class="ui-block-d">
					<div style="height:200px;text-align:center;">
						<img src="http://52.10.68.119:8080/images/fall.png" style="height:200px;" />
					</div>
				</div>
			</div><!-- /grid-b -->
			<fieldset class="ui-grid-c">
				<div class="ui-block-a"><a href="#" data-role="button">Calm</a></div>
				<div class="ui-block-b"><a href="#" data-role="button">Blow</a></div>
				<div class="ui-block-c"><a href="#" data-role="button">Autumn</a></div>
				<div class="ui-block-d"><a href="#" data-role="button">Fall</a></div>
			</fieldset>
		</div><!-- /content -->
		<div data-role="footer" data-theme="<%= theme%>" style="text-align:right; padding-right:20px;">
			<a href="#one" data-role="button">Home</a>
		</div><!-- /footer -->
	</div><!-- /page two -->

	<div data-role="page" id="activity1" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			<h1>Listen and Choose!</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="<%= theme%>">
			<div class="ui-grid-b">
				<div class="ui-block-a">
					<div class="ui-bar ui-bar-a" style="height:200px;text-align:center;">
						<img src="resources/images/elephant.svg" style="height:200px;" />
					</div>
				</div>
				<div class="ui-block-b">
					<div class="ui-bar ui-bar-a" style="height:200px;text-align:center;">
						<img src="resources/images/donkey.svg" style="height:200px;" />
					</div>
				</div>
				<div class="ui-block-c">
					<div class="ui-bar ui-bar-a" style="height:200px;text-align:center;">
						<img src="resources/images/happy-cow.svg" style="height:200px;" />
					</div>
				</div>
			</div><!-- /grid-b -->
			<fieldset class="ui-grid-b">
				<div class="ui-block-a"><a href="#" data-role="button">Elephant</a></div>
				<div class="ui-block-b"><a href="#" data-role="button">Donkey</a></div>
				<div class="ui-block-c"><a href="#" data-role="button">Cow</a></div>
			</fieldset>
		</div><!-- /content -->
		<div data-role="footer" data-theme="<%= theme%>" style="text-align:right; padding-right:20px;">
			<a href="#one" data-role="button">Home</a>
		</div><!-- /footer -->
	</div><!-- /page two -->

	<div data-role="page" id="activity2" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			<h1>Listen and Choose!</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="<%= theme%>">
			<div class="ui-grid-b">
				<div class="ui-block-a">
					<div class="ui-bar ui-bar-a" style="height:200px;text-align:center;" >
						<img src="resources/images/elephant.svg" style="height:100px;" />
					</div>
				</div>
				<div class="ui-block-b">
					<div class="ui-bar ui-bar-a" style="height:200px;text-align:center;">
						<img src="resources/images/elephant.svg" style="height:150;" />
					</div>
				</div>
				<div class="ui-block-c">
					<div class="ui-bar ui-bar-a" style="height:200px;text-align:center;">
						<img src="resources/images/elephant.svg" style="height:200;" />
					</div>
				</div>
			</div><!-- /grid-b -->
			<fieldset class="ui-grid-b">
				<div class="ui-block-a"><a href="#" data-role="button">Small</a></div>
				<div class="ui-block-b"><a href="#" data-role="button">Medium</a></div>
				<div class="ui-block-c"><a href="#" data-role="button">Large</a></div>
			</fieldset>
		</div><!-- /content -->
		<div data-role="footer" data-theme="<%= theme%>" style="text-align:right; padding-right:20px;">
			<a href="#one" data-role="button">Home</a>
		</div><!-- /footer -->
	</div><!-- /page two -->

	<!-- Start of second page: #two -->
	<div data-role="page" id="two" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			<h1>Two</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="<%= theme%>">	
			<h2>Two</h2>
			<p>Test</p>
			<a href="#" data-role="button" data-icon="star">Star button</a>
			<p><a href="#one" data-direction="reverse" data-role="button">Back to page "one"</a></p>	
		</div><!-- /content -->
		
		<div data-role="footer" data-theme="<%= theme%>">
			<h4></h4>
		</div><!-- /footer -->
	</div><!-- /page two -->

	<!-- Start of third page: #popup -->
	<div data-role="page" id="popup" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			<h1>Dialog</h1>
		</div><!-- /header -->

		<div data-role="content" data-theme="<%= theme%>">	
			<h2>Popup</h2>
			<p>I have an id of "popup" on my page container and only look like a dialog because the link to me had a <code>data-rel="dialog"</code> attribute which gives me this inset look and a <code>data-transition="pop"</code> attribute to change the transition to pop. Without this, I'd be styled as a normal page.</p>		
			<p><a href="#one" data-rel="back" data-role="button" data-inline="true" data-icon="back">Back to page "one"</a></p>	
		</div><!-- /content -->
		
		<div data-role="footer" data-theme="<%= theme%>">
			<h4></h4>
		</div><!-- /footer -->
	</div><!-- /page popup -->

	<!-- Start of second page: #two -->
	<div data-role="page" id="two" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			<h1>Two</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="<%= theme%>">	
			<h2>Two</h2>
			<p>Test</p>
			<a href="#" data-role="button" data-icon="star">Star button</a>
			<p><a href="#one" data-direction="reverse" data-role="button">Back to page "one"</a></p>	
		</div><!-- /content -->
		
		<div data-role="footer" data-theme="<%= theme%>">
			<h4></h4>
		</div><!-- /footer -->
	</div><!-- /page two -->
	
	</body>
</html>