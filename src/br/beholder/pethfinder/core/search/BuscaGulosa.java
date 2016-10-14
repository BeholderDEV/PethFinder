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
public class BuscaGulosa extends DefaultAStarMethod{

    public BuscaGulosa(boolean stepByStep, NormalPathController controller, Mapa mapa) {
        super(stepByStep, controller, mapa);
    }

    @Override
    public void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if(t == null || closed[t.getPosition().y][t.getPosition().x])return;
        int t_final_cost = t.getHeuristicCost();
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.getFinalCost()){
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if(!inOpen)open.add(t);
        }
    }

    @Override
    public void calculate() {
        iterations=0;
        open.add(grid[startI][startJ]);
        
        Cell current;
        
        while(true){ 
            iterations++;
            current = open.poll();
            if(current==null)break;
            closed[current.getPosition().y][current.getPosition().x]=true; 

            if(current.equals(grid[endI][endJ])){
                return; 
            } 

            Cell t;  
            if(current.getPosition().y-1>=0){
                t = grid[current.getPosition().y-1][current.getPosition().x];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST); 

                if(current.getPosition().x-1>=0){                      
                    t = grid[current.getPosition().y-1][current.getPosition().x-1];
                    checkAndUpdateCost(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }

                if(current.getPosition().x+1<grid[0].length){
                    t = grid[current.getPosition().y-1][current.getPosition().x+1];
                    checkAndUpdateCost(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }
            } 

            if(current.getPosition().x-1>=0){
                t = grid[current.getPosition().y][current.getPosition().x-1];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST); 
            }

            if(current.getPosition().x+1<grid[0].length){
                t = grid[current.getPosition().y][current.getPosition().x+1];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST); 
            }

            if(current.getPosition().y+1<grid.length){
                t = grid[current.getPosition().y+1][current.getPosition().x];
                checkAndUpdateCost(current, t, current.getFinalCost()+V_H_COST); 

                if(current.getPosition().x-1>=0){
                    t = grid[current.getPosition().y+1][current.getPosition().x-1];
                    checkAndUpdateCost(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }
                
                if(current.getPosition().x+1<grid[0].length){
                   t = grid[current.getPosition().y+1][current.getPosition().x+1];
                    checkAndUpdateCost(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }  
            }
            if(stepByStep){
                controller.stepImage(closed);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                }
            }
        } 
    }
    
}
