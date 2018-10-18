$(document).ready(function(){
        $("#register-form").on("submit", function(event) {
            event.preventDefault();
            var that = $(this);
            url = that.attr('action');
            type = that.attr('method');
            data = [];
            
            //init value
//            $(this).find('[name]').each(function(index,value){
//            	var that = $(this);
//            	name = that.attr('name');
//            	value = that.val();
//            	data[name] = value;
//            });
            
            var data = {
                'email': $('input[name=email]').val(), //for get email
                'password': $('input[name=password]').val(),
                'password2': $('input[name=password2]').val(),
                'command': $('input[name=command]').val()
                
            };
            console.log(data);

            $.ajax({
                url: url,
                type: type,
                data: data,
                success: function(data, status){
                	document.getElementById("message").innerHTML= ( data );
                }
            	
            });
            
            return false;
        });
        
    }) 