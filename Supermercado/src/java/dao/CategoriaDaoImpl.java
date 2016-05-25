/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class CategoriaDaoImpl implements CategoriaDao{

    @Override
    public List<Categoria> selectItems() {
        List<Categoria> listado = null;
        String sql ="from model.Categoria";
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
    public Categoria findByCategoria(Categoria categoria) {
        Categoria model = null;        
        
        String sql ="from model.Categoria where categoria = '"+categoria.getNombre()+"'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Categoria) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
    
    @Override
    public Categoria findById(Long id) {
        Categoria model = null;        
        
        String sql ="from model.Categoria where id_categoria ="+id;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Categoria) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> listado = null;
        String sql ="from model.Categoria";
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
    public boolean create(Categoria categoria) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(categoria);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Categoria categoria) {
         boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Categoria categoriadb = (Categoria) session.load(Categoria.class, categoria.getIdCategoria());
            categoriadb.setEstado(categoria.getEstado());
            categoriadb.setNombre(categoria.getNombre());
            
            session.update(categoriadb);
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
            Categoria categoria = (Categoria) session.load(Categoria.class, id);
            session.delete(categoria);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
}
