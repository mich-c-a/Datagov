package sg.edu.rp.c346.id21025446.datagov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCarpark = findViewById(R.id.lvCarpark);
        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            String carparkNo;
            int totalLots;
            char lotType;
            int lotsAvail;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrCarpark = firstObj.getJSONArray("carpark_data");

                    for(int i = 0; i < jsonArrCarpark.length(); i++) {
                        JSONObject secondObj = jsonArrCarpark.getJSONObject(i);
                        carparkNo = secondObj.getString("carpark_number");
                        JSONArray jsonArrInfo = secondObj.getJSONArray("carpark_info");

                        for(int m = 0; m < jsonArrInfo.length(); m++) {
                            JSONObject thirdObj = jsonArrInfo.getJSONObject(m);
                            totalLots = Integer.parseInt(thirdObj.getString("total_lots"));
                            lotType =  thirdObj.getString("lot_type").charAt(0);
                            lotsAvail = Integer.parseInt(thirdObj.getString("lots_available"));
                            Carpark carpark = new Carpark(carparkNo, totalLots, lotType, lotsAvail);
                            alCarpark.add(carpark);
                        }
                    }
                }
                catch(JSONException e){

                }

                //POINT X – Code to display List View
                ArrayAdapter<Carpark> aa = new ArrayAdapter<Carpark>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lvCarpark.setAdapter(aa);

            }//end onSuccess
        });
    }//end onResume
}