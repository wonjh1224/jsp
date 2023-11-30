package repository;

import java.util.List;

import domain.BoardVO;
import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int update(MemberVO mvo);

	int delete(String id);

	List<MemberVO> selectAll();

	



	
	

}
