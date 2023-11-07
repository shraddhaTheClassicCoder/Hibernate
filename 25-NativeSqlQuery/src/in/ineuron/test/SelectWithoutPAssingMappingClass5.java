package in.ineuron.test;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class SelectWithoutPAssingMappingClass5 {
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;

		try {
			session = HibernateUtil.getSession();

			@SuppressWarnings("unchecked")
			NativeQuery<String> nativeQuery = session.createSQLQuery("SELECT SNAME FROM STUDENT WHERE sage>=:age");

			// setting parameter

			nativeQuery.setParameter("age", 25);

			// Mapping with Entity Class
			nativeQuery.addEntity(Student.class);

			// Executing Result
			List<String> policies = nativeQuery.getResultList();

			// processing result
			policies.forEach(System.out::println);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
