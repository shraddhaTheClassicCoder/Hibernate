package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class SelectWithoutPAssingMappingClass1 {
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;

		try {
			session = HibernateUtil.getSession();

			@SuppressWarnings("unchecked")
			NativeQuery<Student> nativeQuery = session
					.createSQLQuery("SELECT * FROM Student WHERE sage>=:min AND sage<=:max");

			// setting parameter
			nativeQuery.setParameter("min", 25);
			nativeQuery.setParameter("maxSelectWithPAssingMappingClass.java", 28);
			
			//Mapping with Entity Class
			nativeQuery.addEntity(Student.class);

			// Executing Result
			List<Student> policies = nativeQuery.list();
            
			
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
