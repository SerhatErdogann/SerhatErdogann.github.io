fetch("navbar.html")
    .then(res => res.text())
    .then(data => {
    document.getElementById("navbar-placeholder").innerHTML = data;
});
   
$(function () {
    $("#volunteer-bar").progressbar({ value: 76 }); 
    $("#toy-bar").progressbar({ value: 64 }); 
    $("#city-bar").progressbar({ value: 28 }); 
              });       
        