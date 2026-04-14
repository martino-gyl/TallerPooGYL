package dominio;

public class Admin {
    private String nombre;
    private String usuario;
    private String password;

    public Admin(String nombre, String usuario, String password) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }
}
