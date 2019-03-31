package fresh.sistema;

import java.io.*;
import java.util.Objects;

import fresh.Status;

/**
 * <p>Esta clase permite cargar el fichero de configuración, acceder fácilmente
 *    a sus datos y guardarlo cuando se modifica.</p>
 */
public class Configuracion {
    private String ruta;

    private String nombreAdministrador;
    private String contrasenaAdministrador;
    private int edadMinima;
    private int maxReproduccionesAnonimo;
    private int maxReproduccionesRegistrado;
    private float cuotaPremium;
    private int minReproduccionesPremium;
    private int caracteresMinimos;

    /**
     * Instancia la configuración dada la ruta del archivo. Si se produce algún
     * error, se creará un nuevo fichero con los parámetros predeterminados.
     * @param ruta Ruta del archivo de configuración
     */
    public Configuracion(String ruta) {
        this.ruta = ruta;
        if (cargarConfiguracion() == Status.OK) return;

        nombreAdministrador = "admin";
        contrasenaAdministrador = "admin";
        edadMinima = 14;
        maxReproduccionesAnonimo = 20;
        maxReproduccionesRegistrado = 100;
        cuotaPremium = 9.99f;
        minReproduccionesPremium = 1000;
        caracteresMinimos = 4;

        guardarConfiguracion();
    }

    /**
     * Devuelve el nombre del administrador.
     * @return Nombre del administrador.
     */
    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    /**
     * Establece el nombre del administrador.
     * @param nombreAdministrador Nuevo nombre del administrador
     */
    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    /**
     * Devuelve la contraseña del administrador.
     * @return Contraseña del administrador
     */
    public String getContrasenaAdministrador() {
        return contrasenaAdministrador;
    }

    /**
     * Establece la contraseña del administrador.
     * @param contrasenaAdministrador Nueva contraseña del administrador
     */
    public void setContrasenaAdministrador(String contrasenaAdministrador) {
        this.contrasenaAdministrador = contrasenaAdministrador;
    }

    /**
     * Devuelve la edad mínima para registrarse en la aplicación.
     * @return Edad mínima para registrarse en la aplicación.
     */
    public int getEdadMinima() {
        return edadMinima;
    }

    /**
     * Establece la edad mínima para registrarse en la aplicación.
     * @param edadMinima Nueva edad mínima para registrarse en la aplicación
     */
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    /**
     * Devuelve el máximo de reproducciones por sesión de un usuario anónimo.
     * @return Máximo de reproducciones por sesión de un usuario anónimo.
     */
    public int getMaxReproduccionesAnonimo() {
        return maxReproduccionesAnonimo;
    }

    /**
     * Establece el máximo de reproducciones por sesión de un usuario anónimo.
     * @param maxReproduccionesAnonimo Máximo de reproducciones por sesión.
     */
    public void setMaxReproduccionesAnonimo(int maxReproduccionesAnonimo) {
        this.maxReproduccionesAnonimo = maxReproduccionesAnonimo;
    }

    /**
     * Devuelve el máximo de reproducciones mensuales de un usuario registrado.
     * @return Máximo de reproducciones mensuales de un usuario registrado.
     */
    public int getMaxReproduccionesRegistrado() {
        return maxReproduccionesRegistrado;
    }

    /**
     * Establece el máximo de reproducciones mensuales de un usuario registrado.
     * @param maxReproduccionesRegistrado Máximo de reproducciones mensuales
     */
    public void setMaxReproduccionesRegistrado(int maxReproduccionesRegistrado) {
        this.maxReproduccionesRegistrado = maxReproduccionesRegistrado;
    }

    /**
     * Devuelve la cuota mensual del servicio premium de la aplicación.
     * @return Cuota mensual del servicio premium de la aplicación.
     */
    public float getCuotaPremium() {
        return cuotaPremium;
    }

    /**
     * Establece la cuota mensual del servicio premium de la aplicación.
     * @param cuotaPremium Cuota mensual del servicio premium de la aplicación
     */
    public void setCuotaPremium(float cuotaPremium) {
        this.cuotaPremium = cuotaPremium;
    }

