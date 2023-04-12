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
        // Query 1
        a.getCountriesLargestPopToSmallest();
        for ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
        // Query 2
        a.getcountriesbycontinent("Europe");
        for ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
        // Query 3
        a.getCountriesByRegionLargestPopToSmallest ( "Western Europe");
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }

       // Query 4
        a.getTopPoppulatedCountries( 5);
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
        // Query 5
        getTopPopulatedCountriesInContinent(5, "Europe");
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
       // Query 6
        getTopPopulatedCountriesInRegion(6, "British Asia");
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
       // Query 7
        a.getCityLargestPopToSmallest();
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
        // Query 8
        a.getCitiesLargestPopToSmallestByContinent( "Asia");

        for   ( int i = 0 ; i<=1; i++)
        {
            System.out.println("//////////////////////");
        }
        // Query 9
        a.getCitiesLargestPopToSmallest();
        for   ( int i = 0 ; i<=4; i++)
        {
            System.out.println("//////////////////////");
        }
        // query 10
        a.getCitiesLargestPopInCountry("Pakistan");
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
        // query 11
        a.getCitiesLargestPopInDistrict("karachi");
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }



        // query 12
      a.getTopPopulatedCities(6);
        for   ( int i = 0 ; i<=2; i++)
        {
            System.out.println("//////////////////////");
        }
      // Query 13
        a.getTopPopulatedCitiesInContinent("Europe", 10);
        for   ( int i = 0 ; i<=3; i++)
        {
            System.out.println("//////////////////////");
        }
      // Query 14
        a.getNCitiesLargestPopInRegion("Eastern Asia" , 6);
        for   ( int i = 0 ; i<=3; i++)
        {
            System.out.println("//////////////////////");
        }
     // Query 15
        a.getTopNCitiesInCountry("Portugal", 5);
        for (int i = 0 ; i<=4; i++)
        {
            System.out.println("////////////////////////");
        }
      // Query 16
        a.getTopNCitiesInDistrict("Europe", 5);
        for (int i = 0 ; i<=4; i++)
        {
            System.out.println("////////////////////////");
        }
      // Query 17
        a.getCapitalCitiesLargestPop();
        for (int i = 0 ; i<=4; i++)
        {
            System.out.println("////////////////////////");
        }
    // Query 18
        a.getCapitalCitiesByContinent("Europe");
        {
            System.out.println("Answer for Query 18 is above...........");
        }
        // Query 19
        a.getCapitalCitiesLargestPopInRegion("Eastern Asia");
        {
            System.out.println("Answer for query 19..........");
        }
        // Query 20
        a.getTopNCapitalCities(10);
        {
            System.out.println("Answer for Query 20");
        }
       // Query 21
        a.getTopNCapitalCitiesInContinent("Europe", 6);
        {
            System.out.println("Answer for query 21");
        }
       // Query 22
        a.getPopulationByContinent();
        {
            System.out.println("Answer for query 22");
        }
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

    /**
     *
     * QUERY 1
     *
     */
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
     * QUERY 2
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


    /** QUERY 3
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
/** QUERY 4
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

    /**
     * Query 5
     *
     * /*The top N populated countries IN a continent WHERE N IS provided BY the USER*/

    public static void getTopPopulatedCountriesInContinent(int n, String continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name AS Country, Population, Continent " +
                            "FROM country " +
                            "WHERE Continent = '" + continent + "' " +
                            "ORDER BY Population DESC " +
                            "LIMIT " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check if any records are returned
            if (!rset.isBeforeFirst()) {
                System.out.println("No records found.");
                return;
            }

            // Print out the records
            while (rset.next()) {
                String name = rset.getString("Country");
                int population = rset.getInt("Population");
                System.out.println(name + "\t" + population);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to complete query.");
        }
    }
    /**
     * Query 6
     * Populated countries in a region
     */
    public static void getTopPopulatedCountriesInRegion(int n, String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name AS Country, Population, Region " +
                            "FROM country " +
                            "WHERE Region='" + region + "' " +
                            "ORDER BY Population DESC " +
                            "LIMIT " + n;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check one is returned
            while (rset.next()) {
                int pop = rset.getInt("Population");
                String name = rset.getString("Country");
                System.out.println(name + "\t" + pop);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to Complete Query");
        }
    }

    /**
     *
     * Query 7
     *
     *
     */
    public Statement CcreateStatement(Connection con) throws SQLException {
        return con.createStatement();
    }
    private String CcreateSelectQuery() {
        return "SELECT Name As City, Population FROM city ORDER BY Population DESC";
    }
    private ResultSet eexecuteQuery(Statement stmt, String query) throws SQLException {
        return stmt.executeQuery(query);
    }

    private void printCityData(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int pop = resultSet.getInt("Population");
            String name = resultSet.getString("City");
            System.out.println("" + name + "\t" + pop);
        }
    }
    public void getCityLargestPopToSmallest() {
        try {
            Statement stmt = CcreateStatement(con);
            String strSelect = CcreateSelectQuery();
            ResultSet rset = eexecuteQuery(stmt, strSelect);
            printCityData(rset);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to Complete Queries ");
        }
    }

    /**
     *
     * Query 8
     */
    public void getCitiesLargestPopToSmallestByContinent(String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name As City, city.Population, country.Continent "
                            + "FROM city JOIN country on city.CountryCode=country.Code "
                            + "WHERE country.Continent='" + continent + "' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Iterate through the result set and print the data
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                String continentName = rset.getString("Continent");
                System.out.println(cityName + " | Population: " + population + " | Continent: " + continentName);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
/**
 * Query 9
 */
public void getCitiesLargestPopToSmallest() {
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String strSelect = "SELECT city.Name As City, city.Population, country.Region " +
                "FROM city JOIN country on city.CountryCode=country.Code " +
                "ORDER BY Population DESC";
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);
        // Process the result set
        processCitiesResultSet(rset);
    } catch (SQLException e) {
        System.out.println("Error executing query: " + e.getMessage());
    }
}

    private void processCitiesResultSet(ResultSet rset) throws SQLException {
        while (rset.next()) {
            // Retrieve data from result set
            String city = rset.getString("City");
            int population = rset.getInt("Population");
            String region = rset.getString("Region");

            // Display values
            System.out.println(city + "\t" + population + "\t" + region);
        }
    }

    /**
     * Query 10
     */
    public void getCitiesLargestPopInCountry(String countryName) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name AS City, Population, District " +
                            "FROM city " +
                            "WHERE CountryCode = (SELECT Code FROM country WHERE Name = '" + countryName + "') " +
                            "ORDER BY Population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                System.out.println(cityName + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Query 11
     */
    public void getCitiesLargestPopInDistrict(String districtName) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name AS City, city.Population, city.District " +
                            "FROM city " +
                            "WHERE city.District = '" + districtName + "' " +
                            "ORDER BY city.Population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                System.out.println(cityName + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
         * Query 12
         */
        public void getTopPopulatedCities(int limit) {
            try {
                Statement stmt = con.createStatement();
                String strSelect = "SELECT Name As City, Population FROM city ORDER BY Population DESC LIMIT " + limit;
                ResultSet rset = stmt.executeQuery(strSelect);
                while (rset.next()) {
                    String cityName = rset.getString("City");
                    int population = rset.getInt("Population");
                    System.out.println(cityName + " - " + population);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    /**
     * Query 13
     *
     * *The top N populated cities in a continent where N is provided by the user*/

    public void getTopPopulatedCitiesInContinent(String continent, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name AS City, city.Population, country.Continent " +
                            "FROM city JOIN country ON city.CountryCode = country.Code " +
                            "WHERE country.Continent = '" + continent + "' " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT " + limit + ";";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                System.out.println(cityName + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Query 14
     * The top populated n countries in a region.............
     *
     */
    public void getNCitiesLargestPopInRegion(String regionName, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name AS City, city.Population, country.Region " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "WHERE country.Region = '" + regionName + "' " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT " + n + ";";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                System.out.println(cityName + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Query15
     * Top N cities in a country
     *
     */
    public void getTopNCitiesInCountry(String countryName, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name AS City, city.Population, country.Name AS Country " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Name = '" + countryName + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + N + ";";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Loop through the result set and print each city and its population
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                System.out.println(cityName + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Query 16
     * Top N in a Distrivy
     *
     *
     */



    public void getTopNCitiesInDistrict(String districtName, int n) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name AS City, Population, District " +
                            "FROM city " +
                            "WHERE District = '" + districtName + "' " +
                            "ORDER BY Population DESC " +
                            "LIMIT " + n;

            // Execute the SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print the city name and population
            while (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");
                System.out.println(cityName + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Query 17
     */
    public void getCapitalCitiesLargestPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population "
                    + "FROM city "
                    + "JOIN country ON city.ID = country.Capital "
                    + "ORDER BY city.Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                String capitalCity = rset.getString("CapitalCity");
                String country = rset.getString("Country");
                int population = rset.getInt("Population");
                System.out.println(capitalCity + " - " + country + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Query 18 Capital citites in a continent
     */

    public void getCapitalCitiesByContinent(String continentName) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name AS CapitalCity, country.Name AS Country, country.Continent, city.Population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "WHERE country.Continent = '" + continentName + "' " +
                            "ORDER BY city.Population DESC;";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                String capitalCityName = rset.getString("CapitalCity");
                String countryName = rset.getString("Country");
                String continent = rset.getString("Continent");
                int population = rset.getInt("Population");
                System.out.println(capitalCityName + " - " + countryName + " - " + continent + " - " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * Query 19 Capital cities in a region
 */
public void getCapitalCitiesLargestPopInRegion(String regionName) {
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String strSelect = "SELECT city.Name AS CapitalCity, country.Name AS Country, country.Region, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = '" + regionName + "' " +
                "ORDER BY city.Population DESC;";
        ResultSet rset = stmt.executeQuery(strSelect);
        while (rset.next()) {
            String cityName = rset.getString("CapitalCity");
            String countryName = rset.getString("Country");
            String region = rset.getString("Region");
            int population = rset.getInt("Population");
            System.out.println(cityName + " - " + countryName + " (" + region + ")" + " - " + population);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

/**
 * Query 20 top n populated capital cities.
 */
public void getTopNCapitalCities(int n) {
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                        "FROM city " +
                        "JOIN country ON city.ID = country.Capital " +
                        "ORDER BY city.Population DESC " +
                        "LIMIT " + n + ";";

        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);

        // Process the result set
        while (rset.next()) {
            String capitalCity = rset.getString("CapitalCity");
            String country = rset.getString("Country");
            int population = rset.getInt("Population");

            System.out.println(capitalCity + " - " + country + " - " + population);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
/**
 * Query 21 Top N captian cities in a continent
 */
public void getTopNCapitalCitiesInContinent(String ContinentName, int n) {
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();

        // Create string for SQL statement
        String strSelect =
                "SELECT city.Name AS CapitalCity, country.Name AS Country, country.Continent, city.Population " +
                        "FROM city " +
                        "JOIN country ON city.ID = country.Capital " +
                        "WHERE country.Continent = '" + ContinentName + "' " +
                        "ORDER BY city.Population DESC " +
                        "LIMIT " + n + ";";

        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);

        // Process the result set
        while (rset.next()) {
            String capitalCity = rset.getString("CapitalCity");
            String country = rset.getString("Country");
            String continent = rset.getString("Continent");
            int population = rset.getInt("Population");

            System.out.println(capitalCity + " - " + country + " - " + continent + " - " + population);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    public void getPopulationByContinent() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, " +
                            "SUM(country.Population) AS TotalPopulation, " +
                            "SUM(city.Population) AS UrbanPopulation, " +
                            "SUM(country.Population) - SUM(city.Population) AS RuralPopulation " +
                            "FROM country " +
                            "LEFT JOIN city ON country.Capital = city.ID " +
                            "GROUP BY country.Continent;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Process the result set
            while (rset.next()) {
                String continent = rset.getString("Continent");
                long totalPopulation = rset.getLong("TotalPopulation");
                long urbanPopulation = rset.getLong("UrbanPopulation");
                long ruralPopulation = rset.getLong("RuralPopulation");

                System.out.println(continent + " - Total: " + totalPopulation +
                        ", Urban: " + urbanPopulation + ", Rural: " + ruralPopulation);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
