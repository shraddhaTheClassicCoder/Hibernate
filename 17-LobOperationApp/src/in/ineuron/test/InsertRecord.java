package in.ineuron.test;

import in.ineuron.model.jobSeeker;
import in.ineuron.util.HibernateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

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
		//Integer id = null;
		
		byte[] photo=null;
		
		char[] resume=null;
		
		try {

			session = HibernateUtil.getSession();
			jobSeeker seeker=new jobSeeker();

			
			try(FileInputStream fis=new FileInputStream("C:\\Users\\Lenovo\\OneDrive\\Documents\\shraddha.jpg"))
			{ photo=new byte[fis.available()];
			  fis.read(photo);
			}
			 File f=new File("C:\\Users\\Lenovo\\OneDrive\\Documents\\AdharCard.docx");
			
		    try(FileReader fr=new FileReader(f))
            {
               resume=new char[(int)f.length()];
               fr.read(resume);
               
            }		
			if (session != null) {
				tx = session.beginTransaction();
				
				seeker.setJsName("shraddha");
				seeker.setAddress("Pune");
                seeker.setResume(resume);
                seeker.setPhoto(photo);
                
				
                Integer id= (Integer) session.save(seeker);
                
                flag=true;
				}

			}
		catch (HibernateException e) {
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
