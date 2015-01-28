/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb;

/**
 *
 * @author Moiz
 */
import static java.lang.System.out;

import java.util.Arrays;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;

import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Fb {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {
        FacebookConnector con = new FacebookConnector();
        //con.makeTestPost();
        //con.getVerCode();
        //con.makeTextPost();
        //con.makeComment();
        //con.makeCommentOnProfile();
        con.getExpiry();
        //con.getCode();
    }
}
