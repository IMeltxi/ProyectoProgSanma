package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import domain.Usuario.tipoSocio;

public class Admin {

		private Map<tipoSocio,ArrayList<Usuario>>mapaUsuarios;
		private List<Usuario>listaEsperaUsuarios;
		private List<Partido>listaPartidos;
		
		public Admin() {
			super();
			mapaUsuarios = new HashMap<tipoSocio,ArrayList<Usuario>>();
			listaEsperaUsuarios = new LinkedList<Usuario>();
			listaPartidos = new ArrayList<Partido>();
			
		}
		
		
		private void añadirUsuario(Usuario user) {
			if(user.getTiposocio().equals(tipoSocio.GAZTEABONO)) {
				if(!mapaUsuarios.containsKey(user.getTiposocio())) {
					mapaUsuarios.put(user.getTiposocio(), new ArrayList<>());		
				}
				mapaUsuarios.get(user.getTiposocio()).add(user);
			}else if(user.getTiposocio().equals(tipoSocio.SOCIO)) {
				if(!mapaUsuarios.containsKey(user.getTiposocio())) {
					mapaUsuarios.put(user.getTiposocio(), new ArrayList<>());		
				}
				mapaUsuarios.get(user.getTiposocio()).add(user);
			}else if(user.getTiposocio().equals(tipoSocio.SOCIOMENSUAL)) {
				if(!mapaUsuarios.containsKey(user.getTiposocio())) {
					mapaUsuarios.put(user.getTiposocio(), new ArrayList<>());		
				}
				mapaUsuarios.get(user.getTiposocio()).add(user);
			}else if(user.getTiposocio().equals(tipoSocio.VIP)) {
				if(!mapaUsuarios.containsKey(user.getTiposocio())) {
					mapaUsuarios.put(user.getTiposocio(), new ArrayList<>());		
				}
				mapaUsuarios.get(user.getTiposocio()).add(user);
			}
		}
		
		private void borrarUsuario(Usuario user) {
		}
		
		
		
}
