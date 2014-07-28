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
package Enums;

/**
 * Classe que mantém as CONSTANTS necessárias para acessar
 * os servidores do SEFAZ.
 *
 * Lista de WebServices em Ambiente de Produção: www.nfe.fazenda.gov.br
 * Lista de WebServices em Ambiente de Homologação: www.hom.nfe.fazenda.gov.br
 *
 * UF que utilizam a SVAN - Sefaz Virtual do Ambiente Nacional: ES, MA, PA, PI
 * UF que utilizam a SVRS - Sefaz Virtual do RS:
 * - Para serviço de Consulta Cadastro: AC, RN, PB, SC
 * - Para demais serviços relacionados com o sistema da NF-e: AC, AL, AP, DF, PB, RJ, RN, RO, RR, SC, SE, TO 
 * 
 * Ou utilizar a seguinte URL, por ex: http://www.portalfiscal.inf.br/nfe/wsdl/NfeAutorizacao 
 *                                     http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2
 * 
 * Desenvolvido por: 
 * Ivan Vargas
 * ivan@is5.com.br
 */
public class NFCeConstants {
    
    public static final String COD_PAIS = "1058";
    public static final String PAIS = "BRASIL";
    
    public static class TIPO_AMBIENTE {
        public static final String PRODUCAO = "1";
        public static final String HOMOLOGACAO = "2";
    }
    
    public static class VERSAO {
        public static final String V200 = "2.00";
        public static final String V300 = "3.00";
        public static final String V310 = "3.10";
    }
    
    public static class MODELO {
        public static final String NFe = "55";
        public static final String NFCe = "65";
    }
    
    public static class TIPO_IMPRESSAO {
        public static final String SEM_GERACAO_DANFE = "0";
        public static final String DANFE_NORMAL_RETRATO = "1";
        public static final String DANFE_NORMAL_PAISAGEM = "2";
        public static final String DANFE_SIMPLIFICADO = "3";
        public static final String DANFE_NFCe = "4"; //o tipo geralmente usado
        public static final String DANFE_NFCe_EM_MENSAGEM_ELETRONICA = "5"; //utilizar tipo 5 qdo esta for a unica forma de emissao do DANFE
    }
    
    public static class IDENTIFICADOR_DESTINO {
        public static final String OPERACAO_INTERNA = "1";
        public static final String OPERACAO_INTERESTADUAl = "2";
        public static final String OPERACAO_EXTERIOR = "3";
    }
    
    public static class INDICADOR_OPERACAO_COM_CONSUMIDOR_FINAL {
        public static final String NAO = "0";
        public static final String CONSUMIDOR_FINAL = "1";
    }
    
    public static class INDICADOR_PRESENCA_CONSUMIDOR {
        public static final String NAO_SE_APLICA = "0";
        public static final String OPERACAO_PRESENCIAL = "1";
        public static final String OPERACAO_NAO_PRESENCIAL_INTERNET = "2";
        public static final String OPERACAO_NAO_PRESENCIAL_TELEATENDIMENTO = "3";
        public static final String OPERACAO_NAO_PRESENCIAL_OUTROS = "9";                
    }
    
    public static class FORMA_PAGAMENTO {
        public static final String DINHEIRO = "01";
        public static final String CHEQUE = "02";
        public static final String CARTAO_CREDITO = "03";
        public static final String CARTAO_DEBITO = "04";
        public static final String CREDITO_LOJA = "05";
        public static final String VALE_ALIMENTACAO = "10";
        public static final String VALE_REFEICAO = "11";
        public static final String VALE_PRESENTE = "12";
        public static final String VALE_COMBUSTIVEL = "13";
        public static final String OUTROS = "99";
    }
    
    public static class BANDEIRA_OPERADORA_CARTAO {
        public static final String VISA = "01";
        public static final String MASTERCARD = "02";
        public static final String AMERICAN_EXPRESS = "03";
        public static final String SOROCRED = "04";
        public static final String OUTROS = "99";
    }
  
    public static class WEBSERVICES {
        
