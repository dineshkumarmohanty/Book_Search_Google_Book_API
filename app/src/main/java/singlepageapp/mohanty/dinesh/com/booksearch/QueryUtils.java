package singlepageapp.mohanty.dinesh.com.booksearch;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {



    private QueryUtils() {
    }


    public static ArrayList<Book> extractBook(String jsonString) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Book> books = new ArrayList<Book>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++)
            {

                JSONObject jsonObject1 = jsonArray.getJSONObject(i).getJSONObject("volumeInfo");
                String title = jsonObject1.getString("title");
                String des = jsonObject1.getString("publishedDate");
                String jsonString1 = jsonArray.getJSONObject(i).getString("selfLink");


                books.add(new Book(title , des , jsonString1));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the books JSON results", e);
        }

        // Return the list of earthquakes
        return books;
    }

}