package eu.pozoga.nice.classes.demo1;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.classes.PackFilter;
import eu.pozoga.nice.classes.SimplePackFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Demo1Servlet", urlPatterns = {"/Demo1"})
public class Demo1Servlet extends HttpServlet {

    @Inject
    protected People people;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("objects", people.getAll());
        RequestDispatcher view = request.getRequestDispatcher("examples/demo1/view.jspx");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Vote for human
        if (request.getParameter("voteHuman") != null) {
            String humanName = request.getParameter("name");
            Human human = (Human) people.getCloud().get(humanName);
            human.vote();
        }

        //Vote for all workers
        if (request.getParameter("voteWorkers") != null) {
            voteGroup(Worker.class);
        }
        
        //Vote for all fishermans
        if (request.getParameter("voteFishermans") != null) {
            voteGroup(Fisherman.class);
        }
        
        //Vote for all humans
        if (request.getParameter("voteHumans") != null) {
            voteGroup(Human.class);
        }

        doGet(request, response);
    }

    private void voteGroup(Class clazz) throws ServletException {
        try {
            PackFilter filter = new SimplePackFilter(clazz, null);
            C.getInstance().select(filter).invoke(people.getAll(), "vote", new Object[]{}, null);
        } catch (Exception ex) {
            Logger.getLogger(Demo1Servlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }
}
