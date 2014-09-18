package com.luke.model.fruit;

import org.springframework.stereotype.Component;

@Component
public class Apple implements Fruit {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "I am Apple";
	}

}
