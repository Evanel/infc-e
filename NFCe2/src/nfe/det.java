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
