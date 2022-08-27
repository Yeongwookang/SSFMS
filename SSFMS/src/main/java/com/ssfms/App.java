package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.util.DBConn;

public class App {
	public static void main(String[] args) {
		int ch=0;
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		try {
			do {

				String n = " _____  _____  ______ ___  ___ _____ \r\n"
				                + "/  ___|/  ___| |  ___||  \\/  |/  ___|\r\n"
				                + "\\ `--. \\ `--.  | |_   | .  . |\\ `--. \r\n"
				                + " `--. \\ `--. \\ |  _|  | |\\/| | `--. \\\r\n"
				                + "/\\__/ //\\__/ / | |    | |  | |/\\__/ /\r\n"
				                + "\\____/ \\____/  \\_|    \\_|  |_/\\____/ ";

				System.out.println(n);
				        
				System.out.print("1.회계 2.구매 3.인사 4.생산 5.영업 6.종료 => ");
				ch=Integer.parseInt(br.readLine());
				
			}while(ch<1||ch>6);
			if(ch==6) {
				System.exit(0);
			}
			switch(ch) {
			case 1: new AccUI().menu();
			case 2: new BuyUI().menu();
			case 3: new EmpUI().menu();
			case 4: new ProdUI().menu();
			case 5: new SalesUI().menu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
