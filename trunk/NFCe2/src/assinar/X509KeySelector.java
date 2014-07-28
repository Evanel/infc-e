package assinar;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.keyinfo.*;

public class X509KeySelector extends KeySelector {

    private KeyStore ks;

    public X509KeySelector(KeyStore keyStore) throws KeyStoreException {
        if (keyStore == null) {
            throw new NullPointerException("keyStore is null");
        }
        this.ks = keyStore;
        this.ks.size();
    }

    public KeySelectorResult select(KeyInfo keyInfo,
            KeySelector.Purpose purpose,
            AlgorithmMethod method,
            XMLCryptoContext context)
            throws KeySelectorException {
        Iterator ki = keyInfo.getContent().iterator();
        while (ki.hasNext()) {
            XMLStructure info = (XMLStructure) ki.next();
            if (!(info instanceof X509Data)) {
                continue;
            }
            X509Data x509Data = (X509Data) info;
            Iterator xi = x509Data.getContent().iterator();
            while (xi.hasNext()) {
                Object o = xi.next();
                if (!(o instanceof X509Certificate)) {
                    continue;
                }
                final PublicKey key = ((X509Certificate) o).getPublicKey();
                if (algEquals(method.getAlgorithm(), key.getAlgorithm())) {
                    return new KeySelectorResult() {

                        public Key getKey() {
                            return key;
                        }
                    };
                }
            }
        }
        throw new KeySelectorException("No key found!");
    }

    static boolean algEquals(String algURI, String algName) {
        if ((algName.equalsIgnoreCase("DSA")
                && algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1))
                || (algName.equalsIgnoreCase("RSA")
                && algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1))) {
            return true;
        } else {
            return false;
        }
    }
}
