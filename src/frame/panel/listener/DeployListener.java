/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frame.panel.listener;

import deploiement.git.GitProject;
import frame.panel.Project;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author sarobidy
 */
public class DeployListener implements MouseListener{
    
    Project projet;
    
    public DeployListener(){
        
    }
    public DeployListener(Project project){
       this.setProjet(project);
    }

    public Project getProjet() {
        return projet;
    }

    public void setProjet(Project projet) {
        this.projet = projet;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        // Okey ato izy ato
        // Alaina ny project git mifanaraka
        int selected_row = this.getProjet().getJtable().getSelectedRow();
        try{
            if( selected_row > -1 ){
                // Andao ary hanao ny etape amin'izay eh
                // Etape 1 : Recuperer le projet git
                GitProject project = this.getProjet().getConfig().getGithub().getProjects()[selected_row];
                project.setPath( this.getProjet().getConfig().getSrc_folder() );
                project.setCompilation(this.getProjet().getConfig().getBuildFile());
                // Etape 2 : azo ilay project, manao controlle hoe mi-existe ve sa tsia
                project.handleProject();
                // Etape 3 : Rehefa vita handle ilay izy de verifiena ny fisian'ilay file de compilation
               project.buildProject();
               // Etape 4 : Rehefa build ilay izy de transferena any amin'ny serveur telo ilay projet.war
               String projectName = (String) JOptionPane.showInputDialog(new JFrame(), "Nom du projet(war) selon le script");
                // Etape 5 : Rehefa azo ny anaranle projet de verifiena hoe tena misy ve ilay izy sa tsia
                project.setBuildedProject(projectName);
                // Etape 6 : Deployer le projet dans tous les servers
                this.getProjet().getConfig().deployProject( project );
                
                JOptionPane.showMessageDialog(new JFrame(), "Projet déployé avec succès");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
        // Okey azo ny selected row andao ary andramana mihitsy ilay izy
        //System.out.println("row selected is : " + selected_row);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
