/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deploiement.configuration;

import com.google.gson.Gson;
import deploiement.git.Git;
import deploiement.git.GitProject;
import deploiement.server.Server;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Config {
    String src_folder;
    String name;
    Git github;
    Server haproxy;
    Server[] servers;
    String buildFile;
    
    public Config(){
    }
    
    public static Config  createConfig() throws Exception{
        String filename = "config.json";
        URL ressource = Config.class.getResource("/config.json");
        // Inona ny manarak
        File file = new File(ressource.toURI());
        if( file.exists() ){
            // Si le fichier existe
            // Avadika objet ilay izy
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader( new FileInputStream(file) );
            return gson.fromJson(reader, Config.class);
        }else{
            throw new Exception("Please create a config.json file");
        }
    }
    
    public void deployProject( GitProject project ) throws Exception{
         // Mi -deploye ao amin'ny zareo rehetra
         String projectPath = project.getBuildedProject();
         Server[] servers = this.getServers();
         for( int i = 0; i < servers.length; i++ ){
             servers[i].transferFileUsingScp(projectPath);
         }
         
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Git getGithub() {
        return github;
    }

    public void setGithub(Git github) {
        this.github = github;
    }

    public Server getHaproxy() {
        return haproxy;
    }

    public void setHaproxy(Server haproxy) {
        this.haproxy = haproxy;
    }

    public Server[] getServers() {
        return servers;
    }

    public void setServers(Server[] servers) {
        this.servers = servers;
    }

    public String getSrc_folder() {
        return src_folder;
    }

    public void setSrc_folder(String src_folder) {
        this.src_folder = src_folder;
    }

    public String getBuildFile() {
        return buildFile;
    }

    public void setBuildFile(String buildFile) {
        this.buildFile = buildFile;
    }
}
