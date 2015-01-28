/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultWebRequestor;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.WebRequestor;
import com.restfb.exception.FacebookException;
import com.restfb.types.Comment;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Moiz
 */
public class FacebookConnector {

    private String pageAccessToken = "CAAJUSESZCsOgBAOe7DxdnmfqFZAZCQ0qAZBS0enFEK99ml26JPhZBAE6tCmkTropCTk1QDs2ZA59avyem5inZCot7b4S3KbiPB44xc95ovilTFAcToFusxLJje01ebrJ6hZBN23tSQLx6ixrsWzly6lligpr7cCX45g8OQkCbSUCmLeSE8FGt4vFVQIPGM2BMzKBbSLl0ympbFiUTGVa9RgG";
    private final String pageID = "10152936886546438";
    private final String APP_KEY = "655619321213160";
    private final String APP_SECRET = "05ea276b310ec1d87470e289f4b73c81";
    private String VERIFICATION_CODE = "";
    private String ACCESS_TOKEN = "";
    private FacebookClient fbClient;
    private User myuser = null;    //Store references to your user and page
    private Page mypage = null;    //for later use. In this answer's context, these
    //references are useless.
    public String firstRequest = "https://graph.facebook.com/oauth/authorize?"
            + "client_id=" + APP_KEY
            + "&redirect_uri=http://localhost:3000/connect/login_success.html&"
            + "scope=publish_stream,offline_access,create_event,read_stream";

    public String secondRequest = "https://graph.facebook.com/oauth/access_token?"
            + "client_id=" + APP_KEY
            + "&redirect_uri=http://localhost:3000/connect/login_success.html&"
            + "client_secret=" + APP_SECRET + "&code=";

    private int counter = 0;
    private AccessToken accessToken;

