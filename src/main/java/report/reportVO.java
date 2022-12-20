package report;

public class reportVO {
	private int replyIdx;
	private String replyMid;
	private String replyContent;
	private String replyWriteDay;
	private String reportMid;
	private String reportWriteDay;
	
	public int getReplyIdx() {
		return replyIdx;
	}
	public void setReplyIdx(int replyIdx) {
		this.replyIdx = replyIdx;
	}
	public String getReplyMid() {
		return replyMid;
	}
	public void setReplyMid(String replyMid) {
		this.replyMid = replyMid;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyWriteDay() {
		return replyWriteDay;
	}
	public void setReplyWriteDay(String replyWriteDay) {
		this.replyWriteDay = replyWriteDay;
	}
	public String getReportMid() {
		return reportMid;
	}
	public void setReportMid(String reportMid) {
		this.reportMid = reportMid;
	}
	public String getReportWriteDay() {
		return reportWriteDay;
	}
	public void setReportWriteDay(String reportWriteDay) {
		this.reportWriteDay = reportWriteDay;
	}
	
	@Override
	public String toString() {
		return "reportVO [replyIdx=" + replyIdx + ", replyMid=" + replyMid + ", replyContent=" + replyContent
				+ ", replyWriteDay=" + replyWriteDay + ", reportMid=" + reportMid + ", reportWriteDay=" + reportWriteDay + "]";
	}
	
}
