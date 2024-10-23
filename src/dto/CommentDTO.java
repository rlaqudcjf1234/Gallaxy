package dto;

public class CommentDTO extends BoardDTO {

	private String commentId;

	private String commentContent;

	private String commentRegDt;

	private String commentLupDt;

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

}
