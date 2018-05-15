function parse_options () {
    var url = document.URL;
    var params;
    var options = {};
    if (url.lastIndexOf("?")>0)
    {
        params = url.substring(url.lastIndexOf("?") + 1, url.length);
        params = params.split("&");
        for(var i=0;i<params.length;i++)
        {
            var param = params[i].split("=");
            options[decodeURIComponent(param[0])] = decodeURIComponent(param[1]);
        }
    }
    return options;
}
function build_preview (resObj,options) {
    var pageWidth = window.innerWidth;
    var pageHeight = window.innerHeight;
    if (typeof pageWidth != "number") {
        if (document.compatMode == "CSS1Compat") {
            pageWidth = document.documentElement.clientWidth;
            pageHeight = document.documentElement.clientHeight;
        } else {
            pageWidth = document.body.clientWidth;
            pageHeight = document.body.clientHeight;
        }
    }

    var previewHTML;
    var nodeSelector;
    var previewCSS = null;
    var postProc = undefined;
    switch(resObj.type)
    {
        case 1: // image
        case 2: // video
            var w = resObj.width;
            var h = resObj.height;
            var scale = pageWidth / w;
            if (h * scale > pageHeight)
                scale = pageHeight / h;
            if (scale > 1.0)
                scale = 1.0;

            var l = (pageWidth - w * scale) / 2;
            var t = (pageHeight - h * scale) / 2;

            previewCSS = {"width":w*scale,"height":h*scale,"left":l,"top":t};

            if (resObj.type == 1) {
                previewHTML = '<img class="preview_image" src="'+resObj.path+'" alt="Your browser does not support the img tag."></img>';
                nodeSelector = "img.preview_image";
            }
            else {
                previewHTML = '<video class="preview_video" controls="controls">'+
                    '<source src="'+resObj.path+'" type="video/mp4">Your browser does not support the video tag.</video>';
                nodeSelector = "video.preview_video";

                postProc = function () {
                    var videoNode = $("video.preview_video").get(0);
                    videoNode.play();
                }
            }
            break;
        case 3: // audio
            break;
        case 4: // flash
            previewHTML = '<div style="overflow: hidden;width:'+pageWidth+'px;height:'+pageHeight+'px">'+
                '<object class="preview_flash" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">'+
                '<param name="movie" value="'+resObj.path+'" />'+
                '<param name="quality" value="high" />'+
                '<param name="play" value="true" />'+
                '<param name="loop" value="true" />'+
                '<param name="wmode" value="transparent" />'+
                '<param name="scale" value="showall" />'+
                '<param name="menu" value="false" />'+
                '<embed class="preview_flash" src="'+resObj.path+'" quality="high" play="true" loop="true" wmode="transparent" scale="showall" menu="false" type="application/x-shockwave-flash"></embed>'+
                '</object></div>';
            break;
        case 5: // PPT
        case 6: // artical
            previewHTML = '<iframe class="preview_webpage" src="'+resObj.path+'" width="'+pageWidth+'px" height="'+pageHeight+'px"></iframe>';
            break;
        case 7: // Word
        case 8: // Excel
        case 9: // PDF
            previewHTML = '<div class="preview_scroll" id="scroll_wrapper"><div id="scroller">';
            var w = resObj.width;
            var h = resObj.height;
            if (w > pageWidth) {
                w = pageWidth;
                h = resObj.height * pageWidth / resObj.width;
            }
                widthStr = "100%";
            for (var i=resObj.start; i<=resObj.end; i++) {
                var singlePage = '<div class="scroll_item"><img src="'+resObj.path+i+'.jpg" width="'+w+'px" height="'+h+'px"></img></div>';
                previewHTML += singlePage;
            }
            previewHTML +='</div></div>';

            postProc = function () {
                window.scrollObj = new IScroll("#scroll_wrapper", {
                    scrollbars: true,
                    mouseWheel: true,
                    interactiveScrollbars: true,
                    shrinkScrollbars: 'scale',
                    fadeScrollbars: false
                });
                document.addEventListener("touchmove", function (e) { e.preventDefault(); }, false);
            }
            break;
    }

    $("div.preview").html(previewHTML);
    if (previewCSS)
        $(nodeSelector).css(previewCSS);

    $("div.waiting").hide();
    $("div.preview").show();

    if (postProc)
        postProc();
}