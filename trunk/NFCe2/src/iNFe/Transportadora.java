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
