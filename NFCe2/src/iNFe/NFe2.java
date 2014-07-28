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

import br.inf.portalfiscal.nfe.*;
import br.inf.portalfiscal.nfe.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Dest;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Emit;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Ide;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.InfAdic;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Total;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Total.ICMSTot;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Total.ISSQNtot;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import nfe.util.Log;
import nfe.util.Util;

/**
 *
 * @author Ivan Vargas
 */

/*
 * 
 * 
Atualmente os endereços dos Web Services de Lote e de Consulta do Retorno do Lote para a NFC-e no RS são os seguintes:

https://nfe.sefaz.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx 

* Consulta Retorno Lote

https://nfe.sefaz.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx

* 
No ambiente de homologação, os Web Services são os seguintes:

https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx 

Consulta Retorno Lote

https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx

Para as demais operações, a NFC-e utiliza os mesmos Web Services da NF-e.
 * 
 */
public class NFe2 {
    
    private TNFe nfe;
    public TEnviNFe enviNFe;
    public InfNFe infNFe;
    
    public Ide ide;
    public Emit emit;
    public Dest dest;
    //public Det det;
    public Total total;
    public Transportadora transportadora;
    public InfAdic infAdic;
    //public Cobranca cobranca; //NAO TEM NO NFC-e
    public ArrayList<Item> Itens;
    
    private TEnderEmi enderEmit;
    private TEndereco enderDest;
    private ICMSTot icmstot;
    private ISSQNtot issqntot;
    
    private String Lote = null;
    private String Versao = null;
    
    public String xmlValidado = null;
    
    private List<String> Erros;
    
    private String pathXml = null;

    public String getPathXml() {
        return pathXml;
    }

    public void setPathXml(String pathXml) {
        this.pathXml = pathXml;
    }
        
    
    public List<String> getErros() {
        if (this.Erros == null) {
            this.Erros = new ArrayList();
        }
        return this.Erros;
    }
    
    public boolean OcorreuErro() {
        return getErros().size() > 0;
    }

    
    public NFe2() {
        try
        {
            this.nfe = new TNFe(); 
            this.infNFe = new InfNFe(); 
            this.enviNFe = new TEnviNFe(); 
            this.ide = new Ide(); 
            this.emit = new Emit(); 
            this.dest = new Dest();
            //det = new Det();
            this.total = new Total();
            this.transportadora = new Transportadora(infNFe);
            this.infAdic = new InfAdic();
            //this.cobranca = new Cobranca(infNFe);
            this.Itens = new ArrayList();
            this.enderEmit = new TEnderEmi();
            this.enderDest = new TEndereco();
            this.icmstot = new ICMSTot();
            this.issqntot = new ISSQNtot();

            /* Vincula classes */
            emit.setEnderEmit(enderEmit);
            dest.setEnderDest(enderDest);
            total.setICMSTot(icmstot);
            total.setISSQNtot(issqntot);

            /* Monta a estrutura principal */
            this.infNFe.setIde(ide);
            this.infNFe.setEmit(emit);
            this.infNFe.setDest(dest);
            this.infNFe.setInfAdic(infAdic);
            this.infNFe.setTotal(total);

            nfe.setInfNFe(infNFe);  
            
            
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao instanciar NFe: " + ex.getMessage());
        }
    }
    
    public void setLote(String Lote) {
        this.Lote = Lote;
    }
    
    public String getLote() {
        return this.Lote;
    }
    
    public void setVersao(String Versao) {
        this.Versao = Versao;
    }
    
    public String getVersao() {
        return this.Versao;
    }
    
    public Item NovoItem() {
        Item p = new Item(this.infNFe);
        Itens.add(p);
        return p;
    }
    
    public String getXML() 
    {
        getId();
        try {
            cleanImpostos();
            cleanTransportadora();
            cleanDestinatario();
        }catch(Exception ex) {}
        
        try
        {
            JAXBContext context = JAXBContext.newInstance(TNFe.class);  
            Marshaller marshaller = context.createMarshaller();  
            JAXBElement<TNFe> element = new ObjectFactory().createNFe(this.nfe);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);  
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);  

            StringWriter sw = new StringWriter();  
            marshaller.marshal(element, sw);  

            String xml = sw.toString();  
            xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");  
            xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");  

            //posso fazer isso para reduzir espaco com tags vazias
            xml = xml.replace("<cEAN></cEAN>", "<cEAN/>").replace("<cEANTrib></cEANTrib>", "<cEANTrib/>");
            xml = xml.replace("<CPF/>","").replace("<CNPJ/>", "");
            
