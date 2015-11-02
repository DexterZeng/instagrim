package uk.ac.dundee.computing.aec.instagrim.models;


import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import javax.imageio.ImageIO;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

public class PicModel {

    Cluster cluster;

    public void PicModel() {

    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public void insertPic(byte[] b, String type, String name, String user) {
        try {
            Convertors convertor = new Convertors();

            String types[]=Convertors.SplitFiletype(type);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            java.util.UUID picid = convertor.getTimeUUID();
            
            //The following is a quick and dirty way of doing this, will fill the disk quickly !
            Boolean success = (new File("/var/tmp/instagrimWZ/")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File("/var/tmp/instagrimWZ/" + picid));

            output.write(b);
            byte []  thumbb = picresize(picid.toString(),types[1]);
            int thumblength= thumbb.length;
            ByteBuffer thumbbuf=ByteBuffer.wrap(thumbb);
            byte[] processedb = picdecolour(picid.toString(),types[1]);
            ByteBuffer processedbuf=ByteBuffer.wrap(processedb);
            int processedlength=processedb.length;
            Session session = cluster.connect("instagrimWZ");

            PreparedStatement psInsertPic = session.prepare("insert into pics ( picid, image,thumb,processed, user, interaction_time,imagelength,thumblength,processedlength,type,name) values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement psInsertPicToUser = session.prepare("insert into userpiclist ( picid, user, pic_added) values(?,?,?)");
            BoundStatement bsInsertPic = new BoundStatement(psInsertPic);
            BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);

            Date DateAdded = new Date();
            session.execute(bsInsertPic.bind(picid, buffer, thumbbuf,processedbuf, user, DateAdded, length,thumblength,processedlength, type, name));
            session.execute(bsInsertPicToUser.bind(picid, user, DateAdded));
            session.close();

        } catch (IOException ex) {
            System.out.println("Error --> " + ex);
        }
    }
    public void insertPhoto(byte[] b, String type, String name, String user) {
        try {
            Convertors convertor = new Convertors();

            String types[]=Convertors.SplitFiletype(type);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            java.util.UUID photoid = convertor.getTimeUUID();
            
            Boolean success = (new File("/var/tmp/instagrimWZ/")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File("/var/tmp/instagrimWZ/" + photoid));

            output.write(b);
            byte []  thumbb = picresize(photoid.toString(),types[1]);
            int thumblength= thumbb.length;
            ByteBuffer thumbbuf=ByteBuffer.wrap(thumbb);
            byte[] processedb = picdecolour(photoid.toString(),types[1]);
            ByteBuffer processedbuf=ByteBuffer.wrap(processedb);
            int processedlength=processedb.length;
            Session session = cluster.connect("instagrimWZ");

            PreparedStatement psInsertPhoto = session.prepare("insert into photo ( photoid, image,thumb,processed, user, interaction_time,imagelength,thumblength,processedlength,type,name) values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement psInsertPhotoToUser = session.prepare("insert into userphotolist ( photoid, user, pic_added) values(?,?,?)");
            BoundStatement bsInsertPhoto = new BoundStatement(psInsertPhoto);
            BoundStatement bsInsertPhotoToUser = new BoundStatement(psInsertPhotoToUser);

            Date DateAdded = new Date();
            session.execute(bsInsertPhoto.bind(photoid, buffer, thumbbuf,processedbuf, user, DateAdded, length,thumblength,processedlength, type, name));
            session.execute(bsInsertPhotoToUser.bind(photoid, user, DateAdded));
            session.close();

        } catch (IOException ex) {
            System.out.println("Error --> " + ex);
        }
    }

    public byte[] picresize(String picid,String type) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrimWZ/" + picid));
            BufferedImage thumbnail = createThumbnail(BI);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, type, baos);
            baos.flush();
            
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }
    
    public byte[] picdecolour(String picid,String type) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/instagrimWZ/" + picid));
            BufferedImage processed = createProcessed(BI);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(processed, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }

    public static BufferedImage createThumbnail(BufferedImage img) {
        img = resize(img, Method.SPEED, 250, OP_ANTIALIAS, OP_GRAYSCALE);
        // Let's add a little border before we return result.
        return pad(img, 2);
    }
    
   public static BufferedImage createProcessed(BufferedImage img) {
        int Width=img.getWidth()-1;
        img = resize(img, Method.SPEED, Width, OP_ANTIALIAS, OP_GRAYSCALE);
        return pad(img, 4);
    }
   
