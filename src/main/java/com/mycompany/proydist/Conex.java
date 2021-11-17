/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proydist;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.*;
import com.google.firebase.cloud.FirestoreClient;

/**
 *
 * @author puma0
 */


public class Conex {

    public Firestore iniciarFirebase(){

        try {
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("google-services.json"))).build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }
}

