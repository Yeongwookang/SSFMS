package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class EmpUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private EmpDAO dao = new EmpDAOImpl();
	
	public void menu() { 
        Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		// 안에 문자에도 대소문자 구별 필수
		String s = sdf.format(date);
		System.out.println(s);
		
		int ch;
		
		while(true) {
			try {
				System.out.print("1.사원등록 2.사원수정 3.사원삭제 4.경력사항 입력 5.경력사항 수정 6.경력사항 삭제 7.연봉입력 8.뒤로가기=> ");
				ch = Integer.parseInt(br.readLine());
				
				
				switch(ch) {
				case 1: insert(); break;
				case 2: update(); break;
				case 3: delete(); break;
				case 4: findById(); break;
				case 5: findByName(); break;
				case 6: listAll(); break;
				case 7: ainsert(); break;
				case 8: App.main(null); break;
				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	protected void insert() {
		System.out.println("\n사원 등록하기 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("사원번호 ? ");
			dto.setEmpNo(br.readLine());
			
			System.out.print("성명 ? ");
			dto.setName(br.readLine());

			System.out.print("비밀번호 ? ");
			dto.setPwd(br.readLine());

			System.out.print("학력사항 ? ");
			dto.setEdu(br.readLine());

			System.out.print("연락처 ? ");
			dto.setTel(br.readLine());
			
			System.out.print("이메일 ? ");
			dto.setEmail(br.readLine());
			
			System.out.print("주소 ? ");
			dto.setAddr(br.readLine());
			
			System.out.print("통장번호 ? ");
			dto.setAccount(br.readLine());
			
			System.out.print("고용형태 ? ");
			dto.setHire_class(br.readLine());
			
			System.out.print("주민번호 ? ");
			dto.setRrn(br.readLine());
			
			dao.insertEmp(dto);
			
			System.out.println("사원등록에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("사원등록에 실패 했습니다.");
		}
		
		System.out.println();

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
		
		List<EmpDTO> list = dao.listEmp();
		for(EmpDTO dto : list) {
			System.out.print(dto.getEmpNo() +"\t");
			System.out.print(dto.getPwd() +"\t");
			System.out.print(dto.getName() +"\t");
			System.out.print(dto.getTel() +"\t");
			System.out.print(dto.getRrn() +"\t");
			System.out.print(dto.getEmail() +"\t");
			System.out.print(dto.getAddr() +"\t");
			System.out.print(dto.getEdu() +"\t");
			System.out.println(dto.getAccount());
		}
		System.out.println();
	
	}
	
	protected void ainsert() {
		System.out.println("연봉 입력하기 !!!");
		
		try {
			EmpDTO adto = new EmpDTO();
			
			System.out.print("연봉코드 ? ");
			adto.setAsalNo(br.readLine());
			
			System.out.print("계약일자 ? ");
			adto.setSal_date(br.readLine());

			System.out.print("계약연봉 ? ");
			adto.setAsal(Integer.parseInt(br.readLine()));

			System.out.print("사번 ? ");
			adto.setEmpNo(br.readLine());
			
			dao.insertAsal(adto);
			
			System.out.println("연봉등록에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("연봉등록에 실패 했습니다.");
		}
		
		System.out.println();
	}
}
