package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordhibernate.entity.tools.ConfigUtil;
import com.honghe.recordweb.action.frontend.news.entity.Program;
import com.honghe.recordweb.util.base.util.StringUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.regex.RegexQuery;
import org.apache.lucene.util.Version;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(Query.class);
	static IndexSearcher indexSearcher = null;
	static {
		if (indexSearcher == null) {
			try {
				indexSearcher = new IndexSearcher(
						IndexReader.open(IndexBuilder.directory));
			} catch (Exception e) {
				log.error("indexSearcher 初始化失败", e);
			}
		}
	}

	public static synchronized void updateIndexSearcher() {
		try {
			indexSearcher = new IndexSearcher(
					IndexReader.open(IndexBuilder.directory));
		} catch (Exception e) {
			log.error("indexSearcher 初始化失败", e);
		}
	}

	public int getTopDocsLimit() {
		int topDocsNum = Integer.parseInt(ConfigUtil.get("topDocsNum").trim());
		if (topDocsNum < 1) {
			topDocsNum = Integer.MAX_VALUE;
		}
		return topDocsNum;
	}

	/**
	 * //todo 加注释
	 * @param request
	 * @param keyWords
	 * @param navPro
	 * @param username
	 * @return
	 * @throws ParseException
	 */
	public List<HighlightComponent> getDocList(HttpServletRequest request,
			String keyWords, String navPro, String username)
			throws ParseException {
		List<HighlightComponent> mapDocument = new ArrayList<HighlightComponent>(0);
		StringBuilder result = new StringBuilder("");

		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
				new String[] { "name", "theme", "note" }, IndexBuilder.analyzer);
		int topDocsNum = getTopDocsLimit();
		Filter filter = getFilter(request, navPro, username);
		org.apache.lucene.search.Query query = null;
		try {
			if (StringUtil.isEmpty(keyWords)) {
				keyWords = "***";
				Term t1 = new Term("name", keyWords);
				query = new WildcardQuery(t1);
			} else {
				query = getQueryer(keyWords);
			}
			TopDocs topDocs = indexSearcher.search(query, filter, topDocsNum);
			int count = topDocs.totalHits;// count

			// 高亮显示
			QueryScorer scorer = new QueryScorer(query);
			SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
					"<font color=\"red\">", "</font>");
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
			Highlighter highlight = new Highlighter(formatter, scorer);
			highlight.setTextFragmenter(fragmenter);

			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				Document document = indexSearcher.doc(scoreDoc.doc);
				String value = document.get("name");
				if (!"***".equals(keyWords)) {
					String str1 = "";
					if (value != null) {
						if(selectQuery(keyWords.trim())){
							if(value.contains(keyWords)){
								StringBuffer stringBuffer=new StringBuffer();
								HighlightHelper.highlight(keyWords,value,stringBuffer);
								str1=stringBuffer.toString();
							}
						}
						else{
							TokenStream tokenStream = IndexBuilder.analyzer
									.tokenStream("name", new StringReader(value));
							str1 = highlight.getBestFragment(tokenStream, value);
						}
					}
					if (StringUtil.isEmpty(str1)) {
						str1 = value;
					}
					mapDocument.add(new HighlightComponent(document, str1));
				} else {
					mapDocument.add(new HighlightComponent(document, value));
				}

				// listDocument.add(document);
			}
		} catch (Exception e) {
			log.error("搜寻过程出现错误", e);
		}
		return mapDocument;
	}

	/**
	 * //todo 加注释
	 * @param request
	 * @param keyWords
	 * @param navPro
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<Program> getTopPrograms(HttpServletRequest request,
			String keyWords, String navPro, String username) throws Exception {
		List<Program> listPrograms = new ArrayList<Program>(0);
		List<HighlightComponent> listDocuments = getDocList(request, keyWords,
				navPro, username);
		for (HighlightComponent entry : listDocuments) {
			Program program = Document2Program.convert(entry,username);
			if(null!=program)
				listPrograms.add(program);
		}
		return listPrograms;
	}

	/**
	 * //todo 加注释
	 * @param keyWords
	 * @return
	 */
	public boolean selectQuery(String keyWords){
		keyWords = keyWords.trim();
//		Pattern pattern = Pattern.compile(wizardStr);
//		 Matcher matcher = pattern.matcher("abcdwqwwqwqw中文中国11111aaaaaed");
//		 boolean b= matcher.matches();
//		String regex= "[\\S\\s]{0,}"+keyWords+"[\\S\\s]{0,}";
		String exchinese="^[^\u4e00-\u9fa5]{1,}$";
		//搜索关键字含空格使用第二套搜索规则
		String exchblank="^[^\u4e00-\u9fa5]{1,}(\\s)+[^\u4e00-\u9fa5]{1,}$";
		Pattern pattern = Pattern.compile(exchinese);
		Pattern patternblank = Pattern.compile(exchblank);
		Matcher matcher = pattern.matcher(keyWords);
		Matcher matcherblank = patternblank.matcher(keyWords);
		return matcher.matches()&&!matcherblank.matches();
	}

	/**
	 * //todo 加注释
	 * @param keyWords
	 * @return
	 * @throws ParseException
	 */
	public org.apache.lucene.search.Query getQueryer(String keyWords) throws ParseException{
		org.apache.lucene.search.Query query=null;
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36,
				new String[] { "name", "theme", "note" }, IndexBuilder.analyzer);
		keyWords = keyWords.trim();
		if(selectQuery(keyWords)){
			BooleanQuery boleanQuery = new BooleanQuery();
			keyWords = "[\\S\\s]{0,}"+keyWords+"[\\S\\s]{0,}";
			Term nameTerm=new Term("name",keyWords);
			Term themeTerm=new Term("theme",keyWords);
			Term noteTerm=new Term("note",keyWords);
			org.apache.lucene.search.Query nameQuery=new RegexQuery(nameTerm);
			org.apache.lucene.search.Query themeQuery=new RegexQuery(themeTerm);
			org.apache.lucene.search.Query noteQuery=new RegexQuery(noteTerm);
			boleanQuery.add(nameQuery, Occur.SHOULD);
			boleanQuery.add(themeQuery, Occur.SHOULD);
			boleanQuery.add(noteQuery, Occur.SHOULD);
			query=boleanQuery;
		}
		else {
			query = queryParser.parse(keyWords);
		}
		return query;
	}

	/**
	 * //todo 加注释
	 * @param request
	 * @param keyWords
	 * @param navPro
	 * @param username
	 * @return
	 * @throws ParseException
	 */
	public String query(HttpServletRequest request, String keyWords,
			String navPro, String username) throws ParseException {
		StringBuilder result = new StringBuilder("");
		
		// 针对不同的搜索类型使用不同的过滤方式
		Filter filter = getFilter(request, navPro, username);
		int topDocsNum = getTopDocsLimit();
		try {
			org.apache.lucene.search.Query query=getQueryer(keyWords);
			TopDocs topDocs = indexSearcher.search(query, filter, topDocsNum);
			int count = topDocs.totalHits;// count

			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				result.append(indexSearcher.doc(scoreDoc.doc).get("name")
						+ "\n");
			}
		} catch (Exception e) {
			log.error("搜寻过程出现错误", e);
		}
		return result.toString();
	}

	/**
	 * //todo 加注释
	 * @param request
	 * @param navPro
	 * @param username
	 * @return
	 * @throws ParseException
	 */
	private Filter getFilter(HttpServletRequest request, String navPro,
			String username) throws ParseException {
		Filter filter = null;
		String theme = request.getParameter("theme");
		String w = request.getParameter("w");
		String h = request.getParameter("h");
		String showcategory = request.getParameter("showcategory");
		BooleanQuery query = new BooleanQuery();
		// BooleanQuery booleanQuery = new BooleanQuery();
		Analyzer analyzer = IndexBuilder.analyzer;
		if ("1".equalsIgnoreCase(navPro)) {
			QueryParser queryParser = new QueryParser(Version.LUCENE_36,
					"createname", analyzer);
			query.add(queryParser.parse(username), Occur.MUST);
			query.add(new TermQuery(new Term("isEdit", "1")), Occur.MUST);
		} else if ("2".equalsIgnoreCase(navPro)) {
			query.add(new TermQuery(new Term("isEdit", "1")), Occur.MUST);
			query.add(new TermQuery(new Term("state", navPro)), Occur.MUST);
		} else if ("5".equalsIgnoreCase(navPro)) {
			query.add(new TermQuery(new Term("state", navPro)), Occur.MUST);
			query.add(new TermQuery(new Term("isEdit", "0")), Occur.MUST);
		} else if ("100".equalsIgnoreCase(navPro)) {
//			query.add(new TermQuery(new Term("state", navPro)), Occur.MUST);
			query.add(new TermQuery(new Term("state", "1")), Occur.MUST_NOT);
			query.add(new TermQuery(new Term("state", "2")), Occur.MUST_NOT);
			query.add(new TermQuery(new Term("state", "3")), Occur.MUST_NOT);
			query.add(new TermQuery(new Term("state", "4")), Occur.MUST_NOT);
			query.add(new TermQuery(new Term("state", "5")), Occur.MUST_NOT);
			query.add(new TermQuery(new Term("isEdit", "0")), Occur.MUST);
		}
		if (!StringUtil.isEmpty(theme)) {
			if (!theme.equals("全部")) {
				QueryParser queryParser = new QueryParser(Version.LUCENE_36,
						"theme", analyzer);
				query.add(
						queryParser.parse(theme.replaceAll("节", "").replace(
								"一", "")), Occur.MUST);
			}
		}
		if (!StringUtil.isEmpty(showcategory)) {
			if (!showcategory.equals("全部")) {
				if (showcategory.contains("屏")) {
					showcategory = showcategory.substring(0, 1);
				}
				QueryParser queryParser = new QueryParser(Version.LUCENE_36,
						"showcategory", analyzer);
				query.add(queryParser.parse(showcategory), Occur.MUST);
			}
		}
		//这里分辨率为或的关系
		if (!StringUtil.isEmpty(w) && !"0".equals(w)&&!"1".equals(w)) {
			if(Integer.parseInt(w)>Integer.parseInt(h)){
				query.add(new TermQuery(new Term("resolution", w+"x"+h)), Occur.MUST);
			}
			else {
				query.add(new TermQuery(new Term("resolution", h+"x"+w)), Occur.MUST);
			}
		}
//		if (!StringUtil.isEmpty(h) && !"0".equals(h)&& !"1".equals(h)) {
//			query.add(new TermQuery(new Term("h", h)), Occur.MUST);
//		}
		if("1".equals(w)&&"1".equals(h)){
			String[] resolutionSet=ConfigUtil.get("resolutionSet").split(";");
			for(String resolution:resolutionSet){
				query.add(new TermQuery(new Term("resolution", resolution)), Occur.MUST_NOT);
			}
		}
		filter = new QueryWrapperFilter(query);
		return filter;
	}
}
