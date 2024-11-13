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
		private Map<Integer, Usuario> socios;
		private List<Usuario> usuarios;
		
		
		public Admin() {
			
			super();
			mapaUsuarios = new HashMap<tipoSocio,ArrayList<Usuario>>();
			listaEsperaUsuarios = new LinkedList<Usuario>();
			listaPartidos = new ArrayList<Partido>();
			usuarios = new ArrayList<>();

		}
		
	    public List<Usuario> getUsuarios() {
	        return usuarios;
	    }

	    public void setUsuarios(List<Usuario> usuarios) {
	        this.usuarios = usuarios;
	    }
		
		
		
		public void añadirUsuario(Usuario user) {
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
		
	    public boolean eliminarUsuario(int numeroSocio, String contrasena) {
	        for (Usuario usuario : usuarios) {
	            if (usuario.getNumeroSocio() == numeroSocio && usuario.getContrasena().equals(contrasena)) {
	                usuarios.remove(usuario);
	                return true;
	            }
	        }
	        return false;
	    }
		
		
		 public Map<Integer, Usuario> getSocios(){
			 return socios;
		 }
		
		
}
