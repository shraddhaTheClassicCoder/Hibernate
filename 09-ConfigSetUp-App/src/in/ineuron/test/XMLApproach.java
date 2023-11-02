package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.ineuron.model.Student;

public class XMLApproach {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		int id = 25;

		try {

			Configuration config = new Configuration();
			config.configure();

			factory = config.buildSessionFactory();

			session = factory.openSession();

			if (session != null) {

				Student student = session.get(Student.class, id);

				if (student != null) {
					System.out.println(student);

				} else {
					System.out.println("Student with give record is not available::" + id);
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
