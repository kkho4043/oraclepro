package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<PersonVo> personList;//그냥 리스트
		List<PersonVo> personsearchList;// search 리스트
		PhoneDao phoneDao = new PhoneDao();
		
		
		System.out.println("***************************");
		System.out.println("*     전화번호 관리 프로그램        *");
		System.out.println("***************************");
		while(true) {
			System.out.println("1.리스트	2.등록	3.수정	4.삭제	5.검색	6종료");
			System.out.println("--------------------------------------------");
			System.out.print(">메뉴 번호 : ");
			int menu = sc.nextInt();
				
			if(menu == 6) {
				System.out.println("***************************");
				System.out.println("*         감사합니다.        *");
				System.out.println("***************************");
				break;
			}else if(menu ==  1){
				personList = phoneDao.getPersonList();
				System.out.println("<1.리스트>");
				for(int i = 0; i < personList.size();i++) {
					PersonVo vo = personList.get(i);
					System.out.println(vo.getPersonId()+".\t"+vo.getName()+"\t"+vo.getHp()+"\t"+vo.getCompany());
				}
				
			}else if(menu == 2) {
				System.out.println("<2.등록>");
				sc.nextLine();
				
				System.out.print("이름 > ");
				String name = sc.nextLine();
				
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				
				PersonVo personVo = new PersonVo(name,hp,company);
				phoneDao.personInsert(personVo);
			}else if(menu == 3) {
				System.out.println("<3.수정>");
				sc.nextLine();
				
				System.out.print("번호 > ");
				int personId= sc.nextInt();
				sc.nextLine();
				
				System.out.print("이름 > ");
				String name = sc.nextLine();
				
				System.out.print("휴대전화 > ");
				String hp = sc.nextLine();
				
				System.out.print("회사번호 > ");
				String company = sc.nextLine();
				
				PersonVo personVo = new PersonVo(personId,name,hp,company);
				phoneDao.personUpdate(personVo);
			}else if(menu == 4) {
				System.out.println("<4.삭제>");
				System.out.print(">번호 : ");
				phoneDao.parsonDelete(sc.nextInt());
			}else if(menu == 5) {
				System.out.println("<5.검색>");
				System.out.print("검색어 > ");
				
				sc.nextLine();
				
				personsearchList = phoneDao.getsearchList(sc.nextLine());
				System.out.println("<1.리스트>");
				for(int i = 0; i < personsearchList.size();i++) {
					PersonVo svo = personsearchList.get(i);
					System.out.println(svo.getPersonId()+".\t"+svo.getName()+"\t"+svo.getHp()+"\t"+svo.getCompany());
				}
				
			}else {
				System.out.println("[다시입력해주세요.]");
			}
			System.out.println("");
			
		}
	}
	
}



