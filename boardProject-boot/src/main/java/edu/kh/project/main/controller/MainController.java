package edu.kh.project.main.controller;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.service.MemberService;

@Controller
@RequestMapping("member")
public class MainController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping("login")
	public String login(Member inputMember, 
						RedirectAttributes ra
						) {
		 return "";
	}
}
