# Star Wars Resistence Social Network

## Descrição 

API REST que armazena informações sobre rebeldes, bem como os recursos que eles possuem.

## Documentação dos endpoints 

* **Postman**

    Todos os endpoints com seus parâmentros estão no [Postman](https://documenter.getpostman.com/view/2139593/S1TZwZxG?version=latest).

## Funções


* **Adicionar rebeldes**

    Adiciona um rebelde para a resistencia.

* **Atualizar localização do rebelde**
    
    Permite um rebelde atualizar sua localização atual.

* **Reportar o rebelde como um traidor**

    Quando um rebelde trair a resistência, os outros rebeldes podem reportar a traição. Caso um rebelde receba três reportes, esse rebelde é classificado como traidor. 

    Um traidor é restringido com:

    * Itens do inventário se tornam inacessíveis (Não pode negociar itens com os demais)
    * Não pode manipular o inventário
    * Não é exibido em relátorios

* **Negociar itens**

    Os pertences dos rebeldes são declarados somente quando eles são registrados no sistema. Após isso eles só poderão mudar seu inventário através de negociação com os outros rebeldes.

    Para que possa ocorrer a troca, os rebeldes devem respeitar a tabela de preços abaixo:

| Item      | Pontos   |
|-----------|----------|
| 1 Arma    | 4 pontos |
| 1 Munição | 3 pontos |
| 1 Água    | 2 pontos |
| 1 Comida  | 1 ponto  |

Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3).
A negociação em si não é armazenada, mas os itens são transferidos de um rebelde a outro.

* **Relatórios**

    * Prcentagem de Traidores
        Retorna a porcentagem de traidores na resistência.

    * Porcentagem de Rebeldes
        Retorna a porcentagem de Rebeldes na resistência que não são traidores.

    * Quantidade média de cada tipo de recurso por rebelde
        Retorna cada item possível para o rebelde, com sua quantidade entre os Rebeldes e sua media por Rebelde. Ex: 2 armas por Rebelde.
    
    * Pontos perdidos devido a traidores
        Retorna a quantidade de pontos que a resistencia perdeu devido a traição dos rebeldes.

## Tecnologias utilizadas

As tecnologias utilizadas foram Java, Spring boot, Spring Data, Hibernate (utilizando H2, pois é mais simples para a configuração e visualização rápida), Maven como gerenciador de dependência.

É utilizado o Junit para testes dos repositórios em salvar e atualizar.