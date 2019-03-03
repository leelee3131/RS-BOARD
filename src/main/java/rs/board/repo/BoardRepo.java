package rs.board.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.board.domain.BoardVO;

//redis repository
@Repository
public class BoardRepo {
	
	public  String KEY = "board";
	
	private RedisTemplate<String,Object> redisTemplate;
	private ZSetOperations zsetOperations;
	
	public BoardRepo(RedisTemplate<String, Object> redisTemplate) {
	    this.redisTemplate = redisTemplate;
	    zsetOperations = redisTemplate.opsForZSet();
	}
	
	public long getBoardSize(){
		
		return zsetOperations.size(KEY);
		
	}
	public List<BoardVO> getBoardList(int page){
		
		Set<String> value = zsetOperations.reverseRange(KEY, (page-1)*10, page*10-1);
		System.out.println(value.toString());
		List<BoardVO> list = new ArrayList<BoardVO>();
		Iterator<String> iter = value.iterator();
		while(iter.hasNext()) {
			BoardVO boardVO = new BoardVO();
			Gson gson = new Gson();
			boardVO = gson.fromJson(iter.next(), BoardVO.class);
			list.add(boardVO);
		}
		return list;
	}
	public void addBoard(BoardVO boardVO){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.convertValue(boardVO, Map.class);
		Gson gson = new Gson();
		String jValue = gson.toJson(boardVO);
		zsetOperations.add(KEY, jValue, boardVO.getBbs_no());
		
    }
	
	@Transactional
	public void updateBoard(BoardVO boardVO) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.convertValue(boardVO, Map.class);
		Gson gson = new Gson();
		String jValue = gson.toJson(boardVO);
		int bbs_no = boardVO.getBbs_no();
		zsetOperations.removeRangeByScore(KEY, bbs_no, bbs_no);
		zsetOperations.add(KEY, jValue, bbs_no);
		
	}
	public void deleteBoard(int bbs_no) {
		
		zsetOperations.removeRangeByScore(KEY, bbs_no, bbs_no);
	}
	public BoardVO getBoard(int bbs_no) {
		
		BoardVO boardVO = new BoardVO();
		Set<String> value = zsetOperations.rangeByScore(KEY,bbs_no,bbs_no);
		String sValue= String.join("",value);
		Gson gson = new Gson();
		boardVO = gson.fromJson(sValue, BoardVO.class);
		return boardVO;
	}
	
	public Integer getLastBoradNo() {
		
		BoardVO boardVO = new BoardVO();
		Set<String> value = zsetOperations.reverseRange(KEY, 0, 0);
		String sValue= String.join("",value);
		
		if(sValue.equals("")) {
			return 0;
		}else {
			System.out.println("false");
			Gson gson = new Gson();
			boardVO = gson.fromJson(sValue, BoardVO.class);
			return boardVO.getBbs_no();
			
		}
		
	}

}
