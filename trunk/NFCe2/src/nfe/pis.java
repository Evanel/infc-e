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

import nfe.util.Util;

/**
 *
 * @author Ivan Vargas
 */
public class pis {
    //PIS
    public String CST;
    public Double vBC;
    public Double pPIS;
    public Double vPIS;
    public Double qBCProd;
    public Double vAliqProd;  
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<PISAliq>")
        .append("<CST>").append(this.CST).append("</CST>")
        .append("<vBC>").append(Util.formatFloat(this.vBC)).append("</vBC>")
        .append("<pPIS>").append(Util.formatFloat(this.pPIS)).append("</pPIS>")
        .append("<vPIS>").append(Util.formatFloat(this.vPIS)).append("</vPIS>")
        //.append("<qBCProd>").append(Util.formatFloat(this.qBCProd)).append("</qBCProd>")
        //.append("<vAliqProd>").append(Util.formatFloat(this.vAliqProd)).append("</vAliqProd>")
        .append("</PISAliq>");

        return sb.toString();
    }
    
}
