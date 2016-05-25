/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import entidad.Producto;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto_Dao;

@ManagedBean(name="proContrl")
public class ProductoControlador {
    private List<Producto> lst = new ArrayList<Producto>();

    public List<Producto> getLst() {
        Producto_Dao dao = new Producto_Dao();
        return dao.listar();
    }

    public void setLst(List<Producto> lst) {
        this.lst = lst;
    }
    
}
