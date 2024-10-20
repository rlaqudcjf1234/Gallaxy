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
	private String boardWordYyyy;
	// �Խ��� ��� ����
	private String boardWordMm;
	// �Խ��� ��� ����
	private String boardWordDd;

	// �Խ��� ��� �ð�
	private String boardWordApm;
	// �Խ��� ��� �ð�
	private String boardWordHh;
	// �Խ��� ��� �ð�
	private String boardWordMi;

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
