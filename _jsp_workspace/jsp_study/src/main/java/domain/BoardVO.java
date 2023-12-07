package domain;

public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private String moddate;
	private int readCount;
	private String imageFile;
	
	public BoardVO() {}
	
	//insert : title, writer, content
	public BoardVO(String title, String writer, String content) {
		this.title=title;
		this.writer=writer;
		this.content=content;
	}	
	//list : bno, title, writer, regdate, readCount
	public BoardVO(int bno, String title, String writer, String regdate, int readCount) {
		this.bno=bno;
		this.title=title;
		this.writer=writer;
		this.regdate=regdate;
		this.readCount=readCount;
	}
	//update : bno, title, content
	public BoardVO(int bno, String title, String content) {
		this.bno=bno;
		this.title=title;
		this.content=content;
	}
	//detail : All
	public BoardVO(int bno, String title, String writer, String content, String regdate, String moddate, int readCount, String imageFile) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
		this.readCount = readCount;
		this.imageFile = imageFile;
	}
	//getter/setter

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModdate() {
		return moddate;
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", moddate=" + moddate + ", readCount=" + readCount + ", imageFile=" + imageFile + "]";
	}

	
	
}
