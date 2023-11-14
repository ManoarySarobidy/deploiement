/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frame.panel.server;

import deploiement.configuration.Config;
import deploiement.server.Server;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author sarobidy
 */
public class ServerPanel extends JPanel {
    
    String[] COLUMNS = {"Server Ip" , "Server port", "Server tomcat" };
    JTable table;
    Config config;
    
    public ServerPanel(Config config){
            this.setConfig(config);
            this.init();
    }
    
    public void init(){
        
        Server[] servers = this.getConfig().getServers();
        Object[][] datas = new Object[servers.length][ COLUMNS.length ];
        for( int i = 0; i < servers.length ; i++ ){
                Object[] data = new Object[COLUMNS.length];
                data[0] = servers[i].getIp();
                data[1] = servers[i].getPort();
                data[2] = servers[i].getTomcat();
                datas[i] = data;
        }
        JTable t = new JTable(datas, COLUMNS);
        JScrollPane scroll = new JScrollPane(t);
        this.add(scroll);
        // Okey azo ny servers
        // Inona no azo atao
        // Apoitra fotsiny aloha io
        
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }    
}
