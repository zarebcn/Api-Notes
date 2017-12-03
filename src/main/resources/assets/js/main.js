setupSearchButton();
setupCreateButton();
setupBackButton();
setupAddButton();


function setupSearchButton() {

    let search = document.querySelector(".main .inputandbutton .search");

    search.onclick = function() {

        let input = document.querySelector(".main .inputandbutton input");
        let searchTerm = input.value;
        if (searchTerm) {
            location.href = '/api/notes/search/' + searchTerm;
        }
    };
}

function setupCreateButton () {
	
	$(".main .create").click(function() {
		
		document.querySelector(".main").style.display = "none";
		document.querySelector(".textareas").style.display = "initial";
		document.querySelector(".textareas").style.display = "flex";
		document.querySelector(".backandadd").style.display = "initial";
		document.querySelector(".backandadd").style.display = "flex";
	});
}

function setupBackButton() {

    $(".backandadd .back").click(function() {
		
    	location.href = '/api/notes'
	});
}

function setupAddButton() {
	
	$(".textareas textarea").keyup(function() {
		
		if ($(".textareas .title").val() || $(".textareas .argument").val() || $(".textareas .tags").val()) {
			
			document.querySelector(".textareas .errortext").style.display = "none";
		}
	}); 
	
	
	
	$(".backandadd .add").click(function() {
		
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
	         .post("/api/notes/", note)
	         .then(createdNote => {
	             location.href = '/api/notes';
	         })
	         .catch(error => console.error("Error creating note!", error));
			 
		} else {
			
			clearTextareas();
			document.querySelector(".textareas .errortext").style.display = "initial";
		}
	});
}

function clearTextareas() {
	
	$(".textareas textarea").val('');
}