package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import in.ineuron.model.Insurancepolicy;
import in.ineuron.util.HibernateUtil;

public class TestApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();

			Query<Insurancepolicy> query = session.createQuery("from in.ineuron.model.Insurancepolicy");

			List<Insurancepolicy> policies = query.list();

			policies.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
