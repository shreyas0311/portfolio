// handle browser back/close button
window.addEventListener("beforeunload", function () {
  fetch("/admin/logout", {
    method: "POST",
    credentials: "same-origin",
  });
});

// error toast message
document.addEventListener("DOMContentLoaded", function () {
  const toastElList = document.querySelectorAll(".toast");
  const options = {
    animation: true,
    autohide: true,
    delay: 3500,
    onHidden: function () {
      // Clear URL parameters after toast is hidden
      window.history.replaceState({}, document.title, window.location.pathname);
    },
  };
  toastElList.forEach((toast) => {
    const toastBootstrap = new bootstrap.Toast(toast, options);
    toastBootstrap.show();
  });
});
