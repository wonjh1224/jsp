package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int register(MemberVO mvo) {
		log.info(">>> join check 2");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>> login check2");
		return mdao.login(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info("lastLogin check2");
		return mdao.lastLogin(id);
	}

	@Override
	public List<MemberVO> getList() {
		log.info("list check2");
		return mdao.getList();
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("modify check2");
		return mdao.modify(mvo);
	}

	@Override
	public int remove(String id) {
		log.info("remove check2");
		return mdao.remove(id);
	}


}
