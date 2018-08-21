package com.honghe.recordhibernate.dao;
import org.apache.struts2.ServletActionContext;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lch on 2014/10/21.
 */
public class Pager {
    final String CSS_FIRST_PAGE="first";
    final String CSS_LAST_PAGE="last";
    final String CSS_PREVIOUS_PAGE="previous";
    final String CSS_NEXT_PAGE="next";
    final String CSS_INTERNAL_PAGE="page";
    final String CSS_HIDDEN_PAGE="hidden";
    final String CSS_SELECTED_PAGE="selected";

    /**
     * @var integer maximum number of page buttons that can be displayed. Defaults to 10.
     */
    private int maxButtonCount=5;
    /**
     * @var string the text label for the next page button. Defaults to 'Next &gt;'.
     */
    private String nextPageLabel;
    /**
     * @var string the text label for the previous page button. Defaults to '&lt; Previous'.
     */
    private String prevPageLabel;
    /**
     * @var string the text label for the first page button. Defaults to '&lt;&lt; First'.
     */
    private String firstPageLabel;
    /**
     * @var string the text label for the last page button. Defaults to 'Last &gt;&gt;'.
     */
    private String lastPageLabel;
    /**
     * @var string the text shown before page buttons. Defaults to 'Go to page: '.
     */
    String header;
    /**
     * @var string the text shown after page buttons.
     */
    private String footer="";
    /**
     * @var mixed the CSS file used for the widget. Defaults to null, meaning
     * using the default CSS file included together with the widget.
     * If false, no CSS file will be used. Otherwise, the specified CSS file
     * will be included when using this widget.
     */
    private String cssFile;
    private String ulcss = "yiiPager";
    /**
     * @var array HTML attributes for the pager container tag.
     */
    private Map<String,String> htmlOptions;

    private String html = "";

    private Integer pageCount;

    private Integer currentPage;

    private Boolean javascript = false;
    /**
     * Initializes the pager by setting some default property values.
     */
    public Pager(Integer pageCount,Integer currentPage,int maxButtonCount,String nextPageLabel,String prevPageLabel,String firstPageLabel,String lastPageLabel,String header,String footer,String cssFile,Map<String,String> htmlOptions)
    {
        if(this.nextPageLabel=="" || this.nextPageLabel == null)
            this.nextPageLabel="下一页";
        else
            this.nextPageLabel = nextPageLabel;
        if(this.prevPageLabel == "" || this.nextPageLabel==null)
            this.prevPageLabel="上一页";
        else
            this.prevPageLabel = prevPageLabel;
        if(this.firstPageLabel == "" || this.nextPageLabel==null)
            this.firstPageLabel="首页";
        else
            this.firstPageLabel = firstPageLabel;
        if(this.lastPageLabel == "" || this.nextPageLabel==null)
            this.lastPageLabel="尾页";
        else
            this.lastPageLabel = lastPageLabel;
        /*if(this.header == "" || this.nextPageLabel.equals(null))
            this.header="跳转";
        else
            this.header = header;*/
        if(this.htmlOptions.get("id").equals("") || this.htmlOptions.get("id")==null)
            this.htmlOptions.put("id",this.getId());
        else
            this.htmlOptions = htmlOptions;
        if(this.htmlOptions.get("class").equals("") || this.htmlOptions.get("class")==null)
            this.htmlOptions.put("class","pager");
        else
            this.htmlOptions = htmlOptions;
        this.maxButtonCount = maxButtonCount;
        this.footer = footer;
        this.cssFile = cssFile;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }

