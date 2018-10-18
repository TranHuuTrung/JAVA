$(document).ready(function(){
        $("#login-form").on("submit", function(event) {
            event.preventDefault();
            var that = $(this);
            url = that.attr('action');
            type = that.attr('method');
            data = [];
            
            var data = {
                'email': $('input[name=email]').val(), //for get email
                'password': $('input[name=password]').val(),
                'command': $('input[name=command]').val()               
            };
            console.log(data);

            $.ajax({
                url: url,
                type: type,
                data: data,
                success: function(data, status){
                	if( request.responseText.indexOf( "<html>" ) > -1 )
                        window.location = window.location;
                	 if (data.redirect) {
                         // data.redirect contains the string URL to redirect to
                         window.location.href = data.redirect;
                     }
                     else {
                         // data.form contains the HTML for the replacement form
                    	 document.getElementById("message").innerHTML= ( data );
                     }
                
                }
            	
            });
            
            return false;
        });
        
    }) 