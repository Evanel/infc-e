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
import java.io.File;
import java.text.MessageFormat;
import nfe.util.GetFieldsXML;
import nfe.util.Log;
import nfe.util.Util;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import webservices.Retornos.RetornoStatusServico;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo retorno 
 
  <?xml version="1.0" encoding="utf-8"?>
  <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <soap:Header>
        <nfeCabecMsg xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2">
            <cUF>43</cUF>
            <versaoDados>2.00</versaoDados>
        </nfeCabecMsg>
    </soap:Header>
    <soap:Body>
        <nfeStatusServicoNF2Result xmlns="http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2">
            <retConsStatServ versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">
                <tpAmb>2</tpAmb>
                <verAplic>RS20131126155321</verAplic>
                <cStat>107</cStat>
                <xMotivo>Servico em Operacao</xMotivo>
                <cUF>43</cUF>
                <dhRecbto>2013-12-04T10:47:39</dhRecbto>
                <tMed>1</tMed>
            </retConsStatServ>
        </nfeStatusServicoNF2Result>
    </soap:Body>
  </soap:Envelope>

 */
public class NfeStatusServico2 extends WebServices{
    /*
     * Consulta Status do Servico
     * 
     * Metodo: nfeStatusServicoNF2;
     * Schema Envio: consStatServ_v2.00.xsd
     * Schema Retorno: retConsStatServ_v2.00.xsd
     */
    
    public RetornoStatusServico Retorno;
    
    public NfeStatusServico2() {
        this.Retorno = new RetornoStatusServico();
    }

    private String getSoapMsg() 
    {
        try
        {
            StringBuilder msg = new StringBuilder();

            msg.append("<consStatServ xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"{0}\">")
            .append("<tpAmb>{1}</tpAmb>")
            .append("<cUF>{2}</cUF>")
            .append("<xServ>STATUS</xServ>")
            .append("</consStatServ>");

            return MessageFormat.format(msg.toString(), this.versao.getVersao(),
                                                        this.tpamb.getTpAmb(), 
                                                        this.cuf.getCodigo());
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NfeStatusServico.getSoap(): " + ex.getMessage());
            return null;
        }
    }
    
    private String getSoapEnvelope() 
    {
        try
        {
            StringBuilder soapMessage = new StringBuilder();

            soapMessage.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                    .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">")
                    .append("<soap12:Header>")
                    .append("<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">")
                    .append("<cUF>{0}</cUF>")
                    .append("<versaoDados>{1}</versaoDados>")
                    .append("</nfeCabecMsg>")
                    .append("</soap12:Header>")
                    .append("<soap12:Body>")
                    .append("<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">")
                    .append(getSoapMsg())
                    .append("</nfeDadosMsg>")
                    .append("</soap12:Body>")
                    .append("</soap12:Envelope>");

            return MessageFormat.format(soapMessage.toString(), this.cuf.getCodigo(), 
                                                                this.versao.getVersao());
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NfeStatusServico.getSoapEnvelope(): " + ex.getMessage());
            return null;
        }
    }
    
    public void Executar() 
    {
        try
        {
            //Configuracao c = new Configuracao();
            String arquivoEnviar = this.c.getConfiguracoes().getPATH_NFE() + getPrefixName() + "-ped-sta.xml";

            /* salva o arquivo com o conteudo da msg a ser enviada */
            String msgSoap = getSoapMsg();
            Util.SaveToFile(msgSoap, arquivoEnviar);

            /* retorna a mensagem envelopada */
            String envSoap = getSoapEnvelope();
            
            /* salva msg envelopada somente para depuracao */
            Util.SaveToFile(envSoap, this.c.getConfiguracoes().getPATH_NFE() + getPrefixName() + "-ped-sta-envelope.xml" );

            /* envia para o sefaz */
            //String retorno = WebServices.Invoke(envSoap, "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx"); 
            //String url = new GetWebService().getURL(this.cuf, this.tpamb, this.versao, Servicos.STATUS_SERVICO);
            String retorno = invoke(envSoap, Servicos.STATUS_SERVICO); 

            if (retorno != null)
            {
                /* gera o nome do arquivo de retorno conforme o manual */
                String arquivoRetorno = this.c.getConfiguracoes().getPATH_NFE() + getPrefixName() + "-sta.xml";

                /* recebe a msg de retorno, desenvelopa e retira tags desnecessarias */
                String msg = GetFieldsXML.getMensagem(retorno);
                msg = msg.replace("<nfeStatusServicoNF2Result xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">", "");
                msg = msg.replace("</nfeStatusServicoNF2Result>", "");

                /* grava o arquivo com a msg de retorno */
                Util.SaveToFile(msg, arquivoRetorno);

                /* copia msg de retorno para as variaveis da classe */
                LerRetorno(arquivoRetorno);
            }
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NfeStatusServico.Executar(): " + ex.getMessage());
            return;
        }
    }
    
    private void LerRetorno(String Arquivo) 
    {
        try
        {
            SAXBuilder b = new SAXBuilder();
            Document doc = b.build(new File(Arquivo));
            
            Element root = doc.getRootElement();
  
            this.Retorno.cStat = Util.getValue(root, "cStat");
            this.Retorno.cUF = Util.getValue(root, "cUF");
            this.Retorno.dhRecbto = Util.getValue(root, "dhRecbto");
            this.Retorno.tMed = Util.getValue(root, "tMed");
            this.Retorno.tpAmb = Util.getValue(root, "tpAmb");
            this.Retorno.verAplic = Util.getValue(root, "verAplic");
            this.Retorno.xMotivo = Util.getValue(root, "xMotivo");
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro em NfeStatusServico.LerRetorno(): " + ex.getMessage());
        }
    }
    
    private String getPrefixName() 
    {
        return Util.getDataAtualFormatStr("yyyyMMdd") + "T" + Util.getDataAtualFormatStr("hhmmss");
    }
        
    
    
}
