package cn.com.liandisys.wujian.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloBootController {

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "Hello, SpringBoot!!";
	}

	@RequestMapping("/page")
	public String helloPage(@RequestParam(required=false) String name,Model model) {
		System.out.println(name);
		model.addAttribute("message", "Hello, " + name);
		return "resultPage";
	}

}

