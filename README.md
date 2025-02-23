# Show da Programação

## Introdução
O **Show da Programação** é um jogo educativo desenvolvido como uma paródia do programa "Show do Milhão", adaptado para o ensino de **Engenharia de Software**. O objetivo do jogo é ensinar conceitos fundamentais da área, incluindo **organização de projetos, criação de jogos e testes de software**.

Diferentemente de abordagens tradicionais, o jogo utiliza humor e vozes dubladas pela própria equipe para proporcionar uma experiência mais imersiva e divertida aos jogadores.

### Vozes
- **Kermit**: Walter Cavalcante  
- **Faustão**: Gustavo Lameu  
- **Lula**: Thiago Guilherme  

## Objetivos
- Proporcionar uma experiência interativa e divertida para o aprendizado de **Engenharia de Software**.
- Ensinar conceitos importantes como **gerenciamento de projetos, testes de software e desenvolvimento de jogos**.
- Tornar o aprendizado mais acessível e dinâmico por meio de **gamificação e humor**.

## Tecnologias Utilizadas
- **Linguagem de Programação**: Java
- **Framework**: JavaFX
- **Gerenciamento de Dependências**: Maven
- **IDE**: IntelliJ IDEA
- **Formato de Armazenamento das Perguntas e Respostas**: JSON (utilizando a biblioteca Gson)
- **Reprodução de áudio**: JavaZoom
- **Armazenamento de arquivos**: Imagens e áudios estão localizados na pasta `resources`

## Mecânica do Jogo
O jogo segue a estrutura clássica de perguntas e respostas, onde o jogador responde questões relacionadas à Engenharia de Software.

- A cada resposta correta, o jogador **avança e acumula dinheiro fictício**.
- O jogo utiliza **dublagens próprias** para tornar a experiência mais envolvente e engraçada.
- A interface do jogo é intuitiva, com um **layout inspirado no "Show do Milhão"**.
- A mecânica incentiva o jogador a pensar estrategicamente, aprendendo enquanto se diverte.
- O jogador pode utilizar **botões de ajuda**, incluindo amigos, cartas e convidados, para auxiliá-lo nas perguntas mais difíceis.
- Quem acertar **10 perguntas** ganha **1 milhão de reais fictícios**.

## Estrutura das Perguntas
As perguntas serão armazenadas em arquivos **JSON**, contendo os seguintes elementos:
- **Tema abordado** (ex: Gerenciamento de Projetos, Testes de Software, Desenvolvimento de Jogos)
- **Enunciado da pergunta**
- **Alternativas de resposta** (0, 1, 2, 3)
- **Resposta correta** na alternativa `0`

## Estrutura do Código
O projeto conta com diversas classes principais:

- **Main**: Ponto de entrada do jogo, inicializa a interface e os componentes principais.
- **OrganizadorAudios**: Responsável por gerenciar a reprodução dos arquivos de áudio utilizando JavaZoom.
- **GameOver**: Controla a tela de fim de jogo e exibe mensagens dependendo do desempenho do jogador.
- **Jogatina**: Contém a lógica principal do jogo, incluindo a dinâmica das perguntas e respostas.
- **LeitorJson**: Responsável por carregar e interpretar as perguntas e respostas armazenadas em JSON usando Gson.
- **TelaInicial**: Gera a interface da tela principal do jogo, permitindo ao jogador iniciar a partida.
- **TelaPrincipal**: Controla a interface durante a jogatina, exibindo perguntas, opções de resposta e o progresso do jogador.
- **BotoesAjuda & BotoesAjuda2**: Implementam os botões de ajuda que oferecem dicas para os jogadores em momentos difíceis.

## Link do Projeto
Repositório do projeto no GitHub:  
[https://github.com/walterr2307/show_programacao](https://github.com/walterr2307/show_programacao)

## Conclusão
O **Show da Programação** combina aprendizado e entretenimento para tornar o ensino de **Engenharia de Software** mais acessível e divertido. Com o uso de **JavaFX** e um formato interativo, buscamos engajar os jogadores e promover um ensino dinâmico e inovador, transformando o aprendizado em uma experiência mais leve e divertida.

