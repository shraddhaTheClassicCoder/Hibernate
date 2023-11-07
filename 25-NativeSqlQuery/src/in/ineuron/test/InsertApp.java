package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import in.ineuron.util.HibernateUtil;

public class InsertApp {
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;
		int count = 0;
		boolean flag = false;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSession();

			transaction=session.beginTransaction();
			@SuppressWarnings("unchecked")
			NativeQuery nativeQuery = session.createSQLQuery("insert into student(sname,sage,saddress) values(?,?,?)");

			// setting the parameter
			nativeQuery.setParameter(1, "Shreya");
			nativeQuery.setParameter(2, 23);
			nativeQuery.setParameter(3, "Pune");

			// Execute Query
			count = nativeQuery.executeUpdate();
			flag = true;

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record inserted to database");
			} else {
				transaction.rollback();
				System.out.println("Record failed to insert into database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
