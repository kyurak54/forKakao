package com.example.spring.equip;

import java.util.Random;

public class RandomString {

	// 영숫자 난수
	public String RandomString(int count_word) {
		
		Random rnd =new Random();

		StringBuffer buf =new StringBuffer();

		for(int i=0; i<count_word; i++){

			if (i==4 || i==9 || i==14) {
				buf.append("-");
				continue;
			}

			// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 대문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
		    if(rnd.nextBoolean()){

		        buf.append((char)((int)(rnd.nextInt(26))+65));

		    }else{

		        buf.append((rnd.nextInt(10)));

		    }
		}
		return buf.toString();

	}

}