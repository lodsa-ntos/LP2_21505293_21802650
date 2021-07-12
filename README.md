# No bairro do Freddy M.
![alt_text](TWDGame.jpg?raw=true "The Walking Deisi Game")

<p align="justify"> No bairro do Freddy M. passa-se algo de muito estranho. Algumas pessoas têm desaparecido
          sem deixar rasto. O Freddy resolveu investigar e descobriu que está no meio de um surto de zombies.
          O Freddy pretende salvar os seus vizinhos, mas não sabe qual é a melhor forma de o fazer.
          A simulação irá consistir na aplicação de várias regras que definem os movimentos dos
          humanos (e seus animais de companhia) ao longo de vários dias, e várias noites e tem como
          objetivo tentar planear os movimentos que permitam salvar o maior número de companheiros. "</p>

# Diagrama UML
![](diagrama.png?raw=true "Diagrama UML")

# Descrição do comportamento das criaturas
<p align="justify"> As criaturas vão ter comportamentos ligeiramente diferentes em função da sua categoria."</p>
As diferenças de comportamento vão dividir-se em duas grandes categorias:
    ## Iteração com equipamentos:
        - Os __Vivos__ só podem carregar um __Equipamento__ de cada vez.
            - As __Crianças Vivas__, devido à sua baixa estatura, não conseguem usar a espada de forma eficaz contra 
              todos os Zombies.
                - Conseguem apanhar a espada, mas apenas conseguem infligir dano a um zombie que também seja criança.
            - Os __Idosos Vivos__ não podem transportar equipamentos.
            - O __Escudo de Madeira__, quando empunhado por um Militar, aguenta dois ataques , pois o Militar 
              tem uma técnica defensiva de nível três.
            - Os __Cães Vivos__ podem transportar equipamento.
            - O __Coelho Vivo__ apenas se pode mover para casas que não tenham equipamentos
        - Os __Zombies__ (qualquer categoria) destroem equipamento.
            - Um __Zombie Vampiro__ não se pode mover para uma posição onde esteja uma __Cabeça de Alho__,
              ou seja o Zombie Vampiro não pode destruir uma Cabeça de Alho.
            - O __Coelho Zombie__ apenas se pode mover para casas que não tenham equipamentos
    ## Movimentos:
        - O movimento pode variar das seguintes formas:
            - A quantidade máxima de deslocamento que podem fazer.
            - Em que tipo de turno se podem mover (diurno, noturno, ambos).
            - As direções nas quais se podem mover:
                ### Principais
                    - Norte, Sul, Este, Oeste
                ### Colaterais
                    - Noroeste, Nordeste, Sudoeste, Sudeste
        - Caso um __Vivo__ se mova para uma casa que seja um __SafeHaven__ (uma área segura), sai do jogo.
        - Os __Zombies__ não se podem mover para os __SafeHaven__.
        - Um __Vivo__ não se pode mover para uma casa onde esteja outro __Vivo__.
        - Um __Zombie__ não se pode mover para uma casa onde esteja outro __Zombie__.
        - Uma criatura nunca pode passar por cima de outra  criatura, equipamento ou portas __SafeHaven__, 
          mesmo que tenha um deslocamento máximo superior a 1.
        ## Combate:
        - Um __Vivo__ apenas se pode mover para uma casa onde esteja um Zombie se tiver um equipamento ofensivo
          que possa ser usado contra esse zombie.
        - Se o __Vivo__ não tiver nenhum equipamento, então vai ser transformado em __Zombie__.
            - O __Zombie__ que se moveu e iniciou o combate vai continuar na sua casa original.
        - Os __Cães__ nunca podem ser atacados, porque assustam os __Zombies__.
            #### Restrições de Movimentos de Combate:
                - Se um __Vivo__ tiver uma pistola, não se pode mover para cima do Zombie Vampiro.

# Equipas
Neste jogo existem duas equipas:
    - [Os Vivo]()
        — A equipa [Os Vivos] é controlada pelo utilizador. Esta é a equipa que contém o Freddy M. e os seus amigos
          e vizinhos.
    - [Os Outros]()
        — A equipa [Os Outros] são apenas zombies.

# Equipamentos
| Defensivo/Ofensivo | Titulo                       | Descrição                                                                                                                                                                      |
|--------------------|------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Defensivo          | Escudo de Madeira            | Permite obter proteção contra 1 ataque de zombies.  Após esse ataque, o escudo é destruído.                                                                                    |
| Ofensivo           | Espada Hattori Hanzo         | Serve para decapitar e/ou desmembrar zombies.                                                                                                                                  |
| Ofensivo           | Pistola Walther PPK          | A pistola tem 3 balas, por isso permite matar 3 Zombies.  A pistola não tem efeito contra Zombies Vampiros.  Quando as balas se esgotarem, a pistola deixa de ter efeito.      |
| Defensivo          | Escudo Tático                | Permite obter proteção contra vários ataques.                                                                                                                                  |
| Defensivo          | Revista Maria                | Para enrolar no braço. Permite obter proteção contra ataques de Zombies Idosos.  Não protege contra ataques de outros Zombies.                                                 |
| Defensivo          | Cabeça de Alho               | Funciona como repelente e permite obter proteção contra ataques de Zombies Vampiros.                                                                                           |
| Ofensivo           | Estaca de Madeira            | Permite matar Zombies.  Inclui Zombies Vampiros.                                                                                                                               |
| Defensivo          | Garrafa de Lixívia (1 litro) | Permite obter proteção contra ataques de Zombies, pois o Zombies não sente o cheiro do vivo.  Para uma defesa ser bem sucedida, são necessários/gastos 0.3 litros de lixívia.  |
| Defensivo          | Veneno                       | O veneno dá proteção durante 2 turnos.  Se o “Vivo” estiver envenenado durante 3 turnos, morre.  Os Zombies não se podem mover para casas onde esteja um veneno.               |
| Defensivo          | Antídoto                     | O antídoto cura um “Vivo” envenenado.  Apenas podem apanhar o antídoto os “vivos” que estejam envenenados.  Ao ser apanhado, o antídoto é consumido automaticamente.           |
| Defensivo/Ofensivo | Beskar Helmet                | Este fantástico capacete futurista não só defende quem o usa, como também serve como arma, dado que multiplica a força das cabeçadas por um número muito grande.               |


