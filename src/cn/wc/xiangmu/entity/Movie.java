package cn.wc.xiangmu.entity;

public class Movie {
	//����
	String id;
	
	String title;
	// ����
	String top;
	//ʵʱƱ��
	String realtime;
	//�ۼ�Ʊ��
	String total;
	//Ʊ��ռ��
	String boxofficeof;
	//����ռ��
	String Rowseatof;
	//��ӳ����
	String datas;
	//ͼƬ·��
	String picurl;
	//���е���
	String rCountry;
	//ʱ��
	String rRuntime;
	//����
	String rDaoyan;
	//���
	String rBianju;
	//����
	String rYanyuan;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getRealtime() {
		return realtime;
	}
	public void setRealtime(String realtime) {
		this.realtime = realtime;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getBoxofficeof() {
		return boxofficeof;
	}
	public void setBoxofficeof(String boxofficeof) {
		this.boxofficeof = boxofficeof;
	}
	public String getRowseatof() {
		return Rowseatof;
	}
	public void setRowseatof(String rowseatof) {
		Rowseatof = rowseatof;
	}
	public String getDatas() {
		return datas;
	}
	public void setDatas(String datas) {
		this.datas = datas;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getrCountry() {
		return rCountry;
	}
	public void setrCountry(String rCountry) {
		this.rCountry = rCountry;
	}
	public String getrRuntime() {
		return rRuntime;
	}
	public void setrRuntime(String rRuntime) {
		this.rRuntime = rRuntime;
	}
	public String getrDaoyan() {
		return rDaoyan;
	}
	public void setrDaoyan(String rDaoyan) {
		this.rDaoyan = rDaoyan;
	}
	public String getrBianju() {
		return rBianju;
	}
	public void setrBianju(String rBianju) {
		this.rBianju = rBianju;
	}
	public String getrYanyuan() {
		return rYanyuan;
	}
	public void setrYanyuan(String rYanyuan) {
		this.rYanyuan = rYanyuan;
	}
	public Movie(String title, String top, String realtime, String total,
			String boxofficeof, String rowseatof, String datas, String picurl,
			String rCountry, String rRuntime, String rDaoyan, String rBianju,
			String rYanyuan) {
		super();
		this.title = title;
		this.top = top;
		this.realtime = realtime;
		this.total = total;
		this.boxofficeof = boxofficeof;
		Rowseatof = rowseatof;
		this.datas = datas;
		this.picurl = picurl;
		this.rCountry = rCountry;
		this.rRuntime = rRuntime;
		this.rDaoyan = rDaoyan;
		this.rBianju = rBianju;
		this.rYanyuan = rYanyuan;
	}
	public Movie() {
		super();
	}
	@Override
	public String toString() {
		return "Movie [title=" + title + ", top=" + top + ", realtime="
				+ realtime + ", total=" + total + ", boxofficeof="
				+ boxofficeof + ", Rowseatof=" + Rowseatof + ", datas=" + datas
				+ ", picurl=" + picurl + ", rCountry=" + rCountry
				+ ", rRuntime=" + rRuntime + ", rDaoyan=" + rDaoyan
				+ ", rBianju=" + rBianju + ", rYanyuan=" + rYanyuan + "]";
	}
	
	
	
	
}
