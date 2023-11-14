/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frame;

import deploiement.configuration.Config;
import frame.panel.Project;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author sarobidy
 */
public class MainFrame extends JFrame {
     
    Config config;
    Project projectPanel;
    
    public MainFrame(){
        
    }
    
    public MainFrame(Config config){
        this.setConfig(config);
        this.init();
    }
    
    public void init(){
        this.setLayout(new GridLayout(1,1));
        this.setProjectPanel( new Project(this.getConfig())  );
        this.setTitle("Deploiement Administrator");
        this.setLocationRelativeTo(null);
        this.setSize(500, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.getProjectPanel());
        this.setVisible(true);
    }

    public Project getProjectPanel() {
        return projectPanel;
    }

    public void setProjectPanel(Project projectPanel) {
        this.projectPanel = projectPanel;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
    
    public void reloadConfig() throws Exception{
        this.setConfig( Config.createConfig() );
    }
}
