
package Agriculture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;


public class Agriculture {

  
    public static void main(String[] args) throws SQLException, FileNotFoundException
    {        
        File file = new File("D:\\home\\site\\wwwroot\\output.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        connect_php cp = new connect_php();
        cp.connect_php();
        
    }
    
}
