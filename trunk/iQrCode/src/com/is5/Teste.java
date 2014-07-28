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
package com.is5;

import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Vargas
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("iQrCode - Gerador/leitor de QrCode\n\n");
        sb.append("Este .jar se utiliza de classes do projeto QrCode e ZXing.\n");
        sb.append("Como a utilização destas classes é um pouco complexa\n");
        sb.append("resolvi empacotar tudo em um .jar juntamente com uma\n");
        sb.append("classe para facilitar a utilizacao das mesmas.\n\n");
        sb.append("Com a classe iQrCode basta chamar os metodos encode()\n");
        sb.append(" e decode() para gerar e ler Qrcode, respectivamente;)\n\n");
        sb.append("Ivan S. Vargas\nivan@is5.com.br\n\n");
        sb.append("Site: www.is5.com.br\n");
        sb.append("Twitter: @isvargas\n");
        sb.append("Skype: ivan_is5\n");
        //sb.append("e-mail: devbrasil.net/profiles/IvanVargas");
       
        try
        {
            JOptionPane.showMessageDialog(null, sb.toString());
        }catch(Exception ex) {
            System.out.println(sb.toString());
        }
               
        /*
        
        iQrCode qr = new iQrCode();
        
        qr.setSize(125);
        qr.setMensagem("ISSO EH UMA MENSAGEM DE TESTE");
        qr.setPathImagem("C:/QRTESTE.JPG");
        
        if ( qr.encode() )
            System.out.println("QR GERADO COM SUCESSO!");
        else
            System.out.println(qr.getRetorno());

        String r = qr.decode();
        
        System.out.println("Mensagem: " + r);
        * 
        */
        
    }
}
