/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deploiement.server;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

/**
 *
 * @author sarobidy
 */
public class Haproxy {
    
    String ip;
    String name;
    int port;
    String backend;
    String bindTo;
    String conf;
    Server[] servers;

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }
    
    public Server[] getServers() {
        return servers;
    }

    public void setServers(Server[] servers) {
        this.servers = servers;
    }
    
    public String getBindTo() {
        return bindTo;
    }

    public void setBindTo(String bindTo) {
        this.bindTo = bindTo;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }
    
    public void createFrontEndFile() throws Exception {
        String front_end = "frontend " + this.getName() + "\n";
        String binding = "\t bind " + this.getBindTo() + ":" + this.getPort() + "\n" ;
        String mode = "\t mode http \n" ;
        String back = "\t default_backend " + this.getBackend() + "\n";
        // Okey mila avadika file io
        File file = new File( "./front_end.cfg" );
        file.createNewFile();
        PrintWriter writer = new PrintWriter(file);
        writer.write(front_end);
        writer.write(binding);
        writer.write(mode);
        writer.write(back);
        writer.flush();
        writer.close();
    }
    
    public void createBackEndFile() throws Exception{
        // Ilay server fotsiny no mila ovaina ny ip any
        String backend_template = "./backend.cfg";
        File backend = new File(backend_template);
        // Okey ny manaraka dia ny hoe izao atao
        // andao atao hoe vakiana daholo ny ao
        InputStreamReader str = new InputStreamReader(new FileInputStream(backend));
        BufferedReader bfr = new BufferedReader(str);
        String line = null;
        StringBuilder builder = new StringBuilder();
        while( (line = bfr.readLine()) != null ){
            builder.append(line + "\n");
        }
        String conf = builder.toString();
        // misy pourcentage f any mila tenenina hoe mivadika list ana string
        StringBuilder servers_lines = new StringBuilder();
        for( Server server : this.getServers() ){
                line = "\t server " + server.getIp() + ":" + server.getPort();
                servers_lines.append(line + "\n");
        }
        String servs = servers_lines.toString();
        conf = String.format(conf, servs);
        
        File file = new File("./back_end.cfg");
        file.createNewFile();
        try( PrintWriter out = new PrintWriter(file) ){
            out.println(conf);
        }
        bfr.close();
        str.close();
    }
    
    public static Haproxy getCurrentHaproxy() throws Exception{
      
        URL url = Haproxy.class.getResource("/haproxy.json");
        File file = new File( url.toURI() );
        if( file.exists() ){
            // Si le fichier existe
            // Avadika objet ilay izy
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader( new FileInputStream(file) );
            return gson.fromJson(reader, Haproxy.class);
        }else{
            throw new Exception("Please create a config.json file");
        }
//        return ha;
    }
    
}
