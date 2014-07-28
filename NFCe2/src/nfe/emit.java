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
public class emit {
    
    public String CNPJ;
    public String xNome;
    public String xFant;
    public endereco enderEmit;
    public String IE;
    public String IEST;
    public String IM;
    public String CNAE;
    public String CRT;
    
    public emit(){
        this.enderEmit = new endereco();
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        //sb.append("<emit>");
        sb.append("<CNPJ>").append(this.CNPJ).append("</CNPJ>");
        sb.append("<xNome>").append(this.xNome).append("</xNome>");
        sb.append("<xFant>").append(this.xFant).append("</xFant>");
        sb.append("<enderEmit>");
            sb.append(this.enderEmit.toString());
        sb.append("</enderEmit>");
        sb.append("<IE>").append(this.IE).append("</IE>");
        sb.append("<IM>").append(this.IM).append("</IM>");
        sb.append("<CNAE>").append(this.CNAE).append("</CNAE>");
        sb.append("<CRT>").append(this.CRT).append("</CRT>");
        //sb.append("</emit>");  
        
        return sb.toString();
        
    }
    
}
