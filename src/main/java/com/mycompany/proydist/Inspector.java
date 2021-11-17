/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proydist;

import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author puma0
 */
public class Inspector {
   

    private static Firestore bd=null;

    public Inspector() {
        Conex conexion = new Conex();
        bd=conexion.iniciarFirebase();
    }

    public static boolean agregarOferta(Oferta oferta)
    {
        Map<String, Object> docUsuario = new HashMap<>();
        docUsuario.put("idOferta", oferta.getidOferta());
        docUsuario.put("idEmpresa", oferta.getidEmpresa());
        docUsuario.put("nombreE", oferta.getnombreE());
        docUsuario.put("carreraProfesional", oferta.getcarreraProfesional());
        docUsuario.put("sueldo", oferta.getsueldo());
        docUsuario.put("sector", oferta.getsector());
        docUsuario.put("experiencia", oferta.getExperiencia());
        docUsuario.put("edad", oferta.getEdad());
        ApiFuture<WriteResult> future = bd.collection("ofertas").document(Integer.toString(oferta.getidOferta())).set(docUsuario);
        try {
            System.out.println("Ofertas Update : " + future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Oferta consultarOferta(String idOferta)
    {
        Oferta retorno = null;
        DocumentReference docRef = bd.collection("ofertas").document(idOferta);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot documento;
        try {
            documento = future.get();
            if (documento.exists()) {
                retorno = documento.toObject(Oferta.class);
                //System.out.println("Document data: " + documento.getData());
            } else {
                System.out.println("No existe la oferta!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return retorno;
    }
}