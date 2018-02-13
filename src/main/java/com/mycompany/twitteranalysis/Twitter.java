/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.twitteranalysis;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import java.util.Arrays;

public class Twitter {

    public String rege = "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9_]+)";

    // A total of how many users there is
    public long userCount(DBCollection collection) {
        return collection.count();
    }

    //List of top 10 users that link to others
    public Iterable<DBObject> topLinker(DBCollection collection) {
        Iterable<DBObject> ai = collection.aggregate(Arrays.asList(
                new BasicDBObject("$match", new BasicDBObject("text", new BasicDBObject("$regex", rege))),
                new BasicDBObject("$group", new BasicDBObject("_id", "$user").append("count", new BasicDBObject("$sum", 1))),
                new BasicDBObject("$sort", new BasicDBObject("count", -1)),
                new BasicDBObject("$limit", 10)
        )).results();
        return ai;
    }
    
    // Top 5 most mentioned users
    public Iterable<DBObject> mostMentioned(DBCollection collection) {
        Iterable<DBObject> ai = collection.aggregate(Arrays.asList(
                new BasicDBObject("$match", new BasicDBObject("text", new BasicDBObject("$regex", rege))),
                new BasicDBObject("$project", new BasicDBObject("user", "$user").append("texts", new BasicDBObject("$split", Arrays.asList("$text", " ")))),
                new BasicDBObject("$unwind", "$texts"),
                new BasicDBObject("$match", new BasicDBObject("texts", new BasicDBObject("$regex", rege))),
                new BasicDBObject("$group", new BasicDBObject("_id", "$texts").append("count", new BasicDBObject("$sum", 1))),
                new BasicDBObject("$sort", new BasicDBObject("count", -1)),
                new BasicDBObject("$limit", 5)
        )).results();
        return ai;
    }
    
     //Top 10 most active users
    public Iterable<DBObject> mostActiveUsers(DBCollection collection) {
        Iterable<DBObject> ai = collection.aggregate(Arrays.asList(
                new BasicDBObject("$group", new BasicDBObject("_id", "$user").append("count", new BasicDBObject("$sum", 1))),
                new BasicDBObject("$sort", new BasicDBObject("count", -1)),
                new BasicDBObject("$limit", 10)
        )).results();
        return ai;
    }
        //Top 5 most "grumpy" tweeter
    public Iterable<DBObject> mostGrumpy(DBCollection collection) {
        Iterable<DBObject> ai = collection.aggregate(Arrays.asList(
                new BasicDBObject("$match", new BasicDBObject("polarity", new BasicDBObject("$eq", 0))),
                new BasicDBObject("$group", new BasicDBObject("_id", "$user").append("count", new BasicDBObject("$sum", 1))),
                new BasicDBObject("$sort", new BasicDBObject("count", -1)),
                new BasicDBObject("$limit", 5)
        )).results();
        return ai;
    }
    
    // Top 5 most happy tweeter
    public Iterable<DBObject> mostHappy(DBCollection collection) {
        Iterable<DBObject> ai = collection.aggregate(Arrays.asList(
                new BasicDBObject("$match", new BasicDBObject("polarity", new BasicDBObject("$eq", 4))),
                new BasicDBObject("$group", new BasicDBObject("_id", "$user").append("count", new BasicDBObject("$sum", 1))),
                new BasicDBObject("$sort", new BasicDBObject("count", -1)),
                new BasicDBObject("$limit", 5)
        )).results();
        return ai;
    }
}
