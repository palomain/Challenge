var eregex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

function testNotEmpty(val){
	return val != undefined && val.length != 0 ;
}

function testIsEmail(val){
	return val != undefined && eregex.test(val);
}

function testIs10DigitsNumber(val){
	return val != undefined && /^[0-9]{10}$/.test(val);
}

var validationInfo = {

		"email" :
		{
			errId: "emailHelp",
			validators : [ { validator : testNotEmpty, message : "Please specify your e-mail "}, 
						   { validator : testIsEmail, message : "The email must be in the correct format"}]		
		}, 
		"lname" : {
			
			errId: "lnameHelp",
			validators : [ { validator : testNotEmpty, message : "Please specify your last name"} ]		
		}, 
		"fname" : {
			
			errId: "fnameHelp",
			validators : [ { validator : testNotEmpty, message : "Please specify your first name"} ]		
		}, 
		"number" : {
			
			errId: "numberHelp",
			validators : [ { validator : testNotEmpty, message : "Please specify your cell phone number "}, 
						   { validator : testIs10DigitsNumber, message : "The cell phone number must be exactly 10 digits in length"}
			]		
		}
	
	};


function validateForm(){
	
	var error = false;
	
	for(var i in validationInfo ){
		var info = validationInfo[i];
		var val = $("#"+i).val();	
		var errMessageCont = $("#"+info.errId);	
		errMessageCont.addClass("hidden");
		
		for(var j = 0; j < info.validators.length; j++ ){
			if(!info.validators[j].validator(val)){
				errMessageCont.removeClass("hidden");
				errMessageCont.html(info.validators[j].message);
				error = true;
				break;
			}	
		}
		
	}
	
	if(error){
		bootbox.alert("Please check the errors in the form");
	}
	
	return !error;
	
	
}