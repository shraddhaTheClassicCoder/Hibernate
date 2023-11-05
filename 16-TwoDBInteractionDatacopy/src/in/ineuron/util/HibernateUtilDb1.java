package in.ineuron.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import in.ineuron.model.Product;

public class HibernateUtilDb1 {

	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	private HibernateUtilDb1() {
		// Making constructor private so no one can create object of it outside of the
		// class
	}

	static {
		sessionFactory = new Configuration().configure("in/ineuron/config/hibernate.cfg_Db1.xml").addAnnotatedClass(Product.class).buildSessionFactory();

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

}
