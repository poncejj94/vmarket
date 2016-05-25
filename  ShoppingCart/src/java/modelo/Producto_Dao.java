/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import entidad.Producto;
import java.util.ArrayList;
import org.hibernate.Session;

public class Producto_Dao {
    public List<Producto> listar()
    {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Producto> lista = new ArrayList<Producto>();
        try{
            s.beginTransaction();
            lista = s.createCriteria(Producto.class).list();
            s.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            s.getTransaction().rollback();
        }
        return lista;
    }
}
