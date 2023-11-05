package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class MergeApp {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Student std = null;

		// Integer id = 21;
		try {

			Student student = new Student();

			student.setSid(7);
			student.setSaddress("mumbai");
			student.setSage(22);
			student.setSname("nikita");

			session = HibernateUtil.getSession();

			if (session != null) {
				transaction = session.beginTransaction();

				if (transaction != null) {

					std = (Student) session.merge(student);

					flag = true;

				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record is updated with Given Id");
			} else {
				transaction.rollback();
				System.out.println("Record is not updated with Given Id");

			}
			HibernateUtil.closeUp(session, factory);
		}
	}

}
