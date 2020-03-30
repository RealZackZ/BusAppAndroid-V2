package com.example.busapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.busapp.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{


   // ArrayList<String> Buslist22;

    private ListView listView;
    private BusAdapter mAdapter;

    private String username1;
    private String password1;
    private Button login;

    int swaper = 0;


    public static String busIDfromClick = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       listView = (ListView) findViewById(R.id.buslistView);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);


//       username1 = usernameEditText.getText().toString();
//       password1 = passwordEditText.getText().toString();
//


        //getBuses();
        //adapter = new BusAdapter(this, R.layout.adapter, Buslist);
        //int listSize = Buslist.size();


//        username =findViewById(R.id.username);
//        password =findViewById(R.id.password);
//        login = findViewById(R.id.login);
//
//

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        listView.setClickable(true);
//
//        initItemClick();
//        //getBuses();
//        getBuses();
//        //addBus(7);
//
//        //deleteBus(10);
//
//        BusPOJO b1 = new BusPOJO("1","1","1","1","1","1");
//        BusPOJO b2 = new BusPOJO("2","2","2","2","2","2");
//        BusPOJO b3 = new BusPOJO("2","2","5","2","2","2");
//        BusPOJO b4 = new BusPOJO("2","2","3","2","2","2");

//        ArrayList<BusPOJO> buslist = new ArrayList<>();
//
//        buslist.add(b1);
//        buslist.add(b2);
//        buslist.add(b3);
//        buslist.add(b4);
//
//        getBuses();
//
//
//       BusAdapter busAdapter = new BusAdapter(this,buslist);

      // listView.setAdapter(busAdapter);



        initItemClick();
        // getBuses();




         getAvaliableBuses();


        Button deleteBus = findViewById(R.id.busDelete);

        deleteBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBus(busIDfromClick);

                getBuses();



            }
        });


        Button addBus = findViewById(R.id.buttonBook);


        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBus(busIDfromClick);
                getAvaliableBuses();
            }
        });



        Button settings = findViewById(R.id.settings);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (swaper % 2 ==1){
                    getAvaliableBuses();
                    swaper++;
                }
                else {
                    getBuses();
                    swaper++;
                }





            }
        });




    }


    private void initItemClick() {
        final ListView listView = findViewById(R.id.buslistView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                BusPOJO bus =(BusPOJO) listView.getItemAtPosition(position);
                busIDfromClick = "";
                busIDfromClick = bus.getBusID();
                Log.d("BusID Clicked", busIDfromClick);
            }
        });
    }





    public void getBuses(){



        String email;
        String password;
        //Your Ip Address here
        //mac ipconfig getifaddr en0
        String url = "http://10.0.0.237:5000/busreservationJ/";

        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();
        // Initialize a new JsonArrayRequest instance



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON


                        try{
                            // Loop through the array elements

                            ArrayList<BusPOJO> buslist = new ArrayList<>();

                            buslist.clear();
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject obj = response.getJSONObject(i);
                                BusPOJO busPOJO = new BusPOJO();



                                busPOJO.setBusID(obj.getString("busID"));
                                busPOJO.setBusNumber(obj.getString("busNumber"));
                                busPOJO.setBusDate(obj.getString("busDate"));
                                busPOJO.setBusFrom(obj.getString("busFrom"));
                                busPOJO.setBusTo(obj.getString("busTo"));
                                busPOJO.setPrice(obj.getString("price"));


                                //Buslist.clear();



                                buslist.add(busPOJO);

                                Log.d("BusList   ",buslist.get(i).toString());





                            }

                            BusAdapter busAdapter = new BusAdapter(getApplicationContext(),buslist);
                            busAdapter.notifyDataSetChanged();
                            listView.setAdapter(busAdapter);



//                            BusAdapter adapter = new BusAdapter(getApplicationContext(), R.layout.adapter, Buslist);
//                            listView.setAdapter(adapter);
                           // Log.d("TEST32423423", Buslist.get().getBusDate());

                           // adapter.notifyDataSetChanged();



                        }catch (JSONException e){
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){


                       // Log.e("ErrorResponse:", error.getLocalizedMessage());
                        // Do something when error occurred
//                        Snackbar.make(
//                                mCLayout,
//                                "Error...",
//                                Snackbar.LENGTH_LONG
//                        ).show();
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","zz","zz");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }

    public void getAvaliableBuses(){



        String email;
        final String password;
        //Your Ip Address here
        //mac ipconfig getifaddr en0
        String url = "http://10.0.0.237:5000/availablebusscheduleJ/";

        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();
        // Initialize a new JsonArrayRequest instance



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON


                        try{
                            // Loop through the array elements

                            ArrayList<BusPOJO> buslist = new ArrayList<>();

                            buslist.clear();
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject obj = response.getJSONObject(i);
                                BusPOJO busPOJO = new BusPOJO();



                                busPOJO.setBusID(obj.getString("busID"));
                                busPOJO.setBusNumber(obj.getString("busNumber"));
                                busPOJO.setBusDate(obj.getString("busDate"));
                                busPOJO.setBusFrom(obj.getString("busFrom"));
                                busPOJO.setBusTo(obj.getString("busTo"));
                                busPOJO.setPrice(obj.getString("price"));


                                //Buslist.clear();



                                buslist.add(busPOJO);

                                Log.d("BusList   ",buslist.get(i).toString());





                            }

                            BusAdapter busAdapter = new BusAdapter(getApplicationContext(),buslist);
                            busAdapter.notifyDataSetChanged();
                            listView.setAdapter(busAdapter);



//                            BusAdapter adapter = new BusAdapter(getApplicationContext(), R.layout.adapter, Buslist);
//                            listView.setAdapter(adapter);
                            // Log.d("TEST32423423", Buslist.get().getBusDate());

                            // adapter.notifyDataSetChanged();



                        }catch (JSONException e){
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

//
//                        Intent intentMain = new Intent(MainActivity.this ,
//                                LoginActivity.class);
//                        MainActivity.this.startActivity(intentMain);

                        // Log.e("ErrorResponse:", error.getLocalizedMessage());
                        // Do something when error occurred
//                        Snackbar.make(
//                                mCLayout,
//                                "Error...",
//                                Snackbar.LENGTH_LONG
//                        ).show();
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String u = "zz";
                String creds = String.format("%s:%s",u,u);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }



    public void addBus(String busID){



        String email;
        String password;
        //Your Ip Address here
        //mac ipconfig getifaddr en0
        String url = "http://10.0.0.237:5000/availablebusscheduleJ/addto/" + busID;

        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){


                        // Log.e("ErrorResponse:", error.getLocalizedMessage());
                        // Do something when error occurred
//                        Snackbar.make(
//                                mCLayout,
//                                "Error...",
//                                Snackbar.LENGTH_LONG
//                        ).show();
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","zz","zz");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
    }


    public void deleteBus(String busID){



        String email;
        String password;
        //Your Ip Address here
        //mac ipconfig getifaddr en0
        String url = "http://10.0.0.237:5000/busreservationJ/delete/" + busID;

        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.DELETE,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){


                        // Log.e("ErrorResponse:", error.getLocalizedMessage());
                        // Do something when error occurred
//                        Snackbar.make(
//                                mCLayout,
//                                "Error...",
//                                Snackbar.LENGTH_LONG
//                        ).show();
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s","zz","zz");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);


        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
    }








}
