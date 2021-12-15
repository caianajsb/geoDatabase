/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Date;
import java.util.List;
import model.Alert;
import model.AlertService;
import model.Cliente;
import model.Municipio;
import util.JPAUtil;

/**
 *
 * @author caian
 */
public class Application {
    public static void main(String[] args) {
       AlertService mgr = new AlertService();
       
       mgr.createAndStoreAlert("Aula BDGeo...", new Date(), "POINT(1 2)");
       List<Alert> lista = mgr.listAlerts();
       System.out.println(lista);
       Cliente cliente = new Cliente();
       cliente.setNome("Nome do Meu Cliente...");
       mgr.gravarCliente(cliente);
       
       List<Municipio> municipios = mgr.listarMunicipiosVizinhos("Guarabira");
       for (Municipio m: municipios){
            System.out.println(m);
       }      
       
       JPAUtil.close();
    }
}
