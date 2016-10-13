/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.control;

import br.beholder.pethfinder.core.model.Mapa;
import br.beholder.pethfinder.ui.MainPanel;

/**
 *
 * @author lite
 */
public class SimulationController {
    Mapa mapa;
    MainPanel mainPanel;
    String tipo;

    public SimulationController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
