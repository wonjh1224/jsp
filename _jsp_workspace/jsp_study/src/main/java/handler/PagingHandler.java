package handler;

import domain.PagingVO;

public class PagingHandler {
	//list하단에 나오는 페이지네이션
	private int startPage; // 현재 화면에서 보여줄 시작 페이지네이션 번호 1 / 11 / 21 
	private int endPage; // 현재 화면에서 보여줄 마지막 페이지네이션 번호 10 / 20 / 30
	private int realEndpage; // 전체리스트의 끝 페이지 번호
	private boolean prev, next; // 이전, 다음페이지의 존재 여부
	
	//파라미터로 받기
	private int totalCount; // 전체 글 수
	private PagingVO pvo; // pageNo, qty => 현재 사용자가 클릭한 번호와 한 화면에 표시되는 개수
	
	public PagingHandler (PagingVO pvo, int totalCount) {
		this.pvo=pvo; // 1, 10
		//컨트롤러에서 DB조회 후 파라미터로 전송
		this.totalCount=totalCount;
		
		// 1~10 // 11~20 / 21~30
		// 페이지번호 1~10을 클릭해도 시작은1 끝은 10
		// 1 => 10 2=> 10 / 11 => 20 ...
		// 페이지번호 / 한 화면의 페이지네이션 수 * //
		// 
		this.endPage = (int)Math.ceil(pvo.getPageNo() / (double)pvo.getQty())*pvo.getQty();
		this.startPage = this.endPage - 9;
		
		//전체 게시글 수 / 한 화면에 게시되는 게시글 수
		// 101 / 10 => 10.1 => 올림 => 11페이지
		//나머지 게시글이 하나라도 있다면 1페이지가 더 생겨야 함
		this.realEndpage = (int)Math.ceil(totalCount / (double)pvo.getQty());
		
		// 찐 끝페이지 번호 설정
		if(this.realEndpage < this.endPage) {
			this.endPage = realEndpage;
		}
		
		//이전, 다음 유무
		//startPage 1, 11, 21
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEndpage;
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndpage() {
		return realEndpage;
	}

	public void setRealEndpage(int realEndpage) {
		this.realEndpage = realEndpage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPvo() {
		return pvo;
	}

	public void setPvo(PagingVO pvo) {
		this.pvo = pvo;
	}

	@Override
	public String toString() {
		return "PagingHandler [startPage=" + startPage + ", endPage=" + endPage + ", realEndpage=" + realEndpage
				+ ", prev=" + prev + ", next=" + next + ", totalCount=" + totalCount + ", pvo=" + pvo + "]";
	}
	
	
	
}
