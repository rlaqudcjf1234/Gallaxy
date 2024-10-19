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
	private String boardWordDate;
	// 게시판 약속 시간
	private String boardWordTime;
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

	public String getBoardWordDate() {
		return boardWordDate;
	}

	public void setBoardWordDate(String boardWordDate) {
		this.boardWordDate = boardWordDate;
	}

	public String getBoardWordTime() {
		return boardWordTime;
	}

	public void setBoardWordTime(String boardWordTime) {
		this.boardWordTime = boardWordTime;
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
