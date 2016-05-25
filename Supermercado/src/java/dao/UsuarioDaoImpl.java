/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class UsuarioDaoImpl implements UsuarioDao{
    
    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;        
        
        String sql ="from model.Usuario where usuario = '"+usuario.getUsuario()+"'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Usuario) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
    
    @Override
    public Usuario findById(Long id) {
        Usuario model = null;        
        
        String sql ="from model.Usuario where id_usuario ="+id;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            
            model=(Usuario) session.createQuery(sql).uniqueResult();
            tx.commit();
            return model;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Usuario login(Usuario usuario) {
       Usuario model = findByUsuario(usuario);
       if(model!= null){
           if(!usuario.getClave().equals(model.getClave())){
               model=null;
           }
       }
        return model;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listado = null;
        String sql ="from model.Usuario u left join fetch u.rol";
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
    public boolean create(Usuario usuario) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(usuario);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Usuario usuario) {
         boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Usuario usuariodb = (Usuario) session.load(Usuario.class, usuario.getIdUsuario());
            usuariodb.setEstado(usuario.getEstado());
            usuariodb.setUsuario(usuario.getUsuario());
            usuariodb.setRol(usuario.getRol());
            session.update(usuariodb);
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
            Usuario usuario = (Usuario) session.load(Usuario.class, id);
            session.delete(usuario);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
}
