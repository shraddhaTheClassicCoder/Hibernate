package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Address;
import in.ineuron.model.StudentInfo;
import in.ineuron.util.HibernateUtil;

public class InsertApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory=null;
		Transaction transaction = null;
		boolean flag = false;
		try {

			session = HibernateUtil.getSession();
			if (session != null) {

				transaction = session.beginTransaction();

				Address addr1 = new Address();
				addr1.setCityName("Mumbai");
				addr1.setCountryName("INDIA");
				addr1.setStateName("Maharashtra");

				StudentInfo info1 = new StudentInfo();
				info1.setSage(31);
				info1.setSname("maria");
				info1.setAddress(addr1);

				Address addr2 = new Address();
				addr2.setCityName("kanpur");
				addr2.setCountryName("INDIA");
				addr2.setStateName("DELHI");

				StudentInfo info2 = new StudentInfo();
				info2.setSage(32);
				info2.setSname("Vijay");
				info2.setAddress(addr2);

				session.save(info1);
				session.save(info2);

				flag = true;

			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Obects Saved in DB...");
			} else {
				transaction.rollback();
				System.out.println("Objects not saved in DB...");
			}
			HibernateUtil.closeUp(session, factory);
		}

	}

}
