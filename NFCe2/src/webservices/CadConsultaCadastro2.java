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
import br.inf.portalfiscal.nfe.TConsCad;
import br.inf.portalfiscal.nfe.TUfCons;
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
import webservices.Retornos.RetornoCadConsultaCadastro;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo arquivo de retorno 
 
 <?xml version="1.0" encoding="utf-8"?>
 <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <soap:Header>
        <nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2">
            <cUF>43</cUF>
            <versaoDados>2.00</versaoDados>
        </nfeCabecMsg>
    </soap:Header>
    <soap:Body>
        <consultaCadastro2Result xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2">
            <retConsCad versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">
                <infCons>
                    <verAplic>RSb20130125134156</verAplic>
                    <cStat>111</cStat>
                    <xMotivo>Consulta cadastro com uma ocorrencia</xMotivo>
                    <UF>RS</UF>
                    <CNPJ>00000000000000</CNPJ>
                    <dhCons>2013-12-04T14:55:17</dhCons>
                    <cUF>43</cUF>
                    <infCad>
                        <IE>0000000000</IE>
                        <CNPJ>00000000000000</CNPJ>
                        <UF>RS</UF>
                        <cSit>1</cSit>
                        <indCredNFe>2</indCredNFe>
                        <indCredCTe>4</indCredCTe>
                        <xNome>EMPRESA DE TESTE LTDA</xNome>
                        <xRegApur>SIMPLES NACIONAL</xRegApur>
                        <CNAE>0000000</CNAE>
                        <dIniAtiv>1997-01-01</dIniAtiv>
                        <dUltSit>1997-01-30</dUltSit>
                        <ender>
                            <xLgr>ENDERECO</xLgr>
                            <nro>123</nro>
                            <xBairro>CENTRO</xBairro>
                            <cMun>4311502</cMun>
                            <xMun>Lavras do Sul</xMun>
                            <CEP>97390000</CEP>
                        </ender>
                    </infCad>
                </infCons>
            </retConsCad>
        </consultaCadastro2Result>
    </soap:Body>
  </soap:Envelope>

 */
public class CadConsultaCadastro2 extends WebServices {
    /*
     * Consulta Cadastro
     * 
     * Metodo: consultaCadastro2
     * Schema Envio: consCad_v2.00.xsd
     * Schema Retorno: retConsCad_v2.00.xsd
     */
    
   private String cnpj;
   private String ie;
   private String uf;

   public void setCnpj(String cnpj) {
       this.cnpj = cnpj;
   }

   public void setIe(String ie) {
       this.ie = ie;
   }

   public void setUf(String uf) {
       this.uf = uf;
   }
   
   public RetornoCadConsultaCadastro Retorno;
   
   public CadConsultaCadastro2() {
       this.Retorno = new RetornoCadConsultaCadastro();
   }
   
   private String getSoapEnvelope(String Arquivo) 
   {
       try
       {
            String envelope = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            envelope += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
            envelope += "<soap12:Header>";
            envelope += "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2\">";
            envelope += "<cUF>{0}</cUF>";
            envelope += "<versaoDados>{1}</versaoDados>";
            envelope += "</nfeCabecMsg>";
            envelope += "</soap12:Header>";
            envelope += "<soap12:Body>";
            envelope += "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2\">{2}</nfeDadosMsg>";
            envelope += "</soap12:Body>";
            envelope += "</soap12:Envelope>";

            envelope = MessageFormat.format(envelope, this.cuf.getCodigo(), //"43", 
                                                        this.versao.getVersao(), //"2.00", 
                                                        Util.loadFromFile(Arquivo));

            return envelope;
       
       }catch(Exception ex) {
            Log.getInstance().addErro("Erro em CadConsultaCadastro.getSoapEnvelope(): " + ex.getMessage());
            return null;
       }
   }
   
