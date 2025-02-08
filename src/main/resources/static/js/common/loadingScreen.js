class FoodLoadingScreen {
  constructor() {
    this.loadingContainer = document.querySelector(".loading-container");
    this.loadingTexts = [
      "Flipping burgers...",
      "Brewing coffee...",
      "Almost ready...",
      "Preparing your feast...",
      "Adding secret sauce...",
    ];
  }

  start() {
    this.updateLoadingText();
    setTimeout(() => this.hide(), 2000); // Hide after 2 seconds
  }

  updateLoadingText() {
    const loadingText = document.querySelector(".loading-text");
    let currentIndex = 0;

    setInterval(() => {
      loadingText.textContent = this.loadingTexts[currentIndex];
      currentIndex = (currentIndex + 1) % this.loadingTexts.length;
    }, 500);
  }

  hide() {
    this.loadingContainer.style.opacity = 0;
    setTimeout(() => {
      this.loadingContainer.style.display = "none";
    }, 500);
  }
}

// Initialize loading screen
document.addEventListener("DOMContentLoaded", () => {
  const loadingScreen = new FoodLoadingScreen();
  loadingScreen.start();
});
