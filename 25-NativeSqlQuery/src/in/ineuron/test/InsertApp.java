package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import in.ineuron.util.HibernateUtil;

public class InsertApp {
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;

		try {
			session = HibernateUtil.getSession();

			@SuppressWarnings("unchecked")
			NativeQuery<Object[]> nativeQuery = session
					.createSQLQuery("SELECT * FROM Student WHERE sage>=? AND sage<=?");

			// setting parameter
			nativeQuery.setParameter(1, 25);
			nativeQuery.setParameter(2, 28);

			// Executing Result
			List<Object[]> policies = nativeQuery.getResultList();
            
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			// processing result
			policies.forEach(row -> {
				for (Object obj : row) {
					System.out.print(obj+"\t");
				}
				System.out.println();
			});
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
