$(document).ready(function() {
    $("#birthday").datepicker();

    var languages = [
        "Python", "JavaScript", "Java", "C", "C++", "C#", "Go", "Rust", "PHP", "Swift"
    ];

    $("#language").autocomplete({
        source: languages
    });

    $("form").on("submit", function(event) {
        var isValid = true;

        $("input").removeClass("error");

        if ($("#language").val().trim() === "") {
            $("#language").addClass("error");
            isValid = false;
        }

        if ($("#email").val().trim() === "") {
            $("#email").addClass("error");
            isValid = false;
        }

        if ($("#website").val().trim() === "") {
            $("#website").addClass("error");
            isValid = false;
        }

        if (!isValid) {
            event.preventDefault(); 
        }
    });

    $("input").on("input", function() {
        if ($(this).val().trim() !== "") {
            $(this).removeClass("error");
        }
    });
});
