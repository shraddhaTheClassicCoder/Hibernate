package in.ineuron.test;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsertRecord {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;

		try {

			session = HibernateUtil.getSession();

			if (session != null) {
				tx = session.beginTransaction();
				InsurancePolicy policy = new InsurancePolicy();

				policy.setName("Jeevan");
				policy.setTenure(8);
				policy.setPeriod("monthly");

				Integer id = (Integer) session.save(policy);

				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				tx.commit();
				System.out.println("Record Save or Updated in the Database");
			} else {
				tx.rollback();
				System.out.println("Record is not Saved or Updated in the Database");
			}
		}
		HibernateUtil.closeUp(session, factory);
	}

}
