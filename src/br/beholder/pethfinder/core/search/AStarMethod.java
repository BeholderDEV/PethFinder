/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.core.search;

import br.beholder.pethfinder.core.model.Cell;

/**
 *
 * @author lite
 */
public interface AStarMethod {
    public void checkAndUpdateCost(Cell current, Cell t, int cost);
    public void calculate();
}
