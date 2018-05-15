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
            location.href = rootpath+"/settings/Settings_findLicenseKey.do";
            break;
        case 7:
            location.href = rootpath+"/settings/Settings_curriculumTypeOpt.do";
            break;
        case 8:
            location.href = rootpath+"/settings/Settings_curriculumImport.do";
            break;
        case 9:
            location.href = rootpath+"/settings/Settings_timeploy.do";
            break;
        case 10:
            location.href = rootpath+"/settings/Settings_recordName.do";
            break;
    }

}