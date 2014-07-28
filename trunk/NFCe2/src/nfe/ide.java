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
public class ide {
    
    public int id;
    public int cUF;
    public String cNF;
    public String natOp;
    public int indPag;
    public String mod;
    public int serie;
    public int nNF;
    public String dEmi;
    public String dSaient;
    public String hSaiEnt;
    public int tpNF;
    public int cMunFG;
    public String refNF;
    public int tpImp;
    public int tpEmis;
    public int cDV;
    public int tpAmb;
    public int finNFe;
    public int procEmi;
    public String verProc;
    
    public ide(){
        this.dEmi = null;
        this.dSaient = null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        //sb.append("<ide>");
        sb.append("<cUF>").append(this.cUF).append("</cUF>");
        sb.append("<cNF>").append(Util.leftPad(this.cNF,8,'0')).append("</cNF>");
        sb.append("<natOp>").append(this.natOp).append("</natOp>");
        sb.append("<indPag>").append(this.indPag).append("</indPag>");
        sb.append("<mod>").append(this.mod).append("</mod>");
        sb.append("<serie>").append(this.serie).append("</serie>");
        sb.append("<nNF>").append(this.nNF).append("</nNF>");
        sb.append("<dEmi>").append(this.dEmi).append("</dEmi>");
        sb.append("<dSaiEnt>").append(this.dSaient).append("</dSaiEnt>");
        sb.append("<hSaiEnt>").append(this.hSaiEnt).append("</hSaiEnt>");
        sb.append("<tpNF>").append(this.tpNF).append("</tpNF>");
        sb.append("<cMunFG>").append(this.cMunFG).append("</cMunFG>");
        sb.append("<tpImp>").append(this.tpImp).append("</tpImp>");
        sb.append("<tpEmis>").append(this.tpEmis).append("</tpEmis>");
        sb.append("<cDV>").append(this.cDV).append("</cDV>");
        sb.append("<tpAmb>").append(this.tpAmb).append("</tpAmb>");
        sb.append("<finNFe>").append(this.finNFe).append("</finNFe>");
        sb.append("<procEmi>").append(this.procEmi).append("</procEmi>");
        sb.append("<verProc>").append(this.verProc).append("</verProc>");
        //sb.append("</ide>");
             
        return sb.toString();
    }
    
}
