/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.List;
import model.MunicipioService;
import model.Municipio;
import util.JPAUtil;

/**
 *
 * @author caian
 */
public class Application {
    public static void main(String[] args) {
       MunicipioService municipioService = new MunicipioService();

       List<Municipio> municipios = municipioService.listarMunicipiosVizinhos("Campina Grande");
       for (Municipio m: municipios){
            System.out.println(m);
       }  
       
       double distancia = municipioService.distanciaEntreMunicipios("João Pessoa", "Guarabira");
       System.out.println(distancia);
       
       
       
       JPAUtil.close();
    }
}