    public Pager(Integer pageCount,Integer currentPage)
    {
        this.nextPageLabel="下一页";
        this.prevPageLabel="上一页";
        this.firstPageLabel="首页";
        this.lastPageLabel="尾页";
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }
    public Pager(Integer pageCount,Integer currentPage,String prevPageLabel,String nextPageLabel,String firstPageLabel,String lastPageLabel,Boolean javascript)
    {
        this.nextPageLabel=nextPageLabel;
        this.prevPageLabel=prevPageLabel;
        this.firstPageLabel=firstPageLabel;
        this.lastPageLabel=lastPageLabel;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        if(javascript)
        {
            this.javascript = javascript;
        }
    }
    public Pager(Integer pageCount,Integer currentPage,int maxButtonCount ,String prevPageLabel,String nextPageLabel,String firstPageLabel,String lastPageLabel,Boolean javascript)
    {
        this.nextPageLabel=nextPageLabel;
        this.prevPageLabel=prevPageLabel;
        this.firstPageLabel=firstPageLabel;
        this.lastPageLabel=lastPageLabel;
        this.currentPage = currentPage;
        this.maxButtonCount = maxButtonCount;
        this.pageCount = pageCount;
        if(javascript)
        {
            this.javascript = javascript;
        }
    }
    public Pager(Integer pageCount,Integer currentPage,Boolean javascript)
    {
        this.nextPageLabel="下一页";
        this.prevPageLabel="上一页";
        this.firstPageLabel="首页";
        this.lastPageLabel="尾页";
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        if(javascript)
        {
            this.javascript = javascript;
        }
    }

    public String  getId()
    {
        return "page_1";
    }
    /**
     * Executes the widget.
     * This overrides the parent implementation by displaying the generated page buttons.
     */
    public String run()
    {
        //Pager.html = this.header;
        this.html = "";
        this.html = "<ul class="+this.ulcss+">";
        //StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.delete(0,stringBuilder.length());
        //Pager.html = stringBuilder.append(Pager.html).append("<ul class="+this.ulcss+">").toString();
//        $this->registerClientScript();
        //String[] buttons=this.createPageButtons();
        List buttons = this.createPageButtons();
//        if(buttons.size()<=4)
//            return null;
       // else
        //{
        if(buttons != null)
        {
            if(maxButtonCount >= buttons.size()-2){
                for(int j=0;j<buttons.size();j++)
                {
                    this.html += buttons.get(j);
                }
            }else{
                this.html += buttons.get(0);
                if(currentPage == 1){
                    for(int j = 1;j<buttons.size()-2;j++){
                        if(j>maxButtonCount){
                            break;
                        }
                        this.html += buttons.get(j);
                    }
                }else{
                    if(currentPage+maxButtonCount<=buttons.size()-1){
                        for(int j = currentPage-1;j<buttons.size()-2;j++){
                            if(j>=currentPage-1+maxButtonCount){
                                break;
                            }
                            this.html += buttons.get(j);
                        }
                    }else{
                        for(int j = buttons.size()-1-maxButtonCount;j<buttons.size()-1;j++){//button.size()-1为共有多少个页码，并且页码从倒数第maxButtonCount开始
                            this.html += buttons.get(j);
                        }
                    }
                }
                this.html += buttons.get(buttons.size()-1);
            }

        }

      //  }

        //echo CHtml::tag('ul',$this->htmlOptions,implode("\n",$buttons));

       // Pager.html += this.footer;
        this.html += "</ul>";
        //Pager.html = stringBuilder.append(Pager.html).append("</ul>").toString();
        return this.html;
    }

