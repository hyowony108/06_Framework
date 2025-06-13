package edu.kh.project.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.admin.model.service.AdminService;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins="http://localhost:5173"/*, allowCredentials = "true"*/)
// allowCredentials = "true" 클라이언트로부터 들어오는 쿠키 허용
@RequestMapping("admin")
@Slf4j
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor // 필드에 final
public class AdminController {

	private final AdminService service;
	
	@PostMapping("login")
	public Member login(@RequestBody Member inputMember, Model model) {
		
		Member loginMember = service.login(inputMember);
		
		if(loginMember == null) {
			return null;
		}
		
		model.addAttribute(loginMember);
		return loginMember;
	}
	
	/** 관리자 로그아웃
	 * @return
	 */
	@GetMapping("logout")
	public ResponseEntity<String> logout(HttpSession session){
		
		// ResponseEntity
		// Spring에서 제공하는 Http 응답 데이터를
		// 커스터마이징 할 수 있도록 지원하는 클래스
		// -> HTTP 상태코드, 헤더, 응답 본문(body)
		
		try {
			
			session.invalidate(); // 세션 무효화 처리
			return ResponseEntity.status(HttpStatus.OK) // 200
					.body("로그아웃이 완료되었습니다.");
			
		} catch (Exception e) {

			// 세션 무효화 중 예외 발생한 경우
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
					.body("로그아웃 중 예외 발생 : " + e.getMessage());
		}
	}
	
// ----------------------- 통계 ------------------------------------
	
	
	@GetMapping("MaxSignCount")
	public ResponseEntity<Object> MaxSignCount(){
		try {
			List<Member> newSignUpData = service.MaxSignCount();
			return ResponseEntity.status(HttpStatus.OK).body(newSignUpData);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}
	
	@GetMapping("maxReadCount")
	public ResponseEntity<Object> maxReadCount(){
		try {
			Board board = service.maxReadCount();
			return ResponseEntity.status(HttpStatus.OK).body(board);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}
	@GetMapping("maxLikeCount")
	public ResponseEntity<Object> maxLikeCount(){
		try {
			Board board = service.maxLikeCount();
			return ResponseEntity.status(HttpStatus.OK).body(board);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	@GetMapping("maxCommentCount")
	public ResponseEntity<Object> maxCommentCount(){
		try {
			Board board = service.maxCommentCount();
			return ResponseEntity.status(HttpStatus.OK).body(board);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	
	//----------------------------복구------------------------------
	
	/** 탈퇴 회원 리스트 조회
	 * @return
	 */
	@GetMapping("withdrawnMembers")
	public ResponseEntity<Object> selectWithdrawnMemberList(){
		// 성공 시 List<member> 반환, 실패 시 String 반환, 실패 시 String 반환 -> Object

		try {
			
			List<Member> withdrawnMemberList = service.selectWithdrawnMemberList();
			System.out.println(withdrawnMemberList);
			return ResponseEntity.status(HttpStatus.OK).body(withdrawnMemberList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("탈퇴한 회원 목록 조회 중 문제 발생 : " + e.getMessage());
		}
	}
	
	/** 삭제된 게시글 리스트 조회
	 * @return
	 */
	@GetMapping("deleteBoards")
	public ResponseEntity<Object> selectdeleteBoardsList(){
		// 성공 시 List<member> 반환, 실패 시 String 반환, 실패 시 String 반환 -> Object

		try {
			
			List<Board> deleteBoardsList = service.selectdeleteBoardsList();
			return ResponseEntity.status(HttpStatus.OK).body(deleteBoardsList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("삭제한 게시글 목록 조회 중 문제 발생 : " + e.getMessage());
		}
	}
	
	@PutMapping("restoreMember")
	public ResponseEntity<String> restoreMember(@RequestBody Member member){
		try {
			int result = service.restoreMember(member.getMemberNo());
			
			if( result > 0) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(member.getMemberNo() + "번 회원 복구 완료");
				
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("유효하지 않은 memberNo : " + member.getMemberNo());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("탈퇴한 회원 복구 중 문제 발생 : " + e.getMessage());
		}
	}
	
	@PutMapping("restoreBoard")
	public ResponseEntity<String> restoreBoard(@RequestBody Board board){
		try {
			int result = service.restoreBoard(board.getBoardNo());
			
			if( result > 0) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(board.getBoardNo() + "번 게시글 복구 완료");
				
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("유효하지 않은 boardNo : " + board.getBoardNo());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("삭제된 게시글 복구 중 문제 발생 : " + e.getMessage());
		}
	}
}
