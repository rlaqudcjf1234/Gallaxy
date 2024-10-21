package service;

import java.util.List;

import dto.CommentDTO;

public interface CommentService {

	public int insertComment(CommentDTO dto);

	public List<CommentDTO> selectCommentList(CommentDTO dto);

}
