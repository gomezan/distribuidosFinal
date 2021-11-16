package com.mycompany.pruebamensaje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class IntRemotaImp extends UnicastRemoteObject
implements InterfazRemota{
				int nOfer = 0;
				int nHabi = 0;
				int aux=0;
				Oferta[] ofertas=new Oferta[10];
				Habilidad[] habilidades=new Habilidad[3];
				public IntRemotaImp(String name) throws RemoteException {
				super();
				try {
				System.out.println("Rebind Object " + name);
				Naming.rebind(name, this);
				//System.out.println(LocateRegistry.getRegistry());
				} catch (Exception e){
				System.out.println("Exception: " + e.getMessage());
				e.printStackTrace();
				}
				}
				protected IntRemotaImp() throws RemoteException {
					super();
					// TODO Auto-generated constructor stub
				}

		 @Override
	 	public String verOfertas() {
			System.out.println("CLiente visualizando ofertas");
		String path="C:\\Users\\TUCHYS\\Desktop\\CiclosU\\3-2021\\distribuidos\\Proyecto\\ofertas.txt";
		String lectura="";
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		boolean bandera=false;
		try {
			 // Apertura del fichero y creacion de BufferedReader para poder
			 // hacer una lectura comoda (disponer del metodo readLine()).
			 archivo = new File (path);
			 fr = new FileReader (archivo);
			 br = new BufferedReader(fr);
			 // Lectura del fichero
			 String linea;
			 while((linea=br.readLine())!=null) {
			// System.out.println(linea);
				if(bandera==false) {
					String[] partsA = linea.split("-");
						lectura=lectura+"Cargo de vacante:  "+partsA[0]+"\n";
					bandera=true;
				}else {
					String[] partsA = linea.split("-");
					for(int j=0;j<partsA.length;j++) {
								lectura=lectura+"Nombre de habilidad:  "+partsA[j]+"\n";
								j++;
								lectura=lectura+"Anios de experiencia:  "+partsA[j]+"\n";
					}
					bandera=false;
				}
			 }

		}
		catch(Exception e){
			 e.printStackTrace();
		}
		return lectura;
	 	}

				@Override
				public String menu(String op) throws RemoteException {
					// TODO Auto-generated method stub
					if(op.equals("1"))
				    System.out.println("Nueva oferta registrada");
					else if(op.equals("2"))
				    System.out.println("Solicitud de visualizacion de vacantes");
					return op;
				}
				@Override
	public void crearOferta(String cargo,int cantH) throws RemoteException {
		// TODO Auto-generated method stub
		ofertas[nOfer]=new Oferta();
		ofertas[nOfer].setCargo(cargo);
		ofertas[nOfer].habilidades=new Habilidad[cantH];
		aux=nOfer;
		nOfer++;
	}
	@Override
	public void crearHabilidad(String nombre, int anios, int indice) throws RemoteException {
		// TODO Auto-generated method stub
		ofertas[aux].habilidades[indice]=new Habilidad();
		ofertas[aux].habilidades[indice].setNombre(nombre);
		ofertas[aux].habilidades[indice].setAnios(anios);
	}
				@Override
public void escribir() throws RemoteException {
	// TODO Auto-generated method stub
	FileWriter fichero = null;
			PrintWriter pw = null;
			String path="C:\\Users\\TUCHYS\\Desktop\\CiclosU\\3-2021\\distribuidos\\Proyecto\\ofertas.txt";
			try
			{
					fichero = new FileWriter(path);
					pw = new PrintWriter(fichero);
					System.out.println("TAMANO"+nOfer);
					for(int i=0;i<nOfer;i++) {
				//System.out.println("Cargo de la oferta "+ofertas[i].getCargo());
				pw.println(ofertas[i].getCargo());

			//System.out.println("CANT HABILIDADES "+ofertas[i].habilidades.length);
				for(int j=0;j<ofertas[i].habilidades.length;j++) {
				//	System.out.println("Nombre habilidad "+(j+1)+" "+ofertas[i].habilidades[j].getNombre());
					//System.out.println("Experiencia en años "+ofertas[i].habilidades[j].getAnios());
					pw.println(ofertas[i].habilidades[j].getNombre()+"-"+ofertas[i].habilidades[j].getAnios());
				}
			}

			} catch (Exception e) {
					e.printStackTrace();
			} finally {
				 try {
				 // Nuevamente aprovechamos el finally para
				 // asegurarnos que se cierra el fichero.
				 if (null != fichero)
						fichero.close();
				 } catch (Exception e2) {
						e2.printStackTrace();
				 }
			}
		}
}
