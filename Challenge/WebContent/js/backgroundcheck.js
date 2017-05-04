


window.onload = function(){
		
		const fields = ['email', 'fname', 'lname', 'number', 'rcode'];
		
		for(let key of fields){
			
			const el = $("#"+key );
			
			el.editable({
				pk  : key,
				url : './UpdateSessionData', 
	    		validate: function(value) {
	    			if(!validationInfo[key]){
	    				return undefined;
	    			}
	    			
	    			const validators = validationInfo[key].validators;
	       			for( let j = 0; j < validators.length; j++){
	       				if(!validators[j].validator(value)){
	       					return validators[j].message;
	       				}
	       			}
	    		}
			});
		}
		
		$('#save_shopper').click(function() {
			$('#authNotChecked').addClass("hidden");
			if(!$("#auth").attr("checked")){
				$('#authNotChecked').removeClass("hidden");
				return false;
			}
			
		    $('.myeditable').editable('submit', {   //call submit
		        url: './SaveShopperApplication',
		        success : function(){
		        	window.location = "./success.html";
		        }, 
		        error : function(){
		        	window.location = "./error.html";
		        }
		    }); 
		});
		
		setTimeout(updateTimeLeft, 1000);
	
};

function updateTimeLeft(){
	const timeLeftSpan = $("#time-left");
	let timeStamp = +(timeLeftSpan.attr("timeLeft"));
	const timeLeft = timeStamp - 1000;
	timeStamp = timeStamp/1000;
	const hours = ~~(timeStamp/3600);
	timeStamp = ~~(timeStamp%3600);
	
	const minutes = ~~(timeStamp/60);
	const seconds = ~~(timeStamp%60);
	
	timeLeftSpan.attr("timeLeft", timeLeft);
	timeLeftSpan.html((hours/10<1? "0" : "") + hours + ":" + (minutes/10<1? "0" : "") + minutes + ":" + (seconds/10<1? "0" : "") + seconds  );
	
	setTimeout(updateTimeLeft, 1000);
}

function goBack(){
	window.location = "./DestroySession";
}

