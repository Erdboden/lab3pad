package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import http.entities.Employee;
import http.repositories.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/employees", new ListEmployeesHandler());
        server.createContext("/employees/show", new ShowEmployeeHandler());
        server.createContext("/employees/create", new CreateEmployeeHandler());
//
//        server.createContext("/departments", new ListDepartmentsHandler());
//        server.createContext("/departments/show/{id}", new ShowDepartmentHandler());
//        server.createContext("/departments/create", new CreateEmployeeHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

//        URL yahoo = new URL("http://localhost:8000/test");
//        URLConnection yc = yahoo.openConnection();
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                        yc.getInputStream()));
//        String inputLine;
//
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
//        in.close();

    }
    static class ListEmployeesHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
            List<Employee> employees = employeeRepository.getEmployees();


            t.sendResponseHeaders(200, employees.toString().length());
            OutputStream os = t.getResponseBody();

            os.write(employees.toString().getBytes());

            os.close();
        }
    }

    static class CreateEmployeeHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
           employeeRepository.pushEmployee(new Employee(1,"Name", "Surename", 1));
           String success="200";
            t.sendResponseHeaders(200,success.length());
            OutputStream os = t.getResponseBody();
            os.write(success.getBytes());
            os.close();
        }
    }

    static class ShowEmployeeHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
            Map<String, String> params = queryToMap(t.getRequestURI().getQuery());
            Integer id = Integer.parseInt(params.get("id"));
            for (Employee employee:
                 employeeRepository.getEmployees()) {
                if(employee.getId()==id){
                    t.sendResponseHeaders(200,employee.toString().length());
                    OutputStream os = t.getResponseBody();
                    os.write(employee.toString().getBytes());
                    os.close();
                    break;
                }
            }
        }
    }
    public static Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
