package com.emagrorrim.pm25;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.emagrorrim.pm25.Area.AreaInfo;
import com.emagrorrim.pm25.NetRequest.CityInforFetcher;
import java.util.List;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by apple on 15/12/22.
 */
public class DetialActivity extends Activity {
    private final String className = "DetialActivity";

    private ListView listView;
    private TextView titleView;
    private CityInforFetcher fetcher;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = this.getIntent().getExtras();
        String cityName = bundle.getString("cityName");
        Log.e(className, cityName);
        initUI();
        titleView.setText(cityName);
        queryCityInfo(cityName);
    }

    private void initUI() {
        initFunctionalComponents();
        initListView();
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("Loading");
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetialActivity.this.finish();
            }
        });
    }

    private void initFunctionalComponents() {
        fetcher = CityInforFetcher.sharedFetcher();
        titleView = (TextView)findViewById(R.id.activity_title);
    }

    private void initListView() {
        listView = (ListView)findViewById(R.id.list_view);
//
//        AreaInfo[] areaInfos = new AreaInfo[2];
//        areaInfos[0] = new AreaInfo("123","124",215);
//        areaInfos[1] = new AreaInfo("老顾客","千万人",124);
//        listView.setAdapter(new AreaInfoList(areaInfos, this));
    }

    private void queryCityInfo(String cityName) {
        if (fetcher == null) {
            Log.e(className, "fetcher does not init");
        }else {
            loadingDialog.show();
            fetcher.requestPM25(cityName, new Callback<List<AreaInfo>>() {
                @Override
                public void onResponse(Response<List<AreaInfo>> response, Retrofit retrofit) { requestSucceed(response.body());}
                @Override
                public void onFailure(Throwable t) { requestFailed(); }
            });
        }
    }

    private void requestSucceed(List<AreaInfo> data) {
        AreaInfo[] areaInfos = new AreaInfo[data.size()];
        listView.setAdapter(new AreaInfoList(data.toArray(areaInfos), this));
        loadingDialog.dismiss();
    }

    private void requestFailed() {
        Toast.makeText(DetialActivity.this,"Request Failed",Toast.LENGTH_SHORT).show();
        loadingDialog.dismiss();
    }
}
