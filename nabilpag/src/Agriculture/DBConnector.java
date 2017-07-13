
package Agriculture;


import java.sql.*;
import sun.nio.cs.ext.PCK;
 

public class DBConnector 
{
    private Connection con;
    private Statement st;
    private ResultSet rs;


    public DBConnector ()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
//            database=agri_data;server=sharifserver.mysql.database.azure.com;uid=MSISharif@sharifserver;pwd=******
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agri_data","root","");
            con = DriverManager.getConnection("jdbc:mysql://sharifserver.mysql.database.azure.com:3306/agri_data","MSISharif@sharifserver","MD74274385$");
            st = con.createStatement();
            
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            System.err.println("Error"+ex);
        }
    }
    
    public ResultSet getData(String npk, String grainN)
      {
        try{
            
            String query = "select "+npk+" from grain where grain_name = '"+grainN+"'";
            
            rs = st.executeQuery(query);
            
            
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
        return rs;
    }
      
    public ResultSet getSoilType(String npk, String soilType, String landType) throws SQLException
      {
          ResultSet soilresult = null;
          
          String query1 = "select "+npk+" from interpretation where soil_type = '"+soilType+"' && land_type = '"+landType+"'";
          
          soilresult = st.executeQuery(query1);
          
          return soilresult;
      }
      
    public ResultSet getGrainType(String grainType) throws SQLException
    {
          ResultSet grainResult = null;
          
          String query1 = "select * from grain where grain_name = '"+grainType+"'";
          
          grainResult = st.executeQuery(query1);
          
          return grainResult;
      }
    
    
    public ResultSet get_soil_info(String a, String b) throws SQLException
    {
        ResultSet soil_rs = null;
        String query = "select * from soil_information where soil_series ='"+a+"' && land_classification = '"+b+"'";
        soil_rs =  st.executeQuery(query);
        return  soil_rs;
    }

   public ResultSet get_suitable_land(String a) throws SQLException
    {
        ResultSet suit_land_set = null;
        String query1 = "select suitable_land from grain where grain_name = '"+a+"'";
        suit_land_set = st.executeQuery(query1);
        
        return  suit_land_set;
    }
   
   
   public ResultSet get_ph() throws SQLException
   {
       ResultSet rs = null;
       String query = "select * from Ph_table";
       rs = st.executeQuery(query);
       return rs;
   }
   
   public ResultSet get_crop_status_acc_ph(String ph, String crop, float f) throws SQLException
   {
       ResultSet cph = null;
       String query = "select * from grain where grain_name = '"+crop+"'";
       cph = st.executeQuery(query);
       return cph;
       
   }
   
   public ResultSet check_crop(String ccrop) throws SQLException
   {
       ResultSet crop_find = null;
       String query = "select * from grain where grain_name = '"+ccrop+"'";
       crop_find = st.executeQuery(query);
       return crop_find;
   }
   
   public ResultSet get_map_info(String map_no) throws SQLException
   {
       ResultSet map_rs = null;
       String query = "select * from mapping_units where mapping_unit_no = '"+map_no+"'";
       map_rs = st.executeQuery(query);
       return map_rs;
   }
   
   public ResultSet get_mouza_details(String mouza_n) throws SQLException
   {
       ResultSet mouza_details = null;
       String query = "select * from mouza_table where mouza_name = '"+mouza_n+"'";
       mouza_details = st.executeQuery(query);
       return mouza_details;
   }
   
   public ResultSet get_check_soil(String land_type, String soil_l, String a, String b, int change) throws SQLException
   {
       ResultSet check_soil = null;
       if(change == 1)
         {
              String query = "select soil_series from soil_information where land_classification ='"+land_type+"' && soil_series ='"+soil_l+"' && water_removing_fact = '"+a+"' && soil_solidness = '"+b+"'";
              check_soil =  st.executeQuery(query);     
         }
       if(change == 2)
         {
              String query = "select soil_series from soil_information where land_classification ='"+land_type+"' && soil_series ='"+soil_l+"' && water_removing_fact = '"+a+"'";
              check_soil =  st.executeQuery(query);     
         }
      
       return check_soil;
   }
   
   public ResultSet input_info(String info, int a) throws SQLException
   {
       ResultSet rs_info = null;
       String query = null;
       if(a == 1)
       {
          query = "select unions from upazila_info where upazila_name = '"+info+"'"; 
       }
       if(a == 2)
       {
          query = "select mouza_name from mouza_table where union_name = '"+info+"'"; 
       }
       
       rs_info = st.executeQuery(query);
       return rs_info;
   }
}
