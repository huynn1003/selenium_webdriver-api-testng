package javaBasic;

import java.util.Random;

public class Topic_02_Fake_Data {
	//Lấy số ngẫu nhiên
	public int randomNum() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}
