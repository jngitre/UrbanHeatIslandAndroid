/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.lasa.urbanheatisland.backend;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.internal.Log;

import java.io.IOException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(getServletContext()
                .getResourceAsStream("/WEB-INF/Urban-HeatIsland-Project-368c016c7f71.json"))
                .setDatabaseUrl("https://urban-heatisland-project.firebaseio.com/").build();
        try{
            FirebaseApp.getInstance();
        }catch(Exception error){
            Log.i("Info", "uh oh exist");
        }
        try{ 
            FirebaseApp.initializeApp(options);
        }catch(Exception error){
            Log.i("Info", "uh oh too many exist");
        }

        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/plain");
        if(name == null) {
            resp.getWriter().println("Please enter a name");
        }
        resp.getWriter().println("Hello " + name);
    }
}
