
package Agriculture;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class calculation 
{
    JSONParser parser = new JSONParser();
    FileWriter fw = null;
    BufferedWriter bw = null;
 
    DBConnector db = new DBConnector();
    String stype,land_type,soil_series = "";
    float given[] = new float[3],ph_value;
    String npk[]={"N","P","K"};
    int flag = 0, condition = 0;
    public void get_input(String  union, String mouza_name, String land_t, String water_removing_fact, String soil_solidness, float N, float P, float K, float ph_value) throws SQLException, IOException
    {
        given[0] = N; 
        given[1] = P;
        given[2] = K;
        land_type = land_t;
//        ResultSet rs_info = null;
//        Scanner input = new Scanner(System.in);
//        String upazila = "gazipur sadar", union, mouza_name, water_removing_fact, soil_solidness;
//        rs_info = db.input_info(upazila,1);
//        rs_info.absolute(1);
//        
//        System.out.println("All uinions of "+upazila+" upazila's are :\n"+rs_info.getString(1));
//        
//        System.out.println("\nPlease enter the union name : ");
//        union = input.nextLine();
//        rs_info = db.input_info(union,2);
//        System.out.println("\nAll mouza of "+union+" union are :\n");
//        while(rs_info.next())
//        {
//            System.out.println(rs_info.getString(1));
//        }
//        
//        System.out.println("\nPlease enter the mouza name : ");
//        mouza_name = input.nextLine();
//        
//        System.out.println("\nPlease enter the land type : ");
//        land_type = input.next();
//        
//        System.out.println("\nPlease enter the water removing condition of land during rainy season : ");
//        water_removing_fact = input.next();
//        
//        System.out.println("\nPlease enter the soil solidness of land : ");
//        soil_solidness = input.next();
//        
//       for (int i = 0; i < given.length; i++)
//        {
//            System.out.println("\nPlease enter value of '"+npk[i]+"': ");
//            given[i] = input.nextFloat();
//        }
//       
//        System.out.println("\nPlease enter the value of ph : ");
//        ph_value = input.nextFloat();
        
        
        starting_with_mouza(mouza_name,water_removing_fact,soil_solidness);
        
    }
    
    public String[] first_split(String word)
    {
     String a[] = word.split("\\|");
     return a;
    }
   
   public String[] second_split(String word)
    { 
     String a[] = word.split("\\#");
     return a;
    }
   
   public String[] third_split(String word)
    { 
     String a[] = word.split("\\<>");
     return a;
    }
   
    
    public int interpretation(double standard, double given)
    {
        int store = (int)Math.ceil(given/standard);
        
        switch (store) {
            case 1:
                return 4;   //very low
            case 2:
                return 3;   //low
            case 3:
                return 2;   //medium
            case 4:
                return 1;   //optimum
            default:
                return 0;
        }
    }
    
    public int land_type_interpretor()
    {
        int ret = 0;
        switch (land_type) 
        {
            case "hl":
                ret = 5;
                break;
            case "mhl":
                ret = 6;
                break;
            case "ml":
                ret = 7;
                break;
            case "ll":
                ret = 8;
                break;
            case "vll":
                ret = 9;
                break;
            default:
                break;
        }
        return ret;
    }
    
    public float dataAcurator(float a)
    {
        String av = String.valueOf(a);
        av = av + "1";
//        System.out.println(a);
//        
//        String fi[] = av.split("\\.");
//        
//        int value =  Integer.parseInt(fi[1]);
//        value++;
//        fi[1] = ""+value;
//        
//        String finalResult = ""+fi[0]+"."+fi[1];
//        
//        float previousValue = Float.valueOf(finalResult);
        float previousValue = Float.valueOf(av);
        
        return previousValue;
        
    }
    
    
    public void math( String grainN, String soilType, String landType) throws SQLException
    {
       
        float uf=0,ci=0,cs=0,ls=0,result=0;
        for(int i=0; i<3; i++)
        {

            uf = getUf(npk[i], grainN, soilType, given[i], landType);
            ci = getCi(npk[i], grainN);
            cs = getCs(npk[i], soilType,landType);
            ls = getLs(npk[i], soilType, given[i],landType);

            result= ((uf - ((ci/cs)*(given[i] - ls))));
            if(npk[i] == "N")
            {
             result = result*(float) (100.0/46);
             System.out.println("N: "+result);
            }
            if(npk[i] == "P")
            {
             result = result*(float) (100.0/20);
             System.out.println("P: "+result);
            }
            if(npk[i] == "K")
            {
             result = result*(float) (100.0/50);
             System.out.println("K: "+result);
            }
        }
    }
    
        
        
    public float getLs(String npk, String soilType, float given, String landType) throws SQLException
    {
            
            float id;
            try (ResultSet interpretationData = db.getSoilType(npk, soilType,landType))
            {
                interpretationData.absolute(1);
                id = interpretationData.getFloat(npk);
            }
            
            return dataAcurator(id*(4 - interpretation(id, given)));
            
        }
    
    public float getCs(String npk, String soilType, String landType) throws SQLException
    {
            
            try (ResultSet rs = db.getSoilType(npk,soilType,landType)) 
            {
                rs.absolute(1);
                return rs.getFloat(npk);
            }
        }
    
    
    public float getCi(String npk, String grainN) throws SQLException
    {
            
            try (ResultSet rs = db.getData(npk,grainN)) 
            {
                rs.absolute(1);
                return rs.getFloat(npk);
            }
        }
    
    
    public float getUf(String npk, String grainN, String soilType, float given, String landType) throws SQLException
    {
    
            float rsnpk;
            try (ResultSet rs = db.getData(npk,grainN)) 
            {
                rs.absolute(1);
                rsnpk = rs.getFloat(npk);
            }

            float id;
            try (ResultSet interpretationData = db.getSoilType(npk, soilType, landType))
            {
                interpretationData.absolute(1);
                id = interpretationData.getFloat(npk);
            }
            float uf = interpretation(id, given) * rsnpk;
            

            return uf;
           
        }
    
     public ResultSet get_crop_acc_soil(String a) throws SQLException
     {
        
     try{
         
      
         ResultSet soil_test = db.get_soil_info(a,land_type);

        if(!soil_test.next())
        {System.out.println("sorry "+a+" is not in database.");}
        
        else
        {
         for(int i=2; i<=21 ; i++)
          {
            ResultSet soil = db.get_soil_info(a,land_type);
            soil.absolute(1);
           if(i<10)
            {
              String x= soil.getString(i);
              System.out.println(x);
              if(i == 6)
              {
                  stype = x;
              }
            }
        if(i>=10 && i<16)
            {
             if(i<=10){
             System.out.println("\nWithout Irrigation: ");
             
             }
             String x= soil.getString(i);
          
             crops(x,i,ph_value);
            }
        if(i>=16)
            {
             if(i<=16){
             System.out.println("\nIrrigation: ");
             
             }
             
             String x= soil.getString(i);
             crops(x,i,ph_value);
            }
            }
          } 
        
        }
        
        catch(SQLException ex){
            System.err.println(ex);
        }
                return null;
        }
     public void crops(String rst, int i, float f) throws SQLException
        {

            if(i == 10 || i == 16)
            {
                if(rst != null && !rst.isEmpty())
                {
                    System.out.println("\nRabi crops are: ");
                    spilt_s(rst,i,f);
                }    
            }
           if(i == 11 || i == 17)
            {
                if(rst != null && !rst.isEmpty())
                {
                    System.out.println("\nkharif 1 crops are: ");
                    spilt_s(rst,i,f);
                }
            }
           if(i == 12 || i == 18)
            {
                if(rst != null && !rst.isEmpty())
                {
                    System.out.println("\nkharif 2 crops are: ");
                    spilt_s(rst,i,f);
                }
            }
           if(i == 13 || i == 19)
            {
                if(rst != null && !rst.isEmpty())
                {
                    System.out.println("\none year crops are: ");
                    spilt_s(rst,i,f);
                }
            }
            if(i == 14 || i == 20)
            {
                if(rst != null && !rst.isEmpty())
                {
                    System.out.println("\nmany years crops are: ");
                    spilt_s(rst,i,f);
                }
            }
            if(i == 15 || i == 21)
            {
                if(rst != null && !rst.isEmpty())
                {
                    System.out.println("\ncrop distribution lists are: ");
                    spilt_s(rst,i,f);
                }
            }
            
        }   
     
    public String get_suitable_land_for_crop(String grain_n) throws SQLException
        {
            String suit_land;
            ResultSet suit_land_set = null;

            suit_land_set = db.get_suitable_land(grain_n);
            suit_land_set.absolute(1);

            suit_land=suit_land_set.getString(1);
            System.out.println("land '"+suit_land+"'");
            return suit_land;
        
        }
    
    public String ph_list_acc_crop(float x) throws SQLException
    {
        ResultSet r1;
        r1 = db.get_ph();
        String a,c= null;
        float c1,c2;
        
        while(r1.next())
        {
                a = r1.getString(1);
                String sp[] = a.split("\\|");    
                c1 = Float.parseFloat(sp[0]);
                c2 = Float.parseFloat(sp[1]);
                
                if(x >= c1 && x <= c2)
                {
                    c = sp[2];
                }
                   
         }
        return c;
    }
    
    public void check_crop_with_ph(String ph, String crop, float f) throws SQLException
    {
       ResultSet cph = null;
       cph = db.get_crop_status_acc_ph(ph,crop,f);
       cph.absolute(1);
       String ph_check = null;
       int count = 0,count1 = 1;
       for (int i = 6; i <= 7; i++) 
       {
           ph_check = cph.getString(i);
           String ph_c[] = first_split(ph_check);
           
           for (int j = 0; j < ph_c.length; j++)
           {
               String s1 = new String(ph_c[j]);
               String s2 = new String(ph);
               count1 = count1+j;

               if(s1.equals(s2))
               {
                   if(i == 6)
                   {
                       System.out.println("ph value is "+ph+"("+f+") and it is suitable for crop : "+crop+"");
                       i=i+2;
                   }
                   if(i == 7)
                   {
                       System.out.println("ph value is "+ph+"("+f+") and it is medium suitable for crop : "+crop+"");
                        i=i+1;
                   }

                   break;
               }
               else
               {
                   count++;
               }
           }
           if(count == count1)
           {
                System.out.println("ph value is "+ph+"("+f+") and it is not suitable for crop : "+crop+"");
           }
           
       }
    }
    
        
    public void spilt_s(String a, int j, float f) throws SQLException
        {
            String sui_la_cr,ph,c_crop;
            String[] words = first_split(a);
            
            if(j != 15 && j != 21)
                {
                    System.out.println("________________________________________________CROP_____________________________________________");
                }
               else
                {
                    System.out.println("________________________________________________CROP DISTRIBUTION_____________________________________________");
                }
            
            for (int i = 0; i < words.length; i++) 
            {
               ResultSet check_cr = null;
               
              
               System.out.println("\n"+words[i]);
               check_cr = db.check_crop(words[i]);
               
               if(!check_cr.next())
                {               
                     if(j != 15 && j != 21)
                     {
                         System.out.println("Sorry crop name : "+words[i]+" is not in database.");
                     }
                }
               else
               {
                    if(j != 15 && j != 21)
                    {
                       ph =  ph_list_acc_crop(f);
                       check_crop_with_ph(ph,words[i],f);
                       sui_la_cr = get_suitable_land_for_crop(words[i]);
                       math(words[i], stype, sui_la_cr);
                    }
               }
               

            }
    
        }
    
    
    public void get_soil_acc_map(String map_no) throws SQLException
    {
        ResultSet rs_map = null;
        rs_map = db.get_map_info(map_no);
        if(!rs_map.next())
                {               
                   System.out.println("Sorry "+map_no+" is not in database.");
                }
        else
        {
        int land_type_no = land_type_interpretor();
        rs_map.absolute(1);
        for (int i = 1; i <= 10; i++)
        {
            String a = rs_map.getString(i);
            if(i >= 1 && i <= 3)
            {
                System.out.println(a);
            }
            if(i == 4)
            {
                String check[] = first_split(a);
                for (int j = 0; j < check.length; j++)
                {
                    System.out.println(check[j]);
                }
            }
            if(i >= 5 && i <=9)
            {
                if(a != null && !a.isEmpty())
                {
                    String check[] = first_split(a);
                    System.out.println("\ntotal area :"+check[0]);
                    for (int j = 1; j < check.length; j++)
                    {
                        String check1[] = second_split(check[j]);
                        System.out.println(check1[0]+"("+check1[1]+")");
                        if(i == land_type_no) //first filtering according land classification.
                        {
                            if(soil_series.toUpperCase().contains(check1[0].toUpperCase()))
                            {
                                continue;
                            }
                            else
                            {
                                soil_series+=check1[0]+"|";
                            }   
                        }
                    }
                
                }
            }
            if(i == 10)
            {
                
                String check[] = third_split(a);
                for (int j = 0; j < check.length; j++)
                {
                    System.out.println("\n");
                    String check1[] = second_split(check[j]);
                     for (int l = 0; l < check1.length; l++)
                        {
                            String check2[] = first_split(check1[l]);
                            for (int m = 0; m < check2.length; m++)
                           {
                               System.out.println(check2[m]);
                           }
                            
                        }
                    
                }
            }
            
            
        }
        
       }
        
        
        
    }
    
    public void starting_with_mouza(String mouza_name, String water_removing_fact, String soil_solidness) throws SQLException, IOException
    {
        fw = new FileWriter("C:\\Users\\shane\\Documents\\My Web Sites\\MSISharifApp\\dataShare1.txt");
        bw = new BufferedWriter(fw);
        ResultSet mouza_rs = null;
        mouza_rs = db.get_mouza_details(mouza_name);
        mouza_rs.absolute(1);
        String x = null;
        String mouza_list[] = null;
        System.out.println("____________________________________________________MOUZA DETAILS________________________________________");
        for (int i = 1; i <=3 ; i++)
        {
            x = mouza_rs.getString(i);
            String mouza_det[] = first_split(x);
            if(i == 3)
            {
                mouza_list = mouza_det;
            }
            for (int j = 0; j < mouza_det.length; j++)
            { 
                System.out.println(mouza_det[j]);
            }
            
        }
        for (int i = 0; i < mouza_list.length; i++)
        {
            System.out.println("________________________________________________MAP_____________________________________________");
            get_soil_acc_map(mouza_list[i]);
        }
        
        System.out.println("\n________________________________________________SOIL LIST____________________________________________");
        if(soil_series != null && !soil_series.isEmpty())
        {
            System.out.println("\nAccording land type : '"+land_type+"', all soils are : '"+soil_series+"'");
        }
        else
        {
            System.out.println("Sorry land type : '"+land_type+"' is not present in this mouza");
        }
  
        String soil_list = filter_soil(water_removing_fact,soil_solidness);
        String soil_s[] = first_split(soil_list);
        for (int i = 0; i < soil_s.length; i++)
        {
            
            System.out.println("\n________________________________________________SOIL____________________________________________");
            System.out.println(soil_s[i]);
            if(condition == 2)
            {
                System.out.println("According water removing fact and soil solidness");
            }
            else if(condition == 1)
            {
                System.out.println("According water removing fact");
            }
            else
            {
                System.out.println("According only land type");
            }
            get_crop_acc_soil(soil_s[i]);
            
        }
    }
    
    public String filter_soil(String water_removing_fact, String soil_solidness) throws SQLException
    {
         ResultSet check_soil = null;
         String soil_l[] = first_split(soil_series);
         String soil_list = "";
         for (int i = 0; i < soil_l.length; i++)
         {
            check_soil = db.get_check_soil(land_type,soil_l[i],water_removing_fact,soil_solidness,1);
            if (!check_soil.next()) 
            {
                continue;
            }
            else 
            {
                check_soil.absolute(1);
                soil_list+=check_soil.getString("soil_series")+"|";
                flag = 1;
                condition = 2;
            }
            
         }
         if(flag == 0)
          {
               for (int i = 0; i < soil_l.length; i++)
                {
                check_soil = db.get_check_soil(land_type,soil_l[i],water_removing_fact,soil_solidness,2);
                    if (!check_soil.next()) 
                    {
                        continue;
                    }
                    else 
                    {
                        check_soil.absolute(1);
                        soil_list+=check_soil.getString("soil_series")+"|";
                        flag = 1;
                        condition = 1;
                    }
                }
              if(flag == 0)
                {
                    soil_list+=soil_series+"|";
                }
          }
         return soil_list;
    }
    
}
