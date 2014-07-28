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
package webservices;

import Enums.Servicos;
import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento;
import iNFe.Configuracao;
import java.io.File;
import java.io.StringWriter;
import java.text.MessageFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import nfe.util.GetFieldsXML;
import nfe.util.Log;
import nfe.util.Util;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import webservices.Retornos.RetornoCancelamento;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo retorno

  <retEnvEvento versao="1.00" xmlns="http://www.portalfiscal.inf.br/nfe">
    <idLote>1</idLote>
    <tpAmb>2</tpAmb>
    <verAplic>RS20131127100609</verAplic>
    <cOrgao>43</cOrgao>
    <cStat>128</cStat>
    <xMotivo>Lote de Evento Processado</xMotivo>
    <retEvento versao="1.00">
        <infEvento>
            <tpAmb>2</tpAmb>
            <verAplic>RS20131127100609</verAplic>
            <cOrgao>43</cOrgao>
            <cStat>297</cStat>
            <xMotivo>Rejeicao: Assinatura difere do calculado</xMotivo>
            <chNFe>43131201600275000124550010000009901000009908</chNFe>
            <tpEvento>110111</tpEvento>
            <nSeqEvento>1</nSeqEvento>
            <dhRegEvento>2013-12-03T17:51:20-02:00</dhRegEvento>
        </infEvento>
    </retEvento>
  </retEnvEvento>

*/

public class NfeCancelamento2 extends WebServices {
    /*
     * Cancelamento de NF-e
     * 
     * Metodo: nfeCancelamentoNF2
     * Schema Envio: cancNFe_v2.00.xsd
     * Schema Retorno: retCancNFe_v2.00.xsd
     */
    
    public RetornoCancelamento Retorno;    
        
    private String chave;
    private String idLote;
    private String CNPJ_CPF;
    private String justificativa;
    private String nProt;
    
    private final int nSeqEvento;

    public void setCNPJ_CPF(String CNPJ_CPF) {
        this.CNPJ_CPF = CNPJ_CPF;
    }

