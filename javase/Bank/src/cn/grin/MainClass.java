package cn.grin;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {
	public static void main(String [] args){
		for(int i = 1;i<5;i++){
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setWindowID(i);
			commonWindow.start();
		}
		
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
		
		ServiceWindow vipWindow = new ServiceWindow();
		vipWindow.setType(CustomerType.VIP);
		vipWindow.start();

		//��ͨ�ͻ�ȡ��
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer number = NumberMachine.getInstance().getCommonManager().generateNewNumber();
				System.out.println(number+"����ͨ�ͻ��ȴ�����");
			}
		}, 0, Constants.COMOMON_CUSTOMER_INTEVAL_TIME, TimeUnit.SECONDS);
		//���ٿͻ�ȡ��
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer number = NumberMachine.getInstance().getExpressManager().generateNewNumber();
				System.out.println(number+"�ſ��ٿͻ��ȴ�����");
				
			}
		}, 0, Constants.EXPRESS_CUSTOMER_INTEVAL_TIME, TimeUnit.SECONDS);
		//VIP�ͻ�ȡ��
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer number = NumberMachine.getInstance().getVipManager().generateNewNumber();
				System.out.println(number+"��VIP�ͻ��ȴ�����");
				
			}
		}, 0, Constants.VIP_CUSTOMER_INTEVAL_TIME, TimeUnit.SECONDS);
		
		
	}
}