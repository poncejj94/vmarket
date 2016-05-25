/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class ProductoDaoImpl implements ProductoDao{

    @Override
    public List<Producto> selectItems() {
        List<Producto> listado = null;
        String sql ="from model.Producto";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            listado = session.createQuery(sql).list();
            tx.commit();
            
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        return listado;
    }

    @Override
    public Producto findByProducto(Producto producto) {
        Producto model = null;        
        
        String sql ="from model.Producto where producto = '"+producto.getNombre()+"'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Producto) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
    
    @Override
    public Producto findById(Long id) {
        Producto model = null;        
        
        String sql ="from model.Producto where id_producto ="+id;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Producto) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> listado = null;
        String sql ="from model.Producto u";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            listado = session.createQuery(sql).list();
            tx.commit();
            
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        return listado;
    }

    
    @Override
    public List<Producto> findAllByCtegoria(Integer categoria) {
        List<Producto> listado = null;
        String sql ="from model.Producto where categoria ='"+categoria+"'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            listado = session.createQuery(sql).list();
            tx.commit();
            
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        return listado;
    }

    
    @Override
    public boolean create(Producto producto) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(producto);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Producto producto) {
         boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Producto productodb = (Producto) session.load(Producto.class, producto.getIdProducto());
            productodb.setNombre(producto.getNombre());
            productodb.setPrecio(producto.getPrecio());
            productodb.setImagen(producto.getImagen());
            
            session.update(productodb);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Producto producto = (Producto) session.load(Producto.class, id);
            session.delete(producto);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
}
