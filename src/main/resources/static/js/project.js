document.addEventListener("DOMContentLoaded", function () {
  const skillsSelect = document.getElementById("skills");

  skillsSelect.addEventListener("mousedown", function (e) {
    e.preventDefault();

    const option = e.target;
    if (option.tagName === "OPTION") {
      option.selected = !option.selected;
      this.dispatchEvent(new Event("change"));
    }
  });

  skillsSelect.addEventListener("change", function () {
    const selectedOptions = Array.from(this.selectedOptions);
    const container = document.getElementById("selectedSkillsContainer");
    container.innerHTML = "";

    selectedOptions.forEach((option) => {
      const card = document.createElement("span");
      card.className = "skill-card";
      card.innerHTML = `
              ${option.text}
              <i class="bi bi-x-circle remove-skill" data-value="${option.value}"></i>
          `;
      container.appendChild(card);
    });

    document.querySelectorAll(".remove-skill").forEach((btn) => {
      btn.addEventListener("click", function () {
        const value = this.dataset.value;
        const select = document.getElementById("skills");
        const option = Array.from(select.options).find(
          (opt) => opt.value === value
        );
        option.selected = false;
        select.dispatchEvent(new Event("change"));
      });
    });
  });
});

// Add date validation
document.getElementById("startDate").addEventListener("change", validateDates);
document.getElementById("endDate").addEventListener("change", validateDates);

function validateDates() {
  const startDate = document.getElementById("startDate");
  const endDate = document.getElementById("endDate");

  if (startDate.value && endDate.value) {
    if (new Date(startDate.value) > new Date(endDate.value)) {
      alert("Start date cannot be later than end date");
      startDate.value = "";
      endDate.value = "";
      duration.value = "";
    }
  }
}

// Calculate duration automatically
document.addEventListener("DOMContentLoaded", function () {
  const startDate = document.getElementById("startDate");
  const endDate = document.getElementById("endDate");
  const duration = document.getElementById("duration");

  function calculateDuration() {
    if (startDate.value && endDate.value) {
      const start = new Date(startDate.value);
      const end = new Date(endDate.value);
      const diffTime = Math.abs(end - start);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      const months = Math.floor(diffDays / 30);
      const days = diffDays % 30;

      duration.value =
        months > 0
          ? `${months} month${months > 1 ? "s" : ""}${
              days > 0 ? ` and ${days} day${days > 1 ? "s" : ""}` : ""
            }`
          : `${days} day${days > 1 ? "s" : ""}`;
    }
  }

  startDate.addEventListener("change", calculateDuration);
  endDate.addEventListener("change", calculateDuration);
});

// handle edit button click
function editProject(id) {
  fetch(`/admin/project/edit/${id}`)
    .then((response) => response.json())
    .then((project) => {
      document.getElementById("projectName").value = project.projectName;
      document.getElementById("projectDescription").value = project.description;
      document.getElementById("projectLink").value = project.projectLink;
      document.getElementById("startDate").value = project.projectStartDate;
      document.getElementById("endDate").value = project.projectEndDate;
      document.getElementById("duration").value = project.duration;

      // Clear existing selected skills container
      const selectedSkillsContainer = document.getElementById(
        "selectedSkillsContainer"
      );
      selectedSkillsContainer.innerHTML = "";

      // Create and display skill cards
      const skillsArray = project.skills.split(",");
      skillsArray.forEach((skill) => {
        const card = document.createElement("span");
        card.className = "skill-card";
        card.innerHTML = `
                  ${skill.trim()}
                  <i class="bi bi-x-circle remove-skill" data-value="${skill.trim()}"></i>
              `;
        selectedSkillsContainer.appendChild(card);
      });

      // Select the skills in dropdown
      const selectElement = document.getElementById("skills");
      Array.from(selectElement.options).forEach((option) => {
        option.selected = skillsArray
          .map((s) => s.trim().toLowerCase())
          .includes(option.value);
      });
    });
}

// Add this new function to handle the skills display and removal
function updateSelectedSkillsBadges() {
  const selectedSkillsContainer = document.getElementById(
    "selectedSkillsContainer"
  );
  selectedSkillsContainer.innerHTML = "";

  const selectElement = document.getElementById("skills");
  Array.from(selectElement.selectedOptions).forEach((option) => {
    const badge = document.createElement("span");
    badge.className = "badge bg-primary me-1 mb-1";
    badge.innerHTML = `
          ${option.text}
          <i class="bi bi-x-circle ms-1" style="cursor: pointer;" onclick="removeSkill('${option.value}')"></i>
      `;
    selectedSkillsContainer.appendChild(badge);
  });
}

// Add this function to handle skill removal
function removeSkill(skillValue) {
  const selectElement = document.getElementById("skills");
  const option = Array.from(selectElement.options).find(
    (opt) => opt.value === skillValue
  );
  if (option) {
    option.selected = false;
    updateSelectedSkillsBadges();
  }
}

// Update the skills select event listener
document
  .getElementById("skills")
  .addEventListener("change", updateSelectedSkillsBadges);
