package site.pushy.weather.citymanage;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.pushy.weather.R;
import site.pushy.weather.data.db.MyArea;
import site.pushy.weather.data.weather.Weather;
import site.pushy.weather.selectarea.SelectAreaActivity;
import site.pushy.weather.uitls.StorageUtil;
import site.pushy.weather.weatherinfo.WeatherInfoModel;

public class CityManageActivity extends AppCompatActivity implements CityManageContract.View,
        View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "CityManageActivity";
    private List<Weather> weatherList;
    private CityListAdapter cityListAdapter;

    @BindView(R.id.tb_city_manage) Toolbar mToolbar;
    @BindView(R.id.fb_city_manage) FloatingActionButton fbAdd;
    @BindView(R.id.recycler_city) RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_city_manage) SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
        ButterKnife.bind(this);
        initWidget();

        CityManagePresenter presenter = new CityManagePresenter(this, new WeatherInfoModel());
        presenter.getMyAreaWeatherInfo();
    }

    @Override
    public void setPresenter(CityManageContract.Presenter presenter) {

    }

    private void initWidget() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("城市管理");
        }
        fbAdd.setOnClickListener(this);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        /* 初始化城市管理列表 */
        weatherList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        cityListAdapter = new CityListAdapter(weatherList);
        mRecyclerView.setAdapter(cityListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_city_manage:
                Intent intent = new Intent(this, SelectAreaActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setLoading(boolean v) {
        swipeRefresh.setRefreshing(v);
    }

    @Override
    public void setMyAreaWeatherInfo(List<Weather> weathers) {
        weatherList.addAll(weathers);
        cityListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(false);
        Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
    }
}