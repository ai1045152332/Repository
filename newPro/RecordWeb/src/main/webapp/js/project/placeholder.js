// JavaScript Document
var JPlaceHolder = {
	//检测
	_check : function(){
		return 'placeholder' in document.createElement('input');
	},
	//初始化
	init : function(){
		if(!this._check()){
			this.fix();
		}
	},
	//修复
	fix : function(){
		jQuery(':input[placeholder]').each(function(index, element) {
            var self = $(this), txt = self.attr('placeholder');
			self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));//定义input框外围div和css
			var pos = self.position(), h = self.outerHeight(true)+"px", paddingleft = self.css('padding-left');//获取input的属性值
			var holder = $('<span></span>').text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, 'line-Height':h, 'padding-Left':paddingleft, color:'#aaa'}).appendTo(self.parent());//
			self.focusin(function(e) {
                holder.hide();
            }).focusout(function(e) {
                if(!self.val()){
					holder.show();
				}
            });
			holder.click(function(e) {
                holder.hide();
				self.focus();
            });
        });
	}
};
//执行
jQuery(function(){
	JPlaceHolder.init();	
});