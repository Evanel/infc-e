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
package com.is5.Danfe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Vargas
 */
public class Danfe {
    
    private Emitente emitente;
    private String data;
    private String hora;
    private String tipoEmissao; //para consumidor, estabelecimento, etc
    private List<Item> itens;
    private String valorTotal;
    private List<FormaPag> formasPagamento;
    private String totalTributos;
    private String urlConsulta;
    private String chaveAcesso;
    private Consumidor consumidor;
    private String ambiente;
    
    private String numero;
    private String serie;
    private String dataHoraEmissao;
    private String protocolo;
    private String dataHoraProtocolo;
    private String tipoVia;
    
    private String vDesc;
    private String vOutro;
    
    private String troco;
        
    private String arquivoXML;

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getvDesc() {
        return vDesc;
    }

    public void setvDesc(String vDesc) {
        this.vDesc = vDesc;
    }

    public String getvOutro() {
        return vOutro;
    }

    public void setvOutro(String vOutro) {
        this.vOutro = vOutro;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }
    
    
    public Danfe()
    {
        this.data = "";
        this.hora = "";
        this.tipoEmissao = "";
        this.valorTotal = "";
        this.totalTributos = "";
        this.urlConsulta = "";
        
        
        this.itens = new ArrayList<>();
        this.formasPagamento = new ArrayList<>();
    }
    
    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipoEmissao() {
        return tipoEmissao;
    }

    public void setTipoEmissao(String tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public Emitente getEmitente() {
        return emitente;
    }

    public void setEmitente(Emitente emitente) {
        this.emitente = emitente;
    }

    public List<FormaPag> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPag> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public String getTotalTributos() {
        return totalTributos;
    }

    public void setTotalTributos(String totalTributos) {
        this.totalTributos = totalTributos;
    }

    public String getUrlConsulta() {
        return urlConsulta;
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public void setDataHoraEmissao(String dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public String getDataHoraProtocolo() {
        return dataHoraProtocolo;
    }

    public void setDataHoraProtocolo(String dataHoraProtocolo) {
        this.dataHoraProtocolo = dataHoraProtocolo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getArquivoXML() {
        return arquivoXML;
    }

    public void setArquivoXML(String arquivoXML) {
        this.arquivoXML = arquivoXML;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }
        
    
    
    
}
