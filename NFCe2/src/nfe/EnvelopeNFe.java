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
