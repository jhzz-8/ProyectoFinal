package co.edu.uniquindio.cinemauq.model;

/**
 * Entrada a una función para un asiento. Es abstracta: cada tipo define su recargo sobre el
 * precio base de la función (polimorfismo). Las subclases solo cambian el recargo y el nombre,
 * así que cualquier Entrada se puede usar donde se espera otra (LSP).
 * Se crean con el patrón Factory Method (EntradaFactory).
 */
public abstract class Entrada implements Cobrable {

    private final String id;
    private final Funcion funcion;
    private final Asiento asiento;

    protected Entrada(String id, Funcion funcion, Asiento asiento) {
        this.id = id;
        this.funcion = funcion;
        this.asiento = asiento;
    }

    /** Valor adicional que se suma al precio base de la función. */
    public abstract double getRecargo();

    /** Nombre del tipo de entrada: "General", "Preferencial", "3D". */
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
