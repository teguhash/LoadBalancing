package com.test.loadbalancing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SessionManager {

	private List<Session> sessionPool;
	private Random randomizer;
	private List<Integer> sampledIndex;
	
	public SessionManager() {
		sessionPool = new ArrayList<Session>();
		sampledIndex = new ArrayList<Integer>();
		generatePool(100);
	}
	
	private void generatePool(int size)
	{
		for (int i = 0; i < size; i++) {
			randomizer = new Random();
			sessionPool.add(new Session("user#"+(i+1), randomizer.nextInt(100)));
		}
	}
	
	private boolean alreadySampled(int index)
	{
		for (Integer i : sampledIndex) {
			if(index == i.intValue()) return true;
		}
		return false;
	}
	
	public Session sample()
	{
		randomizer = new Random();
		if( sampledIndex.size() == 0 )
		{
			return sessionPool.get(randomizer.nextInt(100));
		}
		else if( sampledIndex.size() == sessionPool.size() )
		{
			return null;
		}
		else
		{
			int index = 0;
			do{
				index = randomizer.nextInt(100);
			}while(alreadySampled(index));
			sampledIndex.add(index);
			return sessionPool.get(index);
		}
	}
	
	public void printAllSessionDetails()
	{		
		System.out.println("+----------+-----+");
		System.out.println("| username | hit |");
		System.out.println("+----------+-----+");
		for (Session session : sessionPool) {
			System.out.printf("| %-8s | %-3d |\n", session.getUsername(), session.getHitCount());
		}
		System.out.println("+----------+-----+");
	}
	
	public int getSessionCount()
	{
		return sessionPool.size();
	}
	
	public void clearSamples()
	{
		sampledIndex.clear();
	}

}
