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
public class icmsTot {
    
    public Double vBC;
    public Double vICMS;
    public Double vBCST;
    public Double vST;
    public Double vProd;
    public Double vFrete;
    public Double vSeg;
    public Double vDesc;
    public Double vII;
    public Double vIPI;
    public Double vPIS;
    public Double vCOFINS;
    public Double vOutro;
    public Double vNF;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<vBC>").append(Util.formatFloat(this.vBC)).append("</vBC>");
        sb.append("<vICMS>").append(Util.formatFloat(this.vICMS)).append("</vICMS>");
        sb.append("<vBCST>").append(Util.formatFloat(this.vBCST)).append("</vBCST>");
        sb.append("<vST>").append(Util.formatFloat(this.vST)).append("</vST>");
        sb.append("<vProd>").append(Util.formatFloat(this.vProd)).append("</vProd>");
        sb.append("<vFrete>").append(Util.formatFloat(this.vFrete)).append("</vFrete>");
        sb.append("<vSeg>").append(Util.formatFloat(this.vSeg)).append("</vSeg>");
        sb.append("<vDesc>").append(Util.formatFloat(this.vDesc)).append("</vDesc>");
        sb.append("<vII>").append(Util.formatFloat(this.vII)).append("</vII>");
        sb.append("<vIPI>").append(Util.formatFloat(this.vIPI)).append("</vIPI>");
        sb.append("<vPIS>").append(Util.formatFloat(this.vPIS)).append("</vPIS>");
        sb.append("<vCOFINS>").append(Util.formatFloat(this.vCOFINS)).append("</vCOFINS>");
        sb.append("<vOutro>").append(Util.formatFloat(this.vOutro)).append("</vOutro>");
        sb.append("<vNF>").append(Util.formatFloat(this.vNF)).append("</vNF>");

        return sb.toString();        
    }

    
    
    
}
