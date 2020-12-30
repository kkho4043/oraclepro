package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<PersonVo> personList;//�׳� ����Ʈ
		List<PersonVo> personsearchList;// search ����Ʈ
		PhoneDao phoneDao = new PhoneDao();
		
		
		System.out.println("***************************");
		System.out.println("*     ��ȭ��ȣ ���� ���α׷�        *");
		System.out.println("***************************");
		while(true) {
			System.out.println("1.����Ʈ	2.���	3.����	4.����	5.�˻�	6����");
			System.out.println("--------------------------------------------");
			System.out.print(">�޴� ��ȣ : ");
			int menu = sc.nextInt();
				
			if(menu == 6) {
				System.out.println("***************************");
				System.out.println("*         �����մϴ�.        *");
				System.out.println("***************************");
				break;
			}else if(menu ==  1){
				personList = phoneDao.getPersonList();
				System.out.println("<1.����Ʈ>");
				for(int i = 0; i < personList.size();i++) {
					PersonVo vo = personList.get(i);
					System.out.println(vo.getPersonId()+".\t"+vo.getName()+"\t"+vo.getHp()+"\t"+vo.getCompany());
				}
				
			}else if(menu == 2) {
				System.out.println("<2.���>");
				sc.nextLine();
				
				System.out.print("�̸� > ");
				String name = sc.nextLine();
				
				System.out.print("�޴���ȭ > ");
				String hp = sc.nextLine();
				
				System.out.print("ȸ���ȣ > ");
				String company = sc.nextLine();
				
				
				PersonVo personVo = new PersonVo(name,hp,company);
				phoneDao.personInsert(personVo);
			}else if(menu == 3) {
				System.out.println("<3.����>");
				sc.nextLine();
				
				System.out.print("��ȣ > ");
				int personId= sc.nextInt();
				sc.nextLine();
				
				System.out.print("�̸� > ");
				String name = sc.nextLine();
				
				System.out.print("�޴���ȭ > ");
				String hp = sc.nextLine();
				
				System.out.print("ȸ���ȣ > ");
				String company = sc.nextLine();
				
				PersonVo personVo = new PersonVo(personId,name,hp,company);
				phoneDao.personUpdate(personVo);
			}else if(menu == 4) {
				System.out.println("<4.����>");
				System.out.print(">��ȣ : ");
				phoneDao.parsonDelete(sc.nextInt());
			}else if(menu == 5) {
				System.out.println("<5.�˻�>");
				System.out.print("�˻��� > ");
				
				sc.nextLine();
				
				personsearchList = phoneDao.getsearchList(sc.nextLine());
				System.out.println("<1.����Ʈ>");
				for(int i = 0; i < personsearchList.size();i++) {
					PersonVo svo = personsearchList.get(i);
					System.out.println(svo.getPersonId()+".\t"+svo.getName()+"\t"+svo.getHp()+"\t"+svo.getCompany());
				}
				
			}else {
				System.out.println("[�ٽ��Է����ּ���.]");
			}
			System.out.println("");
			
		}
	}
	
}



