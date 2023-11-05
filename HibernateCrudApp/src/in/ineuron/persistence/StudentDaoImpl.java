package in.ineuron.persistence;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Utility.HibernateUtil;
import in.ineuron.dto.Student;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {

	boolean flag = true;
	Session session = HibernateUtil.getSession();
	Transaction transaction = null;

	@Override
	public String save(String sname, Integer sage, String saddress) {

		Student student = new Student();
		String status = "";
		try {

			if (session != null) {

				transaction = session.beginTransaction();

				if (transaction != null) {
					student.setSname(sname);
					student.setSage(sage);
					student.setSaddress(saddress);

					session.save(student);
					flag = true;

				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "fail";
			}
		}
		return "success";
	}

	@Override
	public Student searchById(Integer sid) {

		Student student = new Student();

		if (session != null)
			student = session.get(Student.class, sid);

		if (student != null)
			return student;
		else
			return null;

	}

	public String updateById(Student student) {

		// Student student = new Student();
		String status = "";
		try {

			if (session != null) {

				transaction = session.beginTransaction();

				if (transaction != null) {

					/*
					 * student.setSname(newStudent.getSname());
					 * student.setSage(newStudent.getSage());
					 * student.setSaddress(newStudent.getSaddress());
					 */

					session.merge(student);

					flag = true;

				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record is updated");

			} else {
				transaction.rollback();
				System.out.println("Record  updation failed ");

			}
		}
		return "success";
	}

	@Override
	public String deleteById(Integer sid) {

		Student student = new Student();
		String status = "";
		try {

			if (session != null) {

				student = session.get(Student.class, sid);
				transaction = session.beginTransaction();

				if (transaction != null) {

					if (student != null) {

						student.setSid(sid);
						session.delete(student);
						flag = true;
					}

				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "fail";
			}
		}
		return "success";

	}

}
