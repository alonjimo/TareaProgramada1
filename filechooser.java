import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
 
public class filechooser {
     
    String nombreArchivo;
    String error = "Se cancel๓ la ejecuci๓n";
     
    public filechooser() throws IOException{
         
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fc.showOpenDialog(null);
                switch (result){
                 case JFileChooser.APPROVE_OPTION: 
                     nombreArchivo = fc.getSelectedFile().toString();
                     System.out.println(nombreArchivo);
                     break;
                 case JFileChooser.CANCEL_OPTION:
                     JOptionPane.showMessageDialog(null,"Se cancelo la ejecucion del programa"
                             , "Confirmaciรณn", JOptionPane.WARNING_MESSAGE);
                     System.exit(0);
                     break;
                 case JFileChooser.ERROR_OPTION:
                     JOptionPane.showMessageDialog(null,"Sucedio un error inesperado"
                             , "Error", JOptionPane.ERROR_MESSAGE);
                     System.exit(0);    
                     break;
                }        
        }
}