import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateAppDemo1 {

	public static void main(String[] args) throws ParseException {

		// Take the inputs from the user in string format

		System.out.println("Enter the Date in dd-MM-yyyy");
		Scanner scan = new Scanner(System.in);

		String sdate = scan.next();

		// convert that String date to Java.sql.Util.Date fromat
		// here we are using one intermediator class for conversion

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date udate = sdf.parse(sdate);

		// convert Util date to Sql Date
		long l = udate.getTime();

		java.sql.Date sqlDate = new java.sql.Date(l);

		// Printing all date
		System.out.println("String Date format" + sdate);
		System.out.println("Util Date format" + udate);
		System.out.println("SQL date format" + sqlDate);
		System.out.println();

		System.out.println("Enter the DOM in format:(yyyy-MM-dd)");
		String standardInput=scan.next();
		
		java.sql.Date standardSqlInput=java.sql.Date.valueOf(standardInput);
		System.out.println("Standard Input"+standardInput);
		System.out.println("Sql Standard Input"+standardSqlInput);
		
		scan.close();
		

	}
}
