package reply;

public class replyVO {
	private int ridx;
	private String boardIdx;
	private String content;
	private double rating;
	private String writeDay;
	private String mid;
	public int getRidx() {
		return ridx;
	}
	public void setRidx(int ridx) {
		this.ridx = ridx;
	}
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getWriteDay() {
		return writeDay;
	}
	public void setWriteDay(String writeDay) {
		this.writeDay = writeDay;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	@Override
	public String toString() {
		return "replyVO [ridx=" + ridx + ", boardIdx=" + boardIdx + ", content=" + content + ", rating=" + rating
				+ ", writeDay=" + writeDay + ", mid=" + mid + "]";
	}
	
}
