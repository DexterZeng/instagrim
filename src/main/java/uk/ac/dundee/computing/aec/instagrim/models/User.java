package uk.ac.dundee.computing.aec.instagrim.models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.Com;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Administrator
 */
public class User {
    Cluster cluster;
    public User(){
        
    }
    
    public boolean RegisterUser(String username, String Password, String first_name, String last_name, Set<String> email){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (login,password,first_name,last_name,email) Values(?,?,?,?,?)");
        
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username,EncodedPassword,first_name,last_name,email));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;       
    }
    
    public boolean ChangeInfo(String username,String first_name, String last_name, Set<String> email){
       
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("update userprofiles SET first_name=?, last_name=?, email=? where login=?");
        
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        first_name,last_name,email,username));
        //We are assuming this always works.  Also a transaction would be good here !
        
        return true;       
    }
    
    public boolean IsValidUser(String username, String Password){
        AeSimpleSHA1 sha1handler=  new AeSimpleSHA1();
        String EncodedPassword=null;
        try {
            EncodedPassword= sha1handler.SHA1(Password);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException et){
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {
               
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0)
                    return true;
            }
        }
   
    
    return false;  
    }
    
    public boolean checkUsername(String username)
    {
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select login from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("Username is available");
            return false;
    }
        else
            return true;
    }
    
    public void insertProfile(byte[] b,  String name, String user){
    	ByteBuffer buffer = ByteBuffer.wrap(b);
        int length = b.length;
        Boolean success = (new File("/var/tmp/instagrim/")).mkdirs();
        FileOutputStream output = null;
		try {
			output = new FileOutputStream(new File("/var/tmp/instagrim/" + user));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			output.write(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Session session = cluster.connect("instagrim");

        PreparedStatement psInsertPro = session.prepare("update userprofiles SET profile=? WHERE login=?");
        BoundStatement bsInsertPro = new BoundStatement(psInsertPro);
        session.execute(bsInsertPro.bind(buffer,user));
        session.close();
    }
    
     public String First_name(String username)
    {
    	 String Storedname = null;
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select first_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No First_Name returned");
            return null;
        } else {
            for (Row row : rs) {
               
                Storedname = row.getString("first_name");
                              
            }
            return Storedname;
            }
        }
    
    public String Last_name(String username)
    {
    	String StoredName = null;
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select last_name from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Last_Name returned");
            return null;
        } else {
            for (Row row : rs) {
               
                StoredName = row.getString("last_name");
               
               
            }
            return StoredName;
            }
        }
    
    public Set<String> Email(String username)
    {
    	Set<String> Storedname = null;
    	Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select email from userprofiles where login =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        
        if (rs.isExhausted()) {
            System.out.println("No email returned");
            return null;
        } else {
            for (Row row : rs) {
               
                Storedname =  row.getSet("email", String.class);
                              
            }
            return Storedname;
            }
        }
       public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
       
       public boolean Friend(String usernamef,String username){
           Set<String> friend=new HashSet<String>();
           friend.add(usernamef);
           Session session = cluster.connect("instagrim");
           PreparedStatement ps = session.prepare("UPDATE userprofiles SET friend = friend + ? WHERE login=?");
           ResultSet rs = null;
           BoundStatement boundStatement = new BoundStatement(ps);
           rs = session.execute( // this is where the query is executed
                 boundStatement.bind( // here you are binding the 'boundStatement'
                        friend,username));
           return true;
       }
       
       public boolean UnFriend(String usernamef,String username){
           Set<String> friend=new HashSet<String>();
           friend.add(usernamef);
           Session session = cluster.connect("instagrim");
           PreparedStatement ps = session.prepare("UPDATE userprofiles SET friend = friend - ? WHERE login=?");
           ResultSet rs = null;
           BoundStatement boundStatement = new BoundStatement(ps);
           rs = session.execute( // this is where the query is executed
                 boundStatement.bind( // here you are binding the 'boundStatement'
                        friend,username));
           return true;
       }
       
    public Set<String> ShowFriends(String username){
            Set<String> friends = null;
          //  java.util.LinkedList<String> friends = new java.util.LinkedList<>();
            Session session = cluster.connect("instagrim");
            PreparedStatement ps = session.prepare("select friend from userprofiles where login = ?");
            ResultSet rs = null;
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            username));
            if (rs.isExhausted()) {
                System.out.println("No friends found");
                return null;
            } else {
                for (Row row : rs) {
                         friends=row.getSet("friend", String.class);
                }
                return friends;
                }
    }
    
}
