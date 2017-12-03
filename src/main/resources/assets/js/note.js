searchButton();
homeButton();
setupDeleteButton();
setupEditButton();
setupBackButton();


function searchButton() {

    var search = document.querySelector(".main .homeinputsearch .search");

    search.onclick = function() {

        var input = document.querySelector(".main .homeinputsearch input");
        var searchTerm = input.value;
        if (searchTerm) {
            location.href = '/api/notes/search/' + searchTerm;
        }
    };
}

function homeButton () {

    var home = document.querySelector(".main .homeinputsearch .home");

    home.onclick = function() {

        location.href = '/api/notes';
    };
}

function getIdFromUrl() {

    let url = location.href;
    let parts = url.split("/");
    return parts[5];
}

function setupEditButton() {
	
	let id = getIdFromUrl();
	let noteTitle = $(".textareas .title").val();
	let noteText = $(".textareas .argument").val();
	let noteTags = $(".textareas .tags").val();
	
	$(".main .editanddelete .edit").click(function() {
		
		document.querySelector(".main").style.display = "none";
		document.querySelector(".textareas").style.display = "initial";
		document.querySelector(".textareas").style.display = "flex";
		document.querySelector(".backandsave").style.display = "initial";
		document.querySelector(".backandsave").style.display = "flex";
	});
	
	$(".textareas textarea").keyup(function() {
		
		if ($(".textareas .title").val() || $(".textareas .argument").val() || $(".textareas .tags").val()) {
			
			document.querySelector(".textareas .errortext").style.display = "none";
		}
	}); 
	
	$(".backandsave .save").click(function() {
		
		let title = $(".textareas .title").val();
		let text = $(".textareas .argument").val();
		let tagsText = $(".textareas .tags").val();
		let tagsTextSplit = tagsText.split(" ");
		let tags = [];
		
		for (i = 0; i < tagsTextSplit.length; i++) {
			
			tags.push(tagsTextSplit[i]);
		}
		
		let note = {
				title: title,
				text: text,
				tags: tags
		}
		
		if (title && text && tagsText) {
			
			 axios
	         .put("/api/notes/" + id, note)
	         .then(createdNote => {
	             location.href = '/api/notes';
	         })
	         .catch(error => console.error("Error editing the note!", error));
			 
		} else {
			
			document.querySelector(".textareas .errortext").style.display = "initial";
			$(".textareas .title").val(noteTitle);
			$(".textareas .argument").val(noteText);
			$(".textareas .tags").val(noteTags);
		}
	});
}

function setupDeleteButton() {
	
	let id = getIdFromUrl();
	
	$(".main .editanddelete .delete").click(function() {
		
		 axios
         .delete("/api/notes/" + id)
         .then(deletedRental => {
             location.href = '/api/notes';
         })
         .catch(error => console.error("Error deleting note!", error));

	});
}

function setupBackButton() {
	
	let id = getIdFromUrl();
	
	$(".backandsave .back").click(function() {
		
		location.href = "/api/notes/" + id;
	});
} 
