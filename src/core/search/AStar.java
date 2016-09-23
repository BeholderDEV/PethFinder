/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.search;

import com.bethecoder.ascii_table.ASCIITable;
import core.model.Cell;
import core.model.Mapa;
import java.util.PriorityQueue;

/**
 *
 * @author 5663296
 */
public class AStar {
    private static AStar aStar;
    private String consoleLog = "";
    private String[][] costs;
    private int iterations=0;
    public final int DIAGONAL_COST = 14;
    public final int V_H_COST = 10;
    
    //Blocked cells are just null Cell values in grid
    static Cell [][] grid = new Cell[5][5];
    
    static PriorityQueue<Cell> open;
     
    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;
            
    public static void setBlocked(int i, int j){
        grid[i][j] = null;
    }
    
    public static void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }
    
    public static void setEndCell(int i, int j){
        endI = i;
        endJ = j; 
    }
    
    static void checkAndUpdateCostRaio(Cell current, Cell t, int cost){
        if(t == null || closed[t.getPosition().y][t.getPosition().x])return;
        int t_final_cost = t.getHeuristicCost()+cost;
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.getFinalCost()){
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if(!inOpen)open.add(t);
        }
    }
    static void checkAndUpdateCostGuloso(Cell current, Cell t, int cost){
        if(t == null || closed[t.getPosition().y][t.getPosition().x])return;
        int t_final_cost = t.getHeuristicCost();
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.getFinalCost()){
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if(!inOpen)open.add(t);
        }
    }
    
    private static void AStar(){
        
    }

    public String[][] getCosts() {
        return costs;
    }
    
    
    public String getConsoleLog() {
        return consoleLog;
    }
    
    public static AStar getInstance() {
        if(aStar==null){
            aStar= new AStar();
        }
        return aStar;
    }
    private void calculateGuloso(){
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
                checkAndUpdateCostGuloso(current, t, current.getFinalCost()+V_H_COST); 

                if(current.getPosition().x-1>=0){                      
                    t = grid[current.getPosition().y-1][current.getPosition().x-1];
                    checkAndUpdateCostGuloso(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }

                if(current.getPosition().x+1<grid[0].length){
                    t = grid[current.getPosition().y-1][current.getPosition().x+1];
                    checkAndUpdateCostGuloso(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }
            } 

            if(current.getPosition().x-1>=0){
                t = grid[current.getPosition().y][current.getPosition().x-1];
                checkAndUpdateCostGuloso(current, t, current.getFinalCost()+V_H_COST); 
            }

            if(current.getPosition().x+1<grid[0].length){
                t = grid[current.getPosition().y][current.getPosition().x+1];
                checkAndUpdateCostGuloso(current, t, current.getFinalCost()+V_H_COST); 
            }

            if(current.getPosition().y+1<grid.length){
                t = grid[current.getPosition().y+1][current.getPosition().x];
                checkAndUpdateCostGuloso(current, t, current.getFinalCost()+V_H_COST); 

                if(current.getPosition().x-1>=0){
                    t = grid[current.getPosition().y+1][current.getPosition().x-1];
                    checkAndUpdateCostGuloso(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }
                
                if(current.getPosition().x+1<grid[0].length){
                   t = grid[current.getPosition().y+1][current.getPosition().x+1];
                    checkAndUpdateCostGuloso(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }  
            }
        } 
    }
    private void calculateRaio(){
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
                checkAndUpdateCostRaio(current, t, current.getFinalCost()+V_H_COST); 

                if(current.getPosition().x-1>=0){                      
                    t = grid[current.getPosition().y-1][current.getPosition().x-1];
                    checkAndUpdateCostRaio(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }

                if(current.getPosition().x+1<grid[0].length){
                    t = grid[current.getPosition().y-1][current.getPosition().x+1];
                    checkAndUpdateCostRaio(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }
            } 

            if(current.getPosition().x-1>=0){
                t = grid[current.getPosition().y][current.getPosition().x-1];
                checkAndUpdateCostRaio(current, t, current.getFinalCost()+V_H_COST); 
            }

            if(current.getPosition().x+1<grid[0].length){
                t = grid[current.getPosition().y][current.getPosition().x+1];
                checkAndUpdateCostRaio(current, t, current.getFinalCost()+V_H_COST); 
            }

            if(current.getPosition().y+1<grid.length){
                t = grid[current.getPosition().y+1][current.getPosition().x];
                checkAndUpdateCostRaio(current, t, current.getFinalCost()+V_H_COST); 

                if(current.getPosition().x-1>=0){
                    t = grid[current.getPosition().y+1][current.getPosition().x-1];
                    checkAndUpdateCostRaio(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }
                
                if(current.getPosition().x+1<grid[0].length){
                   t = grid[current.getPosition().y+1][current.getPosition().x+1];
                    checkAndUpdateCostRaio(current, t, current.getFinalCost()+DIAGONAL_COST); 
                }  
            }
        } 
    }
    
    /*
    Params :
    tCase = test case No.
    x, y = Board's dimensions
    si, sj = start location's x and y coordinates
    ei, ej = end location's x and y coordinates
    int[][] blocked = array containing inaccessible cell coordinates
    */
    public boolean[][] getPath(Mapa mapa, String tipo){
        consoleLog="";
        int x= mapa.getTamanho().width;
        int y= mapa.getTamanho().height;
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
        if(tipo.contentEquals("Raio")){
            calculateRaio();
        }else{
            calculateGuloso(); 
        }
        
        costs = new String[x][y];
        for(int i=0;i<x;++i){
            for(int j=0;j<y;++j){
                if(grid[i][j]!=null){
                    costs[i][j] = "[f:"+grid[i][j].getFinalCost()+" | h:"+grid[i][j].getHeuristicCost()+"]";
//                    if(costs[i][j].length()<3){
//                        int space=3-costs[i][j].length();
//                        String sc="";
//                        for (int k = 0; k < space; k++) {
//                            sc=sc.concat("0");
//                        }
//                        costs[i][j] = sc.concat(costs[i][j]);
//                    }
                }
                else{
                    costs[i][j]= "BL";
                }
            }
        }  
        consoleLog = consoleLog.concat(ASCIITable.getInstance().getTable(new String[] {}, costs));
        if(closed[endI][endJ]){
            //Trace back the path 
             boolean[][] path = new boolean[y][x];
             for(int i=0;i<y;++i){
                 for(int j=0;j<x;++j){
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
             iterations=0;
             return path;
        }else{
            consoleLog = consoleLog.concat("No possible path");
        }
        return null;
    }
    
}