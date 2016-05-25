/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Producto;
import model.Usuario;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
@ManagedBean(name = "sp")
@SessionScoped
public class cartBean implements Serializable  {
    
    private List<Item> cart = new ArrayList<Item>(); 
    private float total;
    int cantidad;
    public cartBean() {
                
    }
        
    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
    public List<Item> addtoCart(Producto p){
         HttpSession session = HibernateUtil.getSession();
         
         if((List<Item>) session.getAttribute("carrito")!=null){
            cart= (List<Item>) session.getAttribute("carrito");
        }
            
        for(Item item : cart){
            if(item.getProducto().getIdProducto().equals(p.getIdProducto())){
                item.setCantidad(1+item.getCantidad());
                return cart;
            }
        }
        Item i = new Item();
        i.setCantidad(1);
        i.setProducto(p);
        cart.add(i);
        session.setAttribute("carrito", this.cart);
        return cart;
    }

}
