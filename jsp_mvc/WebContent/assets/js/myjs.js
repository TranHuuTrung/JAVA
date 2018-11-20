$(document).ready(function() {
	$("#loginform").submit(function(event){
		   var userName,passWord,msg="";
			userName = document.getElementsByName("userName")[0];
			passWord = document.getElementsByName("passWord")[0];
			switch(msg){
				case userName.value:
					msg="Tài khoản không được để trống";
					userName.focus();
					break;
				case passWord.value:
					msg="Mật khẩu không được để trống";
					passWord.focus();
					break;
			}
			if(userName.value.length>20 || (userName.value.length<3 && userName.value.length>0 )){
				msg="Tài khoản có độ dài từ 3 - 20 kí tự";
				userName.focus();
			}else
			if(passWord.value.length < 8 && passWord.value.length > 0){
				msg="Mật khẩu phải có ít nhất 8 kí tự";
				passWord.focus();
			}
			if(msg!=""){
				 $( "span#thongbao" ).text(msg).show().fadeOut( 4000 );
				 	event.preventDefault();
			}
	   })
   
   $("#registerform").submit(function(event){
	   var userName,passWord,repassWord,msg="";
		userName = document.getElementsByName("userName")[0];
		passWord = document.getElementsByName("passWord")[0];
		repassWord = document.getElementsByName("repassWord")[0];
		switch(msg){
			case userName.value:
				msg="Tài khoản không được để trống";
				userName.focus();
				break;
			case passWord.value:
				msg="Mật khẩu không được để trống";
				passWord.focus();
				break;
			case repassWord.value:
				msg="Nhập lại mật khẩu không được để trống";
				passWord.focus();
				break;
		}
		if(userName.value.length>20 || (userName.value.length<3 && userName.value.length>0 )){
			msg="Tài khoản có độ dài từ 3 - 20 kí tự";
			userName.focus();
		}else
		if(passWord.value.length < 8 && passWord.value.length > 0){
			msg="Mật khẩu phải có ít nhất 8 kí tự";
			passWord.focus();
		}else
		if(repassWord.value.length < 8 && repassWord.value.length > 0){
			msg="Nhập lại mật khẩu phải có ít nhất 8 kí tự";
			repassWord.focus();
		}else
		if(repassWord.value != passWord.value && repassWord.value.length > 0){
			msg="Nhập lại mật khẩu phải trùng với mật khẩu";
			repassWord.focus();
		}
		if(msg!=""){
			 $( "span#thongbao" ).text(msg).show().fadeOut( 4000 );
			 	event.preventDefault();
		}
   })
	$("#ThemNhanVienForm").submit(function(event){
		var TenNV,IdPB, ChucVu,DiaChi, SDT,msg="";
		TenNV = document.getElementsByName("TenNV")[0];
		IdPB = document.getElementsByName("IdPB")[0];
		ChucVu = document.getElementsByName("ChucVu")[0];
		DiaChi = document.getElementsByName("DiaChi")[0];
		SDT = document.getElementsByName("SDT")[0];
		switch(msg){
			case TenNV.value:
				msg="Tên nhân viên không được để trống";
				TenNV.focus();
				break;
			case ChucVu.value:
				msg="Chức vụ không được để trống";
				ChucVu.focus();
				break;
			case DiaChi.value:
				msg="Địa chỉ không được để trống";
				DiaChi.focus();
				break
			case SDT.value:
				msg="Số điện thoại không được để trống";
				SDT.focus();
				break;
		}
		if(TenNV.value.length>20 || (TenNV.value.length<3 && TenNV.value.length>0 )){
			msg="Tên nhân viên có độ dài từ 3 - 20 kí tự";
			TenNV.focus();
		}else
		if(DiaChi.value.length>20 || (DiaChi.value.length<3 && DiaChi.value.length>0 )){
			msg="Địa chỉ có độ dài từ 3 - 20 kí tự";
			DiaChi.focus();
		}else
		if(SDT.value.length != 10 && SDT.value.length > 0){
			msg="Số điện thoại có 10 chữ số";
			SDT.focus();
		}
		if(msg!=""){
			 $( "span#thongbao" ).text(msg).show().fadeOut( 4000 );
			 	event.preventDefault();
		}
	})
	$("#ThemPhongBanForm").submit(function(event){
		var TenPB,msg="";
		TenPB = document.getElementsByName("TenPB")[0];
		switch(msg){
			case TenPB.value:
				msg="Tên phòng ban không được để trống";
				TenNV.focus();
				break;
		}
		if(TenPB.value.length>20 || (TenPB.value.length<3 && TenPB.value.length>0 )){
			msg="Tên phòng ban có độ dài từ 3 - 20 kí tự";
			TenPB.focus();
		}
		if(msg!=""){
			 $( "span#thongbao" ).text(msg).show().fadeOut( 4000 );
			 	event.preventDefault();
		}
	})
	$("#EditPhongBanForm").submit(function(event){
		var TenPB,msg="";
		TenPB = document.getElementsByName("TenPB")[0];
		switch(msg){
			case TenPB.value:
				msg="Tên phòng ban không được để trống";
				TenNV.focus();
				break;
		}
		if(TenPB.value.length>20 || (TenPB.value.length<3 && TenPB.value.length>0 )){
			msg="Tên phòng ban có độ dài từ 3 - 20 kí tự";
			TenPB.focus();
		}
		if(msg!=""){
			 $( "span#thongbao" ).text(msg).show().fadeOut( 4000 );
			 	event.preventDefault();
		}
	})
})
