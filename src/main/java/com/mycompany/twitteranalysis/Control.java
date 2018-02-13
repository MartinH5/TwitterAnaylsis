/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitteranalysis;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Control {
    public static void main(String[] args) {
        //Etablish Connection
        MongoClient mongoClient = DBConnection.getInstance();
        Twitter tweets = new Twitter();
        
        DB db = mongoClient.getDB("social_net");
        DBCollection collection = db.getCollection("tweets");
        
        String input = args[0];
        if(input != null) {
            switch(input) {
                case "TotalAmount":
                    System.out.println("\n" + "The total amount of users, is: " + tweets.userCount(collection));
                    break;
                case "Top10Linkers":
                    System.out.println("\n" + "Top 10 'linkers': " + tweets.topLinker(collection));
                    break;
                case "Top5Mentioned":
                    System.out.println("\n" + "Top 5 mentioned: " + tweets.mostMentioned(collection));
                    break;
                case "Top10Active":
                    System.out.println("\n" + "Top 10 most active: " + tweets.mostActiveUsers(collection));
                    break;
                case "Top5Negative":
                    System.out.println("\n" + "Top 5 most grumpy: " + tweets.mostGrumpy(collection));
                    break;
                case "Top5Positive":
                    System.out.println("\n" + "Top 5 most happy: " + tweets.mostHappy(collection));
                    break;
                default:
                    System.out.println("\n" + "Invalid input, please try again.");
                    break;
            }
        }
    }
}