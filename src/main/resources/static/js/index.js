document.getElementById("currentYear").textContent = new Date().getFullYear();

// Tawk.to Integration
var Tawk_API = Tawk_API || {},
  Tawk_LoadStart = new Date();
(function () {
  var s1 = document.createElement("script"),
    s0 = document.getElementsByTagName("script")[0];
  s1.async = true;
  s1.src = "https://embed.tawk.to/YOUR_TAWK_ID/default";
  s1.charset = "UTF-8";
  s1.setAttribute("crossorigin", "*");
  s0.parentNode.insertBefore(s1, s0);
})();

// Import chat responses
import { chatResponses } from "./chatResponses.js";

// Core toggle functionality - this must remain in index.js
document.getElementById("toggle-chat").addEventListener("click", function () {
  const chatContent = document.querySelector(".chat-content");
  chatContent.style.display =
    chatContent.style.display === "none" ? "block" : "none";
});

// Chat message handling
document.getElementById("send-message").addEventListener("click", function () {
  const input = document.getElementById("chat-input");
  const message = input.value
    .trim()
    .toLowerCase()
    .replace(/[^a-z0-9\s]/g, "") // Remove all special characters
    .replace(/\s+/g, " ") // Replace multiple spaces with single space
    .trim(); // Remove leading/trailing spaces

  if (message) {
    const messagesDiv = document.getElementById("chat-messages");
    messagesDiv.innerHTML += `<div class="mb-2"><strong>You:</strong> ${message}</div>`;
    input.value = "";

    // Find matching response from array
    const matchingResponse = chatResponses.find(
      (item) => item.question.toLowerCase() === message
    );
    const response = matchingResponse
      ? matchingResponse.answer
      : "Thanks for your message! Feel free to ask about my skills, projects, or how we can work together.";

    setTimeout(() => {
      messagesDiv.innerHTML += `<div class="mb-2"><strong>Bot:</strong> ${response}</div>`;
      messagesDiv.scrollTop = messagesDiv.scrollHeight;
    }, 1000);
  }
});
