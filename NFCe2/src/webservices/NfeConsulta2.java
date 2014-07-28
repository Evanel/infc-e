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
import iNFe.Configuracao;
import java.io.File;
import java.text.MessageFormat;
import nfe.util.GetFieldsXML;
import nfe.util.Log;
import nfe.util.Util;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import webservices.Retornos.RetornoConsulta;
import webservices.inf.infProt;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo de arquivo de retorno
  
  <retConsSitNFe versao="2.01" xmlns="http://www.portalfiscal.inf.br/nfe">
    <tpAmb>2</tpAmb>
    <verAplic>RS20131127084734</verAplic>
    <cStat>100</cStat>
    <xMotivo>Autorizado o uso da NF-e</xMotivo>
    <cUF>43</cUF>
    <chNFe>43131201600275000124550010000009881000009123</chNFe>
    <protNFe versao="2.00">
        <infProt Id="ID143130006708093">
            <tpAmb>2</tpAmb>
            <verAplic>RS20131129092745</verAplic>
            <chNFe>43131201600275000124550010000009881000009123</chNFe>
            <dhRecbto>2013-12-02T15:18:03</dhRecbto>
            <nProt>143130006708093</nProt>
            <digVal>Rgci3VDneckA3js7dVtzqI4zEyo=</digVal>
            <cStat>100</cStat>
            <xMotivo>Autorizado o uso da NF-e</xMotivo>
        </infProt>
    </protNFe>
 </retConsSitNFe>
 
 */
public class NfeConsulta2 extends WebServices {
    /*
     * Consulta Situacao Atual da NF-e
     * 
     * Metodo: nfeConsultaNF2
     * Schema Envio: consSitNFe_v2.00.xsd
     * Schema Retorno: retConsSitNFe_v2.00.xsd 
     */

    public RetornoConsulta Retorno;            
    
    private String chave;
    
    public NfeConsulta2() {
        this.Retorno = new RetornoConsulta();
    }
    
    public void setChave(String chave) {
        this.chave = chave;
    }
    
    //public void setNFe2(NFe2 nfe2) {
    //    this.nfe2 = nfe2;
    //}

    public void Executar() 
    {
        try
        {
            String msg = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            msg += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
            msg += "<soap12:Header>";
            msg += "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\">";
            msg += "<cUF>{0}</cUF>";
            msg += "<versaoDados>{1}</versaoDados>";
            msg += "</nfeCabecMsg>";
            msg += "</soap12:Header>";
            msg += "<soap12:Body>";
            msg += "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\">";
            msg += "<consSitNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"{2}\">";
            msg += "<tpAmb>{3}</tpAmb>";
            msg += "<xServ>CONSULTAR</xServ>";
            msg += "<chNFe>{4}</chNFe>";
            msg += "</consSitNFe>";
            msg += "</nfeDadosMsg>";
            msg += "</soap12:Body>";
            msg += "</soap12:Envelope>"; 

            String envelope = MessageFormat.format(msg, this.cuf.getCodigo(), //this.nfe2.ide.getCUF(),
                                                        this.versao.getVersao(), //this.nfe2.getVersao(),
                                                        this.versao.getVersao(), //"2.01"
                                                        this.tpamb.getTpAmb(), //this.nfe2.ide.getTpAmb(),
                                                        this.chave // this.nfe2.infNFe.getId().substring(3)
                                                );

            //String envelope = MessageFormat.format(msg, this.chave);

            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/Nfeconsulta/NFeConsulta2.asmx");
            //String url = new GetWebService().getURL(cuf, tpamb, versao, Servicos.CONSULTA_PROTOCOLO);
            String retorno = invoke(envelope, Servicos.CONSULTA_PROTOCOLO);

            if (retorno != null) 
            {
                //String arquivoRetorno = new Configuracao().getPATH_NFE() + nfe2.infNFe.getId().substring(3) + "-ped.sit.xml";
                String arquivoRetorno = Configuracao.getInstance().getConfiguracoes().getPATH_NFE() + chave + "-ped-sit.xml";

                String msgRetorno = GetFieldsXML.getMensagem(retorno);

                if ( (!msgRetorno.equals("") ) && (msgRetorno != null) )
                {
                    msgRetorno = msgRetorno.replace("<nfeConsultaNF2Result xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\">", "").replace("</nfeConsultaNF2Result>","");
                    Util.SaveToFile(msgRetorno, arquivoRetorno);

                    try
                    {                                                  
                        //recupera retorno
                        File f = new File(arquivoRetorno);

                        SAXBuilder b = new SAXBuilder();
                        Document doc = b.build(f);

                        Element root = doc.getRootElement();

                        this.Retorno.cStat = Util.getValue(root, "cStat");
                        this.Retorno.cUF = Util.getValue(root, "cUF");
                        this.Retorno.chNFe = Util.getValue(root, "chNFe");
                        this.Retorno.tpAmb = Util.getValue(root, "tpAmb");
                        this.Retorno.verAplic = Util.getValue(root, "verAplic");
                        this.Retorno.xMotivo = Util.getValue(root, "xMotivo");

                        //if ( (this.cStat.equals("100")) || (this.cStat.equals("110")) )/
                        Element prot = (Element)root.getChild("protNFe", root.getNamespace());

                        if (prot != null) 
                        {
                            Element inf = (Element)prot.getChild("infProt", root.getNamespace());

                            if (inf != null)
                            {                        
                                this.Retorno.infProt = new infProt();

                                this.Retorno.infProt.cStat = Util.getValue(inf, "cStat");
                                this.Retorno.infProt.chNFe = Util.getValue(inf, "chNFe");
                                this.Retorno.infProt.dhRecbto = Util.getValue(inf, "dhRecbto");
                                this.Retorno.infProt.digVal = Util.getValue(inf, "digVal");
                                this.Retorno.infProt.id = Util.getValue(inf, "id");
                                this.Retorno.infProt.nProt = Util.getValue(inf, "nProt");
                                this.Retorno.infProt.xMotivo = Util.getValue(inf, "xMotivo");
                            }
                        }

                    }
                    catch(Exception ex) 
                    {
                        Log.getInstance().addErro("Erro em NfeConsulta.LerRetorno: " + ex.getMessage());
                    }
                }  

            }
            else
                Log.getInstance().addErro("O WebService NfeConsulta2 não retornou dados.");
        }catch(Exception ex) {
           Log.getInstance().addErro("Erro em NfeConsulta.Executar(): " + ex.getMessage());
        }
    }
    

    
}
