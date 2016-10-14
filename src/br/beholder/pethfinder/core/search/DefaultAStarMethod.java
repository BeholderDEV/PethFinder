/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.core.search;

import br.beholder.pethfinder.control.NormalPathController;
import br.beholder.pethfinder.core.model.Cell;
import br.beholder.pethfinder.core.model.Mapa;
import com.bethecoder.ascii_table.ASCIITable;
import java.util.PriorityQueue;

/**
 *
 * @author lite
 */
public abstract class DefaultAStarMethod implements AStarMethod{
    private String consoleLog = "";
    private String[][] costs;
    int iterations=0;
    int delay = 100;
    public final int DIAGONAL_COST = 14;
    public final int V_H_COST = 10;
    boolean stepByStep;
    NormalPathController controller;
    private Mapa mapa;
    
    Cell [][] grid = new Cell[5][5];
    
    PriorityQueue<Cell> open;
     
    boolean closed[][];
    int startI, startJ;
    int endI, endJ;

    public DefaultAStarMethod(boolean stepByStep, NormalPathController controller, Mapa mapa) {
        this.stepByStep = stepByStep;
        this.controller = controller;
        this.mapa = mapa;
    }
    
    public void setDelay(int del){
        delay = del;
    }
    public void setBlocked(int i, int j){
        grid[i][j] = null;
    }
    
    public void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }
    
    public void setEndCell(int i, int j){
        endI = i;
        endJ = j; 
    }
    
    public String[][] getCosts() {
        return costs;
    }
    
    public String getConsoleLog() {
        return consoleLog;
    }
    
    public int getIterations() {
        return iterations;
    }
    
    
    public boolean[][] getPath(){
        consoleLog="";
        int x= mapa.getTamanho().height;
        int y= mapa.getTamanho().width;
        int si = mapa.getPontoInicial().y;
        int sj = mapa.getPontoInicial().x;
        int ei = mapa.getPontoFinal().y;
        int ej = mapa.getPontoFinal().x;
        int[][] blocked = mapa.getBlocked();
        grid = new Cell[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
             Cell c1 = (Cell)o1;
             Cell c2 = (Cell)o2;

             return c1.getFinalCost()<c2.getFinalCost()?-1:
                     c1.getFinalCost()>c2.getFinalCost()?1:0;
         });
        setStartCell(si, sj);

        setEndCell(ei, ej); 

        for(int i=0;i<x;++i){
           for(int j=0;j<y;++j){
               grid[i][j] = new Cell(i, j);
               grid[i][j].setHeuristicCost((Math.abs(i-endI)+Math.abs(j-endJ)));
           }
        }
        grid[si][sj].setFinalCost(0);
        for(int i=0;i<blocked.length;++i){
            setBlocked(blocked[i][0], blocked[i][1]);
        }
        
        calculate();
        
        costs = new String[x][y];
        for(int i=0;i<x;++i){
            for(int j=0;j<y;++j){
                if(grid[i][j]!=null){
                    costs[i][j] = "[f:"+grid[i][j].getFinalCost()+" | h:"+grid[i][j].getHeuristicCost()+"]";
                }
                else{
                    costs[i][j]= "BL";
                }
            }
        }  
        consoleLog = consoleLog.concat(ASCIITable.getInstance().getTable(new String[] {}, costs));
        if(closed[endI][endJ]){
            //Trace back the path 
             boolean[][] path = new boolean[x][y];
             for(int i=0;i<x;++i){
                 for(int j=0;j<y;++j){
                     path[i][j]=false;
                 }
             }
             consoleLog = consoleLog.concat("Path: \n");
             Cell current = grid[endI][endJ];
             consoleLog = consoleLog.concat(current+"");
             while(current.getParent()!=null){
                 path[current.getPosition().y][current.getPosition().x]=true;
                 consoleLog = consoleLog.concat(" -> "+current.getParent());
                 current = current.getParent();
             } 
             consoleLog = consoleLog.concat("\n");
             consoleLog = consoleLog.concat("Iterationr: "+iterations);
             
             return path;
        }else{
            consoleLog = consoleLog.concat("No possible path");
        }
        return null;
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
                controller.stepImage(closed,grid);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                }
            }
        } 
    }
}
