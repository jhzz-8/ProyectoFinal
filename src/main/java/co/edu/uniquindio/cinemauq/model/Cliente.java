package co.edu.uniquindio.cinemauq.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente extends Usuario {

    private String telefono;
    private final TarjetaVirtual tarjeta;
    private final CuentaPuntos cuentaPuntos = new CuentaPuntos();
    private final List<Compra> compras = new ArrayList<>();
    private final List<Notificacion> notificaciones = new ArrayList<>();

    public Cliente(String documento, String nombre, String correo, String contrasena,
                   String telefono, TarjetaVirtual tarjeta) {
        super(documento, nombre, correo, contrasena);
        this.telefono = telefono;
        this.tarjeta = tarjeta;
    }

    @Override
    public String getRol() { return "CLIENTE"; }

    public void agregarCompra(Compra compra) { compras.add(compra); }

    public void notificar(Notificacion notificacion) { notificaciones.add(notificacion); }

    public long contarNotificacionesNoLeidas() {
        return notificaciones.stream().filter(n -> !n.isLeida()).count();
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public TarjetaVirtual getTarjeta() { return tarjeta; }
    public CuentaPuntos getCuentaPuntos() { return cuentaPuntos; }
    public List<Compra> getCompras() { return Collections.unmodifiableList(compras); }
    public List<Notificacion> getNotificaciones() { return Collections.unmodifiableList(notificaciones); }
}
