package service;

import java.util.List;

import domain.BoardVO;
import domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	MemberVO login(MemberVO mvo);
	


	int modify(MemberVO mvo);

	int remove(String id);

	List<MemberVO> getList();



}
