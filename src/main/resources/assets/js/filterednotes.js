searchButton();
homeButton();
createButton();

function searchButton() {

    let search = document.querySelector(".boton .search");

    search.onclick = function() {

        let input = document.querySelector(".boton input");
        let searchTerm = input.value;
        if (searchTerm) {
            location.href = '/api/notes/search/' + searchTerm;
        }
    };
}

function homeButton () {

    let home = document.querySelector(".boton .home");

    home.onclick = function() {

        location.href = '/api/notes';
    };
}

function createButton () {
	
	let create = document.querySelector(".create");
	
	create.onclick = function() {
		
		location.href = '/html/create.html';
	}
}