package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    //로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class); 
	
	//jsp에서 받는 요청을 처리, 처리결과를 다른 jsp로 보내는 역할을 하는 객체
	private RequestDispatcher rdp;
	private String destpage; //목적지 주소를 저장하는 변수
	private int isOk; // DB구문 체크 값 저장변수
	
	//controller <-> service 
	private BoardService bsv; // interface생성
	
    public BoardController() {
        // 생성자
    	bsv =  new BoardServiceImpl(); //class로 생성
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 처리 메서드
		log.info("필요한 로그");	
		// 매개변수의 객체의 인코딩 설정
		request.setCharacterEncoding("utf-8");  //요청
		response.setCharacterEncoding("utf-8"); //응답
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI(); //jsp에서 오는 요청주소를 받는 설정
		System.out.println("syso:"+uri);
		log.info("log:"+uri); // /brd/register
		String path = uri.substring(uri.lastIndexOf("/")+1); //register
		log.info("실 요청 경로>>"+path);
		
		switch(path) {
		case "register":
			destpage = "/board/register.jsp";
			break;
		case "insert":
			try {
				//jsp에서 데이터를 입력후 => controller로 전송
				//DB에 등록한 후 => index.jsp로 이동
				
				//jsp에서 가져온 title, writer, content를 꺼내오기
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info(">>insert check1");
				
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("insert bvo >>> {} "+bvo);
				
				//만들어진 bvo를 db에 저장
				isOk = bsv.register(bvo);
				log.info("board register >> {} ",isOk>0?"성공":"실패");
				
				//목적지 주소
				destpage = "/index.jsp";
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info("insert error");
				e.printStackTrace();
			}
			break;
		case "list" :
			try {
				//index에서 list 버튼을 클릭하면 
				//컨트롤러에서 db로 전체 리스트 요청
				//전체 리스트를 가지고 list.gsp에 뿌리기
				log.info("list check 1");
				List<BoardVO>list = bsv.getList();
				
				log.info("list >>>> {}" + list);
				//list를 jsp로 전송
				request.setAttribute("list", list);
				destpage="/board/list.jsp";
				
				
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				//jsp에서 보낸 bno을 받아서 
				//해당 번호의 전체 값을 조회하여 detail.jsp에 뿌리기
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("detail check 1");
				
				BoardVO bvo = bsv.getDetail(bno);
				log.info("detail bvo >>", bvo);
				request.setAttribute("bvo", bvo);
				destpage="/board/detail.jsp";
				
				
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				//수정할 데이터의 bno를 받아서 수정 페이지로 보내서
				//modify.jsp를 띄우는 역할
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
				//파라미터로 받은 bno, title, content 데이터를 
				// DB에 수정하여 넣고, list로 이동
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title =  request.getParameter("title");
				String content = request.getParameter("content");
				
				BoardVO bvo = new BoardVO(bno, title, content);
				log.info("edit check 1");
				log.info("edit >>> {}" + bvo);
				
				isOk = bsv.modify(bvo);
				log.info(isOk>0?"성공":"실패");
				
				destpage = "list"; //내부 list case로 이동
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
				// TODO: handle exception
			}
			break;
		case "remove" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("remove check 1");
				isOk = bsv.remove(bno);
				log.info(isOk>0? "성공":"실패");
				destpage = "list";
			} catch (Exception e) {
				// TODO: handle exception
				log.info("remove error");
				e.printStackTrace();
			}
			break;
		}

		
		//목적지 주소로 데이터를 전달(RequestDispatcher)
		
		rdp = request.getRequestDispatcher(destpage);
		rdp.forward(request, response); // 요청에 필요한 객체를 가지고 경로로전송
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get으로 오는 요청 처리
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post로 오는 요청 처리
		service(request, response);
	}

}
