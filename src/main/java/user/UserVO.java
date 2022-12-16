package user;

public class UserVO {
	private int uidx; 
	private int cidx;
	
	private String mid;
	private String pwd;
	private String email;
	private String birth;
	private String tel;
	private String createDay;
	private String userLevel;
	private int point;
	
	private String name;
	private String cpName;
	private String cpAddr;
	private String cpImg;
	private String cpHomePage;
	private String cpIntro;
	private String cpIntroImg;
	private String cpExp;
	private String act;
	private int imgSize;
	private String createDayCP;
	public int getUidx() {
		return uidx;
	}
	public void setUidx(int uidx) {
		this.uidx = uidx;
	}
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCreateDay() {
		return createDay;
	}
	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getCpAddr() {
		return cpAddr;
	}
	public void setCpAddr(String cpAddr) {
		this.cpAddr = cpAddr;
	}
	public String getCpImg() {
		return cpImg;
	}
	public void setCpImg(String cpImg) {
		this.cpImg = cpImg;
	}
	public String getCpHomePage() {
		return cpHomePage;
	}
	public void setCpHomePage(String cpHomePage) {
		this.cpHomePage = cpHomePage;
	}
	public String getCpIntro() {
		return cpIntro;
	}
	public void setCpIntro(String cpIntro) {
		this.cpIntro = cpIntro;
	}
	public String getCpIntroImg() {
		return cpIntroImg;
	}
	public void setCpIntroImg(String cpIntroImg) {
		this.cpIntroImg = cpIntroImg;
	}
	public String getCpExp() {
		return cpExp;
	}
	public void setCpExp(String cpExp) {
		this.cpExp = cpExp;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public int getImgSize() {
		return imgSize;
	}
	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}
	public String getCreateDayCP() {
		return createDayCP;
	}
	public void setCreateDayCP(String createDayCP) {
		this.createDayCP = createDayCP;
	}
	
	@Override
	public String toString() {
		return "UserVO [uidx=" + uidx + ", cidx=" + cidx + ", mid=" + mid + ", pwd=" + pwd + ", email=" + email + ", birth="
				+ birth + ", tel=" + tel + ", createDay=" + createDay + ", userLevel=" + userLevel + ", point=" + point
				+ ", name=" + name + ", cpName=" + cpName + ", cpAddr=" + cpAddr + ", cpImg=" + cpImg + ", cpHomePage="
				+ cpHomePage + ", cpIntro=" + cpIntro + ", cpIntroImg=" + cpIntroImg + ", cpExp=" + cpExp + ", act=" + act
				+ ", imgSize=" + imgSize + ", createDayCP=" + createDayCP + "]";
	}
	
}
