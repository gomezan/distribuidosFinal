package com.mycompany.pruebamensaje;

import com.mycompany.entidad.Oferta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.Naming;
import java.rmi.registry.*;
import java.util.Scanner;

public class ClienteRMI {

	public static void main(String[] args) {
            int Nofertas = 0;
            Oferta[] oferta=new Oferta[10];
            try {//1099
            Registry registry =LocateRegistry.getRegistry("25.13.204.246",1099);
            //Registry registry =LocateRegistry.getRegistry("25.4.189.185",1099);
            InterfazRemota servicio = (InterfazRemota) registry.lookup("Oferta");
            System.out.println("Proceso: Buscando Servidor ");
            while(true){
		Scanner sc = new Scanner(System.in);  //crear un objeto Scanner
		System.out.println("Por favor ingrese un opcion");
                System.out.println("1-> Ingresar una oferta");
                System.out.println("2-> Ver vacantes");
                String opcion=sc.nextLine();
		if(opcion.equals("1")) {
			 System.out.println("Por favor ingrese datos de la oferta oferta");
			 System.out.println("Cargo de la oferta");
			 String cargo=sc.nextLine();
			 System.out.println("Cantidad de habilidades");
			 int nH=sc.nextInt();
			 servicio.crearOferta(cargo,nH);
			 for(int i=0;i<nH;i++) {
				 sc=new Scanner(System.in);
					 System.out.println("Nombre de la habilidad "+(i+1));
					 String nombreH=sc.nextLine();
				 System.out.println("Experiencia en la habilidad "+(i+1)+" en anios");
					 int aniosH=sc.nextInt();
				  servicio.crearHabilidad(nombreH, aniosH,i);
			 }
			 System.out.println("Por favor ingrese datos de la oferta oferta");
		 }else if(opcion.equals("2")) {
			 System.out.println("mostrar vacantes");
			 System.out.println(servicio.verOfertas());

		 }
	 servicio.escribir();
	 System.out.print(servicio.menu(opcion));
 }
	 }
	 catch(Exception e){
	 System.err.println(" System exception"+e);
	 }
	 System.exit(0);
	}

}
