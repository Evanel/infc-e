/*******************************************************************************
* Projeto iNFC-e                                                               *
* Emissao de NFC-e em Java                                                     *
*                                                                              *
* Direitos Autorais Reservados (c) 2014 Ivan S. Vargas                         *
*                                                                              *
*  Você pode obter a última versão desse arquivo na pagina do Projeto iNFC-e   *
* localizado em https://code.google.com/p/infc-e/                              *
*                                                                              *
*  Esta biblioteca é software livre; você pode redistribuí-la e/ou modificá-la *
* sob os termos da Licença Pública Geral Menor do GNU conforme publicada pela  *
* Free Software Foundation; tanto a versão 2.1 da Licença, ou (a seu critério) *
* qualquer versão posterior.                                                   *
*                                                                              *
*  Esta biblioteca é distribuída na expectativa de que seja útil, porém, SEM   *
* NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU      *
* ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral Menor*
* do GNU para mais detalhes. (Arquivo LICENÇA.TXT ou LICENSE.TXT)              *
*                                                                              *
*  Você deve ter recebido uma cópia da Licença Pública Geral Menor do GNU junto*
* com esta biblioteca; se não, escreva para a Free Software Foundation, Inc.,  *
* no endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          *
* Você também pode obter uma copia da licença em:                              *
* http://www.opensource.org/licenses/lgpl-license.php                          *
*                                                                              *
*        Ivan S. Vargas  -  ivan@is5.com.br  -  http://www.is5.com.br          *
*                                                                              *
********************************************************************************/
package nfe.util;
import iNFe.Configuracao;
import iNFe.NFe2;
import java.io.*;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 *
 * @author Ivan Vargas
 */
public class Util {
    
    public static final int DIF_MILESEGUNDOS = 1;
    public static final int DIF_SEGUNDOS = 2;
    public static final int DIF_MINUTOS = 3;
    public static final int DIF_HORAS = 4;
    public static final int DIF_DIAS = 5;
    
    public static final int DIFERENCA_DATA = 10;
    public static final int DIFERENCA_HORA = 11;
    public static final int DIFERENCA_TIMESTAMP = 12;
    
    public static String formatFloat(Double Valor) 
    {       
            if (Valor == 0.00)
            {
                return "0.00"; //do contrario retorna ,0
            }
            else
            {
                DecimalFormat df  = new DecimalFormat("0.00");  
                return df.format(Valor).replace(',', '.');  
            }
    }
    
    public static String formatFloatQtd(Double Valor) 
    {       
            if (Valor == 0.00)
            {
                return "0.000"; //do contrario retorna ,0
            }
            else
            {
                DecimalFormat df  = new DecimalFormat("0.000");  
                return df.format(Valor).replace(',', '.');  
            }
    }
    
    public static String formatFloat(Double Valor, String Formato) 
    {       
            if (Valor == 0.00)
            {
                return Formato; //do contrario retorna ,0
            }
            else
            {
                DecimalFormat df  = new DecimalFormat(Formato);  
                return df.format(Valor).replace(',', '.');  
            }
    }
    
    static public Date DataStrToDate(String data)
    {
        Date date = null; 
        try 
        {
            if(data != null)
                date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } 
        catch (ParseException ex) 
        {
            //
        }
        return date;
    }  
    
    static public String getDataAtualStr()
    {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");    
    
        return out.format(new Date()); 
    }
    
    static public String getDataAtualFormatStr(String Formato)
    {
        /*
         * Formato pode ser qualquer coisa do tipo: 
         * dd/MM/yyyy
         * dd/MM/yyyy hh:mm:ss
         * hh:mm:ss
         * ddMMyyy hhmmss
         * Para NFe: yyyy-MM-dd
         * enfim, o que kiser :P
         */
        
        SimpleDateFormat sdf = new SimpleDateFormat(Formato);    
    
        return sdf.format(new Date()); 
    }
    
