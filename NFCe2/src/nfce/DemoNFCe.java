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
package nfce;

import Frames.frmPrincipal;
import br.inf.portalfiscal.nfe.TNFe.InfNFe.Pag;
import br.inf.portalfiscal.nfe.TUf;
import br.inf.portalfiscal.nfe.TUfEmi;
import iNFe.Configuracao;
import iNFe.Emissor;
import iNFe.Item;
import iNFe.NFe2;
import javax.swing.JOptionPane;
import nfe.util.Util;

/**
 *
 * @author Ivan Vargas
 *   
 *  Este eh um exemplo basico de como enviar uma NFC-e utilizando o projeto iNFC-e.
 *  Ao rodar este exemplo o sistema pede algumas informacoes (utilizando JOptionPane,
 *  pq realizei este teste em desktop, porem vc pode passas os parametros da forma
 *  que desejar), a seguir ele monta a NFC-e (classe NFe2) e procede com o envio
 *  da mesma, mostrando, por fim, o resultado do processamento.
 *  
 */
public class DemoNFCe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //JOptionPane.showMessageDialog(null, "RODANDO!!!");
        //Enviar();
        frmPrincipal p = new frmPrincipal();
        p.setVisible(true);
    }
    
    public static void Enviar() {
        String nNota = JOptionPane.showInputDialog("Numero Nota");
        Configuracao c = Configuracao.getInstance();
        
        if (nNota.length() > 8)
        {
            JOptionPane.showMessageDialog(null, "O Numero da Nota nao pode ter mais que 8 caracteres");
            return;
        }
        
        String nLote = JOptionPane.showInputDialog("Lote", "1");
                
        try
        {       
            NFe2 nfe2 = new NFe2();
           
            nfe2.setLote(nLote);
            nfe2.infNFe.setVersao("3.10"); 
            nfe2.ide.setCUF("43");
            //nfe2.ide.setCNF("00000992");
            nfe2.ide.setCNF(Util.leftPad(nNota, 8, '0'));
            nfe2.ide.setNatOp("VENDA PRODUCAO ESTA");
            nfe2.ide.setIndPag("0");
            nfe2.ide.setMod("65"); //NCF-e
            nfe2.ide.setSerie("1");
            nfe2.ide.setNNF(nNota);
            
            nfe2.ide.setDhEmi(Util.getDhEvento());
            //nfe2.ide.setDhSaiEnt(Util.getDhEvento()); //NAO TEM NO NFC-e!
            
            //nfe2.ide.setDEmi(Util.getDataAtualFormatStr("yyyy-MM-dd"));
            //nfe2.ide.setDSaiEnt(Util.getDataAtualFormatStr("yyyy-MM-dd"));
            //nfe2.ide.setHSaiEnt("17:17:17");
            
            nfe2.ide.setTpNF("1");
            nfe2.ide.setCMunFG(c.getConfiguracoes().getCOD_CIDADE()); 
            
            /* Tipo de Impressao do DANFE (usar 4 ou 5)
                0-Sem geração de DANFE; 
                1-DANFE normal , Retrato; 
                2-DANFE normal, Paisagem; 
                3-DANFE Simplificado; 
             -> 4-DANFE NFC-e; 
             -> 5-DANFE NFC-e em mensagem eletrônica. 
                Nota: O envio de mensagem eletrônica 
                pode ser feita de forma simultânea com 
                a impressão do DANFE. Usar o 
                tpImp=5 quando esta for a única forma 
                de disponibilização do DANFE.
            */
                
            nfe2.ide.setTpImp("4");
            
            nfe2.ide.setTpEmis(c.getConfiguracoes().getFORMA_EMISSAO());
            nfe2.ide.setTpAmb(c.getConfiguracoes().getAMBIENTE());
            nfe2.ide.setFinNFe("1");
            nfe2.ide.setProcEmi("0");
            nfe2.ide.setVerProc("2.2.20");

            /*
             *  1- Operação interna 
                2- Operação interestadual 
                3- Operação com exterior 
             */
            nfe2.ide.setIdDest("1"); 
            
            
            
            /* Indica operacao com consumidor final
             * 0- Não; 
               1- Consumidor final; 
             */
            nfe2.ide.setIndFinal("1");
            
            /*Indicador de presença do comprador 
              no estabelecimento comercial no 
              momento da operação:
              
                0 - Não se aplica (por exemplo, Nota  Fiscal complementar ou de ajuste); 
                1 - Operação presencial; 
                2 - Operação não presencial, pela Internet; 
                3 - Operação não presencial, Teleatendimento; 
                9 - Operação não presencial, outros. 
             */
            
            nfe2.ide.setIndPres("1");
            
            
            //Emitente
            nfe2.emit.setCNPJ(c.getConfiguracoes().getCNPJ());
            nfe2.emit.setXNome(c.getConfiguracoes().getRAZAO_SOCIAL());
            nfe2.emit.setXFant(c.getConfiguracoes().getFANTASIA());
            nfe2.emit.getEnderEmit().setXLgr(c.getConfiguracoes().getLOGRADOURO());
            nfe2.emit.getEnderEmit().setNro(c.getConfiguracoes().getNUMERO());
            nfe2.emit.getEnderEmit().setXCpl(c.getConfiguracoes().getCOMPLEMENTO());
            nfe2.emit.getEnderEmit().setXBairro(c.getConfiguracoes().getBAIRRO());
            nfe2.emit.getEnderEmit().setCMun(c.getConfiguracoes().getCOD_CIDADE());
            nfe2.emit.getEnderEmit().setXMun(c.getConfiguracoes().getCIDADE());
            nfe2.emit.getEnderEmit().setUF(TUfEmi.RS); //aki to setando RS diretamente... (teste)
            nfe2.emit.getEnderEmit().setCEP(c.getConfiguracoes().getCEP());
            nfe2.emit.getEnderEmit().setCPais("1058");
            nfe2.emit.getEnderEmit().setXPais("BRASIL");
            nfe2.emit.getEnderEmit().setFone(c.getConfiguracoes().getFONE());
            nfe2.emit.setIE(c.getConfiguracoes().getIE());
            nfe2.emit.setIM(c.getConfiguracoes().getIM());
            nfe2.emit.setCNAE(c.getConfiguracoes().getCRT());
            nfe2.emit.setCRT(c.getConfiguracoes().getCRT());

            //Destinatario
            nfe2.dest.setCNPJ("11856486000180");
            nfe2.dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
            nfe2.dest.getEnderDest().setXLgr("RUA DE TESTE");
            nfe2.dest.getEnderDest().setNro("123");
            nfe2.dest.getEnderDest().setXBairro("CENTRO");
            nfe2.dest.getEnderDest().setCMun("4311502"); 
            nfe2.dest.getEnderDest().setXMun("LAVRAS DO SUL");
            nfe2.dest.getEnderDest().setUF(TUf.RS);
            nfe2.dest.getEnderDest().setCEP("96570000");
            nfe2.dest.getEnderDest().setXPais("BRASIL");
            nfe2.dest.getEnderDest().setCPais("1058");
            nfe2.dest.getEnderDest().setFone("5591131399");
            //nfe2.dest.setIE("0730024423"); NFCe nao pede IE
            
            /* Indicador De Inscrição Estadual Destinatário
             * Para NFC-e informar 9 e nao especificar o IE
             */
            nfe2.dest.setIndIEDest("9");

            Item i = nfe2.NovoItem();

            i.det.setNItem("1");
            i.det.getProd().setCProd("1234567");
            i.det.getProd().setCEAN("");
            i.det.getProd().setXProd("DESCRICAO DO PRODUTO");
            i.det.getProd().setNCM("30049059");
            i.det.getProd().setCFOP("5933");
            i.det.getProd().setUCom("UN");
            i.det.getProd().setVUnCom("100.00");
            i.det.getProd().setVProd("100.00");
            i.det.getProd().setCEANTrib(null);
            i.det.getProd().setCEANTrib("");
            i.det.getProd().setUTrib("UN");
            i.det.getProd().setQTrib("1.0000");
            i.det.getProd().setQCom("1.00");
            i.det.getProd().setIndTot("1");
            i.det.getProd().setVUnTrib("100.00");
            /*ISSQN
            i.det.getImposto().getISSQN().setVBC("100.00");
            i.det.getImposto().getISSQN().setVAliq("2.00");
            i.det.getImposto().getISSQN().setVISSQN("2.00");
            i.det.getImposto().getISSQN().setCMunFG("3554003");
            i.det.getImposto().getISSQN().setCListServ("14.02");
            //i.det.getImposto().getISSQN().setCSitTrib("N");
            i.det.getImposto().getISSQN().setVOutro("100.00");
            i.det.getImposto().getISSQN().setVDescCond("0.00");
            */
            
            //CFC-e
            i.det.getImposto().getICMS().getICMS00().setCST("00");
            i.det.getImposto().getICMS().getICMS00().setOrig("0");
            i.det.getImposto().getICMS().getICMS00().setModBC("0");
            i.det.getImposto().getICMS().getICMS00().setPICMS("0.00");
            i.det.getImposto().getICMS().getICMS00().setVBC("0.00");
            i.det.getImposto().getICMS().getICMS00().setVICMS("0.00");            
            
            
            //PIS
            i.det.getImposto().getPIS().getPISAliq().setCST("01");
            i.det.getImposto().getPIS().getPISAliq().setVBC("0.00");
            i.det.getImposto().getPIS().getPISAliq().setPPIS("0.00");
            i.det.getImposto().getPIS().getPISAliq().setVPIS("0.00");
             
            //COFINS
            i.det.getImposto().getCOFINS().getCOFINSAliq().setCST("01");
            i.det.getImposto().getCOFINS().getCOFINSAliq().setVBC("0.00");
            i.det.getImposto().getCOFINS().getCOFINSAliq().setPCOFINS("0.00");
            i.det.getImposto().getCOFINS().getCOFINSAliq().setVCOFINS("0.00");
            i.det.setInfAdProd("Informacoes adicionais do produto.");
            
            /*
             Total do vNF =
                (+) vProd (id:W07) 
                (-) vDesc (id:W10) 
                (+) vST (id:W06) 
                (+) vFrete (id:W08) 
                (+) vSeg (id:W09) 
                (+) vOutro (id:W15) 
                (+) vII (id:W11) 
                (+) vIPI (id:W12) 
                (+) vServ (id:W18) (*3) (NT 2011/005) 
            */
            
            //ICMSTotal
            nfe2.total.getICMSTot().setVBC("0.00");
            nfe2.total.getICMSTot().setVBCST("0.00");
            nfe2.total.getICMSTot().setVCOFINS("0.00");
            nfe2.total.getICMSTot().setVDesc("0.00");
            nfe2.total.getICMSTot().setVFrete("0.00");
            nfe2.total.getICMSTot().setVICMS("0.00");
            nfe2.total.getICMSTot().setVII("0.00");
            nfe2.total.getICMSTot().setVIPI("0.00");
            nfe2.total.getICMSTot().setVNF("100.00");
            nfe2.total.getICMSTot().setVOutro("0.00");
            nfe2.total.getICMSTot().setVPIS("0.00");
            nfe2.total.getICMSTot().setVProd("100.00");
            nfe2.total.getICMSTot().setVST("0.00");
            nfe2.total.getICMSTot().setVSeg("0.00");
            nfe2.total.getICMSTot().setVICMSDeson("0.00");
            
            //TDec_1302Opc
            
            /*ISSQNTotal
            nfe2.total.getISSQNtot().setVBC("100.00");
            nfe2.total.getISSQNtot().setVServ("100.00");
            nfe2.total.getISSQNtot().setVISS("2.00");
            */
            
            //NFC-e nao tem transportador
            //Transp
            nfe2.transportadora.transp.setModFrete("9");
            //TNFe.InfNFe.Transp.Vol v = nfe2.transportadora.NovoVolume();
            //v.setQVol("1");
            //v.setEsp("Especie");
            //v.setMarca("marca");
            //v.setNVol("numero");
            //v.setPesoL("100.000");
            //v.setPesoB("110.000");

            //NFC-e nao eh Cobranca, mas Pag
            //Cobranca
            //nfe2.cobranca.cobr.getFat().setNFat("000001");
            //nfe2.cobranca.cobr.getFat().setVOrig("100.00");
            //nfe2.cobranca.cobr.getFat().setVLiq("100.00");

            //TNFe.InfNFe.Cobr.Dup d = nfe2.cobranca.NovaDuplicata();
            //d.setNDup("1234");
            //d.setDVenc("2013-12-01");
            //d.setVDup("50.00");

            //TNFe.InfNFe.Cobr.Dup d2 = nfe2.cobranca.NovaDuplicata();
            //d2.setNDup("1235");
            //d2.setDVenc("2014-01-01");
            //d2.setVDup("50.00");

            /*
              Grupo obrigatório para a NFC-e 
                01- Dinheiro 
                02 -Cheque 
                03- Cartão de Crédito 
                04- Cartão de Débito 
                05- Crédito Loja 
                10- Vale Alimentação 
                11- Vale Refeição 
                12- Vale Presente 
                13- Vale Combustível 
                99 – Outros 
             */
            
            Pag pag = new Pag();
            pag.setTPag("01");
            pag.setVPag("100.00");
            
            nfe2.infNFe.getPag().add(pag);
            
            nfe2.infAdic.setInfAdFisco("Info ao fisco");
            nfe2.infAdic.setInfCpl("Informacoes complementares");
        
        
            Emissor e = new Emissor();
            e.setNFe(nfe2);
            e.setLote(nLote);
            boolean r = e.EmitirNotaFiscal();

            if (r)
            {
                JOptionPane.showMessageDialog(null, "cStat: " + e.Recepcao.Retorno.cStat +
                                                    "\ntpAmb: " + e.Recepcao.Retorno.tpAmb +
                                                    "\nverAplic: " + e.Recepcao.Retorno.verAplic +
                                                    "\nxMotivo: " + e.Recepcao.Retorno.xMotivo +
                                                    "\ncUF: " + e.Recepcao.Retorno.cUF +
                                                    "\ndhRecbto: " + e.Recepcao.Retorno.dhRecbto +
                                                    "\nnRec: " + e.Recepcao.Retorno.infRec.nRec +
                                                    "\ntMed: " + e.Recepcao.Retorno.infRec.tMed );

                if (e.RetornoRecepcao.Retorno.cStat != null) 
                    JOptionPane.showMessageDialog(null, "RETORNO\ncStat: " + e.RetornoRecepcao.Retorno.cStat +
                                                    "\ntpAmb: " + e.RetornoRecepcao.Retorno.tpAmb +
                                                    "\nverAplic: " + e.RetornoRecepcao.Retorno.verAplic +
                                                    "\nxMotivo: " + e.RetornoRecepcao.Retorno.xMotivo +
                                                    "\ncUF: " + e.RetornoRecepcao.Retorno.cUF
                                                    );

                if (e.RetornoRecepcao.Retorno.cStat.equals("104")) 
                    JOptionPane.showMessageDialog(null, "INFORMACOES DE PROTOCOLO" +
                                                    "\ntpAmb: " + e.RetornoRecepcao.Retorno.infProt.tpAmb +
                                                    "\nverAplic: " + e.RetornoRecepcao.Retorno.infProt.verAplic +
                                                    "\nchNFe: " + e.RetornoRecepcao.Retorno.infProt.chNFe +
                                                    "\ndhRecbto: " + e.RetornoRecepcao.Retorno.infProt.dhRecbto +
                                                    "\ndigVal: " + e.RetornoRecepcao.Retorno.infProt.digVal +
                                                    "\ncStat: " + e.RetornoRecepcao.Retorno.infProt.cStat +
                                                    "\nxMotivo: " + e.RetornoRecepcao.Retorno.infProt.xMotivo
                                                );

                if (e.AutorizadoUso())
                    JOptionPane.showMessageDialog(null, "AUTORIZADO O USO. IMPRIMIR DANFE!");
                else
                    JOptionPane.showMessageDialog(null, "USO NAO AUTORIZADO!\nxMotivo: " + e.RetornoRecepcao.Retorno.xMotivo +
                                                        "\ninfProt.xMotivo: " + e.RetornoRecepcao.Retorno.infProt.xMotivo);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Nao foi possivel Emitir nota :/");

                if (e.getMensagem().ContemErros())
                {
                    e.getMensagem().ShowMensagens();
                }
            }
            
       }
       catch(Exception e) 
       {
            JOptionPane.showMessageDialog(null, "Erro ao escrever nota: " + e.getMessage());
            return;
       }
    }
}
