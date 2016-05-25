package entidad;
// Generated 10/04/2016 17:37:54 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Perfil generated by hbm2java
 */
@Entity
@Table(name="perfil"
    ,catalog="supermercado"
)
public class Perfil  implements java.io.Serializable {


     private Integer idPerfil;
     private String descripcion;
     private Boolean accesoProducto;
     private Boolean accesoCliente;
     private Boolean accesoVenta;
     private Boolean accesoUsuario;
     private Boolean accesoCategoia;
     private Boolean accesoRoles;
     private Date fecha;
     private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public Perfil() {
    }

    public Perfil(String descripcion, Boolean accesoProducto, Boolean accesoCliente, Boolean accesoVenta, Boolean accesoUsuario, Boolean accesoCategoia, Boolean accesoRoles, Date fecha, Set<Usuario> usuarios) {
       this.descripcion = descripcion;
       this.accesoProducto = accesoProducto;
       this.accesoCliente = accesoCliente;
       this.accesoVenta = accesoVenta;
       this.accesoUsuario = accesoUsuario;
       this.accesoCategoia = accesoCategoia;
       this.accesoRoles = accesoRoles;
       this.fecha = fecha;
       this.usuarios = usuarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_perfil", unique=true, nullable=false)
    public Integer getIdPerfil() {
        return this.idPerfil;
    }
    
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    
    @Column(name="descripcion", length=15)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="accesoProducto")
    public Boolean getAccesoProducto() {
        return this.accesoProducto;
    }
    
    public void setAccesoProducto(Boolean accesoProducto) {
        this.accesoProducto = accesoProducto;
    }

    
    @Column(name="accesoCliente")
    public Boolean getAccesoCliente() {
        return this.accesoCliente;
    }
    
    public void setAccesoCliente(Boolean accesoCliente) {
        this.accesoCliente = accesoCliente;
    }

    
    @Column(name="accesoVenta")
    public Boolean getAccesoVenta() {
        return this.accesoVenta;
    }
    
    public void setAccesoVenta(Boolean accesoVenta) {
        this.accesoVenta = accesoVenta;
    }

    
    @Column(name="accesoUsuario")
    public Boolean getAccesoUsuario() {
        return this.accesoUsuario;
    }
    
    public void setAccesoUsuario(Boolean accesoUsuario) {
        this.accesoUsuario = accesoUsuario;
    }

    
    @Column(name="accesoCategoia")
    public Boolean getAccesoCategoia() {
        return this.accesoCategoia;
    }
    
    public void setAccesoCategoia(Boolean accesoCategoia) {
        this.accesoCategoia = accesoCategoia;
    }

    
    @Column(name="accesoRoles")
    public Boolean getAccesoRoles() {
        return this.accesoRoles;
    }
    
    public void setAccesoRoles(Boolean accesoRoles) {
        this.accesoRoles = accesoRoles;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="perfil")
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }




}


