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