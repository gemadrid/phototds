package umu.tds;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import umu.tds.modelo.Album;
import umu.tds.modelo.Foto;
import umu.tds.modelo.Notificacion;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;


public class TestUsuario {
	
	Usuario usuario;
	
    
	@Before
	public void inicializar() {
		usuario = new Usuario("Usuario", "", "", "user", "algo", LocalDate.of(2002, 11, 3), "", "");
		Usuario seguidor1 = new Usuario("Usuario", "", "", "user", "algo", LocalDate.of(2005, 5, 4), "", "");
		Usuario seguidor2 = new Usuario("Usuario", "", "", "user", "algo", LocalDate.of(2001, 5, 4), "", "");
		usuario.addSeguidor(seguidor1);
		usuario.addSeguidor(seguidor2);
	}
	
	@Test
	public void testGetEdad() {
		int edadEsperada = 20;
		assertEquals(edadEsperada, usuario.getEdad());
	}
	
	@Test
	public void testAddFotoAlbum() {
		Foto foto = new Foto("", "", usuario, "");
		Album album = new Album("", "", usuario);
		usuario.addFotoAlbum(album, foto);
		assertEquals(true, album.getFotos().contains(foto));
	}
	
	@Test
	public void testNotificar() {
		Publicacion publicacion = new Foto("", "", usuario, "");
		Notificacion notificacion = usuario.notificar(publicacion);
		assertEquals(true, usuario.getNotificaciones().contains(notificacion));
		assertEquals(true, usuario.getSeguidores().stream()
									.allMatch(s -> s.getNotificaciones().contains(notificacion)));
	}
	
	@Test
	public void testHacersePremium() {
		usuario.hacersePremium("Joven");
		assertEquals(true, usuario.isPremium());
		assertEquals("Joven", usuario.getDescuento().getNombre());
	}


}
