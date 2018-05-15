/*
===========================================
//检验所给参数是否为合法的windows目录或路径
===========================================
*/
function validDirOrFile(filename){
	var reg = /^(?!\.)[^\\\/:\*\?"<>\|]{1,255}$/;
	var lastReg=/\.$/;
	if(reg.test(filename)){
		if(lastReg.test(filename))
			return false;
		else
			return true;
	}
	else{
		return false;
	} 
}