package rs.board.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;


import rs.board.domain.BoardVO;

//redis repository
@Repository
public class BoardRepo {
	
	public  String KEY = "board";
	
	private RedisTemplate<String,Object> redisTemplate;
	private ZSetOperations zsetOperations;
	
	public BoardRepo(RedisTemplate<String, Object> redisTemplate) throws Exception {
	    this.redisTemplate = redisTemplate;
	    zsetOperations = redisTemplate.opsForZSet();
	}
	
	public long getBoardSize(){
		
		return zsetOperations.size(KEY);
		
	}
	public List<BoardVO> getBoardList(int page) throws Exception {
		
		Set<String> value = zsetOperations.reverseRange(KEY, (page-1)*10, page*10-1);
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
	public void insertBoard(BoardVO boardVO) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.convertValue(boardVO, Map.class);
		Gson gson = new Gson();
		String jValue = gson.toJson(boardVO);
		zsetOperations.add(KEY, jValue, boardVO.getBbs_no());
		
    }
	
	public void updateBoard(BoardVO boardVO) throws Exception {
		
		zsetOperations.removeRangeByScore(KEY, boardVO.getBbs_no(), boardVO.getBbs_no());
		
		boardVO.setBbs_no(getLastBoradNo()+1);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.convertValue(boardVO, Map.class);
		Gson gson = new Gson();
		String jValue = gson.toJson(boardVO);
		
		zsetOperations.add(KEY, jValue, boardVO.getBbs_no());
		
    }
	public void deleteBoard(int bbs_no) throws Exception {
		
		zsetOperations.removeRangeByScore(KEY, bbs_no, bbs_no);
	}
	public BoardVO getBoard(int bbs_no) throws Exception {
		
		BoardVO boardVO = new BoardVO();
		Set<String> value = zsetOperations.rangeByScore(KEY,bbs_no,bbs_no);
		String sValue= String.join("",value);
		Gson gson = new Gson();
		boardVO = gson.fromJson(sValue, BoardVO.class);
		return boardVO;
	}
	
	public Integer getLastBoradNo() throws Exception {
		
		BoardVO boardVO = new BoardVO();
		Set<String> value = zsetOperations.reverseRange(KEY, 0, 0);
		String sValue= String.join("",value);
		
		if(sValue.equals("")) {
			return 0;
		}else {
			Gson gson = new Gson();
			boardVO = gson.fromJson(sValue, BoardVO.class);
			return boardVO.getBbs_no();
			
		}
		
	}

}
