fetch("navbar.html")
      .then(res => res.text())
      .then(data => {
        document.getElementById("navbar-placeholder").innerHTML = data;
      });
$(document).ready(function () {
    // Volunteer & Sponsor Popups
    $("#volunteer-popup").dialog({ autoOpen: false, modal: true, width: 400 });
    $("#sponsor-popup").dialog({ autoOpen: false, modal: true, width: 400 });
  
    $(".volunteer-btn").click(function (e) {
      e.preventDefault();
      $("#volunteer-popup").dialog("open");
    });
  
    $(".sponsor-btn").click(function (e) {
      e.preventDefault();
      $("#sponsor-popup").dialog("open");
    });
  
    $("#corporate-toggle").change(function () {
      if ($(this).is(":checked")) {
        $("#corporate-fields").slideDown();
        $("#individual-fields").slideUp();
      } else {
        $("#corporate-fields").slideUp();
        $("#individual-fields").slideDown();
      }
    });
  
    
      $("#faq-accordion").accordion({
        collapsible: true,
        heightStyle: "content",
        animate: 200
      });
    
    
  
    // Form : Volunteer
    $("#volunteer-form").validate({
      rules: {
        firstname: "required",
        lastname: "required",
        phone: {
          required: true,
          minlength: 11,
          maxlength: 11
        },
        email: {
          required: true,
          email: true
        },
        city: "required",
        university: "required",
        department: "required"
      },
      messages: {
        firstname: "Please enter your first name",
        lastname: "Please enter your last name",
        phone: {
        required: "Please enter your phone number",
        minlength: "Phone must be 11 digits",
        maxlength: "Phone must be 11 digits"
        },
        email: "Please enter a valid email address",
        city: "Please enter your city",
        university: "Please enter your university",
        department: "Please enter your department"
        }
    });
  
    // Form : Sponsor
    $("#sponsor-form").validate({
      rules: {
        firstName: "required",
        lastName: "required",
        email: {
          required: true,
          email: true
        }
      },
      messages: {
        firstName: "Please enter your first name",
        lastName: "Please enter your last name",
        email: "Please enter a valid email address"
      }
    });
  });
  
