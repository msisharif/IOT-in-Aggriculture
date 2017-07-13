/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author shane
 */
public class ZTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String local = "C:\\new\\hagu.txt";
        String global = "D:\\home\\site\\wwwroot\\dataShare3.txt";
        String san = "asdfg";
        JSONObject obj = new JSONObject();
        obj.put("Name", san);
        obj.put("Author", "App Shah");

        JSONArray company = new JSONArray();
        company.add("Compnay: eBay");
        company.add("Compnay: Paypal");
        company.add("Compnay: Google");
        obj.put("Company List", company);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter(global)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        } catch (IOException ex) {
            Logger.getLogger(ZTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