# Regras do Jogo
- O programa é alimentado por um ficheiro de texto.
- O jogo está dividido em turnos.
- Em cada turno joga uma das equipas.
- A equipa que joga o primeiro turno pode variar e será entrada do programa.
- A cada dois turnos, o mundo passa do dia para noite. Ou seja, os dois primeiros turnos ocorrem durante o dia,
os dois turnos seguintes ocorrem durante a noite, e assim por diante.
- Jogar implica escolher uma criatura para ser movida e um destino para esse movimento.
- A jogada escolhida poderá ser:
    - Válida
    - Inválida: Neste caso, a equipa ativa terá de repetir a escolha da criatura e movimento.
- Validação da jogada:
    - Apenas podem ser movidas as criaturas que pertencem à equipa que está ativa no turno em questão.
- O jogo termina quando for atingida uma das seguintes condições:
    - Tiverem passados 3 dias e 3 noites sem que exista uma transformação de um Vivo em Zombie
    - Não existirem mais “Vivos” no mapa.
        - porque passaram para um Safe Haven
        - ou foram transformados em zombies
        - ou morreram envenenados
    - Não existirem mais zombies em jogo.
    - Ficar apenas idosos e o turno atual for um turno noturno.
    - Ficar apenas Zombies Vampiros e o turno atual for um turno diurno.
    
# Regras de Movimento (Particularidades)

  - ### Criança (Viva ou Zombie)
    - O deslocamento máximo é 1.
    - Movem-se em qualquer turno.
    - Movem-se na horizontal e na vertical.
      
  - ### Adulto (Vivo ou Zombie)
    - O deslocamento máximo é 2.
    - Movem-se em qualquer turno.
    - Movem-se em qualquer direção.

  - ### Militar (Vivo ou Zombie)
    - O deslocamento máximo é 3.
    - Movem-se em qualquer turno.
    - Movem-se em qualquer direção.
    
  - ### Idoso (Vivo)
    - O deslocamento máximo é 1.
    - Movem-se em turnos diurnos.
    - Só se movem nas 4 direções principais (N,S,E,O)

  - ### Idoso (Zombie)
    - O deslocamento máximo é 1.
    - Movem-se em qualquer turno.
    - Só se movem nas 4 direções principais (N,S,E,O)

  - ### Cão
    - O deslocamento máximo é 1.
    - Movem-se em qualquer turno.
    - Só se movem nas 4 direções colaterais (Noroeste, Nordeste, Sudoeste, Sudeste)

  - ### Coelho (Vivo ou Zombie)
    - Deslocamento:
        - três casas nos turnos pares
        - duas casas nos turnos ímpares
    - Movem-se em qualquer turno.
    - Movem-se na horizontal e na vertical

  - ### Zombie Vampiro
    - O deslocamento máximo é 2.
    - Movem-se em turnos noturnos.
    - Movem-se em qualquer direção.
    
# Implementação
## Classes
- TWDGameManager — responsável por gerir o jogo.
  
- Equipamentos — representa os equipamentos que vão existir espalhados no jogo e que podem ser apanhados e/ou destruídos 
                 pelas criaturas, e que contém os atributos relevantes para a caraterização dos equipamentos e 
                 os métodos/funções que processam os comportamentos relevantes para alguns equipamentos.
  
- Porta — representam as portas de um __SafeHaven__ (uma área segura) para onde as criaturas vivas se podem mover e 
          ficarem a salvo dos zombies.
  
- InvalidTWDInitialFileException — representa as exceções a serem lançadas no jogo, ou seja, contém os métodos/funções 
                                   que vão retornar o estado de validação de algo a validar no jogo:
                                        — Se o ficheiro tiver pelo menos 2 criaturas
                                        — Se alguma das criaturas contiver o número errado de componentes
                                            — Componentes: ID: tipo: X: Y
                                            — Número errado: menos que 5 ou mais do que 5.

### Classes abstratas
- Creature — representa todas as criaturas, ou seja, contém os atributos relevantes para a caraterização das criaturas que
  participam no jogo e também contém os métodos/funções que processam os comportamentos relevantes das
  criaturas.
    
    #### Classes filho
    - Adulto — contém os métodos/funções que processam os comportamentos relevantes da criatura adulto (Vivo e Zombie)
    - Cão — contém os métodos/funções que processam os comportamentos relevantes da criatura cão
    - Coelho — contém os métodos/funções que processam os comportamentos relevantes da criatura coelho (Vivo e Zombie)
    - Criança — contém os métodos/funções que processam os comportamentos relevantes da criatura criança (Vivo e Zombie)
    - Idoso — contém os métodos/funções que processam os comportamentos relevantes da criatura Idoso (Vivo e Zombie)
    - Militar — contém os métodos/funções que processam os comportamentos relevantes da criatura Militar (Vivo e Zombie)
    - ZombieVampiro — contém os métodos/funções que processam os comportamentos relevantes da para criatura Zombie Vampiro (Vivo e Zombie)

# Video de demonstração
https://youtu.be/4QDdYK6ZXPc

## Tela do jogo
![alt_text](WalkingDEISI.PNG?raw=true "Walking Deisi")
