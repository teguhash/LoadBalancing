package com.test.loadbalancing;

public class Main {

	public Main() {
		SessionManager manager = new SessionManager();
		manager.printAllSessionDetails();
		
        for (int i = 1; i <= 5; i++) {
        	Session min = manager.sample();
            for (int k = 1; k < manager.getSessionCount()-(i-1); k++) {
                Session queue = manager.sample();
                if (queue.getHitCount() < min.getHitCount() && !queue.isBusy()) min = queue;
            }

            min.setBusy(true);
            System.out.println("Test #"+i);
            System.out.println("Username  : "+min.getUsername());
            System.out.println("Hit count : "+min.getHitCount());
            System.out.println("");
            manager.clearSamples();
		}		      
	}

	public static void main(String[] args) {		
		new Main();
	}

}
