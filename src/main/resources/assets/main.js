searchButton();
homeButton();


function searchButton() {

    var search = document.querySelector(".boton .search");

    search.onclick = function() {

        var input = document.querySelector(".boton input");
        var searchTerm = input.value;
        if (searchTerm) {
            location.href = '/api/notes/search/' + searchTerm;
        }
    };
}

function homeButton () {

    var home = document.querySelector(".boton .home");

    home.onclick = function() {

        location.href = '/api/notes';
    };
}