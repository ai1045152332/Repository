/*
===========================================
//检验所给参数是否为合法的windows目录或路径
===========================================
*/
function validDirOrFile(filename){
	// 中文 英文 数字 () [] - _ : "" ? , 。 ! 空格 /
	var reg = /([a-zA-Z0-9\u4E00-\u9FA5\_\-\(\)\[\]\.\,\?\!\s\——\？\！\。\，\:\：\"\“\”])$/;
	if(reg.test(filename)){
		/*if(lastReg.test(filename))
			return false;
		else
			return true;*/
		return true;
	}
	else{
		return false;
	} 
}
