package model;


import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class MunicipioService {
     
    
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
     
     //Qual é a distância entre os municípios de João Pessoa e Guarabira?
     public double distanciaEntreMunicipios(String municipioA, String municipioB){
        double result = 0;
        EntityManager em = JPAUtil.createEntityManager();   
        em.getTransaction().begin();       

        Query query = em.createQuery("select distance(ma.geometria, mb.geometria) from Municipio ma, Municipio mb where ma.nome = :municipioA  and   mb.nome = :municipioB");
        query.setParameter("municipioA", municipioA);
        query.setParameter("municipioB", municipioB);

        result = (double)query.getSingleResult();
        
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