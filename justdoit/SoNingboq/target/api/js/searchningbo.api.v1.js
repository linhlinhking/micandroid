
function login(){
	$.post('/resource/user/login',{username:"leyxan.nb@gmail.com",password:"123456",key:"soningbo",email:"leyxan.nb@gmail.com"},success:function(data){alert(data)}) ;
}