/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package deploiement;

import com.google.gson.Gson;
import deploiement.configuration.Config;
import deploiement.server.Haproxy;
import frame.MainFrame;

/**
 *
 * @author sarobidy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception  {
//        Config config = Config.createConfig();
//        config.getGithub().getAllProjects();
//            run();
        Haproxy haproxy = Haproxy.getCurrentHaproxy();
        haproxy.createBackEndFile();
//        String file = "/home/sarobidy/Test.java";
//        for( Server server: config.getServers() ){
//            server.transferFileUsingScp(file);
//        }
        
        // Ny atao manaraka de mi-test git pull amin'ny zavatra hafa
        // Andao ary eh
        // Okey vita ny clone , pull indray izao
        // Andao andramana ary
        // Maintenant lorsque le boutton deployer est cliquer
        
    }
    
    public static void runScp(  ) throws Exception{
        String[] command = {"bash", "-c" ,"sshpass -p 'MySoulM' scp /home/sarobidy/Test.java sarobidy@localhost:/home/sarobidy/Desktop"};
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        process.waitFor();
    }
    
    public static void run() throws Exception{
        // TODO code application logic here
        Config config = Config.createConfig();
        config.getGithub().getAllProjects();
        MainFrame frame = new MainFrame(config);
//        ServerFrame frame = new ServerFrame(config);
        
//        System.out.println("Config name = " + config.getName());
//        System.out.println("Config github = " + config.getGithub().getUsername());
//        System.out.println("Config haproxy ip = " + config.getHaproxy().getIp());
//        System.out.println("Config haproxy port = " + config.getHaproxy().getIp());
//        Server[] servers = config.getServers();
//        for( int i =0 ; i < servers.length; i++ ){
//                System.out.println("Server number " + (i+1));
//                System.out.println("------------- Ip = " + servers[i].getIp());
//                System.out.println("------------- Port = " + servers[i].getPort());
//                System.out.println("------------- Username = " + servers[i].getUsername());
//                System.out.println("------------- Password = " + servers[i].getPassword());
//                System.out.println("------------- Tomcat = " + servers[i].getTomcat());
//        }
        
//           System.out.println("All projects of " + config.getGithub().getUsername() + " are : ");
//        for( int i = 0; i < config.getGithub().getProjects().length ; i++ ){
//            System.out.println("===== name == " + config.getGithub().getProjects()[i].getName());
//            System.out.println("===== Full name == " + config.getGithub().getProjects()[i].getFull_name());
//            System.out.println("===== Http url == " + config.getGithub().getProjects()[i].getHtml_url());
//        }
    }
    
}
