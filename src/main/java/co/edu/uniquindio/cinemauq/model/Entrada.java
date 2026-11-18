package co.edu.uniquindio.cinemauq.model;

public abstract class Entrada implements Cobrable {

    private final String id;
    private final Funcion funcion;
    private final Asiento asiento;

    protected Entrada(String id, Funcion funcion, Asiento asiento) {
        this.id = id;
        this.funcion = funcion;
        this.asiento = asiento;
    }

    public abstract double getRecargo();

    public abstract String getTipo();

    @Override
    public double getPrecio() {
        return funcion.getPrecioBase() + getRecargo();
    }

    @Override
    public String getDescripcion() {
        return "Entrada " + getTipo() + " - " + funcion.getPelicula().getTitulo() + " - asiento " + asiento.getCodigo();
    }

    public String getId() { return id; }
    public Funcion getFuncion() { return funcion; }
    public Asiento getAsiento() { return asiento; }

    @Override
    public String toString() { return getDescripcion(); }
}
