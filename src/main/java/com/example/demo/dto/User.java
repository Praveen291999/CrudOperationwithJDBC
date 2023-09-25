package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder

@Getter
public class User {
	private String name;
	private String address;
	private int age;
	

}
