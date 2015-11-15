<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>Closing the Word Gap</title>
		<link rel="stylesheet" href="resources/themes/closing-the-word-gap.min.css" />
		<link rel="stylesheet" href="resources/themes/jquery.mobile.icons.min.css" />
		<link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" />
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile.structure-1.4.5.min.css" />
		<link rel="stylesheet" href="resources/themes/wordgap.css" />
		<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.js"></script>
		<script src="resources/javascript/jquery.contenthover.js"></script>
		<%
		String theme="d";
		String url_prefix="http://52.10.68.119:8080";
		%>
		<script>
			url_prefix = "http://52.10.68.119:8080";

			$(document).ready(function() {
				current_question = -1;
				var data = null;
				
				$("#fill_in_the_blank_btn").click(function() {
				    $.ajax({
				    	type: "GET",
				        url: "get/quiz",
					    dataType: "json",
					    cache: false
				    }).then(function(json_data) {
				    	data = json_data;
				    	current_question = -1;
				    	$('.next_question').trigger('click');
				    });					
				});

				$(".next_question").click(function() {

				   current_question = current_question + 1;

			       $('button').removeClass("ui-btn-active");

			       $('#fill_in_the_blank_question').empty().append(data[current_question].question);
			       $("#fill_in_the_blank_question_audio").attr("src",'get/tts/?text=' + data[current_question].question);
			       $("#fill_in_the_blank_question_audio").trigger('play');

			       $('#fill_in_the_blank_answer1').empty().append(data[current_question].answers[0].word);
			       $('#fill_in_the_blank_answer1_img').empty().css('background','url(' + url_prefix + data[current_question].answers[0].imagePath + ')');
			       $("#fill_in_the_blank_answer1_audio").attr("src",'get/tts/?text=' + data[current_question].answers[0].word);

			       $('#fill_in_the_blank_answer2').empty().append(data[current_question].answers[1].word);
			       $('#fill_in_the_blank_answer2_img').empty().css('background','url(' + url_prefix + data[current_question].answers[1].imagePath + ')');
			       $("#fill_in_the_blank_answer2_audio").attr("src",'get/tts/?text=' + data[current_question].answers[1].word);

			       $('#fill_in_the_blank_answer3').empty().append(data[current_question].answers[2].word);
			       $('#fill_in_the_blank_answer3_img').empty().css('background','url(' + url_prefix + data[current_question].answers[2].imagePath + ')');
			       $("#fill_in_the_blank_answer3_audio").attr("src",'get/tts/?text=' + data[current_question].answers[2].word);

			       $('#fill_in_the_blank_answer4').empty().append(data[current_question].answers[3].word);
			       $('#fill_in_the_blank_answer4_img').empty().css('background','url(' + url_prefix + data[current_question].answers[3].imagePath + ')');
			       $("#fill_in_the_blank_answer4_audio").attr("src",'get/tts/?text=' + data[current_question].answers[3].word);

			       $('#fill_in_the_blank_answer1').off('click').on('click',(function() {
			       		$('#fill_in_the_blank_answer1').addClass("ui-btn-active");
						$('#fill_in_the_blank_answer1_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
						$(".next_question").show();
						$.fn.disableAnswers();
					}));

			       $('#fill_in_the_blank_answer2').off('click').on('click',(function() {
			       		$('#fill_in_the_blank_answer2').addClass("ui-btn-active");
						$('#fill_in_the_blank_answer2_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
						$(".next_question").show();
						$.fn.disableAnswers();
					}));

			       $('#fill_in_the_blank_answer3').off('click').on('click',(function() {
			       		$('#fill_in_the_blank_answer3').addClass("ui-btn-active");
						$('#fill_in_the_blank_answer3_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
						$(".next_question").show();
						$.fn.disableAnswers();
					}));

			       $('#fill_in_the_blank_answer4').off('click').on('click',(function() {
			       		$('#fill_in_the_blank_answer4').addClass("ui-btn-active");
						$('#fill_in_the_blank_answer4_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
						$(".next_question").show();
						$.fn.disableAnswers();
					}));


			       $('#fill_in_the_blank_answer' + data[current_question].correctAnswer).off('click').on('click',(function() {
			       		$('#fill_in_the_blank_answer' + data[current_question].correctAnswer).addClass("ui-btn-active");
						$('#fill_in_the_blank_answer' + data[current_question].correctAnswer + '_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:green; opacity:0.7;'><i class='ion-checkmark'  style='font-size: 13em;'></i></div>");
						$(".next_question").show();
						$.fn.disableAnswers();
					}));
				   
				   $(".next_question").hide();
				});
				$.fn.disableAnswers = function() {
				    $('#fill_in_the_blank_answer1').off('click');
				    $('#fill_in_the_blank_answer2').off('click');
				    $('#fill_in_the_blank_answer3').off('click');
				    $('#fill_in_the_blank_answer4').off('click');
				};
			});
		</script>
	</head> 
	<body> 
	<!-- Start of first page: #one -->
	<div data-role="page" id="one" data-theme="<%= theme%>">
		<div data-role="header" data-theme="<%= theme%>">Welcome ${kid.name}!</div><!-- /header -->

		<div data-role="content" data-theme="<%= theme%>">	
			<p><a href="#fill_in_the_blank" data-role="button" id="fill_in_the_blank_btn">Fill in the blank</a></p>
		</div><!-- /content -->
		
		<div data-role="footer" data-theme="<%= theme%>" style="text-align:right; padding-right:20px;">
			<a href="/profile" data-role="button">Back to Profiles</a>
		</div><!-- /footer -->
	</div><!-- /page one -->

	<div data-role="page" id="fill_in_the_blank" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			Fill in the blank!
		</div><!-- /header -->
		<div data-role="content" data-theme="<%= theme%>">
			<h1 onclick="document.getElementById('fill_in_the_blank_question_audio').play();" id="fill_in_the_blank_question"></h1>
			<audio type="audio/vnd.wav" id='fill_in_the_blank_question_audio'></audio>
			<div class="ui-grid-c">
				<div class="ui-block-a">
					<div onclick="document.getElementById('fill_in_the_blank_answer1_audio').play();">
						<div id="fill_in_the_blank_answer1_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;">
						</div>
						<audio id='fill_in_the_blank_answer1_audio' type="audio/vnd.wav"></audio>
					</div>
				</div>
				<div class="ui-block-b">
					<div onclick="document.getElementById('fill_in_the_blank_answer2_audio').play();">
						<div id="fill_in_the_blank_answer2_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;">
						</div>
						<audio id='fill_in_the_blank_answer2_audio'></audio>
					</div>
				</div>
				<div class="ui-block-c">
					<div onclick="document.getElementById('fill_in_the_blank_answer3_audio').play();">
						<div id="fill_in_the_blank_answer3_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;" >
						</div>
						<audio id='fill_in_the_blank_answer3_audio'></audio>
					</div>
				</div>
				<div class="ui-block-d">
					<div onclick="document.getElementById('fill_in_the_blank_answer4_audio').play();">
						<div id="fill_in_the_blank_answer4_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;">
						</div>
						<audio id='fill_in_the_blank_answer4_audio'></audio>
					</div>
				</div>
			</div><!-- /grid-b -->
			<fieldset class="ui-grid-c">
				<div class="ui-block-a"><button style="font-size:20px;" id="fill_in_the_blank_answer1"></button></div>
				<div class="ui-block-b"><button style="font-size:20px;" id="fill_in_the_blank_answer2"></button></div>
				<div class="ui-block-c"><button style="font-size:20px;" id="fill_in_the_blank_answer3"></button></div>
				<div class="ui-block-d"><button style="font-size:20px;" id="fill_in_the_blank_answer4"></button></div>
			</fieldset>
			<fieldset class="ui-grid">
				<button class="next_question" style="font-size:25px;">Next Question!</button>
			</fieldset>
		</div><!-- /content -->
		<div data-role="footer" data-theme="<%= theme%>" style="text-align:right; padding-right:20px;">
			<a href="#one" data-role="button">Back to Activities</a>
		</div><!-- /footer -->
	</div><!-- /page two -->
	
	</body>
</html>