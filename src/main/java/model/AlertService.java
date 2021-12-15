package model;


import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class AlertService {
    
    public void gravarCliente(Cliente cliente){    
        EntityManager em = JPAUtil.createEntityManager();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close(); 
 
    }

    public void createAndStoreAlert(String title, Date theDate, String wktPoint) {
        Geometry geom = wktToGeometry(wktPoint);

        if (!geom.getGeometryType().equals("Point")) {
            throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
        }

        EntityManager em = JPAUtil.createEntityManager();

        em.getTransaction().begin();

        Alert theAlert = new Alert();
        theAlert.setTitle(title);
        theAlert.setDate(theDate);
        theAlert.setLocation((Point) geom);
        em.persist(theAlert);
        em.getTransaction().commit();
        em.close();     
    }
    
    public List<Alert> listAlerts(){
        List<Alert> result = null;
        EntityManager em = JPAUtil.createEntityManager();
        Geometry filter = wktToGeometry("POLYGON((1 1,20 1,20 20,1 20,1 1))");

        em.getTransaction().begin();  
        Query query = em.createQuery("select a from Alert a where distance(a.location, :filter) < 1");//, Alert.class);
        query.setParameter("filter", filter);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }
    
     public List<Municipio> listarMunicipiosVizinhos(String nome){
        List<Municipio> result = null;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select mb from Municipio ma, Municipio mb where touches(ma.geometria, mb.geometria) = true and ma.nome = :nome");//, Alert.class);
        query.setParameter("nome", nome);
        result = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return result;
    }

    
    private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }
    
}