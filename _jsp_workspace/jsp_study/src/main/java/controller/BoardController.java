package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileRemoveHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
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
	private String savePath; // 파일 저장 경로 변수

	// controller <-> service
	private BoardService bsv; // interface생성

	public BoardController() {
		// 생성자
		bsv = new BoardServiceImpl(); // class로 생성
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
		System.out.println("syso:" + uri);
		log.info("log:" + uri); // /brd/register
		String path = uri.substring(uri.lastIndexOf("/") + 1); // register
		log.info("실 요청 경로>>" + path);

		switch (path) {
		case "register":
			destpage = "/board/register.jsp";
			break;
		case "insert":
			try {
				// 파일을 업로드 할 물리적인 경로 설정
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info("저장위치 ->->->" + savePath);

				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); // 저장 할 위치를 file객체로 지정
				fileItemFactory.setSizeThreshold(1024 * 1024 * 3); // 파일 저장을 위한 임시 메모리 용량 설정 : byte단위

				// 미리 객체 설정
				BoardVO bvo = new BoardVO();

				// multipart/for-data형식으로 넘어온 request객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

				List<FileItem> itemList = fileUpload.parseRequest(request);
				for (FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						// 이미지 있는지 체크
						if (item.getSize() > 0) { // 데이터의 크기를 0보다 큰지 체크
							String fileName = item.getName() // 경로를 포함한 이름 ~~~/dog.jpg
									.substring(item.getName().lastIndexOf(File.separator) + 1); // dog.jpg
							// File.separator : 파일경로기호
							log.info("파일네임:" + fileName);
							// 시스템의 시간을 이용하여 파일을 구분 / 시간_dog.jpg
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir + File.separator + fileName);
							log.info("uploadFilePath 저장경로 ->->-> " + uploadFilePath.toString());

							try {
								item.write(uploadFilePath); // 자바 객체를 디스크에 쓰기
								bvo.setImageFile(fileName); // bvo에 저장할 값 설정

								// 썸네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75)
										.toFile(new File(fileDir + File.separator + "thum_" + fileName));

							} catch (Exception e) {
								log.info(">>> file writer on disk error");
								e.printStackTrace();
							}

						}
						break;
					}
				}

				isOk = bsv.register(bvo);
				log.info("bvo ->->-> " + bvo);
				log.info("board register >> {} ", isOk > 0 ? "성공" : "실패");

				// -- fileUpload 추가 전 --
//				//jsp에서 데이터를 입력후 => controller로 전송
//				//DB에 등록한 후 => index.jsp로 이동
//				
//				//jsp에서 가져온 title, writer, content를 꺼내오기
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content");
//				log.info(">>insert check1");
//				
//				BoardVO bvo = new BoardVO(title, writer, content);
//				log.info("insert bvo >>> {} "+bvo);
//				
//				//만들어진 bvo를 db에 저장
//				isOk = bsv.register(bvo);
//				log.info("board register >> {} ",isOk>0?"성공":"실패");
//				
				// 목적지 주소
				destpage = "/index.jsp";

			} catch (Exception e) {
				// TODO: handle exception
				log.info("insert error");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				// index에서 list 버튼을 클릭하면
				// 컨트롤러에서 db로 전체 리스트 요청
				// 전체 리스트를 가지고 list.gsp에 뿌리기
				log.info("list check 1");
				PagingVO pvo = new PagingVO(); // 1페이지/ 10개/ 0번지

				if (request.getParameter("pageNo") != null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					String type = request.getParameter("type");
					String keyword = request.getParameter("keyword");
					log.info(pageNo + "," + qty + "," + type + "," + keyword);
					pvo = new PagingVO(pageNo, qty, type, keyword);
				}
				// 검색어를 반영한 리스트
				List<BoardVO> list = bsv.getList(pvo);
				log.info("list >>>> {}" + list);

				// 검색한 값의 개수
				int totalCount = bsv.getCnt(pvo);
				log.info("totalCount >>>" + totalCount);
				PagingHandler ph = new PagingHandler(pvo, totalCount);

				// list를 jsp로 전송
				request.setAttribute("list", list);

				request.setAttribute("ph", ph);
				destpage = "/board/list.jsp";

			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				// jsp에서 보낸 bno을 받아서
				// 해당 번호의 전체 값을 조회하여 detail.jsp에 뿌리기
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("detail check 1");

				BoardVO bvo = bsv.getDetail(bno);
				log.info("detail bvo >>", bvo);
				request.setAttribute("bvo", bvo);
				destpage = "/board/detail.jsp";

			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				// 수정할 데이터의 bno를 받아서 수정 페이지로 보내서
				// modify.jsp를 띄우는 역할
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destpage = "/board/modify.jsp";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
		case "edit":
			try {

				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);

				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(1024 * 1024 * 3);

				BoardVO bvo = new BoardVO();

				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

				List<FileItem> itemList = fileUpload.parseRequest(request);
				String old_file = null;

				for (FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("utf-8")));
						break;
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						//이전 파일의 보관용
						old_file = item.getString("utf-8");
						break;
					case "new_file":
						//새로운 파일은 등록이 될수도 있고, 안될 수 도있음
						if(item.getSize()>0) {
							//새로운 등록파일이 있다면
							if(old_file != null) {
								//old_file 삭제 작업
								//파일 삭제 핸들러를 통해서 파일 삭제 작업진행
								FileRemoveHandler frh = new FileRemoveHandler();
								isOk = frh.deleteFile(old_file, savePath);
							}
							//새로운 파일에 대한 객체 작업
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info("파일이름:"+fileName);
							
							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							
							try {
								item.write(uploadFilePath);
								bvo.setImageFile(fileName);
								//썸네일 작업
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"thum_"+fileName));
								
							} catch (Exception e) {
								log.info("FIle update error");
										e.printStackTrace();
							}
						}else {
							//기존 파일은 있지만, 새로운 이미지파일이 없다면
							bvo.setImageFile(old_file);
						}
						break;

					}

				}
				
				isOk = bsv.modify(bvo);
				log.info(isOk>0?"성공":"실패");
				destpage = "list";

				// --파일 수정 전--

//				//파라미터로 받은 bno, title, content 데이터를 
//				// DB에 수정하여 넣고, list로 이동
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String title =  request.getParameter("title");
//				String content = request.getParameter("content");
//				
//				BoardVO bvo = new BoardVO(bno, title, content);
//				log.info("edit check 1");
//				log.info("edit >>> {}" + bvo);
//				
//				isOk = bsv.modify(bvo);
//				log.info(isOk>0?"성공":"실패");

//				destpage = "list"; // 내부 list case로 이동
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
				// TODO: handle exception
			}
			break;
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("remove check 1");
				isOk = bsv.remove(bno);
				log.info(isOk > 0 ? "성공" : "실패");
				destpage = "list";
			} catch (Exception e) {
				// TODO: handle exception
				log.info("remove error");
				e.printStackTrace();
			}
			break;
		}

		// 목적지 주소로 데이터를 전달(RequestDispatcher)

		rdp = request.getRequestDispatcher(destpage);
		rdp.forward(request, response); // 요청에 필요한 객체를 가지고 경로로전송

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get으로 오는 요청 처리
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post로 오는 요청 처리
		service(request, response);
	}

}
