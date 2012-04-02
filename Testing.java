import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** 
 * @author Caleb Drake
 * 
 *This class tests the PrintBalance.java class.
 */

public class Testing extends PrintBalance{
	
	private static final ArrayList<Locale> locales= new ArrayList<Locale>();
	
	
	//Adds the locales used for testing to the "locales" Array List
	@Before
	public void setUp(){   	
		locales.add(Locale.getDefault());
    	locales.add(new Locale("en", "US"));
    	locales.add(new Locale("de", "DE"));
    	locales.add(new Locale("fr", "FR"));
    	
    }
	
	
	//Clears the "locales" Array List
	@After
	public void tearDown(){
		locales.clear();
	}
		
	
	 
	@Test
	public void testSetLocaleChangesCurrentLocales(){
				
		for (Locale loc : locales){
			setLocale(loc);
			assertEquals(loc, currentLocale);
		}
	}
	
	
	//Tests appropriate fetching of Resource Bundles
	@Test
	public void testFetchBundle(){
		assertEquals(fetchBundle(currentLocale),  ResourceBundle.getBundle("MessageBundle", currentLocale));
		
		
	}

	//Tests that every instance of messages resulting from a locale combined with specific key are the appropriate ones  
	@Test
	public void testThatProperMessage(){
		ArrayList<String> keys =  new ArrayList<String>();
		
		for (Locale loc: locales){
			setLocale(loc);
			messages = fetchBundle(loc);
			keys.addAll(messages.keySet());
			
			for(String key : keys){
				assertEquals(locMsg(key), messages.getString(key));
			}
			keys.clear();
		}
	}
	
	//Tests that dates format correctly to each Locale
	@Test
	public void testProperDate(){
		Date date = new Date();
		for (Locale loc: locales){
			setLocale(loc);
			messages = fetchBundle(loc);
			assertEquals(locDate(date), new SimpleDateFormat(locMsg("date"),  currentLocale).format(date));
		}
	}
	
	
	//Tests that monetary amounts format correctly to each Locale
	@Test
	public void testProperCurrency(){
		for (Locale loc: locales){
			setLocale(loc);
			messages = fetchBundle(loc);
			assertEquals(locCurrency(9876543.21),NumberFormat.getCurrencyInstance(currentLocale).format(9876543.21));
		}
	}
}
