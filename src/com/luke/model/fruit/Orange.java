package com.luke.model.fruit;

import org.springframework.stereotype.Component;

@Component("orange") // This is bean name
public class Orange implements Fruit {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "I am Orange";
	}

}
