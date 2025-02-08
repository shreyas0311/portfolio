$(document).ready(function () {
  // Load the loading screen component
  $("#loading-screen").load("/components/loading-screen.html");

  // Show loading screen on page navigation
  $(window).on("beforeunload", function () {
    $("#loading-screen").show();
  });

  // Handle loading screen for internal links
  $("a").click(function (e) {
    if (this.hostname === window.location.hostname) {
      $("#loading-screen").show();
    }
  });
});
