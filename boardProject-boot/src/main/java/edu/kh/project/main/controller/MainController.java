package edu.kh.project.main.controller;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.model.Model;
import edu.kh.project.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("member")
@Slf4j
public class MainController {
	
	@Autowired
	private MemberService service;
	
	/* [로그인] 
	 * - 특정 사이터에 아이디/비밀번호 등을 입력해서
	 * 	 해당 정보가 DB에 있으면 조회/서비스 이용
	 * 
	 * - 로그인 한 회원 정보는 session에 기록하여
	 * 로그아웃 또는 브라우저 종료 시 까지
	 * 해당 정보를 계속 이용할 수 있게 함
	 * 
	 * */
	
	/** 로그인 
	 * @param inputMember : 커맨트 객체 (@ModelAttribute 생략)
	 * 						memberEmail, memberPw 세팅된 상태
	 * @param ra : 리다이렉트 시 request scope -> session scope-> request 로 데이터 전달
	 * @param model : 데이터 전달용 객체(기본 request scope)
	 * 				/ (@SessionAttributes 어노테이션과 함께 사용시 session scope 이동)
	 * 
	 * @return
	 */
	@PostMapping("login")
	public String login(Member inputMember, 
						RedirectAttributes ra,
						Model model,
						RequestParam(value="saveId", required = false) String saveId,
						HttpServletResponse resp
						) {
		
		// 체크박스
		// - 체크가   된 경우 : "on"
		// - 체크가 안된 경우 : null
				
				
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		
		
		 return "";
	}

}