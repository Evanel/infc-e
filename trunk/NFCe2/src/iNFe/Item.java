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
package iNFe;

import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSST;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.ISSQN;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Det.Prod;

/**
 *
 * @author Ivan Vargas
 */
public class Item {
    public Det det;
    public Imposto imposto;
    
    private Prod prod;
    
    private ICMS icms;
    private ICMSST icmsst;
    private ICMSSN500 icmssn500;
    private ICMS.ICMS00 icms00; 
    
    private PIS pis;
    private PISNT pisnt;
    private PISOutr pisoutr;
    private PISQtde pisqtde;
    private PISAliq pisaliq;
    
    private COFINS cofins;
    private COFINSNT cofinsnt;
    private COFINSOutr cofinsoutr;
    private COFINSQtde cofinsqtde;
    private COFINSAliq cofinsaliq;
    
    private ISSQN issqn;
    
    private InfNFe INFNFE = null;
    
    public Item(InfNFe InfNFe) 
    {
        this.INFNFE = InfNFe;
 
        this.det = new Det();
        this.prod = new Prod();
        this.imposto = new Imposto();
        
        //Instancia impostos raiz
        this.icms = new ICMS();
        this.icmsst = new ICMSST();
        this.icmssn500 = new ICMSSN500();
        this.icms00 = new ICMS.ICMS00();
                
        //Instancia detalhe PIS
        this.pis = new PIS();
        this.pisnt = new PISNT();        
        this.pisaliq = new PISAliq();
        this.pisoutr = new PISOutr();
        this.pisqtde = new PISQtde();
        
        //Instancia detalhe COFINS
        this.cofins = new COFINS();
        this.cofinsnt = new COFINSNT();
        this.cofinsaliq = new COFINSAliq();
        this.cofinsoutr = new COFINSOutr();
        this.cofinsqtde = new COFINSQtde();
     
        //Instancia ISSQN
        this.issqn = new ISSQN();
        
        //Configura ICMS
        this.icms.setICMSSN500(icmssn500);
        this.icms.setICMSST(icmsst);
        this.icms.setICMS00(icms00);
        
        //Configura PIS
        this.pis.setPISNT(pisnt);
        this.pis.setPISAliq(pisaliq);
        this.pis.setPISOutr(pisoutr);
        this.pis.setPISQtde(pisqtde);
        
        //Configura COFINS
        this.cofins.setCOFINSNT(cofinsnt);
        this.cofins.setCOFINSAliq(cofinsaliq);
        this.cofins.setCOFINSOutr(cofinsoutr);
        this.cofins.setCOFINSQtde(cofinsqtde);  
              
        //Vincula impostos 
        this.imposto.setICMS(icms);
        this.imposto.getICMS().setICMS00(icms00);
        this.imposto.setPIS(pis);
        this.imposto.setCOFINS(cofins);
        this.imposto.setISSQN(issqn);
        
        det.setProd(prod);
        det.setImposto(imposto);

        INFNFE.getDet().add(det);
    }
}
