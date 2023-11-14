/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deploiement.server;

/**
 *
 * @author sarobidy
 */
public class Server {
    String ip;
    String port;
    int serverPort;
    String username;
    String password;
    String tomcat;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTomcat() {
        return tomcat;
    }

    public void setTomcat(String tomcat) {
        this.tomcat = tomcat;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    // Inona ihany koa ny azo atao
    // Ato no miteny hoe alefaso any
    
    public void transferFileUsingScp( String file ) throws Exception{
        try{
            String command = "sshpass -p '%s' scp '%s' %s@%s:%s";
            command = String.format(command, this.getPassword(), file, this.getUsername(), this.getIp(), this.getTomcat());
//            System.out.println(command);
            String[] commands = {"bash", "-c" ,command};
            ProcessBuilder builder = new ProcessBuilder(commands);
            Process process = builder.start();
            int e = process.waitFor();
            System.out.println("transferUsingScp : " + this.getTomcat() + ", reponse == " + e);
        }catch(Exception e){
            System.err.println("Une erreur s'est produite Fonction transferFileUsingScp : ");
            e.printStackTrace();
            throw e;
        }
    }
    
    // Inona no atao ato
    // Mamorona configuration back_end haproxy
    // Mamorona confiuration front_end haproxy
    
    public void createFrontEndConfig() throws Exception{
        // Mila misy front_end config any zany
        String front_end = "frontend";
    }
   
}
