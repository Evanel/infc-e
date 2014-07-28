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
package iNFe;

import Frames.frmConfiguracao;
import java.io.File;
import java.io.Serializable;
import nfce.Configuracoes;
import nfe.util.Log;
import nfe.util.Util;

/**
 *
 * @author Ivan Vargas
 */
public class Configuracao implements Serializable{
    
    //private static final String ARQUIVO_CONFIGURACAO = Util.getCurrentDir() + "/configuracao.dat";
    private static final String ARQUIVO_CONFIGURACAO = "configuracao.dat"; //procura no diretorio current
    protected Configuracoes c = null;
    private File fileConfig = null;
    
    private static Configuracao INSTANCE = null;
    
    public static void setInstance(Configuracao configuracao) {
        INSTANCE = configuracao;
    }
    
    public synchronized static Configuracao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuracao();
        }
        return INSTANCE;
    }
    
    public static void Configurar() {
        frmConfiguracao c = new frmConfiguracao();
        c.setVisible(true);
    }
    
    public Configuracao() 
    {
        try
        {
            /* retorna o endereco do arquivo no jar */
            //URL u = getClass().getResource(ARQUIVO_CONFIGURACAO); 
            //fileConfig = new File(u.toURI()); /* acesso o arquivo */
            fileConfig = new File(ARQUIVO_CONFIGURACAO);
            
            //getConfiguracoes();
            
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao carregar configurações: " + ex.getMessage() );
        }
    }
    
    public Configuracoes getConfiguracoes() {
        
        if(c == null)
        {
            try
            {
                c = (Configuracoes)Util.desSerializar(fileConfig);
                if (c == null)
                    return c = new Configuracoes(); //se nao encontrou o arquivo, gero uma classe nova
                
            }catch(Exception ex) {
                Log.getInstance().addErro("Erro ao retornar Configurações: " + ex.getMessage());
            }
        }
        return c;
        
    }
         
    public boolean salvarConfiguracoes() {
        try
        {
            return Util.serializar(c, fileConfig); 
                       
        }catch(Exception ex) {
            Log.getInstance().addErro("Erro ao Salvar Configurações: " + ex.getMessage());
            return false;
        }
    }
   
   
}
