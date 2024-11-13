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
		
		
		
	    public void añadirUsuarios(Usuario user) {
			usuarios.add(user);
		}
		
		public ArrayList<Usuario> visualizarUsuariosPorTipo(tipoSocio tipo) {
			HashMap<tipoSocio, ArrayList<Usuario>> mapaPorTipo = new HashMap<>();
			
			
			//Crear un papa con una lista de 
			for(Usuario user:usuarios) {
				if(user.getTiposocio().equals(tipoSocio.GAZTEABONO)) {
					if(!mapaPorTipo.containsKey(user.getTiposocio())) {
						mapaPorTipo.put(user.getTiposocio(), new ArrayList<>());		
					}
					mapaPorTipo.get(user.getTiposocio()).add(user);
				}else if(user.getTiposocio().equals(tipoSocio.SOCIO)) {
					if(!mapaPorTipo.containsKey(user.getTiposocio())) {
						mapaPorTipo.put(user.getTiposocio(), new ArrayList<>());		
					}
					mapaPorTipo.get(user.getTiposocio()).add(user);
				}else if(user.getTiposocio().equals(tipoSocio.SOCIOMENSUAL)) {
					if(!mapaPorTipo.containsKey(user.getTiposocio())) {
						mapaPorTipo.put(user.getTiposocio(), new ArrayList<>());		
					}
					mapaPorTipo.get(user.getTiposocio()).add(user);
				}else if(user.getTiposocio().equals(tipoSocio.VIP)) {
					if(!mapaPorTipo.containsKey(user.getTiposocio())) {
						mapaPorTipo.put(user.getTiposocio(), new ArrayList<>());		
					}
					mapaPorTipo.get(user.getTiposocio()).add(user);
				}
			}
			
			ArrayList<Usuario> listaUsers = mapaPorTipo.get(tipo);
			return listaUsers;
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