    public void setidLote(String idLote) {
        this.idLote = idLote;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public void setnProt(String nProt) {
        this.nProt = nProt;
    }
    
    public void setChave(String chave) 
    {
        this.chave = chave;        
    }
       
    public NfeCancelamento2() {
        this.Retorno = new RetornoCancelamento();
        this.nSeqEvento = 1;
    }
    
    private String getNProt() 
    {
        try
        {
            if (this.nProt == null)
            {
                NfeConsulta2 c = new NfeConsulta2();
                c.setChave(this.chave);
                c.Executar();

                if (c.Retorno.infProt.nProt != null) 
                    this.nProt = c.Retorno.infProt.nProt;
            }
            return this.nProt;
        
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeCancelamento.getNProt(): " + ex.getMessage());
           return null;
        }
    }
    
    private String getEnvelope(String Arquivo)
    {
        try
        {
            String envelope = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
            envelope += "<soap12:Header>";
            envelope += "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\">";
            envelope += "<cUF>{0}</cUF>";
            envelope += "<versaoDados>{1}</versaoDados>";
            envelope += "</nfeCabecMsg>";
            envelope += "</soap12:Header>";
            envelope += "<soap12:Body>";
            envelope += "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\">";

            //envelope += "<envEvento versao=\"1.00\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><idLote>"+this.idLote+"</idLote>";

            envelope += Util.loadFromFile(Arquivo);

            //envelope += "</envEvento>";

            envelope += "</nfeDadosMsg>";
            envelope += "</soap12:Body>";
            envelope += "</soap12:Envelope>";

            //arruma envelope
            envelope = envelope.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
            envelope = envelope.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            envelope = envelope.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");

            envelope = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + envelope;

            return MessageFormat.format(envelope, this.cuf.getCodigo(), "1.00");
        
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeCancelamento.getEnvelope(): " + ex.getMessage());
           return null;
        }
    }
    
    private String getIdEvento() 
    {
       /*
        * O formato da ID eh o seguinte
        * ID+TIPO_EVENTO+CHAVE+SEQUENCIAL(2 casas)
        */
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append("ID").append("110111").append(this.chave).append(Util.leftPad(nSeqEvento, 2, '0'));
        
            return sb.toString();
        
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeCancelamento.getIdEvento(): " + ex.getMessage());
           return null;
        }
    }
    
    
    public void Executar() 
    {
        try
        {               
            //this.nSeqEvento = 1;

            //declara as classes
            InfEvento.DetEvento detEvento = new InfEvento.DetEvento();
            InfEvento infEvento = new InfEvento(); 
            TEvento evento = new TEvento();
            TEnvEvento envEvento = new TEnvEvento();

            //liga as classes
            envEvento.getEvento().add(evento);
            evento.setInfEvento(infEvento);
            infEvento.setDetEvento(detEvento);

            detEvento.setNProt(getNProt());
            detEvento.setXJust(this.justificativa);
            detEvento.setDescEvento("Cancelamento");
            detEvento.setVersao("1.00");  //detEvento.setVersao("1.00");

            infEvento.setCNPJ(this.CNPJ_CPF);
            infEvento.setCOrgao(String.valueOf(this.cuf.getCodigo()));
            infEvento.setChNFe(this.chave);
            infEvento.setDhEvento(Util.getDhEvento());
            infEvento.setId(getIdEvento());
            infEvento.setNSeqEvento(String.valueOf(this.nSeqEvento));
            infEvento.setTpAmb(this.tpamb.getTpAmb());
            infEvento.setTpEvento("110111");
            infEvento.setVerEvento("1.00");

            evento.setVersao("1.00"); //"1.00"
            evento.setSignature(null);

            envEvento.setIdLote(this.idLote);
            envEvento.setVersao("1.00"); //"1.00"

            /* Gera o nome dos arquivos */ 
            String fileName = c.getConfiguracoes().getPATH_NFE() + this.idLote + "-ped-evento.xml";
            String fileRetorno = c.getConfiguracoes().getPATH_NFE() + this.idLote + "-eve.xml";
        
            /* Converte o objeto TEvento em arquivo XML
             * Atencao: o arquivo deve ser gerado levando em conta somente a TAG
             * que serah assinada, ou seja, neste caso: <evento>.
             * Se eu gerar o arquivo com a tag envEvento vai dar erro na assinatura,
             * pois essa tmbm serah assinada, e nao vai bater o valor no SEFAZ
             */
            JAXBContext context = JAXBContext.newInstance(TEvento.class);
            Marshaller m = context.createMarshaller();
            JAXBElement<TEvento> element = new ObjectFactory().createEvento(evento);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            
            /* retorna para uma variavel o conteudo xml */
            StringWriter sw = new StringWriter();
            m.marshal(element, sw);

            /* arruma e salva salva xml em arquivo */
            String texto = sw.toString();
            Util.SaveToFile(texto, fileName);
            
            System.out.println("Arquivo gerado: " + fileName);
            
            /* assina o arquivo -ped-evento.xml */
            Util.AssinarCancelamento(fileName);
            
            /* acrescenta a tag <envEvento> no arquivo e remove tag de versao do xml add pela assinatura*/
            String temp = Util.loadFromFile(fileName);
            temp = "<envEvento versao=\"1.00\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><idLote>"+this.idLote+"</idLote>" + temp + "</envEvento>";          
            temp = temp.replace("<Evento", "<evento").replace("</Evento>", "</evento>");            
            temp = temp.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
            temp = temp.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            temp = temp.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            
            Util.SaveToFile(temp, fileName);       
            
            /* retorna o conteudo do arquivo e envelopa */
            String envelope = getEnvelope(fileName);
           
            Util.SaveToFile(envelope, "C:/ENVELOPACO.xml");
  
            /* envia o envelope SOAP para o servidor */
            //String url = new GetWebService().getURL(this.cuf, this.tpamb, this.versao, Servicos.RECEPCAO_EVENTO);
            String retorno = invoke(envelope, Servicos.RECEPCAO_EVENTO);
            
            /* desenvelopa o retorno */
            retorno = GetFieldsXML.getMensagem(retorno);
            retorno = retorno.replace("<nfeRecepcaoEventoResult xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\">", "");
            retorno = retorno.replace("</nfeRecepcaoEventoResult>", "");
            
            /* salva o retorno em arquivo */
            Util.SaveToFile(retorno, fileRetorno);
            
            /* le valores de retorno para a classe */
            lerRetorno(fileRetorno);
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao executar NfeCancelamento: " + ex.getMessage());
        }
            
    }
    
    public void lerRetorno(String ArquivoRetorno) 
    {
        try
        {
            SAXBuilder b = new SAXBuilder();
            Document doc = b.build(new File(ArquivoRetorno));
            
            Element root = doc.getRootElement();
            
            this.Retorno.tpAmb = Util.getValue(root, "tpAmb");
            this.Retorno.verAplic = Util.getValue(root, "verAplic");
            this.Retorno.cOrgao = Util.getValue(root, "cOrgao");
            this.Retorno.cStat = Util.getValue(root, "cStat");
            this.Retorno.xMotivo = Util.getValue(root, "xMotivo");
            
            Element ret = root.getChild("retEvento", root.getNamespace());
            
            if (ret != null)
            {
                Element inf = ret.getChild("infEvento", root.getNamespace());
                
                this.Retorno.infEvento.tpAmb = Util.getValue(inf, "tpAmb");
                this.Retorno.infEvento.verAplic = Util.getValue(inf, "verAplic");
                this.Retorno.infEvento.cOrgao = Util.getValue(inf, "cOrgao");
                this.Retorno.infEvento.cStat = Util.getValue(inf, "cStat");
                this.Retorno.infEvento.xMotivo = Util.getValue(inf, "xMotivo");
                this.Retorno.infEvento.chNFe = Util.getValue(inf, "chNFe");
                this.Retorno.infEvento.tpEvento = Util.getValue(inf, "tpEvento");
                this.Retorno.infEvento.dhRegEvento = Util.getValue(inf, "dhRegEvento");
            }
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NfeCancelamento.LerRetorno: " + ex.getMessage());
        }
        
    }
    
}
