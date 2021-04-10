window.addEventListener('load', function() {
    var close1 = document.querySelector(".announcement-banner>.close");
    var announcement_banner = document.getElementById("announcement-banner");
    close1.addEventListener('click', function() {
        announcement_banner.style.display = "none";
    })
})