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

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import jp.sourceforge.qrcode.QRCodeDecoder;


/**
 *
 * @author Ivan Vargas
 */
public class iQrCode {

    private int size = 220;
    private Retorno retorno;
    private String pathImagem = "";
    private String mensagem = "";
    
    public Retorno getRetorno() {
        if (this.retorno == null) {
            this.retorno = new Retorno();
        }
        return this.retorno;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String decode() {
        try
        {
            QRCodeDecoder decoder = new QRCodeDecoder();
            byte[] bytes = decoder.decode(new MyImage(this.pathImagem));

            String result = new String(bytes);
            System.out.println(result);
            return result;
            
        }catch(Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            this.getRetorno().addErro("Erro em decode(): " + ex.getMessage() );
            return null;
        }
    }

    public boolean encode() {

        Charset charset = Charset.forName("ISO-8859-1");
        CharsetEncoder encoder = charset.newEncoder();
        byte[] b = null;
        try {
            // Convert a string to ISO-8859-1 bytes in a ByteBuffer
            java.nio.ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(this.mensagem));
            b = bbuf.array();
        } catch (CharacterCodingException e) {
            System.out.println(e.getMessage());
            this.getRetorno().addErro("Erro em encode(): " + e.getMessage() );
            return false;
        }

        String data = null;
        try {
            data = new String(b, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            this.getRetorno().addErro("Erro em encode(): " + e.getMessage() );
            return false;
        }

        // get a byte matrix for the data
        BitMatrix matrix = null;
        int h = this.size;
        int w = this.size;
        com.google.zxing.Writer writer = new QRCodeWriter();
        try {
            matrix = writer.encode(data,
                    com.google.zxing.BarcodeFormat.QR_CODE, w, h);
        } catch (com.google.zxing.WriterException e) {
            System.out.println(e.getMessage());
            this.getRetorno().addErro("Erro em encode(): " + e.getMessage() );
            return false;
        }

        File file = new File(this.pathImagem);
        try {
            MatrixToImageWriter.writeToFile(matrix, "BMP", file); 
            System.out.println("printing to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            this.getRetorno().addErro("Erro em encode(): " + e.getMessage() );
            return false;
        }
        
        return true;
    }
}
