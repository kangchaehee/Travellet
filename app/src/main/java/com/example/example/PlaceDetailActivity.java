package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class PlaceDetailActivity extends AppCompatActivity {

    int placeID;

    TextView detailTitle, detailType, detailOverview, detailAddr, detailTel, detailLink;
    ImageView detailImage;
    ImageButton back;

    String title=" ", keyTitle;
    Button moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_place_detail);

        Intent intent = getIntent();
        placeID = intent.getIntExtra("id", 0);

        back = (ImageButton) findViewById(R.id.detailToList);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailTitle = (TextView) findViewById(R.id.detailTitle);
        detailType = (TextView) findViewById(R.id.detailType);
        detailOverview = (TextView) findViewById(R.id.detailOverview);
        detailAddr = (TextView) findViewById(R.id.detailAddress);
        detailTel = (TextView) findViewById(R.id.detailTel);
        detailLink = (TextView) findViewById(R.id.detailLink);
        detailImage = (ImageView) findViewById(R.id.detailImage);

        getPlaceDetail(placeID);

        moreInfo = (Button) findViewById(R.id.moreInfo);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, title);
                startActivity(intent);
            }
        });
    }

    public void getPlaceDetail(int placeID) {
        try {
            String serviceKey = "x%2FB48ucBtE1tDbI%2FOOc%2B0Qh3MP%2BlYEETjSL5Q8G0L912refn%2FEii%2FgZ5E0S%2Bdqs%2BAmxagAo%2B9%2BieRWWN80QxNA%3D%3D";

            StringBuilder urlBuilder = new StringBuilder(" http://api.visitkorea.or.kr/openapi/service/rest/EngService/detailCommon"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
            urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
            urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(String.valueOf(placeID), "UTF-8")); /*콘텐츠 ID*/
            urlBuilder.append("&" + URLEncoder.encode("countentTypeId","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*관광타입(관광지,숙박 등) ID*/
            urlBuilder.append("&" + URLEncoder.encode("defaultYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*기본정보 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("firstImageYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*원본, 썸네일 대표이미지 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("areacodeYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*지역코드, 시군구코드 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("catcodeYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*대,중,소 분류코드 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("addrinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*주소, 상세주소 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("mapinfoYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*좌표 X,Y 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("overviewYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*콘텐츠 개요 조회여부*/
            urlBuilder.append("&" + URLEncoder.encode("transGuideYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*길안내조회*/

            URL url1 = new URL(urlBuilder.toString());

            ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conManager.getActiveNetworkInfo();

            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()));
            //startActivity(intent);

            if (netInfo != null && netInfo.isConnected()) {
                new DownloadXml().execute(url1.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            String link ="", tel = "",  address = "", type = "", image = " ";
            String dAddress = "", mapx = "", mapy = "", overview = "", directions="";
            NodeList nodeList = doc.getElementsByTagName("item");
            org.jsoup.nodes.Document linkDoc, overviewDoc;

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element element = (Element) node;

                if (!nodeList.item(0).getNodeName().equals("contenttypeid")) {
                    NodeList typeNode = element.getElementsByTagName("contenttypeid");
                    int typeid = Integer.valueOf(typeNode.item(0).getChildNodes().item(0).getNodeValue());

                    switch (typeid) {
                        case 76:
                            type = "Tourism";
                            break;

                        case 78:
                            type = "Culture";
                            break;

                        case 85:
                            type = "etc";
                            break;

                        case 75:
                            type = "Leisure/Sports";
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
                            type = "Transportation";
                            break;
                    }
                }


                if (!nodeList.item(0).getNodeName().equals("homepage")) {
                    NodeList linkNode = element.getElementsByTagName("homepage");
                    if(linkNode.item(0) !=null){
                        link = linkNode.item(0).getChildNodes().item(0).getNodeValue();
                        linkDoc = Jsoup.parse(link);
                        link = linkDoc.text();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("tel")) {
                    NodeList telNode = element.getElementsByTagName("tel");
                    if(telNode.item(0) !=null){
                        tel = telNode.item(0).getChildNodes().item(0).getNodeValue();
                        linkDoc = Jsoup.parse(tel);
                        tel = linkDoc.text();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("title")) {
                    NodeList titleNode = element.getElementsByTagName("title");
                    title = titleNode.item(0).getChildNodes().item(0).getNodeValue();
                }

                if (!nodeList.item(0).getNodeName().equals("firstimage")) {
                    NodeList imageNode = element.getElementsByTagName("firstimage");
                    if(imageNode.item(0) !=null){
                        image = imageNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("overview")) {
                    NodeList overviewNode = element.getElementsByTagName("overview");
                    if(overviewNode.item(0) !=null){
                        overview = overviewNode.item(0).getChildNodes().item(0).getNodeValue();
                        overviewDoc = Jsoup.parse(overview);
                        overview = overviewDoc.text();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("addr1")) {
                    NodeList addressNode = element.getElementsByTagName("addr1");
                    address = addressNode.item(0).getChildNodes().item(0).getNodeValue();
                    overviewDoc = Jsoup.parse(address);
                    address = overviewDoc.text();
                }

                if (!nodeList.item(0).getNodeName().equals("addr2")) {
                    NodeList dAddressNode = element.getElementsByTagName("addr2");
                    if(dAddressNode.item(0) !=null){
                        dAddress = dAddressNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("mapx")) {
                    NodeList xNode = element.getElementsByTagName("mapx");
                    if(xNode.item(0) !=null){
                        mapx = xNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("mapy")) {
                    NodeList yNode = element.getElementsByTagName("mapy");
                    if(yNode.item(0) !=null){
                        mapy = yNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("overview")) {
                    NodeList overviewNode = element.getElementsByTagName("overview");
                    if(overviewNode.item(0) !=null){
                        mapx = overviewNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }

                if (!nodeList.item(0).getNodeName().equals("directions")) {
                    NodeList directionsNode = element.getElementsByTagName("directions");
                    if(directionsNode.item(0) !=null){
                        mapx = directionsNode.item(0).getChildNodes().item(0).getNodeValue();
                    }
                }
            }

            detailTitle.setText(title);
            detailType.setText(type);
            detailOverview.setText(overview);
            detailAddr.setText(address+"\n"+dAddress);
            detailTel.setText(tel);
            detailLink.setText(link);
            GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.image_rounding);
            detailImage.setBackground(drawable);
            detailImage.setClipToOutline(true);
            Glide.with(getApplicationContext()).load(image).into(detailImage);
        }
    }
}
