package co.edu.uniquindio.cinemauq.model;

import co.edu.uniquindio.cinemauq.exceptions.CinemaUQException;
import co.edu.uniquindio.cinemauq.patrones.comportamiento.EstadoCompra;
import co.edu.uniquindio.cinemauq.patrones.comportamiento.EstadoPendiente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Compra {

    private final String id;
    private final Cliente cliente;
    private final Funcion funcion;
    private final List<Entrada> entradas;
    private final List<LineaConfiteria> lineas;
    private final LocalDateTime fecha;
    private final double subtotal;
    private final double descuento;
    private final List<String> promocionesAplicadas;
    private EstadoCompra estado = new EstadoPendiente();
    private Reembolso reembolso;
    private int puntosGanados;

    public Compra(String id, Cliente cliente, Funcion funcion, List<Entrada> entradas,
                  List<LineaConfiteria> lineas, double subtotal, double descuento,
                  List<String> promocionesAplicadas, LocalDateTime fecha) {
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.entradas = new ArrayList<>(entradas);
        this.lineas = new ArrayList<>(lineas);
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.promocionesAplicadas = new ArrayList<>(promocionesAplicadas);
        this.fecha = fecha;
    }

    public void pagar() throws CinemaUQException {
        estado.pagar(this);
    }

    public void cancelar(double porcentaje, String motivo, LocalDateTime fechaCancelacion) throws CinemaUQException {
        estado.cancelar(this, porcentaje, motivo, fechaCancelacion);
    }

    public void cambiarEstado(EstadoCompra nuevoEstado) { this.estado = nuevoEstado; }

    public void registrarReembolso(Reembolso reembolso) { this.reembolso = reembolso; }

    public double getTotal() { return Math.max(0, subtotal - descuento); }

    public TarjetaVirtual getTarjeta() { return cliente.getTarjeta(); }

    public boolean tieneReembolso() { return reembolso != null; }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Funcion getFuncion() { return funcion; }
    public List<Entrada> getEntradas() { return Collections.unmodifiableList(entradas); }
    public List<LineaConfiteria> getLineas() { return Collections.unmodifiableList(lineas); }
    public LocalDateTime getFecha() { return fecha; }
    public double getSubtotal() { return subtotal; }
    public double getDescuento() { return descuento; }
    public List<String> getPromocionesAplicadas() { return Collections.unmodifiableList(promocionesAplicadas); }
    public EstadoCompra getEstado() { return estado; }
    public String getNombreEstado() { return estado.getNombre(); }
    public Reembolso getReembolso() { return reembolso; }
    public int getPuntosGanados() { return puntosGanados; }
    public void setPuntosGanados(int puntosGanados) { this.puntosGanados = puntosGanados; }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Compra c && id.equals(c.id));
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return id + " - " + getNombreEstado() + " - $" + getTotal(); }
}
