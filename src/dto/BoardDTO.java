package dto;

import java.util.Date;

public class BoardDTO extends PageDTO {

	// �Խ��� ���̵�
	private String boardId;
	// �Խ��� ����
	private String boardTitle;
	// �Խ��� ����
	private String boardContent;
	// �Խ��� ��� ����
	private String boardWordDate;
	// �Խ��� ��� �ð�
	private String boardWordTime;
	// �Խ��� ���� ���
	private String boardFilePath;
	// �Խ��� �����ۼ� ����
	private Date boardRegDt;
	// �Խ��� �������� ����
	private Date boardLupDt;
	// �Խ��� �ۼ���
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
