/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class RolDaoImpl implements RolDao{

    @Override
    public List<Rol> selectItems() {
        List<Rol> listado = null;
        String sql ="from model.Rol";
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
    public Rol findByRol(Rol rol) {
        Rol model = null;        
        
        String sql ="from model.Rol where rol = '"+rol.getNombre()+"'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Rol) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
    
    @Override
    public Rol findById(Long id) {
        Rol model = null;        
        
        String sql ="from model.Rol where id_rol ="+id;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Rol) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> listado = null;
        String sql ="from model.Rol u";
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
    public boolean create(Rol rol) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(rol);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Rol rol) {
         boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Rol roldb = (Rol) session.load(Rol.class, rol.getIdPerfil());
            roldb.setEstado(rol.getEstado());
            roldb.setDescripcion(rol.getDescripcion());
            roldb.setNombre(rol.getNombre());
            
            session.update(roldb);
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
            Rol rol = (Rol) session.load(Rol.class, id);
            session.delete(rol);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
}
