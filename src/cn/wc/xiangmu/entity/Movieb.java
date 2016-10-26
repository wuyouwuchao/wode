package cn.wc.xiangmu.entity;



public class Movieb {
	String imagesrc;
	String name;
	String nexturl;
	public String getImagesrc() {
		return imagesrc;
	}
	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNexturl() {
		return nexturl;
	}
	public void setNexturl(String nexturl) {
		this.nexturl = nexturl;
	}
	public Movieb(String imagesrc, String name, String nexturl) {
		super();
		this.imagesrc = imagesrc;
		this.name = name;
		this.nexturl = nexturl;
	}
	public Movieb() {
		super();
	}
	
	
	
}
