/*******************************************************************************
* Projeto iNFC-e                                                               *
* Emissao de NFC-e em Java                                                     *
*                                                                              *
* Direitos Autorais Reservados (c) 2014 Ivan S. Vargas                         *
*                                                                              *
*  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto iNFC-e   *
* localizado em https://code.google.com/p/infc-e/                              *
*                                                                              *
*  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la *
* sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  *
* Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) *
* qualquer vers�o posterior.                                                   *
*                                                                              *
*  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   *
* NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      *
* ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor*
* do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              *
*                                                                              *
*  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto*
* com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  *
* no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          *
* Voc� tamb�m pode obter uma copia da licen�a em:                              *
* http://www.opensource.org/licenses/lgpl-license.php                          *
*                                                                              *
*        Ivan S. Vargas  -  ivan@is5.com.br  -  http://www.is5.com.br          *
*                                                                              *
********************************************************************************/
package nfce;

import java.io.*;
import java.net.URL;
import java.security.Security;
import javax.xml.soap.*;

/**
 *
 * @author Ivan Vargas
 */
public class Principal {

    public static void main(String[] args) {
        
        StringBuilder soapEnvelope = new StringBuilder();

        soapEnvelope.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
        .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">")
        .append("<soap12:Header>")
        .append("<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">")
        .append("<cUF>43</cUF>")
        .append("<versaoDados>2.00</versaoDados>")
        .append("</nfeCabecMsg>")
        .append("</soap12:Header>")
        .append("<soap12:Body>")
        .append("<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">")
        .append("<consStatServ xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.00\">")
        .append("<tpAmb>2</tpAmb>")
        .append("<cUF>43</cUF>")
        .append("<xServ>STATUS</xServ>")
        .append("</consStatServ>")
        .append("</nfeDadosMsg>")
        .append("</soap12:Body>")
        .append("</soap12:Envelope>");
        
       //AcessarWs("https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx", soapEnvelope.toString() );
        
       AcessarWsComAutenticacao("https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx", soapEnvelope.toString() );
    }
    
    private static void AcessarWs(String urlAddress, String envelope) 
    {
        try 
        {
            /* Url do WebService */
            URL url = new URL(urlAddress);

            /* tipo de mensagem: SOAP */
            MimeHeaders header = new MimeHeaders();
            header.addHeader("Content-Type", "application/soap+xml");
            
            MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SOAPMessage message;
            
            /* monta a mensagem SOAP */
            message = factory.createMessage(header, new ByteArrayInputStream(envelope.getBytes()));

            /* instancia uma conexao SOAP */
            SOAPConnection conSoap = SOAPConnectionFactory.newInstance().createConnection();

            /* Envia a mensagem SOAP ao WebService */
            SOAPMessage resWs = conSoap.call(message, url);

            /* Mostra a mensagem de retorno */
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            resWs.writeTo(out);
            System.out.println("out :\n" + out.toString());

            
        } catch (Exception ex) {
            /*
            * Se ocorreu erro de SOAP, imprimo a mensagem
            */
            System.out.println("" + ex.getMessage());
        }        
    }
    
    private static void AcessarWsComAutenticacao(String urlAddress, String envelope) {
        
        try
        {
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            /*
            * limpo propriedades
            */
            System.clearProperty("javax.net.ssl.keyStore");
            System.clearProperty("javax.net.ssl.keyStorePassword");
            System.clearProperty("javax.net.ssl.trustStore");

            /*
            * dados do certificado do cliente
            */
            System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
            System.setProperty("javax.net.ssl.keyStore", "D:/is5/JAVA/GatewayNFe/cert/PAPIONFULL.pfx");
            System.setProperty("javax.net.ssl.keyStorePassword", "nino");

            /*
            * dados do certificado do servidor
            */
            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
            System.setProperty("javax.net.ssl.trustStore", "D:/is5/JAVA/GatewayNFe/cert/NFeCacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            
            /* Url do WebService */
            URL url = new URL(urlAddress);

            /* tipo de mensagem: SOAP */
            MimeHeaders header = new MimeHeaders();
            header.addHeader("Content-Type", "application/soap+xml");
            
            MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SOAPMessage message;
            
            /* monta a mensagem SOAP */
            message = factory.createMessage(header, new ByteArrayInputStream(envelope.getBytes()));

            /* instancia uma conexao SOAP */
            SOAPConnection conSoap = SOAPConnectionFactory.newInstance().createConnection();

            /* Envia a mensagem SOAP ao WebService */
            SOAPMessage resWs = conSoap.call(message, url);

            /* Mostra a mensagem de retorno */
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            resWs.writeTo(out);
            System.out.println("out :\n" + out.toString());
            
        }catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage() );
        }
        
    }
}
