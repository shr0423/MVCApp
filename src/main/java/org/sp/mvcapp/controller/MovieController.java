package org.sp.mvcapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sp.mvcapp.model.movie.MovieManager;

//영화에 대한 조언요청을 받는 컨트롤러
public class MovieController extends HttpServlet{

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//파라미터 받기
		String movie=request.getParameter("movie");
		
		//컨트롤러에서는 가능은 해도 로직을 작성하지 말자
		//이유는? Model(로직)을 분리시켜놓아야 다른 프로젝트등에서 재사용가능하다
		//그리고 여기서 만일 로직을 작성해버리면 MVC중 Controller+Model이 되버림
		MovieManager manager=new MovieManager();
		String msg=manager.getAdvice(movie);
		
		//결과페이지와 컨트롤러가 분리되어 잇으므로 msg와 같은 결과가 담겨진 지역변수가
		//유지되려면 어딘가에 저장해놓지않으면 안된다
		//현재로서는 session에 저장
		//HttpSession session=request.getSession();
		//session.setAttribute("msg", msg);
		
		//만일 요청을 끊지 않고 결과페이지인 result.jsp로 포워딩하는 방법만 있다면
		//우리는 session까지 사용할필요조차 없다
		
		request.setAttribute("msg", msg);
		
		//서버의 view중 어떤 view로 포워딩 할지를 결정하는 객체
		RequestDispatcher dis=request.getRequestDispatcher("/movie/result.jsp");
		dis.forward(request, response);
		
		//PrintWriter out=response.getWriter();
		//서블릿이 디자인결과를 표현할수는 있찌만 MVC로 분리시키지 않으면
		//디자인 코드는 디자이너 퍼블리셔와 협업의 대상이므로 java코드에 두어서는 안됨
		//디자인 즉 View를 담당하는 기술로 표현해야한다.
		//<script>location.href="/movie/result.jsp";</script>
		//response.sendRedirect("/movie/result.jsp");
		
	}
}
