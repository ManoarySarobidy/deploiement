/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deploiement.git;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author sarobidy
 */
public class Git {
    
    String GIT_API_LIST = "https://api.github.com/users/%s/repos";
    String username;
    
    GitProject[] projects;
    
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    
    public void setProjects( GitProject[] projects ){
        this.projects = projects;
    }
    public void setProjects() throws Exception{
        this.getAllProjects();
    }
    public GitProject[] getProjects(  ){
            return this.projects;
    }
    
    public void getAllProjects() throws Exception{
        String api = String.format(GIT_API_LIST, this.getUsername());
        HttpsURLConnection connection = (HttpsURLConnection) new URL(api).openConnection();
        connection.setRequestMethod("GET");
        int response_code = connection.getResponseCode();
        if( response_code == HttpsURLConnection.HTTP_OK ){
            BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
            String inputLine;
            StringBuilder reponse = new StringBuilder();
            while( (inputLine = reader.readLine()) != null ){
                reponse.append(inputLine);
            }  
            //System.out.println("response ::::=> " + reponse.toString());
            reader.close();
            GitProject[] ps = new Gson().fromJson(reponse.toString(), GitProject[].class);
            this.setProjects(ps);
        }
    }
}
