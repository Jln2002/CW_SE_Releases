package com.napier.sem;

import java.sql.*;

/**
 *
 */
public class App
{
    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
          // call the SQL statements here

        a.getCountriesLargestPopToSmallest();
        for ( int i = 0 ; i<=10; i++)
        {
            System.out.println("//////////////////////");
        }
        a.getcountriesbycontinent("Europe");
        for ( int i = 0 ; i<=10; i++)
        {
            System.out.println("//////////////////////");
        }
        a.getCountriesByRegionLargestPopToSmallest ( "Western Europe");
      for   ( int i = 0 ; i<=10; i++)
        {
            System.out.println("//////////////////////");
        }


      a.getTopPoppulatedCountries( 5);

        // Disconnect from database
        a.disconnect();
    }
    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean needtowait = false;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                if (needtowait == true) {
                    Thread.sleep(10000);
                }
                System.out.println("finished waiting");
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;

            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
                needtowait = true;
            }

            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
  //  sql statements here and below
 public Statement createStatement(Connection con) throws SQLException {
      return con.createStatement();
  }
    private String createSelectQuery() {
        return "SELECT * FROM country ORDER BY Population DESC";
    }
    private ResultSet executeQuery(Statement stmt, String query) throws SQLException {
        return stmt.executeQuery(query);
    }
    private void printCountryData(ResultSet rset) throws SQLException {
        while (rset.next()) {
            int pop = rset.getInt("Population");
            String name = rset.getString("Name");
            System.out.println("" + name + "\t" + pop);
        }
    }
    public void getCountriesLargestPopToSmallest() {
        try {
            Statement stmt = createStatement(con);
            String strSelect = createSelectQuery();
            ResultSet rset = executeQuery(stmt, strSelect);
            printCountryData(rset);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to Complete Queries ");
        }
    }

    /**
     *
     * Query for population organised in a continent "Europe" we can use any continent name
     *
     */
    public String createSelectQueryForContinent(String continentName) {
        return "SELECT Name AS Country, Population, Continent " +
                "FROM country " +
                "WHERE Continent = '" + continentName + "' " +
                "ORDER BY Population DESC";
    }
    public ResultSet executeQueryForContinent(Statement stmt, String query) throws SQLException {
        return stmt.executeQuery(query);
    }
    private void printCountryDataForContinent(ResultSet rset) throws SQLException {
        while (rset.next()) {
            String name = rset.getString("Country");
            int population = rset.getInt("Population");
            String continent = rset.getString("Continent");
            System.out.println(name + "\t" + population + "\t" + continent);
        }
    }
    public void getcountriesbycontinent(String continentName) {
        try {
            Statement stmt = createStatement(con);
            String strSelect = createSelectQueryForContinent(continentName);
            ResultSet rset = executeQueryForContinent(stmt, strSelect);
            printCountryDataForContinent(rset);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to Complete Queries ");
        }
    }


    /**
     * Query for countries IN a region organised BY largest population TO smallest
     */
    private String createSelectQueryForRegion(String regionName) {
        return "SELECT Name AS Country, Population, Region " +
                "FROM country " +
                "WHERE Region = '" + regionName + "' " +
                "ORDER BY Population DESC";
    }
    private ResultSet executeQueryForRegion(Statement stmt, String query) throws SQLException {
        return stmt.executeQuery(query);
    }
    private void printCountryDataForRegion(ResultSet rset) throws SQLException {
        while (rset.next()) {
            String name = rset.getString("Country");
            int population = rset.getInt("Population");
            String region = rset.getString("Region");
            System.out.println(name + "\t" + population + "\t" + region);
        }
    }
    public void getCountriesByRegionLargestPopToSmallest(String regionName) {
        try {
            Statement stmt = createStatement(con);
            String strSelect = createSelectQueryForRegion(regionName);
            ResultSet rset = executeQueryForRegion(stmt, strSelect);
            printCountryDataForRegion(rset);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to Complete Queries ");
        }
    }
/**
 * Query for The top N populated countries IN the world WHERE N IS provided BY the USER*
 */
public void getTopPoppulatedCountries(int n)
{
    try
    {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String strSelect =
                "SELECT NAME As Country, Population " +
                        "FROM country " +
                        "ORDER BY Population DESC " +
                        "LIMIT " + n;
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);

        // Check one is returned
        while (rset.next())
        {
            int pop = rset.getInt("Population");
            String name = rset.getString("Country");
            System.out.println("" + name + "\t" + pop);
        }
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Failed to Complete Queries ");
    }
}

}
