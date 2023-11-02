package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class SyncUpdate {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Integer id = 21;
		try {

			session = HibernateUtil.getSession();
			Student student = new Student();

			if (session != null) {
				tx = session.beginTransaction();
				student = session.load(Student.class, id);
				System.out.println("Student with Given id::" + id);

				if (tx != null) {
					if (student != null) {

						student.setSaddress("thane");
                        student.setSage(24);
						session.update(student);
						flag = true;
					}

				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				tx.commit();
				System.out.println("Record is updated with Given Id" + id);
			} else {
				System.out.println("Record is not updated with Given Id" + id);

			}
			HibernateUtil.closeUp(session, factory);
		}
	}

}
