/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deploiement.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author sarobidy
 */
public class GitProject {
    
    String name;
    String full_name;
    String html_url;
    
    String path;
    String compilation;
    String buildedProject;

    public String getBuildedProject() {
        return buildedProject;
    }

    public void setBuildedProject(String buildedProject)  throws Exception{
        if( buildedProject == null || (buildedProject != null && buildedProject.isEmpty()) ) throw new Exception("Veuillez verifier le nom de projet que vous avez entrer");
        String nPath = this.getPath() + File.separator + this.getName() + File.separator + buildedProject;
        File file = new File(nPath);
        if( !file.exists() ) throw new Exception("Le projet " + buildedProject + " n'existe pas à la racine du projet. Veuillez le deplacer à la racine où le recréer à la racine");
        this.buildedProject = nPath;
    }
    public String getCompilation() {
        return compilation;
    }

    public void setCompilation(String compilation) {
        this.compilation = compilation;
    }
  
    public void setPath(String path){
        this.path = path;
    }
    public String getPath(){
        return this.path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
    
    public void buildProject() throws Exception{
        String p = this.getPath() + File.separator + this.getName() ;
        String nPath = p + File.separator + this.getCompilation();
        File file = new File( nPath );
        if( !file.exists() ) throw new Exception("Please verify if the compilation file : " + this.getCompilation() + " exist in your project");
        ProcessBuilder builder = new ProcessBuilder( nPath   );
        builder.directory( new File(p) );
        //ProcessBuilder builder = new ProcessBuilder("bash", "-c", "cp", "/home/sarobidy/Test.java", "/home/sarobidy/Desktop/github/"  );
        System.out.println(builder.command());
        Process process = builder.start();
        InputStream stream = process.getErrorStream();
        InputStream st =process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while( (line = reader.readLine()) != null ){
            sb.append(line);
        }
        reader.close();
        reader = new BufferedReader(new InputStreamReader(st));
        line = null;
        int e =process.waitFor();
        System.out.println("e == " + e);
        while( (line = reader.readLine()) != null ){
            System.out.println("Message : " + line);
        }
        System.err.println("Error is : " + sb.toString());
    }
    
    public void handleProject() throws Exception{
        String nPath = this.getPath() + File.separator + this.getName();
        File file = new File( nPath );
        System.out.println("nPath handleProject : "  + nPath);
        if( file.exists() ) this.pullProject();
        else this.cloneProject();
    }
    
    public void cloneProject() throws Exception{
        String command = "git clone %s %s";
        String nPath = this.getPath() + File.separator + this.getName();
        System.out.println(nPath);
        command = String.format(command, this.getHtml_url(), nPath);
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", command );
        builder.start().waitFor();
    }
    
    public void pullProject() throws Exception{
        String command = "git -C %s pull";
        String nPath = this.getPath() + File.separator + this.getName() + File.separator;
        command = String.format(command, nPath);
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", command );
        builder.start().waitFor();
    }
    
}
