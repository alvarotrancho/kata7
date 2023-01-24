package kata7;

import java.sql.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class FlightApp {
    static String database = "jdbc:sqlite:flights.db";
    
    public String getAll() {
        String sql = "SELECT * FROM flights";
        return this.getDataFromSql(sql);
    }
    
    public String getDay(String day) {
        String sql = "SELECT * FROM flights WHERE DAY_OF_WEEK=" + this.getNumberOfDay(day);
        return this.getDataFromSql(sql);
    }
    public String  getBiggerDistance(String distance) {
        String sql = "SELECT * FROM flights WHERE DISTANCE >=" + distance;
        return this.getDataFromSql(sql);
    }

    public String  getLowerDistance(String distance) {
        String sql = "SELECT * FROM flights WHERE DISTANCE <=" + distance;
        return this.getDataFromSql(sql);
    }
    
    public String getCancelled() {
        String sql = "SELECT * FROM flights WHERE CANCELLED =1";
        return this.getDataFromSql(sql);
    }

    public String getDiverted() {
        String sql = "SELECT * FROM flights WHERE DIVERTED=1";
        return this.getDataFromSql(sql);
    
    }
    
    private Connection connect() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(database);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }

    private String serializeList(List<Flight> flights) {
        return new Gson().toJson(flights);
    }
    
    private List<Flight> resultSetToList(ResultSet res) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (res.next()) {
            flights.add(new Flight(
                    res.getInt(1),
                    res.getInt(2),
                    res.getInt(3),
                    res.getInt(4),
                    res.getInt(5),
                    res.getInt(6),
                    res.getInt(7),
                    res.getInt(8),
                    res.getInt(9))
            );
        }
        return flights;
    }

    private String getNumberOfDay(String day) {
        switch(day) {
            case "monday": return "1";
            case "tuesday": return "2";
            case "wednesday": return "3";
            case "thusrday": return "4";
            case "friday": return "5";
            case "saturday": return "6";
            case "sunday": return "7";
        }
        return "-1";
    }

    
    private String getDataFromSql(String sql) {
        List<Flight> flights = null;
        
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql);) {
                flights = this.resultSetToList(res);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return this.serializeList(flights);
    }

}
