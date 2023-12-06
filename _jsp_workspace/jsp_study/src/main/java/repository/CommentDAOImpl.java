package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	
	private static Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(CommentVO cvo) {
		log.info("post check dao");
		isOk = sql.insert("CommentMapper.add",cvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> list(int bno) {
		log.info("list check dao");
		return sql.selectList("CommentMapper.list", bno);
	}

	@Override
	public int delete(int cno) {
		log.info("remove check dao");
		isOk = sql.delete("CommentMapper.del",cno);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int update(CommentVO cvo) {
		log.info("modify check dao");
		isOk = sql.update("CommentMapper.up",cvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

}
