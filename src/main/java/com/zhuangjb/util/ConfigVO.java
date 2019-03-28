package com.zhuangjb.util;

public class ConfigVO {
	private String title;
	private int height;
	private int width;

	private int autoXiazhuTask; 
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "ConfigVO [title=" + title + ", height=" + height + ", width=" + width + "]";
	}

	public int getAutoXiazhuTask() {
		return autoXiazhuTask;
	}

	public void setAutoXiazhuTask(int autoXiazhuTask) {
		this.autoXiazhuTask = autoXiazhuTask;
	}

}