            return xml;
        }
        catch(Exception ex) 
        {
            Log.getInstance().addErro("Erro: " + ex.getMessage());
            return null;
        }
    }  
    
    public String getXMLEnviNFe() 
    {
        try
        {
            getId();
            try {
                cleanImpostos();
                cleanTransportadora();
                cleanDestinatario();
            }catch(Exception ex) {}

            if (this.Lote == null) this.Lote = "1";
            if (this.Versao == null) this.Versao = "2.00";

            enviNFe.setIdLote(String.valueOf(this.Lote));
            enviNFe.setVersao(this.Versao);
            enviNFe.getNFe().add(this.nfe);

            JAXBContext context = JAXBContext.newInstance(TEnviNFe.class);  
            Marshaller marshaller = context.createMarshaller();  
            JAXBElement<TEnviNFe> element = new ObjectFactory().createEnviNFe(this.enviNFe);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);  
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);  

            StringWriter sw = new StringWriter();  
            marshaller.marshal(element, sw);  

            String xml = sw.toString();  
            xml = xml.replaceAll("<EnviNFe ", "<enviNFe ");  
            xml = xml.replaceAll("</EnviNFe>", "</enviNFe>");
            xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");  
            xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");  

            //posso fazer isso para reduzir espaco com tags vazias
            xml = xml.replace("<cEAN></cEAN>", "<cEAN/>").replace("<cEANTrib></cEANTrib>", "<cEANTrib/>");
            xml = xml.replace("<CPF/>","").replace("<CNPJ/>", "");
            
            return xml;
        }
        catch(Exception ex) 
        {
            Log.getInstance().addErro("Erro: " + ex.getMessage());
            return null;
        }
    }
    
    public boolean SaveToFile(String Arquivo) 
    {
        try
        {
            String xml = getXML();
            
            
            if (xml != null)
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(Arquivo));
                bw.write(xml);
                bw.close();
                return true;
            }
            else
                return false;
        }
        catch(Exception ex) {
            Log.getInstance().addErro("Erro: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean SaveEnviNFeToFile(String Arquivo) 
    {
        try
        {
            String xml = getXMLEnviNFe();
            if (xml != null)
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(Arquivo));
                bw.write(xml);
                bw.close();
                return true;
            }
            else
                return false;
        }
        catch(Exception ex) {
            Log.getInstance().addErro("Erro: " + ex.getMessage());
            return false;
        }
    }
    
    private int getDV(String chave) 
    {
        int dv = 0;
        int digito = 0;
        int sum = 0;        
        int peso = 2;
        
        try
        {
            for (int i = chave.length() -1; i >= 0; i--) 
            {
                digito = Integer.parseInt(String.valueOf(chave.charAt(i)));
                sum += (digito * peso);                
                peso++;                
                
                if (peso == 10) peso = 2;
            }

            int r = (sum % 11); 

            if ( (r == 0) || (r == 1) )
                dv = 0;
            else
                dv = (11 - r);            
        }
        catch(Exception e) {
            Log.getInstance().addErro("Erroe em NFe2.getDV(): " + e.getMessage());
        }
        
        return dv;
    }
    
    private void getId()
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
           43 1312 01611275000124 55  001 000000777  1  00000777  5
           43 1312 01611275000124 55  001 000000987  1  00000987  
         */
        try
        {
            if (this.infNFe.getId() == null)
            {
                String chave = "";
                int cDV = 0;

                try
                {        
                    chave = String.valueOf(this.ide.getCUF());
                    chave += getAAMM();
                    chave += this.emit.getCNPJ();
                    chave += this.ide.getMod();
                    chave += Util.leftPad(this.ide.getSerie(), 3, '0');
                    chave += Util.leftPad(this.ide.getNNF(), 9, '0');
                    chave += this.ide.getTpEmis();
                    chave += Util.leftPad(this.ide.getCNF(), 8, '0');
                    cDV = getDV(chave);
                    chave += getDV(chave);

                    chave = "NFe" + chave;     

                    this.infNFe.setId(chave);

                    this.ide.setCDV(String.valueOf(cDV)); 
                }
                catch(Exception e) {
                    getErros().add("Erro em getId(): " + e.getMessage());
                    System.out.println("Erro em getId(): " + e.getMessage());
                }
            }
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NFe2.getId(): " + ex.getMessage());
        }
    }
    
    public String getNomeArquivo() {
        try
        {
            String c = this.infNFe.getId();
            c = Configuracao.getInstance().getConfiguracoes().getPATH_NFE() + c.substring(3) + "-nfe.xml";
            return c;
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NFe2.getNomeArquivo(): " + ex.getMessage());
            return null;
        }
    }
    
    public String gerarNota() 
    {
        try
        {
            getId();
            String n = getNomeArquivo();
            SaveToFile(n);
            return n;
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NFe2.gerarNota(): " + ex.getMessage());
            return null;
        }
    }
    
    public void assinar() {
        Util.Assinar(getNomeArquivo());
    }
    
    private String getAAMM() {
        /*
         * 0123456789
         * 2013-11-27
         */
        String aamm = "";
        try
        {
            //String data = this.ide.getDEmi();
            String data = this.ide.getDhEmi();

            if ( (!data.equals("")) && (data != null) )
            aamm = data.substring(2, 4) + data.substring(5,7);
        }
        catch(Exception e) {
            Log.getInstance().addErro("Erro em NFe2.getAAMM(): " + e.getMessage());
        }
        
        return aamm;                
    }
    
    private void cleanImpostos() 
    {
        /*
         * Esta funcao percorre cada item da nota, verificando os impostos aplicados.
         * Os impostos que nao foram aplicados seto como nulo, pra nao ser impresso
         * a tag <imposto\> no XML.
         */
        
        for(Item item : Itens) 
        {
            //ICMS
            if (item.imposto.getICMS() != null) {
                if (item.imposto.getICMS().getICMSSN500() != null) {
                    if (item.imposto.getICMS().getICMSSN500().getVICMSSTRet() == null) {
                          item.imposto.getICMS().setICMSSN500(null);
                        
                    }
                }
                
                if (item.imposto.getICMS().getICMS00() != null) {
                    if (item.imposto.getICMS().getICMS00().getCST() == null) {
                        item.imposto.getICMS().setICMS00(null);
                    }
                }
                
                if (item.imposto.getICMS().getICMSST() != null) {
                    if (item.imposto.getICMS().getICMSST().getCST() == null) {
                        item.imposto.getICMS().setICMSST(null);
                    }
                }
            }
            
            //ATENCAO AKI!!!!! VALIDAR TODAS AS OPCOES PRA EXCLUIR O ICMS. USEI ESTAS DUAS PQ SAO AS Q USO POR ENQTO!
            if ( (item.imposto.getICMS().getICMSSN500() == null) && 
                 (item.imposto.getICMS().getICMSST() == null) && 
                 (item.imposto.getICMS().getICMS00() == null) ) {
                item.imposto.setICMS(null);
            }
            
            
            if (item.imposto.getPIS() != null) {
                if (item.imposto.getPIS().getPISAliq() != null) {
                    if (item.imposto.getPIS().getPISAliq().getVPIS() == null) {
                        item.imposto.getPIS().setPISAliq(null);
                    }                        
                }
                
                if (item.imposto.getPIS().getPISNT() != null) {
                    if (item.imposto.getPIS().getPISNT().getCST() == null) {
                        item.imposto.getPIS().setPISNT(null);
                    }
                }
                
                if (item.imposto.getPIS().getPISOutr() != null) {
                    if (item.imposto.getPIS().getPISOutr().getVPIS() == null) {
                        item.imposto.getPIS().setPISOutr(null);
                    }
                }
                
                if (item.imposto.getPIS().getPISQtde() != null) {
                    if (item.imposto.getPIS().getPISQtde().getVPIS() == null) {
                        item.imposto.getPIS().setPISQtde(null);
                    }
                }
            }
            
            //ISSQN
            if (item.imposto.getISSQN() != null) {
                if (item.imposto.getISSQN().getVISSQN() == null) {
                    item.imposto.setISSQN(null);
                }
            }
            
            //COFINS
            if (item.imposto.getCOFINS() != null) 
            {
                if (item.imposto.getCOFINS().getCOFINSAliq() != null) {
                    if (item.imposto.getCOFINS().getCOFINSAliq().getVCOFINS() == null) {
                        item.imposto.getCOFINS().setCOFINSAliq(null);
                    }
                }
                
                if (item.imposto.getCOFINS().getCOFINSNT() != null) {
                    if (item.imposto.getCOFINS().getCOFINSNT().getCST() == null) {
                        item.imposto.getCOFINS().setCOFINSNT(null);
                    }
                }
                
                if (item.imposto.getCOFINS().getCOFINSOutr() != null) {
                    if (item.imposto.getCOFINS().getCOFINSOutr().getVCOFINS() == null) {
                        item.imposto.getCOFINS().setCOFINSOutr(null);
                    }
                }
                
                if (item.imposto.getCOFINS().getCOFINSQtde() != null) {
                    if (item.imposto.getCOFINS().getCOFINSQtde().getVCOFINS() == null) {
                        item.imposto.getCOFINS().setCOFINSQtde(null);
                    }
                } 
                
            }
        }
        
        //limpa impostos da nota
        if (total.getISSQNtot() != null) {
            if (total.getISSQNtot().getVBC() == null) { //se precisar outra validar, tratar aki
                total.setISSQNtot(null);
            }
        } 
    }
    
    private void cleanTransportadora() {
        if(transportadora.transp != null) {
            if (transportadora.transp.getTransporta() != null) {
                if (transportadora.transp.getTransporta().getXNome() == null)
                    transportadora.transp.setTransporta(null);
            }
            
            if (transportadora.transp.getVeicTransp() != null) {
                if (transportadora.transp.getVeicTransp().getPlaca() == null)
                    transportadora.transp.setVeicTransp(null);
            }
            
            
            if ( (transportadora.transp.getTransporta() == null) && (transportadora.transp.getVeicTransp() == null) ) {
                transportadora.transp = null;
            }
        }        
    }
    
    public void cleanDestinatario() {
        if (dest != null) {
            if ( (dest.getXNome() == null) || (dest.getXNome().equals(""))) {
                infNFe.getDest().setEnderDest(null); 
                infNFe.setDest(null);
            }
            
            if ( (dest.getEnderDest().getXLgr() == null) || (dest.getEnderDest().getXLgr().equals("")) ) {
                infNFe.getDest().setEnderDest(null);
            }
        }
        
        
    }

    
}
