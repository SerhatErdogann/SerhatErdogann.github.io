var names = ["Ben", "Joel", "Judy", "Anne"];
var scores = [88, 98, 77, 88];

var $ = function (id) { return document.getElementById(id); };



window.onload = function () {
	$("display_results").onclick = displayResults;
	$("display_scores").onclick = displayScores;
	$("add").onclick = addScore;
	
	
};

function displayResults()
{
	var average = 0;
	var max =0;
	var maxP =0;
	for(var i=0;i<scores.length;i++)
	{
		average= (average*(i)+scores[i])/(i+1);
		if(scores[i]>max){
			max=scores[i];
			maxP=i;
		}
	}

	
	
	document.getElementById("results").innerHTML="<h2> Results </h2><br /> Average score is "+average + "<br \> High score = "+names[maxP]+" with a score of "+ max ;
}

function displayScores()
{
	var total = "<tr><th>Name</th><th>Score</th></tr>";
	for (var i = 0; i < scores.length; i++) {
        total += "<tr><td>" + names[i] + "</td><td>" + scores[i] + "</td></tr>";
    }

    document.getElementById("scores_table").innerHTML = "<h2>Scores</h2>" + total;
}
function addScore()
{
	
	
	if( $("score").value > 100 || 
	$("score").value.trim() === "" ||
	$("name").value.trim() === "" ||
	!/^[A-Za-z]+$/.test($("name").value.trim())){
		document.getElementById("scores_table").innerHTML = "You must enter a name and a valid score";

	}else{
		names.push($("name").value);
		scores.push($("score").value);
	
	}
	
	$("name").value = "";
	$("score").value = "";
	$("results").innerHTML = "";

	$("name").focus();
}





