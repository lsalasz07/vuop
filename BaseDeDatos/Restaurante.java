package BaseDeDatos;

public class Restaurante {
    private int id;
    private String nombre;
    private String direccion;
    private String opcionUno;
    private String opcionDos;

    // Constructor con todos los parámetros (cuando tengas todos los datos)
    public Restaurante(int id, String nombre, String direccion, String opcionUno, String opcionDos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.opcionUno = opcionUno;
        this.opcionDos = opcionDos;
    }

    // Constructor sin parámetros (para crear un objeto vacío)
    public Restaurante() {
        // Constructor vacío
    }

    // Constructor con parámetros, sin el ID (útil para insertar nuevos restaurantes sin un ID inicial)
    public Restaurante(String nombre, String direccion, String opcionUno, String opcionDos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.opcionUno = opcionUno;
        this.opcionDos = opcionDos;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOpcionUno() {
        return opcionUno;
    }

    public void setOpcionUno(String opcionUno) {
        this.opcionUno = opcionUno;
    }

    public String getOpcionDos() {
        return opcionDos;
    }

    public void setOpcionDos(String opcionDos) {
        this.opcionDos = opcionDos;
    }
}
