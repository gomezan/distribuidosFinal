package com.mycompany.pruebamensaje;

import java.rmi.RemoteException;

public interface InterfazRemota extends java.rmi.Remote{
    
    public void crearOferta(String cargo, int cantH)  throws RemoteException;
    public void crearHabilidad(String nombre,int anios,int indice)  throws RemoteException;
    public String verOfertas()  throws RemoteException;
    public String menu(String op)  throws RemoteException;
    public void escribir()  throws RemoteException;
}
