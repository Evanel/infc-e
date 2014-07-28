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
package idanfenfce;

import java.text.DecimalFormat;
import java.text.Normalizer;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 *
 * @author Ivan Vargas
 */
public class Util {
    
    public static String getData(String dhEmi) {
        //2014-01-09T09:14:45-02:00
        //0123456789
        try
        {
            String data = dhEmi.substring(0, 10);

            String ano = data.substring(0,4);
            String mes = data.substring(5,7);
            String dia = data.substring(8);

            return dia + "/" + mes + "/" + ano;        
        }catch(Exception ex) {
            return "";
        }
    }
    
    public static String removeAcentos(String str) 
    {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str; 
    }
    
    public static String getHora(String dhEmi) {
        
        //2014-01-09T09:14:45-02:00
        //012345678910
        try
        {
            return dhEmi.substring(11, 19);
        }catch(Exception ex) {
            return "";
        }
    }
    
    public static String getDataHora(String dhEmi) {
        return getData(dhEmi) + " " + getHora(dhEmi);
    }
    
    public static String formatChave(String chave) {
        /*
         * A Chave NFC-e deve ser impressa no danfe em 11 blocos
         * de 4 numeros cada
         */
        
        chave = chave.replace("NFe", " ").trim();
        
        try
        {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < chave.length(); i++) 
            {
                if (i%4 == 0)
                    sb.append(' ');
                sb.append(chave.charAt(i));
            }
            
            return sb.toString().trim();
        }
        catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return chave; //se deu erro retorno a chave normal :P
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
    
    public static String formatFloat(Double Valor, String Maskara)  
    {
        //usar mascaras do tipo 0.00, 0.000
        if (Valor == 0.00)
        {
            return Maskara; //do contrario retorna ,0
        }
        else
        {
            DecimalFormat df  = new DecimalFormat(Maskara);  
            return df.format(Valor).replace(',', '.');  
        }
    }
    
    public static String formatFloatValorNFCe(Double Valor) {
        return formatFloat(Valor, "0.00");
    }
    
    public static String formatFloatQtdNFCe(Double Valor) {
        return formatFloat(Valor, "0.000");
    }
    
}
