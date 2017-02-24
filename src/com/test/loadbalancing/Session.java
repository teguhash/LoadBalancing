package com.test.loadbalancing;

public class Session {

	private String username;
	private int hitCount;
	private boolean busy;
	
	public Session() {
		busy = false;
	}		

	public Session(String username, int hitCount) {
		super();
		this.username = username;
		this.hitCount = hitCount;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

}
