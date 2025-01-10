package domain;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import domain.Usuario.tipoSocio;

public class Admin {
		
		private List<Usuario>listaEsperaUsuarios;
		private List<Partido>listaPartidos;
		private Map<Integer, Usuario> socios;
		private List<Usuario> usuarios;
		
		// Constructor de la clase Admin
		public Admin() {
			
			super(); // Llama al constructor de la clase base
			listaEsperaUsuarios = new LinkedList<Usuario>();
			listaPartidos = new ArrayList<Partido>();
			usuarios = new ArrayList<>();

		}
		// Método para obtener la lista de usuarios
	    public List<Usuario> getUsuarios() {
	        return usuarios;
	    }
	 // Método para establecer la lista de usuarios
	    public void setUsuarios(List<Usuario> usuarios) {
	        this.usuarios = usuarios;
	    }
		
		
	    // Método para añadir usuarios al sistema
	    public void añadirUsuarios(Usuario user) {	    	
	    	//Añadir a usuarios siempre y cuando no cumplan el limite maximo
	    	if(usuarios.size()<500000) {
	    		user.setNumeroSocio(usuarios.size());
	    		usuarios.add(user);
	    		//cargar el fichero y guardar el nuevo usuario en el fichero
	    		File f = new File("ficheros/socios.txt");
	    		f.delete();
	    		escribirUsuariosEnTXT(usuarios);
	    		System.out.println(user+ " ha sido añadido correctamente.");
	    		
	    	}else {
	    		listaEsperaUsuarios.addLast(user);//añadirlos al final para no alterar la lista de espera
	    	}
	    	
	    }
	    public void escribirUsuariosEnTXT(List<Usuario> usuarios) {
	    	File f = new File("ficheros/socios.txt");
	    	try {
	    		PrintWriter pw = new PrintWriter(f);
	    		//Como lo que tenemos que escribir en el fichero son misisiones, recorremos
	    		//la lista de misiones
	    			
	    		for(Usuario u: usuarios) {
	    			pw.println(u.getTiposocio()+";"+ u.getNombre()+";"+u.getApellido()+";"+u.getTlf()+";"+u.getFechNacStr()+";"+u.getEmail()+";"+ u.getContrasena()+";"+u.getNumeroSocio());
	    		}
	    			pw.flush();
	    			pw.close();
	    		} catch (FileNotFoundException e) {
	    			
	    			e.printStackTrace();
	    		}
	    }
		public ArrayList<Usuario> visualizarUsuariosPorTipo(tipoSocio tipo) {
			HashMap<tipoSocio, ArrayList<Usuario>> mapaPorTipo = new HashMap<>();
			
			
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
	        //Añadir usuario de la lista de espera
	        if(!(listaEsperaUsuarios.size()==0)) {
	        	usuarios.add(listaEsperaUsuarios.getFirst());
	        	listaEsperaUsuarios.removeFirst();
	        }
	        return false;
	    }
	    public boolean eliminarUsuarioAdmin(int numeroSocio) {
	    	for(Usuario usuario : usuarios) {
	    		if(usuario.getNumeroSocio()== numeroSocio) usuarios.remove(usuario);
	    		return true; //usuario eliminado correctamente
	    	}
	    	return false;//el usuario no se a eliminado
	    }
	    // Metodo para cargar los usuarios
		public void cargarUsuarios() {
			File f = new File("ficheros/socios.txt");
			if(f.exists()) {
				try (Scanner sc = new Scanner(f)) {
					while(sc.hasNext()) {
						String linea = sc.nextLine();
						String[] datos = linea.split(";");
						Usuario u= new Usuario(tipoSocio.valueOf(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
						u.setNumeroSocio(Integer.parseInt(datos[7]));
						añadirUsuarios(u);
						System.out.println(u);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		 public Map<Integer, Usuario> getSocios(){
			 return socios;
		 }
		
	public void visualizarSocios(){
		if (usuarios.isEmpty()) {
			System.out.println("No hay ningun socio, nos vamos a la ruina");
		}else {
			System.out.println("Lista de socios:");
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
		}
}
	
	public static Partido buscarPartidoMasCercanoRecursividad(Partido[] partidos, int i, String fechaReferencia) {
	    // Caso base: Si no hay más partidos, devolver null
	    if (i == partidos.length) {
	        return null;
	    }

	    // Parsear la fecha de referencia a LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate fechaRef = LocalDate.parse(fechaReferencia, formatter);

	    // Caso recursivo: Comparar fechas con el siguiente partido
	    Partido siguiente = buscarPartidoMasCercanoRecursividad(partidos, i + 1, fechaReferencia);

	    if (siguiente == null) {
	        return partidos[i];
	    }

	    // Convertir las fechas del partido actual y del siguiente a LocalDate
	    LocalDate fechaActual = LocalDate.parse(partidos[i].getFecha(), formatter);
	    LocalDate fechaSiguiente = LocalDate.parse(siguiente.getFecha(), formatter);

	    // Calcular la diferencia en días entre las fechas
	    long diferenciaActual = Math.abs(ChronoUnit.DAYS.between(fechaRef, fechaActual));
	    long diferenciaSiguiente = Math.abs(ChronoUnit.DAYS.between(fechaRef, fechaSiguiente));

	    // Retornar el partido con la menor diferencia
	    return diferenciaActual < diferenciaSiguiente ? partidos[i] : siguiente;
	}
	
	public Usuario buscarUsuarioPorEmail(String email) {
	    for (Usuario usuario : usuarios) { // Suponiendo que "usuarios" es la lista de usuarios
	        if (usuario.getEmail().equals(email)) {
	            return usuario;
	        }
	    }
	    return null;
	}


	
}
	
