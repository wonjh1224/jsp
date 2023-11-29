package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastLogin(String id);

	List<MemberVO> getList();

	int modify(MemberVO mvo);

	int remove(String id);

}
