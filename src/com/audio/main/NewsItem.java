package com.audio.main;

public class NewsItem {
	private String sec;
	private String i_title;
	private String e_title;
	private String number;
	private int imgId;
	private int bgImg = 0;
	private boolean visible_flag = false;
	private boolean pause_visible_flag = false;
	private boolean main_visible_flag = false;

	public String getSecond() {
		return sec;
	}
	public void setSecond(String sec) {
		this.sec = sec;
	}
	
	
	public String getITitle() {
		return i_title;
	}
	public void setITitle(String i_title) {
		this.i_title = i_title;
	}
	
	
	public String getETitle() {
		return e_title;
	}
	public void setETitle(String e_title) {
		this.e_title = e_title;
	}
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public int getImageId() {
		return imgId;
	}
	public void setImageId(int imgId) {
		this.imgId = imgId;
	}
	
	public boolean getVisible() {
		return visible_flag;
	}
	public void setVisible(boolean visible_flag) {
		this.visible_flag = visible_flag;
	}
	
	public boolean getPauseVisible() {
		return pause_visible_flag;
	}
	public void setPauseVisible(boolean pause_visible_flag) {
		this.pause_visible_flag = pause_visible_flag;
	}
	
	public boolean getMainVisible() {
		return main_visible_flag;
	}
	public void setMainVisible(boolean main_visible_flag) {
		this.main_visible_flag = main_visible_flag;
	}
	
	
	public int getBgImageId() {
		return bgImg;
	}
	public void setBgImageId(int bgImg) {
		this.bgImg = bgImg;
	}
	@Override
	public String toString() {
		return "[i_title=" + i_title + ", e_title=" + e_title + ", number=" + number + "]";
	}
}