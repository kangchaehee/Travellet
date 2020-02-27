package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class PlaceSearchActivity extends AppCompatActivity {

    Handler handler = new Handler();

    ListView listView;
    ArrayList<PlaceSearchItem> items = new ArrayList<PlaceSearchItem>();
    PlaceAdapter adapter = new PlaceAdapter();

    EditText editText;
    TextView textView;
    ImageButton back;

    Button searchLodging, searchFood, searchShopping, searchTourism, searchCulture, searchLeisure, searchTransportation, searchETC;
    boolean lodgingState=false, foodState=false, shoppingState=false, tourismState=false, etcState=false, cultureState=false, leisureState = false, transportationState=false;
    int searchType;
    int placeID[] = new int[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_place_search);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        back = (ImageButton) findViewById(R.id.placeToPlan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        listView = (ListView) findViewById(R.id.listView);

        editText = (EditText) findViewById(R.id.placeSearchKey);
        ImageButton searchButton = (ImageButton) findViewById(R.id.SearchKeyButton);

        searchLodging = (Button) findViewById(R.id.searchLodging);
        searchFood = (Button) findViewById(R.id.searchFood);
        searchShopping = (Button) findViewById(R.id.searchShopping);
        searchTourism = (Button) findViewById(R.id.searchTourism);
        searchETC = (Button) findViewById(R.id.searchETC);
        searchCulture = (Button) findViewById(R.id.searchCulture);
        searchLeisure = (Button) findViewById(R.id.searchLeisure);
        searchTransportation = (Button) findViewById(R.id.searchTransportation);

        searchLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lodgingState){
                    lodgingState = true;
                    searchLodging.setBackgroundResource(R.drawable.button_background_full);
                    searchLodging.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

            }
        });

        searchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!foodState){
                    foodState = true;
                    searchFood.setBackgroundResource(R.drawable.button_background_full);
                    searchFood.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));


                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

            }
        });

        searchShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!shoppingState){
                    shoppingState = true;
                    searchShopping.setBackgroundResource(R.drawable.button_background_full);
                    searchShopping.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));


                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }

            }
        });

        searchTourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tourismState){
                    tourismState = true;
                    searchTourism.setBackgroundResource(R.drawable.button_background_full);
                    searchTourism.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }
            }
        });

        searchLeisure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!leisureState){
                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = true;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_full);
                    searchLeisure.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }
            }
        });

        searchCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cultureState){
                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = true;
                    searchCulture.setBackgroundResource(R.drawable.button_background_full);
                    searchCulture.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }
            }
        });

        searchTransportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!transportationState){
                    transportationState = true;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_full);
                    searchTransportation.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

                else {
                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }
            }
        });

        searchETC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etcState){
                    etcState = true;
                    searchETC.setBackgroundResource(R.drawable.button_background_full);
                    searchETC.setTextColor(getResources().getColor(R.color.white, getTheme()));

                    lodgingState = false;
                    searchLodging.setBackgroundResource(R.drawable.button_background_border);
                    searchLodging.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    foodState = false;
                    searchFood.setBackgroundResource(R.drawable.button_background_border);
                    searchFood.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    shoppingState = false;
                    searchShopping.setBackgroundResource(R.drawable.button_background_border);
                    searchShopping.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    tourismState = false;
                    searchTourism.setBackgroundResource(R.drawable.button_background_border);
                    searchTourism.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    cultureState = false;
                    searchCulture.setBackgroundResource(R.drawable.button_background_border);
                    searchCulture.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    leisureState = false;
                    searchLeisure.setBackgroundResource(R.drawable.button_background_border);
                    searchLeisure.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                    transportationState = false;
                    searchTransportation.setBackgroundResource(R.drawable.button_background_border);
                    searchTransportation.setTextColor(getResources().getColor(R.color.blue, getTheme()));
                }

                else {
                    etcState = false;
                    searchETC.setBackgroundResource(R.drawable.button_background_border);
                    searchETC.setTextColor(getResources().getColor(R.color.blue, getTheme()));

                }

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                if(lodgingState) {
                    searchType = 80;
                }
                else if(foodState) {
                    searchType = 82;
                }
                else if(shoppingState) {
                    searchType = 79;
                }
                else if(tourismState) {
                    searchType = 76;
                }
                else if(cultureState) {
                    searchType = 78;
                }
                else if(leisureState) {
                    searchType = 75;
                }
                else if(transportationState){
                    searchType = 77;
                }
                else if(etcState) {
                    searchType = 85;
                }
                else
                    searchType = -1;
                items.clear();
                getPlaceListData(keyword, searchType);
            }
        });

    }

    class PlaceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PlaceSearchItem item) {
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
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            PlaceSearchItemView view = new PlaceSearchItemView(getApplicationContext());
            PlaceSearchItem item = items.get(position);
            view.setPlaceListThumb(item.getPlaceListThumb());
            view.setPlaceListName(item.getPlaceListName());
            view.setPlaceListAddr(item.getPlaceListAddr());
            view.setPlaceListType(item.getPlaceListType());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PlaceDetailActivity.class);

                    intent.putExtra("id", placeID[position]);

                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public void getPlaceListData(String keyword, int searchType) {
        try {
            String serviceKey = "x%2FB48ucBtE1tDbI%2FOOc%2B0Qh3MP%2BlYEETjSL5Q8G0L912refn%2FEii%2FgZ5E0S%2Bdqs%2BAmxagAo%2B9%2BieRWWN80QxNA%3D%3D";

            StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/EngService/searchKeyword"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*목록 구분(Y=목록, N=개수)*/
            urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("B", "UTF-8")); /*(A=제목순,B=조회순,C=수정일순,D=생성일순) 대표이미지가 반드시 있는 정렬(O=제목순, P=조회순, Q=수정일순, R=생성일순)*/
            if(searchType != -1){
                urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(searchType), "UTF-8")); /*관광타입(관광지, 숙박 등)ID*/
            }
            urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*지역코드*/
            urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*검색 요청할 키워드(국문=인코딩 필요)*/
            URL url = new URL(urlBuilder.toString());

            ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conManager.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
                new DownloadXml().execute(url.toString());
                Log.d("good", "DownloadXml().execute(url.toString());");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("error", "SearchKeyword에러");
        }
    }

    private class DownloadXml extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            Document doc = null;
            try {
                url = new URL((String) urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Document doc) {
            String title = "", address = "", type = "";
            NodeList nodeList = doc.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                String thumbnail = " ";

                Node node = nodeList.item(i);
                Element element = (Element) node;

                if (!nodeList.item(0).getNodeName().equals("firstimage")) {
                    NodeList imageNode = element.getElementsByTagName("firstimage");
                    if(imageNode.item(0) !=null){
                        thumbnail = imageNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("contentid")) {
                    NodeList idNode = element.getElementsByTagName("contentid");
                    if(i>= placeID.length){
                        int[] placeDup = new int[placeID.length];
                        for(int j=0; j<placeDup.length; j++){
                            placeDup[j] = placeID[j];
                        }
                        placeID = new int[placeID.length*2];
                        for(int j=0; j<placeDup.length; j++){
                            placeID[j] = placeDup[j];
                        }
                    }
                    placeID[i] = Integer.parseInt(idNode.item(0).getChildNodes().item(0).getNodeValue());
                }

                if (!nodeList.item(0).getNodeName().equals("title")) {
                    NodeList titleNode = element.getElementsByTagName("title");
                    title = titleNode.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (!nodeList.item(0).getNodeName().equals("addr1")) {
                    NodeList addressNode = element.getElementsByTagName("addr1");
                    address = addressNode.item(0).getChildNodes().item(0).getNodeValue();
                }
                if (!nodeList.item(0).getNodeName().equals("contenttypeid")) {
                    NodeList typeNode = element.getElementsByTagName("contenttypeid");
                    int typeid = Integer.valueOf(typeNode.item(0).getChildNodes().item(0).getNodeValue());

                    switch (typeid) {
                        case 76:
                            type = "Tourism";
                            break;

                        case 78:
                            type = "culture";
                            break;

                        case 85:
                            type = "etc.";
                            break;

                        case 75:
                            type = "leisure/sports";
                            break;

                        case 80:
                            type = "Accommodation";
                            break;

                        case 79:
                            type = "Shopping";
                            break;

                        case 82:
                            type = "Food";
                            break;

                        case 77:
                            type = "transportation";
                            break;
                    }
                }
                adapter.addItem(new PlaceSearchItem(thumbnail, title, address, type));
            }
            listView.setAdapter(adapter);
        }

    }
}


