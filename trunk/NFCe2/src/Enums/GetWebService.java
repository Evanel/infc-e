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
package Enums;

import iNFe.Configuracao;
import java.io.File;
import nfe.util.Log;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Ivan Vargas
 */
public class GetWebService {
    
    
    public String getURL(Estados Estado, TiposAmbiente Ambiente, Versoes Versao, Servicos Servico) {
        
        try
        {
            SAXBuilder b = new SAXBuilder();
            
            //Document doc = b.build( getClass().getResourceAsStream("/WebServices.xml") );
            Document doc = b.build( new File("WebServices.xml") ); //diretorio current
            
            Element root = doc.getRootElement(); 
            
            Element estado = root.getChild( Estado.name() );
            
            Element versao = estado.getChild( Versao.name() );
            
            Element ambiente = versao.getChild( Ambiente.name() );
            
            Element url = ambiente.getChild( Servico.name() );
            
            System.out.println("URL: " + url.getValue());
            
            return url.getValue();
                        

        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em GetWebService.getURL(): " + ex.getMessage());            
        }
        return null;       
        
    }
    
    public static Estados getEstado(String UF) 
    {
        try
        {
            for(Estados e : Estados.values()) {
                if ( e.name().equals(UF.toUpperCase().trim()) ) {
                    return e;
                }
            }        
        }catch(Exception e) {
            Log.getInstance().addErro("Erro em getEstado(): " + e.getMessage());
        }
        return null;        
    }
    
    public static Versoes getVersao(String versao) 
    {        
        try
        {
            for(Versoes v : Versoes.values()) {
                if ( v.getVersao().equals(versao)) {
                    return v;
                }
            }
        }catch(Exception e) {
            Log.getInstance().addErro("Erro em getVersao(): " + e.getMessage());
        }
        return null;
    }
    
    public static TiposAmbiente getAmbiente(String Ambiente) 
    {
        try
        {
            return (Ambiente.equals("1")) ? TiposAmbiente.PRODUCAO : TiposAmbiente.HOMOLOGACAO;
        }catch(Exception e) {
            Log.getInstance().addErro("Erro em getAmbiente(): " + e.getMessage());
        }
        return null;
    }
    
    
    
    
    
}
