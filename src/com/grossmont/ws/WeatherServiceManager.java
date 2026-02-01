package com.grossmont.ws;// Classes for reading web service.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// Classes for JSON conversion to java objects using Google's gson.
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class WeatherServiceManager{

    private Weather m_oWeather = null;

    private String m_sWeatherJson;



    // Gets the overall weather JSON string from the 3rd party web service.
    public void callWeatherWebService(String sCity){

    	String sServiceReturnJson = "";

    	try {

            // Call weather API.
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" +
                    sCity + "&appid=1868f2463a960613c0a78b66a99b5e5f&units=imperial");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                    sServiceReturnJson += strTemp;
            }


            //Prints out raw json
             //System.out.println("Returned json:");
             //System.out.println(sServiceReturnJson);



        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error occurred in callWeatherWebService() in com.grossmont.ws.WeatherServiceManager: " + ex.toString());
        }

        m_sWeatherJson = sServiceReturnJson;

        // Turn raw json into java object heirarchy using Google's gson.
        convertJsonToJavaObject();
    }




	// Uses Google's gson library to convert json into filled java objects
	// using the java object hierarchy that was already created.
    private void convertJsonToJavaObject(){

        Gson gson = new GsonBuilder().create();

        m_oWeather = gson.fromJson(m_sWeatherJson, Weather.class);
    }




    // This uses Google's gson library for parsing json.
    public float getCurrentTemp(){

        return m_oWeather.main.temp;
    }

    public String getCityName(){
        return m_oWeather.name;
    }

    public float getHighTemp(){
        return m_oWeather.main.temp_max;
    }

    public float getLowTemp(){
        return m_oWeather.main.temp_min;
    }

    public static void main(String[] args){


        WeatherServiceManager oCity1 = new WeatherServiceManager();
        WeatherServiceManager oCity2 = new WeatherServiceManager();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the first city: ");
        String sCity1 = scanner.nextLine();
        System.out.print("Please enter second city: ");
        String sCity2 = scanner.nextLine();


        sCity1 = sCity1.replaceAll(" ","%20");
        sCity2 = sCity2.replaceAll(" ","%20");

        oCity1.callWeatherWebService(sCity1);
        oCity2.callWeatherWebService(sCity2);

        if((oCity1.getHighTemp() - oCity1.getLowTemp()) < (oCity2.getHighTemp() - oCity2.getLowTemp())){
            System.out.println("The temperature in " + oCity2.getCityName() + " is " + oCity2.getCurrentTemp() + "°F");
            System.out.println(oCity2.getCityName() + " has a greater temperature range than " + oCity1.getCityName());
            System.out.println(oCity2.getCityName() + ": " + "high of ~ " + oCity2.getHighTemp() + "°F " + " low of ~ " + oCity2.getLowTemp() + "°F");
        }
        else {
            System.out.println("The temperature in " + oCity1.getCityName() + " is " + oCity1.getCurrentTemp() + "°F");
            System.out.println(oCity1.getCityName() + " has a greater temperature range than " + oCity2.getCityName());
            System.out.println(oCity1.getCityName() + ": " + "high of ~ " + oCity1.getHighTemp() + "°F " + " low of ~ " + oCity1.getLowTemp() + "°F");

        }
    }




	// ------------------------------------------------------------------------------------------------------------


	// Only included here just as an example of how the raw json
	// could be parsed directly w/o using 3rd party library like gson.
	public float getTempManualParse(){

		String sTemp = "";
		float fTemp;

		// Parse "temp" out of JSON reply.
		int iTempIndex = m_sWeatherJson.indexOf("\"temp\":") + 7;
		sTemp = m_sWeatherJson.substring(iTempIndex);
		sTemp = sTemp.substring(0, sTemp.indexOf(","));
		fTemp = Float.parseFloat(sTemp);

		return fTemp;
	}

}
