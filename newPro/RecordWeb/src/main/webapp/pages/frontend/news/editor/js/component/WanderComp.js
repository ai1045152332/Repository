/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-3-5
 * Time: 下午2:33
 * To change this template use File | Settings | File Templates.
 */
var WanderComp = {};
DSS.util.extend(WanderComp,AccordionComp);
DSS.util.extend(WanderComp,{
    imgClass : 'WANDER_COMP_img'
});

DSS.Options.comp_map['9'] = WanderComp;
