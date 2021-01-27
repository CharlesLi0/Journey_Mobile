package com.example.journeyMobile.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.stream.UrlLoader;
import com.example.journeyMobile.R;
import com.example.journeyMobile.model.location.Spot;
import com.example.journeyMobile.model.mock.MockData;
import com.example.journeyMobile.model.viewModel.JourneyViewModel;
import com.example.journeyMobile.service.util.CalendarUtils;
import com.example.journeyMobile.view.fragment.HomepageEventFragment;
import com.example.journeyMobile.view.fragment.HomepageWeatherFragment;
import com.example.journeyMobile.view.fragment.pickers.DatePickerFragment;
import com.example.journeyMobile.view.fragment.pickers.TimePickerFragment;
import com.example.journeyMobile.view.widget.EventPopupListener;
import com.example.journeyMobile.view.widget.EventPopupWindow;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends BaseActivity implements DatePickerFragment.DatePickerListener
        , HomepageEventFragment.HomepageEventListener {

    private static final String TAG = "HomepageActivity";

    public static final String EXTRA_DATA = "extra_data";
    public static final Integer REQUEST_CODE = 1;

    private JourneyViewModel journeyViewModel;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Button mNavButton;
    private FloatingActionButton mFab;

    private HomepageEventFragment homepageEventFragment;
    private HomepageWeatherFragment homepageWeatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_home);

        journeyViewModel = ViewModelProviders.of(this).get(JourneyViewModel.class);

        mDrawerLayout = findViewById(R.id.homepage_drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavButton = findViewById(R.id.homepage_nav_button);
        mFab = findViewById(R.id.homepage_fab);

        // event fragment
        homepageEventFragment = new HomepageEventFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homepage_event_fragment, homepageEventFragment);
        transaction.commit();

        // weahter fragment
        homepageWeatherFragment = (HomepageWeatherFragment) getSupportFragmentManager().findFragmentById(R.id.homepage_weather_fragment);
        homepageWeatherFragment.setDate(new Date());

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_car_parking:
                        MapActivity.actionStart(HomeActivity.this, MapActivity.MapType.CARPARK);
                        break;

                    case R.id.nav_facility:
                        MapActivity.actionStart(HomeActivity.this, MapActivity.MapType.FACILITY);
                        break;

                    case R.id.nav_attraction_events:
                        showAttractionEvents();
                        break;

                    case R.id.nav_daily_plan:
                        MapActivity.actionStart(HomeActivity.this, MapActivity.MapType.PLAN);
                        break;

                    default:
                }
                return true;
            }
        });

        mNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MapActivity.actionStart(HomeActivity.this, MapActivity.MapType.ROUTE);
                toGoogleMap();
            }
        });
    }

    private void toGoogleMap() {
        Intent intent = new Intent();
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    private void showAttractionEvents() {
        EventPopupListener eventPopupListener = new EventPopupListener( ) {
            @Override
            public void addEventToList(Spot spot,Date date) {
                if (journeyViewModel.addSpot(spot, date))
                    Toast.makeText(HomeActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "the plan already have it", Toast.LENGTH_SHORT).show();
            }
        };

        MockData mockData = MockData.getSingletonInstance(getApplicationContext());
        EventPopupWindow eventPopupWindow = new EventPopupWindow(this, mockData.getEventList()
                , eventPopupListener);
        eventPopupWindow.show(mDrawerLayout);

    }

    public void showTimePickerDialog(View v) {
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment datePickerFragment = new DatePickerFragment(this);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setTime(String selectedTime) {
        TextView departureTime = findViewById(R.id.hour_minute);
        departureTime.setText(selectedTime);
    }

    public void setDayOfMonth(String dayOfMonth) {
        TextView departureDay = findViewById(R.id.day_of_month_text);
        departureDay.setText(dayOfMonth);
    }

    public void setDayOfWeek(String dayOfWeek) {
        TextView departureWeek = findViewById(R.id.week_text);
        departureWeek.setText(dayOfWeek);
    }

    public void setMonth(String month) {
        TextView departureMoth = findViewById(R.id.month_text);
        departureMoth.setText(month);
    }

    public void expandEventList() {
        FrameLayout frameLayout = findViewById(R.id.homepage_event_fragment);
        int height = frameLayout.getMeasuredHeight();
        ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
        params.height = height;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        homepageEventFragment.setDate(new Date());

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        setTime(CalendarUtils.getFormattedTime(hourOfDay, minute));
        setDayOfMonth(String.valueOf(dayOfMonth));
        setDayOfWeek(CalendarUtils.getDayOfWeek(year, month, dayOfMonth));
        setMonth(CalendarUtils.getMonth(year, month, dayOfMonth));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDateSet(int year,int month,int dayOfMonth) {
        // update the date
        setDayOfMonth(String.valueOf(dayOfMonth));
        setDayOfWeek(CalendarUtils.getDayOfWeek(year, month, dayOfMonth));
        setMonth(CalendarUtils.getMonth(year, month, dayOfMonth));

        // set the the date on the event list
        homepageEventFragment.setDate(new Date(year - 1900, month, dayOfMonth));
        homepageWeatherFragment.setDate(new Date(year - 1900, month, dayOfMonth));
    }

    @Override
    public void showEventList() {
//        showAttractionEvents();
    }
}
