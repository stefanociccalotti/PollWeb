package it.univaq.disim.controller;

import it.univaq.disim.dao.Interface.SurveyInterface;
import it.univaq.disim.dao.SurveyDao;
import it.univaq.disim.model.QuestionModel;
import it.univaq.disim.model.SurveyModel;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "ViewSurveysController")
public class ViewSurveysController extends HttpServlet {
    SurveyInterface surveyDao = new SurveyDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request,response,"");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String action = request.getParameter("action");
        processRequest(request,response, action);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String action){
        switch(action){
            case "viewresult":
                action_viewResult(request,response);
                break;
            case "closesurvey":
                action_closeSurvey(request,response);
                break;
            case "publish":
                action_publish(request,response);
                break;
            default:
                action_getViewSurvey(request,response);
        }

    }

    private void action_getViewSurvey(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        SurveyInterface surveyDao = new SurveyDao();

        try {

            ArrayList<SurveyModel> list = surveyDao.getSurveyByUser((Integer) session.getAttribute("userID"), "viewSurveys");
            request.setAttribute("list", list);
            request.setAttribute("pageCss", "./resources/dist/css/viewSurveys.css");
            request.getRequestDispatcher("jsp/viewSurveys.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_viewResult(HttpServletRequest request, HttpServletResponse response) {
        Integer idsurvey = Integer.valueOf(request.getParameter("surveyid"));
        try {
            ArrayList<QuestionModel> listanswer = surveyDao.getSurveyResult(idsurvey);
            JSON2CSV(listanswer,idsurvey);
            String filePath ="C:/Users/cakes/Desktop/PollWeb/src/main/webapp/resources/csv/answerSurvey"+idsurvey+".csv";
            downloadFile(filePath,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void downloadFile(String filePath,HttpServletResponse response) {
        try {
            File downloadFile = new File(filePath);
            FileInputStream inStream = new FileInputStream(downloadFile);

            ServletContext context = getServletContext();

            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_closeSurvey(HttpServletRequest request, HttpServletResponse response) {
        Integer idsurvey = Integer.valueOf(request.getParameter("surveyid"));
        try {
            surveyDao.surveyClose(idsurvey);
            response.sendRedirect("viewSurveys");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_publish(HttpServletRequest request, HttpServletResponse response) {
        Integer idsurvey = Integer.valueOf(request.getParameter("surveyid"));
        try {
            surveyDao.publishSurvey(idsurvey);
            response.sendRedirect("viewSurveys");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void JSON2CSV(ArrayList<QuestionModel> listanswer, Integer id){
        File file=new File("C:/Users/cakes/Desktop/PollWeb/src/main/webapp/resources/csv/answerSurvey"+id+".csv");
        System.out.println(file);
        Iterator<QuestionModel> itr = listanswer.iterator();
          /*  try {
                FileUtils.writeStringToFile(file, String.valueOf(listanswer), Charset.defaultCharset());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        try (PrintWriter writer = new PrintWriter(file)) {

            StringBuilder sb = new StringBuilder();
            sb.append("ID compiler");
            sb.append(',');
            sb.append("Numero Domanda");
            sb.append(',');
            sb.append("Domanda");
            sb.append(',');
            sb.append("Risposta");
            sb.append('\n');

            while(itr.hasNext()){
                QuestionModel tmp = itr.next();
                sb.append(tmp.getId());
                sb.append(',');
                sb.append(tmp.getSurveyId());
                sb.append(',');
                sb.append(tmp.getNote());
                sb.append(',');
                sb.append(tmp.getQuestion().substring(0,tmp.getQuestion().length()-1));
                sb.append('\n');
            }

            writer.write(sb.toString());

  /*
            String fromFile ="http://localhost:8080/web-engineering-pollweb/resources/csv/answerSurvey"+id+".csv";
            String toFile ="C:/Users/cakes/Desktop/answerSurveyDW"+id+".csv";

            FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);*/


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
