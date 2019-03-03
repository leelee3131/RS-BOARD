package rs.board.domain;

//페이징 class
public class PageVO {
	
	private int maxPost; //페이지당 표시될 게시물 최대갯수,현재 페이지 게시물 갯수
	private int firstPageno; //첫번째 페이지 번호
	private int prevPageno; // 이전 페이지 번호
	private int startPageno; //시작 페이지
	private int curPageno; //현재 페이지번호
	private int endPageno; //끝페이지 번호
	private int nextPageno; //다음 페이지번호
	private int finalPageno; //마지막 페이지 번호
	private int countAll; // 전체 게시물 수
	private int pageSize; //보여지는 페이지 수
	
	//페이지 object 생성
	public void makePage() {
		if(countAll==0)
			return;
		if(curPageno==0)
			setCurPageno(1);
		if(maxPost==0)
			setMaxPost(10);
		int finalPage=(countAll+(maxPost-1))/maxPost;
		if(curPageno>finalPage)
			setCurPageno(finalPage);
		if(curPageno<0)
			curPageno=1;
		boolean isNowfirst=curPageno==1?true:false;
		boolean isNowfinal=curPageno==finalPage?true:false;
		
		int startPage=((curPageno-1)/countAll)*pageSize+1;
		int endPage=startPage+pageSize-1;
		
		if(endPage>finalPage)
			endPage=finalPage;
		setFirstPageno(1);
		
		if(!isNowfirst)
			setPrevPageno(((startPage-1)<1?1:(startPage-1)));
		setStartPageno(startPage);
		setEndPageno(endPage);
		
		if(!isNowfinal)
			setNextPageno(((endPage+1>finalPage?finalPage:(endPage+1))));
		setFinalPageno(finalPage);
		
	}
	public PageVO(int curPageno,int maxPost) {
		this.curPageno=curPageno;
		this.pageSize=10;//페이지당 10개씩
		this.maxPost=(maxPost!=0)?maxPost:10;
	}
	//getter,setter
	public int getMaxPost() {
		return maxPost;
	}

	public void setMaxPost(int maxPost) {
		this.maxPost = maxPost;
	}

	public int getFirstPageno() {
		return firstPageno;
	}

	public void setFirstPageno(int firstPageno) {
		this.firstPageno = firstPageno;
	}

	public int getPrevPageno() {
		return prevPageno;
	}

	public void setPrevPageno(int prevPageno) {
		this.prevPageno = prevPageno;
	}

	public int getStartPageno() {
		return startPageno;
	}

	public void setStartPageno(int startPageno) {
		this.startPageno = startPageno;
	}

	public int getCurPageno() {
		return curPageno;
	}

	public void setCurPageno(int curPageno) {
		this.curPageno = curPageno;
	}

	public int getEndPageno() {
		return endPageno;
	}

	public void setEndPageno(int endPageno) {
		this.endPageno = endPageno;
	}

	public int getNextPageno() {
		return nextPageno;
	}

	public void setNextPageno(int nextPageno) {
		this.nextPageno = nextPageno;
	}

	public int getFinalPageno() {
		return finalPageno;
	}

	public void setFinalPageno(int finalPageno) {
		this.finalPageno = finalPageno;
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}