package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.CommentDTO;

public class CommentDAO {

	public int insertComment(SqlSession session, CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.insert("comment.insertComment", dto);
	}

	public List<CommentDTO> selectCommentList(SqlSession session, CommentDTO dto) {
		// TODO Auto-generated method stub
		return session.selectList("comment.selectCommentList", dto);
	}

}
