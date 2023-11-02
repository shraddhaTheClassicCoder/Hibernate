import java.util.Date;

public class DateAppDemo {

	public static void main(String[] args) {
		
		Date udate=new Date();
		System.out.println("Utile Date:"+udate);
		
		long value=udate.getTime();
		
		java.sql.Date sDate=new java.sql.Date(value);
		
		System.out.println(sDate);

	}

}
