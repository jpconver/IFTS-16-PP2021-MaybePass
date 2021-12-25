package maybePass;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;


public class Utilidades {
    
    public static boolean hayColision(
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        boolean haySolapamientoEnX =  haySolapamientoDeRango(
                elemento1PosicionX,
                elemento1PosicionX+elemento1Ancho,
                elemento2PosicionX,
                elemento2PosicionX+elemento2Ancho);
        boolean haySolapamientoEnY = haySolapamientoDeRango(
                elemento1PosicionY,
                elemento1PosicionY+elemento1Largo,
                elemento2PosicionY,
                elemento2PosicionY+elemento2Largo);
    	if (haySolapamientoEnX &&  haySolapamientoEnY) {
            return true;
        }
        return false;
    }
    
    private static boolean haySolapamientoDeRango(int a, int b, int c, int d) {
        if (a < d && b > c  ) {
            return true;
        }
        return false;
    }
    
    public static boolean verificarColisionEnEjeY(
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        int diferenciaDeSolapamientoEnX =  CalcularDiferenciaDeSolapamiento(
                elemento1PosicionX,
                elemento1Ancho,
                elemento2PosicionX,
                elemento2Ancho);
        int diferenciaDeSolapamientoEnY = CalcularDiferenciaDeSolapamiento(
                elemento1PosicionY,
                elemento1Largo,
                elemento2PosicionY,
                elemento2Largo);
    	if (diferenciaDeSolapamientoEnX >  diferenciaDeSolapamientoEnY) {
            return true;     
        }
        return false;
    }
    
    private static int CalcularDiferenciaDeSolapamiento (int a, int b, int c, int d) {
    	if (a < c) {
    		return (a + b)-c;
    	} else {
    		if(( a + b ) >(c + d)) {
    			return (c + d) - a;
    		} else {
    			return b;
    		}
    	}
    }
    
    public static String obtenerRuta(String pathRelativoDesdeResources) {
    	boolean esLinux = System.getProperty("os.name").equals("Linux");
    	String ruta = "";
    	if (esLinux) {
    		try {
				ruta = Paths.get(ElementoBasico.class.getClassLoader().getResource(pathRelativoDesdeResources).toURI()).toString();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
    	} else {
    		ruta = System.getProperty("user.dir") + "/src/main/resources/" + pathRelativoDesdeResources;
    	}
    	return ruta;
    }
    
    public static Font cargarFuente() {
    	Font fuente = null;
    	String path = "src/main/resources/fuentes/lastninja.ttf";
    	try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(20f);	
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));	
			return fuente;
    	} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
