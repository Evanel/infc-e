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

/**
 *
 * @author Ivan Vargas
 */
public class GetFieldsXML {
    
    public static String getField(String Pai, String Field, String XML) 
    {
        int posInicial = 0;
        int posFinal = 0;
        String secao = "";
        String valor = "";
        
        Pai = Pai.toLowerCase();
        Field = Field.toLowerCase();
        XML = XML.toLowerCase();
       
        try
        {
            String tagPaiInicial = "<"  + Pai.trim() + ">";        
            String tagPaiFinal   = "</" + Pai.trim() + ">";

            posInicial = XML.indexOf(tagPaiInicial) + tagPaiInicial.length();
            posFinal   = XML.indexOf(tagPaiFinal);

            secao = XML.substring(posInicial, posFinal);

            String tagFieldInicial = "<"  + Field.trim() + ">";        
            String tagFieldFinal   = "</" + Field.trim() + ">";

            posInicial = XML.indexOf(tagFieldInicial) + tagFieldInicial.length();
            posFinal   = XML.indexOf(tagFieldFinal);

            valor = XML.substring(posInicial, posFinal);
        }
        catch(Exception e) {
            Log.getInstance().addErro("Erro em GetFieldsXML.getField(): " + e.getMessage());
        }
     
        return valor;
    } 
    
    public static String getField(String TagInicio, String TagFim, String Field, String XML) 
    {
        int posInicial = 0;
        int posFinal = 0;
        String secao = "";
        String valor = "";
        
        TagInicio = TagInicio.toLowerCase();
        TagFim = TagFim.toLowerCase();
        Field = Field.toLowerCase();
        XML = XML.toLowerCase();
       
        try
        {
            String tagPaiInicial = TagInicio;
            String tagPaiFinal   = TagFim;

            posInicial = XML.indexOf(tagPaiInicial) + tagPaiInicial.length();
            posFinal   = XML.indexOf(tagPaiFinal);

            secao = XML.substring(posInicial, posFinal);

            String tagFieldInicial = "<"  + Field.trim() + ">";        
            String tagFieldFinal   = "</" + Field.trim() + ">";

            posInicial = XML.indexOf(tagFieldInicial) + tagFieldInicial.length();
            posFinal   = XML.indexOf(tagFieldFinal);

            valor = XML.substring(posInicial, posFinal);
        }
        catch(Exception e) 
        {
            Log.getInstance().addErro("Erro em GetFieldsXML.GetField(): " + e.getMessage());
        }
     
        return valor;
    } 
    
    public static String getMensagem(String XML) {
        /*
         * Este funcao retorna a mensagem q esta no envelope SOAP
         * ou seja, entre <soap:body>...</soap:body>
         */
        int posInicial = 0;
        int posFinal = 0;
        
        String tagFieldInicial = "<soap:Body>";
        String tagFieldFinal = "</soap:Body>";
        
        try
        {
            posInicial = XML.indexOf(tagFieldInicial) + tagFieldInicial.length();
            posFinal   = XML.indexOf(tagFieldFinal);

            return XML.substring(posInicial, posFinal);
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em GetFieldsXML.getMensagem(): " + ex.getMessage());
            return null;
        }
    }
    
    public static String getMensagem12(String XML) {
        /*
         * Este funcao retorna a mensagem q esta no envelope SOAP
         * ou seja, entre <soap12:body>...</soap12:body>
         */
        int posInicial = 0;
        int posFinal = 0;
        
        String tagFieldInicial = "<soap12:Body>";
        String tagFieldFinal = "</soap12:Body>";
        
        try
        {
            posInicial = XML.indexOf(tagFieldInicial) + tagFieldInicial.length();
            posFinal   = XML.indexOf(tagFieldFinal);

            return XML.substring(posInicial, posFinal);
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em GetFieldsXML.getMensagem12(): " + ex.getMessage());
            return null;
        }
    }
    
}
