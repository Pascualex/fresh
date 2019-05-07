package fresh.interfaz.controladores;

import fresh.sistema.Configuracion;
import fresh.sistema.Sistema;
import fresh.interfaz.vistas.VistaConfiguracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorConfiguracion {
    
    public ControladorConfiguracion(Sistema sistema, VistaConfiguracion vistaConfiguracion) {
        reiniciarCampos(sistema, vistaConfiguracion);
        
        vistaConfiguracion.botonActualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarCampos(sistema, vistaConfiguracion);
                reiniciarCampos(sistema, vistaConfiguracion);
            }
        });
    }

    private void reiniciarCampos(Sistema sistema, VistaConfiguracion vistaConfiguracion) {
        Configuracion configuracion = sistema.obtenerConfiguracion();

        vistaConfiguracion.entradaNombreAdministrador.setText("");
        vistaConfiguracion.entradaContrasenaAdministrador.setText("");
        vistaConfiguracion.entradaEdadMinima.setText("");
        vistaConfiguracion.entradaMaxReproduccionesAnonimo.setText("");
        vistaConfiguracion.entradaMaxReproduccionesRegistrado.setText("");
        vistaConfiguracion.entradaCuotaPremium.setText("");
        vistaConfiguracion.entradaMinReproduccionesPremium.setText("");
        vistaConfiguracion.entradaCaracteresMinimos.setText("");
        
        vistaConfiguracion.entradaNombreAdministrador.setPlaceholder(configuracion.getNombreAdministrador());
        vistaConfiguracion.entradaContrasenaAdministrador.setPlaceholder(configuracion.getContrasenaAdministrador());
        vistaConfiguracion.entradaEdadMinima.setPlaceholder("" + configuracion.getEdadMinima());
        vistaConfiguracion.entradaMaxReproduccionesAnonimo.setPlaceholder("" + configuracion.getMaxReproduccionesAnonimo());
        vistaConfiguracion.entradaMaxReproduccionesRegistrado.setPlaceholder("" + configuracion.getMaxReproduccionesRegistrado());
        vistaConfiguracion.entradaCuotaPremium.setPlaceholder("" + configuracion.getCuotaPremium());
        vistaConfiguracion.entradaMinReproduccionesPremium.setPlaceholder("" + configuracion.getMinReproduccionesPremium());
        vistaConfiguracion.entradaCaracteresMinimos.setPlaceholder("" + configuracion.getCaracteresMinimos());
    }

    private void cargarCampos(Sistema sistema, VistaConfiguracion vistaConfiguracion) {
        if (vistaConfiguracion.entradaNombreAdministrador.getText().length() > 0) {
            sistema.modificarNombreAdministrador(vistaConfiguracion.entradaNombreAdministrador.getText());
        }

        if (vistaConfiguracion.entradaContrasenaAdministrador.getText().length() > 0) {
            sistema.modificarContrasenaAdministrador(vistaConfiguracion.entradaContrasenaAdministrador.getText());
        }

        if (vistaConfiguracion.entradaEdadMinima.getText().length() > 0) {
            try {
                int edadMinima = Integer.parseInt(vistaConfiguracion.entradaEdadMinima.getText());
                sistema.modificarEdadMinima(edadMinima);
            } catch (NumberFormatException e) {

            }
        }

        if (vistaConfiguracion.entradaMaxReproduccionesAnonimo.getText().length() > 0) {
            try {
                int maxReproduccionesAnonimo = Integer.parseInt(vistaConfiguracion.entradaMaxReproduccionesAnonimo.getText());
                sistema.modificarMaxReproduccionesAnonimo(maxReproduccionesAnonimo);
            } catch (NumberFormatException e) {

            }
        }

        if (vistaConfiguracion.entradaMaxReproduccionesRegistrado.getText().length() > 0) {
            try {
                int maxReproduccionesRegistrado = Integer.parseInt(vistaConfiguracion.entradaMaxReproduccionesRegistrado.getText());
                sistema.modificarMaxReproduccionesRegistrado(maxReproduccionesRegistrado);
            } catch (NumberFormatException e) {

            }
        }

        if (vistaConfiguracion.entradaCuotaPremium.getText().length() > 0) {
            try {
                float cuotaPremium = Float.parseFloat(vistaConfiguracion.entradaCuotaPremium.getText());
                sistema.modificarCuotaPremium(cuotaPremium);
            } catch (NumberFormatException e) {

            }
        }

        if (vistaConfiguracion.entradaMinReproduccionesPremium.getText().length() > 0) {
            try {
                int minReproduccionesPremium = Integer.parseInt(vistaConfiguracion.entradaMinReproduccionesPremium.getText());
                sistema.modificarMinReproduccionesPremium(minReproduccionesPremium);
            } catch (NumberFormatException e) {

            }
        }

        if (vistaConfiguracion.entradaCaracteresMinimos.getText().length() > 0) {
            try {
                int caracteresMinimos = Integer.parseInt(vistaConfiguracion.entradaCaracteresMinimos.getText());
                sistema.modificarCaracteresMinimos(caracteresMinimos);
            } catch (NumberFormatException e) {

            }
        }
    }
}