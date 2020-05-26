package com.example.example.feature.plan.select;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example.R;
import com.example.example.feature.plan.select.PlaceSelectItem;
import com.example.example.feature.plan.select.PlaceSelectItemView;

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

public class PlaceSelect extends AppCompatActivity {

    ListView listView;
    ArrayList<PlaceSelectItem> items = new ArrayList<PlaceSelectItem>();
    PlaceSelectAdapter adapter = new PlaceSelectAdapter();

    EditText editText;
    TextView textView;
    ImageButton back;

    int placeID[] = new int[10];
    double mapx, mapy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_place);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(adapter);
        //textView = (TextView) findViewById(R.id.imageView);

        editText = (EditText) findViewById(R.id.placeSearchKey);
        ImageButton searchButton = (ImageButton) findViewById(R.id.SearchKeyButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                Log.d("keyword: ", keyword);
                items.clear();
                getPlaceListData(keyword);
            }
        });

    }

    class PlaceSelectAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PlaceSelectItem item) {
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
            PlaceSelectItemView view = new PlaceSelectItemView(getApplicationContext());
            PlaceSelectItem item = items.get(position);
            final String selectTitle = item.getTitle();
            final double selectX = item.getMapx();
            final double selectY = item.getMapy();
            view.setTitle(item.getTitle());
            view.setAddress(item.getAddress());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("selectTitle", selectTitle);
                    intent.putExtra("selectX", selectX);
                    intent.putExtra("selectY", selectY);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            return view;
        }
    }

    public void getPlaceListData(String keyword) {
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
            //urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(searchType), "UTF-8")); /*관광타입(관광지, 숙박 등)ID*/
            urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*지역코드*/
            urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*검색 요청할 키워드(국문=인코딩 필요)*/
            URL url = new URL(urlBuilder.toString());

            ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conManager.getActiveNetworkInfo();
            Log.d("url: ", urlBuilder.toString());

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

                Log.d("clear complete", "ddd");

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
            String title = "", address = "";
            double mapx=0.0, mapy=0.0;
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
                        placeID = new int[placeID.length+10];
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
                    Log.d("clear complete", address);
                }
                if (!nodeList.item(0).getNodeName().equals("mapx")) {
                    NodeList xNode = element.getElementsByTagName("mapx");
                    if(xNode.item(0) !=null){
                        mapx = Double.parseDouble(xNode.item(0).getChildNodes().item(0).getNodeValue());
                    }
                }
                if (!nodeList.item(0).getNodeName().equals("mapy")) {
                    NodeList yNode = element.getElementsByTagName("mapy");
                    if(yNode.item(0) !=null){
                        mapy = Double.parseDouble(yNode.item(0).getChildNodes().item(0).getNodeValue());
                    }
                }

                adapter.addItem(new PlaceSelectItem(title, address, mapx, mapy, false));
            }
            listView.setAdapter(adapter);
            Log.d("good", "listView.setAdapter(adapter);");
        }

    }
}
