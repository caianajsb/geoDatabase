/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Date;
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
       MunicipioService mgr = new MunicipioService();

       List<Municipio> municipios = mgr.listarMunicipiosVizinhos("Campina Grande");
       for (Municipio m: municipios){
            System.out.println(m);
       }  
       
       double distancia = mgr.distanciaEntreMunicipios("João Pessoa", "Guarabira");
       System.out.println(distancia);
       
       JPAUtil.close();
    }
}
