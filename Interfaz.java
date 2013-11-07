import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
public class Interfaz extends JFrame {
	public JPanel contentPane;	
	public JTable tableEstatico;
	public JTable tableDinamico;
	public JTextPane textfield_estatico = new JTextPane(); 
	public JTextPane textfield_dinamico = new JTextPane();
	public Interfaz(String codigo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProgramaOriginal = new JLabel("Programa Original");
		lblProgramaOriginal.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramaOriginal.setBounds(120, 11, 226, 14);
		contentPane.add(lblProgramaOriginal);
		
		JLabel lblNewLabel = new JLabel("Ambiente Est\u00E1tico");
		lblNewLabel.setBounds(49, 211, 103, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAmbienteDinmico = new JLabel("Ambiente Din\u00E1mico");
		lblAmbienteDinmico.setBounds(303, 211, 147, 14);
		contentPane.add(lblAmbienteDinmico);
		
		
		textfield_estatico.setEditable(false);
        textfield_estatico.setBounds(0, 0, 343, 1000); 
        
        final JTextPane textfield_codigo = new JTextPane();
        textfield_codigo.setEditable(false);
        textfield_codigo.setBounds(0, 0, 343, 1000);
        
        final JScrollPane SPCodigo = new JScrollPane(textfield_codigo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        SPCodigo.setBounds(22, 37, 434, 163); 
        SPCodigo.setHorizontalScrollBar(null); 
        SPCodigo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
          
        final JScrollPane SPEstatico = new JScrollPane(textfield_estatico,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        SPEstatico.setBounds(22, 246, 212, 183); 
        SPEstatico.setHorizontalScrollBar(null); 
        SPEstatico.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        contentPane.add(SPEstatico); 
        
        
        textfield_dinamico.setEditable(false);
        textfield_dinamico.setBounds(0, 0, 343, 1000);
          
        final JScrollPane SPDinamico = new JScrollPane(textfield_dinamico,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        SPDinamico.setBounds(244, 246, 212, 183); 
        SPDinamico.setHorizontalScrollBar(null); 
        SPDinamico.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        textfield_codigo.setText(codigo);
        contentPane.add(SPCodigo);
        contentPane.add(SPDinamico);
		
	}
	public void modificarestatico(String variables){
		textfield_estatico.setText(variables);
	}
	
	
}