    public java.util.LinkedList<Pic> getPicsForUser(String User) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrimWZ");
        PreparedStatement ps = session.prepare("select picid from userpiclist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                System.out.println("UUID" + UUID.toString());
                pic.setUUID(UUID);
                Pics.add(pic);

            }
        }
        return Pics;
    }
    public java.util.LinkedList<Pic> getPhotoForUser(String User) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrimWZ");
        PreparedStatement ps = session.prepare("select photoid from userphotolist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("photoid");
                System.out.println("UUID" + UUID.toString());
                pic.setUUID(UUID);
                Pics.add(pic);

            }
        }
        return Pics;
    }

    public Pic getPic(int image_type, java.util.UUID picid) {
        Session session = cluster.connect("instagrimWZ");
        ByteBuffer bImage = null;
        String type = null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;
         
            if (image_type == Convertors.DISPLAY_IMAGE) {
                
                ps = session.prepare("select image,imagelength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_THUMB) {
                ps = session.prepare("select thumb,imagelength,thumblength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                ps = session.prepare("select processed,processedlength,type from pics where picid =?");
            }
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));

            if (rs.isExhausted()) {
                System.out.println("No Images returned");
                return null;
            } else {
                for (Row row : rs) {
                    if (image_type == Convertors.DISPLAY_IMAGE) {
                        bImage = row.getBytes("image");
                        length = row.getInt("imagelength");
                    } else if (image_type == Convertors.DISPLAY_THUMB) {
                        bImage = row.getBytes("thumb");
                        length = row.getInt("thumblength");
                
                    } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                        bImage = row.getBytes("processed");
                        length = row.getInt("processedlength");
                    }
                    
                    type = row.getString("type");

                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }
        session.close();
        Pic p = new Pic();
        p.setPic(bImage, length, type);

        return p;

    }
    public Pic getPhoto(int image_type, java.util.UUID photoid) {
    Session session = cluster.connect("instagrimWZ");
    ByteBuffer bImage = null;
    String type = null;
    int length = 0;
    try {
        Convertors convertor = new Convertors();
        ResultSet rs = null;
        PreparedStatement ps = null;
     
        if (image_type == Convertors.DISPLAY_IMAGE) {
            
            ps = session.prepare("select image,imagelength,type from photo where photoid =?");
        } else if (image_type == Convertors.DISPLAY_THUMB) {
            ps = session.prepare("select thumb,imagelength,thumblength,type from photo where photoid =?");
        } else if (image_type == Convertors.DISPLAY_PROCESSED) {
            ps = session.prepare("select processed,processedlength,type from photo where photoid =?");
        }
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        photoid));

        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                if (image_type == Convertors.DISPLAY_IMAGE) {
                    bImage = row.getBytes("image");
                    length = row.getInt("imagelength");
                } else if (image_type == Convertors.DISPLAY_THUMB) {
                    bImage = row.getBytes("thumb");
                    length = row.getInt("thumblength");
            
                } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                    bImage = row.getBytes("processed");
                    length = row.getInt("processedlength");
                }
                
                type = row.getString("type");

            }
        }
    } catch (Exception et) {
        System.out.println("Can't get Pic" + et);
        return null;
    }
    session.close();
    Pic p = new Pic();
    p.setPic(bImage, length, type);

    return p;

}
    public void DeletePic(java.util.UUID picid){
        
        try{	
    		Session session =cluster.connect("instagrimWZ");       	
        	PreparedStatement ps1 =session.prepare("select user from pics where picid=?");
        	PreparedStatement ps2 =session.prepare("select interaction_time from pics where picid=?");
                BoundStatement bs1 = new BoundStatement(ps1);        	
        	BoundStatement bs2 = new BoundStatement(ps2);
        	ResultSet rs1 = session.execute(bs1.bind(picid));
        	ResultSet rs2 = session.execute(bs2.bind(picid));
        	String user="";
        	Date date=new Date();       	        	
                if (rs1.isExhausted()) {
                    System.out.println("No user found in the pics");
                } else {
                    for (Row row : rs1) {
                            user=row.getString("user");
                            System.out.println(user);
                    }
                }
                if (rs2.isExhausted()) {
                    System.out.println("No interactiontime found in the pics");
                } else {
                    for (Row row : rs2) {
                            date=row.getDate("interaction_time");
                            System.out.println(date.toString());
                    }
                }    
        	PreparedStatement ps3 = session.prepare("delete from userpiclist where user=? and pic_added=?");
        	BoundStatement bs3 = new BoundStatement(ps3);
        	session.execute(bs3.bind(user,date));
                
                PreparedStatement ps4 = session.prepare("delete from pics where picid=?");
        	BoundStatement bs4 = new BoundStatement(ps4);
        	session.execute(bs4.bind(picid));       	
        	session.close();
    	} catch (Exception ex) {
            System.out.println("Error --> " + ex);
    	}
    
    }

}
