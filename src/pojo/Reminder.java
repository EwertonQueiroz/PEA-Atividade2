package pojo;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder extends Thread {
	Timer timer;
	Jogo j = new Jogo();
	
	public Reminder () {}
	
	public Reminder (int segundos) {
		timer = new Timer();
		timer.schedule(new RemindTask(), segundos * 1000);
	}
	
	class RemindTask extends TimerTask {
		@Override
		public void run () {
			synchronized (this) {
				j.limpar_console();
				timer.cancel();
				notify();
			}
		}
	}
}