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

		//普通客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer number = NumberMachine.getInstance().getCommonManager().generateNewNumber();
				System.out.println(number+"号普通客户等待服务");
			}
		}, 0, Constants.COMOMON_CUSTOMER_INTEVAL_TIME, TimeUnit.SECONDS);
		//快速客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer number = NumberMachine.getInstance().getExpressManager().generateNewNumber();
				System.out.println(number+"号快速客户等待服务");
				
			}
		}, 0, Constants.EXPRESS_CUSTOMER_INTEVAL_TIME, TimeUnit.SECONDS);
		//VIP客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer number = NumberMachine.getInstance().getVipManager().generateNewNumber();
				System.out.println(number+"号VIP客户等待服务");
				
			}
		}, 0, Constants.VIP_CUSTOMER_INTEVAL_TIME, TimeUnit.SECONDS);
		
		
	}
}
