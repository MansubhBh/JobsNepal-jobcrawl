/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mansubh.webscrapper.utility;

/**
 *
 * @author mansubh
 */
public class Job {
    private String title;
    private String positionType,experience;
    private String openings;
    

    public Job() {
    }

    public Job(String title, String positionType, String experience, String openings) {
        this.title = title;
        this.positionType = positionType;
        this.experience = experience;
        this.openings = openings;
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String  getOpenings() {
        return openings;
    }

    public void setOpenings(String openings) {
        this.openings = openings;
    }

   

    @Override
    public String toString() {
        return "Job{" + "title=" + title + ", positionType=" + positionType + ", experience=" + experience + ", openings=" + openings + '}';
    }

    
   public String toCSV(){
       return "Title:" + title +",  Position Type:" + positionType + ",  Experience:" + experience 
               +",  Openings:"+ openings + "\r\n"; 
   }
    
}
