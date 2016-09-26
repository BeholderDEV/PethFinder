/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.ui.swing;

import br.beholder.pethfinder.core.model.Mapa;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author lite
 */
public class MapRenderer extends JComponent{

    static Image tile_set;
    static Image cat_sprite;
    static ImageObserver observer;
    static int tile_size = 64;
    static int base = 500;
    static private MapRenderer mapRenderer;

    private MapRenderer() {
        try {
            tile_set =  ImageIO.read(getClass().getResource("/br/beholder/pethfinder/ui/resources/tileset.png"));
            cat_sprite =  ImageIO.read(getClass().getResource("/br/beholder/pethfinder/ui/resources/cat.png"));
            observer= new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return true;
                }
            };
        } catch (Exception ex) {
            Logger.getLogger(MapRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MapRenderer getInstance() {
        if(mapRenderer==null){
            mapRenderer=new MapRenderer();
        }
        return mapRenderer;
    }
    
    public Image getFirstImage(Mapa mapa) {
        
        BufferedImage bi = new BufferedImage((mapa.getMatrix().length+1)*tile_size, (mapa.getMatrix()[0].length+1)*tile_size/2, BufferedImage.TYPE_4BYTE_ABGR);
        base = mapa.getMatrix().length*tile_size/2;
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        
        g2d.setColor(Color.DARK_GRAY);
        int[][] matrix = mapa.getMatrix();
        for (int i = 0; i < mapa.getTamanho().height; i++) {
            for (int j = 0; j < mapa.getTamanho().width; j++) {
                int cartX = Math.round(j*tile_size/2f);
                int cartY =  Math.round(i*tile_size/2f);
                int isoX = cartX - cartY;
                int isoY = Math.round((cartX + cartY) / 2f);
                g2d.drawImage(tile_set, base+isoX, isoY, base+isoX+tile_size, isoY+tile_size, 0, 0, tile_size, tile_size, observer);
                if(matrix[i][j]==1){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, tile_size, 0, 2*tile_size, tile_size, observer);
                }
                
                if(i==mapa.getPontoInicial().y && j==mapa.getPontoInicial().x){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, 2*tile_size, 0, 3*tile_size, tile_size, observer);
                } else if(i==mapa.getPontoFinal().y && j==mapa.getPontoFinal().x){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, 3*tile_size, 0, 4*tile_size, tile_size, observer);
                }
                
//                g2d.drawRect(j*tile_size, i*tile_size, tile_size, tile_size);
            }
        }
        return bi;
    }
    public Image getPathedImage(Mapa mapa) {
        
        BufferedImage bi = new BufferedImage((mapa.getMatrix().length+1)*tile_size, (mapa.getMatrix()[0].length+1)*tile_size/2, BufferedImage.TYPE_4BYTE_ABGR);
        base = mapa.getMatrix().length*tile_size/2;
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        
        g2d.setColor(Color.DARK_GRAY);
        int[][] matrix = mapa.getMatrix();
        for (int i = 0; i < mapa.getTamanho().height; i++) {
            for (int j = 0; j < mapa.getTamanho().width; j++) {
                int cartX = Math.round(j*tile_size/2f);
                int cartY =  Math.round(i*tile_size/2f);
                int isoX = cartX - cartY;
                int isoY = Math.round((cartX + cartY) / 2f);
                g2d.drawImage(tile_set, base+isoX, isoY, base+isoX+tile_size, isoY+tile_size, 0, 0, tile_size, tile_size, observer);
                if(matrix[i][j]==1){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, tile_size, 0, 2*tile_size, tile_size, observer);
                }
                if(mapa.getPathMatrix()[i][j]){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, 4*tile_size, 0, 5*tile_size, tile_size, this);
                }
                if(i==mapa.getPontoInicial().y && j==mapa.getPontoInicial().x){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, 2*tile_size, 0, 3*tile_size, tile_size, observer);
                } else if(i==mapa.getPontoFinal().y && j==mapa.getPontoFinal().x){
                    g2d.drawImage(tile_set, base+isoX, isoY-tile_size/2, base+isoX+tile_size, isoY+tile_size/2, 3*tile_size, 0, 4*tile_size, tile_size, observer);
                }
                
//                g2d.drawRect(j*tile_size, i*tile_size, tile_size, tile_size);
            }
        }
        return bi;
    }
    
}
