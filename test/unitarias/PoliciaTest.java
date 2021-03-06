package unitarias;

import modelo.tablero.*;
import modelo.tablero.tipos_casilleros.*;
import modelo.jugador.Jugador;
import modelo.tablero.tipos_casilleros.Edificios.EsquemaPrecio;
import org.junit.Assert;
import org.junit.Test;

public class PoliciaTest {
    @Test
    public void testJugadorCaeEnPoliciaNoPuedeMoverse() {
        Tablero tablero = new Tablero();
        Jugador unJugador = new Jugador("Magalí", tablero);
        Policia policia = new Policia();

        policia.pisar(unJugador);
        Assert.assertFalse(unJugador.puedeMoverse());
    }

    @Test
    public void testJugadorCaeEnPoliciaSuPosicionEsLaCarcel() {
        Tablero tablero = new Tablero();
        Jugador unJugador = new Jugador("Magalí", tablero);
        EsquemaPrecio esquema = new EsquemaPrecio();
        esquema.setPrecioAlquilerUnaCasa(0)
                .setPrecioAlquilerDosCasas(0)
                .setPrecioAlquilerHotel(0)
                .setPrecioConstruirCasa(0)
                .setPrecioConstruirHotel(0)
                .setPrecioAlquilerCeroCasas(0);
        tablero.agregarCasillero(new BarrioSimple("Barrio", 4000,esquema));
        Policia policia = new Policia();
        tablero.agregarCasillero(policia);
        tablero.agregarCasillero(new BarrioSimple("Barrio", 6000,esquema));
        tablero.agregarCasillero(new Quini6());
        Carcel carcel = new Carcel();
        tablero.agregarCasillero(carcel);
        policia.pisar(unJugador);

        Assert.assertEquals(tablero.getNodoCarcel(), unJugador.getNodoActual());
    }
}
