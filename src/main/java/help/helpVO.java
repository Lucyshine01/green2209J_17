package help;

public class helpVO {
	private int hidx;
	private String title;
	private String content;
	private String conf;
	private String answer;
	private String mid;
	private String writeDay;
	private String answerDay;
	
	
	public int getHidx() {
		return hidx;
	}
	public void setHidx(int hidx) {
		this.hidx = hidx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConf() {
		return conf;
	}
	public void setConf(String conf) {
		this.conf = conf;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getWriteDay() {
		return writeDay;
	}
	public void setWriteDay(String writeDay) {
		this.writeDay = writeDay;
	}
	public String getAnswerDay() {
		return answerDay;
	}
	public void setAnswerDay(String answerDay) {
		this.answerDay = answerDay;
	}
	
	@Override
	public String toString() {
		return "helpVO [hidx=" + hidx + ", title=" + title + ", content=" + content + ", conf=" + conf + ", answer="
				+ answer + ", mid=" + mid + ", writeDay=" + writeDay + ", answerDay=" + answerDay + "]";
	}
	
}
