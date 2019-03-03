package rs.board.domain;

//게시물 class
public class BoardVO {
	
	private int bbs_no;//게시물 번호
	private String bbs_tit;//게시물 제목
	private String bbs_detail;//게시물 상세내용
	private String regist_dt;//등록 날짜
	private String regist_nicknm;//등록 닉네임
	private String update_dt;//최종 수정날짜
	private String bbs_pwd;//게시물 비밀번호
	//getter, setter
	public int getBbs_no() {
		return bbs_no;
	}
	public void setBbs_no(int bbs_no) {
		this.bbs_no = bbs_no;
	}
	public String getBbs_tit() {
		return bbs_tit;
	}
	public void setBbs_tit(String bbs_tit) {
		this.bbs_tit = bbs_tit;
	}
	public String getBbs_detail() {
		return bbs_detail;
	}
	public void setBbs_detail(String bbs_detail) {
		this.bbs_detail = bbs_detail;
	}
	public String getRegist_dt() {
		return regist_dt;
	}
	public void setRegist_dt(String regist_dt) {
		this.regist_dt = regist_dt;
	}
	public String getRegist_nicknm() {
		return regist_nicknm;
	}
	public void setRegist_nicknm(String regist_nicknm) {
		this.regist_nicknm = regist_nicknm;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	public String getBbs_pwd() {
		return bbs_pwd;
	}
	public void setBbs_pwd(String bbs_pwd) {
		this.bbs_pwd = bbs_pwd;
	}
	
}
