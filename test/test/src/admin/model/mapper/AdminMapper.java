package edu.kh.project.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

@Mapper
public interface AdminMapper {

	/** 관리자 로그인
	 * @param memberEmail
	 * @return
	 */
	Member login(String memberEmail);

	Board maxReadCount();

	Board maxLikeCount();

	Board maxCommentCount();

	// 신규 가입 회원 7일
	List<Member> MaxSignCount();

	List<Member> selectWithdrawnMemberList();

	List<Board> selectdeleteBoardsList();

	int restoreMember(int memberNo);

	int restoreBoard(int boardNo);

}
