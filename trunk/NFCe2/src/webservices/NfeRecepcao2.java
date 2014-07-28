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
//import com.sun.xml.internal.ws.util.Pool;
import iNFe.Configuracao;
import iNFe.NFe2;
import java.io.File;
import java.text.MessageFormat;
import nfe.util.GetFieldsXML;
import nfe.util.Log;
import nfe.util.Util;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import webservices.Retornos.RetornoRecepcao;

/**
 *
 * @author Ivan Vargas
 */

/* Exemplo arquivo de retorno 
 
 <retEnviNFe versao="2.00" xmlns="http://www.portalfiscal.inf.br/nfe">
    <tpAmb>2</tpAmb>
    <verAplic>RS20131127114833</verAplic>
    <cStat>103</cStat>
    <xMotivo>Lote recebido com sucesso</xMotivo>
    <cUF>43</cUF>
    <dhRecbto>2013-12-02T11:28:47</dhRecbto>
    <infRec>
        <nRec>431000023700471</nRec>
        <tMed>1</tMed>
    </infRec>
 </retEnviNFe>
 
 */
public class NfeRecepcao2 extends WebServices {
    /*
     * Transmissao de Lote de NF-e
     * 
     * Metodo: nfeRecepcaoLote2
     * Schema Envio: enviNFe_v2.00.xsd
     * Schema Retorno: retEnviNFe_v2.00.xsd
     */
    
    public RetornoRecepcao Retorno;
    
    private NFe2 nfe2;
   
    public void setNFe(NFe2 nfe2) 
    {
        this.nfe2 = nfe2;
        
    }
    
    public NfeRecepcao2() {
        this.Retorno = new RetornoRecepcao();
    }
    
    public boolean Executar() 
    {
        try
        {
            String msg = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                        + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                        + "<soap12:Header>"
                        + "<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao\">"
                        + "<cUF>{0}</cUF>"
                        + "<versaoDados>{1}</versaoDados>"
                        + "</nfeCabecMsg>"
                        + "</soap12:Header>"
                        + "<soap12:Body>"
                        + "<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao\">{2}</nfeDadosMsg>"
                        + "</soap12:Body>"
                        + "</soap12:Envelope>";
            
            String envelope = MessageFormat.format(msg, this.cuf.getCodigo(), //nfe2.ide.getCUF(), 
                                                        this.versao.getVersao(), //"3.10" /*nfe2.getVersao()*/, 
                                                        nfe2.xmlValidado);
            
            /* remove indicacao de xml q soh deve ter uma por arquivo */
            envelope = envelope.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            envelope = envelope.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
            envelope = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + envelope.trim();
               
            Util.SaveToFile(envelope, "C:/TESTE.XML");
            
            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx");
            //String retorno = WebServices.Invoke(envelope, "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx");
            //String url = new GetWebService().getURL(this.cuf, this.tpamb, this.versao, Servicos.RECEPCAO);
            String retorno = invoke(envelope, Servicos.RECEPCAO);
            
            /***** BUSCA RETORNO **********************************************/            
            if (retorno != null) 
            {
                //String arquivoRetorno = nfe2.getNomeArquivo().replace("-nfe.xml", "-ret.xml");
                String arquivoRetorno = Configuracao.getInstance().getConfiguracoes().getPATH_NFE() + nfe2.getLote().trim() + "-rec.xml";
                
                /* trata o retorno para gravar em arquivo, tirando do envelope SOAP */
                retorno = GetFieldsXML.getMensagem(retorno);
                retorno = retorno.replace("<nfeAutorizacaoLoteResult xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao\">", "");
                retorno = retorno.replace("</nfeAutorizacaoLoteResult>", "");
                
                Util.SaveToFile(retorno, arquivoRetorno);
                
                try
                {
                    File f = new File(arquivoRetorno);

                    SAXBuilder b = new SAXBuilder();
                    Document doc = b.build(f);

                    Element root = (Element) doc.getRootElement();

                    this.Retorno.cStat = Util.getValue(root, "cStat");
                    this.Retorno.cUF = Util.getValue(root, "cUF");
                    this.Retorno.dhRecbto = Util.getValue(root, "dhRecbto");
                    this.Retorno.tpAmb = Util.getValue(root, "tpAmb");
                    this.Retorno.verAplic = Util.getValue(root, "verAplic");
                    this.Retorno.xMotivo = Util.getValue(root, "xMotivo");

                    Element inf = (Element)root.getChild("infRec", root.getNamespace());

                    this.Retorno.infRec.nRec = Util.getValue(inf, "nRec");
                    this.Retorno.infRec.tMed = Util.getValue(inf, "tMed");
                 
                    return true;
                    
                }catch(Exception ex) {
                    Log.getInstance().addErro("Erro ao buscar Retorno em NfeRecepcao: " + ex.getMessage() + "\nResposta salva em \"" + arquivoRetorno + "\"");
                    return false;
                }                
                
            }            
            /***** FIM RETORNO ************************************************/
            else
            {
                Log.getInstance().addErro("O WebService NfeRecepcao não retornou dados.");
                return false;
            }
        }
        catch(Exception e) 
        {
            Log.getInstance().addErro("Erro ao executar NfeRecepcao: " + e.getMessage());
            return false;
        }
    }
    
    
}