    public FacebookConnector() throws IOException {
        try {
            
            //accessToken=getFacebookUserToken("AQAVWSlQbmFHgaDNukJu1EHxcUaSs9c2AHeQswYU2RmD_XPX8sitSeUbmWjD455sM3T7JjJc08ss1R8QFEI3l6kYC4ASLMhL6dFRYMjXyj_hpnrXz8lVFNQFW8AoGw4f4XxKmA4Kp2qCuyqbUDY2r6mf6GjLlU-g5gUgeoQAvxShs5L8grkGUxD30HY9zNF9e7Uk-NGcUokO4zUG2uyWH_yPdSvucGwAcbxlRBAbBBmPGtRKF3ZngdToffLh84em2uAXZz_jRvFvdQT5Lj4UouMFyneYNVNeDx7DNMIHKf1VGpuYsC_oEB5RumUATGgPVRg#_=_", "http://localhost:3000/connect/login_success.html");
            
            //out.println("My application access token: " + accessToken);
            accessToken = new DefaultFacebookClient().obtainExtendedAccessToken(APP_KEY, APP_SECRET, pageAccessToken);
            //accessToken = new DefaultFacebookClient().obtainAppAccessToken(APP_KEY, APP_SECRET);
            //fbClient = new DefaultFacebookClient(pageAccessToken)
            fbClient = new DefaultFacebookClient(accessToken.getAccessToken());
            //fbClient = new DefaultFacebookClient(accessToken.getAccessToken());
            myuser = fbClient.fetchObject("me", User.class);
            ///fbClient
            
            //mypage = fbClient.fetchObject(pageID, Page.class);
            out.println("User name: " + myuser.getName());
            //out.println("Page likes: " + mypage.getAbout());
            counter = 0;
        } catch (FacebookException ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public String getCode() 
    {
        try {

            String url = firstRequest;

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");

            System.out.println("Request URL ... " + url);

            boolean redirect = true;

            // normally, 3xx is redirect
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                if (status == HttpURLConnection.HTTP_MOVED_TEMP
                        || status == HttpURLConnection.HTTP_MOVED_PERM
                        || status == HttpURLConnection.HTTP_SEE_OTHER) {
                    redirect = true;
                }
            }

            System.out.println("Response Code ... " + status);

            if (redirect) {

                // get redirect url from "location" header field
                String newUrl = conn.getHeaderField("Location");

                // get the cookie if need, for login
                String cookies = conn.getHeaderField("Set-Cookie");

                // open the new connnection again
                conn = (HttpURLConnection) new URL(newUrl).openConnection();
                conn.setRequestProperty("Cookie", cookies);
                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn.addRequestProperty("User-Agent", "Mozilla");
                conn.addRequestProperty("Referer", "google.com");

                System.out.println("Redirect to URL : " + newUrl);

            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer html = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                html.append(inputLine);
            }
            in.close();

            System.out.println("URL Content... \n" + html.toString());
            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
return "ok";
    }

    private FacebookClient.AccessToken getFacebookUserToken(String code, String redirectUrl) throws IOException {
        String appId = APP_KEY;
        String secretKey = APP_SECRET;

        WebRequestor wr = new DefaultWebRequestor();
        WebRequestor.Response accessTokenResponse = wr.executeGet(
                "https://graph.facebook.com/oauth/access_token?client_id=" + appId + "&redirect_uri=" + redirectUrl
                + "&client_secret=" + secretKey + "&code=" + code);

        return DefaultFacebookClient.AccessToken.fromQueryString(accessTokenResponse.getBody());
        
        
    }
    
    public void getVerCode() {
        try {
            URL url = new URL(firstRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            
            //URLConnection con = new URL( firstRequest ).openConnection();
            System.out.println( "orignal url: " + connection.getURL() );
            connection.connect();
            System.out.println( "connected url: " + connection.getURL() );
            InputStream is = connection.getInputStream();
            System.out.println( "redirected url: " + connection.getURL() );
            is.close();
            
            /*URL url = new URL(firstRequest);
            HttpURLConnection.setFollowRedirects(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String strTemp = "";
            con.setInstanceFollowRedirects(false);
            final int responseCode = con.getResponseCode();
            final String location = con.getHeaderField("Location");
            //System.err.format("%d%n%s%n", responseCode, location);
            while (null != (strTemp = br.readLine())) {
                System.out.println(strTemp);
            }
            System.out.println(con.getURL());*/
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
    
    public void makeTextPost()
    {
        FacebookType publishMessageResponse = fbClient.publish("me/feed", FacebookType.class,Parameter.with("message", "RestFB test"));

        out.println("Published message ID: " + publishMessageResponse.getId());

// Publishing an event
       /* Date tomorrow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 24L);
        Date twoDaysFromNow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 48L);

        FacebookType publishEventResponse = fbClient.publish("me/events", FacebookType.class,Parameter.with("name", "Party"), Parameter.with("start_time", tomorrow),Parameter.with("end_time", twoDaysFromNow));

        out.println("Published event ID: " + publishEventResponse.getId());*/
    }

    public void makeTestPost() {
        fbClient.publish(pageID + "/photos", FacebookType.class, Parameter.with("url", "http://slovnikonline.com/img/hi-ohio-logo.jpg"));
        counter++;
    }
    
    public void makeComment()
    {
        //Post post = fbClient.fetchObject("6798562721_10153092482322722", Post.class, Parameter.with("fields", "from"));
        Post post = fbClient.fetchObject("74133697733_10152696935927734", Post.class, Parameter.with("fields", "from,to,likes.summary(true),comments.summary(true)"));
        out.println("Likes count: " + post.getLikesCount());
        //out.println("Likes count (from Likes): " + post.getLikes().getCount());
        //out.println("Comments count: " + post.getComments().getTotalCount());
        FacebookType publishMessageResponse = fbClient.publish(post.getId()+"/comments", FacebookType.class, Parameter.with("message", "Test comment posted using RestFB API!!!"));
        out.println("Published message ID: " + publishMessageResponse.getId());
        //fbClient.publish(post.getId()+"/comments", String.class, Parameter.with("message", "Test comment posted using RestFB API"));
    }
    
    public void getExpiry()
    {
        Date d1 = accessToken.getExpires();
        out.println(d1.toString());
    }
    
    public void makeCommentOnProfile()
    {
        Post post = fbClient.fetchObject("627981437_10152955394871438", Post.class, Parameter.with("fields", "from"));
        //Post post = fbClient.fetchObject("74133697733_10152696935927734", Post.class, Parameter.with("fields", "from,to,likes.summary(true),comments.summary(true)"));
        //out.println("Likes count: " + post.getLikesCount());
        //out.println("Likes count (from Likes): " + post.getLikes().getCount());
        //out.println("Comments count: " + post.getComments().getTotalCount());
        FacebookType publishMessageResponse = fbClient.publish(post.getId()+"/comments", FacebookType.class, Parameter.with("message", "COMMENTING with RestFB"));
        out.println("Published message ID: " + publishMessageResponse.getId());
        //fbClient.publish(post.getId()+"/comments", String.class, Parameter.with("message", "Test comment posted using RestFB API"));
    }
    
    public void getTopPost()
    {
        Connection<Post> pageFeed = fbClient.fetchConnection("627981437/feed", Post.class);
        
        for (List<Post> feed : pageFeed){
                        for (Post post : feed){     
                             //PRINTING THE POST 
                             out.println(post.getId());
                        }
                   }
    }
}
