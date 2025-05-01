package edu.kh.project.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Comment;

@Mapper
public interface CommentMapper {

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	public List<Comment> select(int boardNo);

	/** 댓글/ 답글 작성
	 * @param comment
	 * @return
	 */
	public int insert(Comment comment);

	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	public int delete(int commentNo);

	/** 댓글 수정
	 * @param comment
	 * @return
	 */
	public int update(Comment comment);
}