   public void Executar() 
   {
       try
       {
            /* classes para gerar o arquivo */
            TConsCad consCad = new TConsCad();
            TConsCad.InfCons infCons = new TConsCad.InfCons();

            /* passa paramentos para infCons */
            infCons.setCNPJ(this.cnpj);
            //infCons.setIE(this.IE);
            infCons.setUF(TUfCons.RS);
            infCons.setXServ("CONS-CAD");

            /* passa paramentos para consCad */
            consCad.setVersao(this.versao.getVersao()); //2.00

            /* vincula as classes */
            consCad.setInfCons(infCons);

            /* gera nome para arquivos de envio e retorno */
            String prefix = Util.getDataAtualFormatStr("yyyyMMddhhmmss");
            String fileName = c.getConfiguracoes().getPATH_NFE() + prefix + "-ped-cad.xml"; //nome inventado...nao achei a declaracao
            String fileRetorno = c.getConfiguracoes().getPATH_NFE() + prefix + "-cad.xml"; //tmbm inventado

            /* gera xml */
            JAXBContext context = JAXBContext.newInstance(TConsCad.class);
            Marshaller m = context.createMarshaller();
            JAXBElement<TConsCad> element = new ObjectFactory().createConsCad(consCad);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            
            /* joga o conteudo em xml para sw */
            StringWriter sw = new StringWriter();
            m.marshal(element, sw);
            
            /* grava no arquivo de envio */
            String temp = sw.toString();
            //temp = temp.replace("<ConsCad", "<consCad").replace("</ConsCad>", "</consCad>"); 
            Util.SaveToFile(temp, fileName);
            
            String soapEnv = getSoapEnvelope(fileName);
            
            /* salva para depuracao */
            Util.SaveToFile(soapEnv, "C:/ENVELOPE.xml");
            
            /* chama ws */
            //String retorno = WebServices.Invoke(soapEnv, "https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx");
            //String url = new GetWebService().getURL(this.cuf, this.tpamb, this.versao, Servicos.CONSULTA_CADASTRO);
            String retorno = invoke(soapEnv, Servicos.CONSULTA_CADASTRO);
                        
            /* recebe, trata e grava retorno */
            retorno = GetFieldsXML.getMensagem(retorno);
            retorno = retorno.replace("<consultaCadastro2Result xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/CadConsultaCadastro2\">", "");
            retorno = retorno.replace("</consultaCadastro2Result>", "");
            
            Util.SaveToFile(retorno, fileRetorno);
            
            /* recupera valores para as propriedades da classe */
            LerRetorno(fileRetorno);
            
            
       }catch(Exception ex) {
           Log.getInstance().addErro("Erro ao executar CadConsultaCadastro: " + ex.getMessage());
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
                Element infcons = root.getChild("infCons", root.getNamespace());
                
                this.Retorno.verAplic = Util.getValue(infcons, "verAplic");
                this.Retorno.cStat = Util.getValue(infcons, "cStat");
                this.Retorno.xMotivo = Util.getValue(infcons, "xMotivo");
                this.Retorno.UF = Util.getValue(infcons, "UF");
                this.Retorno.CNPJ = Util.getValue(infcons, "CNPJ");
                this.Retorno.dhCons = Util.getValue(infcons, "dhCons");
                this.Retorno.cUF = Util.getValue(infcons, "cUF");
                
                Element infcad = infcons.getChild("infCad", root.getNamespace());
                
                if(infcad != null)
                {
                    this.Retorno.infCad.CNAE = Util.getValue(infcad, "CNAE");
                    this.Retorno.infCad.CNPJ = Util.getValue(infcad, "CNPJ");
                    this.Retorno.infCad.CPF = Util.getValue(infcad, "CPF");
                    this.Retorno.infCad.IE = Util.getValue(infcad, "IE");
                    this.Retorno.infCad.IEAtual = Util.getValue(infcad, "IEAtual");
                    this.Retorno.infCad.IEUnica = Util.getValue(infcad, "IEUnica");
                    this.Retorno.infCad.UF = Util.getValue(infcad, "UF");
                    this.Retorno.infCad.cSit = Util.getValue(infcad, "cSit");
                    this.Retorno.infCad.dBaixa = Util.getValue(infcad, "dBaixa");
                    this.Retorno.infCad.dIniAtiv = Util.getValue(infcad, "dIniAtiv");
                    this.Retorno.infCad.dUltSit = Util.getValue(infcad, "dUltSit");
                    this.Retorno.infCad.indCredCTe = Util.getValue(infcad, "indCredCTe");
                    this.Retorno.infCad.indCredNFe = Util.getValue(infcad, "indCredNFe");
                    this.Retorno.infCad.xFant = Util.getValue(infcad, "xFant");
                    this.Retorno.infCad.xNome = Util.getValue(infcad, "xNome");
                    this.Retorno.infCad.xRegApur = Util.getValue(infcad, "xRegApur");
                                
                    Element ender = infcad.getChild("ender", root.getNamespace());

                    if (ender != null)
                    {
                        this.Retorno.infCad.ender.CEP = Util.getValue(ender, "CEP");
                        this.Retorno.infCad.ender.UF = Util.getValue(ender, "UF");
                        this.Retorno.infCad.ender.cMun = Util.getValue(ender, "cMun");
                        this.Retorno.infCad.ender.cPais = Util.getValue(ender, "cPais");
                        this.Retorno.infCad.ender.fone = Util.getValue(ender, "fone");
                        this.Retorno.infCad.ender.nro = Util.getValue(ender, "nro");
                        this.Retorno.infCad.ender.xBairro = Util.getValue(ender, "xBairro");
                        this.Retorno.infCad.ender.xCpl = Util.getValue(ender, "xCpl");
                        this.Retorno.infCad.ender.xLgr = Util.getValue(ender, "xLgr");
                        this.Retorno.infCad.ender.xMun = Util.getValue(ender, "xMun");
                        this.Retorno.infCad.ender.xPais = Util.getValue(ender, "xPais");      
                    }
                }    
            }
       }
       catch(Exception ex) {
           Log.getInstance().addErro("Erro em CadConsultaCadastro.LerRetorno(): " + ex.getMessage());
       }
   }
    
}
