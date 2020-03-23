package com.example.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.odsay.odsayandroidsdk.API;
import com.odsay.odsayandroidsdk.ODsayData;
import com.odsay.odsayandroidsdk.ODsayService;
import com.odsay.odsayandroidsdk.OnResultCallbackListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class PlanInitialActivity extends AppCompatActivity {
    // github test
    Button addButton, placeSearch;
    Button withFriends;

    ImageView calculation;
    OnResultCallbackListener onResultCallbackListener;
    ODsayService odsayService;

    ListView listView;
    ArrayList<PlanInitialSubItem> items = new ArrayList<PlanInitialSubItem>();
    PlanSubAdapter adapter = new PlanSubAdapter();

    String time, name, memo;
    int type;
    double x, y;
    int transportExp;

    DeleteDialog oDialog;
    TransportDialog tDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_plan_initial);

        //버전 상향에 따른 네트워크 연결 조정
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        listView = (ListView) findViewById(R.id.con);
        listView.setAdapter(adapter);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlanInputActivity.class);
                startActivityForResult(intent, 101);

            }
        });

        // 싱글톤 생성, Key 값을 활용하여 객체 생성
        odsayService = ODsayService.init(getApplicationContext(), "TNRXyW/xqRXnX/zMI8tA2fbn4N+HPUuaIySAow33Qvs");
        // 서버 연결 제한 시간(단위(초), default : 5초)
        odsayService.setReadTimeout(5000);
        // 데이터 획득 제한 시간(단위(초), default : 5초)
        odsayService.setConnectionTimeout(5000);

        calculation = (ImageView) findViewById(R.id.calculation);
        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        placeSearch = (Button) findViewById(R.id.placeSearch);
        placeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlaceSearchActivity.class);

                startActivityForResult(intent, 102);
            }
        });

        withFriends = (Button) findViewById(R.id.withFriends);
        withFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WithFriendsActivity.class);
                startActivity(intent);
            }
        });

        oDialog = new DeleteDialog(this);

    }

    class PlanSubAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PlanInitialSubItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            PlanInitialSubItemView view= new PlanInitialSubItemView(getApplicationContext());
            final PlanInitialSubItem item = items.get(position);
            view.setPlaceTime(item.getPlaceTime());
            view.setPlaceName(item.getPlaceName());
            view.setPlaceMemo(item.getPlaceMemo());

            ImageButton del = (ImageButton) view.findViewById(R.id.deleteButton);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oDialog.setCancelable(false);
                    oDialog.show();

                    oDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            boolean del = oDialog.getDelete();
                            if(del){
                                items.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });

                }
            });

            final ImageButton addT = (ImageButton) view.findViewById(R.id.transport_ic);
            final TextView transText = (TextView) view.findViewById(R.id.transportText);
            addT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tDialog = new TransportDialog(PlanInitialActivity.this);
                    tDialog.callFunction(item.getTransport());
                    tDialog.show();
                    tDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            int transport = tDialog.getTransport();
                            if(transport == 1){
                                addT.setBackgroundResource(R.drawable.ic_walk_24px);
                                transText.setText("Walk");
                            }

                            if(transport == 2){
                                addT.setBackgroundResource(R.drawable.ic_bus_24px);
                                transText.setText("Bus");
                            }

                            if(transport == 3){
                                addT.setBackgroundResource(R.drawable.ic_subway_24px);
                                transText.setText("Subway");
                            }

                            if(transport == 4){
                                addT.setBackgroundResource(R.drawable.ic_taxi_24px);
                                transText.setText("Taxi");
                            }

                            if(transport == 5){
                                addT.setBackgroundResource(R.drawable.ic_car_24px);
                                transText.setText("Car");
                            }
                            item.setTransport(transport);
                        }

                    });
                }
            });

            return view;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(intent != null){
                int hour = intent.getIntExtra("hour", 0);
                int min = intent.getIntExtra("min", 0);
                String ap;
                String sHour, sMin;
                if(hour > 12) {
                    ap = "PM";
                    hour -= 12;
                }
                else
                    ap = "AM";

                if(hour<10)
                   sHour = "0"+hour;
                else
                    sHour = String.valueOf(hour);

                if(min<10)
                    sMin = "0"+hour;
                else
                    sMin = String.valueOf(min);

                time = ap+ " "+sHour+":"+sMin;
                name = intent.getStringExtra("title");
                memo = intent.getStringExtra("memo");
                type = intent.getIntExtra("type", 1);
                x = intent.getDoubleExtra("x", 0);
                y = intent.getDoubleExtra("y", 0);
                adapter.addItem(new PlanInitialSubItem(time, name, memo, 1, type, x, y));
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void totalExp(){
        double[] xList = new double[adapter.getCount()];
        double[] yList = new double[adapter.getCount()];
        int[] transportList = new int[adapter.getCount()];
        for(int i=0; i<adapter.getCount(); i++){
            xList[i] = items.get(i).getX();
            yList[i] = items.get(i).getY();
            transportList[i] = items.get(i).getTransport();
        }

        for(int j=1; j<adapter.getCount(); j++){
            transportExp = returnTransportExp(xList[j-1], yList[j-1], xList[j], yList[j], transportList[j-1]);
        }


    }

    public int returnTransportExp(double SX, double SY, double EX, double EY, int transport){
        // 콜백 함수 구현
        OnResultCallbackListener onResultCallbackListener = new OnResultCallbackListener() {
            // 호출 성공 시 실행
            @Override
            public void onSuccess(ODsayData odsayData, API api) {
                try {
                    // API Value에 API 호출 메소드 명 입력.
                    if (api == API.SEARCH_PUB_TRANS_PATH) {
                        JSONArray path = odsayData.getJson().getJSONObject("result").getJSONArray("path");
                        JSONObject info = path.getJSONObject(0).getJSONObject("info");
                        int payment = info.getInt("payment");
                        Log.d("payment : %s", String.valueOf(payment));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // 호출 실패 시 실행
            @Override
            public void onError(int i, String s, API api) {
                if (api == API.SEARCH_PUB_TRANS_PATH) {
                }
            }
        };
        // API 호출
        odsayService.requestSearchPubTransPath("126.926493082645", "37.6134436427887", "127.126936754911", "37.5004198786564", "0", "0", "1", onResultCallbackListener);
        //객체 초기화 -> (출발지 x좌표, 출발지 y좌표, 도착지 x좌표, 도착지 y좌표, opt가 뭐였지..., 정렬 기준, 교통 수단)
        taxiFare();
        return 0;
    }

    private void taxiFare() {
        URL url= null;
        String str, receiveMsg="";
        InputStream is = null;
        JSONArray jsonArray;

        try {
            String serviceKey = "l7xx45199929f5df446a85d295e1e5eebe6a";
            //startX=126.98217734415019&startY=37.56468648536046&
            StringBuilder urlBuilder = new StringBuilder("https://apis.openapi.sk.com/tmap/routes"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("version", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("appKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*Tmap에서 발급받은 인증키*/
            urlBuilder.append("&" + URLEncoder.encode("endX", "UTF-8") + "=" + URLEncoder.encode("129.07579349764512", "UTF-8")); /*목적지 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("endY", "UTF-8") + "=" + URLEncoder.encode("35.17883196265564", "UTF-8")); /*목적지 Y좌표*/
            urlBuilder.append("&" + URLEncoder.encode("startX", "UTF-8") + "=" + URLEncoder.encode("126.98217734415019", "UTF-8")); /*출발지 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("startY", "UTF-8") + "=" + URLEncoder.encode("37.56468648536046", "UTF-8")); /*출발지 Y좌표*/

            url = new URL(urlBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer buffer = new StringBuffer();
            while ((str = rd.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            Log.i("receiveMsg : ", receiveMsg);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("receiveMsg : ", "error");
        }

        try {
            JSONObject json = new JSONObject(receiveMsg);
            JSONObject features = json.getJSONArray("features").getJSONObject(0).getJSONObject("properties");
            int taxiFare = features.getInt("taxiFare");

            //int payment = features.getInt("payment");
            Log.d("taxi : %s", String.valueOf(taxiFare));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
