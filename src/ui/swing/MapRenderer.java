/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.swing;

import core.model.Mapa;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author lite
 */
public class MapRenderer extends JComponent{
    Mapa mapa;
    Image tile_set;
    Image cat_sprite;
    int tile_size = 32;

    public MapRenderer(Mapa mapa) {
        this.mapa = mapa;
        try {
            tile_set =  ImageIO.read(getClass().getResource("/ui/resources/tileset.png"));
            cat_sprite =  ImageIO.read(getClass().getResource("/ui/resources/cat.png"));
        } catch (Exception ex) {
            Logger.getLogger(MapRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        System.out.println("print");
        
        g2d.setColor(Color.DARK_GRAY);
        int[][] matrix = mapa.getMatrix();
        for (int i = 0; i < mapa.getTamanho().height; i++) {
            for (int j = 0; j < mapa.getTamanho().width; j++) {
                g2d.drawImage(tile_set, j*tile_size, i*tile_size, (j+1)*tile_size, (i+1)*tile_size, matrix[i][j]*tile_size, 0, (matrix[i][j]+1)*tile_size, tile_size, this);
                if(i==mapa.getPontoInicial().y && j==mapa.getPontoInicial().x){
                    g2d.drawImage(cat_sprite, j*tile_size, i*tile_size, (j+1)*tile_size, (i+1)*tile_size, matrix[i][j]*tile_size, 0, (matrix[i][j]+1)*tile_size, tile_size, this);
                } else if(i==mapa.getPontoFinal().y && j==mapa.getPontoFinal().x){
                    g2d.fillRect(j*tile_size, i*tile_size, tile_size, tile_size);
                }
//                g2d.drawRect(j*tile_size, i*tile_size, tile_size, tile_size);
            }
        }
    }
    
}
