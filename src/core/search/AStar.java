/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.search;

import core.model.Cell;
import core.model.Mapa;
import java.util.PriorityQueue;

/**
 *
 * @author 5663296
 */
public class AStar {
    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;
    
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
    
    static void checkAndUpdateCost(Cell current, Cell t, int cost){
        if(t == null || closed[t.getPosition().y][t.getPosition().x])return;
        int t_final_cost = t.getHeuristicCost()+cost;
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.getFinalCost()){
            t.setFinalCost(t_final_cost);
            t.setParent(current);
            if(!inOpen)open.add(t);
        }
    }
    
    public static void AStar(){ 
        
        //add the start location to open list.
        open.add(grid[startI][startJ]);
        
        Cell current;
        
        while(true){ 
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
    public static boolean[][] test(Mapa mapa){
           int x= mapa.getTamanho().width;
           int y= mapa.getTamanho().height;
           int si = mapa.getPontoInicial().y;
           int sj = mapa.getPontoInicial().x;
           int ei = mapa.getPontoFinal().y;
           int ej = mapa.getPontoFinal().x;
           int[][] blocked = mapa.getBlocked();
           //Reset
           grid = new Cell[x][y];
           closed = new boolean[x][y];
           open = new PriorityQueue<>((Object o1, Object o2) -> {
                Cell c1 = (Cell)o1;
                Cell c2 = (Cell)o2;

                return c1.getFinalCost()<c2.getFinalCost()?-1:
                        c1.getFinalCost()>c2.getFinalCost()?1:0;
            });
           //Set start position
           setStartCell(si, sj);  //Setting to 0,0 by default. Will be useful for the UI part
           
           //Set End Location
           setEndCell(ei, ej); 
           
           for(int i=0;i<x;++i){
              for(int j=0;j<y;++j){
                  grid[i][j] = new Cell(i, j);
                  grid[i][j].setHeuristicCost((Math.abs(i-endI)+Math.abs(j-endJ)));
//                  System.out.print(grid[i][j].heuristicCost+" ");
              }
//              System.out.println();
           }
           grid[si][sj].setFinalCost(0);
           
           /*
             Set blocked cells. Simply set the cell values to null
             for blocked cells.
           */
           for(int i=0;i<blocked.length;++i){
               setBlocked(blocked[i][0], blocked[i][1]);
           }
           
           //Display initial map
           System.out.println("Grid: ");
           for(int i=0;i<x;++i){
                for(int j=0;j<y;++j){
                   if(i==si&&j==sj)System.out.print("SO  "); //Source
                   else if(i==ei && j==ej)System.out.print("DE  ");  //Destination
                   else if(grid[i][j]!=null)System.out.printf("%-3d ", 0);
                   else System.out.print("BL  "); 
                }
                System.out.println();
            } 
            System.out.println();
           
           AStar(); 
           System.out.println("\nScores for cells: ");
           for(int i=0;i<x;++i){
               for(int j=0;j<y;++j){
                   if(grid[i][j]!=null)System.out.printf("%-3d ", grid[i][j].getFinalCost());
                   else System.out.print("BL  ");
               }
               System.out.println();
           }
           System.out.println();
            
           if(closed[endI][endJ]){
               //Trace back the path 
                boolean[][] path = new boolean[y][x];
                for(int i=0;i<y;++i){
                    for(int j=0;j<x;++j){
                        path[i][j]=false;
                    }
                }
                path[si][sj]=true;
                System.out.println("Path: ");
                Cell current = grid[endI][endJ];
                System.out.print(current);
                while(current.getParent()!=null){
                    path[current.getPosition().y][current.getPosition().x]=true;
                    System.out.print(" -> "+current.getParent());
                    current = current.getParent();
                } 
                System.out.println();
                return path;
           }else System.out.println("No possible path");
           return null;
    }
    
}