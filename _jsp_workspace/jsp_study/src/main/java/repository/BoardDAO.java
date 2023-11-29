package repository;

import java.util.List;

import domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList();

	int readCountUpdate(int bno);

	BoardVO getDetail(int bno);

	int Update(BoardVO bvo);

	int delete(int bno);

}
