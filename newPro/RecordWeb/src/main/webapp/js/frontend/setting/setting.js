var rootpath="";
function jump(index){
    rootpath = $("#root_path").val();
    switch (index)
    {
        case 0:
            location.href = rootpath+"/settings/Settings_logoList.do";
            break;
        case 1:
            location.href = rootpath+"/settings/Settings_findRecordInfo.do";
            break;
        case 2:
            location.href = rootpath+"/settings/Settings_findLiveplay.do";
            break;
        case 3:
            location.href = rootpath+"/settings/Settings_findNas.do";
            break;
        case 4:
            location.href = rootpath+"/settings/Settings_findNvr.do";
            break;
        case 5:
            location.href = rootpath+"/settings/Settings_findFtp.do";
            break;
        case 6:
            location.href = rootpath+"/settings/Settings_findLicense.do";
            break;
    }

}
function turn(index){

    rootpath = $("#root_path").val();
    switch (index){
        case 0:
            location.href = rootpath+"/settings/Settings_findLicense.do";
            break;
        case 1:
            location.href = rootpath+"/settings/Settings_initialization.do";
            break;
    }
}