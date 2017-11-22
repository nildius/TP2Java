package unitarias;

import exceptions.JugadorNoTieneDineroException;
import exceptions.PrecioNegativoException;
import modelo.tablero.Tablero;
import modelo.tablero.TableroFactory;
import modelo.tablero.tipos_casilleros.Barrio;
import modelo.jugador.Jugador;
import org.junit.Assert;
import org.junit.Test;

public class BarrioTest {
    @Test(expected = PrecioNegativoException.class)
    public void testCreoUnBarrioConPrecioNegativoLanzaError() {
        Barrio barrio = new Barrio(-200,0,0,0,0,0,0);
    }

    @Test
    public void testJugadorCaseEnCasilleroCompraTerrenoYJugadorEsPropietarioDeTerreno() {
        Tablero tablero = TableroFactory.crearTablero();
        Barrio buenosAiresSur = new Barrio(20000,0,0,0,0,0,0);
        Jugador unJugador = new Jugador("Kev", tablero);
        buenosAiresSur.pisar(unJugador);
        unJugador.comprar(buenosAiresSur);
        Assert.assertEquals(buenosAiresSur.getPropietario(), unJugador);
    }

    @Test
    public void testJugadorCaseEnCasilleroCompraTerrenoYTerrenoApareceEnLaListaDePropiedadesDeJugador() {
        Tablero tablero = TableroFactory.crearTablero();
        Barrio barrio = new Barrio(20000,0,0,0,0,0,0);
        Jugador unJugador = new Jugador("Lucky", tablero);
        barrio.pisar(unJugador);
        unJugador.comprar(barrio);
        Assert.assertTrue(unJugador.esDuenioDePropiedad(barrio));
    }

    @Test(expected = JugadorNoTieneDineroException.class)
    public void testJugadorCaeEnCasilleroYCompraTerrenoPeroNoTieneDinero() {
        Tablero tablero = TableroFactory.crearTablero();
        Barrio barrio = new Barrio(120000,0,0,0,0,0,0);
        Jugador unJugador = new Jugador("Lucky", tablero);

        unJugador.comprar(barrio);
    }
    @Test
    public void testJugadorTieneBASurYNorteContruyeCasaSuDineroSeDecrementa5000() {
    	Tablero tablero=TableroFactory.crearTablero();
    	Jugador unJugador = new Jugador("Kev", tablero);
    	unJugador.avanzar(2);
    	Barrio baSur=(Barrio) (unJugador.getNodoActual()).getCasillero();//BA sur
    	baSur.pisar(unJugador);										
    	unJugador.comprar(baSur);
    	unJugador.avanzar(1);
    	Barrio baNorte=(Barrio)(unJugador.getNodoActual()).getCasillero();//Ba Norte
    	baNorte.pisar(unJugador);
    	unJugador.comprar(baNorte);
    	int Dinero=unJugador.getDinero();
    	baSur.agregarCasa();
    	Assert.assertEquals(unJugador.getDinero(), Dinero-5000);
    }
    @Test
    public void testJugadorTieneBASurYNorteConCasasCaeNuevoJugadorSobreAlgunaZonaSuDineroSeDecrementa3000() {
    	Tablero tablero=TableroFactory.crearTablero();
    	Jugador unJugador = new Jugador("Kev", tablero);
    	Jugador unJugador2=new Jugador("EvilKev",tablero);
    	unJugador.avanzar(2);
    	Barrio baSur=(Barrio) (unJugador.getNodoActual()).getCasillero();
    	baSur.pisar(unJugador);										
    	unJugador.comprar(baSur);
    	unJugador.avanzar(1);
    	Barrio baNorte=(Barrio)(unJugador.getNodoActual()).getCasillero();//Ba Norte
    	baNorte.pisar(unJugador);
    	unJugador.comprar(baNorte);
    	baSur.agregarCasa();
    	baNorte.agregarCasa();
    	int Dinero=unJugador2.getDinero();//dinero que tiene antes de pasar por casillero
    	unJugador2.avanzar(2);
    	baSur.pisar(unJugador2);
    	Assert.assertEquals(unJugador2.getDinero(),Dinero-3000);
    	}
    @Test 
    public void testJugadorTieneBASurYNorteCon2Casasy1RespectivamenteCaeNuevoJugadorSobreAlgunaZonaSuDineroSeDecrementa3500() {
    	Tablero tablero=TableroFactory.crearTablero();
    	Jugador unJugador = new Jugador("Kev", tablero);
    	Jugador unJugador2=new Jugador("EvilKev",tablero);
    	unJugador.avanzar(2);
    	Barrio baSur=(Barrio) (unJugador.getNodoActual()).getCasillero();
    	baSur.pisar(unJugador);										
    	unJugador.comprar(baSur);
    	unJugador.avanzar(1);
    	Barrio baNorte=(Barrio)(unJugador.getNodoActual()).getCasillero();//Ba Norte
    	baNorte.pisar(unJugador);
    	unJugador.comprar(baNorte);
    	baSur.agregarCasa();
    	baSur.agregarCasa();
    	baNorte.agregarCasa();
    	int Dinero=unJugador2.getDinero();//dinero que tiene antes de pasar por casillero
    	unJugador2.avanzar(2);
    	baSur.pisar(unJugador2);
    	Assert.assertEquals(unJugador2.getDinero(),Dinero-3500);
    	
    }
    @Test
    public void testJugadorTieneBaSurYBaNorteSinCapacidadMaximaDeCasasIntentaContruirHotelSuDineroNoSeDecrementa() {
    	Tablero tablero=TableroFactory.crearTablero();
    	Jugador unJugador = new Jugador("Kev", tablero);
    	unJugador.avanzar(2);
    	Barrio baSur=(Barrio) (unJugador.getNodoActual()).getCasillero();
    	baSur.pisar(unJugador);										
    	unJugador.comprar(baSur);
    	unJugador.avanzar(1);
    	Barrio baNorte=(Barrio)(unJugador.getNodoActual()).getCasillero();//Ba Norte
    	baNorte.pisar(unJugador);
    	unJugador.comprar(baNorte);
    	baNorte.agregarCasa();
    	baSur.agregarCasa();
    	int Dinero=unJugador.getDinero();//dinero que tiene antes de pasar por casillero
    	baSur.agregarHotel();
    	Assert.assertEquals(unJugador.getDinero(), Dinero);
    }
    @Test
    public void testJugadorTieneBaSurYBaNorteConCapacidadMaximaDeCasasIntentaContruirHotelSuDineroSeDecrementa8000() {
    	Tablero tablero=TableroFactory.crearTablero();
    	Jugador unJugador = new Jugador("Kev", tablero);//esto si esta loco tenemos que hablar sobre esto, no verifico las 4 casas
    	unJugador.avanzar(2);//solo 2 en buanos aires sur, no quiero modificar mucho el codigo
    	Barrio baSur=(Barrio) (unJugador.getNodoActual()).getCasillero();
    	baSur.pisar(unJugador);										
    	unJugador.comprar(baSur);
    	unJugador.avanzar(1);
    	Barrio baNorte=(Barrio)(unJugador.getNodoActual()).getCasillero();//Ba Norte
    	baNorte.pisar(unJugador);
    	unJugador.comprar(baNorte);
    	baNorte.agregarCasa();
    	baSur.agregarCasa();
    	baSur.agregarCasa();
    	int Dinero=unJugador.getDinero();//dinero que tiene antes de pasar por casillero
    	baSur.agregarHotel();
    	Assert.assertEquals(unJugador.getDinero(), Dinero-8000);
    	}
	
}

   
