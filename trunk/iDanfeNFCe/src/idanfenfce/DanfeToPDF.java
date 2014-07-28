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
package idanfenfce;

import QrCode.GerarUrlQrCode;
import com.is5.Danfe.Danfe;
import com.is5.Danfe.FormaPag;
import com.is5.Danfe.Item;
import com.is5.iQrCode;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Ivan Vargas
 */
public class DanfeToPDF {
    
    private Retorno retorno;
    
    private String pathPDF;
    private Danfe danfe;
    private String urlConsulta;
    private String idToken;
    private String token;
    
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    private int sizeQrCode = 220;
    
    public void setSizeQrCode(int size) {
        this.sizeQrCode = size;
    }

    public Retorno getRetorno() {
        if (retorno == null) {
            retorno = new Retorno();
        }
        return retorno;
    }
    
    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
    
    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }

    public void setDanfe(Danfe danfe) {
        this.danfe = danfe;
    }

    public void setPathPDF(String pathPDF) {
        this.pathPDF = pathPDF;
    }


    public boolean gerarPdf() {
        
        try
        {
            Rectangle ret = new Rectangle(220,800);
            //Document doc = new Document(PageSize.A5, 0, 0, 0, 0); //Document(ret);
            Document doc = new Document(ret, 0, 0, 0, 0);
            
            //cria o stream de saida
            OutputStream os = new FileOutputStream(this.pathPDF);
            
            //associa o stream de saida ao doc
            PdfWriter.getInstance(doc, os);
            
            //abre o documento
            doc.open();
            
            //especifico o tipo de fonte
            Font f = new Font(Font.COURIER, 7);
            Font fBold = new Font(Font.COURIER, 7, Font.BOLD);
            
            //adiciona elementos ao doc
            Paragraph linha = new Paragraph("----------------------------------------------------", f);
                      
            
            //EMITENTE
            Paragraph p1 = new Paragraph(danfe.getEmitente().getRazaoSocial(), fBold);
            Paragraph p2 = new Paragraph("CNPJ: " + danfe.getEmitente().getCnpj() + "   IE: " + danfe.getEmitente().getIe(), f);
            Paragraph p3 = new Paragraph(danfe.getEmitente().getEndereco().toString(), f);
            Paragraph p4 = new Paragraph(danfe.getDataHoraEmissao(), f);
            
            p1.setAlignment(Element.ALIGN_LEFT);
            p2.setAlignment(Element.ALIGN_LEFT);
            p3.setAlignment(Element.ALIGN_LEFT);
            p4.setAlignment(Element.ALIGN_LEFT);
            
            doc.add(p1);
            doc.add(p2);
            doc.add(p3);
            doc.add(p4);
            
            doc.add(linha);
            
            //NFCE
            Paragraph p5 = new Paragraph("DANFE NFC-e - Documento Auxiliar da Nota Fiscal Eletrônica para Consumidor Final" ,fBold);
            p5.setAlignment(Element.ALIGN_CENTER);
            p5.setSpacingAfter(5);
            //p5.setSpacingBefore(3);
            doc.add(p5);
            
            Paragraph p6 = new Paragraph("NFC-e não permite aproveitamento de crédito ICMS.", f);
            p6.setAlignment(Element.ALIGN_CENTER);
            doc.add(p6);
            
            //Paragraph p7 = new Paragraph(danfe.getTipoEmissao(), f);
            //p7.setAlignment(Element.ALIGN_CENTER);
            //p7.setSpacingAfter(3);
            //doc.add(p7);
            
            doc.add(linha);
            
            //ITENS CABECALHO
            //PdfPTable table = new PdfPTable(5);
            PdfPTable table = new PdfPTable(new float[] { 0.2f, 0.3f, 0.1f, 0.2f, 0.2f}); //percentual witdh cell 0.1 = 10, 0.2 = 20..
            Paragraph pCod = new Paragraph("CÓDIGO", f);
            table.addCell( getCellSemBordas(pCod) ); 
                
            Paragraph pDesc = new Paragraph("DESCRIÇÃO", f);
            table.addCell( getCellSemBordas(pDesc) ); 
            
            Paragraph pQtd = new Paragraph("QTD", f);
            PdfPCell cQtd = new PdfPCell(pQtd);
            cQtd.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cQtd.setBorder(0);
            table.addCell(cQtd); 
            
            Paragraph pUnit = new Paragraph("V UNIT", f);
            PdfPCell cUnit = new PdfPCell(pUnit);
            cUnit.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cUnit.setBorder(0);
            table.addCell(cUnit);
            
            Paragraph pTot = new Paragraph("V TOTAL", f);
            PdfPCell cTot = new PdfPCell(pTot);
            cTot.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cTot.setBorder(0);
            table.addCell(cTot);
            
            table.setWidthPercentage(100.0f); //100% da largura da pagina
            doc.add(table);
            
            doc.add(linha);
            
            //ITENS DESCRICAO
            for(Item i : danfe.getItens()) 
            {
                //essa tabela representa a primeira linha. Nela add somente
                //cod e descricao do item. A descricao com colspan = 4
                PdfPTable t1 = new PdfPTable(new float[] { 0.2f, 0.3f, 0.1f, 0.2f, 0.2f}); //percentual witdh cell 0.1 = 10, 0.2 = 20..
                            
                Paragraph c = new Paragraph(i.getCodigo(), f);
                t1.addCell( getCellSemBordas(c) ); 
                
                Paragraph d = new Paragraph(i.getDescricao(), f);
                PdfPCell cDescricao = new PdfPCell(d);
                cDescricao.setColspan(4);
                cDescricao.setBorder(0);
                t1.addCell(cDescricao);
                
                //add a primeira linha da descricao (codigo, descricao)
                t1.setWidthPercentage(100.0f); //100% da largura da pagina
                doc.add(t1);

                //essa tabela representa a segunta linha da descricao
                //esta comecando na terceira celula e mostrar somente qtd, unit e total
                PdfPTable t2 = new PdfPTable(new float[] { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f}); //percentual witdh cell 0.1 = 10, 0.2 = 20..
                
                Paragraph espaco = new Paragraph("", f);
                PdfPCell cEsp = new PdfPCell(espaco);
                cEsp.setColspan(2);
                cEsp.setBorder(0);
                t2.addCell(cEsp);
                
                Paragraph q = new Paragraph(String.valueOf(i.getQtd()), f);
                PdfPCell q2 = new PdfPCell(q);
                q2.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                q2.setBorder(0);
                t2.addCell(q2); 

                Paragraph u = new Paragraph(String.valueOf(i.getVlUnit()), f);
                PdfPCell u2 = new PdfPCell(u);
                u2.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                u2.setBorder(0);
                t2.addCell(u2);
                
                Paragraph t = new Paragraph(String.valueOf(i.getVlTotal()), f);
                PdfPCell t3 = new PdfPCell(t);
                t3.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                t3.setBorder(0);
                t2.addCell(t3);  
                
                //add a segunda linha da descricao (qtd, unit, total)
                t2.setWidthPercentage(100.0f); //100% da largura da pagina
                doc.add(t2);
            }
            
            doc.add(linha);
            
            //TOTAL E FORMAS PAGAMENTO
            PdfPTable table2 = new PdfPTable(2);
            table2.addCell( getCellSemBordas(new Paragraph("Quantidade Total de Itens", f)) );
            
            Integer qtdTotal = danfe.getItens().size();
            PdfPCell cqt = new PdfPCell( new Paragraph(String.valueOf(qtdTotal), f) );
            cqt.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cqt.setBorder(0);
            table2.addCell(cqt);
            
            //mostra o total de descontos, se houver...
            if ( danfe.getvDesc() != null)
            {
                try
                {
                   if (!danfe.getvDesc().equals("0.00"))
                   {
                       table2.addCell( getCellSemBordas(new Paragraph("Descontos", f)) );
                       
                       PdfPCell vDesc = new PdfPCell( new Paragraph(danfe.getvDesc(), f) );
                       vDesc.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                       vDesc.setBorder(0);
                       table2.addCell(vDesc);
                   }
                    
                }catch(Exception ex) {
                    System.out.println("Erro em retornar vDesc: " + ex.getMessage());
                }
            }
            
            //mostra o total de acrescimos, se houver...
            if ( danfe.getvOutro() != null)
            {
                try
                {
                   if (!danfe.getvOutro().equals("0.00"))
                   {
                       table2.addCell( getCellSemBordas(new Paragraph("Acréscimos", f)) );
                       
                       PdfPCell vOutro = new PdfPCell( new Paragraph(danfe.getvOutro(), f) );
                       vOutro.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                       vOutro.setBorder(0);
                       table2.addCell(vOutro);
                   }
                    
                }catch(Exception ex) {
                    System.out.println("Erro em retornar vOutro: " + ex.getMessage());
                }
            }
            
            table2.addCell( getCellSemBordas(new Paragraph("VALOR TOTAL R$", f)) );
            
            PdfPCell cti = new PdfPCell( new Paragraph(danfe.getValorTotal(), f) );
            cti.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cti.setBorder(0);
            table2.addCell(cti);
            
            table2.addCell( getCellSemBordas(new Paragraph("FORMA PAGAMENTO", f)) );
            
            PdfPCell hVp = new PdfPCell( new Paragraph("VALOR PAGO R$", f) );
            hVp.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            hVp.setBorder(0);
            table2.addCell(hVp);
            
            for(FormaPag fp : danfe.getFormasPagamento()) {
                PdfPCell fpDescricao = new PdfPCell( new Paragraph(fp.getDescricao(), f) );
                fpDescricao.setHorizontalAlignment(Cell.ALIGN_LEFT);
                fpDescricao.setBorder(0);
                table2.addCell(fpDescricao); 
                
                PdfPCell fpValor = new PdfPCell( new Paragraph(String.valueOf(fp.getValor()), f) );
                fpValor.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                fpValor.setBorder(0);
                table2.addCell(fpValor);
            }
            
            //ESPACO
            PdfPCell spaco = new PdfPCell( new Paragraph("") );
            spaco.setColspan(2);
            spaco.setBorder(0);
            table2.addCell(spaco);
           
            //TROCO?
            if (danfe.getTroco() != null)
            {
                if ( (!danfe.getTroco().equals("")) && (danfe.getTroco().equals("0.00")) )
                {
                    table2.addCell( getCellSemBordas(new Paragraph("Troco", f)) ); 
                    PdfPCell cTroco = new PdfPCell( new Paragraph(danfe.getTroco(), f) );
                    cTroco.setHorizontalAlignment(Cell.ALIGN_RIGHT);
                    cTroco.setBorder(0);
                    table2.addCell(cTroco); 
                }
            }
            
            table2.setWidthPercentage(100.0f); //100% da largura da pagina
            doc.add(table2);
            
            doc.add(linha);
            
            //TOTAL TRIBUTOS
            PdfPTable table3 = new PdfPTable(new float[] { 0.9f, 0.1f});
            table3.addCell( getCellSemBordas(new Paragraph("Tributos Totais Incidentes (Lei Federal 12.741/2012):", f)) ); 
            PdfPCell cTrib = new PdfPCell( new Paragraph(String.valueOf(danfe.getTotalTributos()), f) );
            cTrib.setHorizontalAlignment(Cell.ALIGN_RIGHT);
            cTrib.setBorder(0);
            table3.addCell(cTrib);
            
            table3.setWidthPercentage(100.0f); //100% da largura da pagina
            doc.add(table3);
            
            doc.add(linha);
            
            //AREA MENSAGEM FISCAL
            Paragraph tpEmis = new Paragraph(danfe.getTipoEmissao() ,fBold);
            tpEmis.setAlignment(Element.ALIGN_CENTER);
            tpEmis.setSpacingAfter(3);
            //pAmbiente.setSpacingBefore(3);
            doc.add(tpEmis);
            
            Paragraph pNumSerie = new Paragraph("Número: " + danfe.getNumero() 
                                                           + "   Série: " + danfe.getSerie() 
                                                           + "   Emissão: " + danfe.getDataHoraEmissao() 
                                                           + " - " + danfe.getTipoVia(), f);
            pNumSerie.setAlignment(Element.ALIGN_CENTER);
            doc.add(pNumSerie);
            
            Paragraph pLink = new Paragraph("Consulte pela Chave de Acesso em: " + danfe.getUrlConsulta() ,f);
            pLink.setAlignment(Element.ALIGN_CENTER);
            doc.add(pLink);
            
            Paragraph pca = new Paragraph("CHAVE DE ACESSO" ,fBold);
            pca.setAlignment(Element.ALIGN_CENTER);
            doc.add(pca);
            
            Paragraph pChave = new Paragraph(danfe.getChaveAcesso() ,f);
            pChave.setAlignment(Element.ALIGN_CENTER);
            //pChave.setSpacingAfter(3);
            //pChave.setSpacingBefore(5);
            doc.add(pChave);
            
           
            //CONSUMIDOR
            doc.add(linha);
            Paragraph pConsumidor = new Paragraph("CONSUMIDOR", fBold);
            pConsumidor.setAlignment(Element.ALIGN_CENTER);
            doc.add(pConsumidor);
            doc.add(linha);

            if (danfe.getConsumidor() == null) 
            {
                Paragraph pNaoIdentificado = new Paragraph("Consumidor não identificado", f);
                pNaoIdentificado.setAlignment(Element.ALIGN_CENTER);
                doc.add(pNaoIdentificado);
            }
            else
            {
                Paragraph pDocNome = new Paragraph("CNPJ/CPF/ID Estrangeiro - " + danfe.getConsumidor().getCnpjCpfId() + "     " + danfe.getConsumidor().getNome(), f);
                pDocNome.setAlignment(Element.ALIGN_CENTER);
                doc.add(pDocNome);
                
                Paragraph pEndereco = new Paragraph(danfe.getConsumidor().getEndereco().toString(), f);
                pEndereco.setAlignment(Element.ALIGN_CENTER);
                doc.add(pEndereco);
            }
            
            doc.add(linha);
            
            //QRCODE
            try
            {
                GerarUrlQrCode g = new GerarUrlQrCode();                
                g.setArquivoXML(danfe.getArquivoXML());
                g.setUrlConsulta(this.urlConsulta);
                g.setIdToken(this.idToken);
                g.setToken(this.token);
                g.setRetorno(this.retorno);
                
                String url = g.getURL(); //gera a URL para o QrCode
                
                if (url != null)
                {           
                    String pathQrCode = "C:/" + danfe.getChaveAcesso() + "_QRCODE.jpg";
                    
                    iQrCode qrCode = new iQrCode();
                    qrCode.setMensagem(url);
                    qrCode.setSize(this.sizeQrCode);
                    qrCode.setPathImagem(pathQrCode);
                    
                    if (qrCode.encode())
                    {                    
                        Paragraph Consulta = new Paragraph("Consulta via leitor de Qr Code", f);
                        Consulta.setAlignment(Element.ALIGN_CENTER);
                        Consulta.setSpacingBefore(3);
                        Consulta.setSpacingAfter(5);
                        doc.add(Consulta);

                        Image img = Image.getInstance(pathQrCode);
                        img.setAlignment(Element.ALIGN_CENTER);
                        //img.setWidthPercentage(10f); 
                        //img.setBorderWidth(0.1f);
                        doc.add(img); 
                        
                        System.out.println("URL: " + qrCode.decode() );
                        
                    }
                }
            }catch(Exception ex) {
                System.out.println("Erro ao gerar QrCode: " + ex.getMessage());
                this.getRetorno().addErro( "Erro ao gerar QrCode: " + ex.getMessage() + "\n" );
            }
            
            Paragraph Protocolo = new Paragraph("Protocolo de Autorização: " + danfe.getProtocolo() + "     " + danfe.getDataHoraProtocolo(), f);
            Protocolo.setAlignment(Element.ALIGN_CENTER);
            Protocolo.setSpacingBefore(5);
            doc.add(Protocolo);
            
            //fechamento
            doc.close();
            os.close();
            
            return true;
            
        }catch(Exception ex) {
            System.out.println("Erro em importarXML(): " + ex.getMessage());
            this.getRetorno().addErro( "Erro em importarXML(): " + ex.getMessage() + "\n" );
            return false;
        }
    }
    
    private PdfPCell getCellSemBordas(Paragraph p) {       
        PdfPCell c = new PdfPCell(p);
        c.setBorder(0);
        return c;
    }
}
