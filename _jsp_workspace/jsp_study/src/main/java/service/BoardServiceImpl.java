package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class); 
	private BoardDAO bdao; //interface로 생성  (repository)
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); //클래스로 생성 (repository)
	}
	
}
