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
public class endereco {
    
    public String xLgr;
    public String nro;
    public String xCpl;
    public String xBairro;
    public String cMun;
    public String xMun;
    public String UF;
    public String CEP;
    public String cPais;
    public String xPais;
    public String fone;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<xLgr>").append(this.xLgr).append("</xLgr>")
        .append("<nro>").append(this.nro).append("</nro>")
        .append("<xCpl>").append(this.xCpl).append("</xCpl>")
        .append("<xBairro>").append(this.xBairro).append("</xBairro>")
        .append("<cMun>").append(this.cMun).append("</cMun>")
        .append("<xMun>").append(this.xMun).append("</xMun>")
        .append("<UF>").append(this.UF).append("</UF>")
        .append("<CEP>").append(this.CEP).append("</CEP>")
        .append("<cPais>").append(this.cPais).append("</cPais>")
        .append("<xPais>").append(this.xPais).append("</xPais>")
        .append("<fone>").append(this.fone).append("</fone>");
        
        return sb.toString();
    }
    
}
