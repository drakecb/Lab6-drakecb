import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 *	Localized Java Method which gives requests the user's preferred locale (English, French, or German) greets the user, prompts the user's for a name,
 *	prints-out the current date & the associated name's debt to the school, and then bids the user farewell.
 *
 * @author Caleb Drake
 *         Created Friday, 2012 March 31
 */
public class PrintBalance{

	public static Locale currentLocale;
	public static ResourceBundle messages;
	public static String name;

	
			
		
	/**
	 *  
	 *
	 * @param args
	 */
	public static void main(String args[]){
		
		//Prompts a user to select a locality (English [en], German [de], French[fr]
		System.out.println("For English, type\t->\ten\nFür deutsche, geben sie\t->\tde\nPour le français, tapez\t->\tfr");		
		Scanner scanner = new Scanner (System.in);
		switch(scanner.nextLine()){
		case "en":	setLocale(new Locale("en","US"));
					break;
		case "de":	setLocale(new Locale("de", "DE"));
					break;
		
		case "fr":	setLocale(new Locale("fr", "FR"));
					break;
					
		default:	setLocale(Locale.getDefault());
					break;
		
		}

		messages = fetchBundle(currentLocale);
		Date today = new Date(); // the current time
		
		
		//Greeting
		System.out.println(locMsg("greeting"));
		
		//Get User's Name
		System.out.println(locMsg("prompt"));
		name = scanner.nextLine();
		System.out.println(locMsg("acquaint") + name);
		
		
        
		//print today's date, balance and bid goodbye
		System.out.println(locMsg("asOf")+ locDate(today));
		System.out.println(locMsg("bill")+ locCurrency(9876543.21));
		System.out.println(locMsg("farewell"));
	}
	
	
	//Set the currentLocale to the Locale specified 
	public static void setLocale(Locale loc){
		currentLocale = loc;
	}
	
	
	//Returns the "MessageBundle" associated with the specified locality
	public static ResourceBundle fetchBundle(Locale loc){
		return ResourceBundle.getBundle("MessageBundle", loc);
	}
	
	
	//Returns the localized phrase associated with the specified key
	public static String locMsg(String key){
		return messages.getString(key);
	}
	
	
	//Returns the specified date formatted for the current locale
	public static String locDate(Date date){
		return new SimpleDateFormat(locMsg("date"),  currentLocale).format(date);
	}
	
	
	//Returns the specified monetary amount formatted to the current locale's currency
	public static String locCurrency(double money){
		return NumberFormat.getCurrencyInstance(currentLocale).format(money);
	}
}



