O iNFC-e é um projeto que ainda está em desenvolvimento, e que tem por finalidade facilitar a vida do programador Java que pretende desenvolver um Emissor de Nota Fiscal Eletrônica para o Consumidor Final (NFC-e).

Comecei a desenvolver este projeto em Novembro de 2013. Nos testes que realizei consegui gerar, assinar, validar, enviar, consultar, cancelar, corrigir e imprimir o DANFE de NFC-e, tanto em homologação quanto em produção. Minha ideia era construir um PDV para essa tarefa. Porém, devido a uma questão de prioridades, acabei deixando o projeto em segundo plano, e agora, para ajudar os desenvolvedores que estão começando nessa empreitada, resolvi liberar o código fonte do que até então havia desenvolvido, para assim, junto com a comunidade Java, darmos continuidade a este projeto que, com certeza, pode ser útil para muita gente.

O iNFC-e possui, basicamente, três módulos:

1. NFCe2
2. iQrCode
3. iDanfeNFCe

Na pasta "Libs" do projeto você pode encontrar os pacotes compilados de cada um destes módulos. Para ter uma ideia de como funciona, basta clicar sobre eles que uma tela explicativa será mostrada para o você.

1. NFCe2: Este módulo é o responsável por todas as operações envolvendo a emissão de NFC-e. Gerar nota em XML, assinar, validar, enviar, consultar, imprimir, etc... tudo é realizado por ele.

2. iQrCode: quem já trabalhou com QrCode sabe o quanto pode ser chato chamar funções para codificar e decodificar neste formato. O iQrCode se vale de projetos já existentes e apenas adiciona algumas classes para facilitar o processo de gerar o QrCode que deve ser impresso no Danfe.

3. iDanfeNFCe: este módulo é o responsável por ler o XML da NFC-e, gerar o hash do QrCode para consulta, gerar a imagem com o QrCode que será impresso no Danfe e, por fim, gerar o Danfe em PDF... o enviando para a impressora padrão do sistema.

Todos estes módulos funcionaram nos testes que realizei, dentro, é claro, das particularidades do meu ambiente, Estado, certificados, etc.

Enfim, disponibilizo aqui os fontes de cada um destes módulos. Acredito que muito pode ser melhorado, e conto com a contribuição de todos os que desejam ajudar no desenvolvimento do iNFC-e.

Para dúvidas, críticas e sugestões, entre em contato comigo. No site do projeto você encontra todas as informações necessárias para isso.

Atenciosamente,

Ivan S. Vargas
ivan@is5.com.br