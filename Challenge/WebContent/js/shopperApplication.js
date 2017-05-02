


function showForm(){
	$("#applyForm").removeClass("hidden");
}

function hideForm(){
	$("#applyForm").addClass("hidden");
}

window.onload = function(){
	$('#formFields').load('./formFields.html');
};



