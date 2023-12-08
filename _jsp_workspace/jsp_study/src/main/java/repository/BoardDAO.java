package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pvo);

	int readCountUpdate(int bno);

	BoardVO getDetail(int bno);

	int Update(BoardVO bvo);

	int delete(int bno);

	int getCnt(PagingVO pvo);

//	int removeCmt(int bno);

}
