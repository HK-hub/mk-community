window.addEventListener('load', function() {
    var toc_li = document.querySelector(".toc-box");
    for (var i = 0; i < toc_li.children[0].children.length; i++) {
        toc_li.children[0].children[i].addEventListener('mouseover', function() {
            this.children[0].style.color = "#fc5531";
            this.style.backgroundColor = "#f5f5fa";
        })

        toc_li.children[0].children[i].addEventListener('mouseleave', function() {
            this.children[0].style.color = "#2d2e2f";
            this.style.backgroundColor = "white";
        })

        toc_li.children[0].children[i].addEventListener('click', function() {
            for (var j = 0; j < toc_li.children[0].children.length; j++)
                toc_li.children[0].children[j].className = '';
            this.className = 'active';
        })
    }
})