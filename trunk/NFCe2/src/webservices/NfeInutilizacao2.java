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
import br.inf.portalfiscal.nfe.TInutNFe;
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
import webservices.Retornos.RetornoInutilizacao;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo de retorno
  
  <?xml version="1.0" encoding="utf-8"?>
  <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <soap:Header>
        <nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2">
            <cUF>43</cUF>
            <versaoDados>2.00</versaoDados>
        </nfeCabecMsg>
    </soap:Header>
    <soap:Body>
        <nfeInutilizacaoNF2Result xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2">
            <retInutNFe versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">
                <infInut Id="Id20131205093430897">
                    <tpAmb>2</tpAmb>
                    <verAplic>RS20131126163341</verAplic>
                    <cStat>517</cStat>
                    <xMotivo>Rejeicao: Falha no schema XML – inexiste atributo versao na tag raiz da mensagem</xMotivo>
                    <cUF>43</cUF>
                    <dhRecbto>2013-12-05T09:34:30</dhRecbto>
                </infInut>
            </retInutNFe>
        </nfeInutilizacaoNF2Result>
    </soap:Body>
  </soap:Envelope>

 */
public class NfeInutilizacao2 extends WebServices {
    /*
     * Inutilizacao de Numeracao de NF-e
     * 
     * Metodo: nfeInutilizacaoNF2
     * Schema Envio: inuNFe_v2.00.xsd
     * Schema Retorno: retInutNFe_v2.00.xsd 
     */
    
    //private String xServ;
    private String ano;
    private String cnpj;
    private String mod;
    private String serie;
    private String nnffini;
    private String nnffin;
    private String xjust;
    
    public RetornoInutilizacao Retorno;
    
