/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Cliente;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "clienteBean")
@RequestScoped
public class clienteBean {

    private List<SelectItem> selectOneItemsCliente;
    
    private List<Cliente> clientees;
    private Cliente cliente1 = new Cliente();
    private Cliente selectedCliente = new Cliente();
    
    
    public List<SelectItem> getSelectOneItemsCliente(){
        this.selectOneItemsCliente = new  ArrayList<SelectItem>();
        ClienteDao clientedao = new ClienteDaoImpl();
        List<Cliente> clientees = clientedao.selectItems();
        for (Cliente cliente : clientees){
            SelectItem selectItem = new SelectItem(cliente.getIdCliente(),cliente.getNombre());
            this.selectOneItemsCliente.add(selectItem);
        }
        return selectOneItemsCliente;
    }
    public clienteBean() {
        this.clientees = new ArrayList<Cliente>();
    }

    public List<Cliente> getClientes() {
        ClienteDao clienteDao = new ClienteDaoImpl();
        this.clientees = clienteDao.findAll();
        return clientees;
    }

    public void setClientes(List<Cliente> clientees) {
        this.clientees = clientees;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente) {
        this.cliente1 = cliente;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
    
    
     
    public void btnCreateCliente(ActionEvent event){
        String msg;
        ClienteDao clienteDao = new ClienteDaoImpl();
        
        if(clienteDao.create(this.cliente1)){
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }        
    }
    
    public void btnDeleteCliente(ActionEvent actionEvent){
        String msg;
        ClienteDao clienteDao = new ClienteDaoImpl();
        if(clienteDao.delete(this.selectedCliente.getIdCliente())){
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    public void btnUpdateCliente(ActionEvent actionEvent){
        String msg;
        ClienteDao clienteDao = new ClienteDaoImpl();
        if(clienteDao.update(this.selectedCliente)){
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al actualizar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
}
