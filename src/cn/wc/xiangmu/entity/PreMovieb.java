package cn.wc.xiangmu.entity;

import java.io.Serializable;





public class PreMovieb implements Serializable{
	String title;
	
	String id;
	//ͼƬ·��
	String imgsrc;
	//��ӳʱ��
	String time;
	//leixing
	String movietype;
	//���ҵ���
	String moviecountry;
	//����
	String moviedirector;
	//����
	String moviestarring;
	
	String moviefilsh;
	
	int t;
	
	
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public PreMovieb(String title, String id, String imgsrc, String time,
			String movietype, String moviecountry, String moviedirector,
			String moviestarring, String moviefilsh, int t) {
		super();
		this.title = title;
		this.id = id;
		this.imgsrc = imgsrc;
		this.time = time;
		this.movietype = movietype;
		this.moviecountry = moviecountry;
		this.moviedirector = moviedirector;
		this.moviestarring = moviestarring;
		this.moviefilsh = moviefilsh;
		this.t = t;
	}
	public String getMovietype() {
		return movietype;
	}
	 public String getMoviefilsh() {
		return moviefilsh;
	}
	public void setMoviefilsh(String moviefilsh) {
		this.moviefilsh = moviefilsh;
	}

	public void setMovietype(String movietype) {
		this.movietype = movietype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMoviecountry() {
		return moviecountry;
	}

	public void setMoviecountry(String moviecountry) {
		this.moviecountry = moviecountry;
	}

	public String getMoviedirector() {
		return moviedirector;
	}

	public void setMoviedirector(String moviedirector) {
		this.moviedirector = moviedirector;
	}

	public String getMoviestarring() {
		return moviestarring;
	}

	public void setMoviestarring(String moviestarring) {
		this.moviestarring = moviestarring;
	}
	public PreMovieb(String title, String id, String imgsrc, String time,
			String movietype, String moviecountry, String moviedirector,
			String moviestarring, String moviefilsh) {
		super();
		this.title = title;
		this.id = id;
		this.imgsrc = imgsrc;
		this.time = time;
		this.movietype = movietype;
		this.moviecountry = moviecountry;
		this.moviedirector = moviedirector;
		this.moviestarring = moviestarring;
		this.moviefilsh = moviefilsh;
	}
	public PreMovieb() {
		super();
	}
	

	
}
