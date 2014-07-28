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