    static public String getHoraAtualStr()
    {
        SimpleDateFormat out = new SimpleDateFormat("hh:mm:ss");    
    
        return out.format(new Date()); 
    }
    
    static public String getDataHoraAtualStr()
    {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");    
    
        return out.format(new Date()); 
    }
    
    static public String dateToStr(Date data)
    {
        try 
        {
            if(data != null)
            {
                SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");  
                return out.format(data);
            }
            else
                return null;
        } 
        catch (Exception ex) 
        {
            return null;
        }

    }
    
    static public String dateHoraToStr(Date data)
    {
        try 
        {
            if(data != null)
            {
                SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");  
                return out.format(data);
            }
            else
                return null;
        } 
        catch (Exception ex) 
        {
            return null;
        }

    }
    
    public static long difData(String DataInicial, String DataFinal, String Formato, int TipoDif) {
        
        long diferenca = 0;
        
        SimpleDateFormat sdf = new SimpleDateFormat(Formato);
        
        try
        {
            Date date1 = sdf.parse(DataInicial);
            Date date2 = sdf.parse(DataFinal);
            
            diferenca = date2.getTime() - date1.getTime();
            
            switch(TipoDif)
            {
                case DIF_MILESEGUNDOS: 
                {
                    return diferenca;
                }
                   
                case DIF_SEGUNDOS: 
                {
                    diferenca = (diferenca/1000);
                    break;
                }
                    
                case DIF_MINUTOS:
                {
                    diferenca = (diferenca/1000/60);
                    break;
                }
                    
                case DIF_HORAS:
                {
                    diferenca = (diferenca/1000/60/60);
                    break;
                }
                    
                case DIF_DIAS:
                {
                    diferenca = (diferenca/1000/60/60/24);
                    break;
                }
            }
            
        }catch(Exception ex) {
            //
        }    
                    
        return diferenca;
        
    }
    
    public static long difData(String DataInicial, String DataFinal, int FormatoDif, int TipoDif) {
        String formato = null;
        
        switch (FormatoDif)
        {
            case DIFERENCA_DATA:
            {
                formato = "dd/MM/yyyy";
                break;
            }
            
            case DIFERENCA_HORA:
            {
                formato = "hh:mm:ss";
                break;
            }
                
            case DIFERENCA_TIMESTAMP:
            {
                formato = "dd/mm/yyyy hh:mm:ss";
                break;
            }
        }
                    
        return difData(DataInicial, DataFinal, formato, TipoDif);
    }
   
