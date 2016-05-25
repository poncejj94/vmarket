/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class ClienteDaoImpl implements ClienteDao{

    @Override
    public List<Cliente> selectItems() {
        List<Cliente> listado = null;
        String sql ="from model.Cliente";
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
    public Cliente findByCliente(Cliente cliente) {
        Cliente model = null;        
        
        String sql ="from model.Cliente where cliente = '"+cliente.getNombre()+"'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Cliente) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
    
    @Override
    public Cliente findById(Long id) {
        Cliente model = null;        
        
        String sql ="from model.Cliente where id_cliente ="+id;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Cliente) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> listado = null;
        String sql ="from model.Cliente u";
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
    public boolean create(Cliente cliente) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(cliente);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Cliente cliente) {
         boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Cliente clientedb = (Cliente) session.load(Cliente.class, cliente.getIdCliente());
            clientedb.setCedula(cliente.getCedula());
            clientedb.setNombre(cliente.getNombre());
            clientedb.setEmail(cliente.getEmail());
            clientedb.setDireccion(cliente.getDireccion());
            
            session.update(clientedb);
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
            Cliente cliente = (Cliente) session.load(Cliente.class, id);
            session.delete(cliente);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
}
