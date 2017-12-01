searchButton();
homeButton();
setupDeleteButton();

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

function getIdFromUrl() {

    let url = location.href;
    let parts = url.split("/");
    return parts[5];
}

function setupDeleteButton() {
	
	let id = getIdFromUrl();
	
	$(".delete").click(function() {
		
		 axios
         .delete("/api/notes/" + id)
         .then(deletedRental => {
             location.href = '/api/notes';
         })
         .catch(error => console.error("Error deleting note!", error));

	});
}