    public static void mensagem(String Mensagem) {
        JOptionPane.showMessageDialog(null, Mensagem, "Atenção", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /*
    public static void MostrarMensagem(String msg) {
        try
        {
            if (Configuracao.getInstance().getConfiguracoes().getMOSTRAR_MENSAGEM().equals("S"))
                JOptionPane.showMessageDialog(null, msg, "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex) {
           System.out.println(msg); 
        }
    }
    */
    
    public static boolean perguntar(String Pergunta) {
        return ( (JOptionPane.showConfirmDialog(null, Pergunta, "Confirmar", JOptionPane.YES_NO_OPTION) ) == 0);
    }
    
    public static void limparBuffer(char[] Buffer) {
        for (int i = 0; i < Buffer.length; i++) {
            Buffer[i] = ' ';
        }
    }
    
    public static void limparBuffer(char[] Buffer, char c) {
        for (int i = 0; i < Buffer.length; i++) {
            Buffer[i] = c;
        }
    }
    
    public static void limparBuffer(char[] Buffer, char c, int limite) {
        for (int i = 0; i < limite; i++) {
            Buffer[i] = c;
        }
    }
    
    public static void limparBuffer(char[] Buffer, char c, int inicio, int limite) {
        for (int i = inicio; i < limite; i++) {
            Buffer[i] = c;
        }
    }
    
    public static long Mod(long Valor) {
        if (Valor < 0)
            return (Valor * (-1));
        else
            return Valor;
    }
    
    public static String leftPad(Object Valor, int Pad, char c) 
    {
        String valor = Valor.toString().trim();
        String mask = "";
        
        for (int i = 0; i < Pad; i++)
            mask = mask + c;
        
        mask = mask.trim();

        return mask.substring(valor.length()) + valor; 
    }
    
    public static String rightPad(Object Valor, int Pad, char c) 
    {
        String valor = Valor.toString().trim();
        String mask = "";
        
        for (int i = 0; i < Pad; i++)
            mask += c;
        
        mask = mask.trim();
 
        return valor + mask.substring(valor.length()); 
    }
    
    public static String removeAcentos(String str) 
    {
        try
        {
            str = Normalizer.normalize(str, Normalizer.Form.NFD);
            str = str.replaceAll("[^\\p{ASCII}]", "");
            return str; 
        }catch(Exception ex) {
            return str;
        }
    }
    
    public static int modulo11(String chave) 
    {  
        int total = 0;  
        int peso = 2;  
              
        for (int i = 0; i < chave.length(); i++) {  
            total += (chave.charAt((chave.length()-1) - i) - '0') * peso;  
            peso ++;  
            if (peso == 10)  
                peso = 2;  
        }  
        
        int resto = total % 11;  
        
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);  
    }  
    
    public static void SaveToFile(String Conteudo, String Arquivo) {
        /* Gravo a NFe em um arquivo */
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Arquivo));
            bw.write(Conteudo);
            bw.close();
        }
        catch(Exception e) 
        {
            System.out.println("Erro ao salvar NFe: " + e.getMessage());
        }
    }
    
    public static boolean Assinar(String Arquivo) 
    {
       
        String args[] = { Arquivo, Configuracao.getInstance().getConfiguracoes().getKEYSTORE_PATH(), Configuracao.getInstance().getConfiguracoes().getKEYSTORE_SENHA(), Arquivo, "1" };
        try 
        {
            return assinar.Assinador.main(args);
        } 
        catch (Exception ex) 
        {
            System.out.println("ERRO AO ASSINAR NF-e: " + ex.getMessage());
            return false;
        }
    }
    
    public static boolean Assinar(String Arquivo, String TipoAssinatura) 
    {
       
        String args[] = { Arquivo, Configuracao.getInstance().getConfiguracoes().getKEYSTORE_PATH(), Configuracao.getInstance().getConfiguracoes().getKEYSTORE_SENHA(), Arquivo, TipoAssinatura };
        try 
        {
            return assinar.Assinador.main(args);
        } 
        catch (Exception ex) 
        {
            System.out.println("ERRO AO ASSINAR NF-e: " + ex.getMessage());
            return false;
        }
    }
    
    public static boolean AssinarCancelamento(String Arquivo) {
        return Assinar(Arquivo, "4");
    }
    
    public static boolean AssinarInutilizacao(String Arquivo) {
        return Assinar(Arquivo, "3");
    }
    
    public static String getNomeArquivoNotaFiscal(NFe2 nfe) {
        try
        {
            return Configuracao.getInstance().getConfiguracoes().getPATH_NFE() + nfe.infNFe.getId().substring(3) + "-nfe.xml";
        }
        catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    } 
    
    public static String loadFromFile(String Arquivo) {
        /* retorna conteudo nota fiscal */
        try
        {
            StringBuilder sb = new StringBuilder();
            String linha = "";
           
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(Arquivo), "ISO-8859-1"));  
            while ((linha = in.readLine()) != null) {  
                sb.append(linha);  
            }  
            in.close();
            
            
            return removeAcentos(sb.toString());
            
        }catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }
    
    public static String getValue(Element e, String s) 
    {
        try
        { 
            return e.getChild(s, e.getNamespace()).getValue();
        }
        catch(Exception ex) 
        {
            return "";
        }
    }
    
    public static String getValue(Element e, String s, Namespace n) 
    {
        try
        { 
            return e.getChild(s, n).getValue();
        }
        catch(Exception ex) 
        {
            return "";
        }
    }
    
    
    
    public static String getDhEvento() 
    {
        /*
         * Data e hora do evento no formato AAAA-MM-DDThh:mm:ssTZD 
         * (UTC - Universal Coordinated Time, onde TZD pode ser -02:00 
         * (Fernando de Noronha), -03:00 (Brasília) ou -04:00 (Manaus), no 
         * horário de verão serão -01:00, -02:00 e -03:00. Ex.: 2010-08-19T13:00:15-03:00. 
         */
        String dh = getDataAtualFormatStr("yyyy-MM-dd") + "T" + getDataAtualFormatStr("HH:mm:ss") + "-02:00";
        
        return dh;
        
        
    }
    
    public static String getDhConFUSO(String FusoHorario) //gostou do nome? ehehe
    {
        /*
         * Data e hora do evento no formato AAAA-MM-DDThh:mm:ssTZD 
         * (UTC - Universal Coordinated Time, onde TZD pode ser -02:00 
         * (Fernando de Noronha), -03:00 (Brasília) ou -04:00 (Manaus), no 
         * horário de verão serão -01:00, -02:00 e -03:00. Ex.: 2010-08-19T13:00:15-03:00. 
         */
        String dh = getDataAtualFormatStr("yyyy-MM-dd") + "T" + getDataAtualFormatStr("HH:mm:ss") + FusoHorario.trim();
        
        return dh;
        
        
    }
    
    public static boolean serializar(Object obj, String Arquivo) {
        boolean retorno = false;
        
        FileOutputStream arq = null;
        ObjectOutputStream out = null;
        try
        {
            arq = new FileOutputStream(Arquivo);
            out = new ObjectOutputStream(arq);
            
            out.writeObject(obj);
            
            retorno = true;
        }
        catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        finally
        {
            try {
                arq.close();
                out.close();
            }catch(Exception ex) {
                //
            }
        }
        
        return retorno;
    }
    
    public static boolean serializar(Object obj, File Arquivo) {
        boolean retorno = false;
        
        FileOutputStream arq = null;
        ObjectOutputStream out = null;
        try
        {
            arq = new FileOutputStream(Arquivo);
            out = new ObjectOutputStream(arq);
            
            out.writeObject(obj);
            
            retorno = true;
        }
        catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        finally
        {
            try {
                arq.close();
                out.close();
            }catch(Exception ex) {
                //
            }
        }
        
        return retorno;
    }
    
    public static Object desSerializar(File Arquivo) 
    {
        Object obj = null;
        FileInputStream arq = null;
        ObjectInputStream in = null;
        try
        {
            arq = new FileInputStream(Arquivo);
            in = new ObjectInputStream(arq);
            
            obj = in.readObject();            
        }
        catch(Exception ex ) {
            System.out.println("Erro: " + ex.getMessage());
        }
        finally{
            try {
                arq.close();
                in.close();
            }catch(Exception ex) {
                
            }
        }
        return obj;  
    }
    
    public static String selecionarArquivo()
    {  
        String fileName = "";
        try
        {            
            JFileChooser fc = new JFileChooser();  
            fc.setDialogTitle("Escolha o(s) arquivo(s)...");  
            fc.setDialogType(JFileChooser.OPEN_DIALOG);  
            fc.setApproveButtonText("OK");  
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
            fc.setMultiSelectionEnabled(false);  
            int resultado = fc.showOpenDialog(fc);  
            if (resultado == JFileChooser.APPROVE_OPTION)
                fileName =  fc.getSelectedFile().getAbsolutePath(); // fc.getSelectedFile().getName(); 
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar arquivo: " + ex.getMessage());
        }
        return fileName;
    }  
    
    public static String getCurrentDir() {
        try
        {
            File f = new File(".");
            
            return f.getCanonicalPath(); 
            
        }catch(Exception ex) {
            System.out.println("Erro getCurrentDir(): " + ex.getMessage());
            return null;
        }
    }
}
