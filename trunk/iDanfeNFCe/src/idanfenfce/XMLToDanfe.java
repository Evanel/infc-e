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
package idanfenfce;

import com.is5.Danfe.*;
import java.io.File;
import java.util.ArrayList;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Ivan Vargas
 */
public class XMLToDanfe {
    
    private Retorno retorno;
    
    private String urlConsulta;
    private String protocolo;
    private String dhProtocolo;
    private String arquivoXML;
    
    public Retorno getRetorno() {
        if (retorno == null) {
            retorno = new Retorno();
        }
        return retorno;
    }
    
    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
    
    public void setArquivoXML(String arquivoXML) {
        this.arquivoXML = arquivoXML;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public void setDhProtocolo(String dhProtocolo) {
        this.dhProtocolo = dhProtocolo;
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }
    
    public Danfe executar() {
        try
        {
            Danfe danfe = new Danfe();
            danfe.setArquivoXML(this.arquivoXML); //soh para facilitar na hora de gerar o qrCode
            
            SAXBuilder b = new SAXBuilder();
            org.jdom2.Document doc = b.build(new File(this.arquivoXML));
            org.jdom2.Element root = doc.getRootElement();
            
            if (root != null) 
            {
                org.jdom2.Element infNFe = root.getChild("infNFe", root.getNamespace());
                if (infNFe != null)
                {
                    //retorna chave
                    String id = infNFe.getAttributeValue("Id", root.getName());
                    if (!id.equals(""))
                    {
                        id = Util.formatChave(id);
                        danfe.setChaveAcesso(id);
                    }
                    
                    //DADOS DA NOTA
                    org.jdom2.Element ide = infNFe.getChild("ide", root.getNamespace());
                    if (ide != null) 
                    {
                        danfe.setNumero( Util.getValue(ide, "cNF") );
                        danfe.setSerie( Util.getValue(ide, "serie") );
                        danfe.setAmbiente( Util.getValue(ide, "tpAmb") );
                        
                        if (danfe.getAmbiente().equals("1"))
                        {
                            /*
                            1- Emissão normal (não em contingência); 
                            2- Contingência FS-IA, com impressão do DANFE em formulário de segurança; 
                            3- Contingência SCAN (Sistema de Contingência do Ambiente Nacional); 
                            4- Contingência DPEC (Declaração Prévia da Emissão em Contingência); 
                            5- Contingência FS-DA, com impressão do DANFE em formulário de  segurança; 
                            6- Contingência SVC-AN (SEFAZ Virtual de Contingência do AN); 
                            7- Contingência SVC-RS (SEFAZ Virtual de Contingência do RS); 
                            9- Contingência off-line da NFC-e (as demais opções de contingência são válidas também para a NFC-e); 
                            Nota: As opções de contingência 3, 4, 6 e 7 (SCAN, DPEC e SVC) não estão 
                            disponíveis no momento atual. 
                            */

                        if ( !Util.getValue(ide, "tpEmis").equals("1") );
                                danfe.setTipoEmissao("EMITIDA EM CONTINGÊNCIA");

                        //SE EH PRODUCAO E FOI EMITIDA NORMALMENTE, NAO MOSTRAR NADA... EU ACHO
                        }                        
                        else
                        {
                            danfe.setTipoEmissao("EMITIDA EM AMBIENTE DE HOMOLOGAÇÃO - SEM VALOR FISCAL");
                        }   

                        String dhEmi = Util.getValue(ide, "dhEmi");
                        danfe.setDataHoraEmissao( Util.getDataHora(dhEmi) );
                        danfe.setData( Util.getData(dhEmi) );
                        danfe.setHora( Util.getHora(dhEmi) );                       
                        danfe.setTipoVia("Via Cliente");
                    }

                    //DADOS DO EMITENTE
                    org.jdom2.Element emit = infNFe.getChild("emit", root.getNamespace());
                    if (emit != null) 
                    {
                        Emitente emitente = new Emitente();

                        emitente.setRazaoSocial(Util.getValue(emit, "xNome"));
                        emitente.setCnpj(Util.getValue(emit, "CNPJ"));
                        emitente.setIe(Util.getValue(emit, "IE"));

                        org.jdom2.Element endereco = emit.getChild("enderEmit", root.getNamespace());
                        if (endereco != null) 
                        {
                            Endereco e = new Endereco();

                            e.setBairro( Util.getValue(endereco, "xBairro") );
                            e.setCep( Util.getValue(endereco, "CEP") );
                            e.setCidade( Util.getValue(endereco, "xMun") );
                            e.setComplemento( Util.getValue(endereco, "xCpl") );
                            e.setEstado( Util.getValue(endereco, "UF") );
                            e.setLogradouro( Util.getValue(endereco, "xLgr") );
                            e.setNumero( Util.getValue(endereco, "nro") );
                            e.setTelefone( Util.getValue(endereco, "fone") );

                            emitente.setEndereco(e);                        
                        }

                        danfe.setEmitente(emitente);                    
                    }

                    //DADOS DO CONSUMIDOR (destinatario)
                    org.jdom2.Element dest = infNFe.getChild("dest", root.getNamespace());
                    if (dest != null)
                    {
                        Consumidor consumidor = new Consumidor();
                        consumidor.setNome( Util.getValue(dest, "xNome") );

                        String cgc = Util.getValue(dest, "CNPJ");
                        if (cgc.equals(""))
                            cgc = Util.getValue(dest, "CPF");

                        consumidor.setCnpjCpfId(cgc);
                        
                        org.jdom2.Element enderDest = dest.getChild("enderDest", dest.getNamespace());
                        if (enderDest != null) 
                        {
                            Endereco endDest = new Endereco();

                            endDest.setBairro( Util.getValue(enderDest, "xBairro") );
                            endDest.setCep( Util.getValue(enderDest, "CEP") );
                            endDest.setCidade( Util.getValue(enderDest, "xMun") );
                            endDest.setComplemento( Util.getValue(enderDest, "xCpl") );
                            endDest.setEstado( Util.getValue(enderDest, "UF") );
                            endDest.setLogradouro( Util.getValue(enderDest, "xLgr") );
                            endDest.setNumero( Util.getValue(enderDest, "nro") );
                            endDest.setTelefone( Util.getValue(enderDest, "fone") );

                            consumidor.setEndereco(endDest);
                        }
                        
                        danfe.setConsumidor(consumidor);
                    }

                    //ITENS
                    java.util.List<Item> itens = new ArrayList<>();

                    java.util.List<org.jdom2.Element> det = infNFe.getChildren("det", root.getNamespace());
                    if (det != null)
                    {
                        for (org.jdom2.Element d : det) 
                        {
                            org.jdom2.Element prod = d.getChild("prod", root.getNamespace() );
                            if (prod != null)
                            {
                                Item i = new Item();

                                i.setItem( d.getAttributeValue("nItem", root.getNamespace()) );
                                i.setCodigo( Util.getValue(prod, "cProd") );
                                i.setDescricao( Util.getValue(prod, "xProd") );
                                i.setQtd( Util.getValue(prod, "qCom") );
                                i.setVlUnit( Util.getValue(prod, "vUnCom") );
                                i.setVlTotal( Util.getValue(prod, "vProd") );   

                                itens.add(i);
                            }
                        } 
                        danfe.setItens(itens);
                    }
                    
                    //TOTAL DA NOTA
                    org.jdom2.Element total = infNFe.getChild("total", root.getNamespace());
                    if (total != null) 
                    {
                        org.jdom2.Element icmsTot = total.getChild("ICMSTot", root.getNamespace());
                        if (icmsTot != null) 
                        {
                            danfe.setValorTotal( Util.getValue(icmsTot, "vNF") );
                            danfe.setvDesc( Util.getValue(icmsTot, "vDesc") );
                            danfe.setvOutro( Util.getValue(icmsTot, "vOutro") );
                        }
                    }

                    //FORMAS PAG
                    java.util.List<org.jdom2.Element> pag = infNFe.getChildren("pag", root.getNamespace());
                    if (pag != null)
                    {
                        java.util.List<FormaPag> formasPag = new ArrayList<>();
                        
                        for (org.jdom2.Element p : pag) 
                        {
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

                            FormaPag fp = new FormaPag();

                            String codFormaPag = Util.getValue(p, "tPag");
                            switch(codFormaPag) 
                            {
                                case "01" : fp.setDescricao("Dinheiro"); break;
                                case "02" : fp.setDescricao("Cheque"); break;
                                case "03" : fp.setDescricao("Cartão de Crédito"); break;
                                case "04" : fp.setDescricao("Cartão de Débito"); break;
                                case "05" : fp.setDescricao("Crédito Loja"); break;
                                case "10" : fp.setDescricao("Vale Alimentação"); break;
                                case "11" : fp.setDescricao("Vale Refeição"); break;
                                case "12" : fp.setDescricao("Vale Presente"); break;
                                case "13" : fp.setDescricao("Vale Combustível"); break;
                                case "99" : fp.setDescricao("Outros"); break;      
                                default : fp.setDescricao("Dinheiro");
                            }

                            fp.setValor( Util.getValue(p, "vPag"));                            
                            formasPag.add(fp);
                        }
                        danfe.setFormasPagamento(formasPag);
                    }

                    danfe.setUrlConsulta(this.urlConsulta);
                    danfe.setProtocolo(this.protocolo);
                    danfe.setDataHoraProtocolo(this.dhProtocolo);
                
                } //infNFe   
            } //root
            
      
            
            return danfe;
            
        }catch(Exception ex) {
            System.out.println("Erro em importarXML(): " + ex.getMessage());
            this.getRetorno().addErro( "Erro em importarXML(): " + ex.getMessage() + "\n" );
            return null;
        }
    }
}
