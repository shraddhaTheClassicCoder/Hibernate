package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.model.Address;
import in.ineuron.model.StudentInfo;
import in.ineuron.util.HibernateUtil;

public class SelectApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();
			Query<StudentInfo> query = session
					.createQuery("from in.ineuron.model.StudentInfo where address.cityName=:city");
			query.setParameter("city", "Bengaluru");

			List<StudentInfo> student = query.list();
			student.forEach(System.out::println);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			HibernateUtil.closeUp(session, factory);
		}

	}

}
