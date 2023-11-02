package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class delete {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;

		try {

			session = HibernateUtil.getSession();

			if (session != null) {

				tx = session.beginTransaction();
			}

			if (tx != null) {
				Student student = new Student();
				student.setSid(22);
				session.delete(student);
				tx.commit();
				flag = true;
				System.out.println("Record is deleletd from database with Id");
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		HibernateUtil.closeUp(session, factory);
	}

}
