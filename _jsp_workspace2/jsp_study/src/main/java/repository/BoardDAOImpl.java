package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class); 
	
	//DB연결
	private SqlSession sql;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 3");
		int isOk = sql.insert("BoardMapper.add", bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> list() {
		log.info("list check 3");
		return sql.selectList("BoardMapper.list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("detail check 3");
		return sql.selectOne("BoardMapper.one", bno);
	}

	@Override
	public int readCountUpate(int bno) {
		log.info("dao readCountUpdate check");
		int isOk = sql.update("BoardMapper.read", bno);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("edit check 3");
		int isOk = sql.update("BoardMapper.up", bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("remove check 3");
		int isOk = sql.update("BoardMapper.del",bno);
		if(isOk>0)sql.commit();
		return isOk;
	}

}
