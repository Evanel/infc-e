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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import nfe.util.Util;

/**
 *
 * @author Ivan Vargas
 */
public class NFe {
    
    public int IdLote;
    //public String id;
    public String versao;
    public ide ide;
    public emit emit;
    public dest dest;
    public ArrayList<det> det;
    public total total;
    public transp transp;
    public cobr cobr;
    public infoAdic infAdic;
    
       
    public NFe() {
        this.ide = new ide();
        this.emit = new emit();
        this.dest = new dest();
        this.det = new ArrayList();
        this.total = new total();
        this.transp = new transp();
        this.cobr = new cobr();
        this.infAdic = new infoAdic();
    }
    
    private String getAAMM() {
        /*
         * 0123456789
         * 2013-11-27
         */
        String aamm = "";
        try
        {
            String data = this.ide.dEmi;

            if ( (!data.equals("")) && (data != null) )
            aamm = data.substring(2, 4) + data.substring(5,7);
        }
        catch(Exception e) {
            System.out.println("Erro em getAAMM(): " + e.getMessage());
        }
        
        return aamm;                
    }
    
    private int getDV(String chave) 
    {
        int dv = 0;
        int digito = 0;
        //int index = 7; //indice da mult
        int sum = 0;
        
        //int mult[] = { 9, 8, 7, 6, 5, 4, 3, 2 };
        int peso = 2;
        
        try
        {
            for (int i = chave.length() -1; i >= 0; i--) 
            {
                digito = Integer.parseInt(String.valueOf(chave.charAt(i)));

                //sum += (digito * mult[index]);
                //index--;
                //if (index < 0) index = 7
                
                sum += (digito * peso);                
                
                peso++;                
                
                if (peso == 10) peso = 2;
            }

            int r = (sum % 11); 

            if ( (r == 0) || (r == 1) )
                dv = 0;
            else
            {
                dv = (11 - r);
            }
        }
        catch(Exception e) {
            System.out.println("Erro em getDV(): " + e.getMessage());
        }
        
        return dv;
    }
    
    private String getId()
    {
        /* Compreendendo a Chave de Acesso
         * -------------------------------
                           
                                +->CNPJ           +-> nNF - aleatorio 
                                |                 |            +-> cNF - codigo da nota no sistema
                 +->AAMM        |  +->Modelo      |            |
            UF   |              |  |    +->Serie  |  +->tpEmi  |  +- Digito Verificador
            |    |              |  |    |         |  |         |  |
           43 1311 01611275000124 55  001 000003215  1  00003215  0
           43 1311 01611275000124 55  001 000000888  1  00000888  2
           43 1311 01611275000124 55  001 000003215  2  00000010  1
         
         */
        
        String chave = "";
        
        try
        {        
            chave = String.valueOf(this.ide.cUF);
            chave += getAAMM();
            chave += this.emit.CNPJ;
            chave += this.ide.mod;
            chave += Util.leftPad(this.ide.serie, 3, '0');
            chave += Util.leftPad(this.ide.nNF, 9, '0');
            chave += this.ide.tpEmis;
            chave += Util.leftPad(this.ide.cNF, 8, '0');
            chave += getDV(chave);
            
            chave = "NFe" + chave;            
        }
        catch(Exception e) {
            System.out.println("Erro em getId(): " + e.getMessage());
        }
        
        return chave;
    }
    
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
        sb.append("<infNFe versao=\"").append(this.versao).append("\" Id=\"").append(this.getId()).append("\">");
        
        sb.append("<ide>");
            sb.append(this.ide.toString());
        sb.append("</ide>");
        sb.append("<emit>");
            sb.append(this.emit.toString());
        sb.append("</emit>");
        sb.append("<dest>");
            sb.append(this.dest.toString());
        sb.append("</dest>");        
        //<det...>
        for(det d : det) 
        {
            sb.append(d.toString());
        }
        //</det>        
        sb.append("<total>");
            sb.append("<ICMSTot>");
                sb.append(this.total.ICMSTot.toString());
            sb.append("</ICMSTot>");
            sb.append("<ISSQNtot>");
                sb.append(this.total.ISSQNTotal.toString());
            sb.append("</ISSQNtot>");
        sb.append("</total>");
        sb.append("<transp>");
            sb.append(this.transp.toString());
        sb.append("</transp>");
        sb.append("<cobr>");
            sb.append(this.cobr.toString());
        sb.append("</cobr>");
        sb.append("<infAdic>");
            sb.append(this.infAdic.toString());
        sb.append("</infAdic>");
        
        sb.append("</infNFe>");
        sb.append("</NFe>");
        
        return sb.toString();
    }
    
    public void SaveToFile(String Arquivo) {
        /* Gravo a NFe em um arquivo */
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Arquivo));
            bw.write(this.toString());
            bw.close();
        }
        catch(Exception e) 
        {
            System.out.println("Erro ao salvar NFe: " + e.getMessage());
        }
    }
    
}
