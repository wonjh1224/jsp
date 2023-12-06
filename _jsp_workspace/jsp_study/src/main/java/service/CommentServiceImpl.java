package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.log.LogFactory;

import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {

	private static Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}

	@Override
	public int post(CommentVO cvo) {
		log.info("post check service");
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> list(int bno) {
		log.info("list check service");
		return cdao.list(bno);
	}

	@Override
	public int remove(int cno) {
		log.info("remove check service");
		return cdao.delete(cno);
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("modify check service");
		return cdao.update(cvo);
	}
}
