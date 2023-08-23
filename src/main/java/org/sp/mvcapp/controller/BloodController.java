package org.sp.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sp.mvcapp.model.blood.BloodManager;

//MVC에서 Model과 View를 분리시키기 위해서는 컨트롤러의 관여가 필요하다
//MVC개발방법론을 javaEE기술로 구현하였을때의 개발방법을 가리켜 Model2라 부르고
//Model-java(순수자바코드), View-jsp, Controller-servlet
public class BloodController extends HttpServlet{
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blood=request.getParameter("blood");
		
		//로직
		BloodManager manager=new BloodManager();
		String msg=manager.getAdvice(blood);
		
		//msg 변수가 곧(응답시) 소멸되므로 어딘가에 저장해놓지 않으면
		//아래의 코드에 의한 재접속시 msg를 사용할수없게된다
		//session을 이용해보자
		
		//서버에 의해 생성된 세션 가져오기
		HttpSession session=request.getSession();
		
		//세션은 map의  자식이므로 , key-value의 쌍으로 데이터를 관리
		session.setAttribute("msg", msg);
		
		//결과보여주기
		response.sendRedirect("/blood/result.jsp");
	}
}