    /**
     * Creates the page buttons.
     * @return array a list of page buttons (in HTML code).
     */
    protected List createPageButtons()
    {
        int pageCount = this.pageCount;
        if(pageCount<=1)
        return null;

        //String[] buttons = new String[Integer.parseInt(this.maxButtonCount)+4];
        List buttons = new ArrayList();
        int currentPage=this.currentPage; // currentPage is calculated in getPageRange()

        // first page
        if(this.firstPageLabel != "" && this.firstPageLabel != null)
        {
            buttons.add(this.createPageButton(this.firstPageLabel,1,this.CSS_FIRST_PAGE,currentPage<=0,false));
        }
        // prev page
        int page;
        page = currentPage-1;
        if((page-1)<=0)
        {
            page=1;
        }
        if(this.prevPageLabel != "" && this.prevPageLabel != null)
        {
            buttons.add(this.createPageButton(this.prevPageLabel,page,this.CSS_PREVIOUS_PAGE,currentPage<=0,false));
        }
        // internal pages
        for(int i=2;i<this.pageCount+2;i++)
        {
            if(currentPage == i-1)
            {
                buttons.add(this.createPageButton(String.valueOf(i-1),i-1,this.CSS_SELECTED_PAGE,false,i==this.currentPage-1));
            }
            else {
                buttons.add(this.createPageButton(String.valueOf(i-1),i-1,this.CSS_INTERNAL_PAGE,false,i==this.currentPage-1));
            }
            //buttons[i]=this.createPageButton(String.valueOf(i-1),i-2,this.CSS_INTERNAL_PAGE,false,i==this.currentPage);
        }
        // next page
        int page_1 ;
        page_1 = currentPage+1;
        if(page_1 >= pageCount)
            page_1=pageCount;
        buttons.add(this.createPageButton(this.nextPageLabel,page_1,this.CSS_NEXT_PAGE,currentPage>=pageCount,false));
        //buttons[this.pageCount+2]=this.createPageButton(this.nextPageLabel,page_1,this.CSS_NEXT_PAGE,currentPage>=pageCount-1,false);

        // last page
        if(this.lastPageLabel != "" && this.lastPageLabel != null) {
            buttons.add(this.createPageButton(this.lastPageLabel, pageCount, this.CSS_LAST_PAGE, currentPage >= pageCount, false));
            //buttons[this.pageCount+3]=this.createPageButton(this.lastPageLabel,pageCount-1,this.CSS_LAST_PAGE,currentPage>=pageCount-1,false);
        }
        return buttons;
    }

    /**
     * Creates a page button.
     * You may override this method to customize the page buttons.
     * @param label String  the text label for the button
     * @param page Integer  the page number
     * @param classtyle String  the CSS class for the page button. This could be 'page', 'first', 'last', 'next' or 'previous'.
     * @param hidden boolean  whether this page button is visible
     * @param selected boolean  whether this page button is selected
     * @return string the generated button
     */
    protected String createPageButton(String label,int page,String classtyle,boolean hidden,boolean selected)
    {
        if(hidden || selected)
            classtyle +=" "+(hidden ? this.CSS_HIDDEN_PAGE : this.CSS_SELECTED_PAGE);
        /*String path = request.getRequestURI();
        String param = "";*/
        //String path = ServletActionContext.getRequest().getServletPath();
        //String param = ServletActionContext.getRequest().getQueryString();
        Map<String,String[]> params = ServletActionContext.getRequest().getParameterMap();
        String root_path = ServletActionContext.getRequest().getContextPath();
        String action_name = ServletActionContext.getActionContext(ServletActionContext.getRequest()).getName();
        String action_namespace = ServletActionContext.getActionMapping().getNamespace();
        String url = root_path+action_namespace+"/"+action_name+".do";
        String queryString = url+"?currentPage="+page;

        for (String key : params.keySet()) {
            String[] values = params.get(key);
            if(!key.equals("currentPage"))
            {
                for (int i = 0; i < values.length; i++) {
                    String value = "";
//                    try{
//                        value = URIEncoderDecoder.decode(values[i]);
//                    }catch (Exception e){
                        value = URLEncoder.encode(values[i]);
                    //}
                    queryString += "&"+key + "=" + value;
                }
            }
        }

        if(currentPage == page)
        {
            return "<li class='"+classtyle+" '>"+label+"</li>";
        }
        else
        {
            if(this.javascript)
            {
                return "<li class='"+classtyle+"'><a href='javascript:void(0)' onclick='jumpPage("+page+")'>"+label+"</a></li>";
            }
            else
            {
                return "<li class='"+classtyle+"'><a href='"+queryString+"'>"+label+"</a></li>";
            }
        }
    }
}
