/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.control;

import br.beholder.pethfinder.core.model.Mapa;
import br.beholder.pethfinder.core.reader.PFReader;
import br.beholder.pethfinder.core.reader.XMLReader;
import br.beholder.pethfinder.core.search.AStar;
import br.beholder.pethfinder.ui.MainPanel;
import br.beholder.pethfinder.ui.swing.MapRenderer;
import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;
import java.awt.Image;

/**
 *
 * @author lite
 */
public class NormalPathController {
    Mapa mapa;
    MainPanel mainPanel;
    String tipo;
    
    private void setPathImage(){
        Image image= MapRenderer.getInstance().getFirstImage(mapa);
        WebImage webImage = new WebImage(image);
        webImage.setDisplayType(DisplayType.fitComponent);
        mainPanel.getImagePane().removeAll();
        mainPanel.getImagePane().add(webImage);
        mainPanel.getImagePane().revalidate();
    }

    public NormalPathController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void readPF(){
        mapa = PFReader.lerPF();
        setPathImage();
    }
    
    public void calculate(){
        if(mapa!=null){
            mapa.setPathMatrix(AStar.getInstance().getPath(mapa,mainPanel.getCalcType(),false));
            mainPanel.getConsoleArea().setText(AStar.getInstance().getConsoleLog());
            if(mapa.getPathMatrix()!=null){
                setPathImage();
            }
        }
    }
    
    public void readXML(){
        mapa = XMLReader.lerXML();
        setPathImage();
    }
}
