package com.cai.po;

public class PageBean {


	//当前页面
	private int currentPage = 1;
	//每页显示的记录数
	private int pageSize = 5;
	//总记录数
	private int totalRecord; //set
	//总页面数
	private int totalPage;  // totalRecord / pageSize
	//索引 数据库从第几行数据开始索引
	private int startIndex; // (currentPage - 1) * 5
	
	//起始页
	private int start = 1; 
	//结束页
	private int end = 5;
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	public void caculateSomePer(){
		//totalPage
		if (this.totalRecord % pageSize == 0){
			totalPage = totalRecord / pageSize;
		}else{
			totalPage = this.totalRecord / pageSize + 1;
		}
		
		//startIndex
		this.startIndex = (this.currentPage - 1) * 5;
		
		//
		if (this.totalPage <= 5){
			this.end = this.totalPage;
		}else{
			this.start = this.currentPage - 2;
			this.end = this.currentPage + 2;
			
			if (this.start <= 0){
				this.start = 1;
				this.end = 5;
			}
			if (this.end > this.totalPage){
				this.start = this.totalPage - 5;
				this.end = this.totalPage;
			}
		}
	}
	
	
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage + ", startIndex=" + startIndex + ", start=" + start + ", end=" + end + "]";
	}
}
