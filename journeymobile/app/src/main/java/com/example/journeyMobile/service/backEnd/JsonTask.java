package com.example.journeyMobile.service.backEnd;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.journeyMobile.controller.map.MapActivityController;
import com.example.journeyMobile.model.journey.Route;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.service.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "JasonTask";

    private List<AsyncTask> tasks = new ArrayList<>();

    private Handler handler;
    private int code;

    private Route route;
    private HashMap<String, List<Spot>> facilities;



    public JsonTask(Handler handler, Route route, int code) {
        this.handler = handler;
        this.code = code;

        this.route = route;
    }

    public JsonTask(Handler handler, HashMap<String, List<Spot>> facilities, int code) {
        this.handler = handler;
        this.code = code;

        this.facilities = facilities;
    }

    @Override
    protected String doInBackground(String... url) {
        String data = "";

        try {
            data = HttpUtil.downloadUrl(url[0]);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        Log.d("json", data);

        return data;
    }


    @Override
    protected void onPostExecute(String result) {

        if (this.code == MapActivityController.RouteSearchComplete) {
            Log.i("charles", "Json:" + result);
            tasks.add(new ParseDirectJsonTask().execute(result));

        } else if (this.code == MapActivityController.RouteSearchComplete) {

        } else {
            return;
        }

    }

    private class ParseDirectJsonTask extends AsyncTask<String, Void, Route> {
        private String Tag = getClass().getName();

        @Override
        protected Route doInBackground(String... jsonData) {
            JSONObject jsonObject;

            Route route = null;
            try {
                jsonObject = new JSONObject(jsonData[0]);
                DirectionJsonParser jsonParser = new DirectionJsonParser();
                route = jsonParser.getPolyOption(jsonObject);

            } catch (JSONException e) {
                Log.d(Tag, e.getMessage());
            }

            Log.d("Charles", "fin route");

            return route;
        }

        @Override
        protected void onPostExecute(Route result) {
//            super.onPostExecute(route);
            Log.d("Charles", "post");

            route.copy(result);

            Log.d("Charles", "finished task");
            Message message = new Message();
            message.what = code;
            handler.sendMessage(message);

        }

        //        @Override
//        protected void onPostExecute(Route result) {
////            super.onPostExecute(result);
//            Log.d("Charles", "post");
//
//            if (route == null) route = new Route();
//            route.copy(result);
//
//            Log.d("Charles", "finished task");
//        }
    }

    private class ParseFacilitiesJason extends AsyncTask<String, Void, List<Spot>> {

        @Override
        protected List<Spot> doInBackground(String... strings) {
            return null;
        }
    }

    public enum Code{
        RouteCode(1001), FacilitiesCode(1002);

        private  final int code;
        Code(int code) {
            this.code = code;
        }

        public final int getCode() {
            return code;
        }
    }
}
