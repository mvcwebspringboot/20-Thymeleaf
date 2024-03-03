package com.study.boot.ctrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.study.boot.model.DataDTO;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	//예제10
	@GetMapping("/10-basic")
	public String basic(Model model) {
		
		//문제 - 문자열을 뷰로 전송하여 타임리프로 출력하시오.
		model.addAttribute("msg", "Hello, World~!");
		
		//문제 - 현재날짜시간을 뷰로 전송하여 타임리프로 출력하시오.
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E요일 HH:mm:ss");
		String now = sdf.format(date);
		
		model.addAttribute("now", now);
		
		model.addAttribute("htmlcode", "<h3>아기공룡둘리</h3>");//th:text, th:utext 비교
		
		model.addAttribute("message", "서버로부터의 메세지");
		return "10-basic";
	}
	

	//예제20
	@GetMapping("/20-object")
	public ModelAndView object() {
		
		ModelAndView mv = new ModelAndView();		
		
		Map<String, String> me = new HashMap<String, String>(); 
		me.put("name", "홍길동");
		me.put("age", "20");
		me.put("address", "서울시 강서구");		
		mv.addObject("me",me);
		
		DataDTO dto = new DataDTO();
		dto.setName("하니");
		dto.setAge(15);
		dto.setAddress("서울시 쌍문동");
		mv.addObject("dto",dto);
		
		//자바스크립트에서 출력 테스트
		mv.addObject("message", "서버로부터의 메세지");
		
		mv.setViewName("20-object");
		return mv;
	}
	
	
	
	//예제30
	@GetMapping("/30-control")
	public ModelAndView control() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("data", "이 문자열이 보입니다.");
		
		
		//th:each 로 출력
		List<String> friends = Arrays.asList("둘리","또치","도우너","마이콜");
		mv.addObject("friends",friends);
		
		
		//<th:block> 태그로 출력
		List<DataDTO> list = new ArrayList<DataDTO>();
		for(int i=0;i<5;i++) {
			DataDTO dto = new DataDTO();
			dto.setName("이름"+i);
			dto.setAge(25+i);
			dto.setAddress("주소"+i);
			list.add(dto);
		}
		
		mv.addObject("list",list);		
		mv.setViewName("30-control");		
		return mv;
	}
	
	//예제40
	@GetMapping("/40-form")
	public String form() {
		return "40-form";
	}
	
	@GetMapping("/a_send")
	public ModelAndView a_send(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
		ModelAndView mv = new ModelAndView();
		
        //String으로 받을 경우 @RequestParam 필요 없음
        //대신 형변환 필요.
		mv.addObject("result",num1+num2);
		mv.setViewName("result");
		
		return mv;
	}
	
	@GetMapping("/noneDtoSend")
	public String noneDtoSend(@RequestParam("u_name") String username, @RequestParam("u_age") int age, @RequestParam("u_address") String address, Model model) {
		DataDTO dto = new DataDTO();
		dto.setName(username);
		dto.setAge(age);
		dto.setAddress(address);
		
		String result = dto.toString();
		log.info("result = "+result);
		
		model.addAttribute("result", result);
		
		return "result";
	}
	 
	@PostMapping("/dtoSend")
	public String dtoSend(@Valid DataDTO dto, BindingResult bindingResult, Model model) {
		log.info("noneDtoSend~!!!!!");
		String result = dto.toString();
		System.out.println("reslut = "+result);
		
		if(bindingResult.hasErrors()) {
			if(bindingResult.getFieldError("age")!=null) {
				System.out.println(bindingResult.getFieldError("age").getDefaultMessage());
			}
			return "sendData";
		}
		
		model.addAttribute("result", result);
		
		return "result";
	}
	
	
	//예제50 - layout
	@GetMapping("/50-page")
	public void page(Model model) {
		model.addAttribute("name", "둘리");
		model.addAttribute("age", 7);
		model.addAttribute("address", "서울시 도봉구 쌍문동");
	}
	
}


























