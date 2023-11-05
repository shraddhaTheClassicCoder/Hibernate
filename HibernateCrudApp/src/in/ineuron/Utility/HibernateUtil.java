package in.ineuron.Utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.ineuron.dto.Student;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	private HibernateUtil() {
		// Making constructor private so no one can create object of it outside of the
		// class
	}

	static {
		sessionFactory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

	}

	public static Session getSession() {
		if (session == null) {
			session = sessionFactory.openSession();

		}
		return session;
	}

	public static void closeUp(Session session, SessionFactory sessionFactory) {

		if (session != null) {
			session.close();
		}
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public static void startUp() {
		System.out.println("********Hibernate SetUp has Started******");
	}
}
