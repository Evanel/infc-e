/*******************************************************************************
* Projeto iNFC-e                                                               *
* Emissao de NFC-e em Java                                                     *
*                                                                              *
* Direitos Autorais Reservados (c) 2014 Ivan S. Vargas                         *
*                                                                              *
*  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto iNFC-e   *
* localizado em https://code.google.com/p/infc-e/                              *
*                                                                              *
*  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la *
* sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  *
* Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) *
* qualquer vers�o posterior.                                                   *
*                                                                              *
*  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   *
* NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      *
* ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor*
* do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              *
*                                                                              *
*  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto*
* com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  *
* no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          *
* Voc� tamb�m pode obter uma copia da licen�a em:                              *
* http://www.opensource.org/licenses/lgpl-license.php                          *
*                                                                              *
*        Ivan S. Vargas  -  ivan@is5.com.br  -  http://www.is5.com.br          *
*                                                                              *
********************************************************************************/
package nfe.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Ivan Vargas
 */
public class Log {
    
    private static final String ARQUIVO_LOG = "log.txt";
    
    private static Log INSTANCE = null;
    
    private static Logger log = null;
    
    private JTextArea taSaida = null;
    
    private boolean mostrarMensagem = false;
    
    public void setMostrarMensagens(boolean b) {
        mostrarMensagem = b;
    }
    
    public void setSaida(JTextArea saida) {
        this.taSaida = saida;
    }
   
    private void publicarMensagem(String msg) {
        if (this.taSaida != null)
        {
            this.taSaida.setText(this.taSaida.getText() + "\n" + msg);
            this.taSaida.setCaretPosition(this.taSaida.getText().length()); //forca o scroll
            //this.taSaida.repaint();
        }
        
        if (mostrarMensagem) 
        {
            try
            {
                JOptionPane.showMessageDialog(null, msg);
            }catch(Exception ex) {
                System.out.println(msg);
            }
        }
    }
    
    public synchronized static Log getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Log();
        }
        return INSTANCE;
    }
    
    public synchronized void ClearLog() 
    {
        //funcao para apagar os arquivos de log, qdo necessario
        try
        {
            File f1 = new File(ARQUIVO_LOG);
            f1.delete();
            
            if (taSaida != null) 
                taSaida.setText(null);
            
        }catch(Exception ex) {
            System.out.println("Erro ao limpar log: " + ex.getMessage());
        }
    }
    
    private Log() 
    {
        try
        {
            //seto os arquivos onde serao gravados os log
            FileHandler fhi = new FileHandler(ARQUIVO_LOG);
            
            //configuro um formato simples, pra nao ser gravado em xml
            SimpleFormatter sf = new SimpleFormatter();
            
            //especifico os formato nos handler de arquivo
            fhi.setFormatter(sf);
            
            //crio o logger e seto as configuracoes
            log = Logger.getLogger("informacoes");
            //log.setLevel(Level.INFO);
            log.addHandler(fhi);
            
        }catch(Exception ex) {
            System.out.println("Erro ao gerar logs: " + ex.getMessage());
        }
    }
    
    public synchronized void addMensagem(String msg) {
        try
        {
            Log.log.log(Level.INFO, msg);
            publicarMensagem(msg);
        }catch(Exception ex) {
            //
        }
    }
    
    public synchronized void addErro(String erro) {
        try
        {
            Log.log.log(Level.SEVERE, erro);
            publicarMensagem(erro);
        }catch(Exception ex) {
            //
        }
    }
      
    public synchronized String getMensagens() {
         try
        {
            StringBuilder sb = new StringBuilder();
            String linha = "";
           
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVO_LOG), "ISO-8859-1"));  
            
            while ((linha = in.readLine()) != null) 
            {
                if ( (linha.toLowerCase().startsWith("info")) || (linha.toLowerCase().startsWith("grave")) )
                  sb.append(linha).append("\n");  
            }  
            in.close();
            
            
            return sb.toString();
            
        }catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }        
        
    }
    
}
