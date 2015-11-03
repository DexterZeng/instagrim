/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Com;


/**
 *
 * @author lenovo
 */
public class ComModel {
    Cluster cluster;



public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    
public void insertComment(String comment, java.util.UUID picid  ) {
	
    	try
    	{
    	        Date Com_time = new Date();	
    	        Session session = cluster.connect("instagrim");
                PreparedStatement ps = session.prepare("insert into UserComment (picid, comment,Com_time) Values(?,?,?)");
    	        
    	        BoundStatement boundStatement = new BoundStatement(ps);
    	        session.execute( 
    	                boundStatement.bind( 
    	                        picid, comment, Com_time));
    	        session.close();
    	}catch (Exception ex) {
            System.out.println("Error --> " + ex);
        }
    	}
		  
 


public java.util.LinkedList<Com> UserComment(java.util.UUID pid){
    
    java.util.LinkedList<Com> Coms = new java.util.LinkedList<>();
    Session session = cluster.connect("instagrim");
    PreparedStatement ps = session.prepare("select comment from UserComment where picid = ?");
    ResultSet rs = null;
    BoundStatement boundStatement = new BoundStatement(ps);
    rs = session.execute( // this is where the query is executed
            boundStatement.bind( // here you are binding the 'boundStatement'
                    pid));
    if (rs.isExhausted()) {
        System.out.println("No Comments returned");
        return null;
    } else {
        for (Row row : rs) {
        	 Com c = new Com();
        	 String com = row.getString("comment");
        	 c.setCom(com);
             Coms.add(c);
             
                          
        }
        session.close();
        return Coms;
        }
    }
}
