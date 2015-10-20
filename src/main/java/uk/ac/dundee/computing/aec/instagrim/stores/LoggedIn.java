/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;
import java.util.Set;

public class LoggedIn {
    boolean logedin=false;
    String Username=null;
    String first_name=null;
    String last_name=null;
    Set<String>email=null;

    public void LogedIn(){
        
    }
    
    public void setUsername(String name){
        this.Username=name;
    }
    public String getUsername(){
        return Username;
    }
    public void setLogedin(){
        logedin=true;
    }
    public void setLogedout(){
        logedin=false;
    }
    public void setfirst_name(String name){
        this.first_name=name;
    }
    public String getfirst_name(){
        return first_name;
    }
    public void setlast_name(String name){
        this.last_name=name;
    }
    public String getlast_name(){
        return last_name;
    }
    public void setemail(Set<String> email ){
        this.email=email;
    }
    public Set<String> getemail(){
        return email;
    }
    
    public void setLoginState(boolean logedin){
        this.logedin=logedin;
    }
    public boolean getlogedin(){
        return logedin;
    }
}