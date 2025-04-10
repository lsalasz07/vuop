package BaseDeDatos;

public class Usuario {
    private int id;
    private String nombre;
    private String direccion;
    private String correo;
    private String clave;

    // Constructor con todos los parámetros (para usar cuando tengas todos los datos)
    public Usuario(int id, String nombre, String direccion, String correo, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.clave = clave;
    }

    // Constructor sin parámetros (para crear un objeto vacío)
    public Usuario() {
        // Constructor vacío
    }

    // Constructor con parámetros, sin el ID (útil para insertar nuevos usuarios sin un ID inicial)
    public Usuario(String nombre, String direccion, String correo, String clave) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.clave = clave;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
