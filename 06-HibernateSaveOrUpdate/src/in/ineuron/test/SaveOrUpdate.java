package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class SaveOrUpdate {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		//Integer id = 25;
		try {

			session = HibernateUtil.getSession();
			Student student = new Student();

			if (session != null) {
                tx=session.beginTransaction();
				//student = session.load(Student.class, id);
				//System.out.println("Student with Given id::" + id);

				if (student != null) {
					
					student.setSid(25);
					student.setSname("nita");
					student.setSage(32);
					student.setSaddress("mumbai");
					
					session.saveOrUpdate(student);
					tx.commit();
					flag=true;
					
					System.out.println("Record Save or Updated in the Database");
				}else {
					
					System.out.println("Record is not Saved or Updated in the Database");
				}
					
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		HibernateUtil.closeUp(session, factory);
	}

}