    public void setCNPJ(String CNPJ) {
        this.cnpj = CNPJ;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public void setnNFFin(String nNFFin) {
        this.nnffin = nNFFin;
    }

    public void setnNFIni(String nNFIni) {
        this.nnffini = nNFIni;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setxJust(String xJust) {
        this.xjust = xJust;
    }
    
    public NfeInutilizacao2() {
        this.Retorno = new RetornoInutilizacao();
    }
    
    private String getId()
    {
        /*
         * O campo ID deve ter 43 caracteres, como segue:
         * 
         * ID 43 13 01600275000124 55 001 000000001 000000002
         * 
         * Onde:
         * 
         * Prefixo ID (2)
         * cUF (2)
         * Ano (2)
         * CNPJ (14)
         * Modelo (2)
         * Serie (3)
         * nInicial (9)
         * nFinal (9)
         */
        try
        {
            String id = "ID";
            id += String.valueOf(this.cuf.getCodigo());
            id += this.ano.trim();
            id += this.cnpj.trim();
            id += Util.leftPad(this.mod.trim(), 2, '0');
            id += Util.leftPad(this.serie.trim(), 3, '0');
            id += Util.leftPad(this.nnffini.trim(), 9, '0');
            id += Util.leftPad(this.nnffin.trim(), 9, '0');

            return id;
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeInutilizacao.getId(): " + ex.getMessage());
           return null;
        }
    }
    
    private String getPrefix() {
        /*
         * Formato do nomo do arquivo
         * 
         *  UF + ANO + CNPJ + MODELO + SERIE + NUMERO_INICIAL + NUMERO_FINAL -ped.inu.xml
         */
        try
        {
            return this.cuf.getCodigo() + 
                   this.ano + 
                   this.mod + 
                   this.serie + 
                   this.nnffini + 
                   this.nnffin;
       }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeInutilizacao.getPrefix(): " + ex.getMessage());
           return null;
       }
    }
    
    private String getSoapEnvelope(String Arquivo) 
    {
        try
        {
            String envelope = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            envelope += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
            envelope += "<soap12:Header>";
            envelope += "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2\">";
            envelope += "<cUF>{0}</cUF>";
            envelope += "<versaoDados>{1}</versaoDados>";
            envelope += "</nfeCabecMsg>";
            envelope += "</soap12:Header>";
            envelope += "<soap12:Body>";
            envelope += "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2\">{2}</nfeDadosMsg>";
            envelope += "</soap12:Body>";
            envelope += "</soap12:Envelope>";

            String env = MessageFormat.format(envelope, this.cuf.getCodigo(), //this.cUF, 
                                                        this.versao.getVersao(), //"2.00", 
                                                        Util.loadFromFile(Arquivo));

            //arruma envelope
            env = env.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
            env = env.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            env = env.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            env = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + env;

            return env;
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeInutilizacao.getSoapEnvelope(): " + ex.getMessage());
           return null;
        }
    }
    
    public void Executar() 
    {
        try
        {
            /* classes para gerar o xml */
            TInutNFe inutNFe = new TInutNFe();
            TInutNFe.InfInut infInut = new TInutNFe.InfInut();

            /* vincula as classes */
            inutNFe.setInfInut(infInut);            

            /* passa os paramentos */
            inutNFe.setVersao(this.versao.getVersao()); //2.00
            
            infInut.setAno(this.ano); //2 caracteres
            infInut.setCNPJ(this.cnpj);
            infInut.setCUF(String.valueOf(this.cuf.getCodigo()));
            infInut.setId(getId());
            infInut.setMod(this.mod);
            infInut.setSerie(this.serie);
            infInut.setNNFIni(this.nnffini);
            infInut.setNNFFin(this.nnffin);
            infInut.setTpAmb(this.tpamb.getTpAmb());
            infInut.setXJust(this.xjust);
            infInut.setXServ("INUTILIZAR");        
            
            /* gera nome dos arquivos de retorno */
            String fileName = c.getConfiguracoes().getPATH_NFE() + getPrefix() + "-ped-inu.xml";
            String fileRetorno = c.getConfiguracoes().getPATH_NFE() + getPrefix() + "-inu.xml";
            
            /* gera xml com base na classe */
            JAXBContext context = JAXBContext.newInstance(TInutNFe.class);
            Marshaller m = context.createMarshaller();
            JAXBElement<TInutNFe> element = new ObjectFactory().createInutNFe(inutNFe);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            
            StringWriter sw = new StringWriter();
            m.marshal(element, sw);
            
            /* grava o xml em arquivo */
            String texto = sw.toString();
            texto = texto.replace("<InutNFe ", "<inutNFe ").replace("</InutNFe>", "</inutNFe>");
            Util.SaveToFile(texto, fileName);

            /* assina */
            Util.AssinarInutilizacao(fileName);
            
            /* remove tags */
            String temp = Util.loadFromFile(fileName);
            temp = temp.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
            temp = temp.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            temp = temp.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            Util.SaveToFile(temp, fileName);
            
            /* envelopa */
            String envelope = getSoapEnvelope(fileName);
            Util.SaveToFile(envelope, "C:/ENVELOPE.xml"); //salva para depuracao
            
            /* envia */
            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/NFeInutilizacao2.asmx");
            //String url = new GetWebService().getURL(this.cuf, this.tpamb, this.versao, Servicos.INUTILIZACAO);
            String retorno = invoke(envelope, Servicos.INUTILIZACAO);
            Util.SaveToFile(retorno, "C:/TESTE_INUT.xml");
            
            /* desenvelopa o retorno */
            retorno = GetFieldsXML.getMensagem(retorno);   
            retorno = retorno.replace("<nfeInutilizacaoNF2Result xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeInutilizacao2\">", "");
            retorno = retorno.replace("</nfeInutilizacaoNF2Result>", "");
            
            /* grava retorno */
            Util.SaveToFile(retorno, fileRetorno);

            /* le retorno */
            LerRetorno(fileRetorno);
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao executar NfeInutilizacao: " + ex.getMessage());
        }
    }
    
    private void LerRetorno(String Arquivo)
    {
        try
        {
            SAXBuilder b = new SAXBuilder();
            Document doc = b.build(new File(Arquivo));
            
            Element root = doc.getRootElement();
            
            if (root != null)
            {
                Element inf = root.getChild("infInut", root.getNamespace());
                
                this.Retorno.tpAmb = Util.getValue(inf, "tpAmb");
                this.Retorno.verAplic = Util.getValue(inf, "verAplic");
                this.Retorno.cStat = Util.getValue(inf, "cStat");
                this.Retorno.xMotivo = Util.getValue(inf, "xMotivo");
                this.Retorno.cUF = Util.getValue(inf, "cUF");
                this.Retorno.dhRecbto = Util.getValue(inf, "dhRecbto");                
            }
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NfeInutilizacao.LerRetorno: " + ex.getMessage());
        }
    }
    
}
