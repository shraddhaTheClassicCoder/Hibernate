import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialization {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// Serialization is the process of converting java object into network supported
		// form or file supported form.
		// Two Classes:1. FileOutputStream 2.ObjectOutputStream

		// write the data to file
		Emp e = new Emp(111, "kiya");
		FileOutputStream fos = new FileOutputStream("abc.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(e);
		System.out.println("Serialization is completed");

		
		 //Deserialization
		//The Process of reading the object from file or from the network supported form into the object supported form is called 
		//De-serialization
		
		//Read the data from file
		FileInputStream fis=new FileInputStream("abc.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		Emp e1=(Emp)ois.readObject();		
		System.out.println("Deserialization is complted");
		System.out.println("Emp Object: "+e1.eid+"\t"+e1.ename);
	}

}

class Emp implements Serializable{

	int eid;
	String ename;

	Emp(int eid, String ename) {
		this.eid = eid;
		this.ename = ename;
	}
}