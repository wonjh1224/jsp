package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("join check3");
		int isOk = sql.insert("MemberMapper.reg", mvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check3");
		return sql.selectOne("MemberMapper.login", mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("lastLogin check3");
		int isOk = sql.update("MemberMapper.last", id);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> getList() {
		log.info("list check3");
		return sql.selectList("MemberMapper.list");
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("modify check3");
		int isOk = sql.update("MemberMapper.up", mvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int remove(String id) {
		log.info(">>> remove check 3");
		int isOk = sql.delete("MemberMapper.del", id);
		if(isOk>0)sql.commit();
		return isOk;
	}
}
