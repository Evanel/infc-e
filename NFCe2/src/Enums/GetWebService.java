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
