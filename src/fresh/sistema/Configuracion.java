package fresh.sistema;

import java.io.*;
import java.util.Objects;

import fresh.Status;

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

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getContrasenaAdministrador() {
        return contrasenaAdministrador;
    }

    public void setContrasenaAdministrador(String contrasenaAdministrador) {
        this.contrasenaAdministrador = contrasenaAdministrador;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getMaxReproduccionesAnonimo() {
        return maxReproduccionesAnonimo;
    }

    public void setMaxReproduccionesAnonimo(int maxReproduccionesAnonimo) {
        this.maxReproduccionesAnonimo = maxReproduccionesAnonimo;
    }

    public int getMaxReproduccionesRegistrado() {
        return maxReproduccionesRegistrado;
    }

    public void setMaxReproduccionesRegistrado(int maxReproduccionesRegistrado) {
        this.maxReproduccionesRegistrado = maxReproduccionesRegistrado;
    }

    public float getCuotaPremium() {
        return cuotaPremium;
    }

    public void setCuotaPremium(float cuotaPremium) {
        this.cuotaPremium = cuotaPremium;
    }

    public int getMinReproduccionesPremium() {
        return minReproduccionesPremium;
    }

    public void setMinReproduccionesPremium(int minReproduccionesPremium) {
        this.minReproduccionesPremium = minReproduccionesPremium;
    } 

    public int getCaracteresMinimos() {
        return caracteresMinimos;
    }

    public void setCaracteresMinimos(int caracteresMinimos) {
        this.caracteresMinimos = caracteresMinimos;
    }

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