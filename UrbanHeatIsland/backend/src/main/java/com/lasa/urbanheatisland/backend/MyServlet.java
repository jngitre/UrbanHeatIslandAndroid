/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.lasa.urbanheatisland.backend;

import com.google.api.client.util.Data;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

//import freemarker.*;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;

public class MyServlet extends HttpServlet {
    static Logger Log = Logger.getLogger("com.lasa.urbanheatisland.backend.MyServlet");

    ArrayList<String> id;
    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException, ServletException {
        FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(getServletContext()
                .getResourceAsStream("/WEB-INF/Urban-HeatIsland-Project-e31a3434c6ed.json"))
                .setDatabaseUrl("https://urban-heatisland-project.firebaseio.com/").build();
        try{
            FirebaseApp.getInstance();
        }catch(Exception error){
            Log.info(":(");
        }

        try{
            FirebaseApp.initializeApp(options);
        }catch(Exception error){
            Log.info(":((");
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("datapoint");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object snap = dataSnapshot.getValue();

                Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();
                id = new ArrayList<String>();
                while(children.hasNext()) {
                    id.add(children.next().getValue().toString());
                }
                Map<String, Object> input = new HashMap<String, Object>();
                Log.info(Arrays.toString(id.toArray()));
                //resp.getWriter().println(Arrays.toString(id.toArray()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        resp.setContentType("text/html");
        if(id == null){
            resp.getWriter().println("Refresh the page to see the data!");
        }else {
            resp.getWriter().println(id);

        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(getServletContext()
                .getResourceAsStream("/WEB-INF/Urban-HeatIsland-Project-e31a3434c6ed.json"))
                .setDatabaseUrl("https://urban-heatisland-project.firebaseio.com/").build();
        try{
            FirebaseApp.getInstance();
        }catch(Exception error){
            Log.info(":(");
        }

        try{
            FirebaseApp.initializeApp(options);
        }catch(Exception error){
            Log.info(":((");
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("datapoint");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object snap = dataSnapshot.getValue();

                Iterator<DataSnapshot> children = dataSnapshot.getChildren().iterator();
                id = new ArrayList<String>();
                while(children.hasNext()) {
                    id.add(children.next().getValue().toString());
                }
                Map<String, Object> input = new HashMap<String, Object>();
                Log.info(Arrays.toString(id.toArray()));
                //resp.getWriter().println(Arrays.toString(id.toArray()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        resp.setContentType("text/html");
        if(id == null){
            resp.getWriter().println("Refresh the page to see the data!");
        }else {
            resp.getWriter().println(id);
        }
    }
}
