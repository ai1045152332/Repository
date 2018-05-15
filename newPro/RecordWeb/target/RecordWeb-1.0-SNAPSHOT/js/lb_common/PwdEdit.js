$(function(){
   
  
   var confrmpw;
 
   $('.text#oldpw').focus(function(){
	
	   if($('.text#oldpw').val()==""){
		   
	   $('#pweditdlg #hintmsg').css("display","none");
	   
	   }
   });
   
	$('.text#oldpw').blur(function(){
	  
	        var oldpw = $('.text#oldpw').val();
	     if(oldpw==""){
		 
		   $.messager.alert('提示', '请输入原密码！', 'show');
		   $('#pweditdlg #hintmsg').css("display","none");
		    
		 }
	    else{
		  $.ajax({
					url : '/ManagementCenter/user/User_confirmPassword.do',
					type : 'POST',
					data : {
						pw :oldpw
					},
					dataType : 'json',
					success : function(result) {
						var json = eval("(" + result + ")");
						if (json) {
							
							$('#pweditdlg #hintmsg').css("display","none");
							
						} else {
							
							$('#pweditdlg #hintmsg').css("display","inline");
						}
						
					 },
					error : function(result) {
							
						$('#pweditdlg #hintmsg').css("display","inline");
					
					
				  }
				  
				});
		  
		
		}
	  
	  });
	 $('.text#confirmpw').focus(function(){
	 
	      var newpw = $('.text#newpw').val();
		  if(newpw == ""){
		     
			 $.messager.alert('提示', '新密码为空,请输入新密码！', 'show');
		     
		  }
	    
	 
	 });
	 
	$('.btnalter#ok').click(function(){
	 
	       var oldpw = $('.text#oldpw').val();
	     if(oldpw==""){
		 
		   $.messager.alert('提示', '请输入原密码！', 'show');
		   
		      return ;
		    
		 }
	    if($('#pweditdlg #hintmsg').css("display")!="none"){
		
		      return;
		}
	confrmpw = $('.text#confirmpw').val();
		  if(confrmpw == ""){
		  
		  $.messager.alert('提示', '请输入确认密码！', 'show');
		  
		       return;
		     
		  }
		  else if($('.text#newpw').val()!=confrmpw){
		  
		     $.messager.alert('提示', '两次密码不一致,请重新输入！', 'show');
			     
				 return;
		  }
		  else  {
		              $('#pweditdlg').dialog('close');
			   $.ajax({
					url : '/ManagementCenter/user/User_changePassword.do',
					type : 'POST',
					data : {
						pw : confrmpw
					},
					dataType : 'json',
					success : function(result) {
						var json = eval("(" + result + ")");
						if (json) {
							
							 $.messager.alert('提示', '密码修改成功！', 'show');
						} else {
							
							$.messager.alert('提示', '密码修改失败！', 'show');
						}
						
					  },
				   error : function(result) {
						
							 $.messager.alert('提示', '密码修改失败！', 'show');
						
						
					  }
				});
		    
		  }
   
	});
	
});