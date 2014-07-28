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
import java.io.File;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import nfe.util.GetFieldsXML;
import nfe.util.Log;
import nfe.util.Util;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import webservices.Retornos.RetornoCartaCorrecao;

/**
 *
 * @author Ivan Vargas
 * 
 * A carta de correcao nao tem um WebService especifico, uma vez que se utiliza
 * de Eventos. Mas, pra nao ficar misturando codigo, prefiro reescrever o
 * codigo aki mesmo (eh semelhante ao cancelamento de nfe)
 */
public class CartaCorrecao extends WebServices {
    
    public RetornoCartaCorrecao Retorno;
        
    private String idLote;
    private String chNFe;
    private String xCorrecao;
    private String nSeqEvento;
    
    public CartaCorrecao() {
        this.Retorno = new RetornoCartaCorrecao();
    }
        
    public void setIdLote(String value) {
        this.idLote = value;
    }
    
    public void setChNFe(String value) {
        this.chNFe = value;
    }
    
    public void setXCorrecao(String value) {
        this.xCorrecao = value;
    }
    
    public void setNSeqEvento(String value) {
        this.nSeqEvento = value;
    }
    
    private String getEnvelope(String Arquivo)
    {
        try
        {
            String envelope = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
            envelope += "<soap12:Header>";
            envelope += "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\">";
            envelope += "<cUF>" + this.cuf.getCodigo() + "</cUF>";
            envelope += "<versaoDados>1.00</versaoDados>"; //a versao aki dese ser 1.00 mesmo
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

            return envelope;
            
       }catch(Exception ex) {
           Log.getInstance().addErro("Erro em CartaCorrecao.getEnvelope(): " + ex.getMessage());
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
            sb.append("ID").append("110110").append(this.chNFe).append(Util.leftPad(nSeqEvento, 2, '0'));

            return sb.toString();
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em CartaCorrecao.getIdEvento(): " + ex.getMessage());
           return null;
        }
    }
    
    public void Executar() 
    {
        try
        {
            /* classes para gerar XML */
            TEnvEvento envEvento = new TEnvEvento();
            TEvento evento = new TEvento();
            TEvento.InfEvento infEvento =  new TEvento.InfEvento();
            TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();

            /* vinculo as classes */
            envEvento.getEvento().add(evento);
            evento.setInfEvento(infEvento);
            infEvento.setDetEvento(detEvento);

            /* passo os parametros */
            evento.setVersao("1.00");        
            envEvento.setIdLote(this.idLote);
            envEvento.setVersao("1.00");

            infEvento.setId(getIdEvento());
            infEvento.setCOrgao(String.valueOf(this.cuf.getCodigo()));
            infEvento.setTpAmb(this.tpamb.getTpAmb());
            infEvento.setCNPJ(c.getConfiguracoes().getCNPJ());
            infEvento.setChNFe(this.chNFe);
            infEvento.setDhEvento(Util.getDhEvento());
            infEvento.setTpEvento("110110");
            infEvento.setNSeqEvento(this.nSeqEvento);
            infEvento.setVerEvento("1.00");

            detEvento.setVersao("1.00");
            detEvento.setDescEvento("Carta de Correcao");
            detEvento.setXCorrecao(this.xCorrecao);

            //ATENÇÃO: Este texto eh um literal. Deve ser passado como está:
            detEvento.setXCondUso("A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.");

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

            /* salva xml em arquivo */
            String texto = sw.toString();
            texto = texto.replace("<Evento", "<evento").replace("</Evento>", "</evento>");
            Util.SaveToFile(texto, fileName);

            System.out.println("Arquivo gerado: " + fileName);

            /* assina o arquivo -ped-evento.xml */
            Util.AssinarCancelamento(fileName);
            
            /* acrescenta a tag <envEvento> no arquivo */
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
            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx");
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
            Log.getInstance().addErro("Erro ao executar CartaCorrecao: " + ex.getMessage());
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
            Log.getInstance().addErro("Erro em CartaCorrecao.LerRetorno(): " + ex.getMessage());
        }
        
    }
    
}
