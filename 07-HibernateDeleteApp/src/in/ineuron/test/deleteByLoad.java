package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class deleteByLoad {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Integer id = 21;
		try {

			session = HibernateUtil.getSession();

			Student student1 = session.load(Student.class, id);

			if (session != null) {

				tx = session.beginTransaction();

				if (student1 != null) {
					session.delete(student1);
					tx.commit();
					flag = true;
					System.out.println("Record is deleletd from database");
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		HibernateUtil.closeUp(session, factory);
	}

}
