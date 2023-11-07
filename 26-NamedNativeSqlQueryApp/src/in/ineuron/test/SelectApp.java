package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.query.NativeQuery;

import in.ineuron.util.HibernateUtil;

public class SelectApp {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;
		int count = 0;
		boolean flag = false;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSession();

			NativeQuery<Object[]> nativeQuery=session.getNamedNativeQuery("GET_ALL_SNAMES");
			
			nativeQuery.setParameter(1, "rani");
			
			
			List<Object[]> policies=nativeQuery.getResultList();
		
			policies.forEach(row->{
				for(Object obj:row) {
					System.out.println(obj+"\t");
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
