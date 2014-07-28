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
package iNFe;

import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp.Transporta;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Transp.Vol;
import br.inf.portalfiscal.nfe.TVeiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Vargas
 */
public class Transportadora {
    public Transp transp;
    private Transporta transporta;
    private TVeiculo veiculo; 
    
    private InfNFe INFNFE = null;
    
    public Transportadora(InfNFe InfNFe) {
        this.INFNFE = InfNFe;
        
        this.transp = new Transp();
      
        this.transporta = new Transporta();
        this.veiculo = new TVeiculo();
                      
        this.transp.setTransporta(transporta);
        this.transp.setVeicTransp(veiculo);    
        
        this.INFNFE.setTransp(this.transp);
    }
    
    public Vol NovoVolume() {
        Vol v = new Vol();
        this.transp.getVol().add(v);
        return v;
    }
    
    public List<Vol> getVolumes() {
        return this.transp.getVol();
    }
    
    public Vol getVolume(int index) {
        return this.transp.getVol().get(index);
    }
    
    public TVeiculo NovoReboque() {
        TVeiculo r = new TVeiculo();
        this.transp.getReboque().add(r);
        return r;
    }
    
    public List<TVeiculo> getReboques() {
        return this.transp.getReboque();
    }
    
    public TVeiculo getReboque(int index) {
        return this.transp.getReboque().get(index);
    }
    
    
}
