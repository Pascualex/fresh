package fresh.modulos;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import fresh.datos.tipos.*;
import fresh.datos.*;

/**
 * Gestiona los eventos del sistema, realizando operaciones en la base de datos.
 * @author Víctor Yrazusta (victor.yrazusta@estudiante.uam.es)
 */
public class GestorEventos{
    private GregorianCalendar ultimoDiaComprobado;
    private SortedSet<ParCancionFecha> cancionesAEliminar = new TreeSet<>();
    private BaseDeDatos baseDeDatos;
    static final long msDia = 86400000;

    /**
     * Instancia un gestor de eventos dada la base de datos relacionada.
     * @param baseDeDatos Base de datos asociada con el gestor.
     */
    public GestorEventos(BaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
        ultimoDiaComprobado = new GregorianCalendar();
    }

    /**
     * Instancia un gestor de eventos, cargando su información desde un fichero.
     * @param ruta Ruta desde la que se ha de cargar el gestor previamente
     * guardado. Si no se puede encontrar el archivo en la ruta especificada
     * se creará un gestor nuevo.
     * @param baseDeDatos Base de datos relacionada con el gestor.
     */
    public GestorEventos(String ruta, BaseDeDatos baseDeDatos) {

    }

    /**
     * Instancia un GestorEventos, configurando el último día que el gestor de
     * eventos ha realizado sus comprobaciones.
     * @param ultimoDiaComprobado Último día que el gestor ha comprobado los 
     * eventos.
     */
    public GestorEventos(GregorianCalendar ultimoDiaComprobado) {
        this.ultimoDiaComprobado = ultimoDiaComprobado;
    }

    /**
     * Guarda la información relacionada con el gestor de eventos en la ruta 
     * especificada para que pueda ser más tarde cargada.
     * @param ruta Ruta en la que guardar la información del gestor.
     */
    public boolean GuargarInformacion(String ruta) {

    }
    
    /**
     * Inicia el proceso de comprobar las canciones a eliminar y degradar a los
     * usuarios a usuarios normales quitándoles el premium a inicio de mes.
     */
    public void Iniciar() {
        while (true) {
            //Calculo la fecha actual
            GregorianCalendar fecha_actual = new GregorianCalendar();
            //Si no ha pasado un día aún espero a que sea el día que viene
            if (fecha_actual.getTime()-fecha.getTime() < msDia) {
                TimeUnit.SECONDS.sleep((fecha_actual.getTime()-msDia)/1000);
            }
            
            //He de realizar las operaciones

            //-----------
            //Operaciones
            //-----------

            //Reestablezco el tiempo
            fecha = new GregorianCalendar();
        }
    }

}

class ParCancionFecha implements Comparable<ParCancionFecha> {
    Cancion cancion;
    GregorianCalendar fecha;

    public ParCancionFecha(Cancion cancion) {
        this.cancion = cancion;
        fecha = new GregorianCalendar();
    }

    public ParCancionFecha(Cancion cancion, GregorianCalendar fecha) {
        this.cancion = cancion;
        this.fecha = fecha;
    }

    @Override
    public int compareTo(ParCancionFecha par) {
        return fecha.compareTo(par.fecha);
    }
}