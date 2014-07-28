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
