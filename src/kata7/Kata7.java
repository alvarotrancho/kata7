package kata7;

import static spark.Spark.get;

public class Kata7 {

    public static void main(String[] args) {
        FlightApp flightapp = new FlightApp();
                
        get("/flights/all", (req, res) -> {
            return flightapp.getAll();
        });
        
        get("/flights/day/:day", (req, res) -> {
            return flightapp.getDay(req.params(":day"));
        });
        
        get("/flights/distance/bigger/:distance", (req, res) -> {
            return flightapp.getBiggerDistance(req.params(":distance"));
        });
        
        get("/flights/distance/lower/:distance", (req, res) -> {
            return flightapp.getLowerDistance(req.params(":distance"));
        });
        
        get("/flights/cancelled", (req, res) -> {
            return flightapp.getCancelled();
        });
        
        get("/flights/diverted", (req, res) -> {
            return flightapp.getDiverted();
        });
    }
    
}
