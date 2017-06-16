package com.gla.aircare.tools;

public class Main {

	public static void main(String[] args) {
		ParseCSV parse = new ParseCSV("MPD_task.csv");
		parse.findAllMpd();
		//System.out.println(parse.myToString());
	}
}
