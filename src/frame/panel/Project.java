/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frame.panel;

import deploiement.configuration.Config;
import deploiement.git.GitProject;
import frame.panel.listener.DeployListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author sarobidy
 */

public class Project extends JPanel {
     
    String[] COLUMNS = {"Project Name" , "Project Full Name", "Project URLS"};
    
    JTable jtable;
    Config config;
    DeployListener deployListener;
    JButton deployButton;
    
    public Project( Config config ){
        this.setConfig(config);
        this.init();
    }
    
    public void paint(){
        
    }
    
    final void init(){
        GitProject[] projects = this.getConfig().getGithub().getProjects();
        Object[][] datas = new Object[ projects.length ][ COLUMNS.length ];
        this.setDatas(projects, datas);
        JTable table = new JTable(datas, COLUMNS);
        this.setJtable(table);
        this.setLayout(new BorderLayout(20, 20));
        JScrollPane scroll = new JScrollPane(table);
        this.add( scroll, BorderLayout.WEST );
        this.setDeployButton(new JButton("Deployer le projet"));
        this.setDeployListener(new DeployListener(this));
        this.getDeployButton().addMouseListener(this.getDeployListener());
        this.add(this.getDeployButton(), BorderLayout.EAST);
    }
    
    void setDatas( GitProject[] projects, Object[][] datas ){  
        for( int i = 0; i < projects.length ; i++ ){
            Object[] data = new Object[COLUMNS.length];
            data[0] = projects[i].getName();
            data[1] = projects[i].getFull_name();
            data[2] = projects[i].getHtml_url();
            datas[i] = data;
        }
    }
    
    public JTable getJtable() {
        return jtable;
    }

    public void setJtable(JTable jtable) {
        this.jtable = jtable;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public DeployListener getDeployListener() {
        return deployListener;
    }

    public void setDeployListener(DeployListener deployListener) {
        this.deployListener = deployListener;
    }

    public JButton getDeployButton() {
        return deployButton;
    }

    public void setDeployButton(JButton deployButton) {
        this.deployButton = deployButton;
    }
    
}