        /* Sefaz Rio Grande do Sul - (RS)  */
        public static class RS {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx ";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx";
                    public static final String CONSULTA_CADASTRO = "https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                    public static final String CONSULTA_DEST = "https://nfe.sefaz.rs.gov.br/ws/nfeConsultaDest/nfeConsultaDest.asmx";
                    public static final String DOWNLOAD_NF = "https://nfe.sefaz.rs.gov.br/ws/nfeConsultaDest/nfeConsultaDest.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx ";
                    public static final String INUTILIZACAO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
		    public static final String CONSULTA_DEST = "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeConsultaDest/nfeConsultaDest.asmx";
		    public static final String DOWNLOAD_NF = "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeDownloadNF/nfeDownloadNF.asmx";
                }
            }
            
            /*
             * A versao 3.00 eh exclusiva para os participantes do projeto piloto.
             * Para as demais empresas, utilizar a versao 3.10
             */
            public static class VERSAO_310 { 
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx";
                    public static final String CONSULTA_CADASTRO = "https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                    public static final String CONSULTA_DEST = "https://nfe.sefaz.rs.gov.br/ws/nfeConsultaDest/nfeConsultaDest.asmx";
                    public static final String DOWNLOAD_NF = "https://nfe.sefaz.rs.gov.br/ws/nfeConsultaDest/nfeDownloadNF.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx ";
                    public static final String RETRECEPCAO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
                    public static final String INUTILIZACAO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "https://sef.sefaz.rs.gov.br/ws/cadconsultacadastro/cadconsultacadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe.sefaz.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                    public static final String CONSULTA_DEST = "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeConsultaDest/nfeConsultaDest.asmx";
                    public static final String DOWNLOAD_NF = "https://homologacao.nfe.sefaz.rs.gov.br/ws/nfeDownloadNF/nfeDownloadNF.asmx";
                }
            }
        }  
        
        /* Sefaz Amazonas - (AM) */
        public static class AM {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.am.gov.br/services2/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.am.gov.br/services2/services/NfeRetRecepcao2";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.am.gov.br/services2/services/NfeInutilizacao2";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.am.gov.br/services2/services/NfeConsulta2";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.am.gov.br/services2/services/NfeStatusServico2";
                    public static final String CONSULTA_CADASTRO = "https://nfe.sefaz.am.gov.br/services2/services/cadconsultacadastro2";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.ba.gov.br/webservices/sre/RecepcaoEvento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homnfe.sefaz.am.gov.br/services2/services/NfeRecepcao2";
                    public static final String RETRECEPCAO = "https://homnfe.sefaz.am.gov.br/services2/services/NfeRetRecepcao2";
                    public static final String INUTILIZACAO = "https://homnfe.sefaz.am.gov.br/services2/services/NfeInutilizacao2";
                    public static final String CONSULTA_PROTOCOLO = "https://homnfe.sefaz.am.gov.br/services2/services/NfeConsulta2";
                    public static final String STATUS_SERVICO = "https://homnfe.sefaz.am.gov.br/services2/services/NfeStatusServico2";
		    public static final String CONSULTA_CADASTRO = "https://homnfe.sefaz.am.gov.br/services2/services/cadconsultacadastro2";
                    public static final String RECEPCAO_EVENTO = "https://homnfe.sefaz.am.gov.br/services2/services/RecepcaoEvento";
                }
            }
        }  
        
        /* Sefaz Bahia - (BA) */
        public static class BA {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeInutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.ba.gov.br/webservices/nfenw/NfeStatusServico2.asmx";
                    public static final String CONSULTA_CADASTRO = "https://nfe.sefaz.ba.gov.br/webservices/nfenw/CadConsultaCadastro2.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeInutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://hnfe.sefaz.ba.gov.br/webservices/nfenw/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_STATUS = "https://hnfe.sefaz.ba.gov.br/webservices/nfenw/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://hnfe.sefaz.ba.gov.br/webservices/sre/RecepcaoEvento.asmx";
                }
            }
        }  
        
        /* Sefaz Ceará - (CE)  */
        public static class CE {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.ce.gov.br/nfe2/services/NfeRetRecepcao2 ";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2 ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.ce.gov.br/nfe2/services/NfeConsulta2 ";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.ce.gov.br/nfe2/services/NfeConsulta2 ";
                    public static final String CONSULTA_CADASTRO = "https://nfe.sefaz.ce.gov.br/nfe2/services/CadConsultaCadastro2 ";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeRetRecepcao2 ";
                    public static final String INUTILIZACAO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeInutilizacao2 ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeConsulta2 ";
                    public static final String STATUS_SERVICO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/NfeStatusServico2 ";
                    public static final String CONSULTA_CADASTRO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/CadConsultaCadastro2 ";
                    public static final String RECEPCAO_EVENTO = "https://nfeh.sefaz.ce.gov.br/nfe2/services/RecepcaoEvento";
                }
            }
        } 
        
        /* Sefaz Espírito Santo - (ES) */
        public static class ES {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "";
                    public static final String RETRECEPCAO = "";
                    public static final String INUTILIZACAO = "";
                    public static final String CONSULTA_PROTOCOLO = "";
                    public static final String STATUS_SERVICO = "";
                    public static final String CONSULTA_CADASTRO = "https://app.sefaz.es.gov.br/ConsultaCadastroService/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "";
                    public static final String RETRECEPCAO = "";
                    public static final String INUTILIZACAO = "";
                    public static final String CONSULTA_PROTOCOLO = "";
                    public static final String STATUS_SERVICO = "";
                    public static final String CONSULTA_CADASTRO = "https://app.sefaz.es.gov.br/ConsultaCadastroService/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "";
                }
            }
        }  
        
        /* Sefaz Goias - (GO)  */
        public static class GO {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRecepcao2?wsdl ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeRetRecepcao2?wsdl ";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2?wsdl ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2?wsdl ";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl ";
                    public static final String CONSULTA_CADASTRO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/CadConsultaCadastro2?wsdl";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.go.gov.br/nfe/services/v2/RecepcaoEvento?wsdl";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRecepcao2?wsdl ";
                    public static final String RETRECEPCAO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRetRecepcao2?wsdl ";
                    public static final String INUTILIZACAO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeInutilizacao2?wsdl ";
                    public static final String CONSULTA_PROTOCOLO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeConsulta2?wsdl ";
                    public static final String STATUS_SERVICO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeStatusServico2?wsdl ";
                    public static final String CONSULTA_CADASTRO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/CadConsultaCadastro2?wsdl";
                    public static final String RECEPCAO_EVENTO = "https://homolog.sefaz.go.gov.br/nfe/services/v2/NfeRecepcaoEvento?wsdl";
                }
            }
        }  
        
        /* Sef Minas Gerais - (MG) */
        public static class MG {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://nfe.fazenda.mg.gov.br/nfe2/services/NfeRetRecepcao2 ";
                    public static final String INUTILIZACAO = "https://nfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2 ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2 ";
                    public static final String STATUS_SERVICO = "https://nfe.fazenda.mg.gov.br/nfe2/services/NfeStatus2 ";
                    public static final String CONSULTA_CADASTRO = "https://nfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2";
                    public static final String RECEPCAO_EVENTO = "https://nfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeRetRecepcao2 ";
                    public static final String INUTILIZACAO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeInutilizacao2 ";
                    public static final String CONSULTA_PROTOCOLO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeConsulta2 ";
                    public static final String STATUS_SERVICO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/NfeStatusServico2 ";
                    public static final String CONSULTA_CADASTRO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/cadconsultacadastro2";
                    public static final String RECEPCAO_EVENTO = "https://hnfe.fazenda.mg.gov.br/nfe2/services/RecepcaoEvento";
                }
            }
        }  
        
        /* Sefaz Mato Grosso do Sul - (MS)  */
        public static class MS {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.fazenda.ms.gov.br/producao/services2/NfeRecepcao2";
                    public static final String RETRECEPCAO = "https://nfe.fazenda.ms.gov.br/producao/services2/NfeRetRecepcao2";
                    public static final String INUTILIZACAO = "https://nfe.fazenda.ms.gov.br/producao/services2/NfeInutilizacao2";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.fazenda.ms.gov.br/producao/services2/NfeConsulta2";
                    public static final String STATUS_SERVICO = "https://nfe.fazenda.ms.gov.br/producao/services2/NfeStatusServico2";
                    public static final String CONSULTA_CADASTRO = "https://nfe.fazenda.ms.gov.br/producao/services2/CadConsultaCadastro2";
                    public static final String RECEPCAO_EVENTO = "https://nfe.fazenda.ms.gov.br/producao/services2/RecepcaoEvento";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeRecepcao2";
                    public static final String RETRECEPCAO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeRetRecepcao2";
                    public static final String INUTILIZACAO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeInutilizacao2";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeConsulta2";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/NfeStatusServico2";
                    public static final String CONSULTA_CADASTRO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/CadConsultaCadastro2";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe.ms.gov.br/homologacao/services2/RecepcaoEvento";
                }
            }
        }  
        
        /* Sefaz Mato Grosso - (MT)  */
        public static class MT {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl ";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2?wsdl ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl ";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2?wsdl ";
                    public static final String CONSULTA_CADASTRO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro2?wsdl ";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.mt.gov.br/nfews/v2/services/CadConsultaCadastro2?wsdl ";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRecepcao2?wsdl ";
                    public static final String RETRECEPCAO = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeRetRecepcao2?wsdl ";
                    public static final String INUTILIZACAO = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeInutilizacao2?wsdl ";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeConsulta2?wsdl ";
                    public static final String STATUS_SERVICO = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/NfeStatusServico2?wsdl ";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.sefaz.mt.gov.br/nfews/v2/services/RecepcaoEvento?wsdl";
                }
            }
        }  
        
        /* Sefaz Pernambuco - (PE)  */
        public static class PE {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeRetRecepcao2 ";
                    public static final String INUTILIZACAO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2 ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2 ";
                    public static final String STATUS_SERVICO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2 ";
                    public static final String CONSULTA_CADASTRO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/CadConsultaCadastro2";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRecepcao2 ";
                    public static final String RETRECEPCAO = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeRetRecepcao2 ";
                    public static final String INUTILIZACAO = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeInutilizacao2 ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeConsulta2 ";
                    public static final String STATUS_SERVICO = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/NfeStatusServico2 ";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://nfehomolog.sefaz.pe.gov.br/nfe-service/services/RecepcaoEvento";
                }
            }
        }  
        
        /* Sefaz Paraná - (PR)  */
        public static class PR {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe2.fazenda.pr.gov.br/nfe/NFeRecepcao2?wsdl";
                    public static final String RETRECEPCAO = "https://nfe2.fazenda.pr.gov.br/nfe/NFeRetRecepcao2?wsdl";
                    public static final String INUTILIZACAO = "https://nfe2.fazenda.pr.gov.br/nfe/NFeInutilizacao2?wsdl";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe2.fazenda.pr.gov.br/nfe/NFeConsulta2?wsdl";
                    public static final String STATUS_SERVICO = "https://nfe2.fazenda.pr.gov.br/nfe/NFeStatusServico2?wsdl";
                    public static final String CONSULTA_CADASTRO = "https://nfe2.fazenda.pr.gov.br/nfe/NFeStatusServico2?wsdl";
                    public static final String RECEPCAO_EVENTO = "https://nfe2.fazenda.pr.gov.br/nfe-evento/NFeRecepcaoEvento?wsdl";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeRecepcao2?wsdl";
                    public static final String RETRECEPCAO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeRetRecepcao2?wsdl";
                    public static final String INUTILIZACAO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeInutilizacao2?wsdl";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeConsulta2?wsdl";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe/NFeStatusServico2?wsdl";
                    public static final String CONSULTA_CADASTRO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe/CadConsultaCadastro2?wsdl";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe2.fazenda.pr.gov.br/nfe-evento/NFeRecepcaoEvento?wsdl";
                }
            }
        }  
        
        /* Sefaz São Paulo - (SP)  */
        public static class SP {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/nferecepcao2.asmx";
                    public static final String RETRECEPCAO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/nferetrecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/nfeinutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/nfestatusservico2.asmx";
                    public static final String CONSULTA_CADASTRO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://nfe.fazenda.sp.gov.br/eventosWEB/services/RecepcaoEvento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRecepcao2.asmx";
                    public static final String RETRECEPCAO = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeInutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/NfeStatusServico2.asmx";
                    public static final String CONSULTA_CADASTRO = "https://homologacao.nfe.fazenda.sp.gov.br/nfeweb/services/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe.fazenda.sp.gov.br/eventosWEB/services/RecepcaoEvento.asmx";
                }
            }
        }  
        
        /* Sefaz Virtual Ambiente Nacional - (SVAN) */
        public static class SVAN {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://www.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://www.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://www.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://www.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://www.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                    public static final String DOWNLOAD_NF = "https://www.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://hom.sefazvirtual.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://hom.sefazvirtual.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://hom.sefazvirtual.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://hom.sefazvirtual.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://hom.sefazvirtual.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://hom.sefazvirtual.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                    public static final String DOWNLOAD_NF = "https://hom.sefazvirtual.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx";
                }
            }
        }   
        
        /* Sefaz Virtual Rio Grande do Sul - (SVRS)  */
        public static class SVRS {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefazvirtual.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "https://svp-ws.sefazvirtual.rs.gov.br/ws/CadConsultaCadastro/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/Nferecepcao/NFeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetRecepcao/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "https://webservice.set.rn.gov.br/projetonfehomolog/set_nfe/servicos/CadConsultaCadastroWS.asmx";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                }
            }
			
			public static class VERSAO_310 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
                    public static final String RETRECEPCAO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
                    public static final String INUTILIZACAO = "https://nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "https://svp-ws.sefazvirtual.rs.gov.br/ws/CadConsultaCadastro/CadConsultaCadastro2.asmx";
                    public static final String RECEPCAO_EVENTO = "https://nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
                    public static final String RETRECEPCAO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
                    public static final String INUTILIZACAO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeConsulta/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeStatusServico/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "https://webservice.set.rn.gov.br/projetonfehomolog/set_nfe/servicos/CadConsultaCadastroWS.asmx";
                    public static final String RECEPCAO_EVENTO = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
                }
            }
        }  
       
        /* Sefaz Contingência Ambiente Nacional - (SCAN)  */
        public static class SCAN {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://www.scan.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://www.scan.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://www.scan.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://www.scan.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://www.scan.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://www.scan.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://hom.nfe.fazenda.gov.br/SCAN/NfeRecepcao2/NfeRecepcao2.asmx ";
                    public static final String RETRECEPCAO = "https://hom.nfe.fazenda.gov.br/SCAN/NfeRetRecepcao2/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://hom.nfe.fazenda.gov.br/SCAN/NfeInutilizacao2/NfeInutilizacao2.asmx ";
                    public static final String CONSULTA_PROTOCOLO = "https://hom.nfe.fazenda.gov.br/SCAN/NfeConsulta2/NfeConsulta2.asmx ";
                    public static final String STATUS_SERVICO = "https://hom.nfe.fazenda.gov.br/SCAN/NfeStatusServico2/NfeStatusServico2.asmx ";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://hom.nfe.fazenda.gov.br/SCAN/RecepcaoEvento/RecepcaoEvento.asmx";
                }
            }
        }  
        
        /* Sefaz Virtual de Contingência Ambiente Nacional - (SVC)  */
        public static class SVC {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "https://www.svc.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx";
                    public static final String RETRECEPCAO = "https://www.svc.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://www.svc.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://www.svc.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://www.svc.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://www.svc.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "https://hom.svc.fazenda.gov.br/NfeRecepcao2/NfeRecepcao2.asmx";
                    public static final String RETRECEPCAO = "https://hom.svc.fazenda.gov.br/NfeRetRecepcao2/NfeRetRecepcao2.asmx";
                    public static final String INUTILIZACAO = "https://hom.svc.fazenda.gov.br/NfeInutilizacao2/NfeInutilizacao2.asmx";
                    public static final String CONSULTA_PROTOCOLO = "https://hom.svc.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx";
                    public static final String STATUS_SERVICO = "https://hom.svc.fazenda.gov.br/NfeStatusServico2/NfeStatusServico2.asmx";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://hom.svc.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                }
            }
        }  
        
        /* Ambiente Nacional - (AN)  */
        public static class AN {

            public static class VERSAO_200 {
          
                public static class PRODUCAO {
                    public static final String RECEPCAO = "";
                    public static final String RETRECEPCAO = "";
                    public static final String INUTILIZACAO = "";
                    public static final String CONSULTA_PROTOCOLO = "";
                    public static final String STATUS_SERVICO = "";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                    public static final String CONSULTA_DEST = "https://www.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx ";
                    public static final String DOWNLOAD_NF = "https://www.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx";
                }

                public static class HOMOLOGACAO {
                    public static final String RECEPCAO = "";
                    public static final String RETRECEPCAO = "";
                    public static final String INUTILIZACAO = "";
                    public static final String CONSULTA_PROTOCOLO = "";
                    public static final String STATUS_SERVICO = "";
                    public static final String CONSULTA_CADASTRO = "";
                    public static final String RECEPCAO_EVENTO = "https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
                    public static final String CONSULTA_DEST = "https://hom.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx";
                    public static final String DOWNLOAD_NF = "https://hom.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx";
                }
            }
        }  
        
        
    }
    
}
