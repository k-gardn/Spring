package com.care.dbQuiz.board;
/*
	CREATE TABLE session_board(
	no VARCHAR2(40), //게시글 번호
	title VARCHAR2(80), //게시글 제목
	content VARCHAR2(200), //게시글 내용
	id VARCHAR2(80), // 작성자
	write_date VARCHAR2(100), // 작성일
	hits VARCHAR2(80), // 조회수
	file_name VARCHAR2(100), // 파일 이름
	PRIMARY KEY(no)
	);

	COMMIT;
 */
public class BoardDTO {
	private String no;
	private String title;
	private String content;
	private String id;
	private String writeDate;
	private String hits;
	private String fileName;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
