package br.beholder.pethfinder;


import com.alee.laf.WebLookAndFeel;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicFileChooserUI;
import br.beholder.pethfinder.ui.MainPanel;
import br.beholder.pethfinder.ui.window.ComponentResizer;
import br.beholder.pethfinder.ui.window.OutsidePanel;

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
    private static JFrame frame = new JFrame();
    private MainPanel mainWindow;
    private final static Lancador application = new Lancador();
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Lancador.getInstance().start();
    }
    
    public static JFrame getJFrame(){
        return frame;
    }
    
    private void start() {
        try {
            final WebLookAndFeel webLookAndFeel = new WebLookAndFeel();
            //Field defaultsTable = WebLookAndFeel.class.getField("table");
            //webLookAndFeel.getDefaults().remove("FileChooserUI");
            webLookAndFeel.getDefaults().put("FileChooserUI", BasicFileChooserUI.class);
            
                    
            javax.swing.UIManager.setLookAndFeel(webLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (SecurityException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Image icon;
                try {
                    icon = ImageIO.read(getClass().getResource("/br/beholder/pethfinder/ui/resources/icon.png"));
                    frame.setIconImage(icon);
        //            getFrame().setIconImage(icon);
                } catch (IOException ex) {
                    Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                ComponentResizer cr = new ComponentResizer();
                cr.setMinimumSize(new Dimension(300, 300));
                cr.setMaximumSize(new Dimension(1920,1080));
                cr.registerComponent(frame);
                cr.setSnapSize(new Dimension(10, 10));
                frame.setUndecorated(true);
                frame.add(new OutsidePanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
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
