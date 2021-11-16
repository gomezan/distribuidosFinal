import com.mycompany.pruebamensaje.IntRemotaImp;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServidorRMI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  System.out.println("Iniciado como servidor");

	  //System.setProperty("java.rmi.server.hostname", "192.168.10.12");
	  System.setProperty("java.rmi.server.hostname", "25.13.204.246");
      try {
          Registry registry =  LocateRegistry.createRegistry(1099);
          registry.rebind("Oferta", new IntRemotaImp("Oferta"));
          System.out.println("Satisfecho");
      } catch (Exception e) {
          System.err.println("No satisfecho" + e);
      }
	}
}
