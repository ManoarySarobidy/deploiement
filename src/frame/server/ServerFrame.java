/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frame.server;

import deploiement.configuration.Config;
import frame.panel.server.ServerPanel;
import javax.swing.JFrame;

/**
 *
 * @author sarobidy
 */
public class ServerFrame extends JFrame{
    
    Config config;
    ServerPanel server ;
    
    public ServerFrame(Config config){
        this.setConfig(config);
        this.init();
    }
    
    public void init(){
         this.setServer( new ServerPanel(config) );
         this.setTitle("Deploiement 1.0 - Servers");
         this.setSize(300, 500);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLocationRelativeTo(null);
         this.add(this.getServer());
         this.setVisible(true);
    }

    public ServerPanel getServer() {
        return server;
    }

    public void setServer(ServerPanel server) {
        this.server = server;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
    
}
