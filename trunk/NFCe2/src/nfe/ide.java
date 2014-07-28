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
