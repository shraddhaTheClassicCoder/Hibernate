package in.ineuron.test;

import in.ineuron.model.jobSeeker;
import in.ineuron.util.HibernateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SelectRecord {

	public static void main(String[] args) {

	
		
		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		//Integer id = null
		Integer id=2;
		
		try {

			session = HibernateUtil.getSession();
			jobSeeker seeker=new jobSeeker();

			
			if (session != null) {
				seeker=session.get(jobSeeker.class, id);
				//tx = session.beginTransaction();
			}
		
			if(seeker!=null) {
				
				System.out.println("The Jobseeker Id:: "+seeker.getJsId());
				System.out.println("The Jobseeker Name:: "+seeker.getJsName());
				System.out.println("The Jobseeker Address:: "+seeker.getAddress());
				try(FileOutputStream fos=new FileOutputStream("./store/Shraddha.jpg");FileWriter fw=new FileWriter("./store/AdharCard.docx"))
				{
					fos.write(seeker.getPhoto());
					fw.write(seeker.getResume());
					System.out.println("Photo and resume got downloaded to :: ./store/****");
				}

			}else {
				System.out.println("Record not available for the given id :: " + id);
			}}
		catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} 
		HibernateUtil.closeUp(session, factory);
	}

}
