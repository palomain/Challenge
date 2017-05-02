
var fields = null;

window.onload = function(){
		var url = window.location.href;
		
		var fieldsStr = url.split("?")[1];
		fields = new URLSearchParams(fieldsStr);
		
		var keys = fields.keys();
		
		for(let key of keys){
			var val = fields.get(key);
			var el = $("#"+key );
			el.html(val);
			el.attr("disabled", "disabled");
			
			el.editable({
	    		validate: function(value) {
	    			var validators = validationInfo[key].validators;
	       			for( var j = 0; j < validators.length; j++){
	       				if(!validators[j].validator(value)){
	       					return validators[j].message;
	       				}
	       			}
	    		}
			});
		}
		
		$('#save_shopper').click(function() {
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

