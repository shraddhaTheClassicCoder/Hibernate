package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import in.ineuron.Utility.HibernateUtil;
import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

//controller logic
public class TestApp {

	static {
		HibernateUtil.startUp();
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE, PRESS[1/2/3/4/5]::  ");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertOpertation();
				break;
			case "2":
				searchOperation();
				break;
			case "3":
				UpdateOperation();
				break;
			case "4":
				deleteOperation();
				break;
			case "5":
				System.out.println("******* Thanks for using the application *****");
				System.exit(0);
			default:
				System.out.println("Invalid option plz try agin with valid options....");
				break;
			}
			

		}

	}

	private static void insertOpertation() {

		IStudentService studentService = StudentServiceFactory.getStudentService();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the name of student: ");
		String sname = scanner.next();

		System.out.print("Enter the age of student: ");
		int sage = scanner.nextInt();

		System.out.print("Enter the address of student: ");
		String saddress = scanner.next();

		String msg = studentService.save(sname, sage, saddress);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record inserted succesfully");
		} else {
			System.out.println("record insertion failed....");
		}
		//scanner.close();
	}

	private static void searchOperation() {
		// Search Student
		IStudentService studentService = StudentServiceFactory.getStudentService();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the sid of student: ");
		int sid = scanner.nextInt();

		Student student = studentService.searchById(sid);

		if (student != null) {
			System.out.println("SID" + "\t" + "SNAME" + "\t" + "SAGE" + "\t" + "SADDRESS");
			System.out.println(student.getSid() + "\t" + student.getSname() + "\t" + student.getSage() + "\t"
					+ student.getSaddress());
		}
	}

	private static void UpdateOperation() throws IOException {
		// Update Record
		/*
		 * IStudentService studentService; studentService =
		 * StudentServiceFactory.getStudentService();
		 * 
		 * Scanner scanner = new Scanner(System.in);
		 * 
		 * System.out.print("Enter the sid of student: "); int sid = scanner.nextInt();
		 * 
		 * System.out.print("Enter the name of student: "); String sname =
		 * scanner.next();
		 * 
		 * System.out.print("Enter the age of student: "); int sage = scanner.nextInt();
		 * 
		 * System.out.print("Enter the address of student: "); String saddress =
		 * scanner.next();
		 * 
		 * String updateMsg = studentService.updateStudent(sid, sname, sage, saddress);
		 * 
		 * if (updateMsg.equalsIgnoreCase("success")) {
		 * System.out.println("Record Updated Successfully"); } else {
		 * System.out.println("Record Updation  failed"); } scanner.close();
		 */

		// Logic implemented for if user does not enters any field details then old
		// records should be preserved else entered records should be updated
		IStudentService studentService = StudentServiceFactory.getStudentService();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the student Id to be Updated: ");
		String Sid = br.readLine();
		Student student = studentService.searchById(Integer.parseInt(Sid));

		if (student != null) {

			Student newStudent = new Student();

			System.out.println("Student Id is:" + student.getSid());
			newStudent.setSid(student.getSid());
			System.out.println("The Old Student Name is" + student.getSname() + "Enter the New Student Name: ");
			String newName = br.readLine();
			if (newName.equals(" ") || newName == "") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}

			System.out.println("The Old Student Age is" + student.getSage() + "Enter the New Student Age: ");
			String newAge = br.readLine();
			if (newAge.equals(" ") || newAge == "") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newAge));
			}

			System.out
					.println("The Old Student Address is" + student.getSaddress() + "Enter the New Student Address: ");
			String newAddrs = br.readLine();
			if (newAddrs.equals(" ") || newAddrs == "") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSaddress(newAddrs);
			}

			String status = studentService.updateById(newStudent);

			if (status.equals("success")) {
				System.out.println("Record updated successfully");
			} else {
				System.out.println("Record Updation Failed");
			}

		}

	}

	private static void deleteOperation() {
		// delete student
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the sid of student: ");
		int sid = scanner.nextInt();
		String DeleteMsg = studentService.deleteById(sid);

		if (DeleteMsg.equalsIgnoreCase("success")) {
			System.out.println("Record Deleted Successfully with Id" + sid);
		} else {
			System.out.println("Record is not available  with Id" + sid);
		}
	}
}
