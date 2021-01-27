package com.example.journeyMobile.controller.map;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.journeyMobile.model.mock.MockData;
import com.example.journeyMobile.R;
import com.example.journeyMobile.model.journey.DailyPlan;
import com.example.journeyMobile.model.journey.Route;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.model.viewModel.JourneyViewModel;
import com.example.journeyMobile.service.backEnd.JsonTask;
import com.example.journeyMobile.service.util.UrlCreatorUtil;
import com.example.journeyMobile.view.MapActivity;
import com.example.journeyMobile.view.fragment.FacilitiesFragment;
import com.example.journeyMobile.view.fragment.PlanListFragment;
import com.example.journeyMobile.view.fragment.UpperSingelSearchFragment;
import com.example.journeyMobile.view.fragment.UpperTwoSearchFragment;
import com.example.journeyMobile.view.widget.DetailPopupWindow;
import com.example.journeyMobile.view.widget.DetailPopupListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.*;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


public class MapActivityController implements View.OnClickListener,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnPolylineClickListener
        , GoogleMap.OnPolygonClickListener, GoogleMap.OnMyLocationButtonClickListener
        , GoogleMap.OnMyLocationClickListener
        , DetailPopupListener {

    private final String TAG = getClass().getName();
    public static final int RouteSearchComplete = 3001;

    // color for the polyLIine
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    // setting of the polyline
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // direct Api Key
    public String DirectAPIKey;
    // the defrault zoom of the map
    private static final float DEFAULT_ZOOM = 12f;

    private MapActivity activity;
    private MapActivity.ViewHolder viewHolder;
    private MapActivity.MapType mapType;

    // the fragment on the map
    private FacilitiesFragment facilitiesFragment;
    private UpperTwoSearchFragment upperTwoSearchFragment;
    private UpperSingelSearchFragment upperSingelSearchFragment;
    private PlanListFragment planListFragment;

    // store the search spot and route get from the Http
    private Spot startPoint;
    private Spot endPoint;
    private Route route;
    private Hashtable<String, List<Spot>> facilities;
    private Hashtable<String, Spot> spotList = new Hashtable<>();

    // plan list
    private JourneyViewModel journeyViewModel;

    // a mock data
    private MockData mockData;

    // fields for the Place API
    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME
            , Place.Field.LAT_LNG, Place.Field.ADDRESS);

    // call back from finishing the AsyncTask
    Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;

            switch (what) {
                // call back after get the route information from the http
                case  RouteSearchComplete:
                    if (route == null) {
                        Toast.makeText(activity, "do not have any dat", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // draw the poly line on the map
                    drawPolyLine(route.getRoute());
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * constructor
     * @param activity the MapActivity
     */
    public MapActivityController(MapActivity activity) {
        this.activity = activity;
        DirectAPIKey = activity.getResources().getString(R.string.google_api_key_direction);
    }

    /**
     * set the viewHolder
     * @param viewHolder the viewHolder of the MapActivity
     */
    public void setViewHolder(MapActivity.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    /**
     * initial the tool such as the search bar bash on the type
     * @param type the type of the map
     */
    public void inititial(MapActivity.MapType type) {
        // initial the view model
        if (journeyViewModel == null) {
            journeyViewModel = ViewModelProviders.of(activity).get(JourneyViewModel.class);
        }

        // change the fragment based on the type
        mapType = type;
        if (type == MapActivity.MapType.FACILITY) {
            facilityTool();

        } else if (type == MapActivity.MapType.CARPARK) {
            carparkTool();

        } else if (type == MapActivity.MapType.PLAN) {
            planTool();

        } else if (type == MapActivity.MapType.ROUTE) {
            routeTool();
        }
    }

    /**
     * have two search on the top of the map
     */
    private void routeTool() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        if (upperTwoSearchFragment == null) {
            upperTwoSearchFragment = new UpperTwoSearchFragment();

        }

        replaceUpperTool(transaction, upperTwoSearchFragment);

        transaction.commit();
    }

    /**
     * have singel search on the top on the map , and some button on the right of the map
     */
    private void facilityTool() {
        // upper tool
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        if (upperSingelSearchFragment == null) {
            upperSingelSearchFragment = new UpperSingelSearchFragment();
            replaceUpperTool(transaction, upperSingelSearchFragment);
        } else {
            replaceUpperTool(transaction, upperSingelSearchFragment);
        }

        // left tool
        if (facilitiesFragment == null) {
            facilitiesFragment = new FacilitiesFragment();
            showFacilitiesTool(transaction, facilitiesFragment);
        } else {
//            showFacilitiesTool(transaction, facilitiesFragment);
            transaction.show(facilitiesFragment);
        }

        transaction.commit();
    }

    /**
     * have single search on the top of the map
     */
    private void carparkTool() {
        // upper tool
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        if (upperSingelSearchFragment == null) {
            upperSingelSearchFragment = new UpperSingelSearchFragment();

        }

        replaceUpperTool(transaction, upperSingelSearchFragment);


        // left tool
        if (facilitiesFragment != null) transaction.hide(facilitiesFragment);

        transaction.commit();
    }

    /**
     * have a single search on the top on the map and a plan list on the bottom
     */
    private void planTool() {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        if (planListFragment == null) {
            planListFragment = new PlanListFragment();

        }

        if (upperSingelSearchFragment == null) {
            upperSingelSearchFragment = new UpperSingelSearchFragment();

        }

        replaceUpperTool(transaction, upperSingelSearchFragment);
        replaveBottomTool(transaction, planListFragment);

        // left tool
        if (facilitiesFragment != null) transaction.hide(facilitiesFragment);

        transaction.commit();


    }

    /**
     * replace the fragment on the top
     * @param transaction transaction
     * @param fragment the one to replace
     */
    private void replaceUpperTool(FragmentTransaction transaction, Fragment fragment) {
        transaction.replace(R.id.upper_search_tool, fragment).addToBackStack(null);
//        transaction.commit();


    }

    /**
     * replace the fragemnt on the bottom
     * @param transaction transaction
     * @param fragment the one to replace
     */
    private void replaveBottomTool(FragmentTransaction transaction, Fragment fragment) {
        transaction.replace(R.id.bottomTool_map, fragment).addToBackStack(null);
//        transaction.commit();
    }

    /**
     * show the facilities
     * @param transaction transaction
     * @param fragment fragment to replace
     */
    private void showFacilitiesTool(FragmentTransaction transaction, Fragment fragment) {

        transaction.replace(R.id.facilities_tool, fragment).addToBackStack(null);
//      transaction.commit();

    }

    /**
     * set the observer of the view model for easy refresh the data of the plan list and some pin on the map
     */
    public void viewModelObserver() {
        // refresh the data on the map and the plan list
        journeyViewModel.getDailiyPlanLiveData().observe(activity,new Observer<DailyPlan>( ) {
            @Override
            public void onChanged(@Nullable DailyPlan dailyPlan) {
                // data of plan list
                planListFragment.getController().setListOnAdapter(dailyPlan.getSpots());
                planListFragment.getController().setDate(dailyPlan.getStartDateTime());

                // some pin on the map
                if (dailyPlan.getSpots().size() != 0)
                    moveCamera(dailyPlan.getSpots().get(0).getCoordination(), DEFAULT_ZOOM);
                addMarkOnMap(dailyPlan.getSpots());
            }
        });
    }

    /**
     * refresh the view model after update the data
     */
    public void refreshData() {
        if (journeyViewModel != null) journeyViewModel.refreshData();
    }

    @Override
    public void onClick(View v) {
        v.setClickable(false);



        v.setClickable(true);
    }

    // clear the added information on the map
    public void clearMap() {
        viewHolder.gMap.clear();
    }


    /**
     * set poly line on the map base on the lineoptions
     * @param lineOptions lineOptions
     */
    private void drawPolyLine(PolylineOptions lineOptions) {
        lineOptions.width(20);

        // Changing the color polyline according to the mode
        lineOptions.color(Color.YELLOW);

        viewHolder.gMap.addPolyline(lineOptions);
    }

    /**
     * draw poly line on the map base on the map
     * @param coordination list of coordination
     */
    private void drawPolyLine(List<LatLng> coordination) {
        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.addAll(coordination);

        lineOptions.width(20);

        // Changing the color polyline according to the mode
        lineOptions.color(Color.BLUE);

        viewHolder.gMap.addPolyline(lineOptions);
    }

    /**
     * draw poly line on the map base on the map
     * @param route list of coordination
     */
    public void drawPolyLine(Route route) {
        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.addAll(route.getRoute());

        lineOptions.width(20);

        // Changing the color polyline according to the mode
        lineOptions.color(Color.GREEN);

        viewHolder.gMap.addPolyline(lineOptions);
    }

    /**
     * get the current zoom from the map
     * @return the float presented the zoom of the map
     */
    public float getCurrentZoom() {

        return viewHolder.gMap.getCameraPosition().zoom;
    }

    /**
     * add mark on the map
     * @param name title of the mark
     * @param latLng coordination of the mark
     */
    private void addMarkOnMap(String name, LatLng latLng) {
//        clearMap();

        // property of the markerOptions
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(name);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        // add markoption on the map
        viewHolder.gMap.addMarker(markerOptions);
    }

    /**
     * add mark on the map
     * @param name title of the mark
     * @param latLng coordination of the mark
     * @param drawable the drawable of the mark
     */
    private void addMarkOnMap(String name, LatLng latLng, int drawable) {

//        clearMap();

        // property of the markerOptions
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(name);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(drawable));

        // add markoption on the map
        viewHolder.gMap.addMarker(markerOptions);
    }

    /**
     * add mark on map
     * @param list list on spot
     */
    private void addMarkOnMap(List<Spot> list) {
        // add the spot on the map
        for (Spot spot : list) {
            spotList.put(spot.getTitle(), spot);
            addMarkOnMap(spot.getTitle(), spot.getCoordination());
        }
    }

    /**
     *
     * @param list list of spot to add on the map
     * @param drawable the drawable of the markOptions
     */
    private void addMarkOnMap(List<Spot> list, int drawable) {
        // add the spot on the map
        for (Spot spot : list) {
            spotList.put(spot.getTitle(), spot);
            addMarkOnMap(spot.getTitle(), spot.getCoordination(), drawable);
        }
    }

    /**
     * start the Place API for get the address
     * @param editText the editText to be click
     * @param code code for call back
     */
    public void toAutocompleteAddress(EditText editText, int code) {
        String text = editText.getText().toString();
//        text = returuAddress(text);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .setInitialQuery(text)
                .build(activity);
        activity.startActivityForResult(intent, code);
    }

    /**
     * set the text on the singel search
     * @param name the title of the spot
     * @param latLng the coordination of the spot
     */
    public void setSingleSearch(String name, LatLng latLng) {
        if (name == null || latLng == null) return;

        if (startPoint == null) startPoint = new Spot(name, latLng);
        else {
            startPoint.setTitle(name);
            startPoint.setCoordination(latLng);
        }
        // move carmer aand add mark on map
        moveCamera(latLng);
        clearMap();
        addMarkOnMap(name, latLng);

        if (mockData == null) mockData = MockData.getSingletonInstance(activity);

        // is carpark type and the locaiton is around the rye
        if (mapType == MapActivity.MapType.CARPARK && latLng.latitude < -38.270 && latLng.latitude > -38.410
                && latLng.longitude < 144.90 && latLng.longitude > 144.74) {
            spotList.clear();
            for (Spot park : mockData.getParkingList()) {
                spotList.put(park.getTitle(), park);
            }
            addMarkOnMap(mockData.getParkingList(), R.drawable.carparking_location);
        }

        upperSingelSearchFragment.getController().setSearchText(name);
    }


    /**
     * show the start point on the map
     */
    public void addStartPinOnMap() {
        if (startPoint == null) return;
        addMarkOnMap(startPoint.getTitle(), startPoint.getCoordination());
    }

    /**
     * set end point
     * @param name title of the spot
     * @param latLng coordination of the spot
     */
    public void setEndPoint(String name, LatLng latLng) {
        if (name == null || latLng == null) return;

        clearMap();
        moveCamera(latLng);
        addMarkOnMap(name, latLng);
        upperTwoSearchFragment.getController().setToText(name);

        if (endPoint == null) {
            endPoint = new Spot(name, latLng);
        } else {
            endPoint.setTitle(name);
            endPoint.setCoordination(latLng);
        }

    }

    /**
     * set start point
     * @param name the title of the spot
     * @param latLng the coordination of the spot
     */
    public void setStartPoint(String name, LatLng latLng) {
        if (name == null || latLng == null) return;

        clearMap();
        moveCamera(latLng);
        addMarkOnMap(name, latLng);
        upperTwoSearchFragment.getController().setFromText(name);

        // set the start point
        if (startPoint == null) {
            startPoint = new Spot(name, latLng);
        } else {
            startPoint.setTitle(name);
            startPoint.setCoordination(latLng);
        }
    }

    /**
     * create the route from the start to the end
     */
    public void createRoute(){
        viewHolder.gMap.setTrafficEnabled(true);

        if (startPoint != null && endPoint != null) {
            String url = UrlCreatorUtil.getDirectionsUrl(startPoint.getCoordination(), endPoint.getCoordination()
                    , DirectAPIKey);
            route = new Route();
            new JsonTask(handler, route, RouteSearchComplete).execute(url);
//             new DownloadJasonTask().execute(url);

        } else {
            Toast.makeText(activity, "please fill the start and end", Toast.LENGTH_LONG).show();
        }
    }

    public void showBBQ(boolean showBbq){
        if (!showBbq) return;
        if (mockData == null) mockData = MockData.getSingletonInstance(activity);

//        addMarkOnMap(mockData.getBbqList());
        addMarkOnMap(mockData.getBbqList(), R.drawable.bbq_location);
    }

    public void showBin(boolean showBin){
        if (!showBin) return;
        if (mockData == null) mockData = MockData.getSingletonInstance(activity);

//        addMarkOnMap(mockData.getBinList());
        addMarkOnMap(mockData.getBinList(), R.drawable.rubbish_bin_location);
    }

    public void showToilet(boolean showToilet) {
        if (!showToilet) return;
        if (mockData == null) mockData = MockData.getSingletonInstance(activity);

//        addMarkOnMap(mockData.getToiletList());
        addMarkOnMap(mockData.getToiletList(), R.drawable.toilet_location);
    }

    /**
     * move the camera
     * @param latLng latLng
     * @param zoom zoom
     */
    private void moveCamera(LatLng latLng, float zoom){
        if (viewHolder.gMap != null){
            viewHolder.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        }
    }

    /**
     * move the camera
     * @param latLng latlng
     */
    private void moveCamera(LatLng latLng){
        if (viewHolder.gMap != null){
            viewHolder.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
        }
    }

    private void showPopupWindow(Spot spot) {

        DetailPopupWindow popupWindow = new DetailPopupWindow(activity, spot, this);
        popupWindow.show(viewHolder.mMapView);


    }

    /**
     * use to change property of the polyLine
     * @param polyline target
     */
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone), 10));
                break;

            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYGON_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // get the title from the marker
        String title = marker.getTitle();
        if (title == null) return false;

        // compare the title with the start, end and spolist
        Spot spot = null;
        if (startPoint != null && startPoint.getTitle().contains(title)) spot = startPoint;
        else if (endPoint != null && endPoint.getTitle().contains(title)) spot = endPoint;
        else spot = spotList.get(marker.getTitle());

        // if null then return else show the popup
        if (spot != null)
            showPopupWindow(spot);
        return false;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(activity, "my Location Button clicked", Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        moveCamera(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }


        if (polyline.getTag() != null) {
            Toast.makeText(activity, "Route type " + polyline.getTag().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addSpotToList(Spot spot) {
        if (!journeyViewModel.addSpot(spot))
            Toast.makeText(activity, "it is already on the plan list", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(activity, "successfully adding to the plan list", Toast.LENGTH_SHORT).show();

        journeyViewModel.refreshData();
    }
}
