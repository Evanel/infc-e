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

import java.util.ArrayList;

/**
 *
 * @author Ivan Vargas
 */
public class det {
    
    public int nItem;
    public prod prod; 
        
    public det(){
        this.prod = new prod();
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<det nItem=\"").append(this.nItem).append("\">")
                .append("<prod>")
                        .append(this.prod.toString())
                .append("</prod>")
                .append("<imposto>")
                        .append("<ISSQN>")
                                .append(this.prod.imposto.ISSQN.toString())
                        .append("</ISSQN>")
                        .append("<PIS>")
                                .append(this.prod.imposto.PIS.toString())
                        .append("</PIS>")
                        .append("<COFINS>")
                                .append(this.prod.imposto.COFINS.toString())
                        .append("</COFINS>")
                .append("</imposto>")
                .append("<infAdProd>").append(this.prod.infoAdProd).append("</infAdProd>")
        .append("</det>");
        
        return sb.toString();
    }
    
    
    
}
