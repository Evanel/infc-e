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
package nfe;

/**
 *
 * @author Ivan Vargas
 */
public class EnvelopeNFe {
    
    public static String getHeader(int cUF, String versaoDados, int idLote, String IdNfe) 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
	.append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">")
	.append("<soap12:Header>")
        .append("<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2\">")
	.append("<cUF>").append(cUF).append("</cUF>")
	.append("<versaoDados>2.00</versaoDados>")
	.append("</nfeCabecMsg>")
	.append("</soap12:Header>")
	.append("<soap12:Body>")
	.append("<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2\">")
	.append("<enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.00\">")
	.append("<idLote>").append(idLote).append("</idLote>")
	.append("<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">")
	.append("<infNFe versao=\"").append(versaoDados).append("\"").append(" Id=\"").append(IdNfe).append("\">");
        
        return sb.toString();
    }
    
    public static String getFoot() 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("</infNFe>")
        .append("</NFe>")
        .append("</enviNFe>")
        .append("</nfeDadosMsg>")
        .append("</soap12:Body>")
        .append("</soap12:Envelope>");
        
        return sb.toString();
    }
}
