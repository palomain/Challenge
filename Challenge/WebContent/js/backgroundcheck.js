
let fields = null;

window.onload = function(){
		const url = window.location.href;
		
		const fieldsStr = url.split("?")[1];
		fields = new URLSearchParams(fieldsStr);
		
		const keys = fields.keys();
		
		const mandatoryFields = ['email', 'fname', 'lname', 'number'];
		
		for(let field of mandatoryFields){
			if(!fields.has(field) || fields.get(field) == null || fields.get(field).trim().length == 0 ){
				goBack();
				
				return;
			}
		}
		
		for(let key of keys){
			const val = fields.get(key);
			const el = $("#"+key );
			if(val != null && val.trim().length > 0 ){
				el.html(val);
			}
			
			el.editable({
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
	
};

function goBack(){
	window.location = "./DestroySession";
}

