package dto;

public class CommentDTO {

	private String boardId;

	private String commentId;

	private String commentContent;

	private String commentRegDt;

	private String commentLupDt;

	private String userId;

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentRegDt() {
		return commentRegDt;
	}

	public void setCommentRegDt(String commentRegDt) {
		this.commentRegDt = commentRegDt;
	}

	public String getCommentLupDt() {
		return commentLupDt;
	}

	public void setCommentLupDt(String commentLupDt) {
		this.commentLupDt = commentLupDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}