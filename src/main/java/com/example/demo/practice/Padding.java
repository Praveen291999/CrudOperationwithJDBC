package com.example.demo.practice;

import org.apache.commons.lang3.StringUtils;

public class Padding {

	public static void main(String[] args) {
		Integer contractNumber = 12345;
		Integer addOnNumber = 7;
		String contractString = StringUtils.leftPad(String.valueOf(contractNumber), 8, '0');
		String addOnString = StringUtils.leftPad(String.valueOf(addOnNumber), 3, '0');

		String voi = "1" +contractString+addOnString;

		System.out.println("VOI: "+voi);
	}
}
