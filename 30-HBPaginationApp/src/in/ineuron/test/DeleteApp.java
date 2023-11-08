package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class DeleteApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Query<InsurancePolicy> query = session.createQuery("from in.ineuron.model.InsurancePolicy");
			
			query.setFirstResult(3);
			query.setMaxResults(5);// It will display max 3 records on page

			List<InsurancePolicy> list1 = query.list();

			list1.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {

			HibernateUtil.closeUp(session, factory);

		}
	}

}
