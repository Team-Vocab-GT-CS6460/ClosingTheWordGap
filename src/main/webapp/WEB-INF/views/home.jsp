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
		<link rel="icon" type="image/png" href="resources/themes/images/cwg_ico.png">
		<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.js"></script>
		<%
		String theme="d";
		String url_prefix="http://52.10.68.119:8080";
		%>
		<script>
			url_prefix = "http://52.10.68.119:8080";

			function goToProfile() {
				window.location="profile";
			}

			$(document).ready(function() {
				
				var data = null;
				var correct_answers = 0;
				var wrong_answers = 0;
				
				$(".fill_in_the_blank_btn").click(function() {
					correct_answers = 0;
				 	wrong_answers = 0;
				 	current_question = -1;

				    $.ajax({
				    	type: "GET",
				        url: "get/quiz?" + Math.floor((Math.random() * 1000) + 1),
					    dataType: "json",
					    cache: false
				    }).then(function(json_data) {
				    	data = json_data;
				    	current_question = -1;
				    	$('.next_question').trigger('click');
				    });					
				});

				$(".audio_click").click(function() {
					var element = "#" + this.id.replace("img","audio");
					if(typeof $(element).attr("src") == 'undefined'){
						$(element).attr("src",$(element).attr("src-temp"));
					}
				});

				$(".next_question").click(function() {

				   $('#wrong_answer_definition').empty();

				   current_question = current_question + 1;
				   if(current_question < data.length) {
				       $('button').removeClass("ui-btn-active");

				       $('#fill_in_the_blank_question').empty().append(data[current_question].question);
				       $("#fill_in_the_blank_question_audio").attr("src",'get/tts/?text=' + data[current_question].ttsString);

				       $('#fill_in_the_blank_answer1').empty().append(data[current_question].answers[0].word);
				       $('#fill_in_the_blank_answer1_img').empty().css('background','url(' + url_prefix + data[current_question].answers[0].imagePath + ')');
				       $("#fill_in_the_blank_answer1_audio").attr("src-temp",'get/tts/?text=' + data[current_question].answers[0].word);
					   $("#fill_in_the_blank_answer1_audio").removeAttr("src");

				       $('#fill_in_the_blank_answer2').empty().append(data[current_question].answers[1].word);
				       $('#fill_in_the_blank_answer2_img').empty().css('background','url(' + url_prefix + data[current_question].answers[1].imagePath + ')');
				       $("#fill_in_the_blank_answer2_audio").attr("src-temp",'get/tts/?text=' + data[current_question].answers[1].word);
				   	   $("#fill_in_the_blank_answer2_audio").removeAttr("src");

				       $('#fill_in_the_blank_answer3').empty().append(data[current_question].answers[2].word);
				       $('#fill_in_the_blank_answer3_img').empty().css('background','url(' + url_prefix + data[current_question].answers[2].imagePath + ')');
				       $("#fill_in_the_blank_answer3_audio").attr("src-temp",'get/tts/?text=' + data[current_question].answers[2].word);
				       $("#fill_in_the_blank_answer3_audio").removeAttr("src");

				       $('#fill_in_the_blank_answer4').empty().append(data[current_question].answers[3].word);
				       $('#fill_in_the_blank_answer4_img').empty().css('background','url(' + url_prefix + data[current_question].answers[3].imagePath + ')');
				       $("#fill_in_the_blank_answer4_audio").attr("src-temp",'get/tts/?text=' + data[current_question].answers[3].word);
				       $("#fill_in_the_blank_answer4_audio").removeAttr("src");

				       $('#fill_in_the_blank_answer1').off('click').on('click',(function() {
				       		$('#fill_in_the_blank_answer1').addClass("ui-btn-active");
							$('#fill_in_the_blank_answer1_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
							$('#wrong_answer_definition').append(data[current_question].answers[0].word + ": " + data[current_question].answers[0].definition );
							$(".next_question").show();
							$.fn.disableAnswers();
							wrong_answers = wrong_answers + 1;
						}));

				       $('#fill_in_the_blank_answer2').off('click').on('click',(function() {
				       		$('#fill_in_the_blank_answer2').addClass("ui-btn-active");
							$('#fill_in_the_blank_answer2_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
							$('#wrong_answer_definition').append(data[current_question].answers[1].word + ": " + data[current_question].answers[1].definition );
							$(".next_question").show();
							$.fn.disableAnswers();
							wrong_answers = wrong_answers + 1;
						}));

				       $('#fill_in_the_blank_answer3').off('click').on('click',(function() {
				       		$('#fill_in_the_blank_answer3').addClass("ui-btn-active");
							$('#fill_in_the_blank_answer3_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
							$('#wrong_answer_definition').append(data[current_question].answers[2].word + ": " + data[current_question].answers[2].definition );
							$(".next_question").show();
							$.fn.disableAnswers();
							wrong_answers = wrong_answers + 1;
						}));

				       $('#fill_in_the_blank_answer4').off('click').on('click',(function() {
				       		$('#fill_in_the_blank_answer4').addClass("ui-btn-active");
							$('#fill_in_the_blank_answer4_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:red; opacity:0.7;'><i class='ion-close'  style='font-size: 13em;'></i></div>");
							$('#wrong_answer_definition').append(data[current_question].answers[3].word + ": " + data[current_question].answers[3].definition );
							$(".next_question").show();
							$.fn.disableAnswers();
							wrong_answers = wrong_answers + 1;
						}));


				       $('#fill_in_the_blank_answer' + data[current_question].correctAnswer).off('click').on('click',(function() {
				       		$('#fill_in_the_blank_answer' + data[current_question].correctAnswer).addClass("ui-btn-active");
							$('#fill_in_the_blank_answer' + data[current_question].correctAnswer + '_img').append("<div style='position:relative; top:0px; width:100%; height:100%; background:green; opacity:0.7;'><i class='ion-checkmark'  style='font-size: 13em;'></i></div>");
							$(".next_question").show();
							$.fn.disableAnswers();
							correct_answers = correct_answers + 1;
						}));

					   $(".next_question").hide();
					}
					else{
						$('#fill_in_the_blank_complete_correct').empty().append(correct_answers);
						$('#fill_in_the_blank_complete_wrong').empty().append(wrong_answers);
						$.mobile.pageContainer.pagecontainer("change", "#fill_in_the_blank_complete", {});
					}

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
		<div data-role="header" data-theme="<%= theme%>">Welcome ${kid.name}!
			<img src="resources/themes/images/cwg_logo.png" class="favicon">
		</div><!-- /header -->

		<div data-role="content" data-theme="<%= theme%>">
			<h1 style="text-align:center;">Language</h1><br />
			<p><a href="#fill_in_the_blank" data-role="button" class="fill_in_the_blank_btn" style="font-size:25px;">English</a></p>
			<p><a href="#fill_in_the_blank" data-role="button" class="fill_in_the_blank_btn" style="font-size:25px;">Espa&ntilde;ol</a></p>
		</div><!-- /content -->

		<div data-role="footer" data-theme="<%= theme%>">
			<a data-role="button" onclick="goToProfile()">Back to Profiles</a>
		</div><!-- /footer -->
	</div><!-- /page one -->

	<div data-role="page" id="fill_in_the_blank" data-theme="<%= theme%>">

		<div data-role="header" data-theme="<%= theme%>">
			Fill in the blank!
			<img src="resources/themes/images/cwg_logo.png" class="favicon">
		</div><!-- /header -->

		<div data-role="content" data-theme="<%= theme%>">
			<div class="ui-grid-a">
				<div class="ui-block-a" style="width:95%">
					<h1 onclick="document.getElementById('fill_in_the_blank_question_audio').play();" id="fill_in_the_blank_question"></h1>
				</div>
				<div class="ui-block-b" style="width:5%; text-align: right;">
					<h1 onclick="document.getElementById('fill_in_the_blank_question_audio').play();" style="font-size:40px;"><i class="ion-volume-medium"></i></h1>
				</div>
			</div>

			<audio id='fill_in_the_blank_question_audio'></audio>
			<div class="ui-grid-c">
				<div class="ui-block-a">
					<div onclick="document.getElementById('fill_in_the_blank_answer1_audio').play();">
						<div id="fill_in_the_blank_answer1_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;" class="audio_click">
						</div>
						<audio id='fill_in_the_blank_answer1_audio'></audio>
					</div>
				</div>
				<div class="ui-block-b">
					<div onclick="document.getElementById('fill_in_the_blank_answer2_audio').play();">
						<div id="fill_in_the_blank_answer2_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;" class="audio_click">
						</div>
						<audio id='fill_in_the_blank_answer2_audio'></audio>
					</div>
				</div>
				<div class="ui-block-c">
					<div onclick="document.getElementById('fill_in_the_blank_answer3_audio').play();">
						<div id="fill_in_the_blank_answer3_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;" class="audio_click">
						</div>
						<audio id='fill_in_the_blank_answer3_audio'></audio>
					</div>
				</div>
				<div class="ui-block-d">
					<div onclick="document.getElementById('fill_in_the_blank_answer4_audio').play();">
						<div id="fill_in_the_blank_answer4_img" style="height:200px;text-align:center; background: url(<%= url_prefix%>/images/cool.png); background-size:100%;" class="audio_click">
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
			<div>
				<h1 id="wrong_answer_definition" style="font-size: 20px; white-space: nowrap;"></h1>
			</div>
			<fieldset class="ui-grid">
				<button class="next_question" style="font-size:20px;">Next Question!</button>
			</fieldset>
		</div><!-- /content -->
		<div data-role="footer" data-theme="<%= theme%>">
			<a data-role="button" onclick="goToProfile()">Back to Profiles</a>
		</div><!-- /footer -->
	</div><!-- /page fill_in_the_blank -->

	<div data-role="page" id="fill_in_the_blank_complete" data-theme="<%= theme%>">
		<div data-role="header" data-theme="<%= theme%>">Congratulations ${kid.name}!
			<img src="resources/themes/images/cwg_logo.png" class="favicon">
		</div><!-- /header -->

		<div data-role="content" data-theme="<%= theme%>">
			<table style="font-size:60px; margin: 0 auto;">
				<tr>
					<th>Correct&nbsp;</th>
					<td><span id="fill_in_the_blank_complete_correct"></span></td>
				</tr>
				<tr>
					<th>Wrong&nbsp;</th>
					<td><span id="fill_in_the_blank_complete_wrong"></span></td>
				</tr>
			</table>
			<br />
			<p><a href="#fill_in_the_blank" data-role="button" class="fill_in_the_blank_btn" style="font-size:25px;">Start Again</a></p>
			<a href="#one" data-role="button" style="font-size:25px;">Change Language</a>
			<a href="#" onclick="goToProfile()" data-role="button" style="font-size:25px;">Finish</a>
		</div><!-- /content -->
		
		<div data-role="footer" data-theme="<%= theme%>">
			<!-- <a data-role="button" onclick="goToProfile()">Back to Profiles</a> -->
		</div><!-- /footer -->
	</div><!-- /page fill_in_the_blank_complete -->

	</body>
</html>