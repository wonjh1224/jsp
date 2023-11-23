package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
