package com.example.example;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

public class PlanInitialActivity extends Fragment {
    // github test
    Button addButton, placeSearch;
    Button withFriends;

    ImageView calculation;

    ListView listView;
    ArrayList<PlanInitialSubItem> items = new ArrayList<PlanInitialSubItem>();
    PlanSubAdapter adapter = new PlanSubAdapter();

    String time, name, memo;
    int type;
    double x, y;
    double transportExp;
    TextView transBudget;
    LinearLayout con;

    String[] timeList, titleList, memoList;
    double[] budgetList;

    DeleteDialog oDialog;
    TransportDialog tDialog;

    FragmentCallBack callback;

    int startYear, startMonth, startDay, startDoW, endYear, endMonth, endDay, endDoW, period;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentCallBack){
            callback = (FragmentCallBack) context;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        timeList = new String[adapter.getCount()];
        titleList = new String[adapter.getCount()];
        memoList = new String[adapter.getCount()];
        budgetList = new double[adapter.getCount()];
        for(int i=0; i<adapter.getCount(); i++){
            timeList[i] = items.get(i).getPlaceTime();
            titleList[i] = items.get(i).getPlaceName();
            memoList[i] = items.get(i).getPlaceMemo();
            budgetList[i] = items.get(i).getTransBudget();
        }

        if(callback != null){
            callback.getPlanInfo(adapter.getCount(), timeList, titleList, memoList, budgetList);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(callback != null){
            callback = null;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_plan_initial, container, false);
        //버전 상향에 따른 네트워크 연결 조정
        if (Build.VERSION.SDK_INT > 28) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if(getArguments() != null){
            startYear = getArguments().getInt("startYear", 0);
            startDay = getArguments().getInt("startDay", 0);
            startMonth = getArguments().getInt("startMonth", 0);
            startDoW = getArguments().getInt("startDow", 0);
            endYear = getArguments().getInt("endYear", 0);
            endMonth = getArguments().getInt("endMonth", 0);
            endDay = getArguments().getInt("endDay", 0);
            endDoW = getArguments().getInt("endDow", 0);
            Log.d("success", String.valueOf(startYear));
        }
        Log.d("fail", "argument is null.");

        con = (LinearLayout) rootView.findViewById(R.id.planner);
        addDay(startYear, startMonth, startDay, endYear, endMonth, endDay);

        listView = (ListView) rootView.findViewById(R.id.con);
        listView.setAdapter(adapter);

        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlanInputActivity.class);
                startActivityForResult(intent, 101);

            }
        });

        calculation = (ImageView) rootView.findViewById(R.id.calculation);
        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalExp();
                calculation.setBackgroundResource(R.drawable.ic_budget_calculation_selected);
            }
        });

        placeSearch = (Button) rootView.findViewById(R.id.placeSearch);
        placeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceSearchActivity.class);

                startActivityForResult(intent, 102);
            }
        });

        withFriends = (Button) rootView.findViewById(R.id.withFriends);
        withFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WithFriendsActivity.class);
                startActivity(intent);
            }
        });

        oDialog = new DeleteDialog(getContext());
        return rootView;

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
            PlanInitialSubItemView view= new PlanInitialSubItemView(getContext());
            final PlanInitialSubItem item = items.get(position);
            view.setPlaceTime(item.getPlaceTime());
            view.setPlaceName(item.getPlaceName());
            view.setPlaceMemo(item.getPlaceMemo());
            int t = item.getTransport();
            if(t == 1){
                view.setTransportIc(R.drawable.ic_walk_24px);
                view.setTransportText("Walk");
            }

            if(t == 2){
                view.setTransportIc(R.drawable.ic_bus_24px);
                view.setTransportText("Bus");
            }

            if(t == 3){
                view.setTransportIc(R.drawable.ic_subway_24px);
                view.setTransportText("Subway");
            }

            if(t == 4){
                view.setTransportIc(R.drawable.ic_taxi_24px);
                view.setTransportText("Taxi");
            }

            if(t == 5){
                view.setTransportIc(R.drawable.ic_car_24px);
                view.setTransportText("Car");
            }

            view.setTransBudgetText(String.valueOf(item.getTransBudget()));

            RelativeLayout i = view.findViewById(R.id.item);
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlanBudget.class);
                    startActivity(intent);
                }
            });


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
                                calculation.setBackgroundResource(R.drawable.ic_budget_calculation);
                            }
                        }
                    });

                }
            });

            transBudget = (TextView) view.findViewById(R.id.TransBudgetText);

            final ImageButton addT = (ImageButton) view.findViewById(R.id.transport_ic);
            final TextView transText = (TextView) view.findViewById(R.id.transportText);
            addT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tDialog = new TransportDialog(getContext());
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
                            calculation.setBackgroundResource(R.drawable.ic_budget_calculation);
                        }

                    });
                }
            });

            return view;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
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
                //transport =1;
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

        for(int j=0; j<adapter.getCount()-1; j++){
            returnTransportExp(j, xList[j], yList[j], xList[j+1], yList[j+1], transportList[j]);
        }
    }

    public void returnTransportExp(int j, double SX, double SY, double EX, double EY, int transport){
        Log.d("x, y : %s", String.valueOf(SX) + String.valueOf(EX));
        int searchType;
        final double[] transportExp = {0};
        final int x=j;
        if(transport==2 || transport == 3) {
            if (transport == 2)
                searchType = 2;
            else
                searchType = 1;

            Log.d("searchType : ", String.valueOf(searchType));

            // 싱글톤 생성, Key 값을 활용하여 객체 생성
            ODsayService odsayService = ODsayService.init(getContext(), "TNRXyW/xqRXnX/zMI8tA2fbn4N+HPUuaIySAow33Qvs");
            // 서버 연결 제한 시간(단위(초), default : 5초)
            odsayService.setReadTimeout(5000);
            // 데이터 획득 제한 시간(단위(초), default : 5초)
            odsayService.setConnectionTimeout(5000);
            // 콜백 함수 구현
            // API 호출

            OnResultCallbackListener onResultCallbackListener = new OnResultCallbackListener() {
                // 호출 성공 시 실행
                @Override
                public void onSuccess(ODsayData odsayData, API api) {

                    try {
                        // API Value에 API 호출 메소드 명 입력.
                        if (api == API.SEARCH_PUB_TRANS_PATH) {
                            JSONArray path = odsayData.getJson().getJSONObject("result").getJSONArray("path");
                            JSONObject info = path.getJSONObject(0).getJSONObject("info");
                            transportExp[0] = info.getInt("payment");
                            Log.d("json", String.valueOf(info));
                            Log.d("payment : %s", String.valueOf(info.getInt("payment")));
                            items.get(x).setTransBudget(transportExp[0]);
                            adapter.notifyDataSetChanged();
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

            odsayService.requestSearchPubTransPath(String.valueOf(SX), String.valueOf(SY), String.valueOf(EX), String.valueOf(EY), "0", "0", String.valueOf(searchType), onResultCallbackListener);
            //객체 초기화 -> (출발지 x좌표, 출발지 y좌표, 도착지 x좌표, 도착지 y좌표, opt가 뭐였지..., 정렬 기준, 교통 수단)
            Log.d("odSay : ", "odsayService.requestSearchPubTransPath");

        }

        else if(transport == 4){
            transportExp[0] = taxiFare(SX, SY, EX, EY);
            items.get(x).setTransBudget(transportExp[0]);
            adapter.notifyDataSetChanged();
        }

        else {
            transportExp[0] = 0;
            items.get(x).setTransBudget(transportExp[0]);
            adapter.notifyDataSetChanged();
        }


    }

    private double busFare(double SX, double SY, double EX, double EY){
        return 0;
    }

    private double taxiFare(double SX, double SY, double EX, double EY) {
        URL url= null;
        String str, receiveMsg="";
        InputStream is = null;
        JSONArray jsonArray;
        double taxiFare=0;

        try {
            String serviceKey = "l7xx45199929f5df446a85d295e1e5eebe6a";
            //startX=126.98217734415019&startY=37.56468648536046&
            StringBuilder urlBuilder = new StringBuilder("https://apis.openapi.sk.com/tmap/routes"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("version", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("appKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*Tmap에서 발급받은 인증키*/
            urlBuilder.append("&" + URLEncoder.encode("endX", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(EX), "UTF-8")); /*목적지 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("endY", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(EY), "UTF-8")); /*목적지 Y좌표*/
            urlBuilder.append("&" + URLEncoder.encode("startX", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(SX), "UTF-8")); /*출발지 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("startY", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(SY), "UTF-8")); /*출발지 Y좌표*/

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
            taxiFare = features.getInt("taxiFare");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taxiFare;
    }


    public void addDay(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); //년월일 표시

        startMonth -=1;
        endMonth -=1;
        startCal.set(startYear, startMonth, startDay);
        String startDate = dateFormat.format(startCal.getTime());
        endCal.set(endYear, endMonth, endDay);
        String endDate = dateFormat.format(endCal.getTime());
        Log.d("period", startDate+"\n"+endDate);

        int i=0;
        while(!startDate.equals(endDate)){ //다르다면 실행, 동일 하다면 빠져나감

            if(i==0) { //최초 실행 출력
                int day = startCal.get(Calendar.DAY_OF_MONTH);
                int dow = startCal.get(Calendar.DAY_OF_WEEK);

                day_sub dayItem = new day_sub(getContext());
                dayItem.setDay(day);
                switch (dow) {
                    case 1:
                        dayItem.setDoW("SUN");
                        break;
                    case 2:
                        dayItem.setDoW("MON");
                        break;
                    case 3:
                        dayItem.setDoW("TUE");
                        break;
                    case 4:
                        dayItem.setDoW("WED");
                        break;
                    case 5:
                        dayItem.setDoW("THU");
                        break;
                    case 6:
                        dayItem.setDoW("FRI");
                        break;
                    case 7:
                        dayItem.setDoW("SAT");
                        break;
                }
                con.addView(dayItem);
            }

            startCal.add(Calendar.DATE, 1); //1일 더해줌
            startDate = dateFormat.format(startCal.getTime()); //비교를 위한 값 셋팅

            int day = startCal.get(Calendar.DAY_OF_MONTH);
            int dow = startCal.get(Calendar.DAY_OF_WEEK);

            day_sub dayItem = new day_sub(getContext());
            dayItem.setDay(day);
            switch (dow) {
                case 1:
                    dayItem.setDoW("SUN");
                    break;
                case 2:
                    dayItem.setDoW("MON");
                    break;
                case 3:
                    dayItem.setDoW("TUE");
                    break;
                case 4:
                    dayItem.setDoW("WED");
                    break;
                case 5:
                    dayItem.setDoW("THU");
                    break;
                case 6:
                    dayItem.setDoW("FRI");
                    break;
                case 7:
                    dayItem.setDoW("SAT");
                    break;
            }
            con.addView(dayItem);

            i++;

        }

    }
}
