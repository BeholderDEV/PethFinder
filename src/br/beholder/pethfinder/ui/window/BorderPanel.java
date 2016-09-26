/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.ui.window;


import br.beholder.pethfinder.Lancador;
import br.beholder.pethfinder.ui.swing.webLaf.WeblafUtils;
import br.beholder.pethfinder.ui.utils.ColorController;
import com.alee.laf.button.WebButton;
import com.alee.utils.swing.MouseEventRunnable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lite
 */
public class BorderPanel extends JPanel {

        private JPanel buttonsPanel;
        private JLabel textPanel;
        private JLabel iconPanel;
        
        private WebButton closeButton;
        private Image close;
        private WebButton resizeButton;
        private Image resize;
        private WebButton minButton;
        private Image min;
        private Image icon;
        int pX, pY;

        public BorderPanel() {
            
            try {
                close = ImageIO.read(getClass().getResource("/br/beholder/pethfinder/ui/resources/window_close.png"));
                min = ImageIO.read(getClass().getResource("/br/beholder/pethfinder/ui/resources/window_min.png"));
                icon = ImageIO.read(getClass().getResource("/br/beholder/pethfinder/ui/resources/icon32.png"));
            } catch (IOException ex) {
                Logger.getLogger(BorderPanel.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            JFrame frame = Lancador.getJFrame();
            setLayout(new BorderLayout());
            
            buttonsPanel = new JPanel();
            textPanel = new JLabel();
            textPanel.setText("Peth Finder");
            textPanel.setForeground(ColorController.COR_LETRA);
            textPanel.setHorizontalAlignment(JLabel.CENTER);
            
            iconPanel = new JLabel();
            iconPanel.setIcon(new ImageIcon(icon));
            
            setBackground(ColorController.FUNDO_ESCURO);
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonsPanel.setOpaque(false);
            
            closeButton=new WebButton();
            closeButton.setIcon(new ImageIcon(close));
            closeButton.onMouseClick(new MouseEventRunnable() {
                @Override
                public void run(MouseEvent me) {
                    System.exit(0);
                }
            });
            
            WeblafUtils.configurarBotao(closeButton, ColorController.COR_PRINCIPAL, ColorController.COR_LETRA,ColorController.PROGRESS_BAR, Color.orange, 5);
            buttonsPanel.add(closeButton);
            add(iconPanel, BorderLayout.WEST);
            add(textPanel, BorderLayout.CENTER);
            add(buttonsPanel, BorderLayout.EAST);
            
            
            
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    // Get x,y and store them
                    pX = me.getX();
                    pY = me.getY();

                }

                 public void mouseDragged(MouseEvent me) {

                    frame.setLocation(frame.getLocation().x + me.getX() - pX,frame.getLocation().y + me.getY() - pY);
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent me) {

                    frame.setLocation(frame.getLocation().x + me.getX() - pX,frame.getLocation().y + me.getY() - pY);
                }
            });
        }
    }
