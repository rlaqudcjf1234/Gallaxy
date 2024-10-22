package dto;

import java.util.Date;

public class BoardDTO extends PageDTO {

	// 게시판 아이디
	private String boardId;

	// 게시판 제목
	private String boardTitle;

	// 게시판 내용
	private String boardContent;

	// 게시판 약속 일자
	private String boardWordYyyy;
	// 게시판 약속 일자
	private String boardWordMm;
	// 게시판 약속 일자
	private String boardWordDd;

	// 게시판 약속 시간
	private String boardWordApm;
	// 게시판 약속 시간
	private String boardWordHh;
	// 게시판 약속 시간
	private String boardWordMi;

	// 게시판 파일 경로
	private String boardFilePath;

	// 게시판 최초작성 일자
	private Date boardRegDt;

	// 게시판 최종수정 일자
	private Date boardLupDt;

	// 게시판 작성자
	private String userId;

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWordYyyy() {
		return boardWordYyyy;
	}

	public void setBoardWordYyyy(String boardWordYyyy) {
		this.boardWordYyyy = boardWordYyyy;
	}

	public String getBoardWordMm() {
		return boardWordMm;
	}

	public void setBoardWordMm(String boardWordMm) {
		this.boardWordMm = boardWordMm;
	}

	public String getBoardWordDd() {
		return boardWordDd;
	}

	public void setBoardWordDd(String boardWordDd) {
		this.boardWordDd = boardWordDd;
	}

	public String getBoardWordApm() {
		return boardWordApm;
	}

	public void setBoardWordApm(String boardWordApm) {
		this.boardWordApm = boardWordApm;
	}

	public String getBoardWordHh() {
		return boardWordHh;
	}

	public void setBoardWordHh(String boardWordHh) {
		this.boardWordHh = boardWordHh;
	}

	public String getBoardWordMi() {
		return boardWordMi;
	}

	public void setBoardWordMi(String boardWordMi) {
		this.boardWordMi = boardWordMi;
	}

	public String getBoardFilePath() {
		return boardFilePath;
	}

	public void setBoardFilePath(String boardFilePath) {
		this.boardFilePath = boardFilePath;
	}

	public Date getBoardRegDt() {
		return boardRegDt;
	}

	public void setBoardRegDt(Date boardRegDt) {
		this.boardRegDt = boardRegDt;
	}

	public Date getBoardLupDt() {
		return boardLupDt;
	}

	public void setBoardLupDt(Date boardLupDt) {
		this.boardLupDt = boardLupDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
