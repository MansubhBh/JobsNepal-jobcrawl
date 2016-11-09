/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mansubh.webscrapper;

import com.mansubh.webscrapper.utility.Job;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author mansubh
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Job> listjob = new ArrayList<>();
        
          String link = "http://www.jobsnepal.com/simple-job-search";
          try{
          Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Enter the search param:");
            String param = input.next();
            String content = Grabber.post(link, "Keywords="  + param);
           // System.out.println(content);
           String jobregex = "<a class=\"job-item\"(.*?)href=\"(.*?)\"\\s>(.*?)<\\/a>";
            Pattern pattern = Pattern.compile(jobregex);
            Matcher matcher = pattern.matcher(content);
            while(matcher.find()){
           String title = matcher.group(3).trim();
            String url =matcher.group(2);
            
              
         String linkdata  = Grabber.get(url);
          String salaryregex = "<label>Salary:</label>\\s+<span>(.*?)</span>";
          String salary = getdataFromPattern(salaryregex, linkdata);
            String experienceregex = "<label>Experience:</label>\\s+<span>(.*?)</span>";
            String experience = getdataFromPattern(experienceregex, linkdata);
            String postiontyperegex = "<label>Position Type:</label>\\s+<span>(.*?)</span>";
            String position = getdataFromPattern(postiontyperegex, linkdata);
            String openingregex= "<label>Openings:</label>\\s+<span>(.*?)</span>";
            String openings = getdataFromPattern(openingregex,linkdata);
            Job job = new Job();
           job.setTitle(title);
            job.setExperience(experience);
            job.setPositionType(position);
            job.setOpenings(openings);
            
            listjob.add(job);
            
          
           }
           writetofile(listjob,param);
            
        }
          }catch(IOException e){
                    System.out.println(e.getMessage());
                    }
    }
    
    public static String getdataFromPattern(String regexp,String Content){
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(Content);
        String data = "" ;
        if(matcher.find()){
          data = matcher.group(1).trim();
        }
        return data;
        
    }
    public static void writetofile(List<Job> joblist,String param){
        try{
            
        FileWriter writer = new FileWriter("/home/mansubh/"+ param +"Jobs.csv");
        StringBuilder builder = new StringBuilder();
        
        for(Job j:joblist){
        builder.append(j.toCSV());
        
        }
        writer.write(builder.toString());
        System.out.println("Data wriiten in file "+param+"Jobs.csv");
        writer.close();
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
   
}
