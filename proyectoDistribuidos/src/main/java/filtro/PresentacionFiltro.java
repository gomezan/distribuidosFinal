/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.ArrayList;
import org.zeromq.SocketType;
import static zmq.ZMQ.socket;

/**
 *
 * @author Guatavita
 */
public class PresentacionFiltro extends Thread {

    ///Atributos
    ///Constructor
    public PresentacionFiltro() {

    }

    /// Metodos
    public boolean autentificar(String user, String password) {

        boolean res = false;

        if (user.equals("gomezan") && password.equals("97080703620")) {
            res = true;
        }

        return res;
    }

    public void enviarOfertas(ArrayList ofertas) {

        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Enviando ofertas");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5550");

            String request = "Ofertas";
            socket.send(request.getBytes(ZMQ.CHARSET), 0);

            byte[] reply = socket.recv(0);
            String respuesta = new String(reply, ZMQ.CHARSET);
            System.out.println(respuesta);

            if (respuesta.equals("OK")) {

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try (ObjectOutputStream ois = new ObjectOutputStream(out)) {
                    ois.writeObject(ofertas);

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }

                byte[] data = out.toByteArray();
                socket.send(data);

                byte[] reply2 = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply, ZMQ.CHARSET)
                );
            }

        }

    }

    public void enviarSolicitudes(ArrayList solicitudes) {

        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Enviando solicitudes");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5550");

            String request = "Solicitudes";
            socket.send(request.getBytes(ZMQ.CHARSET), 0);

            byte[] reply = socket.recv(0);
            String respuesta = new String(reply, ZMQ.CHARSET);
            System.out.println(respuesta);

            if (respuesta.equals("OK")) {

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try (ObjectOutputStream ois = new ObjectOutputStream(out)) {
                    ois.writeObject(solicitudes);

                } catch (IOException ioe) {
                    System.out.println(ioe);
                }

                byte[] data = out.toByteArray();
                socket.send(data);

                byte[] reply2 = socket.recv(0);
                System.out.println(
                        "Received " + new String(reply, ZMQ.CHARSET)
                );
            }

        }

    }

    ///Main
    @Override
    public void run() {
        System.out.println("Filtro presentacion iniciado");

        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5557");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                if (reply != null) {
                    // Print the message
                    System.out.println(
                            "Received : [" + new String(reply, ZMQ.CHARSET) + "]"
                    );

                    String response = "Hello, world!";
                    socket.send(response.getBytes(ZMQ.CHARSET), 0);
                }

            }
        }
    }

}
