package in.ineuron.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

//Persistence logic using hibernate
public class StudentDaoImpl implements IStudentDao {

	Session session = HibernateUtil.getSession();
	Student student = null;
	boolean flag = false;
	Transaction transaction = null;

	@SuppressWarnings("finally")
	@Override
	public String addStudent(Student student) {
		System.out.println(student);

		Boolean flag = false;
		try {
			transaction = session.beginTransaction();
			session.save(student);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				return "success";
			} else {
				transaction.rollback();
				return "failure";
			}
		}
	}

	@Override
	public Student searchStudent(Integer sid) {

		try {

			student = session.get(Student.class, sid);

			if (student != null)
				return student;
			else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return student;
	}

	@SuppressWarnings("finally")
	@Override
	public String deleteStudent(Integer sid) {

		student = searchStudent(sid);
		if (student != null) {
			try {

				transaction = session.beginTransaction();

				session.delete(student);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (flag) {
					transaction.commit();
					return "success";
				} else {
					transaction.rollback();
					return "failure";
				}

			}
		} else {
			return "not found";
		}

	}

	@SuppressWarnings("finally")
	@Override
	public String updateStudent(Student student) {
		System.out.println(student);

		Boolean flag = false;
		try {
			transaction = session.beginTransaction();
			session.merge(student);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				return "success";
			} else {
				transaction.rollback();
				return "failure";
			}
		}
	}

}
