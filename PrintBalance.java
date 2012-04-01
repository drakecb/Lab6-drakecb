import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.text.NumberFormatter;


/**
 * TODO A simple class that needs to be localized
 *
 * @author mohan.
 *         Created Mar 27, 2011.
 */
public class PrintBalance{

	
	/**
	 * Simple Java Method that is crying out to be localized.
	 *
	 * @param args
	 */
	public static void main(String args[])
	{
		Locale currentLocale;
		Locale usLocale = new Locale("en", "US");
		Locale deLocale = new Locale("de", "DE");
		Locale frLocale = new Locale("fr", "FR");
		ResourceBundle messages;
		
		System.out.println("For English, type\t->\ten\nFür deutsche, geben sie\t->\tde\nPour le français, tapez\t->\tfr");
		Scanner langPref = new Scanner (System.in);
		switch(langPref.nextLine()){
		case "en":	currentLocale = usLocale;
					break;
		case "de":	currentLocale = deLocale;
					break;
		
		case "fr":	currentLocale = frLocale;
					break;
					
		default:	currentLocale = Locale.getDefault();
					break;
		
		}

		messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
		
						
		Date today = new Date(); // the current time
		
	    SimpleDateFormat sdf = new SimpleDateFormat(messages.getString("date"),  currentLocale);
	    
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
	    
		//Greeting
		System.out.println(messages.getString("greeting"));
		
		//Get User's Name
		System.out.println(messages.getString("prompt"));
		String name = langPref.nextLine();
		System.out.println("I am pleased to meet you " + name);
		
		
        
		//print today's date, balance and bid goodbye
		System.out.println(messages.getString("asOf")+ sdf.format(today));
		System.out.println(messages.getString("bill")+ currencyFormatter.format(9876543.21));
		System.out.println(messages.getString("farewell"));
	}
}