    /**
     * Devuelve el mínimo número de reproducciones mensuales para obtener el 
     * servicio premium de manera gratuita.
     * @return Mínimo número de reproducciones mensuales.
     */
    public int getMinReproduccionesPremium() {
        return minReproduccionesPremium;
    }

    /**
     * Establece el mínimo número de reproducciones mensuales para obtener el 
     * servicio premium de manera gratuita.
     * @param minReproduccionesPremium Mínimo número de reproducciones mensuales
     */
    public void setMinReproduccionesPremium(int minReproduccionesPremium) {
        this.minReproduccionesPremium = minReproduccionesPremium;
    } 

    /**
     * Devuelve el mímino número de caracteres necesarios en los campos de
     * texto que deben proporcionar los usuarios.
     * @return Mínimo número de caracteres.
     */
    public int getCaracteresMinimos() {
        return caracteresMinimos;
    }

    /**
     * Establece el mímino número de caracteres necesarios en los campos de
     * texto que deben proporcionar los usuarios.
     * @param caracteresMinimos Mínimo número de caracteres
     */
    public void setCaracteresMinimos(int caracteresMinimos) {
        this.caracteresMinimos = caracteresMinimos;
    }

    // Carga del fichero de configuración la información necesaria
    private Status cargarConfiguracion() {
        try (FileInputStream stream = new FileInputStream(ruta);
             InputStreamReader reader = new InputStreamReader(stream);
             BufferedReader buffer = new BufferedReader(reader);) {

            String linea;
            String[] palabras;

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "NOMBRE_ADMINISTRADOR")) throw new IOException();
            nombreAdministrador = palabras[1];

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "CONTRASENA_ADMINISTRADOR")) throw new IOException();
            contrasenaAdministrador = palabras[1];

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "EDAD_MINIMA")) throw new IOException();
            edadMinima = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MAX_REPRODUCCIONES_ANONIMO")) throw new IOException();
            maxReproduccionesAnonimo = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MAX_REPRODUCCIONES_ANONIMO")) throw new IOException();
            maxReproduccionesAnonimo = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MAX_REPRODUCCIONES_REGISTRADO")) throw new IOException();
            maxReproduccionesRegistrado = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "CUOTA_PREMIUM")) throw new IOException();
            cuotaPremium = Float.parseFloat(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "MIN_REPRODUCCIONES_PREMIUM")) throw new IOException();
            minReproduccionesPremium = Integer.parseInt(palabras[1]);

            linea = buffer.readLine();
            if (linea == null) throw new IOException();
            palabras = linea.split(" ");
            if (!Objects.equals(palabras[0], "CARACTERES_MINIMOS")) throw new IOException();
            caracteresMinimos = Integer.parseInt(palabras[1]);

            return Status.OK;
        } catch (IOException e) {
            return Status.ERROR_CARGAR;
        } catch (NumberFormatException e) {
            return Status.ERROR_CARGAR;
        }
    }

    /**
     * Guarda en el fichero de configuración los nuevos parámetros.
     */
    public void guardarConfiguracion() {
        try (FileOutputStream stream = new FileOutputStream(ruta);
             OutputStreamWriter writer = new OutputStreamWriter(stream);
             BufferedWriter buffer = new BufferedWriter(writer);) {

            buffer.write("NOMBRE_ADMINISTRADOR " + nombreAdministrador + "\n");
            buffer.write("CONTRASENA_ADMINISTRADOR " + contrasenaAdministrador + "\n");
            buffer.write("EDAD_MINIMA " + edadMinima + "\n");
            buffer.write("MAX_REPRODUCCIONES_ANONIMO " + maxReproduccionesAnonimo + "\n");
            buffer.write("MAX_REPRODUCCIONES_REGISTRADO " + maxReproduccionesRegistrado + "\n");
            buffer.write("CUOTA_PREMIUM " + cuotaPremium + "\n");
            buffer.write("MIN_REPRODUCCIONES_PREMIUM " + minReproduccionesPremium + "\n");
            buffer.write("CARACTERES_MINIMOS " + caracteresMinimos);
        } catch (IOException e) {
            return;
        }
    }
}