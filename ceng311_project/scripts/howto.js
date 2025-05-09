fetch("navbar.html")
  .then(res => res.text())
  .then(data => {
    document.getElementById("navbar-placeholder").innerHTML = data;
  });

const stepCounts = {
  tiger: 3,
  horse: 4,
  monkey: 3,
};

function showSteps(toy) {
  const steps = stepCounts[toy] || 3;
  const popup = document.getElementById("popup-overlay");
  const title = document.getElementById("popup-title");
  const stepsDiv = document.getElementById("popup-steps");

  title.textContent = `${toy.charAt(0).toUpperCase() + toy.slice(1)} - Instructions`;
  stepsDiv.innerHTML = "";

  for (let i = 1; i <= steps; i++) {
    const stepLabel = document.createElement("p");
    stepLabel.textContent = `Step ${i}:`;
    stepLabel.className = "step-label";

    const img = document.createElement("img");
    img.src = `image/${toy}/${toy}-s${i}.jpg`;
    img.alt = `Step ${i}`;
    img.className = "popup-img";

    stepsDiv.appendChild(stepLabel);
    stepsDiv.appendChild(img);
  }

  popup.style.display = "flex";
}

function closePopup() {
  document.getElementById("popup-overlay").style.display = "none";
}

document.addEventListener("keydown", (e) => {
  if (e.key === "Escape") closePopup();
});

window.addEventListener("click", (e) => {
  const popup = document.getElementById("popup-overlay");
  if (e.target === popup) closePopup();
});

// Grab a random children's book from OpenLibrary
function getBook() {
  fetch("https://openlibrary.org/search.json?q=children")
    .then(res => res.json())
    .then(data => {
      const docs = data.docs;
      const randomBook = docs[Math.floor(Math.random() * docs.length)];
      const title = randomBook.title;
      const author = randomBook.author_name ? randomBook.author_name[0] : "Bilinmeyen";
      const box = document.getElementById("book-box");
      box.innerHTML = `
        <p><strong>${title}</strong></p>
        <p>Author: ${author}</p>
      `;
    })
    .catch(err => {
      document.getElementById("book-box").textContent = "No book suggestions were received.";
      console.error("Book API error:", err);
    });
}

// Automatically fetch books when page loads
document.addEventListener("DOMContentLoaded", getBook);
