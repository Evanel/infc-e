package assinar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import nfe.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Assinador {

    private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
    private static final String PROVIDER_CLASS_NAME = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
    private static final String PROVIDER_NAME = "jsr105Provider";

    public void assinar(String caminhoXml, String caminhoCertificado, String senha, String caminhoXmlNovo, String tipo) throws Exception {

        /*
         * Operação:
         * 1 - NFE 
         * 2 - CANCELAMENTO 
         * 3 - INUTILIZAÇÃO
         * 4 - EVENTO CANCELAMENTO NFe
         */

        String tag = "";
        switch (tipo) {
            case "1":
                tag = "infNFe";
                break;
            case "2":
                tag = "infCanc";
                break;
            case "3":
                tag = "infInut";
                break;
            case "4":
                tag = "infEvento";
                break;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document docs = builder.parse(new File(caminhoXml));
        NodeList elements = docs.getElementsByTagName(tag);
        Element el = (Element) elements.item(0);
        String id = el.getAttribute("Id");
        
        //para testes
        el.setIdAttribute("Id", true); 
        //isso aki pra corrigir alguns erros q dah em versoes do java
        //nas versoes atuais do java (> u45) tah dando erro. Rodei com u11
        //e u13/15 e funfa.... 
        
        
        // Cria um DOM do Tipo XMLSignatureFactory que será utilizado
        // para gerar a assinatura envelopada (eneveloped signature)
        String providerName = System.getProperty(PROVIDER_NAME, PROVIDER_CLASS_NAME);
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());

        // Define os Algoritimos de Transformação
        ArrayList transformList = new ArrayList();
        TransformParameterSpec tps = null;
        Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
        Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
        transformList.add(envelopedTransform);
        transformList.add(c14NTransform);

        // Cria objeto Reference
        Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);

        // Cria o elemento SignedInfo
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                Collections.singletonList(ref));


        // Carrega o keyStore e obtem a chave do certificado
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream(caminhoCertificado), senha.toCharArray());
        Enumeration aliasesEnum = ks.aliases();
        String alias = "";
        while (aliasesEnum.hasMoreElements()) {
            alias = (String) aliasesEnum.nextElement();

            if (ks.isKeyEntry(alias)) {

                break;
            }
        }

        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));

        // Instancia um certificado do tipo X509
        X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

        // Cria o elemento KeyInfo contendo a X509Data;
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        List x509Content = new ArrayList();
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        // Instancia o documento que será assinado
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(caminhoXml));

        // Cria o DOMSignContext especificando a chave e o nó pai
        DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());
        
        // Cria a XMLSignature, mas não assina ainda
        XMLSignature signature = fac.newXMLSignature(si, ki);

        // Empacota (marshal), gera e assina
        signature.sign(dsc);

        // Arquivo novo Assinado
        OutputStream os = new FileOutputStream(caminhoXmlNovo);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));

        // Encontra o elemente Signature
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

        if (nl.getLength() == 0) {
            //throw new Exception("Cannot find Signature element");
            Log.getInstance().addErro("Impossível encontrar elemento Signature.");
        }

        // Cria um DOMValidateContext
        DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(0));
     
        // Desempacota (unmarshal) a XMLsignature
        XMLSignature signatures = fac.unmarshalXMLSignature(valContext);

        // Validate the XMLSignature.
        boolean coreValidity = signatures.validate(valContext);

        //Checa o status da Validação
        if (coreValidity == false) {
            Log.getInstance().addErro("Falha na Assinatura!");
        } else {
            Log.getInstance().addMensagem("Assinatura Correta!");
        }
    }

    public static boolean main(String[] args) throws Exception {

        if (args.length != 5) {
            Log.getInstance().addErro("Sao esperados 5 parametros!");
            return false;
        }

        String caminhoXml = args[0];
        String caminhoCertificado = args[1];
        String senha = args[2];
        String arquivoXmlNovo = args[3];
        String tipo = args[4];

        File file = new File(caminhoXml);

        if (!file.exists()) {
            Log.getInstance().addErro("Arquivo " + caminhoXml + " não encontrado!");
            return false;
        }
        file = new File(caminhoCertificado);
        if (!file.exists()) {
            Log.getInstance().addErro("Arquivo " + caminhoCertificado + " não encontrado!");
            return false;
        }
        try {
            Assinador t = new Assinador();
            t.assinar(caminhoXml, caminhoCertificado, senha, arquivoXmlNovo, tipo);
            Log.getInstance().addMensagem("Arquivo xml assinado com sucesso: " + caminhoXml + "!");
            return true;
        } catch (Exception e) {
            Log.getInstance().addErro("Erro ao tentar assinar arquivo xml! \n\n" + e.toString());
            return false;
        }
    }
}
