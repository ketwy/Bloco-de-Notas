import javax.swing.*;
import java.awt.BorderLayout;
import java.io.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.awt.FlowLayout;
import java.lang.String;

/**
 * Escreva uma descrição da classe corpo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class corpo extends JFrame
{
    private JMenuBar menu = new JMenuBar();
    private JMenu menuArquivo = new JMenu("Arquivo");
    private JMenuItem itemNovo = new JMenuItem("Abrir");
    private JMenuItem itemSalvar = new JMenuItem("Salvar");
    private JMenuItem itemRecortar = new JMenuItem("Recortar");
    private JMenuItem itemCopiar = new JMenuItem("Copiar");
    private JMenuItem itemColar = new JMenuItem("Colar");
    private JTextArea caixaTexto = new JTextArea();
    private JMenu menuEditar = new JMenu("Editar");
    private JScrollPane rolagem = new JScrollPane();
    private JMenuItem itemSubstituir = new JMenuItem("Substituir");
    private JMenuItem itemLocalizar = new JMenuItem("Localizar");
    
    public corpo()
    {
        super.getContentPane().setLayout(new FlowLayout());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        caixaTexto.setColumns(40);
        caixaTexto.setRows(20);
        rolagem.setViewportView(caixaTexto);
        
        itemNovo.addActionListener(e ->
        {
            try
            {
                 ler();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        });
        
        itemSalvar.addActionListener(e ->
        {
            try
            {
                 salvar();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        });
        
        itemRecortar.addActionListener(e -> recortar());

        itemCopiar.addActionListener(e -> copiar());

        itemColar.addActionListener(e -> colar());
        
        itemSubstituir.addActionListener(e -> substituir());
        
        itemLocalizar.addActionListener(e -> localizar());
        
        menuArquivo.add(itemNovo);
        menuArquivo.add(itemSalvar);
        menuEditar.add(itemRecortar);
        menuEditar.add(itemCopiar);
        menuEditar.add(itemColar);
        menuEditar.add(itemSubstituir);
        menuEditar.add(itemLocalizar);
        menu.add(menuArquivo);
        menu.add(menuEditar);
        super.add(caixaTexto);
        super.setJMenuBar(menu);
        super.add(rolagem);
        
        rolagem.setViewportView(caixaTexto);
        
    }
    
    public void main(){
        corpo janela = new corpo();
        janela.setSize(490,400);
        janela.setVisible(true);
    }
    
        public void ler() throws IOException{
        String caminho = "";
        try {
            caminho = JOptionPane.showInputDialog("Digite o caminho do arquivo:");
        } catch (InputMismatchException ex) {
            JOptionPane.showMessageDialog(null, "Entrada inválida!");
            System.exit(0);
        }
        int i;
        try(FileInputStream  fis = new FileInputStream(caminho)){
            do{
             i = fis.read();
             if (i != -1) caixaTexto.append("" + (char) i);
            } while (i != -1);
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            System.exit(0);
        }
        
    }
    
    public void salvar() throws IOException{
        String caminho = "";
        try {
            caminho = JOptionPane.showInputDialog("Digite o caminho para salvar o arquivo:");
        } catch (InputMismatchException ex) {
            JOptionPane.showMessageDialog(null, "Entrada inválida!");
            System.exit(0);
        }
        
        try{
             FileOutputStream fos = new FileOutputStream(caminho);
             DataOutputStream dos = new DataOutputStream(fos);
             dos.writeBytes(caixaTexto.getText());
             dos.close();
         }
         catch(IOException e)
         {
             JOptionPane.showMessageDialog(null, "File error: "+e.getMessage());
             System.exit(0);
         }
        
    }
    
    public void recortar(){
        caixaTexto.cut();
    }
    
    public void copiar(){
        caixaTexto.copy();
    }
    
    public void colar(){
        caixaTexto.paste();
    }
    
    public void substituir(){
        String palavra = "",
               palavra2 ="";
        palavra = JOptionPane.showInputDialog("Digite qual palavra você quer substituir");
        palavra2 = JOptionPane.showInputDialog("Digite a palavra nova");
        String a=caixaTexto.getText();
        String newText=a.replace(palavra,palavra2);
        caixaTexto.setText(newText);
        
    }
    
    public void localizar(){
        String palavra = "";
        int posIni = 0;
        palavra = JOptionPane.showInputDialog("Qual paravra você quer encontrar?");
        int res = caixaTexto.getText().indexOf(palavra, posIni);
        if(res < 0){
            JOptionPane.showMessageDialog(null, "Texto não encontrado");
            posIni = 0;   
        }else{
            caixaTexto.requestFocus();
            caixaTexto.select(res, res + palavra.length());
            posIni = res + palavra.length();
        }
        
    }
}
