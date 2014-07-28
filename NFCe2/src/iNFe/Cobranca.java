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

import br.inf.portalfiscal.nfe.TNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr.Dup;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Cobr.Fat;
import java.util.List;

/**
 *
 * @author Ivan Vargas
 */
public class Cobranca {
    public Cobr cobr;
    private Fat fat;
    
    private InfNFe INFNFE = null;
    
    public Cobranca(InfNFe InfNFe) {
        this.INFNFE = InfNFe;
                
        this.cobr = new Cobr();
        this.fat = new Fat();
        
        this.cobr.setFat(fat);
        
        this.INFNFE.setCobr(this.cobr);
    }
    
    public Dup NovaDuplicata() {
        Dup d = new Dup();
        this.cobr.getDup().add(d);
        return d;
    }
    
    public List<Dup> getDuplicatas() {
        return this.cobr.getDup();
    }
    
    public Dup getDuplicata(int index) {
        return this.cobr.getDup().get(index);
    }
    
}
