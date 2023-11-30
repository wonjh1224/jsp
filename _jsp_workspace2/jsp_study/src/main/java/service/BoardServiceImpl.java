package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class); 
	private BoardDAO bdao; //interface로 생성  (repository)
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); //클래스로 생성 (repository)
	}
	
	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("list check 2");
		return bdao.list();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("detail check2");
		int isOk = bdao.readCountUpate(bno);
		return bdao.selectOne(bno);
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info("edit check2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove check2");
		return bdao.delete(bno);
	}

	@Override
	public List<BoardVO> myBoard(String id) {
		log.info("myBoard check 2");
		return bdao.myBoard(id);
	}

}
