package co.com.axelis.axelisBack.enumeration;

public enum Seccion {
    APLICACIONES("Aplicaciones"),
    JUEGOS("Juegos"),
    PROYECTOS("Proyectos");

    private final String seccion;

    Seccion(String seccion){
        this.seccion = seccion;
    }

    public String getSeccion(){
        return this.seccion;
    }
}
