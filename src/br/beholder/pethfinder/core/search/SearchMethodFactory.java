/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.beholder.pethfinder.core.search;

import br.beholder.pethfinder.control.NormalPathController;
import br.beholder.pethfinder.core.model.Mapa;

/**
 *
 * @author lite
 */
public class SearchMethodFactory {
    public static DefaultAStarMethod create(String tipo, boolean step, NormalPathController controller, Mapa mapa){
        if(tipo.contains("Gulosa")){
            return new BuscaGulosa(step, controller, mapa);
        }
        else{
            return new BuscaRaio(step, controller, mapa);
        }
    }
}
