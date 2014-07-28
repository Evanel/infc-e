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

import idanfenfce.Util;
import java.text.MessageFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Vargas
 */
public class ParametrosNfeQrCode {
    
    private String chNFe = "";
    private String nVersao = "";
    private String tpAmb = "";
    private String cDest = "";
    private String dhEmi = "";
    private String vNF = "";
    private String vICMS = "";
    private String digVal = "";

    public String getcDest() {
        return (cDest != null) ? Util.removeAcentos(cDest) : "";
    }

    public void setcDest(String cDest) {
        this.cDest = cDest;
    }

    public String getChNFe() {
        return (chNFe != null) ? chNFe.substring(3) /* remove o NFe */ : "";
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe;
    }

    public String getDhEmi() {
        return (dhEmi != null) ? UtilSec.stringHexa( dhEmi.getBytes() ) : "";
    }

    public void setDhEmi(String dhEmi) {
        this.dhEmi = dhEmi;
    }

    public String getDigVal() {
        return (digVal != null) ? UtilSec.stringHexa( digVal.getBytes() ) : "";
    }

    public void setDigVal(String digVal) {
        this.digVal = digVal;
    }

    public String getnVersao() {
        return "100"; //POR ENQUANTO EH ACEITO SOMENTE ESSE VALOR NO SEFAZ
        /*
            * switch (nVersao) 
        {
            case "2.00":
                return "200";
            case "3.00":
                return "300";
            case "3.10":
                return "310";
            default:
                return "200";
        }*/
    }

    public void setnVersao(String nVersao) {
        this.nVersao = nVersao;
    }

    public String getTpAmb() {
        return tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getvICMS() {
        return vICMS;
    }

    public void setvICMS(String vICMS) {
        this.vICMS = vICMS;
    }

    public String getvNF() {
        return vNF;
    }

    public void setvNF(String vNF) {
        this.vNF = vNF;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("chNFe={0}"); 
        sb.append("&nVersao={1}");
        sb.append("&tpAmb={2}");
        sb.append("&cDest={3}");
        sb.append("&dhEmi={4}");  
        sb.append("&vNF={5}");
        sb.append("&vICMS={6}");
        sb.append("&digVal={7}");
        //sb.append("&cIdToken={8}");
        //sb.append("&cHashQRCode={9}"); 

        String s = MessageFormat.format(sb.toString(), getChNFe(),
                                                        getnVersao(),
                                                        getTpAmb(),
                                                        getcDest(),
                                                        getDhEmi(),
                                                        getvNF(),
                                                        getvICMS(),
                                                        getDigVal() );

        s = s.replace("&cDest=&", "&");

        return s;
    }
    
}
