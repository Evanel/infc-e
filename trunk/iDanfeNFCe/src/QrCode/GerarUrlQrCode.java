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
package QrCode;

import idanfenfce.Retorno;
import idanfenfce.Util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Ivan Vargas
 */
public class GerarUrlQrCode {
    
    private String arquivoXML;
    private String arquivoDestino;
    private String urlConsulta;
    private String idToken;
    private String Token;
    
    private String cnpjEmitente;
    private String tpAmb;
    
    private Retorno retorno;
    
    public Retorno getRetorno() {
        if (retorno == null) {
            retorno = new Retorno();
        }
        return retorno;
    }
    
    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
    
    public void setArquivoDestino(String arquivoDestino) {
        this.arquivoDestino = arquivoDestino;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public void setArquivoXML(String arquivoXML) {
        this.arquivoXML = arquivoXML;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }
    
    public GerarUrlQrCode() {
        
    }
        
    public String getURL() 
    {
         getAmbienteCnpj();
         
         /*
            URL formada que dever� ser inclu�da no QR Code: 

            https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx 
            
            chNFe=43120910585504000174650010000000541123456781                  //chave da Nfe
            &nVersao=100                                                        //versao, sem pontos. Dese ser 100
            &tpAmb=1                                                            //ambiente
            &cDest=43708379006485                                               //doc ident. consumidor: CNPJ/CPF/IDEstrangeiro. Se o cliente nao foi identificao, nao existira este parametro
            &dhEmi=323031322d30392d32375431363a32303a33342d30333a3030           //Data/Hora de emissao em hexadecimal do valor UTC COM MASCARA
            &vNF=1000.00                                                        //valor total da NFe
            &vICMS=180.00                                                       //valor total ICMS
            &digVal=366d5a696a6a6d4e664f6d6e7a57596844654e6f463832757332513d    //DigestValue da NFC-e em hexadecimal
            &cIdToken=000001                                                    //O  token  corresponde  a  um  c�digo  de  seguran�a  alfanum�rico  de  conhecimento apenas da Secretaria da Fazenda do Estado do emitente e do pr�prio contribuinte. 
            &cHashQRCode=139801B934956C6D0FC2C976568C66F6D612EBF9               //o hash dessa URL
        
            TOKEN
            -----
            Exemplo de Token para ambiente de homologa��o, considerando uma empresa 
            que possua o CNPJ 43.708.379/0064-85: 
            
            1. Token 1:  IdToken: 000001 Token: 4370837920130001 
            2. Token 2:  IdToken: 000002 Token: 4370837920130002
            
            Onde:     43708379 -> primeiros 8 digitos do CNPJ
                      2013     -> ano
                      0002     -> ultimos 4 digitos do IdToken 
            
            Sendo assim, cIdToken=0000014370837920130001 //para o primeiro caso                     
                      
            Para ambiente de producao, entrar em contato com o SEFAZ para obter o
            token para a empresa do emitente!
        
        *   HASH QRCODE
        *   -----------
        * 
        *   Parametros da URL -> SHA-1 -> Hexadecimal -> 40 bytes
        * 
        * 
        */        
        
        ParametrosNfeQrCode p = getParametros(this.arquivoXML);
        
        if ( (this.urlConsulta != null) && (p != null) )
        {
            //gera a URL para gerar o hash do qr code (observe o idToken + token)
            String parametros = p.toString().trim() + "&cIdToken=" + getIdToken().trim() + getToken().trim(); //aki vaki ID+TOKEN pra assinar, depois, na URL vai soh o ID do token
            
            System.out.println("Parametros: " + parametros);
            
            //gera o hash
            String hashQrCode = UtilSec.strToSHA1(parametros); //UtilSec.strToSHA1(parametros) ;
           
            System.out.println("Hash dos parametros: " + hashQrCode);
            
            //retorna a url de consulta (somente com id do token), com o hash
            String url = this.urlConsulta.trim() + '?' + p.toString().trim() 
                                                       + "&cIdToken=" + getIdToken().trim()
                                                       + "&cHashQRCode=" + hashQrCode.toUpperCase().trim();
            
            System.out.println("URL CONSULTA: " + url);
            
            return url;
        }                                                          
        else        
            return null;
    }
        
    private String getToken() {
        
        if (this.Token != null) 
        {
            return this.Token;
        }
        else
        {
            String Token = "";
            /*
                TOKEN
                -----
                Exemplo de Token para ambiente de homologa��o, considerando uma empresa 
                que possua o CNPJ 43.708.379/0064-85: 

                1. Token 1:  IdToken: 000001 Token: 4370837920130001 
                2. Token 2:  IdToken: 000002 Token: 4370837920130002

                Onde:     43708379 -> primeiros 8 digitos do CNPJ
                        2013     -> ano
                        0002     -> ultimos 4 digitos do IdToken 

                Sendo assim, cIdToken=0000014370837920130001 //para o primeiro caso                     

                Para ambiente de producao, entrar em contato com o SEFAZ para obter o
                token para a empresa do emitente!
            */

            if (this.tpAmb.equals("2")) //HOMOLOGACAO 
            { 
                String ano = "";
                String IdToken = "";

                SimpleDateFormat out = new SimpleDateFormat("yyyy");  

                ano = out.format(new Date());             
                IdToken = getIdToken(); 

                Token = IdToken.trim() + 
                        this.cnpjEmitente.substring(0, 8) + 
                        ano.trim() + 
                        IdToken.substring(2).trim(); /* ultimos 4 digitos dos 6 */

            }
            else
            {
                /* PRODUCAO */            
            }

            //JOptionPane.showMessageDialog(null, "Toke: ["+Token+"]");

            return Token;
        }
    }
    
    private String getIdToken() {
        
        if (this.idToken != null) 
        {
            return this.idToken;
        }
        else
        {
            if (this.tpAmb.equals("2"))
                return "000001";
            else
                return this.idToken; //"000001"; //jogar isso nas configuracoes depois
        }
    }
    
    private ParametrosNfeQrCode getParametros(String ArquivoXML) {
        
        ParametrosNfeQrCode p = new ParametrosNfeQrCode();
        
        try
        {
            SAXBuilder b = new SAXBuilder();
            Document doc = b.build(new File(ArquivoXML));
            
            Element root = doc.getRootElement(); //se nfeProc
                
                Element nfe = root.getChild("NFe", root.getNamespace());
                Element infNfe = nfe.getChild("infNFe", root.getNamespace());
            
            //Element infNfe = root.getChild("infNFe", root.getNamespace());
            p.setChNFe( infNfe.getAttributeValue("Id",infNfe.getName()) );
            p.setnVersao( infNfe.getAttributeValue("versao", infNfe.getName() ));
            
            Element ide = infNfe.getChild("ide",infNfe.getNamespace());
            p.setTpAmb( Util.getValue(ide, "tpAmb") );
            p.setDhEmi( Util.getValue(ide, "dhEmi") );
            
            Element dest = infNfe.getChild("dest", infNfe.getNamespace());
            if (dest != null)  //em NFC-e o destinario pode ser ignorado
            {
                String c = Util.getValue(dest, "CNPJ");
                
                if (!c.equals(""))  //pode ser CNPJ ou CPF
                    p.setcDest(c);
                else
                    p.setcDest( Util.getValue(dest, "CPF") );                                    
            }
            
            Element total = infNfe.getChild("total", infNfe.getNamespace());
            Element icmstot = total.getChild("ICMSTot", total.getNamespace());
            p.setvICMS( Util.getValue(icmstot, "vICMS") );
            p.setvNF( Util.getValue(icmstot, "vNF") );
            
            Element signature = root.getChild("Signature", root.getNamespace("http://www.w3.org/2000/09/xmldsig#"));
            if (signature != null)
            {
                Element signedinfo = signature.getChild("SignedInfo",signature.getNamespace());
                Element reference = signedinfo.getChild("Reference", signature.getNamespace());
                p.setDigVal( Util.getValue(reference, "DigestValue") );
            }
            
        }catch(Exception ex) {
            p = null;
            this.getRetorno().addErro( "Erro ao gerar parametros: " + ex.getMessage() );               
        }
        
        return p;
    }
    
    private void getAmbienteCnpj() 
    {
        try
        {
            SAXBuilder b = new SAXBuilder();
            Document doc = b.build(new File(this.arquivoXML));
            
            org.jdom2.Element root = doc.getRootElement(); //nfeProc
            
                org.jdom2.Element nfe = root.getChild("NFe", root.getNamespace());
                org.jdom2.Element infNFe = nfe.getChild("infNFe", root.getNamespace());
            
            //org.jdom2.Element infNFe = root.getChild("infNFe", root.getNamespace());
            org.jdom2.Element ide = infNFe.getChild("ide", root.getNamespace());
            org.jdom2.Element emit = root.getChild("emit", root.getNamespace());
            
            this.tpAmb = Util.getValue(ide, "tmAmb");
            this.cnpjEmitente = Util.getValue(emit, "CNPJ");
            
        }catch(Exception ex) {
            this.getRetorno().addErro( "Erro em getAmbienteCnpj(): " + ex.getMessage() );        
            
        }
        
    }
    
}
