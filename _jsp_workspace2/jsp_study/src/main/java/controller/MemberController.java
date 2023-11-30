package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	//rdp
	private RequestDispatcher rdp;
	//destpage
	private String destpage;
	//isOk
	private int isOk;
	//service
	private MemberService msv; //interface로 생성
	
	public MemberController() {
		msv = new MemberServiceImpl();
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//모든 서비스 처리 경로
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		log.info(">>> path >>> "+ path);
		
		switch (path){
			case "join":
				destpage="/member/join.jsp";
				break;
			case "register":
				try {
					
					String id = request.getParameter("id");
					String pwd = request.getParameter("pwd");
					String email = request.getParameter("email");
					int age = Integer.parseInt(request.getParameter("age"));
					
					MemberVO mvo = new MemberVO(id, pwd, email, age);
					log.info("register check 1");
					
					isOk = msv.register(mvo);
					
					destpage="/index.jsp";
					request.setAttribute("msg_reg", -1);
				} catch (Exception e) {
					log.info("register error");
					e.printStackTrace();
				}
				break;
			case "login" :
				try {
					String id = request.getParameter("id");
					String pwd = request.getParameter("pwd");
					MemberVO mvo = new MemberVO(id, pwd);
					log.info("login check1");
					
					MemberVO logMvo = msv.login(mvo);
					log.info("logMvo>>"+logMvo);
					if(logMvo!=null) {
						HttpSession ses = request.getSession();
						ses.setAttribute("ses", logMvo);
						ses.setMaxInactiveInterval(3600);
					}else {
						request.setAttribute("msg_log", -1);
					}
					destpage="/index.jsp";
					
					
				} catch (Exception e) {
					log.info("login error");
					e.printStackTrace();	
				}
				break;
			case "detail":
				log.info("detail check1");
				destpage="/member/detail.jsp";
				
			case "modify":
				try {
					String pwd = request.getParameter("pwd");
					String eamil = request.getParameter("email");
					int age = Integer.parseInt(request.getParameter("age"));
					log.info("modify check 1");
					MemberVO mvo = new MemberVO(pwd, eamil, age);
					log.info("modify mvo > "+mvo);
					
					isOk = msv.modify(mvo);
					
					destpage="/index.jsp";
					
				} catch (Exception e) {
					log.info("modify error");
					e.printStackTrace();
				}
				break;
			case "remove":
				try {
					HttpSession ses = request.getSession();
					MemberVO mvo = (MemberVO)ses.getAttribute("ses");
					log.info("remove check 1");
					
					isOk = msv.remove(mvo.getId());
				
					ses.invalidate();
					destpage="/index.jsp";
				} catch (Exception e) {
					log.info("remove error");
					e.printStackTrace();
				}
				break;
			case "list":
				try {
					log.info("list check 1");
					List<MemberVO>list=msv.getList();
					
					request.setAttribute("list", list);
					
					destpage="/member/list.jsp";
					
				} catch (Exception e) {
					log.info("list error");
					e.printStackTrace();
				}
				break;
			case "logout":
				HttpSession ses = request.getSession();
				ses.invalidate();
				request.setAttribute("msg_logout", -1);
				destpage="/index.jsp";
				break;

		}
		
		//목적지 주소로 데이터를 전달 (RequestDispatcher)
		rdp = request.getRequestDispatcher(destpage);
		rdp.forward(request, response);
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
