fetch("navbar.html")
  .then(res => res.text())
  .then(data => {
    document.getElementById("navbar-placeholder").innerHTML = data;
  });

$(function () {
  $(document).tooltip();
});

// Fetch data from team.json with AJAX and place it in HTML
document.addEventListener("DOMContentLoaded", function () {
  fetch("team.json")
    .then(response => response.json())
    .then(data => {
      const container = document.getElementById("team-container");
      container.innerHTML = "";

      data.forEach(member => {
        const memberDiv = document.createElement("div");
        memberDiv.className = "team-item";
        memberDiv.innerHTML = `
          <img src="${member.image}" alt="${member.name}" title="${member.name} - ${member.role}">
        `;
        container.appendChild(memberDiv);
      });
    })
    .catch(error => {
      console.error("Takım verileri yüklenemedi:", error);
      document.getElementById("team-container").innerText = "Takım bilgileri yüklenemedi.";
    });
});
