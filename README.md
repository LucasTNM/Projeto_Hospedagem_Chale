## Aplicação em Java e PostgreSQl para simulação do Aluguel de Cháles - Projeto - 5

_Desenvolvido por Lucas Tony Magalhães de Araújo e Vitor Rafael de Sousa Vilhena._

  Este projeto é destinado à matéria de Programação 2, relacionada ao curso de Ciência da Computação do IFB no ano de 2024.
 Ele propõe a criação de um sistema completo de **Banco de dados** e **Java**, baseado em um serviço de hospedagem, que se utiliza do cadastro de dados de três tabelas pré-definidas: **Cliente, Hospedagem, e Chale**. A aplicação gera formulários que tornam o contato com o usuário mais agradável e dinâmico.

### Requisitos: 
Distribuição do Java SE 17 e a versão mais recente do PostgreSQL.

### Procedimento:

    1. Instale os programas requisitados.

    2. Tendo instalado e inicializado o PostgreSQL, use o arquivo: SchemaHospedagem.sql, 
    para criar três tabelas e nove elementos para teste, note que cada objeto contêm três elementos. 
    Esse processo pode ser feito através do terminal ou de uma IDE de sua preferência.
    
    3. Após essa etapa, é hora de compilar o programa, para isso adicione o arquivo: postgresql-42.2.23.jar 
    no Build Path caso seja necessário. Em seguida, na pasta HospedagemChale que contém as pastas bin e src, digite: javac -d bin src/br/com/pc2/rh/view/FrmPrincipal.java
    Enfim, execute: java -cp bin br.com.pc2.rh.view.FrmPrincipal
