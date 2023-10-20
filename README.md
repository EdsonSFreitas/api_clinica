### Projeto API Clinica Medica com Spring Framework
Projeto de uma API de Clínica para fins de prática aplicando boas práticas e autenticação/autorização via token JWT.

###   Dependências utilizadas
1. Spring boot
2. Spring Web
3. Spring Security 6
3. Lombok - Diminuir a verbosidade do código
4. Spring Data JPA
5. MySQL Driver
6. Flyway Migration - Controle de versão do banco de dados
7. Validation - Validações diversas
8. Spring OpenAPI UI - Swagger para documentação da API
9. Spring Context - Fornece contexto de execução da aplicação
10. Auth0 - Autenticação e Autorização via JWT

### Organização dos módulos:
* Package domain - Com pacotes separados por funcionalidade;
* Package Controller - Classes necessárias para configurar as requisições e respostas http, é a intermediária entre a camada de View e Model (services ou dados);
* Package infra - Com configuração do CORS;
* Package Security -Com todas as classes necessárias para configurar a autenticação e autorização via JWT;

### Configurando CORS 
* Configurar o CORS e habilitar uma origem específica para consumir a API;

### Banco de dados relacional
* Esse projeto usa o MySQL e ao usar o docker-compose.yml do projeto a base de dados será criada automaticamente e versionada com Flyway; 
  * Caso queira personalizar os dados do banco, edite o arquivo application.yml e o docker-compose.yml ajustando ao menos três parâmetros:
    ```
    spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      #Exemplo usando o padrao do projeto
      url: jdbc:mysql://mysqlcontainerforapp:3306/api_clinica?useTimezone=true&serverTimezone=UTC&useLegacyDate
      #url: jdbc:mysql://<localhost ou nome do service docker>:3306/<nome do banco>?useTimezone=true&serverTimezone=UTC&useLegacyDate
      username: root
      password: letmein
    jpa:
      show-sql: true
      hibernate:
        #ddl-auto: update
      properties:
        hibernate.format_sql: true

      logging:
       level:
        org:
         hibernate:
          type: trace
    ```

### Realizar um POST para a API
* Para essa API segue um JSON para criar um cadastro de médico:
```json
{
"nome": "Edson da Silva Freitas",
"email": "edson.s.freitas@gmail.com",
"crm": "123456",
"especialidade": "ORTOPEDIA",
"endereco": {
    "logradouro": "rua 1",
    "bairro": "bairro",
    "cep": "12345678",
    "cidade": "Osasco",
    "uf": "SP",
    "numero": "1",
    "complemento": "complemento"
    }
}
```

📋 Executando o projeto

* Clone esse projeto
```sh
  git clone https://github.com/EdsonSFreitas/api_clinica.git
```

* Altere para o diretório que contém os arquivos do docker as pastas src e scripts
```sh
  cd api_clinica/
```

* Inicie o _build_ dos _containers_ necessários para o projeto
```sh
  docker-compose up -d --build
```

* Após todo o processo do docker você terá acesso ao http://localhost:8080/swagger-ui/index.html para usar todos os endpoints disponíveis.
* No endpoint http://localhost:8080/login você pode usar o login _**admin**_ com senha _**12345@Letmein**_ para obter um token que deverá usar para autorizar o acesso aos demais endpoints. Credenciais para ambiente de laboratório, crie um novo para uso em produção.