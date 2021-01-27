package com.example.journeyMobile.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.journeyMobile.R;
import com.example.journeyMobile.controller.map.MapActivityController;
import com.example.journeyMobile.service.util.PermissionUtil;
import com.example.journeyMobile.view.fragment.FacilitiesFragment;
import com.example.journeyMobile.view.fragment.PlanListFragment;
import com.example.journeyMobile.view.fragment.UpperSingelSearchFragment;
import com.example.journeyMobile.view.fragment.UpperTwoSearchFragment;
import com.example.journeyMobile.view.layout.DragRelativeLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;


public class MapActivity extends BaseActivity implements OnMapReadyCallback
        , UpperTwoSearchFragment.OnUpperDoubleSearchListener
        , FacilitiesFragment.OnFacilitiesFragmentListener
        , UpperSingelSearchFragment.OnSingleSearchFragmentListener
        , PlanListFragment.OnPlanListFragmentListener {

    private final String TAG = getClass().getName();
    // start the activity with the code and call back
    public final static int START_CODE = 1001;
    public final static int END_CODE = 1002;
    public final static int SINGLE_SEARCH_CODE = 1003;

    // the tool on the map base on the map type
    public enum MapType {
       FACILITY, CARPARK, ROUTE, PLAN;
    }

    // the current type of map
    private MapType mapType;
    // store all the view
    private ViewHolder viewHolder;
    // controller
    private MapActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initial controller
        controller = new MapActivityController(this);
        setContentView(R.layout.activity_map);

        // initial viewHolder
        this.viewHolder = new ViewHolder();
        viewHolder.mMapView.onCreate(savedInstanceState);
        viewHolder.mMapView.onResume();
        controller.setViewHolder(viewHolder);

        // map initilizer
        try {
            MapsInitializer.initialize(getApplicationContext( ));
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
        viewHolder.mMapView.getMapAsync(this);

        // change the coordination of "My location" button
        View locationButton = ((View) viewHolder.mMapView.findViewById(Integer.parseInt("1"))
                .getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        // position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        rlp.setMargins(0, 320, 50, 0);

        // initial the tool on the frame
        Intent intent = getIntent();
        mapType = (MapType) intent.getSerializableExtra("MapType");
        controller.inititial(mapType);

        // initial the Place API
        String apiKey = getString(R.string.google_api_key);
        if (!Places.isInitialized()){
            Places.initialize(this, apiKey);
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed( );
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        // the start on the double search
        if (requestCode == START_CODE && data != null
                && Autocomplete.getStatusFromIntent(data).isSuccess()){

            Place place = Autocomplete.getPlaceFromIntent(data);
            controller.setStartPoint(place.getAddress(), place.getLatLng());

         // the end on the double search
        } else if (requestCode == END_CODE && data != null
                && Autocomplete.getStatusFromIntent(data).isSuccess()){

            Place place = Autocomplete.getPlaceFromIntent(data);
            controller.setEndPoint(place.getAddress(), place.getLatLng());

         // on the singel search
        } else if (requestCode == SINGLE_SEARCH_CODE && data != null
                && Autocomplete.getStatusFromIntent(data).isSuccess()){

            Place place = Autocomplete.getPlaceFromIntent(data);
            controller.setSingleSearch(place.getAddress(), place.getLatLng());
        }
    }

    @Override
    protected void onResume() {
        super.onResume( );
        viewHolder.mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause( );
        viewHolder.mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop( );
        viewHolder.mMapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory( );
        viewHolder.mMapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        viewHolder.mMapView.onDestroy();
    }


    /**
     * store all the view
     */
    public class ViewHolder {
        public GoogleMap gMap;
        public MapView mMapView;
        public DragRelativeLayout dragRelativeLayout;

        private ViewHolder() {
            mMapView = findViewById(R.id.mapView);

            dragRelativeLayout = findViewById(R.id.dragRL_mapActivity);
            dragRelativeLayout.initial(R.id.bottomTool_map, false, true
                    , DragRelativeLayout.AbsorbType.bottom, 0.1 );

            
        }
    }

    /**
     * start the activity
     * @param context from the context
     * @param type map type
     */
    public static void actionStart(Context context, MapType type) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra("MapType", type);

        context.startActivity(intent);

    }

    @Override
    @SuppressLint("MissingPermission")
    public void onMapReady(GoogleMap googleMap) {
        viewHolder.gMap = googleMap;
        viewHolder.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-37.8078, 144.963), 15f));

        // permission
        if (PermissionUtil.check_Access_Fine_Loction(this)
                && PermissionUtil.check_ACCESS_COARSE_LOCATION(this)) {

            // enable My Locate and traffic layer
            viewHolder.gMap.setMyLocationEnabled(true);
            viewHolder.gMap.setOnMyLocationButtonClickListener(controller);
            viewHolder.gMap.setOnMyLocationClickListener(controller);
//            viewHolder.gMap.setTrafficEnabled(true);
        } else {
            Toast.makeText(this, "do not have permission of location"
                    , Toast.LENGTH_SHORT).show();
        }
        // set the onClick for the marker and polyLine
        viewHolder.gMap.setOnPolylineClickListener(controller);
        viewHolder.gMap.setOnPolygonClickListener(controller);
        viewHolder.gMap.setOnMarkerClickListener(controller);

        // when the mapType is plan, should set the observer of the view model
        if (mapType == MapType.PLAN) controller.viewModelObserver();
    }

    @Override
    public void addressAutoCompleted(EditText editText, int code) {
        controller.toAutocompleteAddress(editText, code);
    }

    @Override
    public void createRoute() {
        controller.createRoute();
    }

    @Override
    public void switchOfFacilities(boolean isShowBBq,boolean isShowToilet,boolean isShowBin,boolean isShowRestaurant,boolean isShowSupermarket) {
        controller.clearMap();
        controller.addStartPinOnMap();

        // if around the rye then show
        LatLng latLng = viewHolder.gMap.getCameraPosition().target;
        if (latLng.latitude < -38.270 && latLng.latitude > -38.410
                && latLng.longitude < 144.90 && latLng.longitude > 144.74) {

            controller.showBBQ(isShowBBq);
            controller.showBin(isShowBin);
            controller.showToilet(isShowToilet);
        } else {
            Toast.makeText(this, "do not have any result", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void refreshData() {
        controller.refreshData();
    }
}
