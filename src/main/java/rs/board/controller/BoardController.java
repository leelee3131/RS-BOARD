package rs.board.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import rs.board.domain.BoardVO;
import rs.board.domain.PageVO;
import rs.board.repo.BoardRepo;

@Controller
public class BoardController {
	
	
	@Autowired
	BoardRepo boardRepo;
	
	//날짜 표기방식 
	private Date time = new Date();
	private SimpleDateFormat curTime = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
	
	@RequestMapping("/home")
	public String boardList(HttpServletRequest request,Model model) throws Exception{
		try {
			HttpSession session = request.getSession();
			
			Integer countAll = 0;
			int curpageno=1;
			int maxpost=10;
			PageVO pageVO=new PageVO(curpageno,maxpost);
			
			//세션의 페이지 정보 확인
			if(session.getAttribute("pages")!=null) {
				pageVO=(PageVO) session.getAttribute("pages");
				curpageno = pageVO.getCurPageno();
				
			}
			if(request.getParameter("pages") != null) {
				curpageno = Integer.parseInt(request.getParameter("pages"));
				pageVO=new PageVO(curpageno,maxpost);
			}
			
			countAll = (int) boardRepo.getBoardSize();
			
			List<BoardVO> list = new ArrayList<>();
			list = boardRepo.getBoardList(curpageno);
			
			pageVO.setCountAll(countAll);
			pageVO.makePage();
			
			model.addAttribute("list",list);
			session.setAttribute("pages", pageVO);
			
			return "home";
		}catch(Exception e) {
			System.out.println("error : "+e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/detail/{bbs_no}")
	public String boardDetail(@PathVariable int bbs_no,HttpServletRequest request,Model model) throws Exception{
		try {
			BoardVO boardVO = new BoardVO();
			boardVO = boardRepo.getBoard(bbs_no);
			model.addAttribute("boardVO",boardVO);
			
			return "detail";
		}catch(Exception e) {
			System.out.println("error : "+e.getMessage());
			return "error";
		}
	}
	//200개 testdata 삽입 비밀번호 abcd
	@RequestMapping("/setTestData")
	public String setTestData() throws Exception{
		try {
			int i = 1;
			int bbs_no = boardRepo.getLastBoradNo();
			while(i<201) {
				
				BoardVO boardVO = new BoardVO();
				boardVO.setBbs_detail("hello test text "+bbs_no+i);
				boardVO.setBbs_tit("hello test title "+bbs_no+i);
				boardVO.setRegist_nicknm("nickname "+bbs_no+i);
				boardVO.setRegist_dt(curTime.format(time));
				boardVO.setUpdate_dt(curTime.format(time));
				boardVO.setBbs_no(bbs_no+i);
				boardVO.setBbs_pwd("abcd");
				boardRepo.insertBoard(boardVO);
				i++;
						
			}
			return "redirect:/home";
		}catch(Exception e) {
			return "error";
		}
	}
	@RequestMapping("/insert")
	public String insertBoard() throws Exception{
		try {
			return "insert";
		}catch(Exception e) {
			return "error";
		}
	}
	@RequestMapping("/insertProc")
	public String insertProcBoard(HttpServletRequest request) throws Exception{	
		try {
			BoardVO boardVO=new BoardVO();
			
			boardVO.setBbs_tit(request.getParameter("bbs_tit"));
			boardVO.setBbs_detail(request.getParameter("bbs_detail"));
			boardVO.setRegist_nicknm(request.getParameter("regist_nicknm"));
			boardVO.setRegist_dt(curTime.format(time));
			boardVO.setUpdate_dt(curTime.format(time));
			boardVO.setBbs_no(boardRepo.getLastBoradNo()+1);
			boardVO.setBbs_pwd(request.getParameter("bbs_pwd"));
			
			if(request.getParameter("regist_nicknm").equals("")||request.getParameter("bbs_tit").equals(""))
				throw new Exception();
			boardRepo.insertBoard(boardVO);
			
			return "redirect:/detail/"+boardVO.getBbs_no();
		}catch(Exception e) {
			return "error";
		}
	}
	@RequestMapping("/update/{bbs_no}")
	public String updateBoard(@PathVariable int bbs_no,HttpServletRequest request,Model model) throws Exception{
		try {
			BoardVO boardVO = new BoardVO();
			boardVO = boardRepo.getBoard(bbs_no);
			model.addAttribute("boardVO", boardVO);
			
			return "update";
		}catch(Exception e) {
			return "error";
		}
	}
	
	@RequestMapping("/updateProc")
	public String updateProcBoard(HttpServletRequest request) throws Exception{
		try {
			BoardVO boardVO = new BoardVO();
			boardVO.setBbs_tit(request.getParameter("bbs_tit"));
			boardVO.setBbs_detail(request.getParameter("bbs_detail"));
			boardVO.setBbs_no(Integer.parseInt(request.getParameter("bbs_no")));
			boardVO.setRegist_dt(request.getParameter("regist_dt"));
			boardVO.setRegist_nicknm(request.getParameter("regist_nicknm"));
			boardVO.setBbs_pwd(request.getParameter("bbs_pwd"));
			boardVO.setUpdate_dt(curTime.format(time));
			
			boardRepo.updateBoard(boardVO);
			
			return "redirect:/detail/"+boardVO.getBbs_no();
		} catch(Exception e) {
			
			return "error";
		}
	}
	@RequestMapping("/delete/{bbs_no}")
	public String deleteBoard(@PathVariable int bbs_no) throws Exception{
		try {
			boardRepo.deleteBoard(bbs_no);
			return "redirect:/home";
		}catch(Exception e) {
			return "error";
		}		
	}
}
