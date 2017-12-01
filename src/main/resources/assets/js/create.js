setupBackButton();
setupCreateButton();

function setupBackButton() {

    $(".buttons .back").click(function() {
		
    	location.href = '/api/notes'
	});
}

function setupCreateButton() {
	
	$(".textareas textarea").keyup(function() {
		
		if ($(".textareas .title").val() || $(".textareas .argument").val() || $(".textareas .tags").val()) {
			
			document.querySelector(".textareas .errortext").style.display = "none";
		}
	}); 
	
	
	
	$(".buttons .create").click(function() {
		
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
