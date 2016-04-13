package testpack2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;



import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetFuelStationData {
	
	private static WebDriver driver;
	private static String url;
	private static int station_id;
	
	@Test
	public static int getStationId() throws InterruptedException, ParseException, IOException {
		
		driver = new FirefoxDriver();
		String apiKey = "Oo7ahCMLb6lFWIToEStDRgyVngLHkA2zdczmmshc";
		String location = "Austin+TX";
		url = "https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key=" + apiKey + "&location=" + location;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(url);
		Thread.sleep(2000);
		
		
	     List<WebElement> fuel_station_data = driver.findElements(By.xpath("/html/body/pre"));
	     for (WebElement pre : fuel_station_data) {

	    	 
	     
	    	 JSONObject obj = new JSONObject(pre.getText().toString());
	    	 String fuel_station = obj.getString("fuel_stations");
	    	 JSONArray arr = new JSONArray(fuel_station);
	    	 
	    	 
	    	 for (int i=0; i < arr.length(); i++) {	
	    		 
	    		 String station_name = arr.getJSONObject(i).getString("station_name");
	    		 
	    		 String ev_network = arr.getJSONObject(i).getString("ev_network");
	    		 
	    		 if((station_name.equals("HYATT AUSTIN")) && (ev_network.equals("ChargePoint Network"))){
	    			 Assert.assertEquals(station_name, "HYATT AUSTIN");
	    			 Assert.assertEquals(ev_network, "ChargePoint Network");
	    			 station_id = arr.getJSONObject(i).getInt("id");
	    			 
	    			 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    		 String json = gson.toJson(arr.getJSONObject(i)); 
		    		 //String prettyJsonString = gson.toJson(je);
		    		 try {
		    			 FileWriter file = new FileWriter ("/Dhruti_workspace/testProject2/src/testpack2/StationNameDetail.json");
				    	 BufferedWriter BW = new BufferedWriter(file); 
				    	 BW.write("result: " + json);
				    	 BW.close();
		    			 
		    		 } catch (IOException ex){
		    			 ex.printStackTrace();
		    		 }
	    		 }

	    	 }
	    	 
	     }
		
		return station_id;		
	}
	
	@Test
	public static void verifyAddress() throws InterruptedException, ParseException, IOException {
		
		int station_id = getStationId();
		System.out.println("station_id: " + station_id);
		
		List<WebElement> fuel_station_data = driver.findElements(By.xpath("/html/body/pre"));
	     for (WebElement pre : fuel_station_data) {
	     
	    	 JSONObject obj = new JSONObject(pre.getText().toString());
	    	 String fuel_station = obj.getString("fuel_stations");
	    	 JSONArray arr = new JSONArray(fuel_station);
	    	 
	    	 
	    	 for (int i=0; i < arr.length(); i++) {	
	    		 
	    		 int stationIdData = arr.getJSONObject(i).getInt("id");
	    		 if(stationIdData == station_id ) {
	    			 Assert.assertEquals(station_id, stationIdData);
	    			 
	    			 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		    		 String json = gson.toJson(arr.getJSONObject(i)); 
		    		 System.out.println(json);		    		 
		    		 
	    			 String address = arr.getJSONObject(i).getString("street_address");
	    			 String city = arr.getJSONObject(i).getString("city");
	    			 String state = arr.getJSONObject(i).getString("state");
	    			 String zipCode = arr.getJSONObject(i).getString("zip");
	    			 
	    			 String full_address = address + ", " + city + ", " + state + ", USA, " + zipCode;
	    			 String expected_address = "208 Barton Springs, Austin, Texas, USA, 78704";
	    			 if (full_address.equals(expected_address)) {
	    				 Assert.assertEquals(expected_address, full_address);
	    				 System.out.println("expected address found - " + expected_address);
	    			 }
	    			 else {
	    				 System.out.println("expected address NOT found.");
	    			 }   			 
	    			 
	    		 }
	    	 }
	     }		
		
	}
	
	
	
    public static  void main(String[] args) throws InterruptedException, ParseException, IOException {
		
    	verifyAddress();    	
    	driver.close();
	}

}
