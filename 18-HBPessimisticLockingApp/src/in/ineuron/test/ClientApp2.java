package in.ineuron.test;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClientApp2 {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;

		Integer id = 2;

		try {

			session = HibernateUtil.getSession();
			InsurancePolicy policy = new InsurancePolicy();

			if (session != null) {
				policy = session.get(InsurancePolicy.class,2,LockMode.UPGRADE_NOWAIT);

				tx = session.beginTransaction();

				policy.setTenure(24);

				

				
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				tx.commit();
				System.out.println("Obj Saved in db");
			} else {
				tx.rollback();
				System.out.println("Obj not saved in db");
			}
		}
		HibernateUtil.closeUp(session, factory);
	}

}
