package ru.laptev.carshstat.sevice;
/*
 * Created by Laptev Egor 2/5/2021
 * */

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.laptev.carshstat.model.Ride;
import ru.laptev.carshstat.model.Rides;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

@Service
public class ConnectionService {
    private HttpsURLConnection connection;
    private static final CookieManager cookieManager = new CookieManager();

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36";

    private String PHONE_NUMBER;
    private String PHONE_NUMBER_CODE;

    private final static String PHONE_LOGIN_ADDRESS = "https://youdrive.today/login/web/phone";
    private final static String PASSCODE_LOGIN_ADDRESS = "https://youdrive.today/signup/code";
    private final static String ORDERS_JSON = "https://youdrive.today/orders?limit=1000&page=1&type=user";

    public ConnectionService() {
        CookieHandler.setDefault(cookieManager);
    }



    public String connect(String phoneCode, String phoneNumber) throws Exception {
        String postParams = createPostParamsForPhoneLogin(phoneNumber, phoneCode);
        PHONE_NUMBER = phoneNumber;
        PHONE_NUMBER_CODE = phoneCode;
        return loginViaPhoneNumber(PHONE_LOGIN_ADDRESS, postParams);
    }

    public List<Ride> getAllRides(String code) throws Exception {
        String jsonStr = createJSONForCodeLogin(code, PHONE_NUMBER, PHONE_NUMBER_CODE);

        String response2 = loginViaSMSCode(PASSCODE_LOGIN_ADDRESS, jsonStr);

        String response3 = sendGET(ORDERS_JSON);
        return createRides(response3);
    }

    private String createPostParamsForPhoneLogin(String phoneNumber, String phoneNumberCode) {
        return "phone=" + phoneNumber + "&phone_code=" + phoneNumberCode;
    }

    private String createJSONForCodeLogin(String code, String phoneNumber, String phoneNumberCode) {
        return "{\"code\": " + code + ",\"phone\": \"" + phoneNumber + "\",\"phone_code\": \"" + phoneNumberCode + "\"}";
    }

    private String loginViaPhoneNumber(String url, String postParams) throws Exception {
        URL obj = new URL(url);
        connection = (HttpsURLConnection) obj.openConnection();

        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("authority", "youdrive.today");
        connection.setRequestProperty("method", "POST");
        connection.setRequestProperty("path", "/login/web/phone");
        connection.setRequestProperty("scheme", "https");
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
        connection.setRequestProperty("accept-language", "en-GB-oxendict,en;q=0.9");
        connection.setRequestProperty("content-length", "29");
        connection.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");

        for (HttpCookie cook : cookieManager.getCookieStore().getCookies()) {
            connection.addRequestProperty("cookie", cook.getName() + "=" + cook.getValue());
        }

        connection.setRequestProperty("origin", "https://youdrive.today");
        connection.setRequestProperty("referer", "https://youdrive.today/login?from=");
        connection.setRequestProperty("user-agent", USER_AGENT);
        connection.setRequestProperty("Connection", "keep-alive");

        connection.setDoOutput(true);
        connection.setDoInput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return response.toString();
    }

    private String loginViaSMSCode(String url, String jsonStr) throws Exception {
        URL obj = new URL(url);
        connection = (HttpsURLConnection) obj.openConnection();

        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("authority", "youdrive.today");
        connection.setRequestProperty("method", "POST");
        connection.setRequestProperty("path", "/signup/code");
        connection.setRequestProperty("scheme", "https");
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
        connection.setRequestProperty("accept-language", "en-GB-oxendict,en;q=0.9");
        connection.setRequestProperty("content-length", "53");
        connection.setRequestProperty("content-type", "application/json");

        for (HttpCookie cook : cookieManager.getCookieStore().getCookies()) {
            connection.addRequestProperty("cookie", cook.getName() + "=" + cook.getValue());
        }

        connection.setRequestProperty("origin", "https://youdrive.today");
        connection.setRequestProperty("referer", "https://youdrive.today/login?from=");
        connection.setRequestProperty("user-agent", USER_AGENT);
        connection.setRequestProperty("Connection", "keep-alive");

        connection.setDoOutput(true);
        connection.setDoInput(true);

        OutputStream wr = connection.getOutputStream();
        byte[] input = jsonStr.getBytes(StandardCharsets.UTF_8);
        wr.write(input, 0, input.length);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + jsonStr);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return response.toString();
    }

    private String sendGET(String url) throws Exception {
        URL obj = new URL(url);
        connection = (HttpsURLConnection) obj.openConnection();

        connection.setUseCaches(false);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("authority", "youdrive.today");
        connection.setRequestProperty("method", "GET");
        connection.setRequestProperty("path", "/orders?limit=5&page=1&type=user");
        connection.setRequestProperty("scheme", "https");
        connection.setRequestProperty("accept", "application/json, text/plain, */*");
        connection.setRequestProperty("accept-encoding", "gzip, deflate, br");
        connection.setRequestProperty("accept-language", "en-GB-oxendict,en;q=0.9");

        for (HttpCookie cook : cookieManager.getCookieStore().getCookies()) {
            connection.addRequestProperty("cookie", cook.getName() + "=" + cook.getValue());
        }

        connection.setRequestProperty("referer", "https://youdrive.today/profile/drives");
        connection.setRequestProperty("user-agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return response.toString();
    }

    private List<Ride> createRides(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Rides rides = objectMapper.readValue(jsonString, Rides.class);
        return rides.getListOfRides();
    }
}
