/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.core.search;

import br.beholder.pethfinder.control.NormalPathController;
import br.beholder.pethfinder.core.model.Cell;
import br.beholder.pethfinder.core.model.Mapa;

/**
 *
 * @author lite
 */
public class BuscaRaio extends DefaultAStarMethod{

    public BuscaRaio(boolean stepByStep, NormalPathController controller, Mapa mapa) {
        super(stepByStep, controller, mapa);
    }

    @Override
    public void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if(t == null || closed[t.getPosition().y][t.getPosition().x])return;
        int t_final_cost = t.getHeuristicCost()+cost;
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.getFinalCost()){
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if(!inOpen)open.add(t);
        }
    }
}
