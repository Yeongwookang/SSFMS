package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.util.DBConn;

public class AccUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AccDAO dao = new AccDAOImpl();
	
	public void menu() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.print("회계 => ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch == 7) {
					DBConn.close();
					return;
				}
				
				switch(ch) {
				case 1: insert(); break;
				case 2: update(); break;
				case 3: delete(); break;
				case 4: findById(); break;
				case 5: findByName(); break;
				case 6: listAll(); break;
				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	protected void insert() {
		System.out.println("\n회원 가입 !!!");

	}
	
	protected void update() {
		System.out.println("\n회원 정보 수정 !!!");
	
	}
	
	protected void delete() {
		System.out.println("\n회원 탈퇴 !!!");
	
		
	}
	
	protected void findByName() {
		System.out.println("\n이름 검색 !!!");

		
	}
	
	protected void findById() {
		System.out.println("\n아이디 검색 !!!");

		
	}
	
	protected void listAll() {
		System.out.println("\n전체 리스트 !!!");
	
	}
}
