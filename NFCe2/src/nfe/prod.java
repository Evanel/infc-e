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
public class prod {
    
    public String cProd;
    public String cEAN;
    public String xProd;
    public String NCM;
    public int genero; //int
    public int CST; //ind
    public int CFOP; //int
    public String uCom;
    public Double qCom; //double
    public Double vUnCom; //double
    public Double vProd; //double
    public String uTrib;
    public Double qTrib; //double
    public Double vUnTrib; //double
    public int indTot; //int
    public Double vFrete; //double
    public Double vSeguro; //double
    public Double vDesc;  //double
    public String nDIAdi;    
    public imposto imposto;
    public String infoAdProd;
    
    public prod(){
        this.imposto = new imposto();
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<cProd>").append(this.cProd).append("</cProd>");
        sb.append("<cEAN/>");
        sb.append("<xProd>").append(this.xProd).append("</xProd>");
        sb.append("<NCM>").append(this.NCM).append("</NCM>");
        sb.append("<CFOP>").append(this.CFOP).append("</CFOP>");
        sb.append("<uCom>").append(this.uCom).append("</uCom>");
        sb.append("<qCom>").append(Util.formatFloatQtd(this.qCom)).append("</qCom>");
        sb.append("<vUnCom>").append(Util.formatFloat(this.vUnCom)).append("</vUnCom>");
        sb.append("<vProd>").append(Util.formatFloat(this.vProd)).append("</vProd>");
        sb.append("<cEANTrib/>");
        sb.append("<uTrib>").append(this.uTrib).append("</uTrib>");
        sb.append("<qTrib>").append(Util.formatFloatQtd(this.qTrib)).append("</qTrib>");
        sb.append("<vUnTrib>").append(Util.formatFloat(this.vUnTrib)).append("</vUnTrib>");
        sb.append("<indTot>").append(this.indTot).append("</indTot>");
        
        //sb.append("<vFrete>").append(Util.formatFloat(this.vFrete)).append("</vFrete>");
       // sb.append("<vSeguro>").append(Util.formatFloat(this.vSeguro)).append("</vSeguro>");
        //sb.append("<vDesc>").append(Util.formatFloat(this.vDesc)).append("</vDesc>");
       // sb.append("<nDIAdi>").append(this.nDIAdi).append("</nDIAdi>");
        //sb.append("<infoAdProd>").append(this.infoAdProd).append("</infoAdProd>");

        
        
        return sb.toString();
    }
    
    
    
    
    
    
    
}
