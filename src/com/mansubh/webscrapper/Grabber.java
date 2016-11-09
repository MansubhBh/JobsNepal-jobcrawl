/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mansubh.webscrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author mansubh
 */
public class Grabber {
    
    private static HttpURLConnection getConnection(String link) throws IOException{
        URL url = new URL(link);
        return (HttpURLConnection)url.openConnection();
    }
    
    
    public static String post(String link,String params) throws IOException{
        
            HttpURLConnection conn =getConnection(link);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            String queryString = params;
            os.write(queryString.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           StringBuilder builder = new StringBuilder();
            String line = "";
            while((line=reader.readLine())!=null){
               builder.append(line);
            }
            reader.close();
            return builder.toString();
                    
            
    }
    
    public static String get(String link) throws IOException{
        
            HttpURLConnection conn =getConnection(link);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            StringBuilder builder = new StringBuilder();
            String line = "";
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            return builder.toString();
            
    }
    
}
