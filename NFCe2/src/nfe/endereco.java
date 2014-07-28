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
