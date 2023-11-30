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
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	// jsp에서 받는 요청을 처리, 처리결과를 다른 jsp로 보내는 역할을 하는 객체
	private RequestDispatcher rdp;
	private String destpage; // 목적지 주소를 저장하는 변수
	private int isOk; // DB구문 체크 값 저장변수

	// controller <-> service
	private BoardService bsv; // interface생성

	public BoardController() {
		bsv = new BoardServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실제 처리 메서드
		log.info("필요한 로그");
		// 매개변수의 객체의 인코딩 설정
		request.setCharacterEncoding("utf-8"); // 요청
		response.setCharacterEncoding("utf-8"); // 응답
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getRequestURI(); // jsp에서 오는 요청주소를 받는 설정
		log.info("log:" + uri); // /brd/register
		String path = uri.substring(uri.lastIndexOf("/") + 1); // register
		log.info("실 요청 경로>>" + path);
		
		switch(path) {
		case "register":
			destpage = "/board/register.jsp";
			break;
		case "insert":
			try {
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");				
				log.info("insert check 1");
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info(""+bvo);
				
				isOk = bsv.insert(bvo);
				log.info(isOk>0?"성공":"실패");
				
				destpage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("insert error");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				log.info("list check 1");
				List<BoardVO> list = bsv.getList();
				
				log.info(""+list);
				request.setAttribute("list", list);	
				
				destpage="/board/list.jsp";
				
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("detail check1");
				
				BoardVO bvo = bsv.getDetail(bno);
				log.info(""+bvo);
				
				request.setAttribute("bvo", bvo);
				destpage = "/board/detail.jsp";
				
				
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destpage="/board/modify.jsp";
				
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
			
		case "edit":
			try {
				log.info("edit check 1");
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				BoardVO bvo = new BoardVO(bno, title, content);
				isOk = bsv.edit(bvo);
				log.info(isOk>0?"성공":"실패");
				
				destpage = "list";
				
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("remove check1");
				isOk = bsv.remove(bno);
				log.info(isOk>0?"성공":"실패");
				destpage = "list";
				
				
			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			break;
		case "myBoard":
			try {
				
				HttpSession ses = request.getSession();
				
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				
				List<BoardVO> bvo = bsv.myBoard(mvo.getId());
				request.setAttribute("list", bvo);
				
				destpage="/member/mylist.jsp";
				
			} catch (Exception e) {
				log.info("myBoard Error");
				e.printStackTrace();
			}
			break;
			
		}
		
		//목적지 주소로 데이터를 전달 (RequestDispatcher)
		rdp = request.getRequestDispatcher(destpage);
		rdp.forward(request, response);
		
		
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
