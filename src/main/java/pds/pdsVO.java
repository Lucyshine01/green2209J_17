package pds;

public class pdsVO {
	private int pIdx;
	private String fOriName;
	private String fSysName;
	private int fSize;
	
	private String mid;
	
	
	public int getpIdx() {
		return pIdx;
	}
	public void setpIdx(int pIdx) {
		this.pIdx = pIdx;
	}
	public String getfOriName() {
		return fOriName;
	}
	public void setfOriName(String fOriName) {
		this.fOriName = fOriName;
	}
	public String getfSysName() {
		return fSysName;
	}
	public void setfSysName(String fSysName) {
		this.fSysName = fSysName;
	}
	public int getfSize() {
		return fSize;
	}
	public void setfSize(int fSize) {
		this.fSize = fSize;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	@Override
	public String toString() {
		return "pdsVO [pIdx=" + pIdx + ", fOriName=" + fOriName + ", fSysName=" + fSysName + ", fSize=" + fSize + ", mid="
				+ mid + "]";
	}
	
	
	
}
