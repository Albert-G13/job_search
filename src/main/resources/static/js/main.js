window.addEventListener("load", onLoad);
function onLoad() {
    const form = document.getElementById("sort");
    if (!form) return;
    const select = form.querySelector('select[name="sort"]');
    const savedSort = localStorage.getItem("sort");
    if (savedSort && select) {
        const config = JSON.parse(savedSort);
        if (config.sort) {
            select.value = config.sort;
        }
    }
    if (select) {
        select.addEventListener("change", () => {
            const data = new FormData(form);
            const sortObj = Object.fromEntries(data);
            localStorage.setItem("sort", JSON.stringify(sortObj));
            form.submit();
        });
    }
}

document.addEventListener("DOMContentLoaded", function (){
    const roleSelect = document.getElementById("roleSelect");
    const nameLabel = document.getElementById("usernameLabel");
    const defaultLabelText = nameLabel.innerText;
    roleSelect.addEventListener("change", function (){
        const selectedText = roleSelect.options[roleSelect.selectedIndex].text.toLowerCase();
        if (selectedText.includes("работодатель") || selectedText.includes("employer")){
            nameLabel.innerText = "Имя компании";
        } else {
            nameLabel.innerText = defaultLabelText;
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('search-input');
    const form = document.getElementById('filter-form');
    const container = document.getElementById('vacancies-container');

    let timeout = null;

    searchInput.addEventListener('input', function() {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            const formData = new URLSearchParams(new FormData(form));

            fetch('/vacancies?' + formData.toString(), {
                headers: { 'X-Requested-With': 'XMLHttpRequest' }
            })
                .then(response => response.text())
                .then(html => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(html, 'text/html');
                    const newContent = doc.getElementById('vacancies-container').innerHTML;
                    container.innerHTML = newContent;

                    window.history.pushState({}, '', '/vacancies?' + formData.toString());
                });
        }, 500);
    });
});
