
import com.alee.laf.WebLookAndFeel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicFileChooserUI;
import ui.MainWindow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lite
 */
public class Lancador {
    
    private static final ExecutorService service = Executors.newCachedThreadPool();
    
    private MainWindow mainWindow;
    private final static Lancador application = new Lancador();
    
    private Lancador() {
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Lancador.getInstance().start();
    }

    private void start() {
        try {
            final WebLookAndFeel webLookAndFeel = new WebLookAndFeel();
            //Field defaultsTable = WebLookAndFeel.class.getField("table");
            //webLookAndFeel.getDefaults().remove("FileChooserUI");
            webLookAndFeel.getDefaults().put("FileChooserUI", BasicFileChooserUI.class);
            
                    
            javax.swing.UIManager.setLookAndFeel(webLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (SecurityException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

    
    public static Lancador getInstance(){
        return application;
    }
    
    public void performAsynchronousTask(Runnable task)
    {
        service.submit(task);
    }
}
