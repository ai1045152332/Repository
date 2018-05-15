package com.honghe.recordweb.service.frontend.tresource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataGrid {
		private Long total=0L;
		private List rows=new ArrayList();
		public Long getTotal() {
			return total;
		}
		public void setTotal(Long total) {
			this.total = total;
		}
		public List getRows() {
			return rows;
		}
		public void setRows(List rows) {
			this.rows = rows;
		}
		public void getReverse(){
			Collections.reverse(rows);	
		}
}
