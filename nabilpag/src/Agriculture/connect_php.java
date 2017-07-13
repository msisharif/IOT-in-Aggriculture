package Agriculture;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class connect_php {
    
    calculation cal = new calculation();
    @SuppressWarnings("unchecked")
    public void connect_php() {
        JSONParser parser = new JSONParser();
        FileWriter fw = null;
//        BufferedWriter bw = null;
 
        try {
 
            Object obj = parser.parse(new FileReader("D:\\home\\site\\wwwroot\\dataShare.txt"));
            
            JSONObject jsonObject = (JSONObject) obj;
            
            String union = (String) jsonObject.get("union");
            String mouza_name = (String) jsonObject.get("mouza");
            String land_type = (String) jsonObject.get("land_type");
            String wrf = (String) jsonObject.get("wrf");
            String soil_solidness = (String) jsonObject.get("soil_solidness");
            float N = Float.parseFloat((String) jsonObject.get("N"));
            float P = Float.parseFloat((String) jsonObject.get("P"));
            float K = Float.parseFloat((String) jsonObject.get("K"));
            float PH = Float.parseFloat((String) jsonObject.get("PH"));
 
            //System.out.println(union+mouza_name+land_type+wrf+soil_solidness+N+P+K+PH);
            cal.get_input(union,mouza_name,land_type,wrf,soil_solidness,N,P,K,PH);    
            
//            fw = new FileWriter("C:\\Users\\shane\\Documents\\My Web Sites\\MSISharifApp\\dataShare3.txt");
            fw = new FileWriter("D:\\home\\site\\wwwroot\\dataShare3.txt");            
//            bw = new BufferedWriter(fw);
//            bw.write(" islam \n seriously?? \n");
            fw.write(calculation.jsobj.toJSONString());
            

//            bw.close();
            fw.close();

 
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

}

